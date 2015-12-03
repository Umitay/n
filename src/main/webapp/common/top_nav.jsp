<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Меню</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" rel="home" href="/"><span class="glyphicon glyphicon-home"  aria-hidden="true"> Рецепты </span></a>
    </div>
     <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        
         <c:if test="${!empty categories}">
		    <c:forEach items="${categories}" var="category">
		    <c:if test="${category.slug != 'hp' && category.slug != 'articles'}">
		       <li><a class="capitalize"  
		       href="/category/${category.slug}">${category.name}</a>
		       </li>
		       </c:if>
		    </c:forEach>
		    </c:if>
 			<li><a class="capitalize"  
		       href="/article/list">Интересно и Полезно</a>
		     </li>
      </ul>
     </div><!-- /.navbar-collapse -->
  </div><!-- /.container -->
</nav>