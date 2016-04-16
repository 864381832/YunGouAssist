$(function() {
	$("#addNum").blur(function() {
		if ($("#addNum").val() == '') {
			$("#addNum").val('请输入新增数量');
		}
	});

	$("#addNum").focus(function() {
		if ($("#addNum").val() == "请输入新增数量") {
			$("#addNum").val('');
		}
	});

	$("#addUser").click(function() {
		var t = prompt("请输入新增用户使用天数", "1");
		if (t != null && t != "") {
			if (t.length == 0) {
				art.dialog.alert('输入不能为空').time(2);
				return false;
			}
			var reg = /^[-+]?\d*$/;
			if (!reg.test(t)) {
				art.dialog.alert('请在输入框中填写续费的天数，请输入整数').time(2);
				return false;
			}
			var t2 = prompt("请输入备注信息：", "");
			if (t2 != null) {
				$.post("managerAjax_addUser", {
					info : "",
					remarksInfo : t2,
					time : t
				}, function(data) {
					if (data != 'null') {
						art.dialog({
							title : '添加用户成功',
							content : data
						});
					} else {
						art.dialog.alert('发生未知错误，请重新提交').time(2);
					}
				});
			}
		}
		$('#selectTextId').val('');
		return false;
	});

	$(".deleteUser").click(function() {
		var t1 = $(this).parent().parent().find('td:eq(1)').html();
		var thisA = $(this);
		var se = confirm("当前要删除的用户:" + t1 + "\n确定要删除吗？");
		if (se == true) {
			$.post("managerAjax_deleteUser", {
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
					$.post("managerAjax_updateUserId", {
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
			$.post("managerAjax_updateUserPassword", {
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

	$(".userInfoIdTd").click(function() {
		var t1 = $(this).parent().find('td:eq(1)').html();
		var thisA = $(this);
		var t = prompt("当前修改的账户为：" + t1 + "\n请输入用户信息：", thisA.html());
		if (t != null && t != "") {
			$.post("managerAjax_updateUserInfo", {
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
			$.post("managerAjax_updateRemarksInfo", {
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

	$(".addUsingTime")
			.click(
					function() {
						var t1 = $(this).parent().parent().find('td:eq(1)')
								.html();
						var t2 = $(this).parent().parent().find('td:eq(7)');
						var thisA = $(this);
						var t = prompt("当前续费用户：" + t1 + "\n请输入续费天数", "31")
						if (t != null && t != "") {
							if (t.length == 0) {
								art.dialog.alert('输入不能为空').time(2);
								return false;
							}
							var reg = /^[-+]?\d*$/;
							if (!reg.test(t)) {
								art.dialog.alert('请在输入框中填写续费的天数，请输入整数').time(2);
								return false;
							}
							var tt = prompt("当前续费用户：" + t1
									+ "\n请输入续费金额,如果添加试用请输入0", "59")
							if (tt != null && t2 != "") {
								if (tt.length == 0) {
									art.dialog.alert('输入不能为空').time(2);
									return false;
								}
								$
										.post(
												"managerAjax_updateUsingTime",
												{
													userid : t1,
													time : t,
													renewMoney : tt
												},
												function(data) {
													if (data == 'true') {
														if (t2.html() == "已到期") {
															t2.html(t);
														} else {
															var d = parseInt(t)
																	+ parseInt(t2
																			.html());
															t2.html(d);
														}
														thisA
																.html("续费/"
																		+ (parseInt(thisA
																				.html()
																				.split(
																						"/")[1]) + parseInt(t)));
													} else {
														art.dialog.alert(
																'发生未知错误，请重新提交')
																.time(2);
													}
												});
							}
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
