/**
 * Created by User on 12/15/2015.
 */
App.factory("cvService", ["$resource", function ($resource) {
    return $resource("core/cv/", {}, {
        getCv: {
            url: "core/cv/:account",
            method: "GET",
            isArray: false
        },
        getCvs: {
            url: "core/cv",
            method: "GET",
            isArray: true
        }
    });
}]);