<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/css/jumbotron-narrow.css">
<link rel="stylesheet" type="text/css" href="/css/home.css">
<link rel="stylesheet" type="text/css" href="/css/jquery.growl.css" />
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/js/jquery.growl.js" type="text/javascript"></script>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li class="active" id="home"><a href="#">Home</a></li>
					<li id="qsLogoutBtn" onclick="logout()"><a href="#">Logout</a></li>
				</ul>
			</nav>
			<h3 class="text-muted">App.com</h3>
		</div>
		<div class="jumbotron">
			<h3>Hello ${userId}!</h3>
		</div>
		<div class="row marketing">
			<div class="col-lg-6">
				<h4>Subheading</h4>
				<p>Donec id elit non mi porta gravida at eget metus. Maecenas
					faucibus mollis interdum.</p>

				<h4>Subheading</h4>
				<p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
					Cras mattis consectetur purus sit amet fermentum.</p>
			</div>

			<div class="col-lg-6">
				<h4>Subheading</h4>
				<p>Donec id elit non mi porta gravida at eget metus. Maecenas
					faucibus mollis interdum.</p>

				<h4>Subheading</h4>
				<p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros.
					Cras mattis consectetur purus sit amet fermentum.</p>
			</div>
		</div>

		<footer class="footer">
			<p>&copy; 2016 Company Inc</p>
		</footer>

	</div>

	<script type="text/javascript">
		function logout() {
 			// assumes we are not part of SSO so just logout of local session
			window.location = "/template/api/v1/auth/logout";
		}
	</script>

</body>
</html>
