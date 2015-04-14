<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="th"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">


<link rel="stylesheet" href="js/datePicker/jquery-ui.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/datePicker/jquery-ui.js"></script>
<script src="js/datePicker/datepicker-fr.js"></script>
<script src="js/datePicker/datepicker-en.js"></script>

<link href="css/main.css" rel="stylesheet" media="screen">

<script>
	$(function() { 
		$( "#introducedFormated" ).datepicker({changeMonth: true,
			changeYear: true});
		$( "#introducedFormated" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$( "#introducedFormated" ).datepicker( "setDate", "${computerEdit.introduced}" );
		$( "#introducedFormated" ).datepicker( "option", $.datepicker.regional["${langTag}"] );
	    $( "#introducedFormated" ).datepicker('option', 'altField', "#introduced");
	    $( "#introducedFormated" ).datepicker('option', 'altFormat', "yy-mm-dd");
		
	});
	$(function() {
		$( "#discontinuedFormated" ).datepicker({changeMonth: true,
			changeYear: true});
		$( "#discontinuedFormated" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
		$( "#discontinuedFormated" ).datepicker( "setDate", "${computerEdit.discontinued}" );
		$( "#discontinuedFormated" ).datepicker( "option", $.datepicker.regional["${langTag}"] );
	    $( "#discontinuedFormated" ).datepicker('option', 'altField', "#discontinued");
	    $( "#discontinuedFormated" ).datepicker('option', 'altFormat', "yy-mm-dd");
	});
</script>

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"><spring:message code="messages.title" text="default text" /></a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<c:if test="${empty computerEdit}">
						<h1><spring:message code="messages.addComputer" text="default text" /></h1>
					</c:if>
					<c:if test="${not empty computerEdit}">
						<h1><spring:message code="messages.editComputer" text="default text" /></h1>
					</c:if>
					
					
					<th:form action="addComputer" method="POST" modelAttribute="computerDTO">
						<fieldset>
						<c:if test="${not empty computerEdit}">
							<input type="text" hidden="true" name="id" id="id" value="${computerEdit.id}">
						</c:if>
							<div class="form-group">
								<label for="computerName"><spring:message code="messages.computerName" text="default text" /></label> 
								<input
									type="text" class="form-control" name="name"
									id="name" placeholder="<spring:message code="messages.computerName" text="default text" />" 
									required
									<c:if test="${not empty computerEdit}">
										value="<c:out value="${computerEdit.name}" />"
									</c:if>
									></input>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="messages.introduced" text="default text" /></label> <input
									type="text" class="form-control" id="introducedFormated"
									name="introducedFormated"  placeholder="yyyy-[m]m-[d]d"
									pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
									oninvalid="setCustomValidity('<spring:message code="messages.dateF" text="default text" /> yyyy-[m]m-[d]d')"
									onchange="try{setCustomValidity('')}catch(e){}"
									readonly="readonly"
									<c:if test="${not empty computerEdit}">
										<c:out value="value=${computerEdit.introduced}" />
									</c:if>
    								/><input type="text" id="introduced" name="introduced" hidden="true" />
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="messages.discontinued" text="default text" /></label> <input
									type="date" class="form-control" id="discontinuedFormated"
									name="discontinuedFormated" placeholder="yyyy-[m]m-[d]d"
									pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"
									oninvalid="setCustomValidity('<spring:message code="messages.dateF" text="default text" /> yyyy-[m]m-[d]d')"
									onchange="try{setCustomValidity('')}catch(e){}"
									readonly="readonly"
										<c:if test="${not empty computerEdit}">
											<c:out value="value=${computerEdit.discontinued}" />
										</c:if>
									/><input type="text" id="discontinued" name="discontinued" hidden="true"
									/>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="messages.company" text="default text" /></label> <select
									class="form-control" id="companyId" name="companyId">
										<option value="-1">--</option>
										<c:forEach var="entry" items="${companyL}">
											<c:if test="${not empty computerEdit}">
												<c:if test="${computerEdit.getCompanyId() == entry.id}">
													<option value="${entry.id}" selected="selected" >${entry.name}</option>
												</c:if>
												<c:if test="${computerEdit.getCompanyId() != entry.getId()}">
													<option value="${entry.id}" >${entry.getName()}</option>
												</c:if>
											</c:if>
											<c:if test="${empty computerEdit}">
												<option value="${entry.id}" >${entry.name}</option>
											</c:if>
										</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<c:if test="${empty computerEdit}">
								<input type="submit" value="<spring:message code="messages.add" text="default text" />" class="btn btn-primary">
							</c:if>
							<c:if test="${not empty computerEdit}">
								<input type="submit" value="<spring:message code="messages.edit" text="default text" />" class="btn btn-primary">
							</c:if>
							or <a href="dashboard" class="btn btn-default"><spring:message code="messages.cancel" text="default text" /></a>
						</div>
					</th:form>
					
					
					<c:out value="${result}" />
				</div>
			</div>
		</div>
	</section>
</body>
</html>