/**
 * Created by User on 12/15/2015.
 */
App.controller("cvController",["$scope", "cvService", function ($scope, cvService) {
    /**
     * This method returns the current instance of the Cv in this context.
     * @param id of the cv which will be gotten.
     * @returns {*|{url, method, isArray}}
     */
    $scope.getCv = function(id) {
        return cvService.getCv({id: id});
    };

    /**
     * This method refreshes the current cv which can be taken by getCv method.
     * @param id of the cv which will be refreshed.
     */
    $scope.findCv = function(id) {
        $scope.cv = $scope.getCv(id);
    };

    //$scope.findCv(1);

    $scope.getCvs = function() {
        return cvService.getCvs();
    };

    $scope.cvs = $scope.getCvs();
}]);