<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Niall McCarthy -->


<script type="text/javascript">
$(function() {
$("#search").click(function(){		
	var studentId = $("#studentId").val();	
	var url="/admin/deferral/advancedlist/studentId/"+studentId;
	location.href="<%=request.getContextPath()%>" + url;
		});
	});
</script>



<form action="" method="GET">
	Please Enter Student Id: <input type="text" id="studentId"
		name="studentId" maxlength="10"> <br /> <input data-theme="b"
		class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b"
		type="button" data-icon="check" value="Search" id="search" />
</form>

