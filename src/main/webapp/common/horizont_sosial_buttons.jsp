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
<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
	<div class="col-xs-2 col-md-2">
			<!-- Put this div tag to the place, where the Like block will be -->
		<div id="vk_like"></div>
		<script type="text/javascript">
		VK.Widgets.Like("vk_like", {type: "mini", verb: 1});
		</script>
	</div>
	<div class="col-xs-2 col-md-2">
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
}(document,"ok_shareWidget","http://www.ur-recipe.com","{width:170,height:30,st:'rounded',sz:20,ck:3}");
</script>

	</div>
	<div class="col-xs-3 col-md-3">
		<a target="_blank" class="mrc__plugiuber_like_button" href="http://connect.mail.ru/share" data-mrc-config="{'cm' : '1', 'sz' : '20', 'st' : '2', 'tp' : 'mm'}">Нравится</a>
		<script src="https:/connect.mail.ru/js/loader.js" type="text/javascript" charset="UTF-8"></script>
	</div>
</div> 