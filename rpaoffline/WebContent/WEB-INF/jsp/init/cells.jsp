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
        <div class='containerBody' id="cellCtrl" ng-controller="cellCtrl">
            <h2 class="title">Cell Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form id="cellForm" name="cellForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title">Office:</td>
                            <td class="col-xs-5 selectContainer">
								<select type="text" class="form-control" id="office" name="officecode" ng-model="cell.officecode" required>
									<option value="">--Select--</option>
									<option ng-selected="offc.officecode == cell.officecode"
										ng-repeat='offc in offices track by $index' ng-value="offc.officecode">
										{{offc.officeshortname}}</option>
								</select>       								
								<span id="officecodeMsg"></span>
								<span class="alert alert-danger" 
									ng-show="cellForm.officecode.$pristine && cellForm.officecode.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Cell Description:</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="celldescription" name="celldescription"
									ng-model="cell.celldescription" required/>        
								<span id="celldescriptionMsg"></span>
								<span class="alert alert-danger" 
									ng-show="cellForm.celldescription.$pristine && cellForm.celldescription.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="button" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="cellForm.$invalid">Add</button>
                                <button name="button" id="edit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="cellForm.$invalid">Update</button>
                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
        </div>
        <script src="resources/application/js/controllers/initcell.js"></script>
    </body>
</html>
