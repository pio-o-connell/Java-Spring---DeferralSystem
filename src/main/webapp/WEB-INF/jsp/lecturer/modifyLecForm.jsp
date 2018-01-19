<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var lastName = $("#lastName").val();
	var lectId=$("#lectId").val();	
	var url="/admin/lecturer/modify/id/"+lectId+"/lastname/"+lastName;
	location.href="<%=request.getContextPath()%>" + url;
				});
	});
</script>
</head>

<body>
	<h4 style="position: relative; bottom: 40px">${message}</h4>
	<form:form style="position:relative; bottom:40px"
		modelAttribute="lecturer">


		<div class="ui-field-contain">
			<form:label path="lectId">Lecturer Id</form:label>
			<form:input path="lectId" value="${lectId}" disabled="true" />
		</div>
		<div class="ui-field-contain">
			<form:label path="firstName">First Name</form:label>
			<form:input path="firstName" value="${firstName}" disabled="true" />
		</div>
		<div class="ui-field-contain">
			<form:label path="lastName">Last Name</form:label>
			<form:input path="lastName" value="${lastName}" />
		</div>
		<div class="ui-field-contain">
			<form:label path="email">Email</form:label>
			<form:input path="email" id="email" value="${email}" disabled="true" />
		</div>

		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify Lecturer" id="modify" />
	</form:form>
</body>
</html>