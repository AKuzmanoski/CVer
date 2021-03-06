/**
 * Created by User on 12/15/2015.
 */
App.factory("CvService", ["$resource", function ($resource) {
    return $resource("core/cvs/", {}, {
        getCv: {
            url: "core/cvs/:account",
            method: "GET",
            isArray: false
        },
        getCvs: {
            url: "core/cvs",
            method: "GET",
            isArray: true
        },
        createCv: {
            url: "core/cvs",
            method: "POST"
        },
        deleteCv: {
            url: "core/cvs/:account",
            method: "DELETE"
        }
    });
}]);