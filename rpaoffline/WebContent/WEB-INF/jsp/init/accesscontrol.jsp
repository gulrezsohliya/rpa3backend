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
    <body ng-app="CommonApp">
<!--     <body ng-app="CombineModules"> -->
        <%@include file="../common/header.jsp" %>
        <div class='containerBody' id="accesscontrolCtrl" ng-controller="accesscontrolCtrl">
            <h2>Access Control</h2>
            <div id="maptable" ></div>
            <div style='width:80%;margin: 50px auto 0;' ng-if='user.usercode>0'>
                <form>
                    <table class="" style="width:100%;margin: 0px auto"> 
                        <tr class="form-group has-feedback">
                            <td class="title" style="width: 15%">Full Name :</td>
                            <td>
                                <span >{{user.fullname}}</span>
                            </td>                                     
                            <td rowspan="15" style="width:65%;border: 1px solid blue;">
                                <div style="width:100%;max-height:230px;overflow-y: auto ">
                                    <table border="1" cellspacing="0"width="100%">
                                        <tr>
                                            <th></th>
                                            <th>URL</th>    
                                            <th>Sub Sub Menu</th>
                                            <th>Sub Menu</td>
                                            <th>Menu Header</th>
                                        </tr>
                                        <tr ng-repeat='item in URLs track by $index' style="width:100%;">
                                            <td><input style="margin:8px" type='checkbox' ng-model="item.checked"/></td>
                                            <td >&nbsp;{{item.pageurl}}</td>
                                            <td>&nbsp;{{item.subsubmenu}}</td>
                                            <td>&nbsp;{{item.submenu}}</td>
                                            <td>&nbsp;{{item.parent}}</td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr> <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td class="title">User Name :</td>
                            <td >
                                <span >{{user.fullname}}</span>
                            </td>
                        </tr><tr><td>&nbsp;</td></tr>
                        <tr >
                            <td class="title">User Access :</td>
                            <td>
                                <span ng-if='user.enabled=="Y"'>Enabled</span>
                                <span ng-if='user.enabled=="F"'>Disabled</span>
                            </td>
                        </tr><tr><td>&nbsp;</td></tr>
                        <tr >
                            <td colspan="2" align="center">
                                <input type="button" value="Save" ng-click="save()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="reset" value="RESET" ng-click="reset()"/>
                            </td>
                        </tr><tr><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>
                    </table>
                </form>
            </div> 
            <div style='width:80%;max-height: 600px;overflow-y:scroll ;margin: 50px auto 50px auto;'>
                <table id="displayRecordsTable" width="100%" cellspacing="0" border="1">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>User Name</th>
                            <th>Designation</th>     
                            <th>Action</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat='u in users track by $index '>
                            <td style='width:10%'>{{u.fullname}}</td>
                            <td style='width:10%'>{{u.username}}</td>
                            <td style='width:10%'>{{u.designation}}</td>
                            <td style='width:10%'>
                                <button style="padding:5px" ng-click="mappedPages($index)">Map Pages</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="resources/application/js/controllers/accesscontrol.js"></script>
    </body>
</html>
