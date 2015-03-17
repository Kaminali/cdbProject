<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>

<%@ taglib tagdir="/WEB-INF/tags/" prefix="page"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"><spring:message code="messages.title" text="default text" /></a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<!-- 121 Computers found -->
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="<spring:message code="messages.search" text="default text" />" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="messages.filter" text="default text" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="messages.add" text="default text" />
						<spring:message code="messages.computer" text="default text" /></a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="messages.edit" text="default text" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="messages.computerName" text="default text" /></th>
						<th><spring:message code="messages.introduced" text="default text" /></th>
						<th><spring:message code="messages.discontinued" text="default text" /></th>
						<th><spring:message code="messages.company" text="default text" /></th>

					</tr>
				</thead>
				<tbody id="results">
					<c:forEach var="entry" items="${resultatC}">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${entry.getId()}"></td>
							<td><a href="addComputer?id=${entry.getId()}" onclick="">${entry.getName()}</a></td>
							<td>${entry.getIntroduced()}</td>
							<td>${entry.getDiscontinued()}</td>
							<td>${entry.getCompanyDto().getName()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>


	<page:page page="${pagination}" />
	
	<script type="text/javascript">
	  var strings = new Array();
	  strings['messages.view'] = "<spring:message code='messages.view' javaScriptEscape='true' />";
	  strings['messages.edit'] = "<spring:message code='messages.edit' javaScriptEscape='true' />";
	</script>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>