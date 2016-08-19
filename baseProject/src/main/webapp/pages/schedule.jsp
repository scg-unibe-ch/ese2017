<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="template/header.jsp" />
<pre>
<a href="/">Home</a>   &gt;   Schedule</pre>

<h2>Your presentations</h2>

<div id="presentationsDiv">			
<c:choose>
	<c:when test="${empty presentations}">
		<p>You currently haven't scheduled any presentations.
	</c:when>
	<c:otherwise>
		<table class="styledTable" id="visits">
			<thead>
			<tr>
				<th>Address</th>
				<th>Date</th>
				<th>Time</th>
				<th>Visit Ad</th>
				<th>Visitors</th>
			</tr>
			</thead>
		<c:forEach var="presentation" items="${presentations}">
			<tr>
				<td>${presentation.ad.street}, ${presentation.ad.zipcode} ${presentation.ad.city}</td>
				<td>
					<fmt:formatDate value="${presentation.startTimestamp}" var="formattedVisitDay"
						type="date" pattern="dd.MM.yyyy" />
					${formattedVisitDay}
				</td>
				<td>	
					<fmt:formatDate value="${presentation.startTimestamp}" var="formattedStartTime"
						type="date" pattern="hh.mm" />
					<fmt:formatDate value="${presentation.endTimestamp}" var="formattedEndTime"
						type="date" pattern="hh.mm" />
					${formattedStartTime} - ${formattedEndTime}
				</td>
				<td><a href="/ad?id=${presentation.ad.id}"><button>Visit</button></a></td>
				<td><a href="/profile/visitors?visit=${presentation.id}"><button>See List</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div><br />

<h2>Your visits</h2>

<div id="visitsDiv">			
<c:choose>
	<c:when test="${empty visits}">
		<p>You currently haven't scheduled any visits.
	</c:when>
	<c:otherwise>
		<table class="styledTable" id="visits">
			<thead>
			<tr>
				<th>Address</th>
				<th>Date</th>
				<th>Time</th>
				<th>Visit Ad</th>
			</tr>
			</thead>
		<c:forEach var="visit" items="${visits}">
			<tr>
				<td>${visit.ad.street}, ${visit.ad.zipcode} ${visit.ad.city}</td>
				<td>
					<fmt:formatDate value="${visit.startTimestamp}" var="formattedVisitDay"
						type="date" pattern="dd.MM.yyyy" />
					${formattedVisitDay}
				</td>
				<td>	
					<fmt:formatDate value="${visit.startTimestamp}" var="formattedStartTime"
						type="date" pattern="hh.mm" />
					<fmt:formatDate value="${visit.endTimestamp}" var="formattedEndTime"
						type="date" pattern="hh.mm" />
					${formattedStartTime} - ${formattedEndTime}
				</td>
				<td><a href="/ad?id=${visit.ad.id}"><button>Visit</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div>

<c:import url="template/footer.jsp" />