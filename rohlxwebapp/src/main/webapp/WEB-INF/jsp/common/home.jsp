<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Rohlx Technologies</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/rohlxcustom.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">

			<div class="container banner-align">
				<div id="rohlxbanner">
					<a href="index.html" class="banner scroller" data-section="#intro">Rohlx
						Technologies</a>
				</div>
				<div class="nav-collapse collapse">
					<ul class="nav pull-right">
						<li><a href="#" class="scroller" data-section="#intro">About
								Us</a>
						</li>
						<li><a href="#" class="scroller"
							data-section="#productsservices">Sales & Services</a>
						</li>
						<li><a href="#" class="scroller" data-section="#request">Request
								Service</a>
						</li>
						<li><a href="#" class="scroller" data-section="#contact">Contact
								us</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="main-container">

		<%-- #about us --%>
		<div id="intro" class="container-fluid">

			<div class="container">
				<div class="row span6 center" id="alignImage">
					<img src="img/rohlx.jpg" />
					<h1>Making a difference !!!!</h1>
					<p class="text">Our Mission is to maximize the business success
						of our customers by providing quality software services,
						installation and maintenance support and help our customers thus,
						achieve their goals.</p>
				</div>
			</div>
			<!-- #about us -->
		</div>
		<!-- About US-->
		<!-- #products -->
		<div id="productsservices" class="container-fluid">
			<div class="container dark">
				<h1>Sales and Service</h1>
				<ul class="thumbnails">
					<li class="span3">
						<div class="thumbnail">
							<h3>Computers & Displays</h3>
							<p>Accessories and Annual Maintenance Contract</p>
						</div></li>
					<li class="span3">
						<div class="thumbnail">
							<h3>Laptops & Accessories</h3>
							<p>All brands sales and Service</p>
						</div></li>
					<li class="span3">
						<div class="thumbnail">
							<h3>Security Systems</h3>
							<p>Latest cameras</p>
						</div></li>
					<li class="span3">
						<div class="thumbnail">
							<h3>Printers</h3>
							<p>All brands sales and service</p>
						</div></li>
				</ul>
			</div>
		</div>
		<!-- End products -->
		<!-- # apply online for Service -->
		<div id="request" class="container-fluid">
			<div class="container dark">
				<h1>Service request form</h1>

				<div class="span5">
					<form action="/home" method="post">
						<div class="control-group">
							<label for="inputName" class="control-label"><span
								class="red">*</span>Name</label>
							<div class="controls">
								<input type="text" placeholder="Name" id="inputName" name="name"
									class="span4">
							</div>
						</div>
						<div class="control-group">
							<label for="inputEmail" class="control-label">Email</label>
							<div class="controls">
								<input type="text" placeholder="Email" id="inputEmail" name="email"
									class="span4">
							</div>
						</div>
						<div class="control-group">
							<label for="inputPhone" class="control-label"><span
								class="red">*</span>Phone</label>
							<div class="controls">
								<input type="text" placeholder="Phone" id="inputPhone" name="phone"
									class="span4">
							</div>
						</div>
						<div class="control-group">
							<label for="inputContact" class="control-label"><span
								class="red">*</span>Message</label>
							<div class="controls">
								<textarea class="span4" id="inputContact"
									placeholder="Please give a brief description of service needed" name="message"></textarea>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button class="btn btn-purple" type="submit">Book a
									service</button>
							</div>
						</div>
					</form>
				</div>

				<div class="span4">
					<h2>Immediate Response</h2>
					<p>We will get in touch with you within 2 hours</p>
					<address>
						<strong>Rohlx Technologies</strong><br> 6/27 - A, West
						Bazaar, <br> Anjugramam - 629401, <br> Kanyakumari
						District, TamilNadu <br> <abbr title="Phone">P:</abbr> 04652
						- 266006
					</address>
					<address>
						<strong>email</strong> <a href="mailto:#">info@rohlx.com</a>
					</address>
				</div>
			</div>
		</div>
		<!-- End  apply for service Service -->
		<!-- #Contact US -->
		<div id="contact" class="container-fluid">
			<div class="container dark" id="contactus">
				<h1>Come on get hold of us here ...</h1>
				<div id="addressinfo">
					<address>
						<a id="openmaps" href="#"> <strong>Rohlx Technologies</strong><br>
							6/27 - A, West Bazaar, <br> Anjugramam - 629401, <br>
							Kanyakumari District, TamilNadu <br>
						</a> <abbr title="Phone">Ph:</abbr> 04652 - 266006
					</address>
					<p>Store Hours: 9:00 AM to 9:00 PM.</p>
					<p>Closed on Sunday</p>
					<address>
						<strong>Email </strong><br> <a href="mailto:#">info@rohlx.com</a></br>
					</address>
					<address>
						<strong>Like us here</strong> <a
							href="https://www.facebook.com/Rohlxtechologies?fref=ts"><img
							src="img/facebook.png" />
						</a><br>
					</address>
				</div>
				<div id="map-canvas"></div>
			</div>
		</div>
		<!-- End Contact US -->
	</div>
	</div>
	</div>

	<%-- js imports --%>
	<script src="js/jquery.js"></script>
	<script src="js/rohlxcustom.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
