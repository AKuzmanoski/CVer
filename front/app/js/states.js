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
            }
        }
    });

    $stateProvider.state("cvsNew", {
        url: '/cvs/new',
        views: {
            main: {
                templateUrl: "views/newCv.html",
                controller: "CvController"
            }
        }
    });

    $stateProvider.state('cv', {
        url: '/cvs/:account',
        views: {
            main: {
                templateUrl: 'views/cv.html',
                controller: 'CvDetailsController'
            }
        }
    });
}]);

