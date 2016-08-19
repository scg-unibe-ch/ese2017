
function loadMessages(data) {
	$("#messageList table tr:gt(0)").remove();
	$.each(data, function(index, message) {
		var result = '<tr data-id="' + message.id + '" class="' + message.state + '" >';
		result += '<td>' + message.subject + '</td>';
		result += '<td>' + message.sender.email + '</td>';
		result += '<td>' + message.recipient.email + '</td>';
		result += '<td>' + message.dateSent + '</td></tr>';

		$("#messageList table").append(result);
	});
}

function prepareRows() {
	var rows = $("#messageList table tr:gt(0)");
	$(rows).hover(function() {
		$(this).children().css("background-color", "#ececec");
	}, function() {
		var color;
		if($(this).hasClass("UNREAD"))
			color = "#AFFFEE;";
		else
			color = "white";
		$(this).children().css("background-color", color);
	});
	$(rows).click(function() {
		var id = $(this).attr("data-id");
		$(this).removeClass("UNREAD");
		$.get("/profile/readMessage?id=" + id, function (data) {
			$.get("/profile/messages/getMessage?id=" + id, function(data) {
				var result = '<h2>' + data.subject + '</h2>';
				result += '<h3><b>To: </b>' + data.recipient.email + '</h3>';
				result += '<h3><b>From: </b>' + data.sender.email + '</h3>';
				result += '<h3><b>Date sent: </b>' + data.dateSent + '</h3>';
				result += '<br /><p>' + data.text + '</p>';
				$("#messageDetail").html(result);
			}, 'json');
			unreadMessages("header");
			unreadMessages("messages");
		});
	});
}

$(document).ready(function() {
	prepareRows();

	$("#inbox").click(function() {
		$.post("/profile/message/inbox", function(data) {
			loadMessages(data);
			prepareRows();
		}, 'json');
	});

	$("#sent").click(function() {
		$.post("/profile/message/sent", function(data) {
			loadMessages(data);
			prepareRows();
		}, 'json');
	});
	
	$("#newMessage").click(function(){
		$("#content").children().animate({opacity: 0.4}, 300, function(){
			$("#msgDiv").css("display", "block");
			$("#msgDiv").css("opacity", "1");
		});
	});
	
	$("#messageCancel").click(function(){
		$("#msgDiv").css("display", "none");
		$("#msgDiv").css("opacity", "0");
		$("#content").children().animate({opacity: 1}, 300);
	});
	
	
	$("#receiverEmail").focusout(function() {
		var text = $("#receiverEmail").val();
		
		$.post("/profile/messages/validateEmail", {email:text}, function(data) {
			if (data != text) {
				alert(data);
				$("#receiverEmail").val("");
			}
		});
	});
	
	$("#messageForm").submit(function (event){
		if($("#receiverEmail").val() == ""){
			event.preventDefault();
		}
	});
	
});