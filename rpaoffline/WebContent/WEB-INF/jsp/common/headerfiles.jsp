<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%> --%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<sec:csrfMetaTags />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf" content="<core:out value="${_csrf.token}" escapeXml="true"></core:out>"/>
<meta name="_csrf_header" content="<core:out value="${_csrf.headerName}" escapeXml="true"></core:out>"/>
<noscript>
	<meta http-equiv="refresh" content="0; URL=<core:out value="${contextPath}" escapeXml="true"/>/noscript.jsp"/>
</noscript>

<!--Vendor CSS -->
<link rel="stylesheet" href="resources/vendor/googlenexusMenu/css/component.css" />  
<link rel="stylesheet" href="resources/vendor/jQuery/jquery-ui.min.css" />  
<link rel="stylesheet" href="resources/vendor/jQuery/fancybox/jquery.fancybox.css" />
<link rel="stylesheet" href="resources/vendor/jQuery/datatable/jquery.dataTables.min.css" />  

<!-- App CSS -->
<link rel="stylesheet" href="resources/application/style/style.css" rel="stylesheet" type="text/css"/>

<!--Vendor Scripts -->
<script src="resources/vendor/jQuery/jquery.min.js"></script>  
<script src="resources/vendor/jQuery/jquery-ui.min.js"></script>  
<script src="resources/vendor/jQuery/datatable/jquery.dataTables.min.js"></script>  
<script src="resources/vendor/angular/angular1.3.js"></script> 
<script src="resources/vendor/date.js"></script>
<script type="text/javascript" src="resources/vendor/jQuery/fancybox/jquery.fancybox.pack.js"></script>

<!-- App Scripts -->
<script src="resources/application/js/ajaxloading.js" type="text/javascript"></script>
<script src="resources/application/js/constants.js"></script>
<script src="resources/application/js/models/initializations.js"></script>
<script src="resources/application/js/commonmodules.js"></script>
<script src="resources/application/js/factories/setHttpHeader.js"></script>
<script src="resources/application/js/factories/commonInitFactory.js"></script>
<script src="resources/application/js/services/commonInitService.js"></script>  
<script src="resources/application/js/directives/ngdirectives.js"></script>