<%@ include file= "/WEB-INF/jsp/include.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome page</title>
<%-- <link href="<c:url value="/resources/css/Group2.css" />" rel="stylesheet"> --%> 
</head>
<body>

<div id="homePageText">
<div class="ui-field-contain" style="position:relative; right: 10%; width:1200px ; font-size: 0.5em" >A completed withdrawal form is required to apply for a deferral. Please use the link below to download form</div>
<a href="<%= request.getContextPath() %>/home/downloadForm" download target="_blank" style="position:relative; left: 36%; width:300px; font-size: 0.5em" class="ui-field-contain" ><font color="#5C8AE6">Download Deferral Form</font></a>
</div>



</body>
</html>