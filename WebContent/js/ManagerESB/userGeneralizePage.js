$(function() {
	$('#upPage').click(function() {
		$('#selectTextId').val('');
		submitForm(parseInt($("#pageNum").val()) - 1);
		return false;
	});
	$('#downPage').click(function() {
		$('#selectTextId').val('');
		submitForm(parseInt($("#pageNum").val()) + 1);
		return false;
	});
	$('#aNumPageDiv a').click(function() {
		$('#selectTextId').val('');
		submitForm($(this).html());
		return false;
	});
});

function sortSubmitForm(sortType) {
	$('#selectTextId').val('');
	$('#sortType').val(sortType);
	if ($('#rankType').val() == "0") {
		$('#rankType').val(1);
	} else {
		$('#rankType').val(0);
	}
	$('#pageNum').val(1);
	$('#manageForm').submit();
}

function submitForm(pageNum) {
	$('#pageNum').val(pageNum);
	$('#manageForm').submit();
}
