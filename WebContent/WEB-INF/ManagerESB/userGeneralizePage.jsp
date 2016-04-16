<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/ManagerESB/managerIndex.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/ManagerESB/userGeneralizePage.js"></script>
<title>智能云购后台管理</title>
</head>
<body class="body">
	<div class="tableDiv">
		<div class="selectDiv">
			<form action="managerAction_userGeneralizePage" target="_self"
				method="post" id="manageForm">
				<input id="pages" type="hidden" value="${otherData.pages}" /> <input
					id="pageNum" type="hidden" value="${otherData.pageNum}"
					name="otherData['pageNum']" /> <input id="sortType" type="hidden"
					value="${otherData.sortType}" name="otherData['sortType']" /> <input
					id="rankType" type="hidden" value="${otherData.rankType}"
					name="otherData['rankType']" />
				<%-- <input id="selectTextId"
					type="text" value="${selectText}" name="otherData['selectText']"
					title="请输入用户开通天数进行添加用户，或输入用户名或用户信息来查询" /> <input type="submit"
					value="查询" id="selectButton" /> --%>
			</form>
		</div>
		<h1>
			用户邀请表
			<lable> 总邀请数：${otherData.listSize}</lable>
		</h1>
		<table>
			<tr>
				<th>账户</th>
				<th>推广ID</th>
				<th>推广时间</th>
				<th>是否领取奖励</th>
				<th>领奖时间</th>
			</tr>
			<s:iterator var="item" value="payRecordList">
				<tr>
					<td><s:property value="#item.Userid" /></td>
					<td><s:property value="#item.RenewMoney" /></td>
					<td><s:date name="#item.RenewTime" format="yyyy-MM-dd H:mm" /></td>
					<td><s:property value="#item.IsDeal" /></td>
					<td><s:date name="#item.DealTime" format="yyyy-MM-dd H:mm" /></td>
				</tr>
			</s:iterator>
		</table>
		<div class="pageButtonDiv">
			<s:if test="otherData.pages > 1">
				<div class="upPageDiv">
					<s:if test="otherData.pageNum > 1">
						<a id="upPage" href="">上一页</a>
					</s:if>
				</div>
				<div class="aNumPageDiv" id="aNumPageDiv">
					<s:bean name="org.apache.struts2.util.Counter" id="counter">
						<s:param name="first" value="0" />
						<s:param name="last" value="otherData.pages-1" />
						<s:iterator>
							<s:if test="otherData.pageNum == current">
								<a href="" class="currentANumPage"><s:property
										value="current" /></a>
							</s:if>
							<s:else>
								<a href="" class="aNumPage"><s:property value="current" /></a>
							</s:else>
						</s:iterator>
					</s:bean>
				</div>
				<div class="downPageDiv">
					<s:if test="otherData.pageNum < otherData.pages">
						<a id="downPage" href="">下一页</a>
					</s:if>
				</div>
			</s:if>
		</div>
	</div>
</body>
</html>