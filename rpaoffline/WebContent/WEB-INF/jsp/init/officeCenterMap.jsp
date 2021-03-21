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
        <div class='containerBody' id="mapCtrl" ng-controller="mapCtrl">
            <h2>Office Center</h2>
            <div id="maptable" ></div>
            <div style='width:80%;margin: 50px auto 0;'>
                <form>
                    <table class="" style="width:100%;margin: 0px auto"> 
                        <tr class="form-group has-feedback">
                            <td class="title" style="width: 15%">Office :</td>
                            <td>
                                <select type="text" class="form-control" id="office" name="officecode" ng-model="officecode" ng-change="mappedCenters()" required>
									<option value="">--Select--</option>
									<option ng-selected="offc.officecode == office.officecode"
										ng-repeat='offc in offices track by $index' ng-value="offc.officecode">
										{{offc.officeshortname}}</option>
								</select>
                            </td>                                     
                            <td rowspan="15" style="width:65%;border: 1px solid blue;">
                                <div style="width:100%;max-height:300px;overflow-y: auto ">
                                    <table border="1" cellspacing="0"width="100%">
                                        <tr>
                                            <th></th>
                                            <th>Examination Center Code</th>    
                                            <th>Examination Center Name</th>
                                        </tr>
                                        <tr ng-repeat='item in centers track by $index' style="width:100%;">
                                            <td><input style="margin:8px" type='checkbox' ng-model="item.checked"/></td>
                                            <td >&nbsp;{{item.centercode}}</td>
                                            <td>&nbsp;{{item.centername}}</td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr> <tr><td>&nbsp;</td></tr>
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
        </div>
        <script src="resources/application/js/controllers/officeCenterMap.js"></script>
    </body>
</html>
