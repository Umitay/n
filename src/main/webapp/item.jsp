<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="header.jsp" %>
<div class="container">
	<div class="row">	
	<div class="col-xs-12 col-sm-8 col-md-8">
	 <div id="item" itemtype="http://schema.org/Recipe" itemscope="">
		<meta itemprop="recipeCategory" content="Овощи и гарниры">
		<meta itemprop="description" content="Легкое,но сытное блюдо из доступных продуктов.">
		<meta itemprop="dateModified" content="2015-07-1">
		<div class="page-header"><h1 itemprop="name">${item.name}</h1></div>
		
		<!-- row -->		 
		<div class="row">
		  <div class="col-xs-6 col-md-4">
		  <div class="thumbnail" id="output_field" style="height: 180px; background-color:#F1FCEC;" itemprop="image"> 
		     <img width="230" height="230" src="${item.thumbnailUrl}" />	</div>
		<script src="https://apis.google.com/js/plusone.js"></script>
		<g:plus action="share"></g:plus>
		  </div>
		  <div class="col-xs-6 col-md-8">
		 	<div>${item.about}</div>
		 	<c:if test="${!empty item_categories}">
			 	<div>Категорий: 
				 	<c:forEach items="${item_categories}" var="category">
				 		<a href="http://ur-recipe.com/category/${category.slug}">${category.name}</a>&nbsp;
					</c:forEach>
				</div>
			</c:if>
			<div>Опубликовано:  <time datetime="2015-07-1" itemprop="datePublished">1.07.2015</time></div>
		 	<div itemprop="author" itemscope="" itemtype="http://schema.org/Person">
		 	Автор:  		<a href="//plus.google.com/u/0/110204310495545191121"><span itemprop="name">Умитай Турдыкулова</span></a>
     		</div>
			
		 </div>
		</div>
		<!-- / row -->
		<div class="bg-warning ads"></div>
		<!--  row -->
		<div class="row">
				<div class="col-xs-6 col-md-8">
					<div>
						<h2>Как приготовить</h2>
						<div itemprop="recipeInstructions">
						<p>Кабачки моем,натираем на крупной тёрке и отжимаем ,чтобы удалить лишнюю жидкость.</p>
<p>Куриное филе отвариваем заранее,остужаем и нарезаем небольшими кубиками.</p>
<p>Творог протираем через сито,или разминаем вилкой,добавляем в него мелко нарезанный укроп.</p>
<p>Сыр натираем на крупной тёрке.</p>
<p>Смазываем противень оливковым или любым другим растительным маслом.</p>
<p>Яйца немного размешать,добавить сметану и повторно взбить.</p>
<p>На противень выкладываем половину кабачков,следующий слой- куриное филе.</p>
<p>Поверх филе выкладываем творог.</p>
<p>Немного посолить и поперчить,если у вас есть любимые специи-добавьте,от этого ваше блюдо будет только вкуснее.</p>
<p>Последним слоем выкладываем оставшиеся кабачки.</p>
<p>Заливаем сметанной заправкой, в которую можно выдавить немного чеснока через пресс.</p>
<p>Обильно посыпаем тёртым сыром.</p>
<p>Запекаем 30-40 минут,при температуре 180 градусов,до появления аппетитной,румяной корочки.</p>
<p>Даём немного остыть, при подаче посыпать свежей зеленью.</p>
<p>Запеканку можно кушать,как в горячем,так и в холодном виде. Вкус неизменно изумительный.</p>
						</div>
					</div>
				</div>
				  <div class="col-xs-6 col-md-4">
					<div itemprop="ingredients">
						<h2>Ингредиенты</h2>
						 							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Кабачки средних размеров :</div>
								      <div class="col-xs-6 col-md-5"> 3 шт.
 </div>
								   </div> 
						  							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Куриное филе:</div>
								      <div class="col-xs-6 col-md-5">300 гр </div>
								   </div> 
						  							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Творог нежирный:</div>
								      <div class="col-xs-6 col-md-5">300 гр </div>
								   </div> 
						  							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Яйца:</div>
								      <div class="col-xs-6 col-md-5">3 шт. </div>
								   </div> 
						  							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Сметана 15%:</div>
								      <div class="col-xs-6 col-md-5"> 300 гр </div>
								   </div> 
						  							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Соль,перец,специи, укроп :</div>
								      <div class="col-xs-6 col-md-5"> по вкусу </div>
								   </div> 
						  							      <div class="row" itemprop="recipeIngredient">
								      <div class="col-xs-6 col-md-7">Оливковое масло:</div>
								      <div class="col-xs-6 col-md-5"> 5 гр </div>
								   </div> 
						  						
					</div>
				</div>
			
		</div>
		<!-- / row -->
	</div>
	<!-- /itemscope  -->
	<nav class="navigation post-navigation" role="navigation">
		<h1 class="screen-reader-text">Навигация по записям</h1>
		<div class="nav-links">
			<a href="http://ur-recipe.com/%d0%b6%d0%b0%d1%80%d0%b5%d0%bd%d1%8b%d0%b9-%d1%80%d0%b8%d1%81-%d1%81-%d0%ba%d1%83%d1%80%d0%b8%d1%86%d0%b5%d0%b9-%d0%b8-%d0%be%d0%b2%d0%be%d1%89%d0%b0%d0%bc%d0%b8/" rel="next"><span class="meta-nav">Следующая запись</span>Жареный рис с курицей и овощами</a>		</div><!-- .nav-links -->
	</nav><!-- .navigation -->
	
<div id="comments" class="comments-area">

	
							<div id="respond" class="comment-respond">
				<h3 id="reply-title" class="comment-reply-title">Добавить комментарий <small><a rel="nofollow" id="cancel-comment-reply-link" href="/%D0%B7%D0%B0%D0%BF%D0%B5%D0%BA%D0%B0%D0%BD%D0%BA%D0%B0-%D0%B8%D0%B7-%D0%BA%D0%B0%D0%B1%D0%B0%D1%87%D0%BA%D0%BE%D0%B2-%D1%81-%D1%82%D0%B2%D0%BE%D1%80%D0%BE%D0%B3%D0%BE%D0%BC/#respond" style="display:none;">Отменить ответ</a></small></h3>
									<form action="http://ur-recipe.com/wp-comments-post.php" method="post" id="commentform" class="comment-form" novalidate>
																			<p class="logged-in-as">Вы вошли как <a href="http://ur-recipe.com/wp-admin/profile.php">Умитай Турдыкулова</a>. <a href="http://ur-recipe.com/wp-login.php?action=logout&amp;redirect_to=http%3A%2F%2Fur-recipe.com%2F%25d0%25b7%25d0%25b0%25d0%25bf%25d0%25b5%25d0%25ba%25d0%25b0%25d0%25bd%25d0%25ba%25d0%25b0-%25d0%25b8%25d0%25b7-%25d0%25ba%25d0%25b0%25d0%25b1%25d0%25b0%25d1%2587%25d0%25ba%25d0%25be%25d0%25b2-%25d1%2581-%25d1%2582%25d0%25b2%25d0%25be%25d1%2580%25d0%25be%25d0%25b3%25d0%25be%25d0%25bc%2F&amp;_wpnonce=1882956d1f" title="Выйти из этой учётной записи">Выйти?</a></p>																			<p class="comment-form-comment"><label for="comment">Комментарий</label> <textarea id="comment" name="comment" cols="45" rows="8" aria-describedby="form-allowed-tags" aria-required="true" required="required"></textarea></p>						<p class="form-allowed-tags" id="form-allowed-tags">Можно использовать следующие <abbr title="HyperText Markup Language">HTML</abbr>-теги и атрибуты:  <code>&lt;a href=&quot;&quot; title=&quot;&quot;&gt; &lt;abbr title=&quot;&quot;&gt; &lt;acronym title=&quot;&quot;&gt; &lt;b&gt; &lt;blockquote cite=&quot;&quot;&gt; &lt;cite&gt; &lt;code&gt; &lt;del datetime=&quot;&quot;&gt; &lt;em&gt; &lt;i&gt; &lt;q cite=&quot;&quot;&gt; &lt;s&gt; &lt;strike&gt; &lt;strong&gt; </code></p>
						<p class="form-submit"><input name="submit" type="submit" id="submit" class="submit" value="Отправить комментарий" /> <input type='hidden' name='comment_post_ID' value='40' id='comment_post_ID' />
<input type='hidden' name='comment_parent' id='comment_parent' value='0' />
</p><input type="hidden" id="_wp_unfiltered_html_comment_disabled" name="_wp_unfiltered_html_comment_disabled" value="4979e0f8da" /><script>(function(){if(window===window.parent){document.getElementById('_wp_unfiltered_html_comment_disabled').name='_wp_unfiltered_html_comment';}})();</script>
					</form>
							</div><!-- #respond -->
			
</div><!-- #comments -->
		
	</div>
	
	</div>
	<!-- /.col-xs-12 col-sm-8 col-md-8 -->
	<div class="col-sm-4 col-md-4">
	<c:if test="${empty unvisible}">
		<!-- Вставьте этот тег в заголовке страницы или непосредственно перед закрывающим тегом основной части. -->
	<script src="https://apis.google.com/js/platform.js" async defer>
	  {lang: 'ru'}
	</script>
	
	<!-- Поместите этот тег туда, где должна отображаться виджет. -->
	<div class="g-page" data-href="//plus.google.com/u/0/102501925985129905212" data-rel="publisher"></div>
	
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<!-- recipe-side -->
	<ins class="adsbygoogle"
	     style="display:block"
	     data-ad-client="ca-pub-2604632423420713"
	     data-ad-slot="6080759413"
	     data-ad-format="auto"></ins>
	<script>
	(adsbygoogle = window.adsbygoogle || []).push({});
	</script>	
	 </c:if>
	</div>
	<!-- /.col-sm-4 col-md-4 -->
</div>	
<!-- /.row -->
</div>	
<!-- /.container -->
<%@include file="bottom.jsp" %>