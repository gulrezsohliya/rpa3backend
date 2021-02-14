var main=angular.module("Home", []);
main.controller('homeCtrl', ['$scope', '$sce', function($scope, $sce) {
	console.log("homeCtrl")    ;    
    }
]);
angular.module("CommonApp", ["Menu","Home"]);
