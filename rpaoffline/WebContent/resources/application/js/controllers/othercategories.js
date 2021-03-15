app.controller('otherCatCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/*Common Ajax Params*/
	var successMsg = "Success: Venue created/updated";
	var errorMsg = "Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = SAVE;
    $scope.otherCat = new OtherCategories();
    $scope.otherCats = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (othercategorycode) {
    	$scope.actionButton = EDIT;
    	$scope.otherCat = $scope.otherCats.filter(obj=>{
    		return obj.othercategorycode==othercategorycode;
    	})[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.otherCat = new OtherCategories();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.otherCatForm.$invalid)
            return false;
        
        $scope.method = "POST";
    	$scope.urlEndpoint = "./createOtherCategory";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.otherCat, () => {
    				$scope.reset();
    				$scope.listOtherCategories(); 
    				MsgBox(successMsg)
				},
				(res) =>{
    				if(res.response===ALREADY_REPORTED){
    					MsgBox("Category already exists");
    				}else{
    					MsgBox(errorMsg)
    				}
    			});
    	
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.otherCatForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateOtherCategory";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.otherCat, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listOtherCategories(); 
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
    	$scope.urlEndpoint = "./deleteOtherCategory/"+othercategorycode;
    	ConfirmBox("Are You Sure to Delete this entry? ",(response)=>{
	    	if(response){
	    		commonInitService.http($scope.method, $scope.urlEndpoint, othercategorycode, (res) => {
		    		if(res===true){
		    			$scope.reset();
		    			$scope.listOtherCategories(); 
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
                    "title": "Other Category Code",
                    "data": "othercategorycode"
                }, {
                    "title": "Description",
                    "data": "othercategorydescription"
                }, 
                {
                    "title": "Action",
                    "sortable": false,
                    "data": "othercategorycode",
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

    /*READ DATA*/
    $scope.listOtherCategories = () => {
        commonInitFactory.listOtherCategories((response)=>{
    		$scope.otherCats = response;
    		$scope.setDataTable($scope.otherCats);
    	});
    };
    $scope.listOtherCategories();
        
}]);

