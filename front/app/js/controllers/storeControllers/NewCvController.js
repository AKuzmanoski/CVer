App.controller("NewCvController",["$scope", "$state", "CvService", function ($scope, $state, CvService) {
    $scope.cv = {}

    $scope.createCv = function() {
        CvService.createCv($scope.cv);
        $scope.cv = {};
        //$state.go("cvs");
    };
}]);