<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%-- <%@ taglib prefix="core" uri="http://java.sun.com/jstl/core_rt"%> --%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<title>RPA</title>
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


<!-- Bootstrap Template -->
<link href="resources/vendor/bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/vendor/bootstrap/css/base.css" rel="stylesheet" />
<link href="resources/vendor/bootstrap/css/base-responsive.css" rel="stylesheet" />
<link href="resources/vendor/bootstrap/css/animate.min.css" rel="stylesheet" />
<link href="resources/vendor/bootstrap/css/slicknav.min.css" rel="stylesheet" />
<link href="resources/vendor/bootstrap/css/font-awesome.min.css" rel="stylesheet" />
<link href="resources/vendor/bootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<!-- <script src="resources/js/vendor/bootstrap/vendor/jquery/jquery.min.js"></script> -->
<script src="resources/vendor/bootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/vendor/bootstrap/vendor/jquery-ui/jquery-ui.js"></script>    
<script src="resources/vendor/bootstrap/js/jquery.slicknav.min.js"></script>


<div style="display: none">
	<div id="MsgBox" style="min-width: 200px; height: auto;"
		align="center">
		<p style="margin: 10px auto;">
			<span style="font-style: 14px;"></span>
		</p>
		<div id="msgboxbuttons">
			
		</div>
	</div>
</div>
<script>
	var focused;
	var MsgCallBack;
	function MsgBox(text, modal, callback) {
		if(callback !== undefined){
			MsgCallBack=callback;
			}
		modal=modal==null?true:modal;
		if (modal) {
			jQuery("#MsgBox").find("span").css("width", "800px");
		} else {
			jQuery("#MsgBox").find("span").css("width", "auto");
		}
		jQuery('#msgboxbuttons').html("<button id='msgboxbutton'" +
				"style='margin: 30px auto 5px auto; width: 70px; border-radius: 5px; border: #34495e solid 2px; text-align: center;'"
					+"onclick='jQuery.fancybox.close();msgboxbuttonpressed(MsgCallBack);'>OK</button>")
		jQuery.fancybox.close();
		jQuery("#MsgBox").find("span").html(text);
		jQuery.fancybox({
			href : '#MsgBox',
			'autoSize' : modal,
			'transitionIn' : 'elastic',
			'transitionOut' : 'elastic',
			'speedIn' : 600,
			'speedOut' : 200,
			'overlayShow' : false,
			'modal' : modal,
		});
		focused = jQuery(':focus');
		jQuery("#msgboxbutton").focus();
		if (modal) {
			jQuery("#MsgBox").find("button").css('display', '');
		} else {
			jQuery("#MsgBox").find("button").css('display', 'none');
		}
	}

	function ConfirmBox(text,  callback) {
		if(callback !== undefined){
			MsgCallBack=callback;
			}
		jQuery.fancybox.close();
		jQuery("#MsgBox").find("span").html(text);
		jQuery('#msgboxbuttons').html("<button " +
				"style='margin: 30px auto 5px auto; width: 70px; border-radius: 5px; border: #34495e solid 2px; text-align: center;'"
					+"onclick='jQuery.fancybox.close();msgboxbuttonpressed(MsgCallBack,true);'>Yes</button>"+
					"<button "+ 
					"style='margin: 30px auto 5px auto; width: 70px; border-radius: 5px; border: #34495e solid 2px; text-align: center;'"
						+"onclick='jQuery.fancybox.close();msgboxbuttonpressed(MsgCallBack,false);'>No</button>")
		jQuery.fancybox({
			href : '#MsgBox',
			'autoSize' : true,
			'transitionIn' : 'elastic',
			'transitionOut' : 'elastic',
			'speedIn' : 600,
			'speedOut' : 200,
			'overlayShow' : false,
			'modal' : true,
		});
		focused = jQuery(':focus');
	}
	function msgboxbuttonpressed(callback,response) {
		jQuery('#MsgBox').find('span').html('');
		if (callback && {}.toString.call(callback) === '[object Function]') {
			callback(response);
		}
	};
</script>
