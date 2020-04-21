<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Add an item</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <button class="btn btn-primary"  onclick="location.href='/listitems'">View Items</button>
    <button class="btn btn-primary"  onclick="location.href='/aggregateitem'">Aggregate Items</button>

</head>
<div class="container">
	<spring:url value="/aggregateitem" var="itemActionUrl" />

	<form:form class="form-horizontal" method="post" 
                modelAttribute="itemForm" action="${itemActionUrl}">
		<form:hidden path="id" />

		<spring:bind path="name">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<form:input path="name" type="text" class="form-control" 
                                id="name" placeholder="Name" />
				<form:errors path="name" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		<spring:bind path="quantity">
		  <div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Quantity</label>
			<div class="col-sm-10">
				<form:input path="quantity" type="number" class="form-control"
				id="quantity" placeholder="quantity"/>
				<form:errors path="quantity" class="control-label" />
			</div>
		  </div>
		</spring:bind>
		
		<div class="form-group">
		  <div class="col-sm-offset-2 col-sm-10">
			     <button type="submit" class="btn-lg btn-primary pull-right">Add/Update
                             </button>			 
		  </div>
		</div>
	</form:form>

</div>
</body>
</html>