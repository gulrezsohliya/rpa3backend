
var app = angular.module('Records', []);
app.controller('displayRecordsCtrl', ['$scope', '$sce', function($scope, $sce) {
        //    
        $scope.trustHTML = function(post) {
            return $sce.trustAsHtml(post);
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
