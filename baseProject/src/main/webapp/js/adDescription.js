$(document).ready(function() {
	
	var buttons = $("#visitList table tr button");
	
	//Makes the enquiry-button inactive after the user applied to a visit
	$(buttons).click(function() {
		var buttonText = $(this).attr("class");
		
		if (buttonText == 'thinInactiveButton') {
			return;
		}
		
		$("#content").children().animate({opacity: 0.4}, 300, function(){
			$("#confirmationDialog").css("display", "block");
			$("#confirmationDialog").css("opacity", "1");
		});
		
		var id = $(this).attr("data-id");
		
		$("#confirmationDialogSend").attr("data-id", id);
	});
	
	function reset(){
		$("#confirmationDialogSend").removeAttr("data-id");
		
		$("#confirmationDialog").css("display", "none");
		$("#confirmationDialog").css("opacity", "0");
		$("#content").children().animate({opacity: 1}, 300);
	}
	
	$("#confirmationDialogSend").click(function (){
		var id = $(this).attr("data-id");
		
		$.get("/profile/enquiries/sendEnquiryForVisit?id=" + id);
		
		
		var enquiryButton = $("#visitList table tr button[data-id='" + id + "']");
		$(enquiryButton).addClass('thinInactiveButton').removeClass('thinButton');
		$(enquiryButton).html('Enquiry sent');
		
		reset();
	});
	
	
	$("#confirmationDialogCancel").click(function (){
		reset();
	}); 
	
});
