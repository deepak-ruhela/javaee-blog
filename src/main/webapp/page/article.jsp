<%@page import="dao.FileDao"%>
<%@page import="model.File"%>
<%@page import="dao.PostTagDao"%>
<%@page import="model.PostTag"%>
<%@page import="dao.PostCategoryDao"%>
<%@page import="model.Category"%>
<%@page import="dao.ArticleDao"%>
<%@page import="model.Article"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>write</title>
<%@include file="../components/common_css_js.jsp"%>

<!-- Editor -->
<!-- <link rel="stylesheet" href="../editormd/css/editormd.css" />
<script src="../editormd/languages/en.js"></script>
<script src="../editormd/editormd.js"></script> -->

<!--  -->
<script src=" https://cdn.jsdelivr.net/npm/editor.md@1.5.0/editormd.min.js "></script>
<link href=" https://cdn.jsdelivr.net/npm/editor.md@1.5.0/css/editormd.min.css " rel="stylesheet">
<!-- Include the editor.md language file -->
    <script src="https://cdn.jsdelivr.net/gh/pandao/editor.md@1.5.0/languages/en.js"></script>
</head>
<!--  -->

<script src="../js/zepto.min.js"></script>

<!-- Multiselect Lov -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

<!-- Tags -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-tagsinput/0.8.0/bootstrap-tagsinput.min.js"></script>

<style type="text/css">
/* Tags Stying */
.bootstrap-tagsinput .tag {
	margin-right: 2px;
	color: white !important;
	background-color: #0d6efd;
	padding: 0.2rem;
}

.card-img-top {
	/* width: 100%; */
	height: 30vh;
	object-fit: contain;
}
</style>
</head>
<body>

	<%@include file="../components/navbar.jsp"%>
	<%
	Article article = new Article();
	Category category = null;
	List<PostTag> postTagList = null;
	File file = null;
	int id = 0;
	String imageName = "";
	if (request.getParameter("strId") != null) {
		id = Integer.parseInt(request.getParameter("strId"));
		article = ArticleDao.getArticle(id);
		int categoryId = PostCategoryDao.getCategoryIdByPostId(id);
		category = CategoryDao.getOneCategory(categoryId);
		postTagList = PostTagDao.getPostTagByPostId(id);
		file = FileDao.getFileByPostId(id);
		if (FileDao.getFileByPostId(id) != null) {
			imageName = file.getName();
		}

	}
	System.out.println(imageName);
	request.setAttribute("article", article);
	List<Category> clist = CategoryDao.getAllCategories();
	//postTagList = PostTagDao.getAllPostTag();
	%>
	<div class="container">



		<h3 class="text-center my-3">Details ${name}</h3>

		<form id="theform"
			action="<%=request.getContextPath()%>/ArticleServlet" method="post"
			enctype="multipart/form-data">

			<div class="row mt-5">
				<div class="col-md-6 offset-md-3">
					<div class="card">
						<div class="card-body px-5">


							<div class="form-group">
								<label for="id">Article Id </label> <input type="text"
									readonly="readonly" name="id" class="form-control"
									value="<%=id%>">
							</div>


							<div class="form-group">

								<label for="title">Title</label> <input type="text" name="title"
									class="form-control"
									value=<%if (article.getTitle() != null) {
	out.print(article.getTitle());
}%>>


							</div>

							<div class="form-group mx-auto">
								<label for="toolTags">Category</label> <select
									class="selectpicker" name="tagsSelector" id="tagsSelector"
									title="Choose Any" data-live-search="true" multiple
									data-max-options="1" required="required">
									<%
									for (int i = 0; i < clist.size(); i++) {
										Category c = clist.get(i);
									%>
									<option value="<%=c.getId()%>"><%=c.getName()%></option>
									<%
									}
									if (category != null) {
									%>
									<option value="<%=category.getId()%>" selected="selected"><%=category.getName()%></option>
									<%
									}
									%>




								</select>

							</div>

							<div class="input-group mx-auto mt-2">
								<label>Tags</label> <select multiple data-role="tagsinput"
									name="tags">
									<%
									if (postTagList != null) {

										for (int i = 0; i < postTagList.size(); i++) {
											PostTag postTag = postTagList.get(i);
									%>
									<option value="<%=postTag.getTag()%>" selected="selected"><%=postTag.getId()%></option>
									<%
									}
									}
									%>

									<!-- <option value="Cairo">Cairo</option> -->
								</select>
							</div>
							<div class="input-group mt-2 mx-auto">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="imageInput"
										name="imageInput" accept="image/*" /> <label
										class="custom-file-label" for="imageInput"> <%
 if (file == null) {
 	out.println("Choose Any File");
 } else {
 	out.println(file.getName());
 }
 %>
									</label>
								</div>

							</div>
							<div class="form-group img-responsive card-img-top mt-3">
								<img src="/image/<%=imageName%>" class="img-fluid card-img-top">
							</div>
						</div>
					</div>
				</div>


			</div>
			<div id="editor" class="container mt-2">
				<textarea name="content">
					<%
					if (article.getContent() != null) {
						out.print(article.getContent());
					}
					%>
				</textarea>

			</div>



			<div class="container text-center">
				<button type="submit" class="btn btn-outline-success">Submit</button>
				<a href="<%=request.getContextPath()%>/page/posts.jsp"
					class="btn btn-outline-secondary">Back</a>
			</div>
		</form>


		<%@include file="/components/footerbar.jsp"%>
	</div>
	<!-- markdown editor -->
	<script type="text/javascript">
		$(function() {
			var editor = editormd("editor", {
				width : "90%",
				height : 720,
				path : '../editormd/lib/',
				codeFold : true,
				searchReplace : true,
				saveHTMLToTextarea : true, // Save HTML to Textarea                   
				htmlDecode : "style,script,iframe|on*", // Enable HTML tag parsing, for security, it is not enabled by default
				emoji : true,
				taskList : true,
				tocm : true,
				tex : true,
				flowChart : true,
				sequenceDiagram : true,
				//	imageUpload : true,
				//	imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp", "webp" ],
				//	imageUploadURL : "/Blog/UploadPic",
				//	The background only needs to return a JSON data				      
				onload : function() {
					//console.log("onload =>", this, this.id, this.settings);
				}
			});
			editor.setToolbarAutoFixed(false);//Enable and disable the automatic fixed positioning of the toolbar             
		});
	</script>
	<!-- tags editor -->
	<script>
		$(function() {
			$('input').on(
					'change',
					function(event) {
						var $element = $(event.target);
						var $container = $element.closest('.example');

						if (!$element.data('tagsinput'))
							return;

						var val = $element.val();
						if (val === null)
							val = 'null';
						var items = $element.tagsinput('items');

						$('code', $('pre.val', $container)).html(
								$.isArray(val) ? JSON.stringify(val) : '"'
										+ val.replace('"', '\\"') + '"');
						$('code', $('pre.items', $container)).html(
								JSON.stringify($element.tagsinput('items')));
					}).trigger('change');
		});
	</script>

	<script>
		$('input[type="file"]').change(function(e) {
			var fileName = e.target.files[0].name;
			$('.custom-file-label').html(fileName);
		});
	</script>
</body>
</html>