<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="dilog-box-msg hidden" >
	<p></p>
</div>
<div class="dilog-box-rating hidden" >
	<div class="title">Насколько понравилось блюдо?<a class="close icon icon-close-small"></a></div>
		<div class="rating">
			<span data-rating="5" class="active">☆</span>
			<span data-rating="4" class="active">☆</span>
			<span data-rating="3">☆</span>
			<span data-rating="2">☆</span>
			<span data-rating="1">☆</span>
		</div>
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
       <h4 class="modal-title">Нажмите «Нравится»,<br>чтобы читать UR-Recipe.com в Facebook</h4>
         <div class="like-button-border">
            <div class="fb-like" data-href="https://www.facebook.com/tut.recipe" data-layout="standard" data-action="like" data-show-faces="false" data-share="false"></div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Мне уже нравится «Полезные рецепты»</button>
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
	  $("body").on("click",".all-rates a", function(event) {
		var position = $(".rates-block").position();
		$(".dilog-box-rating").css( "top", position.top );
		$(".dilog-box-rating").css( "left", position.left );
		$(".dilog-box-rating").removeClass("hidden");
		$(".dilog-box-rating").addClass("show");
      });
	  $("body").on("click",".dilog-box-rating a.close", function(event) {
			$(".dilog-box-rating").removeClass("show");
			$(".dilog-box-rating").addClass("hidden");
	   });
	  $("body").on("click",".rating span", function(event) {
		  onClickAddVote(event);
	   });
	  
	});
	function onClickAddVote(event){
		var slug = "${item.slug}";
		$.post( "/rating/", { "slug": slug , rating: $(event.target).data('rating') })
		 .always(function() { 
			 $(".dilog-box-rating").removeClass("show");
			 $(".dilog-box-rating").addClass("hidden"); 
			 
			 $(".dilog-box-msg").removeClass("hidden");
			 $(".dilog-box-msg").addClass("show");
			 setTimeout(function(){ 
				 $(".dilog-box-msg p").html("Спасибо за активность.");
				 $(".dilog-box-msg").addClass("hidden");
			 	 $(".dilog-box-msg").removeClass("show");
			 }, 3000);
			});
		
	
	
	
	}
<!--

//-->
</script>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

</body>
</html>