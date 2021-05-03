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
	        <h3 class="mt-4" style="font-size:32px;">Venue Initialization</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
			        <div class='containerBody' id="venueCtrl" ng-controller="venueCtrl">            
			            <div  style='width:80%;margin: 15px auto 0'>
			                <form id="venueForm" name="venueForm">
			                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
			                        <tr class="form-group has-feedback">
			                            <td class="title">Office:*</td>
			                            <td class="col-xs-5 selectContainer">
											<select class="form-control" id="office" name="officecode" ng-model="venue.officecode" ng-change="getExamcenters()" required>
												<option value="">--Select--</option>
												<option ng-selected="offc.officecode == venue.officecode"
													ng-repeat='offc in offices track by $index' ng-value="offc.officecode">
													{{offc.officeshortname}}</option>
											</select>       				
										</td>
										<td style="width:100px"><span class="alert alert-danger" 
												ng-show="venueForm.officecode.$pristine && venueForm.officecode.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td class="title">Examination center:*</td>
			                            <td class="col-xs-5 selectContainer">
											<select class="form-control" id="centercode" name="centercode" ng-model="venue.centercode" required>
												<option value="">--Select--</option>
												<option ng-selected="center.centercode == venue.centercode"
													ng-repeat='center in centers' ng-value="center.centercode">
													{{center.centername}}</option>
											</select>       					
										</td> 
										<td style="width:100px"><span class="alert alert-danger" 
												ng-show="venueForm.centercode.$pristine && venueForm.centercode.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td class="title">Venue :*</td>
			                            <td class="col-xs-5 selectContainer">
											<input type="text" class="form-control" id="venuename" name="venuename"	ng-model="venue.venuename" required/>        
											<span id="venuenameMsg"></span>
										</td>
										<td style="width:100px"><span class="alert alert-danger" 
												ng-show="venueForm.venuename.$pristine && venueForm.venuename.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td colspan="2" align="center">
			                                <button type="button" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="venueForm.$invalid">Add</button>
			                                <button name="button" id="edit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="venueForm.$invalid">Update</button>
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
	  <script src="resources/application/js/controllers/initvenue.js"></script>
	</body>
</html>