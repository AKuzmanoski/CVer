/**
 *
 * Developed as a part of a project founded by Sorsix
 *
 * @Authors
 *  Aleksandar Kuzmanoski
 *
 **/
var gulp = require('gulp'),
    concat = require('gulp-concat'),
    templateCache = require('gulp-angular-templatecache'),
    rev = require('gulp-rev-append'),
    eslint = require('gulp-eslint'),
    rename = require('gulp-rename'),
    uglify = require('gulp-uglify'),
    uglifycss = require('gulp-uglifycss'),
    url = require('url'),
    proxy = require('proxy-middleware'),
    connect = require('gulp-connect'),
    plumber = require('gulp-plumber'),
    autoprefixer = require('gulp-autoprefixer'),
    browserSync = require('browser-sync'),
    reload = browserSync.reload,
    fs = require("fs");


var CSS_LIB = [
    'bower_components/angular-material/angular-material.min.css',
    'bower_components/angular-material/angular-material.layouts.min.css'
];

var CSS_APP = [
    'app/css/**/*.css',
    '!app/css/**/*.min.css'
];

var JS_LIB = [
    'bower_components/angular/angular.js',
    'bower_components/angular-resource/angular-resource.min.js',
    'bower_components/angular-ui-router/release/angular-ui-router.min.js',
    'bower_components/angular-translate/angular-translate.min.js',
    'bower_components/angular-translate-loader-static-files/angular-translate-loader-static-files.js',
    'bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.min.js',
    'bower_components/angular-sanitize/angular-sanitize.min.js',
    'bower_components/angular-messages/angular-messages.min.js',
    'bower_components/angular-animate/angular-animate.min.js',
    'bower_components/angular-aria/angular-aria.min.js',
    'bower_components/angular-material/angular-material.min.js'
];

var JS_APP = [
    'app/js/**/*.js',
    'app/js/**/*.min.js'
];

var VIEW_APP = [
    'app/views/**/**.html'
]


/**
 *   The location of the resources for deploy
 */
var DESTINATION = 'app/dest/';
/**
 * The single page initial html file. It will be altered
 * by this script.
 */
var INDEX_FILE = 'app/index.html';
/**
 * The name of the angular module
 */
var MODULE_NAME = 'cver';
/**
 * The URL of the back-end API
 */
var API_URL = 'http://localhost:8080/core';
/**
 * Route to which the API calls will be mapped
 */
var API_ROUTE = '/core';

/**
 * Task for concatenation of the js libraries used
 * in this project
 */
gulp.task('concat_js_lib', function () {
    return gulp.src(JS_LIB) // which js files
        .pipe(concat('lib.js')) // concatenate them in lib.js
        .pipe(rename({suffix: '.min'}))
        .pipe(uglify())
        .pipe(gulp.dest(DESTINATION)) // save lib.js in the DESTINATION folder
});

/**
 * Task for concatenation of the css libraries used
 * in this project
 */
gulp.task('concat_css_lib', function () {
    return gulp.src(CSS_LIB) // which css files
        .pipe(concat('lib.css')) // concat them in lib.css
        .pipe(rename({suffix: '.min'}))
        .pipe(uglifycss())
        .pipe(gulp.dest(DESTINATION)) // save lib.css in the DESTINATION folder
});

/**
 * Task for concatenation of the js code defined
 * in this project
 */
gulp.task('concat_js_app', function () {
    return gulp.src(JS_APP)
        .pipe(plumber())
        .pipe(concat('src.js'))
        .pipe(rename({suffix: '.min'}))
        .pipe(uglify())
        .pipe(gulp.dest(DESTINATION))
        .pipe(reload({stream: true}));
});

/**
 * Task for concatenation of the css code defined
 * in this project
 */
gulp.task('concat_css_app', function () {
    return gulp.src(CSS_APP)
        .pipe(plumber())
        .pipe(concat('app.css'))
        .pipe(rename({suffix: '.min'}))
        .pipe(uglifycss())
        .pipe(gulp.dest(DESTINATION))
        .pipe(reload({stream: true}));
});

/**
 * Task for concatenation of the html templates defined
 * in this project
 */
gulp.task('templates', function () {
    return gulp.src(VIEW_APP) // which html files
        .pipe(
            templateCache('templates.js', { // compile them as angular templates
                module: MODULE_NAME,        // from module MODULE_NAME
                root: ''             // of the app
            }))
        .pipe(gulp.dest(DESTINATION));
});

/**
 * Task for adding the revision as parameter
 * for cache braking
 */
gulp.task('cache-break', function () {
    return gulp.src(INDEX_FILE) // use the INDEX_FILE as source
        .pipe(rev())            // append the revision to all resources
        .pipe(gulp.dest('app/'));  // save the modified file at the same destination
});

var tasks = [
    'concat_js_lib',
    'concat_css_lib',
    'concat_js_app',
    'concat_css_app',
    'templates'
];

gulp.task('build', tasks, function () {
    gulp.start('cache-break');
});

gulp.task('html', function () {
    gulp.src('app/**/*.html')
        .pipe(reload({stream: true}));
});


gulp.task('watch', function () {
    gulp.watch('app/js/**/**.js', ['concat_js_app', 'cache-break']);
    gulp.watch('app/views/**/**.html', ['templates', 'cache-break']);
    gulp.watch('app/css/**/**.css', ['concat_css_app', 'cache-break']);
    gulp.watch('app/**/*.html', ['html']);
    gulp.watch('app/views/**/ *.html', ['templates', 'cache-break']);
});

gulp.task('serve', function () {
    connect.server({
        port: 8000,
        livereload: true,
        baseDir: "./app/",
        middleware: function (connect, opt) {
            return [
                (function () {
                    var options = url.parse(API_URL);
                    options.route = API_ROUTE;
                    return proxy(options);
                })()
            ];
        }
    });
});

gulp.task("browser-sync", function () {
    var proxyOptions = url.parse(API_URL);
    proxyOptions.route = API_ROUTE;

    browserSync({
        open: true,
        port: 3000,
        server: {
            baseDir: "./app/",
            middleware: [proxy(proxyOptions)]
        }
    });
});

gulp.task('default', ['templates', 'browser-sync', 'watch']);

gulp.task('full', tasks);