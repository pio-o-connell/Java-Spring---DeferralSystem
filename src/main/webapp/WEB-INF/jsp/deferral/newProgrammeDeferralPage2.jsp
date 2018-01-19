<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Niall McCarthy -->


<form:form method="POST" enctype="multipart/form-data" data-ajax="false"
	action="addProgrammeDeferralPage2" modelAttribute="deferral">

	<form:errors path="*" cssClass="errorblock" element="div" />


	<form:input path="studentId" value="${studentId}" type="hidden" />
	<form:errors path="studentId" cssClass="error"
		class="notification error" style="display:block"></form:errors>

	<div class="addModuleDeferralPositioning">
		<p class="addModuleDeferralText">Student's name:
			${studentFirstName} ${studentLastName}
		<div class="addModuleDeferralText">Student's ID: ${studentId}</div>

		<div class="dropdown2">
			<form:select path="programmeId" id="programmeId">
				<form:option value="programmeId">Choose Programme ID</form:option>
				<form:option value="${studentProgramme}"></form:option>
				<form:errors path="programmeId" cssClass="error"
					class="notification error" style="display:block"></form:errors>
			</form:select>
		</div>

		<div class="addModuleDeferralText2">
			<form:label path="file">
				<font size="4">Upload Completed Withdrawal Form (*Only
					Supports PDF)</font>
			</form:label>
			<form:input path="file" type="file" />


			<input data-theme="b" type="submit" data-icon="check" value="Save" />
		</div>
	</div>
</form:form>