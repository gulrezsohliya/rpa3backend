app.controller('optionalSubjectCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/* Common Ajax Params */
	var successMsg = "Success: Optional Subject created/updated";
	var errorMsg = "Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = SAVE;
    $scope.optionalSubject = new OptionalSubjects();
    $scope.optionalSubjects = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (subjectcode) {
    	$scope.actionButton = EDIT;
    	$scope.optionalSubject = $scope.optionalSubjects.filter(obj=>{
    		return obj.subjectcode==subjectcode;
    	})[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.optionalSubject = new OptionalSubjects();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.optionalSubjectForm.$invalid)
            return false;
        
        $scope.method = "POST";
    	$scope.urlEndpoint = "./createOptionalSubject";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.optionalSubject, () => {
    				$scope.reset();
    				$scope.listOptionalSubjects(); 
    				MsgBox(successMsg)
				},
				(res) =>{
    				if(res.response===ALREADY_REPORTED){
    					MsgBox("Subject already exists");
    				}else{
    					MsgBox(errorMsg)
    				}
    			});
    	
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.optionalSubjectForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateOptionalSubject";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.optionalSubject, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listOptionalSubjects(); 
				MsgBox(successMsg);				
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    };
    
    $scope.delete = (code) => {
    	
    	$scope.method = "DELETE";
    	$scope.urlEndpoint = "./deleteOptionalSubject/"+code;
    	ConfirmBox("Are You Sure to Delete this entry? ",(response)=>{
	    	if(response){
	    		commonInitService.http($scope.method, $scope.urlEndpoint, code, (res) => {
		    		if(res===true){
		    			$scope.reset();
		    			$scope.listOptionalSubjects(); 
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
                    "title": "Optional Subject Code",
                    "data": "subjectcode"
                }, {
                    "title": "Optional Subject Name",
                    "data": "subjectname"
                }, 
                {
                    "title": "Action",
                    "sortable": false,
                    "data": "subjectcode",
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
    $scope.listOptionalSubjects	 = () => {
        commonInitFactory.listOptionalSubjects((response)=>{
    		$scope.optionalSubjects = response;
    		$scope.setDataTable($scope.optionalSubjects);
    	});
    };
    $scope.listOptionalSubjects();
        
}]);

