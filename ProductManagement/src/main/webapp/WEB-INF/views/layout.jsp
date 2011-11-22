<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta name="author" content="Luka Cvrk (www.solucija.com)" />
	<meta http-equiv="content-type" content="text/html;charset=iso-8859-2" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" type="text/css" />
	<script src="<%=request.getContextPath()%>/resources/js/jquery.js"></script>
	<script type="text/javascript"> 
	$(document).ready(function(){

		$("ul.subnav").parent().append("<span></span>"); //Only shows drop down trigger when js is enabled - Adds empty span tag after ul.subnav

		$("ul.topnav li span").click(function() { //When trigger is clicked...

			//Following events are applied to the subnav itself (moving subnav up and down)
			$(this).parent().find("ul.subnav").slideDown('fast').show(); //Drop down the subnav on click

			$(this).parent().hover(function() {
			}, function(){	
				$(this).parent().find("ul.subnav").slideUp('slow'); //When the mouse hovers out of the subnav, move it back up
			});

			//Following events are applied to the trigger (Hover events for the trigger)
			}).hover(function() { 
				$(this).addClass("subhover"); //On hover over, add class "subhover"
			}, function(){	//On Hover Out
				$(this).removeClass("subhover"); //On hover out, remove class "subhover"
		});

	});
	</script>
	<title>Petizen The Pet Shop</title>
</head>
<body>
	<div class="content">
		<div class="header_right">
			<div class="top_info">
				<div class="top_info_right">
					<p><b>You are not Logged in!</b> <a href="#">Log in</a>  or <a href="#">register</a> to start shopping!</p>					
				</div>		
			</div>
					
			
		</div>
			
		<div class="logo">
			<h1><a href="#" title="The best music on the net!">Petizen <span class="red">Pet Stop</span></a></h1>
			<p>What shall we play today?</p>
		</div>
		
		<div class="search_field">
			<form method="post" action="?">
				<p><span class="grey">Search Example:</span> <span class="search">Pedigree</span>&nbsp;&nbsp; <input type="text" name="search" class="search" /> <input type="submit" value="Search" class="button" /></p>
			</form>
		</div>
		
		<div class="newsletter">
			<p>Subscribe for Newsletter!</p>
		</div>
		<div class="bar">
			<ul class="topnav">
			
				<li><a href="#" accesskey="b">Utilities</a>
					<ul class="subnav">
					            <li><a href="#">Chain</a></li>
					            <li><a href="#">Collar</a></li>
					        </ul>
					</li>
				<li><a href="#" accesskey="a">Food</a></li>
				<li><a href="#" accesskey="a">Vet</a></li>
				<li><a href="#" accesskey="p">Shampoos</a></li>
				<li><a href="#" accesskey="g">Medicines</a></li>
			
			</ul>
		</div>
		<div class="subheader">
			<p>Pet Stop Petizen is one stop for all pet care. We are team of enthusiastic people who love pets and know pets better than any one else.</p>
		</div>
		
		<div class="left">
			<tiles:insertAttribute name="left" />
		</div>	
		<div class="right">
			<tiles:insertAttribute name="right" />
		</div>	
		<div class="footer">
			<p><a href="#">RSS Feed</a> | <a href="#">Contact</a> | <a href="#">Accessibility</a> | <a href="#">Products</a> | <a href="#">Disclaimer</a> | <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a> and <a href="http://validator.w3.org/check?uri=referer">XHTML</a><br />
			&copy; Copyright 2011 Petizen, Design: Luka Cvrk - <a href="http://www.solucija.com/" title="What's your solution?">Solucija</a></p>
		</div>
	</div>
</body>
</html>