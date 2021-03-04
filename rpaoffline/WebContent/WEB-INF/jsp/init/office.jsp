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
        <div class='containerBody' id="officeCtrl" ng-controller="officeCtrl">
            <h2 class="title">User Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form id="officeForm" name="officeForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title">Office Name 1:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="officename1" name="officename1"
									ng-model="office.officename1" required/>        
								<span id="officename1Msg"></span>
								<span class="alert alert-danger" 
									ng-show="!officeForm.officename1.$pristine && officeForm.officename1.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Office Name 2:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="officename2" name="officename2"
									ng-model="office.officename2" required/>        
								<span id="officename2Msg"></span>
								<span class="alert alert-danger" 
									ng-show="!officeForm.officename2.$pristine && officeForm.officename2.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Office Name 3:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="officename3" name="officename3"
									ng-model="office.officename3"/>        
								<span id="officename3Msg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Office Short Name:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="officeshortname" name="officeshortname"
									ng-model="office.officeshortname"/>        
								<span id="officeshortnameMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Signatory Name:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="signatoryname" name="signatoryname"
									ng-model="office.signatoryname" required/>        
								<span id="signatorynameMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Signatory Designation:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="signatorydesignation" name="signatorydesignation"
									ng-model="office.signatorydesignation" required/>        
								<span id="signatorydesignationMsg"></span>
                            </td>
                        </tr>
                        <!-- <tr class="form-group has-feedback">
                            <td class="title">Account No:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="accountno" name="accountno"
									ng-model="office.accountno" required/>        
								<span id="accountnoMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Account Bank name:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="bankname" name="bankname"
									ng-model="office.bankname" required/>        
								<span id="banknameMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Account Branch:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="bankbranch" name="bankbranch"
									ng-model="office.bankbranch" required/>        
								<span id="bankbranchMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Branch Code:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="branchcode" name="branchcode"
									ng-model="office.branchcode" required/>        
								<span id="branchcodeMsg"></span>
                            </td>
                        </tr> -->
                        <tr class="form-group has-feedback">
                            <td class="title">EmailID:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="emailid" name="emailid"
									ng-model="office.emailid"/>        
								<span id="emailidMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Email Password:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="emailidpassword" name="emailidpassword"
									ng-model="office.emailidpassword"/>        
								<span id="emailidpasswordMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">SMS Username:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="smsusername" name="smsusername"
									ng-model="office.smsusername"/>        
								<span id="smsusernameMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">SMS Password:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="smspassword" name="smspassword"
									ng-model="office.smspassword"/>        
								<span id="smspasswordMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">SMS Sender ID:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="smssenderid" name="smssenderid"
									ng-model="office.smssenderid"/>        
								<span id="smssenderidMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="submit" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 1" ng-disabled="officeForm.$invalid">Add</button>
                                <button name="submit" id="add" ng-click="update()" class="button-primary" ng-if="actionButton === 2" ng-disabled="officeForm.$invalid">Update</button>
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
        <script src="resources/application/js/controllers/initoffice.js"></script>
    </body>
</html>
