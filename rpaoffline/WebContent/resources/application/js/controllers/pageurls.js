$(document).ready(function () {
  var scope = angular.element($("#pageurlCtrl")).scope();
  scope.$apply(function () {
    scope.listURLs();
  });
});
var pageurlapp = angular.module("pageurlCtrl", []);
pageurlapp.controller("pageurlCtrl", [
  "$scope",
  "$sce",
  function ($scope, $sce) {
    // $scope.header = ${headers};
    $scope.submenu = [];
    $scope.subsubmenu = [];
    $scope.URLs = [];
    $scope.url = new Pageurls();
    $scope.trustHTML = (post) => {
      return $sce.trustAsHtml(post);
    };
    $scope.reset = function () {
      $scope.url = new Pageurls();
    };
    $scope.getSubmenu = function () {
      jQuery.ajax({
        type: "POST",
        url: "./getSubmenu.htm",
        data: "val=" + $scope.url.parent,
        //                dataType: "json",
        //                                contentType: "application/json; charset=utf-8",
        success: function (response) {
          var scope = angular.element($("#pageurlCtrl")).scope();
          scope.$apply(function () {
            scope.submenu = JSON.parse(response);
          });
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      });
    };
    $scope.save = function () {
      jQuery.ajax({
        type: "POST",
        url: "./saveMenu.htm",
        data: angular.toJson($scope.url),
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          alert(response);
          $scope.reset();
          $scope.listURLs();
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      });
    };
    $scope.edit = function (index) {
      $scope.url = $scope.URLs[index];
      jQuery("html, body").animate(
        {
          scrollTop: 0,
        },
        2000
      );
    };
    $scope.listURLs = function () {
      jQuery.ajax({
        type: "POST",
        url: "./listUrls",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          var scope = angular.element($("#pageurlCtrl")).scope();
          scope.$apply(function () {
            scope.URLs = response;
          });
        },
        error: function (xhr) {
          alert(xhr.status + " = " + xhr);
          alert(
            "Sorry, there was an error while trying to process the request."
          );
        },
      });
    };
  },
]);
angular.module("CombineModules", ["Menu", "pageurlCtrl"]);
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
