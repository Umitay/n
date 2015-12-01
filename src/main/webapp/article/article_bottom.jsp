<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
            pageLanguage = "en"; // Default language
        }
    }
    return pageLanguage;
}
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
} 
function loadPage() {
	
	console.log('start timeout');
	
	setTimeout(function(){
       $('#myModal').modal('show');
       
    },10000);
}
window.onload= loadPage;
</script>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>

</body>
</html>