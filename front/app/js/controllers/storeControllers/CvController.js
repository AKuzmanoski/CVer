/**
 * Created by User on 12/15/2015.
 */
App.controller("CvController",["$scope", "$state", "CvService", function ($scope, $state, CvService) {
    /**
     * This method returns all public resumes.
     * @returns {*|{url, method, isArray}} array of all public resumes.
     */
    $scope.getCvs = function() {
        return CvService.getCvs();
    };

    $scope.cvs = $scope.getCvs();

    $scope.createNew = function() {
        $state.go("cvsNew");
    }
}]);