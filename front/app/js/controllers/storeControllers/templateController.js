/**
 * Created by Dimitar on 2/25/2016.
 */
App.controller("TemplateController",["$scope", "$state", "TemplateService", "$sce","CvService",
    function ($scope, $state, templateService, $sce, cvService) {


        $scope.getCv = function(account) {
            return cvService.getCv({account: account});
        };

        //testing with an existing CV
        $scope.cv = $scope.getCv("DimitarSpasovski");
        console.log($scope.cv);

        $scope.array = ["test1","test2","test3"];
        $scope.defaultTemplate = templateService.getDefaultTemplate();
       //the html will be sanitized (inputs, scripts, and angular directives will get removed)
        $scope.renderHtml = function() {
            return $scope.defaultTemplate.response;
            //  return $sce.trustAsHtml($scope.defaultTemplate.response);
   };




}]);


App.directive('compileTemplate',['$compile','$parse', function($compile, $parse){
        return {
            link: function(scope, element, attr){
                var parsed = $parse(attr.ngBindHtml);
                function getStringValue() { return (parsed(scope) || '').toString(); }

                //Recompile if the template changes
                scope.$watch(getStringValue, function() {
                    $compile(element, null, -9999)(scope);  //The -9999 makes it skip directives so that we do not recompile ourselves
                });
            }
        }
    }]);