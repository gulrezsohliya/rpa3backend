/**
 * @Author Decent Khongstia
 */

$(document).ready(function () {
});

app.controller('officeCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/*Common Ajax Params*/
	var successMsg = "Success: User created or updated successfully";
	var errorMsg = "Error: Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "POST";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = 1;
    $scope.office = new Office();
    $scope.offices = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (officecode) {
    	$scope.actionButton = 2;
    	$scope.office = new Office();
    	$scope.office.forEach((o, x) => {
        	if (o.officecode == officecode){
        		$scope.office = o;
        	}
        });
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.office = new Office();
    	$scope.actionButton = 1;
    };

    $scope.save = function () {
        if($scope.officeForm.$invalid)
            return false;
        
        $scope.method = "POST";
        $scope.urlEndpoint = "./createoffice";
    	
        commonInitService.save($scope.method, $scope.urlEndpoint, $scope.office, () => {$scope.reset();$scope.listOffices(); MsgBox(successMsg)}, () =>{MsgBox(errorMsg)});
    };
    
    $scope.update = () => {
	    if($scope.officeForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateoffice";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.user, () => {$scope.reset();$scope.listOffices(), MsgBox(successMsg)}, () => {MsgBox(errorMsg)});
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
                    "title": "Slno",
                    "data": "officecode",
                    render: function (data, type, row, meta){
                    	return meta.row+1;
                    }
                }, {
                    "title": "Office Name1",
                    "data": "officename1"
                }, {
                    "title": "Office Name2",
                    "data": "officename2"
                }, {
                    "title": "Office Name3",
                    "data": "officename3"
                }, {
                    "title": "Office Short Name",
                    "data": "officeshortname"
                },{
                    "title": "Signatory Details",
                    "data": "signatoryname",
                    render: (data, type, row, meta) => {
                    	let res = "<b>Signatory Name: </b>: <i>"+data+"</i>";
                    	res += "<b>Signatory Designation: </b>: <i>"+row.signatorydesignation+"</i>";
                    }
                }, {
                    "title": "Email ID",
                    "data": "emailid",
                },{
                    "title": "SMS Details",
                    "data": "smsusername",
                    render: (data, type, row, meta) => {
                    	let res = "<b>SMS Username: </b>: <i>"+data+"</i>";
                    	res += "<b>SMS Sender ID: </b>: <i>"+row.smssenderid+"</i>";
                    }
                }, 
                {
                    "title": "Action",
                    "sortable": false,
                    "data": "officecode",
                    "render": function (data, type, row, meta) {
                    	let status = row.enabled == 'Y'?'Disable':'Enable';
                    	let div = '<div style="text-align:center"><button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button>';
                    		div += '<button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="toggleUserStatus(' + data + ')" class="button-primary">'+status+'</button></div>';
                        return div;
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

    /*READ DATA*/
    $scope.listOffices = () => {
        commonInitFactory.listOffices((response)=>{
    		$scope.offices = response;
    		$scope.setDataTable($scope.offices);
    	});
    };
    $scope.listOffices();
        
        
}]);

