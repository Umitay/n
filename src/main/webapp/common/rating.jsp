<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="rates-block">
					<div class="title dilog-box-msg">Насколько понравилось блюдо?</div>
					<div class="rating">
						<c:forEach var="i" begin="1" end="5" varStatus="loop">
							<c:choose>
								<c:when test="${loop.index < item.rating}">
					<span class="active-rate" data-rating="${loop.index}">★</span>
				</c:when>
				<c:otherwise>
		       		<span data-rating="${loop.index}">☆</span> 
		    	</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</div>