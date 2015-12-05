<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/n/header.jsp" %>
	<div class="container">
	<form action="/n/item/save" method="post">
		<div class="row">
			<div  class="col-sm-6 col-lg-6">
				<div class="form-group">
				<label>Slug*</label>
				<input type="text" value="${item.slug}" name="slug" class="form-control" readonly>
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
				<label>Categories*</label>
				<input type="text" value="${item_categories}" name="categories" class="form-control">
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
				<label>nutrition*</label>
				<input type="text" value="${item.nutrition}"  name="nutrition" class="form-control">
				</div>
				<div class="form-group">
				<label>og:image</label>
				<input type="text" value="${item.thumbnailUrl2}" name="thumbnailUrl2" class="form-control">
				</div>
				<div class="form-group">
				<label>Link title</label>
				<input type="text" value="${item.link_title}" name="link_title" class="form-control">
				</div>
				<div class="form-group">
				<label>Image alt</label>
				<input type="text" value="${item.alt}" name="alt" class="form-control">
				</div>
				<div class="form-group">
				<label>Meta title</label>
				<input type="text" value="${item.meta_title}" name="meta_title" class="form-control">
				</div>
				<div class="form-group">
				<label>Meta Keywords</label>
				<input type="text" value="${item.meta_keywords}" name="meta_keywords" class="form-control">
				</div>
				<div class="form-group">
				<label>Meta Description</label>
				<textarea  name="meta_description" class="form-control">${item.meta_description}</textarea>
				</div>
				<div class="form-group">
				<label>ads_horizont1</label>
				<textarea  name="ads_horizont1" class="form-control">${item.ads_horizont1}</textarea>
				</div>
				<div class="form-group">
				<label>ads_horizont2</label>
				<textarea  name="ads_horizont2" class="form-control">${item.ads_horizont2}</textarea>
				</div>
				<div class="form-group">
				<label>ads_side1</label>
				<textarea  name="ads_side1" class="form-control">${item.ads_side1}</textarea>
				</div>
				<div class="form-group">
				<label>ads_side2</label>
				<textarea name="ads_side2" class="form-control">${item.ads_side2}</textarea>
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
				<select name="active" class="form-control">
					<option value="true" ${item.active ? 'selected' : ''} >Yes</option>
					<option value="false" ${!item.active ? 'selected' : ''}>No</option>
				</select>
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
		</div>
		<div  class="col-sm-6 col-lg-6">
		      <div class="form-group">
				<label>ingredients*</label>
				<textarea  name="ingredients" class="form-control">${item.ingredients}</textarea>
				</div>
				<div class="form-group">
				<label>About*</label>
				<textarea   name="about" class="form-control">${item.about}</textarea>
				</div>
				<div class="form-group">
				<label>Description*</label>
				<textarea   id="textarea"  style="height: 700px;" name="description" class="form-control" >${item.description}</textarea>
				</div>
				
			</div>
		</div>
		<div class="form-group">
		<input type="submit" class="btn btn-primary"> 
		</div>
		</form>
	</div><!--/.container -->

</body>
</html>