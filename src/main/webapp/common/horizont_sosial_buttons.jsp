<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
	<div class="col-xs-2 col-md-2">
		<!-- Your like button code -->
		<div class="fb-like"
			data-href="${share_url}"
			data-layout="button" data-action="like" data-show-faces="false">
		</div>
	</div>
	<div class="col-xs-2 col-md-2">
		<!-- Your share button code -->
		<div class="fb-share-button"
			data-href="${share_url}"
			data-layout="button"></div>
	</div>
	<div class="col-xs-2 col-md-2">
		<!-- Your send button code -->
		<div class="fb-send"
			data-href="${share_url}"
			data-layout="button"></div>
	</div>
	<div class="col-xs-2 col-md-2">
		<!-- Your send button code -->
			<div class="g-plusone" data-size="medium"></div>
	</div>
	<div class="col-xs-2 col-md-2">
		<a href="https://twitter.com/share" class="twitter-share-button" data-via="Полезные рецепты">Tweet</a>
	</div>
</div>