$(function() {
	$("#userAccount").focus(function() {
		if ($("#userAccount").val() == "请输入用户名···") {
			$("#userAccount").val("");
		}
	});

	$("#userAccount").blur(function() {
		if ($("#userAccount").val() == "") {
			$("#userAccount").val("请输入用户名···");
		}
	});

	$("#userPassword").focus(function() {
		if ($("#userPassword").val() == "请输入密码···") {
			$("#userPassword").attr("type", "password");
			$("#userPassword").val("");
		}
	});

	$("#userPassword").blur(function() {
		if ($("#userPassword").val() == "") {
			$("#userPassword").attr("type", "text");
			$("#userPassword").val("请输入密码···");
		}
	});

});
