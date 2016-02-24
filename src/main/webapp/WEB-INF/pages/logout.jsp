<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<spring:url value="/resources/css/mainCss.css" var="mainCss" />
	<spring:url value="/resources/js/mainJs.js" var="mainJs" />
	<spring:url value="/resources/js/jquery-1.11.2.min.js" var="jquery" />
	
	<script type="text/javascript" src="${jquery}"></script>
	<script type="text/javascript" src="${mainJs}"></script>
	<link href="${mainCss}" rel="stylesheet" />
	<title>Logout | Link4U</title>
</head>
<body>
	<div id="topwrap">
		<div id="topcontent">
			<div id="nameofsite"><b class="special">></b> <a class="homepage" href="/Link4U">Link4U</a></div>
			<div id="panelofuser">
				<a href="signup.html"><div>Sign up</div></a>
				<a href="login.html"><div>Login</div></a>
			</div>
		</div>
	</div>
	
	<div id="maincontent">
		<p>Site with logout</p>
	</div>	
	
	<div id="bottomwrap">
		<div id="bottomcontent">
			<div id="privatepolicy">Icons are from <a href="http://creativecommons.org/licenses/by-sa/3.0/">here</a></div>
			<div id="author">by Przemyslaw Paluch</div>
		</div>
	</div>
</body>
</html>