App.controller("CvDetailsController",["$scope", "$state", "$stateParams", "CvService", function ($scope, $state, $stateParams, CvService) {
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
        $state.go("cvs");
    };

    $scope.cv = $scope.getCv($stateParams.account);
}]);