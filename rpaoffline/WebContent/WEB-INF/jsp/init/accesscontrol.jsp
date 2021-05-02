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
	        <h3 class="mt-4" style="font-size:32px;">Access Control</h3>
	        
	        <div class="row" id="accesscontrolCtrl" ng-controller="accesscontrolCtrl">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-4 px-5">	        		
	        		<form>
                    <table class="" style="width:100%;margin: 0px auto"> 
                        <tr class="form-group has-feedback">
                            <td class="title" style="width: 15%">Full Name :</td>
                            <td>
                                <span >{{user.fullname}}</span>
                            </td>                                     
                            <td rowspan="15" style="width:65%;border: 1px solid blue;">
                                <div style="width:100%;max-height:230px;overflow-y: auto ">
                                    <table border="1" cellspacing="0"width="100%">
                                        <tr>
                                            <th></th>
                                            <th>URL</th>    
                                            <th>Sub Sub Menu</th>
                                            <th>Sub Menu</td>
                                            <th>Menu Header</th>
                                        </tr>
                                        <tr ng-repeat='item in URLs track by $index' style="width:100%;">
                                            <td><input style="margin:8px" type='checkbox' ng-model="item.checked"/></td>
                                            <td >&nbsp;{{item.pageurl}}</td>
                                            <td>&nbsp;{{item.subsubmenu}}</td>
                                            <td>&nbsp;{{item.submenu}}</td>
                                            <td>&nbsp;{{item.parent}}</td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr> <tr><td>&nbsp;</td></tr>
                        <tr>
                            <td class="title">User Name :</td>
                            <td >
                                <span >{{user.username}}</span>
                            </td>
                        </tr><tr><td>&nbsp;</td></tr>
                        <tr >
                            <td class="title">User Access :</td>
                            <td>
                                <span ng-if='user.enabled=="Y"'>Enabled</span>
                                <span ng-if='user.enabled=="F"'>Disabled</span>
                            </td>
                        </tr><tr><td>&nbsp;</td></tr>
                        <tr >
                            <td colspan="2" align="center">
                                <input type="button" value="Save" ng-click="save()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="reset" value="RESET" ng-click="reset()"/>
                            </td>
                        </tr><tr><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td></tr>
                    </table>
                </form>
	        	</div>		
	        	<div id="displayRecords" style='width:80%;margin: 15px auto 50px auto;'></div>	        	       	        
        		<!-- ====================Dynamic Page Content Will Come Ends====================== -->	        	
	        </div>   	        
	             
	      </div>	      
	    </div> 	    
	  </div>
	  <script src="resources/application/js/controllers/accesscontrol.js"></script>
	</body>
</html>