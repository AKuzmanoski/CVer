/**
 * Created by Dimitar on 2/25/2016.
 */
App.controller("TemplateController",["$scope", "$state", "TemplateService", "$sce", function ($scope, $state, templateService, $sce) {
    /**
     * This method returns all public resumes.
     * @returns {*|{url, method, isArray}} array of all public resumes.
     */
   $scope.defaultTemplate = templateService.getDefaultTemplate();
   $scope.renderHtml = function() {
       return $sce.trustAsHtml($scope.defaultTemplate.response);
   }

}]);