
$(document).ready(function () {
	jQuery('.message').fadeOut(1000);
});

app.controller('advCtrl', ['$scope', '$sce', '$compile','$timeout','commonInitFactory', 'commonInitService', 
	function ($scope, $sce, $compile,$timeout,commonInitFactory, commonInitService) {
	/* Common Ajax Params */
	var successMsg = "Success: The Advertisement has been created/updated successfully";
	var errorMsg = "Error: Unable to perform action";
	$scope.errorCallback = "";
	$scope.method = "POST";
	$scope.successCallback = "";
	$scope.urlEndpoint = "";
	
	$scope.agedate_sameas_issuedate=true;
	/*------------------------*/
	
	$scope.step = 1;
	$scope.actionButton = SAVE;
    $scope.adv = new Advertisement();
    $scope.advs = [];
    $scope.setStep=function(step){
    	$scope.step=step;
    };
    
    $scope.addAdvAge=()=>{
    	$scope.adv.advertisementAge.push(new AdvertisementAge());
    };
    
    $scope.addAdvFee=()=>{
    	$scope.adv.advertisementFee.push(new AdvertisementFee());
    };
    
    $scope.reset = function () {
    	$scope.step=1;
    	$scope.adv = new Advertisement();
    	$scope.agedate_sameas_issuedate=true;
    	$scope.actionButton = SAVE;
    };

    $scope.edit = function (adcode) {
    	$scope.reset();
    	$scope.actionButton = EDIT;
    	$scope.adv =$scope.advs.filter(obj=>{
    		return obj.adcode==adcode;
    	})[0];
    	$scope.adv.issuedate=new Date($scope.adv.issuedate);
    	$scope.adv.lastdate=new Date($scope.adv.lastdate);
    	$scope.adv.agedate=new Date($scope.adv.agedate);
    	$scope.checkOptionals();
//        jQuery('html, body').animate({
//            scrollTop: 0
//        }, 2000);
    };

    $scope.checkOptionals=()=>{
    	$scope.OptionalSubjects1.forEach((x)=>{
			x.checked=false;
			if($scope.adv.noofoptionals>=1){
				$scope.adv.advertisementOptionals[0].optionalsubject.forEach(a=>{
					if(a.optionalsubjectcode==x.optionalsubjectcode){
						x.checked=true;
					}
				});
			}else{
				if(x.optionalsubjectcode==0){
					x.checked=true;
				}
			}
		});
		$scope.OptionalSubjects2.forEach((x)=>{
			x.checked=false;
			if($scope.adv.noofoptionals>=2){
				$scope.adv.advertisementOptionals[1].optionalsubject.forEach(a=>{
					if(a.optionalsubjectcode==x.optionalsubjectcode){
						x.checked=true;
					}
				});
			}else{
				if(x.optionalsubjectcode==0){
					x.checked=true;
				}
			}
		});
		$scope.OptionalSubjects3.forEach((x)=>{
			x.checked=false;
			if($scope.adv.noofoptionals>=3){
				$scope.adv.advertisementOptionals[2].optionalsubject.forEach(a=>{
					if(a.optionalsubjectcode==x.optionalsubjectcode){
						x.checked=true;
					}
				});
			}else{
				if(x.optionalsubjectcode==0){
					x.checked=true;
				}
			}
		});
    };
    $scope.save = function () {
        if($scope.advForm.$invalid)
            return false;
        
        $scope.method = "POST";
        $scope.urlEndpoint = "./createAdvertisement";
    	
        commonInitService.save($scope.method, $scope.urlEndpoint, $scope.adv, (res) => {
        		$scope.adv.adcode=res.adcode;
        		$scope.step=2;
        		$scope.actionButton =EDIT;
        		$scope.listAdvs();
				$scope.message='Advertisement saved successfully. Please add the additional posts details. ';
				jQuery('.message').fadeIn(1000);
				$timeout(function(){
					jQuery('.message').fadeOut(1000);
				},5000);
				}, 
	    	() =>{
	    		MsgBox(errorMsg)
	    		}
	    	);
    };
    
    $scope.update = (checkform=true) => {
	    if(checkform && $scope.advForm.$invalid)
             return false;
	    $scope.setOptionalChecked();
	    $scope.method = "PUT";
    	$scope.urlEndpoint = "./updateAdvertisement";
    	commonInitService.save($scope.method, $scope.urlEndpoint, $scope.adv, (res) => {	
			if(res===true){
				if($scope.step==3){
					$scope.reset(); 
					MsgBox(successMsg);				
				}else{
					$scope.step++;
					$scope.message='Advertisement saved successfully. Please add the additional posts details. ';
					jQuery('.message').fadeIn(1000);
					$timeout(function(){
						jQuery('.message').fadeOut(1000);
					},5000);
				}
				$scope.listAdvs();
			}else{
				MsgBox(errorMsg);								
			}
		},
		() =>{
			MsgBox(errorMsg)
		});
    }
    $scope.setOptionalChecked= async ()=>{
    	$scope.adv.advertisementOptionals[0].optionalsubject = JSON.parse(JSON.stringify($scope.OptionalSubjects1)).filter(x=>{
    		return x.checked == true;
    	});
    	$scope.adv.advertisementOptionals[1].optionalsubject = JSON.parse(JSON.stringify($scope.OptionalSubjects2)).filter(x=>{
    		return x.checked == true;
    	});
    	$scope.adv.advertisementOptionals[2].optionalsubject = JSON.parse(JSON.stringify($scope.OptionalSubjects3)).filter(x=>{
    		return x.checked == true;
    	});    	
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
                    "title": "Advertisement No.",
                    "data": "advertisementno"
                },{
                    "title": "Description",
                    "data": "description"
                },{
                    "title": "Post",
                    "data": "nameofpost"
                },{
                    "title": "Post Short Name",
                    "data": "postshortname"
                },{
                    "title": "Issue Date",
                    "data": (row, type, set)=>{
                    	return "<input style='border:none;background:inherit;' type='date' value="+new Date(row.issuedate)+"'/>"
                    }
                },{
                	"title": "Last Date",
                	"data": (row, type, set)=>{
                		return "<input style='border:none;background:inherit;' type='date' value="+new Date(row.lastdate)+"'/>"
                	}
                },{
                    "title": "Action",
                    "sortable": false,
                    "data": "adcode",
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

    /* READ DATA */
    $scope.listCategories = () => {
    	commonInitFactory.listCategories((response)=>{
    		$scope.Categories = response; 
    	});
    };
    $scope.listAdvs = () => {
        commonInitFactory.listAdvertisements((response)=>{
    		$scope.advs = response;
    		$scope.setDataTable($scope.advs);
    	});
    };
    $scope.listOptionalSubjects = async () => {
    	commonInitFactory.listOptionalSubjects((response)=>{
    		$scope.OptionalSubjects1 = JSON.parse(JSON.stringify(response));
    		$scope.OptionalSubjects2 = JSON.parse(JSON.stringify(response));
    		$scope.OptionalSubjects3 = JSON.parse(JSON.stringify(response));
    	});
    };
    // ---------------------------------------------
    $scope.listCategories();
    $scope.listOptionalSubjects();    
    $scope.listAdvs();
}]);

