<%@page import="rpa.Models.master.User"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%-- <%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<title>RPA3</title>
<%@include file="headerfiles.jsp"%>
<script>
	$(document).ready(function() {
		$(document).tooltip({
			position : {
				my : "center bottom",
				at : "left top-10",
				collision : "flip",
				using : function(position, feedback) {
					$(this).addClass(feedback.vertical).css(position);
				}
			}
		});
	});
</script>
<style>
*, *:after, *::before {
	box-sizing: border-box;
}
:root{
	--main-head-color:#34495e;
	--main-head-fontcolor: #deedf7;
}
body {
	background-color: #fff;
	min-width:460px;
}

.gn-menu-main, .gn-menu-main ul {
	margin: 0;
	padding: 0;
	color: var(--main-head-fontcolor);
	list-style: none;
	text-transform: none;
	font-weight: 300;
	font-family: 'Lato', Arial, sans-serif;
	line-height: 40px;
}

.gn-menu-main {
/*  	background: url('resources/images/estateBanner.jpg') black no-repeat round;  */
	background: var(--main-head-color);
	position: fixed;
	top: 0;
	left: 0;
	width:100%;
	height:90px;
	font-size: 18px;
	padding: 13px 0;
	z-index: 1;
}
.menucontainer{
	min-width:5px;
	width:60px;
}
.header{
	flex-grow:1;
	display:flex;
	flex-flow:row nowrap; 
}
.headerA{
	flex-grow:12;
	line-height:5px;
}
.headerB{
	flex-grow:4;
	display	:inline-flex;
	flex-direction:column-reverse;
	min-width:100px;
	margin-bottom:-13px; 
}
.headerB h6 {
	/*position:absolute;
	bottom:0;*/
	margin: 0;
    line-height: normal;
    padding:5px;
}
.headerB div.user ul {
	position:absolute;
	display:none;
}
.headerB div.user:hover > ul {
	display:block;
}
@media only screen and (max-width:750px) {
	.headerA{
		width:250px; 
	}
	.headerB{
		width:120px; 
	}
	.headerA :is(h2,h4){
		visibility:hidden;
	}
	.headerA h2:before{
		visibility:visible;
		content:'RPA' 
	}
	.headerA h4:before{
		visibility:visible;
		content:'MPSC Shillong' 
	}
}

.gn-menu-main a {
	display: block;
	height: 100%;
	color: white;
	text-decoration: none;
	cursor: pointer;
}

.gn-menu-main:after {
	display: table;
	clear: both;
	content: '';
}

.gn-menu-main>li {
	display: block;
	float: left;
	height: 100%;
	padding: 2px 45px;
}

#govt {
	line-height: 0;
}

.containerBody {
	margin: 135px 0 0 60px;
	z-index: 0;
}

.containerBody h2:first-child {
	width: 100%;
	text-align: center;
}

.dataTables_wrapper {
	position: inherit;
}

table.dataTable tbody th, table.dataTable tbody td {
	padding: 5px 5px;
}

.title {
	font-weight: bold;
}

.ui-tooltip {
	text-align: center;
	box-shadow: none;
	padding: 0;
	font-size: 15px;
	color: white;
}

.ui-tooltip-content {
	position: relative;
	padding: 5px 8px;
	background-color: black;
	border: 1px solid #34495e;
	border-radius: 5px;
}

thead {
	background: white;
}
</style>
<div>
	<%-- <ul id="gn-menu" class="gn-menu-main">
		<li id="menu">
			<core:if test="${pageContext.request.userPrincipal.authenticated eq true}">
				<%@include file="menu.jsp"%>
			</core:if>
		</li>
		<li style="padding-top: 68px; padding-right: 335px">
			<core:if test="${pageContext.request.userPrincipal.authenticated eq true && sessionScope.user ne null}">
				<h6 style="line-height: 0; text-align: right;">
					Logged in as
					<%=session.getAttribute("user")%></h6>
			</core:if> 
		</li>
	</ul> --%>
	<!-------------------- Header Start --------------------------->
	<div  id="gn-menu" class="gn-menu-main" style="display:flex;">
		<div class="menucontainer">
			<core:if test="${pageContext.request.userPrincipal.authenticated eq true}">
				<%@include file="menu.jsp"%>
			</core:if>
		</div>
		<div class="header">
			<div class="headerA">
				<h2>Recruitment Processing Application</h2>
				<h4>Meghalaya Public Service Commission Shillong</h4>
			</div>
			<div class="headerB">
				<core:if test="${pageContext.request.userPrincipal.authenticated eq true && sessionScope.user ne null}">
						<div class="user"><h6>Logged in as <%=((User)session.getAttribute("user")).getUsername()%></h6>
							<ul style="margin:0 5px;z-index:10;background:var(--main-head-color	);">
								<li> <h6><a style="font-family:icomoon" class="icon-pencil" href='/rpaoffline/changepassword.htm'>&nbsp;Change password</a></h6> </li>
								<li> <h6><a style="font-family:icomoon" class="icon-exit" href='/rpaoffline/logout'>&nbsp;Logout</a></h6> </li>
							</ul>
						</div>					
				</core:if>
			</div>
		</div>
	</div> 
	<!-------------------- Header End ----------------------------->
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
</div>
<div id="loadingAjax">
	<%@include file="ajaxloading.jsp"%>
</div>