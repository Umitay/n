<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="n_header.jsp" %>
	<div class="container">
	<form action="/category/save" method="post">
		<div class="form-group">
		<label>Slug*</label>
		<input type="text" value="${category.slug}" name="slug" class="form-control">
		</div>
		<div class="form-group">
		<label>Name*</label>
		<input type="text" value="${category.name}" name="name" class="form-control">
		</div>
		<div class="form-group">
		<label>Description*</label>
		<textarea name="description" class="form-control" type="text">${category.description}</textarea>
		</div>
		<div class="form-group">
		<label>Priority</label>
			<input type="text" value="${category.priority}" name="priority" class="form-control">
		</div>
		<div class="form-group">
		<label>Parent</label>
			<input type="text" value="${category.parent}" name="parent" class="form-control">
		</div>
		<input type="submit" class="btn btn-primary"> 
		</form>
	</div><!--/.container -->

</body>
</html>