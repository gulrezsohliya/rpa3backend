<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<title>RPA3</title>
<link rel="stylesheet" href="resources/googlenexusMenu/css/component.css" />  
<link rel="stylesheet" href="resources/jQuery/jquery-ui.min.css" />  
<script src="resources/jQuery/jquery-ui.min.js"></script>  
<link rel="stylesheet" href="resources/jQuery/fancybox/jquery.fancybox.css" />
<link rel="stylesheet" href="resources/style/style.css" rel="stylesheet" type="text/css"/>
<script src="resources/js/models/classes.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jQuery/fancybox/jquery.fancybox.pack.js"></script>
<script src="resources/js/ajaxloading.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $(document).tooltip({
            position: {
                my: "center bottom",
                at: "left top-10",
                collision: "flip",
                using: function(position, feedback) {
                    $(this).addClass(feedback.vertical)
                            .css(position);
                }
            }
        });
    });
</script>  
<style>
    *,
    *:after,
    *::before {
        box-sizing: border-box;
    }            
    body{
        background-color: #fff;
    }
    .gn-menu-main,
    .gn-menu-main ul {
        margin: 0;
        padding: 0;
        background: #34495e;
        color: white;
        list-style: none;
        text-transform: none;
        font-weight: 300;
        font-family: 'Lato', Arial, sans-serif;
        line-height: 40px;
    }
    .gn-menu-main {
        background: url('resources/images/estateBanner.jpg') black no-repeat round ;
        position: fixed;
        top: 0;
        left: 0;
        max-width: 362vh;
        min-width: 100%;
        height: 120px;
        font-size: 18px;
        padding: 13px 0;
        z-index: 1;
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
    .gn-menu-main > li {
        display: block;
        float: left;
        height: 100%;
        padding:2px 45px;
    }
    #govt{
        line-height: 0;    
    }
    .containerBody{
        margin: 135px 0 0 60px;
/*        overflow-y: auto;
        background: linear-gradient(rgba(255,255,255,.7), rgba(255,255,255,.7)), url(resources/images/estate.jpeg);
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-size: 100% 100%;
        height: auto;  
        min-height: 550px;*/
        z-index: 0;
    }
    .containerBody h2:first-child{
        width:100%;
        text-align: center;
    }
    .dataTables_wrapper{
        position: inherit;
    }
    table.dataTable tbody th, table.dataTable tbody td{
        padding:5px 5px;
    }
    .title{
        font-weight: bold;
    }
    .ui-tooltip {
        text-align: center;
        box-shadow: none;
        padding: 0;
        font-size: 15px;
        color:white;
    }.ui-tooltip-content {
        position: relative;
        padding: 5px 8px;
        background-color:black;
        border: 1px solid #34495e;
        border-radius: 5px;
    }
    thead {
        background: white;
    }

</style>
<div>
    <ul id="gn-menu" class="gn-menu-main">
        <li id="menu">
            <core:if test="${pageContext.request.userPrincipal.authenticated eq true}">
                <%@include file="menu.jsp" %> 
            </core:if>
        </li>
        <li style="float: right;padding-top: 68px; padding-right: 335px">
            <core:if test="${pageContext.request.userPrincipal.authenticated eq true && sessionScope.user ne null}">
                <h6 style="line-height: 0;text-align:right;">Logged in as <%= session.getAttribute("user")%></h6>
            </core:if>
            <core:if test="${pageContext.request.userPrincipal.authenticated eq true && sessionScope.user eq null}">
                <h6 style="line-height: 0;text-align:right;">&nbsp;</h6>
            </core:if>
        </li>
    </ul>
    <div style="display:none">
        <div id="MsgBox" style="min-width:200px;height:auto;" align="center">
            <p style="margin: 10px auto;"><span style="font-style:14px;"></span></p>
            <button id="msgboxbutton" style="margin:30px auto 5px auto;width:70px;border-radius: 5px;border: #34495e solid 2px; text-align: center;" onclick="jQuery.fancybox.close();
        msgboxbuttonpressed();">OK</button>
        </div>
    </div>     
    <script>
    var focused;
    function MsgBox(text, modal) {
        modal = (modal == null) ? true : modal;
        if (modal) {
            jQuery("#MsgBox").find("span").css("width", "800px");
        } else {
            jQuery("#MsgBox").find("span").css("width", "auto");
        }
        jQuery.fancybox.close();
        jQuery("#MsgBox").find("span").html(text);
        jQuery.fancybox({
            href: '#MsgBox',
            'autoSize': modal,
            'transitionIn': 'elastic',
            'transitionOut': 'elastic',
            'speedIn': 600,
            'speedOut': 200,
            'overlayShow': false,
            'modal': modal,
        });
        focused = jQuery(':focus');
        jQuery("#msgboxbutton").focus();
        if (modal) {
            jQuery("#MsgBox").find("button").css('display', '');
        } else {
            jQuery("#MsgBox").find("button").css('display', 'none');
        }
    }
    </script>
</div>
<div id="loadingAjax">
    <%@include file="ajaxloading.jsp" %>   
</div>