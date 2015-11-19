<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
       <h4 class="modal-title">Нажмите «Нравится»,<br>чтобы читать AdMe.ru в Facebook</h4>
         <div class="like-button-border">
         <fb:like data-ga-action="Subscribe" data-ga-category="FacebookArticleTop" data-event-dislike="Unsubscribe" data-event-like="Subscribe" share="false" show_faces="true" action="like" layout="button_count" href="http://facebook.com/www.adme.ru" class=" fb_iframe_widget" fb-xfbml-state="rendered" fb-iframe-plugin-query="action=like&amp;app_id=217598598309697&amp;container_width=0&amp;href=http%3A%2F%2Ffacebook.com%2Fwww.adme.ru&amp;layout=button_count&amp;locale=ru_RU&amp;sdk=joey&amp;share=false&amp;show_faces=true"><span style="vertical-align: top; width: 0px; height: 0px; overflow: hidden;"><iframe width="1000px" height="1000px" frameborder="0" name="f1019c93186c732" allowtransparency="true" allowfullscreen="true" scrolling="no" title="fb:like Facebook Social Plugin" style="border: medium none; visibility: visible; width: 0px; height: 0px;" src="http://www.facebook.com/v2.0/plugins/like.php?action=like&amp;app_id=217598598309697&amp;channel=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FTlA_zCeMkxl.js%3Fversion%3D41%23cb%3Df23db5fd0de87f2%26domain%3Dwww.adme.ru%26origin%3Dhttp%253A%252F%252Fwww.adme.ru%252Ff23d59dcc5e5186%26relation%3Dparent.parent&amp;container_width=0&amp;href=http%3A%2F%2Ffacebook.com%2Fwww.adme.ru&amp;layout=button_count&amp;locale=ru_RU&amp;sdk=joey&amp;share=false&amp;show_faces=true" class="nqruyrdrjljkkdslrbsl"></iframe></span></fb:like>
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
function loadPage() {
	
	console.log('start timeout');
	
	setTimeout(function(){
       $('#myModal').modal('show');
    },10000);
}
window.onload= loadPage;
</script>
</body>
</html>