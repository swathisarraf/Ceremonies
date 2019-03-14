function onReady() {
	resize();
	setHeader();
	setHeader2();
}

function resize() {
	setAutoHeight();
	setAutoWidth();
	setAutoWidth2();
}

function setAutoHeight() {
	var docHeight = jQuery(window).height();
	var topPanelHeight = jQuery("#topPanel").height();

	var height = docHeight - topPanelHeight - 106;
	jQuery("#lineDiv").height(height);
	jQuery("#lineDiv2").height(height);
}

function setAutoWidth() {
	var docWidth = jQuery(window).width() - 2;
	jQuery("#lineDiv").width(docWidth);

	var lineDivHeight = jQuery("#lineDiv").height();
	var lineTableHeight = jQuery("#lineDiv table").height();

	if (lineDivHeight > lineTableHeight) {
		jQuery("#headerDiv").width(docWidth);
		docWidth = docWidth - 1;
	} else {
		jQuery("#headerDiv").width(docWidth - 17);
		docWidth = docWidth - 18;
	}

	var lineTableWidth = jQuery("#lineDiv table").width();
	if (lineTableWidth < docWidth) {
		jQuery("#lineDiv table").width(docWidth);
		jQuery("#headerDiv table").width(docWidth);
	}
}

function setAutoWidth2() {
	var docWidth = jQuery(window).width() - 2;
	jQuery("#lineDiv2").width(docWidth);

	var lineDivHeight = jQuery("#lineDiv2").height();
	var lineTableHeight = jQuery("#lineDiv2 table").height();

	if (lineDivHeight > lineTableHeight) {
		jQuery("#headerDiv2").width(docWidth);
		docWidth = docWidth - 1;
	} else {
		jQuery("#headerDiv2").width(docWidth - 17);
		docWidth = docWidth - 18;
	}

	var lineTableWidth = jQuery("#lineDiv2 table").width();
	if (lineTableWidth < docWidth) {
		jQuery("#lineDiv2 table").width(docWidth);
		jQuery("#headerDiv2 table").width(docWidth);
	}
}

function setHeader() {
	var lineTd = jQuery("#lineDiv table tr:first td div");
	var headerTd = jQuery("#headerDiv table tr:first td div");

	var tdWidth = [];
	for (var i = 0; i < lineTd.length; i++) {
		tdWidth[i] = jQuery(lineTd[i]).width();
	}
	jQuery("#lineDiv table tr:last").hide();

	for (var i = 0; i < tdWidth.length; i++) {
		jQuery(headerTd[i]).width(tdWidth[i]);
		jQuery(lineTd[i]).width(tdWidth[i]);
	}
}

function setHeader2() {
	var lineTd = jQuery("#lineDiv2 table tr:first td div");
	var headerTd = jQuery("#headerDiv2 table tr:first td div");

	var tdWidth = [];
	for (var i = 0; i < lineTd.length; i++) {
		tdWidth[i] = jQuery(lineTd[i]).width();
	}
	jQuery("#lineDiv2 table tr:last").hide();

	for (var i = 0; i < tdWidth.length; i++) {
		jQuery(headerTd[i]).width(tdWidth[i]);
		jQuery(lineTd[i]).width(tdWidth[i]);
	}
}

function divScroll() {
	var lineDivLeft = jQuery("#lineDiv").scrollLeft();
	jQuery("#headerDiv").scrollLeft(lineDivLeft);
}

function divScroll2() {
	var lineDivLeft = jQuery("#lineDiv2").scrollLeft();
	jQuery("#headerDiv2").scrollLeft(lineDivLeft);
}

function showLoadingStatus() {
	document.getElementById('fmrRptForm:MAX_REC_DOWNLOAD').style.display = 'none';
	document.getElementById('LOADING').innerHTML = '<h4>Your file will be loaded in the new window</h4><br/><h4>You will also get notification by email when the file is ready.</h4><br/><h4>Please be patient.</h4>';
}

function showLoadingStatustxt() {
	document.getElementById('fmrRptForm:MAX_REC_POPUP').style.display = 'none';
	document.getElementById('LOADING').innerHTML = '<h4>Your file will be loaded in the new window</h4><br/><h4>You will also get notification by email when the file is ready.</h4> <br/><h4>Please be patient.</h4>';
}

function checkDrillDown() {
	var ele2 = document.getElementById('fmrRptForm:drillID').value;
	if (ele2 === '-99') {
		alert('Drill Down By need to be selected');// NOSONAR
		return false;
	}

}

function checkDownLoad() {
	var popup = document.getElementById('fmrRptForm:MAX_REC_POPUP_VAL');
	var down = document.getElementById('fmrRptForm:MAX_REC_DOWNLOAD_VAL');

	if (popup !== null || down !== null) {
		history.go(-1);
		return false;
	}
}

function checkSearch() {
	var ele3 = document.getElementById('fmrRptForm:serchId').value;
	var seleSercVal = document.getElementById('fmrRptForm:serchVal').value;

	if (ele3 === '9999999999999' && seleSercVal.length === 0) {
		alert('<Search By> and <Search Value> need to be entered.');// NOSONAR
		return false;
	} else if (ele3 === '9999999999999' && seleSercVal.length > 0) {
		alert('<Search By> need to be entered.');// NOSONAR
		return false;
	} else if (ele3 !== '9999999999999' && seleSercVal.length === 0) {
		alert('<Search Value> need to be entered.');// NOSONAR
		return false;
	}
}

function showLoadingStatusLoadExp() {
	jQuery("#largeFile").css('display', 'none');
	jQuery("#LOADING")
			.html(
					"<h4>Your file will be loaded in the new window</h4> <br/> <h4>Please be patient.</h4>");
}

function handlePrintRequest(filename) {
	window
			.open(filename, "print",
					"height=600px,width=1000px,left=100px, top=100px,titlebar=yes,resizable=yes");
}