<html>
	<head>
		<%@include file="../common/headerfiles.jsp" %>     	
		<style>
			.message{
	    			width:100%;
	    			background-color:#28a745;
	    			color:white;
	    			display:flex;
	    			justify-content: center;
	    			padding:5px;
	    		}
		</style>	
	</head>
	<body ng-app="CommonApp">
	<div class="d-flex" id="wrapper">
		<%@include file="../common/menuside.jsp" %>  		   
	    <div id="page-content-wrapper">	
		  <%@include file="../common/menutop.jsp" %>     
	      <div class="container-fluid">
	        <h3 class="mt-4" style="font-size:32px;">Advertisement</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
				        <div class='containerBody' id="advCtrl" ng-controller="advCtrl">				           
				            <div  style='margin: 0 auto'>
					            <div id="stepperHeader">
					        		<div ng-style="step>=1 && {'backgroundColor': '#128293','color':'#fff'}">
							            Advertisement Details
							        </div>
							        <div ng-style="step>=2 && {'backgroundColor': '#128293','color':'#fff'}">
							            Age & Fee
							        </div>
							        <div ng-style="step>=3 && {'backgroundColor': '#128293','color':'#fff'}">
							            Qualifications & Experience
							        </div>
							    </div>
							    
					    		<div id="stepperBody">
					                <form id="advForm" name="advForm" ng-show='step==1' autocomplete="off">
					                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px;"> 
					                        
					                        <tr class="form-group has-feedback">
					                            <td class="title">Advertisement No.</td>
					                            <td class="col-xs-5 selectContainer">
													<input class="form-control" type="text" id="advertisementno" name="advertisementno"
														ng-model="adv.advertisementno" required/>
													<span id="advMsg"></span>
												</td>
					                            <td style='width:100px'><span class="alert alert-danger" 
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
												</td>
					                            <td><span class="alert alert-danger" 
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
												</td>
					                            <td><span class="alert alert-danger" 
														ng-show="advForm.nameofpost.$pristine && advForm.nameofpost.$invalid"> 
														Required
													</span>
					                            </td>
					                        </tr>
					                        <tr class="form-group has-feedback">
					                            <td class="title">Post Sort Name:</br><span style='font-size: 10px'>Please provide a short name of the</br>Name of Post to be  used for sending SMS</span></td>
					                            <td class="col-xs-5 selectContainer">
													<input class="form-control" type="text" id="postshortname" name="postshortname"
														ng-model="adv.postshortname" required/>
													<span id="advMsg"></span>
												</td>
					                            <td><span class="alert alert-danger" 
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
												</td>
					                            <td><span class="alert alert-danger" 
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
												</td>
					                            <td><span class="alert alert-danger" 
														ng-show="advForm.lastdate.$pristine && advForm.lastdate.$invalid"> 
														Required
													</span>
					                            </td>
					                        </tr>
					                        <tr class="form-group has-feedback">
					                            <td class="title">Age to be calculate on date</td>
					                            <td class="col-xs-5 selectContainer" style="line-height:0;padding-bottom:10px">
													<input class="form-control" type="date" id="agedate" name="agedate"
														ng-model="adv.agedate" required ng-disabled="agedate_sameas_issuedate"/>
													<span id="advMsg"></span>
													</br><input type="checkbox" ng-model="agedate_sameas_issuedate"
														 ng-change="adv.agedate=((agedate_sameas_issuedate)?adv.issuedate:adv.agedate)"/><span style="font-size:13px">Same as Issue date?</span>
												</td>
												<td><span class="alert alert-danger" 
														ng-show="advForm.agedate.$pristine && advForm.agedate.$invalid"> 
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
												</td>
					                            <td><span class="alert alert-danger" 
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
												</td>
					                            <td><span class="alert alert-danger" 
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
												</td>
					                            <td><span class="alert alert-danger" 
														ng-show="advForm.finalized.$pristine && advForm.finalized.$invalid"> 
														Required
													</span>
					                            </td>
					                        </tr>
					                        
					                        <tr class="form-group has-feedback">
					                            <td colspan="3" align="center">
					                                <button type="submit" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="advForm.$invalid">Save & Continue</button>
					                                <button name="submit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="advForm.$invalid">Save & Continue</button>
					                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
					                            </td>
					                        </tr>
					                    </table>
					                </form>
					                <form id="advForm2" name="advForm2" ng-show='step==2' autocomplete="off">                	
					                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
					                    	<tr class="form-group has-feedback">
					                            <td class="title" style="border-bottom:1px solid black">Age as on <input type="date" ng-model='adv.agedate' readonly style='border:none;background:inherit;'/></td>
					                            <td class="col-xs-5 selectContainer"style="padding: 10px 0;border-bottom:1px solid black">
													<table border='1' cellspacing="0" style="border-color:ivory"> 
														<tr><th style='width:30%;background: aliceblue'>Category</th>
															<th style='width:30%;background: aliceblue'>Min Age</th>
															<th style='width:30%;background: aliceblue'>Max Age</th>
															<th style='width:60px;background: aliceblue'></th></tr>
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
					                            <td class="title" style="border-bottom:1px solid black">Age relaxations</td>
					                            <td class="col-xs-5 selectContainer"style="padding: 10px 0;border-bottom:1px solid black ">
													<table border='1' cellspacing="0" style="border-color:ivory" >
														<tr>
															<th style='width:30%;background: aliceblue'>PWD</th>
															<th style='width:30%;background: aliceblue'>Woman</th>
															<th style='width:30%;background: aliceblue'>Ex-service man</th>
															<th style='width:60px;background: aliceblue'></th>
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
					                            <td class="title" style="border-bottom:1px solid black">Fees</td>
					                            <td class="col-xs-5 selectContainer"style="padding: 10px 0;border-bottom:1px solid black">
													<table border='1' cellspacing="0" style="border-color:ivory">
														<tr><th style='width:30%;background: aliceblue'>Category</th>
															<th style='width:30%;background: aliceblue'>Fee Amount</th>
															<th style='width:30%;background: aliceblue'></th>
															<th style='width:60px;background: aliceblue'></th>
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
					                            <td class="title" style="border-bottom:1px solid black">Fee relaxations</td>
					                            <td class="col-xs-5 selectContainer"style="padding: 10px 0;border-bottom:1px solid black">
													<table border='1' cellspacing="0" style="border-color:ivory">
														<tr>
															<th style='width:30%;background: aliceblue'>PWD</th>
															<th style='width:30%;background: aliceblue'>Woman</th>
															<th style='width:30%;background: aliceblue'>Ex-service man</th>
															<th style='width:60px;background: aliceblue'></th>
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
					                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
					                            </td>
					                        </tr>
					                    </table>
					                </form>	                
					                <form id="advForm3" name="advForm3"  ng-show='step==3' autocomplete="off">  
					                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
	                       					<tr class="form-group has-feedback">
					                            <td class="col-xs-5 selectContainer title" style="padding:10px 0">No. of Optional Subjects</td>
					                            <td class="col-xs-5 selectContainer">
													<select class="form-control" id="noofoptionals" name="noofoptionals"
														ng-model="adv.noofoptionals" style="width:100%" ng-change="checkOptionals()">
														<option value='0' selected>0</option>
														<option value='1' selected>1</option>
														<option value='2' selected>2</option>
														<option value='3' selected>3</option>
													</select>
													<span id="advMsg"></span>
												</td>
					                            <td style='width:100px'><span class="alert alert-danger" 
														ng-show="advForm3.noofoptionals.$pristine && advForm3.noofoptionals.$invalid"> 
														Required
													</span>
					                            </td>
					                        </tr>
					                       <tr class="form-group has-feedback">
					                            <td class="col-xs-12 selectContainer" colspan="3" style="padding:10px 0">
													<table border='1' cellspacing="0" style="border-color:ivory;width:100%;" >
														<tr>
															<th style='width:30%;'>Optional Subject 1</th>
															<th style='width:30%;'>Optional Subject 2</th>
															<th style='width:30%;'>Optional Subject 3</th>
														</tr>
														<tbody>
															<tr>
																<td style="position:relative;">																
																	<ul style="height:200px;width:100%;overflow: auto;list-style: none;padding-left: 10px;" >
																		<li ng-repeat='opt in OptionalSubjects1 track by $index'>
																			<input type="checkbox" ng-model="opt.checked"  ng-disabled="(adv.noofoptionals<1)"/>
																			{{opt.optionalsubjectname}}
																		</li>
																	</ul> 
																	<div ng-if="(adv.noofoptionals<1)" style="z-index:999;position:absolute;top:0;left:0;width:100%;height:200px;background-color:#0000001a; "></div>																	
																</td>
																<td style="position:relative;">
																	<ul style="height:200px;width:100%;overflow: auto;list-style: none;padding-left: 10px;" >
																		<li ng-repeat='opt in OptionalSubjects2 track by $index'>
																			<input type="checkbox" ng-model="opt.checked" ng-disabled="(adv.noofoptionals<2)"/>
																			{{opt.optionalsubjectname}}
																		</li>
																	</ul> 
																	<div ng-if="(adv.noofoptionals<2)" style="z-index:999;position:absolute;top:0;left:0;width:100%;height:200px;background-color:#0000001a; "></div>																	
																</td>
																<td style="position:relative;">
																	<ul style="height:200px;width:100%;overflow: auto;list-style: none;padding-left: 10px;" >
																		<li ng-repeat='opt in OptionalSubjects3 track by $index'>
																			<input type="checkbox" ng-model="opt.checked" ng-disabled="(adv.noofoptionals<3)"/>
																			{{opt.optionalsubjectname}}
																		</li>
																	</ul> 
																	<div ng-if="(adv.noofoptionals<3)" style="z-index:999;position:absolute;top:0;left:0;width:100%;height:200px;background-color:#0000001a; "></div>																	
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
	        	</div>			        	       	       	                	
	        </div>   	        
	             
	      </div>	      
	    </div> 	    
	  </div>
	  <script src="resources/application/js/controllers/advertisement.js"></script>
	</body>
</html>