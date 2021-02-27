//var main=angular.module("Home", []);
app.controller('homeCtrl', ['$scope', '$sce','commonFactory', function($scope, $sce,commonFactory) {
	console.log("homeCtrl")    ;    
	$scope.value='';
	commonFactory.listUsers((response)=>{
		$scope.value=response;
	});
    }
]);
//angular.module("CommonApp", ["Menu","Home"]);
