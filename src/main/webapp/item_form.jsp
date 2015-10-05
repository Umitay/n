<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="n_header.jsp" %>
	<div class="container">
	<form action="/recipe/save" method="post">
		<div class="form-group">
		<label>Slug*</label>
		<input type="text" value="${item.slug}" name="slug" class="form-control">
		</div>
		<div class="form-group">
		<label>Name*</label>
		<input type="text" value="${item.name}" name="name" class="form-control">
		</div>
		<div class="form-group">
		<label>thumbnailUrl*</label>
		<input type="text" value="${item.thumbnailUrl}" name="thumbnailUrl" class="form-control">
		</div>
		
		<div class="form-group">
		<label>recipeCategory*</label>
		<input type="text" value="${item_categories}" name="recipeCategory" class="form-control">
		</div>
		<div class="form-group">
		<label>totalTime*</label>
		<input type="text" value="${item.totalTime}" name="totalTime" class="form-control">
		</div>
		<div class="form-group">
		<label>recipeYield*</label>
		<input type="text" value="${item.recipeYield}" name="recipeYield" class="form-control">
		</div>
		<div class="form-group">
		<label>dateModified*</label>
		<input type="text" value="${item.dateModified}" class="form-control">
		</div>
		<div class="form-group">
		<label>dateCreated*</label>
		<input type="text" value="${item.dateCreated}"  class="form-control">
		</div>
		<div class="form-group">
		<label>datePublished*</label>
		<input type="text" value="${item.datePublished}" name="datePublished" class="form-control">
		</div>
		<div class="form-group">
		<label>active*</label>
		<input type="text" value="${item.active}"  class="form-control">
		</div>
		<div class="form-group">
		<label>nutrition*</label>
		<input type="text" value="${item.nutrition}"  name="nutrition" class="form-control">
		</div>
		<div class="form-group">
		<label>ingredients*</label>
		<textarea  name="ingredients" class="form-control">${item.ingredients}</textarea>
		</div>
		<div class="form-group">
		<label>about*</label>
			<textarea name="about" class="form-control">${item.about}</textarea>
		</div>
		<div class="form-group">
		<label>Description*</label>
		<textarea name="description" class="form-control" >${item.description}</textarea>
		</div>
		<div class="form-group">
		<label>FB</label>
		<input type="text" value="${item.fb_share}"  name="fb_share" class="form-control">
		</div>
		<div class="form-group">
		<label>VK</label>
		<input type="text" value="${item.vk_share}"  name="vk_share" class="form-control">
		</div>
		<div class="form-group">
		<label>LJ</label>
		<input type="text" value="${item.lj_share}"  name="lj_share" class="form-control">
		</div>
		<div class="form-group">
		<label>Twitter</label>
		<input type="text" value="${item.twitter_share}"  name="twitter_share" class="form-control">
		</div>
		<div class="form-group">
		
		<input type="submit" class="btn btn-primary"> 
		</form>
	</div><!--/.container -->

</body>
</html>