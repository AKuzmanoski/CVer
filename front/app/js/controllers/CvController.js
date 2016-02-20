/**
 * Created by User on 12/15/2015.
 */
App.controller("CvController",["$scope", "$state", "CvService", function ($scope, $state, CvService) {
    /**
     * This method returns the current instance of the Cv in this context.
     * @param id of the cv which will be gotten.
     * @returns {*|{url, method, isArray}}
     */
    $scope.getCv = function(account) {
        return CvService.getCv({account: account});
    };

    $scope.deleteCv = function(account) {
        CvService.deleteCv({account: account});
    };

    /**
     * This method refreshes the current cv which can be taken by getCv method.
     * @param id of the cv which will be refreshed.
     */
    $scope.findCv = function(account) {
        $scope.cv = $scope.getCv(account);
    };

    //$scope.findCv(1);

    /**
     * This method returns all public resumes.
     * @returns {*|{url, method, isArray}} array of all public resumes.
     */
    $scope.getCvs = function() {
        return CvService.getCvs();
    };

    $scope.cvs = $scope.getCvs();

    $scope.cv = this.cv;

    $scope.createCv = function(cv) {
        console.log(cv);
        CvService.createCv(cv);
        $state.go("cvs");
    };

    $scope.createNew = function() {
        $state.go("cvsNew");
    }
}]);