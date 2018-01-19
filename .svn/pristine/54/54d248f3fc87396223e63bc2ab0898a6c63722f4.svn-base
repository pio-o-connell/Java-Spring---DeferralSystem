<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!-- @author Julia Foden -->
<head>
<style>
.error {
	color: #ff0000;
}
</style>
</head>

<form:form method="POST" action="addNew" modelAttribute="student">


	<div class="modifyDropdown">
	<div class="modifyPositioning">
	<div class="ui-field-contain">
		<form:label path="studentId"><font size="4">Student ID</font></form:label>
		<form:input path="studentId" placeholder="Enter Student ID .." value=""/>
		<form:errors path="studentId" cssClass="error" class="notification error" 
			style="display:block"></form:errors>
	</div>
	
	<div class="ui-field-contain">
		<form:label path="firstName"><font size="4">First Name</font></form:label>
		<form:input path="firstName" placeholder="Enter Student first name .." value=""/>
		<form:errors path="firstName" cssClass="error" class="notification error" 
			style="display:block"></form:errors>
	</div>
	
	<div class="ui-field-contain">
		<form:label path="lastName"><font size="4">Last Name</font></form:label>
		<form:input path="lastName" placeholder="Enter Student last name .." value=""/>
		<form:errors path="lastName" cssClass="error" class="notification error" 
			style="display:block"></form:errors>
	</div>
	
	<div class="ui-field-contain">
		<form:label path="email"><font size="4">Student Email</font></form:label>
		<form:input path="email" placeholder="Enter Student Email .." value="" type="email"/>
		<form:errors path="email" cssClass="error" class="notification error" 
			style="display:block"></form:errors>
	</div>
	
	
	
	<div class="saveTextButton">
	<input data-theme="b" type="submit" class="button" data-icon="check" value="Save" />
	</div>
	</div>
	</div>
</form:form>