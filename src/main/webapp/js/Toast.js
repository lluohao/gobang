function toCenter(id) {
	/**
	 * 屏幕长宽
	 */
	var hei = window.innerHeight;
	/**
	 * div长宽
	 */
	var divHei = $(id).height() + parseInt($(id).css("padding-top").replace("px", "")) + parseInt($(id).css("padding-bottom").replace("px", ""))

	console.log((hei - divHei) / 2 + "px")


	$(id).css("top", (hei - divHei) / 2 + "px")
}

$(document).ready(function() {
	toCenter("#toast");
})