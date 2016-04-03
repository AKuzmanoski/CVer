App.controller("NavController",["$scope", "$state", function ($scope, $state) {
    $scope.list = function() {
        $state.go("cvs");
    };

    $scope.new = function() {
        $state.go("cvsNew");
    };
}]);