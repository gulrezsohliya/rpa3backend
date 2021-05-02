<%@page import="rpa.Models.master.User"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<nav class="navbar navbar-expand-lg navbar-light dashboard-bgcolor border-bottom">
  <button class="btn text-white" id="menu-toggle">
  	<span style="display:none;">Menu</span>
  	<span class="fas fa-bars" style="font-size: 1.4rem"></span>
  </button>  
  <button class="navbar-toggler b-dropmenubtn" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
  	<span class="far fa-caret-square-down" style="font-size: 30px; color: #FFF"></span>
  </button>
	
  <div class="collapse navbar-collapse" id="navbarSupportedContent"> 
  	<div style="color:white;padding-left:10px">
	  	Recruitment Processing Application (<small><i>Meghalaya Public Service Commission Shillong</i></small>)		
  	</div>
    <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">          
          <span class="fas fa-user-cog text-white" style="font-size: 20px; cursor: pointer;"></span>
        </a>
        <div class="dropdown-menu dropdown-menu-right text-left b-dropmenu-db" aria-labelledby="navbarDropdown">          
          <a class="dropdown-item" href="/rpaoffline/changepassword.htm">Change Password</a>              
          <div class="dropdown-divider"></div>          
          <a class="dropdown-item" href="/rpaoffline/logout">Logout</a>                 
        </div>
      </li>      
    </ul>
  </div>
</nav>
 

<!-- Breadcrumb -->
<ul class="breadcrumb">
<!--     
	<li><a href="home.htm">Home</a></li>
    <li>Page-1</li>
    <li>Page-2</li> 
-->
	<li>Logged in as : <%=((User)session.getAttribute("user")).getUsername()%></li>
</ul>

<!-- Menu Toggle Script -->
<script>
   $("#menu-toggle").click(function(e) {
     e.preventDefault();
     $("#wrapper").toggleClass("toggled");
   });
</script>

<!-- App CSS -->
<link rel="stylesheet" href="resources/application/style/style.css" rel="stylesheet" type="text/css"/>
