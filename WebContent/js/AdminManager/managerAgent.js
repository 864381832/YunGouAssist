$(function() {
	$("#addUser").click(function() {
		var t = prompt("请输入代理账号：", "");
		if (t != null) {
			var t2 = prompt("请输入代理密码：", "");
			if (t2 != null) {
				var t3 = prompt("请输入备注信息：", "");
				if (t3 != null) {
					$.post("adminAjax_addUser", {
						userid : t,
						info : t2,
						remarksInfo : t3
					}, function(data) {
						if (data == 'true') {
							art.dialog({
								title : '添加代理成功',
							});
						} else {
							art.dialog.alert('发生未知错误，请重新提交').time(2);
						}
					});
				}
			}
		}
		return false;
	});

	$(".deleteUser").click(function() {
		var t1 = $(this).parent().parent().find('td:eq(1)').html();
		var thisA = $(this);
		var se = confirm("当前要删除的用户:" + t1 + "\n确定要删除吗？");
		if (se == true) {
			$.post("adminAjax_deleteUser", {
				userid : t1
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

	$(".userIdTd").click(
			function() {
				var thisA = $(this);
				var t = prompt("当前修改的账户为：" + thisA.html() + "\n请输入账户信息：", thisA
						.html());
				if (t != null && t != "") {
					$.post("adminAjax_updateUserId", {
						userid : thisA.html(),
						info : t
					}, function(data) {
						if (data == 'true') {
							thisA.html(t);
						} else {
							art.dialog.alert('发生未知错误，请重新提交').time(2);
						}
					});
				}
				return false;
			});

	$(".passwordIdTd").click(function() {
		var t1 = $(this).parent().find('td:eq(1)').html();
		var thisA = $(this);
		var t = prompt("当前修改的账户为：" + t1 + "\n请输入账户密码：", thisA.html());
		if (t != null && t != "") {
			$.post("adminAjax_updateUserPassword", {
				userid : t1,
				info : t
			}, function(data) {
				if (data == 'true') {
					thisA.html(t);
				} else {
					art.dialog.alert('发生未知错误，请重新提交').time(2);
				}
			});
		}
		return false;
	});

	$(".remarksInfoIdTd").click(function() {
		var t1 = $(this).parent().find('td:eq(1)').html();
		var thisA = $(this);
		var t = prompt("当前修改的账户为：" + t1 + "\n请输入备注信息：", thisA.html());
		if (t != null && t != "") {
			$.post("adminAjax_updateRemarksInfo", {
				userid : t1,
				info : t
			}, function(data) {
				if (data == 'true') {
					thisA.html(t);
				} else {
					art.dialog.alert('发生未知错误，请重新提交').time(2);
				}
			});
		}
		return false;
	});

	$(".payIdTd").click(function() {
		var t1 = $(this).parent().find('td:eq(1)').html();
		var thisA = $(this);
		var se = confirm("当前要打款的账户为:" + t1 + "\n确定要打款吗？");
		if (se == true) {
			$.post("adminAjax_payMoney", {
				userid : t1,
			}, function(data) {
				if (data == 'true') {
					art.dialog({
						title : '打款成功，刷新页面可显示',
					});
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
