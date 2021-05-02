<html>
	<head>
		<%@include file="../common/headerfiles.jsp" %>     		
	</head>
	<body ng-app="CommonApp">
	<div class="d-flex" id="wrapper">
		<%@include file="../common/menuside.jsp" %>  		   
	    <div id="page-content-wrapper">	
		  <%@include file="../common/menutop.jsp" %>     
	      <div class="container-fluid">
	        <h3 class="mt-4" style="font-size:32px;">Exam Center Initialization</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
					<div class='containerBody' id="examCenterCtrl" ng-controller="examCenterCtrl">            
			            <div  style='width:80%;margin: 15px auto 0'>
			                <form id="examForm" name="examForm">
			                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
			                        <tr class="form-group has-feedback">
			                            <td class="title">Office:</td>
			                            <td class="col-xs-5 selectContainer">
											<select type="text" class="form-control" id="office" name="officecode" ng-model="examCenter.officecode" required>
												<option value="">--Select--</option>
												<option ng-selected="offc.officecode == examCenter.officecode"
													ng-repeat='offc in offices track by $index' ng-value="offc.officecode">
													{{offc.officeshortname}}</option>
											</select>       				
										</td>
										<td style="width:100px"><span class="alert alert-danger" 
												ng-show="examForm.officecode.$pristine && examForm.officecode.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td class="title">Examination Center Name:</td>
			                            <td class="col-xs-5 selectContainer">
											<input type="text" class="form-control" id="centername" name="centername"
												ng-model="examCenter.centername" required/>        
											<span id="usernameMsg"></span>
										</td>
										<td style='width:100px'><span class="alert alert-danger" ng-show="examForm.centername.$pristine && examForm.centername.$invalid"> Required</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td colspan="3" align="center">
			                                <button type="submit" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="examForm.$invalid">Add</button>
			                                <button name="submit" id="add" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="examForm.$invalid">Update</button>
			                                <input type="reset" value="Reset" ng-click="reset()" class="button-default"/>
			                            </td>
			                        </tr>
			                    </table>
			                </form>
			            </div> 
			            <div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>
			        </div>
	        	</div>			        	   
	        </div>   	        
	             
	      </div>	      
	    </div> 	    
	  </div>
	  <script src="resources/application/js/controllers/examcenter.js"></script>
	</body>
</html>