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
	        <h3 class="mt-4" style="font-size:32px;">Other Categories Initialization</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
			        <div class='containerBody' id="otherCatCtrl" ng-controller="otherCatCtrl">            
			            <div  style='width:80%;margin: 15px auto 0'>
			                <form id="otherCatForm" name="otherCatForm">
			                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
			                        <tr class="form-group has-feedback">
			                            <td class="title">Other Category :</td>
			                            <td class="col-xs-5 selectContainer">
											<input type="text" class="form-control" id="othercategorydescription" name="othercategorydescription"
												ng-model="otherCat.othercategorydescription" required/>        
											<span id="othercategorydescriptionMsg"></span>
										</td>
										<td style="width:100px"><span class="alert alert-danger" 
												ng-show="otherCatForm.othercategorydescription.$pristine && otherCatForm.othercategorydescription.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td colspan="2" align="center">
			                                <button type="button" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="otherCatForm.$invalid">Add</button>
			                                <button name="button" id="edit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="otherCatForm.$invalid">Update</button>
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
	  <script src="resources/application/js/controllers/othercategories.js"></script>
	</body>
</html>