$(document).ready(function(){
	
	$(".pictureThumbnail button").click(function (){
		var adId = $(this).attr("data-ad-id");
		var pictureId = $(this).attr("data-picture-id");
		
		$.post("/profile/editAd/deletePictureFromAd", {adId:adId, pictureId:pictureId}, function(){
			var button = $(".pictureThumbnail button[data-ad-id='" + adId + "'][data-picture-id='" + pictureId + "']");			
			var div = $(button).parent();
			$(div).children().animate({opacity: 0}, 300, function(){
				$(div).remove();
			});
		});
	});
	
});