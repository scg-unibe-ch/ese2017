<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<c:import url="template/header.jsp" />

<body>
<%-- profile/publicProfile not valid anymore... needs to be corrected: problem -> how to get the right user??? Maybe use a JS Script --%>
<pre><a href="/">Home</a>   &gt;   <a href="/user?id=${currentUser.id}">Public Profile</a>   &gt;   <a
			href="/profile/editProfile">Edit Profile</a>    &gt;    Profile Updated</pre>

<h1>${message}</h1>
<hr />

<c:import url="template/footer.jsp" />