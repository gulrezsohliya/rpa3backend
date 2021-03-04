/**
 * @Author Decent Khongstia
 */

$(document).ready(function () {
});

app.controller('examCenterCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
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
	
    $scope.examCenter = new ExamCenter();
    $scope.examCenters = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (examcentercode) {
    	$scope.actionButton = 2;
    	$scope.examCenter = new ExamCenter();
    	$scope.examCenter.forEach((o, x) => {
        	if (o.centercode == centercode){
        		$scope.user = o;
        	}
        });
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.examCenter = new ExamCenter();
    	$scope.actionButton = 1;
    };

    $scope.save = function () {
        if($scope.examForm.$invalid)
            return false;
        
        $scope.method = "POST";
        $scope.urlEndpoint = "./createexamcenter";
    	
        commonInitService.save($scope.method, $scope.urlEndpoint, $scope.user, () => {$scope.reset();$scope.listUsers(); MsgBox(successMsg)}, () =>{MsgBox(errorMsg)});
    };
    
    $scope.update = () => {
	    if($scope.examForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateexamcenter";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.user, () => {$scope.reset();$scope.listUsers(), MsgBox(successMsg)}, () => {MsgBox(errorMsg)});
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
                    "data": "centercode",
                    render: function (data, type, row, meta){
                    	return meta.row+1;
                    }
                }, {
                    "title": "Examination Center Name",
                    "data": "centername"
                }, {
                    "title": "Action",
                    "sortable": false,
                    "data": "centercode",
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
    $scope.listExamCenters = () => {
        commonInitFactory.listExamCenters((response)=>{
    		$scope.examCenters = response;
    		$scope.setDataTable($scope.examCenters);
    	});
    };
    $scope.listExamCenters();
        
        
}]);

