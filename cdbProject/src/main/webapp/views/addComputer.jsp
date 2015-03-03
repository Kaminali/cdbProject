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
					<h1>Add Computer</h1>
					<form action="AddComputer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" name="computerName"
									id="computerName" placeholder="Computer name" required>
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="yyyy-[m]m-[d]d"
									pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
									oninvalid="setCustomValidity('date required with the format yyyy-[m]m-[d]d')"
									onchange="try{setCustomValidity('')}catch(e){}"
    								>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="yyyy-[m]m-[d]d"
									pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
									oninvalid="setCustomValidity('date required with the format yyyy-[m]m-[d]d')"
									onchange="try{setCustomValidity('')}catch(e){}"
									>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
										<option value="0">--</option>
										<c:forEach var="entry" items="${companyL}">
											<option value="${entry.getId()}">${entry.getName()}</option>
										</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Add" class="btn btn-primary">
							or <a href="Dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
					
					<c:out value="${result}"></c:out>
					
				</div>
			</div>
		</div>
	</section>
</body>
</html>