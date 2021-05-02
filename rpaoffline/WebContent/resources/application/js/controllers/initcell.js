$(document).ready(function () {
});

app.controller('cellCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/*Common Ajax Params*/
	var successMsg = "Success: Cell created/updated";
	var errorMsg = "Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	/*------------------------*/
	
	$scope.actionButton = SAVE;
    $scope.cell = new Cell();
    $scope.offices = [];
    $scope.cells = [];
    
    $scope.trustHTML = function (post) {
        return $sce.trustAsHtml(post);
    };
    
    $scope.edit = function (cellcode) {
    	$scope.actionButton = EDIT;
    	$scope.cell = $scope.cells.filter(obj=>{
    		return obj.cellcode==cellcode;
    	})[0];
        jQuery('html, body').animate({
            scrollTop: 0
        }, 2000);
    };

    $scope.reset = function () {
    	$scope.cell = new Cell();
    	$scope.actionButton = SAVE;
    };

    $scope.save = function () {
        if($scope.cellForm.$invalid)
            return false;
        
        $scope.method = "POST";
    	$scope.urlEndpoint = "./createcell";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.cell, () => {
    				$scope.reset();
    				$scope.listCells(); 
    				MsgBox(successMsg)
				},
				(res) =>{
    				if(res.response===ALREADY_REPORTED){
    					MsgBox("Cell already exists");
    				}else{
    					MsgBox(errorMsg)
    				}
    			});
    	
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.cellForm.$invalid)
             return false;
	    
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updatecell";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.cell, (res) => {	
			if(res===true){
				$scope.reset();
				$scope.listCells(); 
				MsgBox(successMsg);				
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    };
    
    $scope.delete = (cellcode) => {
    	
    	$scope.method = "DELETE";
    	$scope.urlEndpoint = "./deletecell/"+cellcode;
    	ConfirmBox("Are You Sure to Delete this entry? ",(response)=>{
	    	if(response){
	    		commonInitService.http($scope.method, $scope.urlEndpoint, cellcode, (res) => {
		    		if(res===true){
		    			$scope.reset();
		    			$scope.listCells(); 
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
                    "title": "Office Code",
                    "data": "officecode",
                    render: function (data, type, row, meta){
                    	return meta.row+1;
                    }
                }, {
                    "title": "Office Short Name",
                    "data": "officeshortname"
                },{
                    "title": "Cell code",
                    "data": "cellcode"
                }, {
                    "title": "Cell Description",
                    "data": "celldescription",
                }, 
                {
                    "title": "Action",
                    "sortable": false,
                    "data": "cellcode",
                    "render": function (data, type, row, meta) {
                    	let div = '<div style="text-align:center"><button style="padding:.1em; margin-right: .5em" value="Edit" ng-click="edit(' + data + ')" class="button-primary">Edit</button>';
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
    commonInitFactory.listOffices((response)=>{
    	$scope.offices = response;
    });
    $scope.listCells = () => {
        commonInitFactory.listCells((response)=>{
    		$scope.cells = response;
    		$scope.setDataTable($scope.cells);
    	});
    };
    $scope.listCells();
        
}]);

