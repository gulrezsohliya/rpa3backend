
var app = angular.module('Records', []);
app.controller('displayRecordsCtrl', ['$scope', '$sce', function($scope, $sce) {
        //    
        $scope.Records = [];
        $scope.filters = new RBITransaction();
        $scope.errorCssValues = {'show': "display:block;", 'hide': "display:none;"};
        $scope.trustHTML = function(post) {
            return $sce.trustAsHtml(post);
        };
        $scope.getRBITransactionList = function() {
            console.log($scope.filters);
            $scope.filters.advicedate = Date.parse($scope.filters.advicedate);
            $scope.filters.clearancedate = Date.parse($scope.filters.clearancedate);
            $scope.filters.memodate = Date.parse($scope.filters.memodate);
            jQuery.ajax({
                type: 'POST',
                url: "./viewrecords",
                data: angular.toJson($scope.filters),
//                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function(response) {
                    console.log(response);
                    var scope = angular.element($("#displayRecordsCtrl")).scope();
                    scope.$apply(function() {
                        scope.Records = response;
                    });
//                    $scope.Records = response;
                },
                error: function(xhr) {
                    alert(xhr.status + " = " + xhr)
                    alert("Sorry, there was an error while trying to process the request.");
                }
            });
            $scope.filters.advicedate = (((new Date($scope.filters.advicedate)).toString("dd-MMM-yyyy"))==='01-Jan-1970')?null:((new Date($scope.filters.advicedate)).toString("dd-MMM-yyyy"));
            $scope.filters.clearancedate = (((new Date($scope.filters.clearancedate)).toString("dd-MMM-yyyy"))==='01-Jan-1970')?null:((new Date($scope.filters.clearancedate)).toString("dd-MMM-yyyy"));
            $scope.filters.memodate = (((new Date($scope.filters.memodate)).toString("dd-MMM-yyyy"))==='01-Jan-1970')?null:((new Date($scope.filters.memodate)).toString("dd-MMM-yyyy"));
        };
    }
]);
angular.module("CombineModules", ["Menu", "Records"]);
angular.element(document).ready(function() {
    dTable = $('#displayRecordsTable');
    dTable.DataTable({
        searching: false,
        "bPaginate": false,
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false,
        "oLanguage": {"sZeroRecords": "", "sEmptyTable": ""}
    });
});
