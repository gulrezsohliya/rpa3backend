$(document).ready(function () {
  var scope = angular.element($("#mapCtrl")).scope();
  scope.$apply(function () {
    scope.listOffices();
    scope.listExamCenters();
  });
});
app.controller("mapCtrl", [
  "$scope",
  "$sce","$timeout",'commonInitFactory',
  function ($scope, $sce, $timeout, commonInitFactory) {
    $scope.office = new Office();
    $scope.offices = [];
    $scope.centers = [];
    $scope.trustHTML = function (post) {
      return $sce.trustAsHtml(post);
    };
    
    $scope.reset=()=>{
    	$scope.officecode=0;
    	$scope.office = new Office();
    	$scope.office.centers=[];
    	$scope.mappedCenters();
    };
    
    $scope.save = function () {
      var mapCenters = [];
      jQuery.each($scope.centers, function (i, v) {
        if (v.checked) {
          mapCenters.push({
            slno: 0,
            officecode: $scope.officecode,
            centercode: v.centercode,
          });
        }
      });
      if (mapCenters.length === 0) {
        MsgBox("Select atleast one Center");
        return;
      }
      jQuery.ajax({
        type: "POST",
        url: "./saveOfficeCenters",
        data: angular.toJson(mapCenters),
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          MsgBox(response.response=="CREATED"?"Mapped":"Failed");
	      $scope.listOffices();
	      $scope.reset();
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      });
    };
    
    $scope.mappedCenters = function (index) {
      
      jQuery.each($scope.centers, function (i, v) {
        v.checked = false;
      });
      if($scope.office!==undefined){
    	  $scope.office=$scope.offices.filter((o)=>{
    		  return o.officecode==$scope.officecode;
    	  })[0];
    	  $scope.checks();    	  
      }
    };

    $scope.checks = function () {
      var response;
      jQuery.each($scope.centers, function (i0, v0) {
        response = false;
        jQuery.each($scope.office.centers, function (i, v) {
          if (v0.centercode === v.centercode) {
            response = true;
            return false;
          }
        });
        v0.checked = response;
      });
    };
    
    $scope.listOffices = function () {
      jQuery.ajax({
        type: "GET",
        url: "./listOfficesAndMappedCenters",
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
        	$timeout(()=>{
        		$scope.offices = response        		
        	},0);        	
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      }).then(()=>{
    	  if(response.length===1){
  			$scope.office=response[0];
		  }
    	  $scope.mappedCenters();
      });
    };
    
    $scope.listExamCenters = () => {
        commonInitFactory.listExamCenters((response)=>{
    		$scope.centers = response;
    	});
    };
    
  },
]);
angular.element(document).ready(function () {
  dTable = $("#displayRecordsTable");
  dTable.DataTable({
    searching: false,
    bPaginate: false,
    bLengthChange: false,
    bFilter: true,
    bInfo: false,
    bAutoWidth: false,
    oLanguage: {
      sZeroRecords: "",
      sEmptyTable: "",
    },
  });
});
