<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div data-example-id="togglable-tabs" class="bs-example bs-example-tabs">
	<ul role="tablist" class="nav nav-tabs" id="myTabs">
		<li class="active" role="presentation">
		    <a aria-expanded="true"
			aria-controls="fb" data-toggle="tab" role="tab" id="fb-tab"
			href="#fb">Комментарии из Facebook</a>
		</li>
		<li role="presentation" class="">
		    <a aria-controls="vk"
			data-toggle="tab" id="vk-tab" role="tab" href="#vk"
			aria-expanded="false">Комментарии из Vkontakt</a></li>
	</ul>
	<div class="tab-content" id="myTabContent">
		<div aria-labelledby="fb-tab" id="fb"
			class="tab-pane fade active in" role="tabpanel">
			<div class="fb-comments"
						data-width="750"
						data-href="${share_url}"
						data-numposts="5"></div>
		</div>
		<div aria-labelledby="vk-tab" id="vk" class="tab-pane fade"
			role="tabpanel">
			<!-- Put this div tag to the place, where the Comments block will be -->
			<div id="vk_comments"></div>
			<script type="text/javascript">
			VK.Widgets.Comments("vk_comments", {limit: 5, width: "750", attach: "*"});
			</script>
		</div>
   </div>
 </div>   
 <script type="text/javascript">
 $('#myTabs a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})
</script>