function getVisiblePicture() {
	var visiblePicture = $("#images img").filter(function() {
		return $(this).hasClass('visible');
	});
	return visiblePicture;
}

$(document).ready(function() {

	// denote visible picture
	$("#images img").first().addClass('visible');

	$("#left-arrow img").click(function() {
		var current = $(getVisiblePicture());
		var previous = $(current).prev();
		if ($(previous).size() == 0) {
			// was the first picture
			previous = $("#images img").last();
		}
		$(current).removeClass('visible');
		$(previous).addClass('visible');

		$(current).fadeOut();
		$(previous).fadeIn();
	});

	$("#right-arrow img").click(function() {
		var current = $(getVisiblePicture());
		var next = $(current).next();
		if ($(next).size() == 0) {
			// was the last picture
			next = $("#images img").first();
		}

		$(current).removeClass('visible');
		$(next).addClass('visible');

		$(current).fadeOut();
		$(next).fadeIn();
	});
});