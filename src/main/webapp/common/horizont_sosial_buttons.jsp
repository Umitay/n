<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
Поделиться
<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
	<div class="col-xs-2 col-md-2">
		<!-- Your send button code -->
		<div class="g-plusone" data-size="medium"></div>
	</div>

	<div class="col-xs-3 col-md-3">
		<div id="ok_shareWidget"></div>
		<script>
		!function (d, id, did, st) {
		  var js = d.createElement("script");
		  js.src = "https://connect.ok.ru/connect.js";
		  js.onload = js.onreadystatechange = function () {
		  if (!this.readyState || this.readyState == "loaded" || this.readyState == "complete") {
		    if (!this.executed) {
		      this.executed = true;
		      setTimeout(function () {
		        OK.CONNECT.insertShareWidget(id,did,st);
		      }, 0);
		    }
		  }};
		  d.documentElement.appendChild(js);
		}(document,"ok_shareWidget","http://www.ur-recipe.com/","{width:30,height:35,st:'rounded',sz:30,nt:1,nc:1}");
		</script>
	</div>
	<div class="col-xs-2 col-md-2">
		<a href="https://twitter.com/share" class="twitter-share-button"
			data-via="www.ur-recipe.com">Tweet</a>
	</div>
</div>
<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
	<div class="col-xs-3 col-md-3">
		<div class="fb-share-button" data-href="${share_url}" data-layout="box_count"></div>
	</div>
	<div class="col-xs-3 col-md-3">
		<!-- Put this div tag to the place, where the Like block will be -->
		<div id="vk_like"></div>
		<script type="text/javascript">
		VK.Widgets.Like("vk_like", {type: "vertical"});
		</script>
	</div>
	<div class="col-xs-3 col-md-3">
			<a target="_blank" class="mrc__plugin_uber_like_button" href="http://connect.mail.ru/share?url=${share_url}" data-mrc-config="{'nc' : '1', 'nt' : '1', 'cm' : '1', 'sz' : '30', 'st' : '1', 'tp' : 'mm'}">Нравится mail.ru</a>
			<script src="https:/connect.mail.ru/js/loader.js" type="text/javascript" charset="UTF-8"></script>
	</div>
</div>
