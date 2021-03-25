<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>RBI File Parser</title>-->
        <style>
            td,th{
                font-size: 12px;
            }
            input[type=search]{
                margin-bottom: 5px;
            }
            .title{
            	width:35%;
            }
            .form-group fieldset{
            	display:inline; 
            }
            .form-group fieldset input{
            	width:100%; 
            }
            legend{
	            padding: 0 5px;
			    background-color: aliceblue;
			    border: 1px solid #34495e59;
			    border-radius: 3px;
    		}
        </style>
    </head>
    <body ng-app="CommonApp">
        <%@include file="../common/header.jsp" %> 
        <div class='containerBody' id="advCtrl" ng-controller="advCtrl">
            <h2 class="title">Advertisement</h2>
            <div  style='margin: 0 auto '>
                <form id="advForm" name="advForm">
                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
                        
                        <tr class="form-group has-feedback">
                            <td class="title">Advertisement No.</td>
                            <td class="col-xs-5 selectContainer">
								<input class="form-control" type="text" id="advertisementno" name="advertisementno"
									ng-model="adv.advertisementno" required/>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.advertisementno.$pristine && advForm.advertisementno.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Description</td>
                            <td class="col-xs-5 selectContainer">
	                            <textarea class="form-control" id="nameofpost" name="description"
										ng-model="adv.description" required></textarea> 
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.description.$pristine && advForm.description.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Name of post</td>
                            <td class="col-xs-5 selectContainer">
								<textarea class="form-control" id="nameofpost" name="nameofpost"
									ng-model="adv.nameofpost" required></textarea>        
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.nameofpost.$pristine && advForm.nameofpost.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Post Sort Name:</br><span style='font-size: 9	px'>Please provide a short name of the Name of Post </br>to be  used for sending SMS</span></td>
                            <td class="col-xs-5 selectContainer">
								<input class="form-control" type="text" id="postshortname" name="postshortname"
									ng-model="adv.postshortname" required/>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.postshortname.$pristine && advForm.postshortname.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Issue Date</td>
                            <td class="col-xs-5 selectContainer">
								<input class="form-control" type="date" id="issuedate" name="issuedate"
									ng-model="adv.issuedate" required ng-change="adv.agedate=((agedate_sameas_issuedate)?adv.issuedate:adv.agedate)"/>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.issuedate.$pristine && advForm.issuedate.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Last Date</td>
                            <td class="col-xs-5 selectContainer">
								<input class="form-control" type="date" id="lastdate" name="lastdate"
									ng-model="adv.lastdate" required/>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.lastdate.$pristine && advForm.lastdate.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Age to be calculate on Date</td>
                            <td class="col-xs-5 selectContainer">
								<input class="form-control" type="date" id="agedate" name="agedate"
									ng-model="adv.agedate" required ng-disabled="agedate_sameas_issuedate"/>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.agedate.$pristine && advForm.agedate.$invalid"> 
									Required
								</span>
								</br><input type="checkbox" ng-model="agedate_sameas_issuedate"
									 ng-change="adv.agedate=((agedate_sameas_issuedate)?adv.issuedate:adv.agedate)"/><span>Same as Issue date?</span>
                            </td>
                        </tr>                        
                        <tr class="form-group has-feedback">
                            <td class="title">Age as on <input type="date" ng-model='adv.agedate' readonly style='border:none;background:inherit;'/></td>
                            <td class="col-xs-5 selectContainer">
								<fieldset><legend>General</legend>
									Minimum Age::<input style="width:30%;" class="form-control" type="number" name="GENERALagemin"
										ng-model="adv.ageason['GENERAL'].agemin"  min='0'/>
									Maximun Age::<input style="width:30%;" class="form-control" type="number" name="GENERALagemax"
										ng-model="adv.ageason['GENERAL'].agemax"  min='0'/>
								</fieldset>
								<!-- <span class="alert alert-danger" 
									ng-show="(advForm.GENERALagemin.$pristine && advForm.GENERALagemin.$invalid)||( advForm.GENERALagemax.$pristine && advForm.GENERALagemax.$invalid)"> 
									Required
								</span> -->

								<fieldset><legend>SC</legend>
									Minimum Age::<input style="width:30%;" class="form-control" type="number" name="SCagemin"
										ng-model="adv.ageason['SC'].agemin"  min='0'/>
									Maximun Age::<input style="width:30%;" class="form-control" type="number" name="SCagemax"
										ng-model="adv.ageason['SC'].agemax"  min='0'/>
								</fieldset>

								<fieldset><legend>ST</legend>
									Minimum Age::<input style="width:30%;" class="form-control" type="number" name="STagemin"
										ng-model="adv.ageason['ST'].agemin"  min='0'/>
									Maximun Age::<input style="width:30%;" class="form-control" type="number" name="STagemax"
										ng-model="adv.ageason['ST'].agemax"  min='0'/>
								</fieldset>

								<fieldset><legend>OBC</legend>
									Minimum Age::<input style="width:30%;" class="form-control" type="number" name="OBCagemin"
										ng-model="adv.ageason['OBC'].agemin" min='0'/>
									Maximun Age::<input style="width:30%;" class="form-control" type="number" name="OBCagemax"
										ng-model="adv.ageason['OBC'].agemax" min='0'/>
								</fieldset>
								<span id="advMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Application Fee</td>
                            <td class="col-xs-5 selectContainer">
								<fieldset><legend>General</legend>
									<input class="form-control" type="number" id="applicationfeesgeneral" name="applicationfeesgeneral"
										ng-model="adv.applicationfeesgeneral" required/>
								</fieldset>
								<span class="alert alert-danger" 
									ng-show="advForm.applicationfeesgeneral.$pristine && advForm.applicationfeesgeneral.$invalid"> 
									Required
								</span>
								<fieldset><legend>SC/ST</legend>
									<input class="form-control" type="number" id="applicationfeesscst" name="applicationfeesscst"
										ng-model="adv.applicationfeesscst" required/>
								</fieldset>
								<span class="alert alert-danger" 
									ng-show="advForm.applicationfeesscst.$pristine && advForm.applicationfeesscst.$invalid"> 
									Required
								</span>
								<fieldset><legend>Fee type</legend>
									<input class="form-control" type="number" id="feetype" name="feetype"
										ng-model="adv.feetype" required/>
								</fieldset>
								<span class="alert alert-danger" 
									ng-show="advForm.feetype.$pristine && advForm.feetype.$invalid"> 
									Required
								</span>
								<span id="advMsg"></span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">No. of Optional Subjects</td>
                            <td class="col-xs-5 selectContainer">
								<input class="form-control" type="number" id="noofoptionals" name="noofoptionals"
									ng-model="adv.noofoptionals" required min='0	'/>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.noofoptionals.$pristine && advForm.noofoptionals.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Open</td>
                            <td class="col-xs-5 selectContainer">
								<select class="form-control"  id="open" name="open"	ng-model="adv.open" required>
									<option value="Y" ng-selected='true'>Yes</option> 
									<option value="N">No</option> 
								</select>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.open.$pristine && advForm.open.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Counter Entry</td>
                            <td class="col-xs-5 selectContainer">
								<select class="form-control"  id="counterentry" name="counterentry"	ng-model="adv.counterentry" required>
									<option value="Y" ng-selected='true'>Yes</option> 
									<option value="N">No</option> 
								</select>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.counterentry.$pristine && advForm.counterentry.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Admit card download</td>
                            <td class="col-xs-5 selectContainer">
								<select class="form-control"  id="admitcarddownload" name="admitcarddownload"	ng-model="adv.admitcarddownload" required>
									<option value="Y" ng-selected='true'>Yes</option> 
									<option value="N">No</option> 
								</select>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.admitcarddownload.$pristine && advForm.admitcarddownload.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Interview intimation download</td>
                            <td class="col-xs-5 selectContainer">
								<select class="form-control"  id="interviewintimationdownload" name="interviewintimationdownload"	ng-model="adv.interviewintimationdownload" required>
									<option value="Y" ng-selected='true'>Yes</option> 
									<option value="N">No</option> 
								</select>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.interviewintimationdownload.$pristine && advForm.interviewintimationdownload.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        <tr class="form-group has-feedback">
                            <td class="title">Finalized</td>
                            <td class="col-xs-5 selectContainer">
								<select class="form-control"  id="finalized" name="finalized"	ng-model="adv.finalized" required>
									<option value="Y" ng-selected='true'>Yes</option> 
									<option value="N">No</option> 
								</select>
								<span id="advMsg"></span>
								<span class="alert alert-danger" 
									ng-show="advForm.finalized.$pristine && advForm.finalized.$invalid"> 
									Required
								</span>
                            </td>
                        </tr>
                        
                        <tr class="form-group has-feedback">
                            <td colspan="2" align="center">
                                <button type="submit" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="advForm.$invalid">Add</button>
                                <button name="submit" id="add" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="advForm.$invalid">Update</button>
                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div> 
            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
        </div>
        <style>
            fieldset{
            	margin:0;
            	border-width: 2px;
            }
            fieldset input{
            	width:100%;
            }
        </style>
        <script src="resources/application/js/controllers/advertisement.js"></script>
    </body>
</html>
