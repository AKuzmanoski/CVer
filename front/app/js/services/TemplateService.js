/**
 * Created by Dimitar on 2/25/2016.
 */
App.factory("TemplateService", ["$resource", function ($resource) {
    return $resource("core/template", {}, {
        getDefaultTemplate: {
            url: "core/template",
            method: "GET",
            isArray: false
        }
    });
}]);