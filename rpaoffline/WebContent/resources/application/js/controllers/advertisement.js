
$(document).ready(function () {
});

app.controller('advCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/*Common Ajax Params*/
	var successMsg = "Success: Office created/updated";
	var errorMsg = "Error: Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "POST";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	$scope.agedate_sameas_issuedate=true;
	/*------------------------*/
	
	$scope.actionButton = SAVE;
    $scope.office = new Office();
    $scope.offices = [];
    
    $scope.print = function (post) {
        console.log(post);
    };
    
    $scope.edit = function (officecode) {
    	$scope.actionButton = EDIT;
    	$scope.office =$scope.offices.filter(obj=>{
    		return obj.officecode==officecode;
    	})[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.office = new Office();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.officeForm.$invalid)
            return false;
        
        $scope.method = "POST";
        $scope.urlEndpoint = "./createoffice";
    	
        commonInitService.save($scope.method, $scope.urlEndpoint, $scope.office, () => {$scope.reset();$scope.listOffices(); MsgBox(successMsg)}, () =>{MsgBox(errorMsg)});
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.officeForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateoffice";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.office, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listOffices(); 
				MsgBox(successMsg);				
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    }
    $scope.toggleStatus=(officecode)=>{
    	$scope.office =$scope.offices.filter(obj=>{
    		return obj.officecode==officecode;
    	})[0];
    	$scope.office.enabled=$scope.office.enabled==='Y'?'N':'Y';
    	$scope.update(false);
    };
    
/*-----------------------------------------------------------------------------------------------------------------------------------*/
    
    $scope.setDataTable = function (obj) {
        jQuery("#displayRecords").html("");
        jQuery("#displayRecords").html("<table id='displayRecordsTable' style='width:100%' border='1'>\n\
                                                </table>");
        jQuery('#displayRecordsTable').DataTable({
            data: obj,
            columns: [
                {
                    "title": "Office Code",
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
                    "data": (row, type, set) => {
                    	let res = "<b>Signatory Name: </b>"+row.signatoryname;
                    	res += "<br/><b>Signatory Designation: </b>"+row.signatorydesignation;
                    	return res;
                    }
                }, {
                    "title": "Email ID",
                    "data": "emailid",
                },{
                    "title": "SMS Details",
                    "data":  (row, type, set) => {
                    	let res = "<b>SMS Username: </b>"+row.smsusername;
                    	res += "<br/><b>SMS Sender ID: </b>"+row.smssenderid;
                    	return res;
                    }
                },{
                    "title": "Enabled",
                    "data": "enabled"
                }, 
                {
                    "title": "Action",
                    "sortable": false,
                    "data": "officecode",
                    "render": function (data, type, row, meta) {
                    	let status = row.enabled == 'Y'?'Disable':'Enable';
                    	let div = '<div style="text-align:center"><button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button>';
                    		div += '<button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="toggleStatus(' + data + ')" class="button-primary">'+status+'</button></div>';
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
//    $scope.listOffices();
        
        
}]);

