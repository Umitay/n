<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="n_header.jsp" %>
<div class="container">
	<div class="row">
	 <div class="col-md-6">
	 	<div class="list-group">
		  <a href="#" class="list-group-item active"><b>Articles</b> </a>
		   <c:forEach items="${articles}" var="article">
						<a class="list-group-item" href="/article/e/${article.slug}"> <strong
							style="text-transform: uppercase;">${article.name} </strong></a>
			</c:forEach>
		 </div>
	 </div>
  	 <div class="col-md-6">
  	 	<div class="list-group">
		  <a href="#" class="list-group-item active"><b>Categories</b> </a>
			<c:forEach items="${categories}" var="category">
					<a class="list-group-item"  href="/category/e/${category.slug}"> <strong
									style="text-transform: uppercase;">${category.name} </strong></a>
			</c:forEach>
		 </div>
  	 </div>
	</div><!--/.row -->
	</div><!--/.container -->
</div>	
<%@include file="bottom.jsp" %>