<%@ include file="/WEB-INF/jsp/include.jsp"%>



			<!--  @author Dale Cusack -->

<head>
<style>
.error {
	color: #ff0000;
}
</style>
</head>

<!-- Form for user to add in attributes to create a new Programme
	When Save button pressed data stored in model attribute newProgramme
	/programme/addProg url generated -->
	<form:form method="POST" action="addProg" modelAttribute="newProgramme">
	
	<div class="modifyDropdown">
	<div class="modifyPositioning">
		<div class="ui-field-contain">
 			<form:label path="programmeId"><font size="4">Programme ID</font></form:label>
 			<form:input path="programmeId" placeholder="programmeId" value=""/>
 			<form:errors path="programmeId" cssClass="error" class="notification error" style="display:block"></form:errors>
 			
 		</div>
 		<div class="ui-field-contain">
 			<form:label path="numYears"><font size="4">Number Of Years</font></form:label>
 			<form:input path="numYears" placeholder="numYears" value="" type="number"/>
 			<form:errors path="numYears" cssClass="error" class="notification error" style="display:block"></form:errors>
 			
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="progYear"><font size="4">Programme Year</font></form:label>
 			<form:input path="progYear" placeholder="progYear" value="" type="number"/>
 			<form:errors path="progYear" cssClass="error" class="notification error" style="display:block"></form:errors>
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
		</form:select>
		
 		
 		<input data-theme="b" type="submit" button="class" data-icon="check" value="Save" />
			</div>
			</div>
			</div>
  	</form:form> 

