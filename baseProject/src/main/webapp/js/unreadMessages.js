//we pass a place: If it is "messages", the function returns the string
//for the inbox; if it is anything else, it returns the string for the header.

function unreadMessages(place) {
	$.get("/profile/unread", function(data){ 
		var message;
		if(place == "messages")
			message = "Inbox";
		else
			message = "Messages";
		if(data > 0)
			message += " (" + data + ")";
		if(place == "messages")
			$("#inbox").html(message);
		else
			$("#messageLink").html(message);
	});
}