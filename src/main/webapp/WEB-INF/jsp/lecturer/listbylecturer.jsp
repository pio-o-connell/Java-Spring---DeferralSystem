<%@ include file="/WEB-INF/jsp/include.jsp"%>


<script type="text/javascript">
$(function() {
$("#search").click(function(){		
	var lecturerId = $("#lecturerId").val();	
	var url="/admin/advancedlist/lectId/"+lectId;
	location.href="<%=request.getContextPath()%>" + url;
		});
	});
</script>



<form action="" method="GET">
	Please Enter Lecturer Id: <input type="text" id="lectId" name="lectId"
		maxlength="10"> <br /> <input data-theme="b" type="button"
		data-icon="check" value="Search" id="search" />
</form>
