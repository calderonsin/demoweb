<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="utf-8">
      <title>View Items</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>
<body>
	<div class="container">

		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>

		<h1>All Items</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="item" items="${item}">
			    <tr>
				<td>
					${item.name}
				</td>
				<td>${item.quantity}</td>
				<td>
				  <spring:url value="/users/${user.id}/delete" var="deleteUrl" /> 
				  <spring:url value="/users/${user.id}/update" var="updateUrl" />

				  <button class="btn btn-primary" 
                                          onclick="location.href='${updateUrl}'">Update</button>
				  <button class="btn btn-danger" 
                                          onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                                </td>
			    </tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>