<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        var header=${headers};
        </script>
    </body>
</html>
