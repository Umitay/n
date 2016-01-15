<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="rates-block">
					<div class="title dilog-box-msg">Насколько понравилось?</div>
					<div class="rating">
						<c:forEach var="i" begin="1" end="5" varStatus="loop">
							<c:choose>
								<c:when test="${(loop.end - loop.count + 1) < rating}">
					<span class="active-rate" data-rating="${loop.end - loop.count + 1}">★</span>
				</c:when>
				<c:when test="${(loop.end - loop.count + 1) == rating}">
					<span class="active-rate" data-rating="${loop.end - loop.count + 1}">✰</span>
				</c:when>
				<c:otherwise>
		       		<span data-rating="${loop.end - loop.count + 1}">☆</span> 
		    	</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
</div>