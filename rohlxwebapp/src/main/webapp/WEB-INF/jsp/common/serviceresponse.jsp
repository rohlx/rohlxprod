<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<body>
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">

			<div class="container banner-align">
				<div id="rohlxbanner">
					<a href="index.html" class="banner scroller" data-section="#intro">Rohlx
						Technologies</a>
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
					<tiles:insertAttribute name="content" />
					<p class="text">Please close the browser window</p>
				</div>
			</div>
			<!-- #about us -->
		</div>
	</div>
</body>



