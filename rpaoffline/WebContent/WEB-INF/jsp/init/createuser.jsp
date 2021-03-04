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
                <form id="userForm" name="userForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title" style="width: 30%">Office:</td>
                            <td style="width:70%">
                                <select class="form-control" id="officecode" name="officecode"
                                	 ng-model="user.officecode" 
                                     ng-options="head.officecode as head.officename1 for head in offices" 
                                     ng-change="listOfficeCells(user.officecode)"
                                     ng-selected="user.officecode == head.officecode" 
                                     required></select>
                                <span id="officecodeMsg" ng-show="!userForm.officecode.$pristine && userForm.officecode.$invalid"> Required</span>
                            </td>                                    
                        </tr> 
                        <tr class="form-group has-feedback">
                            <td class="title">Cell :</td>
                            <td class="col-xs-5 selectContainer">
                                <select class="form-control" id="cellcode" name="cellcode"
                                	ng-model="user.cellcode" 
                                	ng-options="head.cellcode as head.celldescription for head in cells"
                                	ng-selected="head.cellcode == user.cellcode" 
                                	required></select>
                               	<span id="cellcodeMsg"></span>
                               	<span class="alert alert-danger" ng-show="!userForm.cellcode.$pristine && userForm.cellcode.$invalid"> Required</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Username:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="username" name="username"
									ng-model="user.username" required/>        
								<span id="usernameMsg"></span>
								<span class="alert alert-danger" ng-show="!userForm.username.$pristine && userForm.username.$invalid"> Required</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Password :</td>
                            <td class="col-xs-5 selectContainer">
                                <input type="password" name="passwords" id="passwords" ng-model="user.passwords" autocomplete="new-password" required/>
<!--                                 <label ng-if="user.usercode > 0" style="color:blue"> *Keep BLANK incase password is unchanged</label> -->
                                <span id="passwordsMsg"></span>
                                <span class="alert alert-danger" ng-show="!userForm.passwords.$pristine && userForm.passwords.$invalid"> Required</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Confirm Password :</td>
                            <td class="col-xs-5 selectContainer">
                                <input type="password"  id="repassword" name="repassword" ng-model="user.repassword" autocomplete="new-password" required/>
<!--                                 <label ng-if="user.usercode > 0" style="color:blue"> *Keep BLANK incase password is unchanged</label> -->
                                <label ng-if="user.repassword != user.passwords && userForm.repassword.$dirty" style="color:red">*password is not matching</label>
                                <span id="repasswordMsg"></span>
                                <span class="alert alert-danger" ng-show="!userForm.repassword.$pristine && userForm.repassword.$invalid"> Required</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="submit" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 1" ng-disabled="userForm.$invalid">Save</button>
                                <button name="submit" id="add" ng-click="update()" class="button-primary" ng-if="actionButton === 2" ng-disabled="userForm.$invalid">Update</button>
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
