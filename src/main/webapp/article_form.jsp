<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="n_header.jsp" %>
	<div class="container">
	<form action="/article/save" method="post">
		<div class="form-group">
		<label>Slug*</label>
		<input type="text" value="${article.slug}" name="slug" class="form-control" readonly>
		</div>
		<div class="form-group">
		<label>Name*</label>
		<input type="text" value="${article.name}" name="name" class="form-control">
		</div>
		<div class="form-group">
		<label>Active*</label>
		<select name="active" class="form-control">
			<option value="true" ${article.active ? 'selected' : ''} >Yes</option>
			<option value="false" ${!article.active ? 'selected' : ''}>No</option>
		</select>
		</div>
		<div class="form-group">
				<label>Image</label>
				<input type="text" value="${article.thumbnailUrl}" name="thumbnailUrl" class="form-control">
		</div>
		<div class="form-group">
				<label>Link title</label>
				<input type="text" value="${article.link_title}" name="link_title" class="form-control">
				</div>
				<div class="form-group">
				<label>Image alt</label>
				<input type="text" value="${article.alt}" name="alt" class="form-control">
				</div>
				<div class="form-group">
				<label>Meta title</label>
				<input type="text" value="${article.meta_title}" name="meta_title" class="form-control">
				</div>
				<div class="form-group">
				<label>Meta Keywords</label>
				<input type="text" value="${article.meta_keywords}" name="meta_keywords" class="form-control">
				</div>
				<div class="form-group">
				<label>Meta Description</label>
				<input type="text" value="${article.meta_description}" name="meta_description" class="form-control">
				</div>
		<div class="form-group">
		<label>About*</label>
		<textarea style="height: 200px;" name="about" class="form-control" type="text">${article.about}</textarea>
		</div>
		
		<div class="form-group">
		<label>Description*</label>
		<textarea style="height: 700px;" name="description" class="form-control" type="text">${article.description}</textarea>
		</div>
		
		<input type="submit" class="btn btn-primary"> 
		</form>
	</div><!--/.container -->

</body>
</html>