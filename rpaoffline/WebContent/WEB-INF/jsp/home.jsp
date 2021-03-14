<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>RBI File Parser</title>-->
    </head>
    <body>
	    <div ng-app="CommonApp">
       	<%@include file="common/header.jsp" %> 
	        <div class='containerBody' id="homeCtrl" ng-controller="homeCtrl" >
	                    <!--<legend class="title">Quarter Location</legend>-->
	            <div style='width:80%;margin:20% auto 0'>
	                <table class="" style="width:90%;margin: 0px auto; border-collapse: separate; border-spacing: 20px 10px; font-size:18px; font-weight: bold ">
	                    <tr>
	                        <td></td>
	                    </tr>
	                </table>
	            </div>
	            
	        </div>
	    </div>
    </body>
<!--     <script src="resources/application/js/factories/commonInitFactory.js"></script> -->
    <script src="resources/application/js/controllers/home.js"></script>
</html>
