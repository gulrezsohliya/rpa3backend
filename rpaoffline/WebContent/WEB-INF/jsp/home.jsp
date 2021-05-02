<html>
	<head>
		<%@include file="common/headerfiles.jsp" %>     		
	</head>
	<body  ng-app="CommonApp">
	<div class="d-flex" id="wrapper" id="homeCtrl" ng-controller="homeCtrl">
		<%@include file="common/menuside.jsp" %>  		   
	    <div id="page-content-wrapper">	
		  <%@include file="common/menutop.jsp" %>     
	      <div class="container-fluid">
	        <h3 class="mt-4" style="font-size:32px;">Home</h3>	        
	        <div class="row">	        
	        	<!-- ====================Dynamic Page Content Will Come Starts====================== -->     	
	        	<div class="col-md-12 py-12 px-12">
	        		<!-- <h3>My Content-1</h3>
	        		<div>
	        			<p><strong>Keep your page content here</strong></p>						
	        		</div> -->
	        	</div>		        	
	        </div>   
	      </div>	      
	    </div> 	    
	  </div>
	</body> 
    <script src="resources/application/js/controllers/home.js"></script>
</html>