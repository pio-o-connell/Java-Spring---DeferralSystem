<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#search").click(function(){		
	var studentId = $("#studentId").val();	
	var url="/admin/module/advancedlist/studentId/"+studentId;
	location.href="<%=request.getContextPath()%>" + url;
		});
	});
</script>
</head>
<body>


	<form action="" method="GET">
		Please Enter Student Id: <input type="text" id="studentId"
			name="studentId" maxlength="10"> <br /> <input
			data-theme="b" type="button" data-icon="check" value="Search"
			id="search" />
	</form>


</body>
</html>
