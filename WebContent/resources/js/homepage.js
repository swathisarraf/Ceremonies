$(function() {

	// 6 create an instance when the DOM is ready
	$('#jstree1').jstree();
	$('#jstree2').jstree();
	$('#jstree3').jstree();
	
	$(document).ajaxStop(function(){
		if ( console && console.log ) {
 	        console.log( "home ajax stop");
 	      }
	//	$("#wait").modal("hide");
		$( "#loader" ).hide();
	});
});

function openPage(page) {
	// alert(page);
	window.location = page;
	return false;
}
