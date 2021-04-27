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
    		.message{
    			width:100%;
    			background-color:#28a745;
    			color:white;
    			display:flex;
    			justify-content: center;
    			padding:5px;
    		}
    		#advForm2 table table input[type=number]{
    			border:none;
    		}
        </style>
    </head>
    <body ng-app="CommonApp">
        <%@include file="../common/header.jsp" %> 
        <div class='containerBody' id="advCtrl" ng-controller="advCtrl">
            <h2 class="title">Advertisement</h2>
            <div  style='margin: 0 auto'>
	            <div id="stepperHeader">
	        		<div ng-style="step>=1 && {'backgroundColor': '#34495e','color':'#fff'}">
			            Advertisement Details
			        </div>
			        <div ng-style="step>=2 && {'backgroundColor': '#34495e','color':'#fff'}">
			            Age & Fee
			        </div>
			        <div ng-style="step>=3 && {'backgroundColor': '#34495e','color':'#fff'}">
			            Qualifications & Experience
			        </div>
			    </div>
			    
	    		<div id="stepperBody">
	                <form id="advForm" name="advForm" ng-show='step==1'>
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
		                            <textarea class="form-control" name="description"
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
									<textarea class="form-control" name="nameofpost"
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
	                            <td class="title">No. of Optional Subjects</td>
	                            <td class="col-xs-5 selectContainer">
									<select class="form-control" id="noofoptionals" name="noofoptionals"
										ng-model="adv.noofoptionals" >
										<option value='0' selected>0</option>
										<option value='1'>1</option>
										<option value='2'>2</option>
										<option value='3'>3</option>
									</select>
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
	                                <button type="submit" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="advForm.$invalid">Save & Continue</button>
	                                <button name="submit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="advForm.$invalid">Save & Continue</button>
	                                <input type="button" value="Reset" ng-click="reset()" class="button-default"/>
	                            </td>
	                        </tr>
	                    </table>
	                </form>
	                <form id="advForm2" name="advForm2" ng-show='step==2'>                	
	                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
	                    	<tr class="form-group has-feedback">
	                            <td class="title">Age as on <input type="date" ng-model='adv.agedate' readonly style='border:none;background:inherit;'/></td>
	                            <td class="col-xs-5 selectContainer">
									<table border='1' cellspacing="0" style='width:80%;border-color: ghostwhite;'>
										<tr><th style='width:30%;background: aliceblue'>Category</th>
											<th style='width:30%;background: aliceblue'>Min Age</th>
											<th style='width:30%;background: aliceblue'>Max Age</th>
											<th style='width:10%;background: aliceblue'></th></tr>
										<tbody>
											<tr ng-repeat='ageItem in adv.advertisementAge'>
												<td><select ng-model="ageItem.categorycode" style='width:100%'>
													<option value="">--Select--</option>
													<option ng-selected="category.categorycode == ageItem.categorycode"
														ng-repeat='category in Categories track by $index' ng-value="category.categorycode">
														{{category.categorycode}}</option>
												</select> 
												</td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="ageItem.minage"  min='0'/></td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="ageItem.maxage"  min='0'/></td>
												<td><input type="button" class="form-control" value="Add" ng-click='addAdvAge()' ng-if='$index==adv.advertisementAge.length-1 && $index < Categories.length-1'/></td>
											</tr>
										</tbody>
									</table>
	                            </td>
	                        </tr>
	                    	<tr class="form-group has-feedback">
	                            <td class="title">Age relaxations</td>
	                            <td class="col-xs-5 selectContainer">
									<table border='1' cellspacing="0" style='width:80%;border-color: ghostwhite;'>
										<tr>
											<th style='width:30%;background: aliceblue'>PWD</th>
											<th style='width:30%;background: aliceblue'>Woman</th>
											<th style='width:30%;background: aliceblue'>Ex-service man</th>
											<th style='width:10%;background: aliceblue'></th>
										</tr>
										<tbody>
											<tr>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="adv.advertisementAgeRelax.pwdadditionalage"  max='10'/></td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="adv.advertisementAgeRelax.womanadditionalage"  max='10'/></td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="adv.advertisementAgeRelax.exservicemenadditionalage"  max='10'/></td>
												<td></td>
											</tr>
										</tbody>
									</table>
	                            </td>
	                        </tr>
	                    	<tr class="form-group has-feedback">
	                            <td class="title">Fees</td>
	                            <td class="col-xs-5 selectContainer">
									<table border='1' cellspacing="0" style='width:80%;border-color: ghostwhite;'>
										<tr><th style='width:30%;background: aliceblue'>Category</th>
											<th style='width:30%;background: aliceblue'>Fee Amount</th>
											<th style='width:30%;background: aliceblue'></th>
											<th style='width:10%;background: aliceblue'></th>
										</tr>
										<tbody>
											<tr ng-repeat='feeItem in adv.advertisementFee'>
												<td><select ng-model="feeItem.categorycode" style='width:100%'>
													<option value="">--Select--</option>
													<option ng-selected="category.categorycode == feeItem.categorycode"
														ng-repeat='category in Categories track by $index' ng-value="category.categorycode">
														{{category.categorycode}}</option>
												</select> 
												</td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="feeItem.feeamount" min='0'/></td>
												<td></td>
												<td><input type="button" class="form-control" value="Add" ng-click='addAdvFee()' ng-if='$index==adv.advertisementFee.length-1 && $index < Categories.length-1'/></td>
											</tr>
										</tbody>
									</table>
	                            </td>
	                        </tr>
	                    	<tr class="form-group has-feedback">
	                            <td class="title">Fee relaxations</td>
	                            <td class="col-xs-5 selectContainer">
									<table border='1' cellspacing="0" style='width:80%;border-color: ghostwhite;'>
										<tr>
											<th style='width:30%;background: aliceblue'>PWD</th>
											<th style='width:30%;background: aliceblue'>Woman</th>
											<th style='width:30%;background: aliceblue'>Ex-service man</th>
											<th style='width:10%;background: aliceblue'></th>
										</tr>
										<tbody>
											<tr>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="adv.advertisementFeeRelax.pwdfees"  min='0'/></td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="adv.advertisementFeeRelax.womanfees"  min='0'/></td>
												<td><input style="width:100%;text-align: center" class="form-control" type="number" ng-model="adv.advertisementFeeRelax.exservicemenfees"  min='0'/></td>
												<td></td>
											</tr>
										</tbody>
									</table>
	                            </td>
	                        </tr>
	                        
	                        <tr class="form-group has-feedback">
	                            <td colspan="2" align="center">
	                                <input type='button' ng-click="setStep(1)" class="button-default" ng-if="actionButton === 'EDIT'" value="Previous Step"/>
	                                <button name="submit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="advForm2.$invalid">Save & Continue</button>
	                                <input type="button" value="Reset" ng-click="reset()" class="button-default"/>
	                            </td>
	                        </tr>
	                    </table>
	                </form>	                
	                <form id="advForm3" name="advForm3"  ng-show='step==3'>  
	                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
	                       
	                       <tr class="form-group has-feedback">
	                            <td class="title">Fee relaxations</td>
	                            <td class="col-xs-5 selectContainer">
									<table border='1' cellspacing="0" style='width:80%;border-color: ghostwhite;'>
										<tr>
											<th style='width:30%;background: aliceblue'>Optional Subject 1</th>
											<th style='width:30%;background: aliceblue'>Optional Subject 2</th>
											<th style='width:30%;background: aliceblue'>Optional Subject 3</th>
										</tr>
										<tbody>
											<tr>
												<td>
													<select ng-disabled="(adv.noofoptionals<1)"
														ng-model="adv.advertisementOptionals[0].optionalsubjectcode" style='width:100%'>
														<option value="">--Select--</option>
														<option ng-selected="opt.optionalsubjectcode == adv.advertisementOptionals[0].optionalsubjectcode"
															ng-repeat='opt in OptionalSubjects track by $index' ng-value="opt.optionalsubjectcode">
															{{opt.optionalsubjectname}}
														</option>
													</select>
												</td>
												<td>
													<select ng-disabled="(adv.noofoptionals<2)"
														ng-model="adv.advertisementOptionals[1].optionalsubjectcode" style='width:100%'>
														<option value="">--Select--</option>
														<option ng-selected="opt.optionalsubjectcode == adv.advertisementOptionals[1].optionalsubjectcode"
															ng-repeat='opt in OptionalSubjects track by $index' ng-value="opt.optionalsubjectcode">
															{{opt.optionalsubjectname}}
														</option>
													</select>
												</td>
												<td>
													<select ng-disabled="(adv.noofoptionals<3)"
														ng-model="adv.advertisementOptionals[2].optionalsubjectcode" style='width:100%'>
														<option value="">--Select--</option>
														<option ng-selected="opt.optionalsubjectcode == adv.advertisementOptionals[2].optionalsubjectcode"
															ng-repeat='opt in OptionalSubjects track by $index' ng-value="opt.optionalsubjectcode">
															{{opt.optionalsubjectname}}
														</option>
													</select>
												</td>
											</tr>
										</tbody>
									</table>
	                            </td>
	                        </tr>
	                       <tr class="form-group has-feedback">
	                           <td colspan="2" align="center">
	                               <input type='button' ng-click="setStep(2)" class="button-default" ng-if="actionButton === 'EDIT'" value="Previous Step"/>
	                                <button name="submit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="advForm.$invalid">Save & Continue</button>
	                               <input type="button" value="Reset" ng-click="reset()" class="button-default"/>
	                           </td>
	                       </tr>
	                    </table>
	                </form>
	            </div> 
	            <div class="message" ><span id="message">{{message}}</span></div>
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
