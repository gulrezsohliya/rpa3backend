var app = angular.module('CommonApp', []).config(function ($httpProvider) {  
    $httpProvider.interceptors.push('CsrfTokenInterceptorService');    
});
