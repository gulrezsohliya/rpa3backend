function GlobalCsrfTokenInterceptorService($q) {

    // Private constants.
    var CSRF_TOKEN_HEADER = $("meta[name='_csrf_header']").attr("content"),
        HTTP_TYPES_TO_ADD_TOKEN = ['DELETE', 'POST', 'PUT','GET'];

    // Private properties.
    var token = $("meta[name='_csrf']").attr("content");

    // Public interface.
    var service = {
        response: onSuccess,
        responseError: onFailure,
        request: onRequest,
    };

    return service;

    // Private functions.
    function onFailure(response) {
        if (response.status === 403) {
            console.log('Request forbidden. Ensure CSRF token is sent for non-idempotent requests.');
        }

        return $q.reject(response);
    }

    function onRequest(config) {
        if (HTTP_TYPES_TO_ADD_TOKEN.indexOf(config.method.toUpperCase()) !== -1) {
            config.headers[CSRF_TOKEN_HEADER] = token;
        }

        return config;
    }

    function onSuccess(response) {
        var newToken = response.headers(CSRF_TOKEN_HEADER);

        if (newToken) {
            console.log(newToken);
            token = newToken;
        }

        return response;
    }
};
app.factory('CsrfTokenInterceptorService', ['$q',
function CsrfTokenInterceptorService($q) {
       return GlobalCsrfTokenInterceptorService($q);
}]);

