
$(document).ready(function () {
// var scope = angular.element($("#createuserCtrl")).scope();
// scope.$apply(function () {
// scope.listUsers();
// });
});
app.controller('changepasswordCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	var scope = angular.element($("#createuserCtrl")).scope();
	commonInitService.success();
	/* Common Ajax Params */
	var successMsg = "Success: Password updated";
	var errorMsg = "Error: Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "POST";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = 1;
	$scope.cellcode = null;
	$scope.cells = [];
	$scope.officecode = null;
	$scope.offices = [];
	
    $scope.user = new Userlogins();
    $scope.users = [];
    $scope.repassword = "";
    
    $scope.reset = function () {
    	$scope.user = new Userlogins();
    	$scope.actionButton = 1;
    };

    $scope.update = () => {
	    if($scope.userForm.$invalid)
             return false;
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateuser";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.user, () => {$scope.reset();$scope.listUsers(), MsgBox(successMsg)}, () => {MsgBox(errorMsg)});
    }
    
/*-----------------------------------------------------------------------------------------------------------------------------------*/
    
        
    	$scope.validateUserForm = function() {
        
	        if($scope.user.password === "" || $scope.user.password === null){
	            jQuery("#passwords").focus();
	            alert("password cannot be empty");
	            return false;
	        }
	        if($scope.repassword === "" || $scope.repassword === null){
	            jQuery("#repassword").focus();
	            alert("password cannot be empty");
	            return false;
	        }
	        if($scope.repassword !== $scope.user.password){
	            jQuery("#repassword").focus();
	            alert("passwords do not matches");
	            return false;
	        }
	            return true;
        };
        

        /* READ DATA */
        
        $scope.listUsers = () => {
        		commonInitFactory.listUser(usersession,(response)=>{
            		$scope.user=response;
            	});
        	
        };
    }]);

