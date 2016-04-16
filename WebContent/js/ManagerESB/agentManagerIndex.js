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

	$('#registerTimeThId').click(function() {
		sortSubmitForm(0);
	});
	$('#expirationTimeThId').click(function() {
		sortSubmitForm(1);
	});
	$('#loginNumberThId').click(function() {
		sortSubmitForm(2);
	});
	$('#loginTimeThId').click(function() {
		sortSubmitForm(3);
	});
	$('#surplusDayThId').click(function() {
		sortSubmitForm(1);
	});
	$('#userInfoThId').click(function() {
		sortSubmitForm(4);
	});
	$('#remarksInfoThId').click(function() {
		sortSubmitForm(5);
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
