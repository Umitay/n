<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
       <h4 class="modal-title">Нажмите «Нравится»,<br>чтобы читать «${site_name}» в Facebook</h4>
         <div class="like-button-border">
            <div class="fb-like" data-href="https://www.facebook.com/tut.recipe" data-layout="standard" data-action="like" data-show-faces="false" data-share="false"></div>
        </div>
      </div>
      <div class="modal-footer">
      	 <button type="button" class="btn btn-default" data-dismiss="modal">Мне уже нравится «${site_name}»</button>
      </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
function getPageLang()
{
    // First try to get the 'lang' attribute from the <html> node
    var pageLanguage = getDocument().documentElement.getAttribute('lang');
    if(!pageLanguage) {
        // Look for the language in the meta tag instead
        pageLanguage = $('head').children('meta[http-equiv=Content-Language]').attr("content");
        if(!pageLanguage) {
            pageLanguage = "ru"; // Default language
        }
    }
    return pageLanguage;
}
function createCookie(name,value,days) {
    if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
    }
    else var expires = "";
    document.cookie = name+"="+value+expires+"; path=/";
}
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {
    createCookie(name,"",-1);
}

function loadPage() {
	$('#myTabs a[href="#fb"]').tab('show');
	console.log('start timeout');
	var x = readCookie('ppkcookie');
	
	if (!x) {
		setTimeout(function(){
			createCookie('ppkcookie','testcookie',7);
	       $('#myModal').modal('show');
	       
	    },10000);
	}
}
window.onload= loadPage;
</script>
<script type="text/javascript">
$( document ).ready(function() {
	console.log( "ready!" );
	 
	  $("body").on("click",".rating span", function(event) {
		  onClickAddVote(event);
	   });
	  
	});
	function onClickAddVote(event){
		var slug = "${article.slug}";
		$.post( "/rating/", { "type": "article" , "slug": slug , rating: $(event.target).data('rating') })
		 .always(function() { 
			
			 setTimeout(function(){ 
				 $(".dilog-box-msg").html("Спасибо за активность.");
		
			 }, 2000);
			});
	}

</script>
<!-- <script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
 --><%@include file="/common/bottom.jsp"%>