<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<spring:url value="/resources/css/mainCss.css" var="mainCss" />
	<spring:url value="/resources/js/mainJs.js" var="mainJs" />
	<spring:url value="/resources/js/jquery-1.11.2.min.js" var="jquery" />
	
	<script type="text/javascript" src="${jquery}"></script>
	<script type="text/javascript" src="${mainJs}"></script>
	<link href="${mainCss}" rel="stylesheet" />
	<title>Homepage | Link4U</title>
</head>
<body>
	<div id="topwrap">
		<div id="topcontent">
			<div id="nameofsite"><b class="special">></b> <a class="homepage" href="/Link4U">Link4U</a></div>
			<div id="panelofuser">
				<a href="logout.html"><div>Logout</div></a>
				<a href="account.html"><div>Edit account</div></a>
			</div>
		</div>
	</div>
	
	<div id="maincontent">
		<p>Hello, ${user.username}!</p>
		<div id="status"></div>
		<div id="genres">
			<c:forEach items="${genres}" var="genre">
			    <div class="genre genre-${genre.id}-${user.id}">
				    <div class="name"><strong>${genre.name}</strong>
						<div class="settings">
							<a action="edit"><img class="edit" title="Edit genre" src="/Link4U/resources/images/edit.png" /></a>
							<a action="delete"><img class="delete" title="Delete genre" src="/Link4U/resources/images/delete.png" /></a>
						</div>
					</div>
				    <c:forEach items="${links}" var="link">
					    <c:if test="${genre.id == link.idGenre}"> 
							<div class="link link-${link.id}-${genre.id}-${user.id}">
								<a class="address" href="${link.address}" title="${link.address}">link</a>
								<div class="description">${link.description}</div>
								<div class="settings">
									<a action="edit"><img class="edit" title="Edit link" src="/Link4U/resources/images/edit.png" /></a>
									<a action="delete"><img class="delete" title="Delete link" src="/Link4U/resources/images/delete.png" /></a>
								</div>
							</div>
						</c:if> 
				    </c:forEach>
					<div class="addcontent addcontent-${genre.id}-${user.id}">
						<img class="add" title="Add link" src="/Link4U/resources/images/add.png" />
						<div class="addlink"></div>
					</div>
			    </div>
			</c:forEach>
			<div class="addcontent addcontent-${user.id}">
				<img class="add" title="Add genre" src="/Link4U/resources/images/add.png" />
				<div class="addgenre"></div>
			</div>
		</div>
	</div>
	
	<div id="bottomwrap">
		<div id="bottomcontent">
			<div id="privatepolicy">Icons are from <a href="http://creativecommons.org/licenses/by-sa/3.0/">here</a></div>
			<div id="author">by Przemyslaw Paluch</div>
		</div>
	</div>
</body>
</html>