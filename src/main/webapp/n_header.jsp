<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"	href="/styles.css">
<link rel="icon" href="/logo.png" type="image/x-icon" />
<link rel="shortcut icon" href="/logo.png" type="image/x-icon" />
<!-- Latest compiled and minified JavaScript -->
<script	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="//tinymce.cachefly.net/4.2/tinymce.min.js"></script>
<script>tinymce.init({selector:'textarea',plugins: "code"});</script>

<title>Admin</title>
<style type="text/css">
body{ padding-top: 5%}
.navbar > div {
    padding-top: 0px;
}
</style>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button aria-controls="navbar" aria-expanded="false" data-target="#navbar" data-toggle="collapse" class="navbar-toggle collapsed" type="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="/n" class="navbar-brand">Admin</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar">
          <ul class="nav navbar-nav">
			<li><a href="/csv"><b>Upload Csv</b></a>  </li>
		 </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>