/**
 * Created by User on 12/15/2015.
 */
App.factory("CvService", ["$state", "$resource", function ($state, $resource) {
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
            method: "POST",
            interceptor: {
                response: function (data) {
                    $state.go("cvs");
                },
                responseError: function (data) {

                }
            }
        },
        deleteCv: {
            url: "core/cvs/:account",
            method: "DELETE",
            interceptor: {
                response: function (data) {
                    $state.go("cv/:account");
                },
                responseError: function (data) {

                }
            }
        }
    });
}]);