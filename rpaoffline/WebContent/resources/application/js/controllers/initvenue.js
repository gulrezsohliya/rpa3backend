app.controller('venueCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
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
    $scope.venue = new Venue();
    $scope.centers = [];
    $scope.venues = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (venuecode) {
    	$scope.actionButton = EDIT;
    	$scope.venue = $scope.venues.filter(obj=>{
    		return obj.venuecode==venuecode;
    	})[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.venue = new Venue();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.venueForm.$invalid)
            return false;
        
        $scope.method = "POST";
    	$scope.urlEndpoint = "./createvenue";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.venue, () => {
    				$scope.reset();
    				$scope.listVenues(); 
    				MsgBox(successMsg)
				},
				(res) =>{
    				if(res.response===ALREADY_REPORTED){
    					MsgBox("Venue already exists");
    				}else{
    					MsgBox(errorMsg)
    				}
    			});
    	
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.venueForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updatevenue";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.venue, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listVenues(); 
				MsgBox(successMsg);				
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    };
    
    $scope.delete = (venuecode) => {
    	
    	$scope.method = "DELETE";
    	$scope.urlEndpoint = "./deletevenue/"+venuecode;
    	ConfirmBox("Are You Sure to Delete this entry? ",(response)=>{
	    	if(response){
	    		commonInitService.http($scope.method, $scope.urlEndpoint, venuecode, (res) => {
		    		if(res===true){
		    			$scope.reset();
		    			$scope.listVenues(); 
		    		}else{
		    			MsgBox("Unable to delete.");								
		    		}
	    		},()=>{});
	    	}
    	});
    };
    
    $scope.getExamcenters=()=>{
    	commonInitFactory.listExamCentersforOffice($scope.venue.officecode,(response)=>{
    		$scope.centers = response;
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
                    "title": "Exam Center Code",
                    "data": "centercode"
                }, {
                    "title": "Exam Center",
                    "data": "centername"
                },{
                    "title": "Venue code",
                    "data": "venuecode"
                }, {
                    "title": "Venue Name",
                    "data": "venuename",
                }, 
                {
                	"width":"100px",
                    "title": "Action",
                    "sortable": false,
                    "data": "venuecode",
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
   
    $scope.listVenues = () => {
        commonInitFactory.listVenues((response)=>{
    		$scope.venues = response;
    		$scope.setDataTable($scope.venues);
    	});
    };
    commonInitFactory.listOffices((response)=>{
    	$scope.offices = response;
    });

    $scope.listVenues();
        
}]);

