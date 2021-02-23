$(document).ready(function () {
  var scope = angular.element($("#accesscontrolCtrl")).scope();
  scope.$apply(function () {
    scope.listUsers();
    scope.listURLs();
  });
});
app.controller("accesscontrolCtrl", [
  "$scope",
  "$sce",
  function ($scope, $sce) {
    $scope.user = new Userlogins();
    $scope.users = [];
    $scope.URLs = [];
    $scope.trustHTML = function (post) {
      return $sce.trustAsHtml(post);
    };
    $scope.reset = function () {
      $scope.user = new Userlogins();
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
    $scope.listUsers = function () {
      jQuery.ajax({
        type: "GET",
        url: "./listUserAndMappedPages",
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          var scope = angular.element($("#accesscontrolCtrl")).scope();
          scope.$apply(function () {
            scope.users = response;
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
    $scope.listURLs = function () {
      jQuery.ajax({
        type: "GET",
        url: "./listUrls",
        // dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
          var scope = angular.element($("#accesscontrolCtrl")).scope();
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
    $scope.checks = function () {
      var response;
      jQuery.each($scope.URLs, function (i0, v0) {
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
