
function randomcode_refresh() {
	var dt = new Date();
	var time = dt.valueOf();
	$("#randomcode_img").attr("src", "/ecm/page/validatecode/" + time + ".jpg");
}