<%@ include file="/WEB-INF/jsp/include.jsp"%>



				<!--  @author Dale Cusack -->
				

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var coordinatorId = $("#coordinatorId").val();	 
	var programmeId=$("#programmeId").val(); 
	var url="/admin/programme/modify/programmeId/"+programmeId+"/coordinatorId/"+coordinatorId;
	location.href="<%= request.getContextPath() %>"+url;
});
});
</script>
</head>



<body>
	<!-- Form for user to add in attributes to create a new Programme
	When Save button pressed data stored in model attribute newProgramme
	/programme/addProg url generated -->
	<h4 style="position:relative; bottom:60px">${message}</h4>
	<form:form  style="position:relative; bottom:120px" modelAttribute="programme">
	
	<div class="modifyDropdown">
	<div class="modifyPositioning">
		<div class="ui-field-contain">
 			<form:label path="programmeId"><font size="4">ProgrammeId</font></form:label>
 			<form:input path="programmeId" value="${programmeId}"
 			disabled="true"/>
 		</div>
 		<div class="ui-field-contain">
 			<form:label path="numYears"><font size="4">Number Of Years</font></form:label>
 			<form:input path="numYears" value="${numYears}"
 			disabled="true"/>
 		</div>
 		<div class="ui-field-contain">
 			<form:label path="progYear"><font size="4">Programme Year</font></form:label>
 			<form:input path="progYear"  value="${progYear}"
 			disabled="true"/>
 		</div>
 	
 		<div class="addModuleText">
		<font size="4">Coordinator ID</font>
	</div>
	
  		<div class="modifyDropdown2">
	<form:select path="coordinatorId" id="coordinatorId">
		<form:option value="coordinatorId">Choose Coordinator ID</form:option>
		<c:forEach var="coordinatorList" items="${coordinatorList}">
			<form:option value="${coordinatorList.lectId}"></form:option>
		</c:forEach>
		<form:errors path="coordinatorId" cssClass="error"
			class="notification error" style="display:block"></form:errors>
	</form:select>

 		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify Programme" id="modify" />
			</div>
			</div>
			</div>
  	</form:form> 
  	
  	
  </body>
</html> 