<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>TODO List Registration</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">

		<!-- The styles -->
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<style type="text/css">
			body {
				padding-top: 40px;
				padding-bottom: 40px;
				background-color: #f5f5f5;
			}
			
			p {color:red;}

			.form-signin {
				max-width: 300px;
				padding: 19px 29px 29px;
				margin: 0 auto 20px;
				background-color: #fff;
				border: 1px solid #e5e5e5;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
				-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
				-moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
				box-shadow: 0 1px 2px rgba(0,0,0,.05);
			}
			.form-signin .form-signin-heading,
			.form-signin .checkbox {
				margin-bottom: 10px;
			}
			.form-signin input[type="text"],
			.form-signin input[type="password"] {
				font-size: 16px;
				height: auto;
				margin-bottom: 15px;
				padding: 7px 9px;
			}

		</style>

	</head>

	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
	      <div class="navbar-inner">
	        <div class="container-fluid">
	          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="brand" href="">TODO List</a>

	        </div>
	      </div>
	    </div>
	    
		<div class="container">

			<div class="row">

				<div class="span12">
					<br>
					<h1 class="text-center" style = "font-family:new courier;color:black;font-size:34px;">Register For TODO List</h1><hr><br>
				</div>

			</div>

			<form class="form-signin" method = "post" action = "register">
				<h2 class="form-signin-heading">Register Now</h2>
				<input type="text" class="input-block-level" placeholder="User name" name = "name">
				<input type="password" class="input-block-level" placeholder="Password" name = "password">
				<input type="password" class="input-block-level" placeholder="Confirm Password" name = "password2">
				<button class="btn btn-large btn-primary" type="submit">Register</button>
				<a href = "login.jsp">Already have account?</a>
				<br>
				<p>Invalid user name/password!</p>
			</form>
			
			<div class="row">

				<div class="span4 offset8">
					<blockquote class="pull-right">
						<h3 class="text-center">Time is Money.</h3>
						<small>Benjamin Franklin</small>
					</blockquote>
				</div>
			</div>

		</div> <!-- /container -->

	</body>
</html>
