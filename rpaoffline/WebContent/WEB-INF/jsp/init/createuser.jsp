<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>RBI File Parser</title>-->
        <style>
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
                            <td class="title" style="width: 30%">Office:</td>
                            <td style="width:70%">
                                <select  class="form-control" ng-model="officecode" ng-options="head.officename1 for head in offices track by head.officecode" ng-value="head.officecode" ng-change="listOfficeCells(officecode)"></select>
                            </td>                                    
                        </tr> 
                        <tr class="form-group has-feedback">
                            <td class="title">Cell :</td>
                            <td class="col-xs-5 selectContainer">
                                <select class="form-control" ng-model="user.cellcode" ng-options="head.celldescription for head in cells track by head.cellcode"></select>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Username:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" ng-model="user.username"/>                                
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
                            <td class="title">Confirm Password :</td>
                            <td class="col-xs-5 selectContainer">
                                <input type="password"  id="repassword" ng-model="repassword" autocomplete="new-password"/>
                                <label ng-if="user.usercode > 0" style="color:blue"> *Keep BLANK incase password is unchanged</label>
                                <label ng-if="repassword != user.password" style="color:red">*password is not matching</label> 
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button name="submit" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 1">Save</button>
                                <button name="submit" id="add" ng-click="update()" class="button-primary" ng-if="actionButton === 2">Update</button>
                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
        </div>
        <script type="text/javascript">
		        /* var offices = '${offices}'; */
        </script>
        <script src="resources/application/js/controllers/createuser.js"></script>
    </body>
</html>
