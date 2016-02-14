/**
 * Created by User on 1/14/2016.
 */

App.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider.state('cvs', {
        url: '/cv',
        views: {
            main: {
                templateUrl: "views/cvs.html",
                controller: "cvController"
            }
        }

    });
}]);