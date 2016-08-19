function showPictures() {
	$.post('/profile/placeAd/getUploadedPictures', function(data) {
		$("#uploaded-pictures tr:gt(0)").remove();
		$.each(data, function(index, picture) {
			appendPictureRow(picture.name, picture.size, picture.url);
		});
	});
}

function appendPictureRow(name, size, url) {
	// var sizeText = (size / 1024).toFixed(2) + ' KB';
	$("#uploaded-pictures")
			.append(
					'<tr><td>' + name + '</td><td>' + size
							+ '</td><td data-delete-url="' + url
							+ '">Delete</td></tr>');
}

$(function() {
	$('#field-pictures').fileupload({
		url : '/profile/placeAd/uploadPictures',
		dataType : 'json',
		acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
		done : function(e, data) {
			showPictures();
		}
	});

	showPictures();

	// attach the picture delete handler to the table cells
	$("table").on("click", "td[data-delete-url]", function(event) {
		var deleteUrl = $(this).attr('data-delete-url');
		$.post('/profile/placeAd/deletePicture', {url : deleteUrl}, function() {
			showPictures();
		});
	});
});
