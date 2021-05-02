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
	        <h3 class="mt-4" style="font-size:32px;">Examination Subject Initialization</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
					<div class='containerBody' id="examSubjectCtrl" ng-controller="examSubjectCtrl">            
			            <div  style='width:80%;margin: 15px auto 0'>
			                <form id="examSubjectForm" name="examSubjectForm">
			                    <table class="" style="width:70%;margin: 0px auto; border-spacing: 10px"> 
			                        <tr class="form-group has-feedback">
			                            <td class="title">Examination Name :</td>
			                            <td class="col-xs-5 selectContainer">
											<input type="text" class="form-control" id="examinationsubjectname" name="examinationsubjectname"
												ng-model="examSubject.examinationsubjectname" required/>        
											<span id="examinationsubjectnameMsg"></span>
										</td>
										<td style='width:100px'><span class="alert alert-danger" 
												ng-show="examSubjectForm.examinationsubjectname.$pristine && examSubjectForm.examinationsubjectname.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td class="title">Description :</td>
			                            <td class="col-xs-5 selectContainer">
											<input type="text" class="form-control" id="description" name="description"
												ng-model="examSubject.description" 	/>
										</td>
										<td style='width:100px'><span class="alert alert-danger" 
												ng-show="examSubjectForm.description.$pristine && examSubjectForm.description.$invalid"> 
												Required
											</span>
			                            </td>
			                        </tr>
			                        <tr class="form-group has-feedback">
			                            <td colspan="3" align="center">
			                                <button type="button" id="add" ng-click="save()" class="button-primary" ng-if="actionButton === 'SAVE'" ng-disabled="examSubject.$invalid">Add</button>
			                                <button name="button" id="edit" ng-click="update()" class="button-primary" ng-if="actionButton === 'EDIT'" ng-disabled="examSubject.$invalid">Update</button>
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
	  <script src="resources/application/js/controllers/examsubjects.js"></script>
	</body>
</html>