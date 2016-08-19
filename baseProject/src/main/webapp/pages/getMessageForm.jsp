<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div id="msgDiv">
<form:form id="messageForm" method="post" modelAttribute="messageForm" class="msgForm" 
		action="#">
	<h2>New Message</h2>
	<br>
	<label>To: <span>*</span></label>
	<form:input path="recipient" class="msgInput" type="text" id="receiverEmail" placeholder="E-mail of recipient" />
	<br><br>
	<label>Subject: <span>*</span></label>
	<form:input path="subject" class="msgInput" type="text" id="msgSubject" placeholder="Subject" />
	<br><br>
	<label>Message: </label>
	<form:textarea path="text" id="msgTextarea" placeholder="Message" />
	
	<button type="submit" id="messageSend">Send</button>
	<button type="button" id="messageCancel">Cancel</button>
	
	<br/>
</form:form>
</div>