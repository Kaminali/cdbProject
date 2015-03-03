<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Dashboard"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<c:if test="${empty computerEdit}">
						<h1>Add Computer</h1>
					</c:if>
					<c:if test="${not empty computerEdit}">
						<h1>Edit Computer</h1>
					</c:if>
					<form action="AddComputer" method="POST">
						<fieldset>
						<c:if test="${not empty computerEdit}">
							<input type="text" hidden="true" id="computerId" value="${computerEdit.getId()}">
						</c:if>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="computerName"
									id="computerName" placeholder="Computer name" required
									<c:if test="${not empty computerEdit}">
										<c:out value="value=${computerEdit.getName()}" />
									</c:if>
									>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="yyyy-[m]m-[d]d"
									pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
									oninvalid="setCustomValidity('date required with the format yyyy-[m]m-[d]d')"
									onchange="try{setCustomValidity('')}catch(e){}"
									<c:if test="${not empty computerEdit}">
										<c:out value="value=${computerEdit.getIntroduced()}" />
									</c:if>
    								>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="yyyy-[m]m-[d]d"
									pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
									oninvalid="setCustomValidity('date required with the format yyyy-[m]m-[d]d')"
									onchange="try{setCustomValidity('')}catch(e){}"
									<c:if test="${not empty computerEdit}">
										<c:out value="value=${computerEdit.getDiscontinued()}" />
									</c:if>
									>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
										<option value="0">--</option>
										<c:forEach var="entry" items="${companyL}">
											<option value="${entry.getId()}" 
											<c:if test="${computerEdit.getCompanyDto().getId() == entry.getId()}">
												<c:out value="selected=selected" />
											</c:if>
											>${entry.getName()}</option>
										</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="Dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
					<c:out value="${result}" />
				</div>
			</div>
		</div>
	</section>
</body>
</html>