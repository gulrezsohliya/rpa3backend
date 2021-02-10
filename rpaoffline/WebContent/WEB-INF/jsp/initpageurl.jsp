<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="resources/vendor/jQuery/jquery.min.js"></script>  
        <link rel="stylesheet" href="resources/vendor/jQuery/datatable/jquery.dataTables.min.css" />  
        <script src="resources/vendor/jQuery/datatable/jquery.dataTables.min.js"></script>   
        <script src="resources/vendor/angular1.3.js"></script> 
        <script src="resources/vendor/date.js"></script> 
        <style>
            input[type=text],select{
                width: calc(90%/3);
            }
        </style>
    </head>
    <body ng-app="CombineModules">
        <%@include file="./common/header.jsp" %> 
        <div class='containerBody' id="pageurlCtrl" ng-controller="pageurlCtrl">
            <h2>Page URL Initialization</h2>
            <div  style='width:80%;margin: 50px auto 50px auto;'>
                <form>            

                    <table class="table-condensed" width="70%" style="margin:0 auto">

                        <tr><td class="title"> Menu Header<span  class="mandatory ">*</span></td>
                            <td width="500">
                                <select name="parent" ng-model="url.parent" ng-change="getSubmenu();">
                                    <option value=""> --Select--</option>
                                    <option ng-selected="head.key == url.parent" ng-repeat='head in header track by $index' ng-value="head.key"> {{head.key}} </option>
                                </select>             
                                <input type="text" name="parentNew" ng-model="url.parent" placeholder="New Menu Header"/>    
                                <input type="text" name="parenticon" ng-model="url.parenticon" class="selectIcon"/>
                            </td>
                        </tr>
                        <tr><td class="title"> Sub Menu </td>
                            <td>
                                <select name="submenu" ng-model="url.submenu" ><!--ng-change="getSubsubmenu()">-->
                                    <option value=""> --Select--</option>
                                    <option ng-selected="head.key == url.submenu" ng-repeat='head in submenu track by $index' ng-value="head.key"> {{head.key}} </option>
                                </select> 
                                <input type="text" ng-model="url.submenu" name="submenuNew" placeholder="New Submenu"/>
                                <input type="text" name="submenuicon" ng-model="url.submenuicon" class="selectIcon"/>
                            </td>
                        </tr>
                        <tr><td class="title"> Sub sub Menu </td>
                            <td>
                                <!--                            <select name="subsubmenu" ng-model="url.subsubmenu" >
                                                                <option value=""> --Select--</option>
                                                                <option ng-repeat='head in subsubmenu track by $index' ng-value="head.key"> {{head.key}} </option>
                                                            </select> -->
                                <input type="text" ng-model="url.subsubmenu" name="subsubmenuNew" placeholder="New Submenu"/>
                                <input type="text" name="subsubmenuicon" ng-model="url.subsubmenuicon" class="selectIcon"/>
                            </td>
                        </tr>

                        <tr><td class="title"> Page URL<span  class="mandatory ">*</span></td>
                            <td >
                                <input type="text" name="pageurl" ng-model="url.pageurl" />
                            </td>
                            <td>&nbsp;<span id="errmsgpageurl" class="mandatory "></span></td>
                        </tr>

                        <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td colspan="3" align="center"> (<span  class="mandatory ">*</span>) specify mandatory fields
                                <input  name="add" type="button" value="Save" id="add" class="btn btn-primary" ng-click="save()"/>

                                <input  name="reset" type="button" value="RESET" id="reset" class="btn btn-default" ng-click="reset()"/>
                            </td>

                        </tr>
                    </table>
                </form>
            </div>
            <div style='width:80%;max-height: 400px;overflow-y:scroll ;margin: 100px auto 50px auto;'>
                <table id="displayRecordsTable" width="100%" cellspacing="0" border="1">
                    <thead>
                        <tr>
                            <th>Url Code</th>
                            <th>Menu Header</th>
                            <th>Sub Menu</th>
                            <th>Sub sub Menu</th>
                            <th>Page URL</th>                            
                            <th>Action</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat='url in URLs track by $index'>
                            <td style='width:10%'>{{url.urlcode}}</td>
                            <td style='width:10%'>{{url.parent}}</td>
                            <td style='width:10%'>{{url.submenu}}</td>
                            <td style='width:10%'>{{url.subsubmenu}}</td>
                            <td style='width:10%'>{{url.pageurl}}</td>
                            <td style='width:10%'><button style="padding:5px" value="Edit" ng-click="edit($index)">Edit</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    <script src="resources/application/js/controllers/pageurls.js"></script>
        <script>
//             $(document).ready(function() {
//                 var scope = angular.element($("#pageurlCtrl")).scope();
//                 scope.$apply(function() {
//                     scope.listURLs();
//                 });
//             });
//             var pageurlapp = angular.module('pageurlCtrl', []);
//             pageurlapp.controller('pageurlCtrl', ['$scope', '$sce', function($scope, $sce) {
//                     $scope.header = ${headers};
//                     $scope.submenu = [];
//                     $scope.subsubmenu = [];
//                     $scope.URLs = [];
//                     $scope.url = new Pageurls();
//                     $scope.trustHTML = function(post) {
//                         return $sce.trustAsHtml(post);
//                     };
//                     $scope.reset = function() {
//                         $scope.url = new Pageurls();
//                     }
//                     $scope.getSubmenu = function() {
//                         jQuery.ajax({
//                             type: 'POST',
//                             url: "./getSubmenu.htm",
//                             data: "val=" + $scope.url.parent,
//                             //                dataType: "json",
// //                                                contentType: "application/json; charset=utf-8",
//                             success: function(response) {
//                                 var scope = angular.element($("#pageurlCtrl")).scope();
//                                 scope.$apply(function() {
//                                     scope.submenu = JSON.parse(response);
//                                 });
//                             },
//                             error: function(xhr) {
//                                 alert(xhr.status + " = " + xhr)
//                                 alert("Sorry, there was an error while trying to process the request.");
//                             }
//                         });
//                     };
//                     $scope.save = function() {
//                         jQuery.ajax({
//                             type: 'POST',
//                             url: "./saveMenu.htm",
//                             data: angular.toJson($scope.url),
//                             //                dataType: "json",
//                             contentType: "application/json; charset=utf-8",
//                             success: function(response) {
//                                 alert(response);
//                                 $scope.reset();
//                                 $scope.listURLs()
//                             },
//                             error: function(xhr) {
//                                 alert(xhr.status + " = " + xhr)
//                                 alert("Sorry, there was an error while trying to process the request.");
//                             }
//                         });
//                     }
//                     $scope.edit = function(index) {
//                         $scope.url = $scope.URLs[index];
//                         jQuery('html, body').animate({
//                             scrollTop: 0
//                         }, 2000);
//                     }
//                     $scope.listURLs = function() {
//                         jQuery.ajax({
//                             type: 'POST',
//                             url: "./listUrls",
// //                                                                    dataType: "json",
//                             contentType: "application/json; charset=utf-8",
//                             success: function(response) {
//                                 var scope = angular.element($("#pageurlCtrl")).scope();
//                                 scope.$apply(function() {
//                                     scope.URLs = response;
//                                 });
// //                                                        $scope.URLs = response;
//                             },
//                             error: function(xhr) {
//                                 alert(xhr.status + " = " + xhr)
//                                 alert("Sorry, there was an error while trying to process the request.");
//                             }
//                         });
//                     }
//                 }]);
//             angular.module("CombineModules", ["Menu", "pageurlCtrl"]);
//             angular.element(document).ready(function() {
//                 dTable = $('#displayRecordsTable');
//                 dTable.DataTable({
//                     searching: false,
//                     "bPaginate": false,
//                     "bLengthChange": false,
//                     "bFilter": true,
//                     "bInfo": false,
//                     "bAutoWidth": false,
//                     "oLanguage": {"sZeroRecords": "", "sEmptyTable": ""}
//                 });
//             });
        </script>
    </body>
</html>
