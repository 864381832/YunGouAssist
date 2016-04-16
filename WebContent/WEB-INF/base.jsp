<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String baseUrl = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
	baseUrl += request.getContextPath() + "/";

	request.setCharacterEncoding("utf-8");
%>

<base href="<%=baseUrl%>" />

<link href="images/web_title.ico" rel="icon" type="image/x-icon" />

<link href="css/artDialog/skins/default.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/artDialog/artDialog.js"></script>
<script type="text/javascript" src="js/artDialog/iframeTools.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
