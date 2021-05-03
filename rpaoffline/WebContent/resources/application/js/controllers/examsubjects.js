app.controller('examSubjectCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/* Common Ajax Params */
	var successMsg = "Success: Examination Subject created/updated";
	var errorMsg = "Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = SAVE;
    $scope.examSubject = new ExamSubjects();
    $scope.examSubjects = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (examinationsubjectcode) {
    	$scope.actionButton = EDIT;
    	$scope.examSubject = $scope.examSubjects.filter(obj=>{
    		return obj.examinationsubjectcode==examinationsubjectcode;
    	})[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.examSubject = new ExamSubjects();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.examSubjectForm.$invalid)
            return false;
        
        $scope.method = "POST";
    	$scope.urlEndpoint = "./createExamSubject";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.examSubject, () => {
    				$scope.reset();
    				$scope.listExamSubjects(); 
    				MsgBox(successMsg)
				},
				(res) =>{
    				if(res.response===ALREADY_REPORTED){
    					MsgBox("Examination Subject already exist");
    				}else{
    					MsgBox(errorMsg)
    				}
    			});
    	
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.examSubjectForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateExamSubject";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.examSubject, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listExamSubjects(); 
				MsgBox(successMsg);				
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    };
    
    $scope.delete = (othercategorycode) => {
    	
    	$scope.method = "DELETE";
    	$scope.urlEndpoint = "./deleteExamSubject/"+othercategorycode;
    	ConfirmBox("Are You Sure you want to delete the examination subject? ",(response)=>{
	    	if(response){
	    		commonInitService.http($scope.method, $scope.urlEndpoint, othercategorycode, (res) => {
		    		if(res===true){
		    			$scope.reset();
		    			$scope.listExamSubjects(); 
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
        jQuery("#displayRecords").html("<table id='displayRecordsTable' style='width:100%' border='1'></table>");
        jQuery('#displayRecordsTable').DataTable({
            data: obj,
            columns: [
                {
                    "title": "Examination Subject Code",
                    "data": "examinationsubjectcode"
                }, {
                    "title": "Examination Subject Name",
                    "data": "examinationsubjectname"
                }, {
                    "title": "Description",
                    "data": "description"
                }, 
                {
                    "title": "Action",
                    "sortable": false,
                    "data": "examinationsubjectcode",
                    "render": function (data, type, row, meta) {
                    	let div = '<div style="text-align:center"><button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button>';
                		div += '<button style="padding:.1em; margin-right: .5em" value="Delete" ng-click="delete(' + data + ')" class="button-primary">Delete</button></div>';
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

    /* READ DATA */
    $scope.listExamSubjects	 = () => {
        commonInitFactory.listExamSubjects((response)=>{
    		$scope.examSubjects = response;
    		$scope.setDataTable($scope.examSubjects);
    	});
    };
    $scope.listExamSubjects();
        
}]);

