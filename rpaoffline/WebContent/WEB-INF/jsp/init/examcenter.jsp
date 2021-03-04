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
        <div class='containerBody' id="examCenterCtrl" ng-controller="examCenterCtrl">
            <h2 class="title">User Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form id="examForm" name="examForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title">Examination Center Name:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="username" name="username"
									ng-model="user.username" required/>        
								<span id="usernameMsg"></span>
								<span class="alert alert-danger" ng-show="!examForm.username.$pristine && examForm.username.$invalid"> Required</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="submit" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 1" ng-disabled="examForm.$invalid">Add</button>
                                <button name="submit" id="add" ng-click="update()" class="button-primary" ng-if="actionButton === 2" ng-disabled="examForm.$invalid">Update</button>
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
        <script src="resources/application/js/controllers/examcenter.js"></script>
    </body>
</html>
