$(function() {
	$(".deleteUser").click(function() {
		var t1 = $(this).parent().parent().find('td:eq(1)').html();
		var thisA = $(this);
		var se = confirm("当前要删除的记录:" + t1 + "\n确定要删除吗？");
		if (se == true) {
			$.post("managerAjax_deleteRenew", {
				userid : thisA.attr("value")
			}, function(data) {
				if (data == 'true') {
					thisA.parent().parent().remove();
				} else {
					art.dialog.alert('发生未知错误，请重新提交').time(2);
				}
			});
		}
		return false;
	});
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
