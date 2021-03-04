//var main=angular.module("Home", []);
app.controller('homeCtrl', ['$scope', '$sce','commonInitFactory', function($scope, $sce,commonInitFactory) {
	console.log("homeCtrl")    ;    
	$scope.value='';
//	commonInitFactory.listUsers((response)=>{
//		$scope.value=response;
//	});
    }
]);
//angular.module("CommonApp", ["Menu","Home"]);
