 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        
       <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
        <style type="text/css">
	        .form-group.required .control-label:after { 
			   content:"*";
			   color:red;
			}
			.modal-title {
	    		text-align: center;
			}
			.alert {
			margin-bottom: 0;
			}
        </style>
        <title>Login</title>    
    </head>
    <body>
	    <div class="container">
		    <div tabindex="-1" id="myModal" class="modal show">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title">Welcome to Back Office</h4>
			         ${errors}
			      </div>
			      <div class="modal-body">
			        <form role="form" id="formSignIn" method="post"  action="/login/submit"  enctype="application/x-www-form-urlencoded">
			            <div class="form-group required">
							<label class="control-label">Email address</label> 
							<input type="text" class="form-control" id="email" name="email" required>
						</div>
						<div class="form-group required">
							<label class="control-label">Password</label> 
							<input type="password" class="form-control" id="password" name="password"  required>
							
						</div>
						<div class="form-group row">
							<div class="col-md-2">
							  <input type="submit" value="LogIn" class="btn btn-primary">
							</div>
						</div>
			        </form>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div>
	    </div> <!-- /.container -->
	    
    
    
       <script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
       <script	src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.min.js"></script>
       
       <script type="text/javascript">
     
    	    $( document ).ready(function() {
	

			// validate signup form on keyup and submit
			$("#formSignIn").validate({
				
								rules : {
										password : {
											required : true,
											minlength : 5
										},
										email : {
											required : true,
											email : true
										}
									},
									messages : {
										password : {
											required : "Please provide a password",
											minlength : "Your password must be at least 5 characters long"
										},
										email : "Please enter a valid email address"
									}
								});
							});
					
							</script>
    </body>
</html>