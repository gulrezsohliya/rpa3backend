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
	        <h3 class="mt-4" style="font-size:32px;">Office Center</h3>
	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
					<div class='containerBody' id="mapCtrl" ng-controller="mapCtrl">            
			            <div id="maptable" ></div>
			            <div style='width:80%;margin: 50px auto 0;'>
			                <form>
			                    <table class="" style="width:100%;margin: 0px auto"> 
			                        <tr class="form-group has-feedback">
			                            <td class="title" style="width: 15%">Office :</td>
			                            <td>
			                                <select type="text" class="form-control" id="office" name="officecode" ng-model="officecode" ng-change="mappedCenters()" required>
												<option value="">--Select--</option>
												<option ng-selected="offc.officecode == office.officecode"
													ng-repeat='offc in offices track by $index' ng-value="offc.officecode">
													{{offc.officeshortname}}</option>
											</select>
			                            </td>                                     
<!-- 			                            <td rowspan="15" style="width:65%;border: 1px solid blue;"> -->
			                                
<!-- 			                            </td> -->
			                        </tr> <tr><td>&nbsp;</td></tr>
			                        <tr >
			                            <td colspan="2" align="center">
			                                <input type="button" value="Save" ng-click="save()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			                                <input type="reset" value="RESET" ng-click="reset()"/>
			                            </td>
			                        </tr><tr><td>&nbsp;</td></tr>
			                        <tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>
			                    </table>
			                    <div style="width:100%;max-height:300px;overflow-y: auto ">
                                   <table border="1" cellspacing="0"width="100%">
                                       <tr>
                                           <th></th>
                                           <th>Examination Center Code</th>    
                                           <th>Examination Center Name</th>
                                       </tr>
                                       <tr ng-repeat='item in centers track by $index' style="width:100%;">
                                           <td><input style="margin:8px" type='checkbox' ng-model="item.checked"/></td>
                                           <td >&nbsp;{{item.centercode}}</td>
                                           <td>&nbsp;{{item.centername}}</td>
                                       </tr>
                                   </table>
                               </div>
			                </form>
			            </div>
			        </div>
	        	</div>			        	   
	        </div>   	        
	             
	      </div>	      
	    </div> 	    
	  </div>
	  <script src="resources/application/js/controllers/officeCenterMap.js"></script>
	</body>
</html>