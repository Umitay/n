<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="row">
		  <div class="col-xs-3 col-md-3">
		   <div class="fb-like" data-href="http://www.ur-recipe.com/recipe/${item.slug}" data-layout="button" data-action="like"  data-share="true"></div>
		  </div>
		   <div class="col-xs-3 col-md-3">
		    <g:plus action="share"></g:plus>
		    </div>
</div>