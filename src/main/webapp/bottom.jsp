<div id="facebook_modal" class="modal">
    <div class="modal-top-part">
        <p class="modal-title"> Жми «Нравится» и получай самые лучшие советы, рецепты в Фейсбуке!</p>
        <div class="like-button-border">
            <div class="fb-like" data-href="https://www.facebook.com/tut.recipe" data-layout="standard" data-action="like" data-show-faces="false" data-share="false"></div>
        </div>
    </div>
    <div class="modal-bottom-part">
        <img src="../img/facebook-like-pic.png" alt="Like pic" >
        <a href="#" onclick="$.modal.close(); return false;">Мне уже нравится «Полезные рецепты»</a>
        <div class="clear"></div>
    </div>
</div>


<script type="text/javascript">
<!--
$("img").error(function () {
	  $(this).unbind("error").attr("src", "/static/noimage.gif");
	});
//-->
function loadPage(device,ref) {
      
      setTimeout(function(){
          var page_like_or_unlike_callback = function(url, html_element) {
              $.modal.close();
          }


          FB.Event.subscribe('edge.create', function(){
              $.modal.close();
          });

          if (/*device != 'mobile' &&*/ ref != '' && getCookie('popup_displayed') == null) {
              $('#facebook_modal').modal({clickClose:false,escapeClose:false});
              createCookie('popup_displayed', 'true', 5);
          };
      },10000);
      
     /*  function parse_signed_request($signed_request) {
    	  list($encoded_sig, $payload) = explode('.', $signed_request, 2); 

    	  $secret = "appsecret"; // Use your app secret here

    	  // decode the data
    	  $sig = base64_url_decode($encoded_sig);
    	  $data = json_decode(base64_url_decode($payload), true);

    	  // confirm the signature
    	  $expected_sig = hash_hmac('sha256', $payload, $secret, $raw = true);
    	  if ($sig !== $expected_sig) {
    	    error_log('Bad Signed JSON signature!');
    	    return null;
    	  }

    	  return $data;
    	}

    	function base64_url_decode($input) {
    	  return base64_decode(strtr($input, '-_', '+/'));
    	} */
</script>
</body>
</html>