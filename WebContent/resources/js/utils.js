function setAutoHeight(objId, diff) {
	var docHeight = jQuery(window).height();
	var docWidth = jQuery(window).width() - 2;

	var topPanelHeight = jQuery("#topPanel").height();
	var objHeight = docHeight - topPanelHeight - diff;

	jQuery(objId).height(objHeight);
	jQuery(objId).width(docWidth);

}

function setAutoHeightExt(diff) {
	var docHeight = jQuery(window).height();
	var docWidth = jQuery(window).width();

	var topPanelHeight = jQuery("#topPanel").height();
	var objHeight = docHeight - topPanelHeight - diff;

	if (jQuery.browser.msie && jQuery.browser.version === "7.0") {
		objHeight = objHeight + 25;
	}

	jQuery("#leftDiv").height(objHeight);
	jQuery("#extRepForm\\:leftTree").height(objHeight - 6);
	jQuery("#rightDiv").height(objHeight);

	jQuery("#leftDiv").width(docWidth * 25 / 100);
	var leftDivWidth = jQuery("#leftDiv").width();
	jQuery("#rightDiv").width(docWidth - leftDivWidth - 5);

}

function setDataTableHeight() {
	var rightDivHeight = jQuery("#rightDiv").height();

	if (jQuery.browser.msie && jQuery.browser.version === "7.0") {
		var scrollBody = jQuery("#extRepForm\\:fileDataTable").find(
				'.ui-datatable-scrollable-body');
		scrollBody.height(rightDivHeight - 121);
	} else {
		var scrollBody = jQuery("#extRepForm\\:fileDataTable .ui-datatable-scrollable-body");
		scrollBody.height(rightDivHeight - 119);
	}
}


function setDataTableHeight(objId, xObjId, diff) {
	var docHeight = jQuery(window).height();
	var xObjHeight = jQuery(xObjId).height();

	// alert(docHeight+"->" + xObjHeight);
	var objHeight = docHeight - xObjHeight - diff;

	if (jQuery.browser.msie && jQuery.browser.version === "7.0") {
		var scrollBody = jQuery(objId).find('.ui-datatable-scrollable-body');
		scrollBody.height(objHeight);
	} else {
		var scrollBody = jQuery(objId + " .ui-datatable-scrollable-body");
		scrollBody.height(objHeight);
	}
}

function handleSaveRequest(xhr, status, args, dlg) {
	if (args.validationFailed || !args.status) {
		jQuery("#" + dlg.id).effect("shake", {
			times : 3
		}, 100);
	} else {
		dlg.hide();
	}
}

