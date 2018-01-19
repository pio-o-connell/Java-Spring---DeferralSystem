<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Niall McCarthy -->

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){ 
	var approval=$("#approval").val(); 
	var defferalId=${deferral.defId};
	var url="/admin/deferral/modify/id/"+defferalId+ "/approval/"+approval ;
	location.href="<%=request.getContextPath()%>" + url;
				});
	});
</script>
</head>

<body>
	<form:form modelAttribute="deferral">

		<div class="modifyDropdown">
			<div class="modifyPositioning">
				<div class="ui-field-contain">
					<form:label path="studentId">
						<font size="4">Student ID</font>
					</form:label>
					<form:input path="studentId" value="${deferral.studentId}"
						disabled="true" />
				</div>
				<div class="ui-field-contain">
					<form:label path="lectId">
						<font size="4">Coordinator ID</font>
					</form:label>
					<form:input path="lectId" value="${deferral.lectId}"
						disabled="true" />
				</div>
				<div class="ui-field-contain">
					<form:label path="programmeId">
						<font size="4">Programme ID</font>
					</form:label>
					<form:input path="programmeId" value="${deferral.programmeId}"
						disabled="true" />
				</div>

				<div class="ui-field-contain">
					<form:label path="moduleId">
						<font size="4">Module ID</font>
					</form:label>
					<form:input path="moduleId" value="${deferral.moduleId}"
						disabled="true" />
				</div>


				<div class="addModuleText">
					<font size="4">Approval State</font>
				</div>
				<div class="modifyDropdown2">
					<form:select path="approval" id="approval">
						<form:option value="True"></form:option>
						<form:option value="False"></form:option>
						<form:errors path="approval" cssClass="error"
							class="notification error" style="display:block"></form:errors>
					</form:select>


					<input type="button" class="ui-btn" data-theme="b"
						data-icon="check" value="Modify" id="modify" />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>
