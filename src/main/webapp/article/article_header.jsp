<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<!--[if IE 7]>
<html class="ie ie7" lang="ru-RU" prefix="og: http://ogp.me/ns#">
<![endif]-->
<!--[if IE 8]>
<html class="ie ie8" lang="ru-RU" prefix="og: http://ogp.me/ns#">
<![endif]-->
<!--[if !(IE 7) & !(IE 8)]><!-->
<html lang="ru-RU" prefix="og: http://ogp.me/ns#">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" type='text/css' media='all' >
<link rel="stylesheet"  href="/static/styles.css" type="text/css" media="all" />
<link rel="icon" href="/static/logo.png" type="image/x-icon" />
<link rel="shortcut icon" href="/static/logo.png" type="image/x-icon" />
<!-- Latest compiled and minified JavaScript -->
<script	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<title>${article.name} | Полезные рецепты</title>
<meta name="description" content="★ ${meta_description}"/>
<meta name="keywords" content="${meta_keywords}">
<link rel="publisher" href="https://plus.google.com/+Urrecipe1/posts"/>
<meta property="og:locale" content="ru_RU" />
<meta property="og:image" content="${article.thumbnailUrl}" />
<meta property="og:type" content="website" />
<meta property="og:title" content="${meta_title} | Полезные рецепты" />
<meta property="og:description" content="★ ${meta_description}" />
<meta property="og:url" content="http://www.ur-recipe.com/article/${article.slug}" />
<meta property="og:site_name" content="Полезные рецепты" />
<meta property="fb:app_id" content="1659668160945449" /> 
<meta name="twitter:card" content="summary_large_image"/>
<meta name="twitter:description" content="★ ${meta_description}"/>
<meta name="twitter:title" content="${meta_title} | Полезные рецепты"/>
<meta name="twitter:site" content="@umitay"/>
<meta name="twitter:domain" content="www.ur-recipe.com"/>
<meta name='yandex-verification' content='6952aca4a0d06474' />
<meta name="robots" value="index, follow" />
<!-- Вставьте этот тег в заголовке страницы или непосредственно перед закрывающим тегом основной части. -->
	<script src="https://apis.google.com/js/platform.js" async defer>
	  {lang: 'ru'}
	</script>
</head>
	<!-- Put this script tag to the <head> of your page -->
<script type="text/javascript" src="//vk.com/js/api/openapi.js?121"></script>

<script type="text/javascript">
  VK.init({apiId: 5170105, onlyWidgets: true});
</script>
<body>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/ru_RU/sdk.js#xfbml=1&version=v2.5&appId=1659668160945449";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
<%@include file="/common/top_nav.jsp" %>
