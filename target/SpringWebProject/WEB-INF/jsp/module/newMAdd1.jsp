<%@ include file="/WEB-INF/jsp/include.jsp"%>

<head>
<style>
.error {
	color: #ff0000;
}
</style>
</head>

<form:form method="POST" action="addModule" modelAttribute="newModule">


	<div class="modifyDropdown">
		<div class="modifyPositioning">
			<div class="ui-field-contain">
				<form:label path="moduleId">
					<font size="4">Module Id</font>
				</form:label>
				<form:input path="moduleId" placeholder="Module ID.." value="" />
				<form:errors path="moduleId" cssClass="error"
					class="notification error" style="display:block"></form:errors>
			</div>


			<div class="ui-field-contain">
				<form:label path="crnNumber">
					<font size="4">CRN Number</font>
				</form:label>
				<form:input path="crnNumber" placeholder="CRN.." value=""
					type="number" />
				<form:errors path="crnNumber" cssClass="error"
					class="notification error" style="display:block"></form:errors>
			</div>

			<div class="ui-field-contain">
				<form:label path="name">
					<font size="4">Module Name</font>
				</form:label>
				<form:input path="name" placeholder="Module Name.." value="" />
				<form:errors path="name" cssClass="error" class="notification error"
					style="display:block"></form:errors>
			</div>

			<div class="addModuleText">
				<font size="4">Lecturer ID</font>
			</div>
			<div class="modifyDropdown2">
				<form:select path="lectId" id="lectId">
					<form:option value="lectId">Choose Lecturer ID</form:option>
					<c:forEach var="coordinatorList" items="${coordinatorList}">
						<form:option value="${coordinatorList.lectId}"></form:option>
					</c:forEach>
				</form:select>
			</div>


			<div class="addModuleText2">
				<font size="4">Semester ID</font>
			</div>
			<div class="modifyDropdown3">
				<form:select path="semesterId" id="semesterId">
					<form:option value="semesterId">Choose Semester ID</form:option>
					<c:forEach var="semesterList" items="${semesterList}">
						<form:option value="${semesterList.semesterId}"></form:option>
					</c:forEach>
				</form:select>



				<input data-theme="b" type="submit" class="button" data-icon="check"
					value="Save" />
			</div>
		</div>
	</div>
</form:form>
