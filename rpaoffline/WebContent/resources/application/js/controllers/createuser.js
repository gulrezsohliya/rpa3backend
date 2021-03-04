/**
 * @Author Decent Khongstia
 */

$(document).ready(function () {
//    var scope = angular.element($("#createuserCtrl")).scope();
//    scope.$apply(function () {
//        scope.listUsers();
//    });
});

app.controller('createuserCtrl', ['$scope', '$sce', '$compile','$timeout','commonFactory', 'commonService', function ($scope, $sce, $compile,$timeout,commonFactory, commonService) {
	var scope = angular.element($("#createuserCtrl")).scope();
	commonService.success();
	/*Common Ajax Params*/
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
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.reset = function () {
    	$scope.user = new Userlogins();
    	$scope.actionButton = 1;
    };


    $scope.edit = function (usercode) {
    	$scope.actionButton = 2;
    	$scope.user = new Userlogins();
    	$scope.user.repasswords = "";
        $scope.users.forEach((o, x) => {
        	console.log(o)
        	if (o.usercode == usercode){
        		$scope.user = o;
        	}
        });
        $scope.listOfficeCells($scope.user.officecode);
        console.info("$scope.user: ",$scope.user);
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };
    
    $scope.save = function () {
    	
        if($scope.userForm.$invalid)
            return false;
//        $scope.user.password = ($scope.user.password === '') ? "" : sha256_digest($scope.user.password);
        $scope.user.mobileno = "";
        $scope.user.cellcode = $scope.user.cellcode!==""?parseInt($scope.user.cellcode):"";
        
        $scope.urlEndpoint = "./createuser";
    	
        commonService.save($scope.method, $scope.urlEndpoint, $scope.user, () => {$scope.reset();$scope.listUsers()}, () =>{alert("failed")});
    };
   
    $scope.update = () => {
	    if($scope.userForm.$invalid)
             return false;
    	
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateuser";
    	console.info("user:, ", $scope.user);
    	commonService.save($scope.method, $scope.urlEndpoint, $scope.user, () => {$scope.reset();$scope.listUsers()}, () => {});
    }
    
/*-----------------------------------------------------------------------------------------------------------------------------------*/
    
    $scope.setDataTable = function (obj) {
        jQuery("#displayRecords").html("");
        jQuery("#displayRecords").html("<table id='displayRecordsTable' style='width:100%' border='1'>\n\
                                                </table>");
        jQuery('#displayRecordsTable').DataTable({
            data: obj,
            columns: [
                {
                    "title": "User Code",
                    "data": "usercode",
                }, {
                    "title": "User Name",
                    "data": "username"
                }, {
                    "title": "Designation",
                    "data": "designation"
                }, {
                    "title": "Mobileno",
                    "data": "mobileno"
                }, {
                    "title": "Cell",
                    "data": "cellcode"
                }, {
                    "title": "Status",
                    "data": "enabled"
                }, {
                    "title": "Action",
                    "sortable": false,
                    "data": "usercode",
                    "render": function (data, type, full, meta) {
                        return '<div style="text-align:center"><button style="padding:5px" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button></div>';
// return '<center><button style="padding:5px" value="Edit" ng-click="edit(' +
// data + ')">Edit</button></center>';
                    }
                }
            ],
            "sortable": false,
            "columnDefs": [
                {"width": "2%", "targets": 0}
            ],
            "bLengthChange": false,
            createdRow: function (row, data, dataIndex) {
                $compile(angular.element(row).contents())($scope);
            }
        });
    };
    
    
    $scope.validateUserForm = function() {

        if($scope.user.officername === "" || $scope.user.officername === null){
            jQuery("#officername").focus();
            alert("Officer name cannot be empty");
            return false;
        }
        if($scope.department.key === "" || $scope.department.key === null){
            jQuery("#department").focus();
            alert("Please select department");
            return false;
        }
        if($scope.designationgrp.key === "" || $scope.designationgrp.key === null){
            jQuery("#designationgrp").focus();
            alert("Please select cadre group");
            return false;
        }
        if($scope.user.designation.designationid <= 0 || $scope.user.designation.designationid === null){
            jQuery("#designationid").focus();
            alert("Please select designation");
            return false;
        }
        if($scope.user.username === "" || $scope.user.username === null){
            jQuery("#username").focus();
            alert("username cannot be empty");
            return false;
        }
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
        

        /*READ DATA*/
        $scope.listOffices = () => {
            commonFactory.listOffices((response)=>{
        		$scope.offices=response;
        	});
        };
        $scope.listOffices();
        
        $scope.listOfficeCells = function (officecode = 0) {
        	commonFactory.listOfficeCells((response)=>{
        		$scope.cells=response;
        	}, officecode);
        };

        $scope.listUsers = () => {
        	commonFactory.listUsers((response)=>{
        		$scope.users=response;
        		$scope.setDataTable(response);
        	});
        };
        $scope.listUsers();
        
    }]);

