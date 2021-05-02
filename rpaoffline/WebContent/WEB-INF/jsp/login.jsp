<!DOCTYPE html>
<html lang="en">
<head>
	<title>RPA | Login</title>
	<!-- <meta charset="utf-8"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1">        
    <%@include file="common/headerfiles.jsp" %>      
   	<script src="resources/vendor/sha256.min.js"></script>
</head>


<body>
	<%@include file="common/headerext.jsp" %> 
	<!-- Background -->
	<div class="b-bg-image py-5" style="padding-bottom: 200px!important">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 d-flex">
					<div class="align-self-center pr-5">
						<h1 class="text-right mt-5 b-left-head">Recruitment Processing Application</h1>
					</div>
				</div>
				
				<div class="col-lg-4 b-login-sec">
					<div class="d-block px-5 pt-5 pb-4 border-bottom-0 b-login-w">
						<h2 class="b-login-head">Log In</h2>
					</div>

					<div class="">
						<form class="form" id="loginForm" name='f' action='/rpaoffline/login' method='POST' autocomplete="off" onsubmit="return beforeSubmit()">
							<div class="form-group ">
								<label for="username" class="">User Name :</label>
								<input class="form-control" type="text" id="username" name="username" autocomplete="off" required>
							</div>
							<div class="form-group">
								<label for="password" class="">Password:</label>
								<input class="form-control" type="password" id="password" name="password" autocomplete="off" required>
							</div>
						
							<div class="container" align="center">
								<span style="color: red; width: 100%;"> 
									<core:out value="${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}" />
								</span>
							</div>														
							<div class="text-center py-4">
								<input type="submit" value="Log In" class="btn btn-primary b-btn" onclick="beforeSubmit()"/>
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
								<!-- <input style='padding: 10px 50px' name="submit" type="submit" value="Login" /> -->
							</div>														
							<script>
						        function beforeSubmit() {
								    //jQuery('#password').val(sha256_digest(jQuery('#password').val()));								    
// 						            jQuery("#loginForm").submit();
									return true;
						        }						        
					        </script>														
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

	<%@include file="common/footerext.jsp" %> 
</body>
</html>


