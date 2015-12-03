<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/n/header.jsp" %>
	<div class="container">
	<form action="/n/category/save" method="post">
		<div class="form-group">
		<label>Slug*</label>
		<input type="text" value="${category.slug}" name="slug" class="form-control" >
		</div>
		<div class="form-group">
		<label>Name*</label>
		<input type="text" value="${category.name}" name="name" class="form-control">
		</div>
		<div class="form-group">
		<label>Link title</label>
		<input type="text" value="${category.link_title}" name="link_title" class="form-control">
		</div>
		<div class="form-group">
		<label>Meta title</label>
		<input type="text" value="${category.meta_title}" name="meta_title" class="form-control">
		</div>
		<div class="form-group">
		<label>Meta Keywords</label>
		<textarea  name="meta_keywords" class="form-control">${category.meta_keywords}</textarea>
		</div>
		<div class="form-group">
		<label>Meta Description</label>
		<textarea name="meta_description" class="form-control">${category.meta_description}</textarea>
		</div>
		<div class="form-group">
		<label>Ads jumbotron</label>
		<textarea  name="ads_jumbotron" class="form-control">${category.ads_jumbotron}</textarea>
		</div>
		<div class="form-group">
		<label>ads_horizont1</label>
		<textarea  name="ads_horizont1" class="form-control">${category.ads_horizont1}</textarea>
		</div>
		<div class="form-group">
		<label>ads_horizont2</label>
		<textarea  name="ads_horizont2" class="form-control">${category.ads_horizont2}</textarea>
		</div>
		<div class="form-group">
		<label>Description*</label>
		<textarea id="textarea" name="description" class="form-control">${category.description}</textarea>
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