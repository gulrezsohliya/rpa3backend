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
	        <h3 class="mt-4" style="font-size:32px;">Cell Initialization</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
					<div class='containerBody' id="cellCtrl" ng-controller="cellCtrl">            
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
											
										</td>
										<td style="width:100px"><span class="alert alert-danger" 
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
										</td> 
										<td><span class="alert alert-danger" 
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
	        	</div>		
	        	   	
	        </div>   	        
	             
	      </div>	      
	    </div> 	    
	  </div>
	  <script src="resources/application/js/controllers/initcell.js"></script>
	</body>
</html>