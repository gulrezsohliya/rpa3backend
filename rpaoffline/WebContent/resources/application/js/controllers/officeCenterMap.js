$(document).ready(function () {
  var scope = angular.element($("#mapCtrl")).scope();
  scope.$apply(function () {
//    scope.listUsers();
    scope.listExamCenters();
  });
});
app.controller("mapCtrl", [
  "$scope",
  "$sce",'commonInitFactory',
  function ($scope, $sce,commonInitFactory) {
    $scope.office = new Office();
    $scope.offices = [];
    $scope.centers = [];
    $scope.trustHTML = function (post) {
      return $sce.trustAsHtml(post);
    };
    
    $scope.save = function () {
      var mapuserpagespages = [];
      jQuery.each($scope.URLs, function (i, v) {
        if (v.checked) {
          mapuserpagespages.push({
            userpagecode: 0,
            usercode: $scope.user.usercode,
            url: v,
          });
        }
      });
      if (mapuserpagespages.length === 0) {
        MsgBox("Select atleast one URL");
        return;
      }
      jQuery.ajax({
        type: "POST",
        url: "./saveUserpages",
        data: angular.toJson(mapuserpagespages),
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          MsgBox(response);
          $scope.reset();
          $scope.listUsers();
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      });
    };
    
    $scope.mappedPages = function (index) {
      jQuery.each($scope.URLs, function (i, v) {
        v.checked = false;
      });
      $scope.user = $scope.users[index];
      $scope.checks();
      jQuery("html, body").animate(
        {
          scrollTop: 0,
        },
        2000
      );
    };
    
    $scope.listOffices = function () {
      jQuery.ajax({
        type: "GET",
        url: "./listOfficesAndMappedCenters",
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
        	scope.offices = response;
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      });
    };
    
    $scope.listExamCenters = () => {
        commonInitFactory.listExamCenters((response)=>{
    		$scope.centers = response;
    	});
    };
    
    $scope.checks = function () {
      var response;
      jQuery.each($scope.centers, function (i0, v0) {
        response = false;
        jQuery.each($scope.user.mappedpages, function (i, v) {
          if (v0.urlcode === v.urlcode) {
            response = true;
            return false;
          }
        });
        v0.checked = response;
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
