<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:import url="template/header.jsp" />

<script>
function star(starnr, rating) {
    if(starnr <= rating)
        return "&#9733";
    else
        return "&#9734";
}
</script>

<script>
function stars(id, rating) {
	document.getElementById(id).innerHTML = 
	"<span onClick=\"rate(" + id + ", 1)\">" + star(1, rating) + "</span>" +
	"<span onClick=\"rate(" + id + ", 2)\">" + star(2, rating) + "</span>" +
	"<span onClick=\"rate(" + id + ", 3)\">" + star(3, rating) + "</span>" +
	"<span onClick=\"rate(" + id + ", 4)\">" + star(4, rating) + "</span>" +
	"<span onClick=\"rate(" + id + ", 5)\">" + star(5, rating) + "</span>";
}
</script>

<script>
function ratingFor(id) {
	$.get("/profile/ratingFor?user=" + id, function(data){stars(id, data)});
}
</script>

<script>
function rate(id, rating) {
	$.get("/profile/rateUser?rate=" + id + "&stars=" + rating, function(){
		ratingFor(id);
	});
}
</script>

<h2>Visitors for your property</h2>

<p>Information about the property: <a href="/ad?id=${ad.id }">${ad.street }, ${ad.zipcode } ${ad.city }</a></p>

<div id="visitorsDiv">			
<c:choose>
	<c:when test="${empty visitors}">
		<p>This property doesn't have any scheduled visitors at the moment.
	</c:when>
	<c:otherwise>
		<table class="styledTable" id="visitors">
			<thead>
			<tr>
				<th>Name</th>
				<th>Username</th>
				<th>Profile</th>
				<th>Rating</th>
			</tr>
			</thead>
		<c:forEach var="visitor" items="${visitors}">
			<tr>
				<td>${visitor.firstName} ${visitor.lastName }</td>
				<td>${visitor.username}</td>
				<td><a href="/user?id=${visitor.id}"><button>Visit</button></a></td>
				<td>
				<div class="rating" id="${visitor.id}">
					<script>ratingFor(${visitor.id})</script>
				</div>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div>

<c:import url="template/footer.jsp" />