
$(document).ready(function () {
});

app.controller('examCenterCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/*Common Ajax Params*/
	var successMsg = "Success: Exam Center created/updated";
	var errorMsg = "Error: Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "POST";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = SAVE;
	
    $scope.examCenter = new ExamCenter();
    $scope.examCenters = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (centercode) {
    	$scope.actionButton = EDIT;
    	$scope.examCenter = $scope.examCenters.filter((o) => {
        	return o.centercode == centercode;
        })[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.examCenter = new ExamCenter();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.examForm.$invalid)
            return false;
        
        $scope.method = "POST";
    	$scope.urlEndpoint = "./createexamcenter";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.examCenter, () => {
    				$scope.reset();
    				$scope.listExamCenters(); 
    				MsgBox(successMsg)
				},
				(res) =>{
    				if(res.response===ALREADY_REPORTED){
    					MsgBox("Exam Center already exists");
    				}else{
    					MsgBox(errorMsg)
    				}
    			});    	
    };

    $scope.update = (checkform=true) => {
	    if(checkform && $scope.examForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateexamcenter";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.examCenter, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listExamCenters(); 
				MsgBox(successMsg);				
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    };

    $scope.delete = (centercode) => {
    	
    	$scope.method = "DELETE";
    	$scope.urlEndpoint = "./deleteexamcenter/"+centercode;
    	ConfirmBox("Are You Sure to Delete this entry? ",(response)=>{
	    	if(response){
	    		commonInitService.http($scope.method, $scope.urlEndpoint, centercode, (res) => {
		    		if(res===true){
		    			$scope.reset();
		    			$scope.listExamCenters(); 
		    		}else{
		    			MsgBox("Unable to delete.");								
		    		}
		    	},()=>{});
    		}
    	});
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
                    	let div = '<div style="text-align:center"><button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button>';
                    		div += '<button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="delete(' + data + ')" class="button-primary">Delete</button></div>';
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

