/**
 * Created by User on 1/14/2016.
 */

App.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/cvs');

    $stateProvider.state('cvs', {
        url: '/cvs',
        views: {
            main: {
                templateUrl: "views/cvs.html",
                controller: "CvController"
            },
            navbar: {
                templateUrl: "views/toolbar.html",
                controller: "NavController"
            }
        }
    });

    $stateProvider.state("cvsNew", {
        url: '/cvs/new',
        views: {
            main: {
                templateUrl: "views/newCv.html",
                controller: "NewCvController"
            },
            navbar: {
                templateUrl: "views/toolbar.html",
                controller: "NavController"
            }
        }
    });

    $stateProvider.state('cv', {
        url: '/cvs/:account',
        views: {
            main: {
                templateUrl: 'views/cv.html',
                controller: 'CvDetailsController'
            },
            navbar: {
                templateUrl: "views/toolbar.html",
                controller: "NavController"
            }
        }
    });

    $stateProvider.state('template', {
        url: '/template',
        views: {
            main: {
                templateUrl: 'views/templateTest.html',
                controller: 'TemplateController'
            },
            navbar: {
                templateUrl: "views/toolbar.html",
                controller: "NavController"
            }
        }
    });


}]);

