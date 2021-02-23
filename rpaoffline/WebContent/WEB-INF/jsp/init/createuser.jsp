<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>RBI File Parser</title>-->
        <script src="resources/jQuery/jquery.min.js"></script>  
        <link rel="stylesheet" href="resources/jQuery/datatable/jquery.dataTables.min.css" />  
        <script src="resources/jQuery/datatable/jquery.dataTables.min.js"></script>   
        <script src="resources/angular1.3.js"></script> 
        <script src="resources/date.js"></script> 
        <script src="resources/sha256.min.js"></script> 
        <style>
            /*            input[type=text],input[type=password],select{
                            width: 70%;
                        }*/
            input[type=radio]{
                margin: 5px 0 5px 30px;
            }
            td,th{
                font-size: 12px;
            }
            input[type=search]{
                margin-bottom: 5px;
            }
        </style>
    </head>
    <body ng-app="CommonApp">
        <%@include file="../common/header.jsp" %> 
        <div class='containerBody' id="createuserCtrl" ng-controller="createuserCtrl">
            <h2 class="title">User Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form>
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title" style="width: 30%">Officer Name :</td>
                            <td style="width:70%">
                                <input name="officername" type="text" id="officername" ng-model="user.officername" />
                            </td>                                    
                        </tr> 
                        <tr class="form-group has-feedback">
                            <td class="title">Department :</td>
                            <td class="col-xs-5 selectContainer">
                                <select class="form-control" id="department" name="department" ng-model="department" ng-options="head.value for head in departments track by head.key" ng-change="listDesignation()"></select>
                                <!--                                <select class="form-control" id="departmentid" name="departmentid" ng-model="departmentid" >
                                                                    <option value="" selected disabled> --SELECT--</option>
                                                                    <option ng-selected="head.key == departmentid" ng-repeat='head in departments track by $index' ng-value="head.key"> {{head.value}} </option>
                                                                </select> -->
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Cadre Group :</td>
                            <td class="col-xs-5 selectContainer">
                                <select   class="form-control" id="designationgrp" name="designationgrp" ng-model="designationgrp" ng-options="head.value for head in designationgroups track by head.key" ng-change="listDesignation()"></select>
                                <!--                                <select   class="form-control" id="designationgrp" name="designationgrp" ng-model="designationgrp" >
                                                                    <option value="" selected disabled> --SELECT--</option>
                                                                    <option ng-selected="head.key == designationgrp" ng-repeat='head in designationgroups track by $index' ng-value="head.key"> {{head.value}} </option>
                                                                </select> -->
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Designation :</td>
                            <td class="col-xs-5 selectContainer">
                                <select class="form-control" id="designationid" name="designationid" ng-model="user.designation" ng-options="head.designationname for head in designations track by head.designationid"></select>
                                <!--                                <select   class="form-control" id="designationid" name="designationid" ng-model="user.designationid" >
                                                                    <option value=""> --SELECT--</option>
                                                                    <option ng-selected="head.key == user.designationid" ng-repeat='head in designations track by $index' ng-value="head.key"> {{head.value}} </option>
                                                                </select> -->
                            </td>
                        </tr>
                        <tr ng-if="user.usercode == 0">
                            <td class="title">User Name :</td>
                            <td class="col-xs-5 selectContainer">
                                <input type="text" ng-model="user.username" id="username"/>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Password :</td>
                            <td class="col-xs-5 selectContainer">
                                <input type="password" name="passwords" id="passwords" ng-model="user.password" autocomplete="new-password"/>
                                <label ng-if="user.usercode > 0" style="color:blue"> *Keep BLANK incase password is unchanged</label>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Re-type Password :</td>
                            <td class="col-xs-5 selectContainer">
                                <input type="password"  id="repassword" ng-model="repassword" autocomplete="new-password"/>
                                <label ng-if="user.usercode > 0" style="color:blue"> *Keep BLANK incase password is unchanged</label>
                                <label ng-if="repassword != user.password" style="color:red">*password is not matching</label> 
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Role :</td>
                            <td>
                                <div style="border: #34495e59 SOLID 2px;border-radius: 5px;width: 80%;">
                                    <input type="radio" name="userrole" ng-model="user.userrole"  value='U' checked="checked"/> <label>&nbsp;User</label>
                                    <input type="radio" name="userrole" ng-model="user.userrole" value='A'/><label>&nbsp;Super User</label>
                                    <input type="radio" name="userrole" ng-model="user.userrole" value='G'/><label>&nbsp;GAD</label>
                                    <input type="radio" name="userrole" ng-model="user.userrole" value='E'/><label>&nbsp;Estate</label>
                                </div>

                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">User Access :</td>
                            <td>
                                <div style="border: #34495e59 SOLID 2px;border-radius: 5px;width: 80%;">
                                    <input type="radio" ng-model="user.enabled" id="yes" value='Y' checked="checked"/><label>&nbsp; Enabled</label>
                                    <input type="radio" ng-model="user.enabled" id="no" value='F'/><label>&nbsp; Disabled</label>
                                </div>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button name="submit" id="add" ng-click="save()" class="button-primary">Save</button>
                                <!--<input type="button" name="submit" id="add" value="Save" ng-click="save()"/>-->
                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
        </div>
        <script src="resources/application/js/controllers/createuser.js"></script>
        <script>
        </script>
    </body>
</html>
