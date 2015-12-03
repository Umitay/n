<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="header.jsp" %>
        <div class="container">
        <h1>CSV loader</h1>
		<form role="form" method="post" action="/csv/upload">
		  <div class="form-group">
		    <label for="filename">File Name</label>
		    <input type="text" class="form-control" name="filename" id="filename" placeholder="Enter file Name">
		  </div>
		  <button type="submit" class="btn btn-default">Submit</button>
		</form>
		
        </div>
        </body>
        </html>
