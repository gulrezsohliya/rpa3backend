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
        <div class='containerBody' id="venueCtrl" ng-controller="venueCtrl">
            <h2 class="title">Venue Initialization</h2>
            <div  style='width:80%;margin: 15px auto 0'>
                <form id="venueForm" name="venueForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        <tr class="form-group has-feedback">
                            <td class="title">Examination center:</td>
                            <td class="col-xs-5 selectContainer">
								<select type="text" class="form-control" id="centercode" name="centercode" ng-model="venue.centercode" required>
									<option value="">--Select--</option>
									<option ng-selected="center.centercode == venue.centercode"
										ng-repeat='center in centers' ng-value="center.centercode">
										{{center.centername}}</option>
								</select>       								
								<span id="centercodeMsg"></span>
								<span class="alert alert-danger" 
									ng-show="venueForm.centercode.$pristine && venueForm.centercode.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Venue :</td>
                            <td class="col-xs-5 selectContainer">
								<input type="text" class="form-control" id="venuename" name="venuename"
									ng-model="venue.venuename" required/>        
								<span id="venuenameMsg"></span>
								<span class="alert alert-danger" 
									ng-show="venueForm.venuename.$pristine && venueForm.venuename.$invalid"> 
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
        <script src="resources/application/js/controllers/initvenue.js"></script>
    </body>
</html>
