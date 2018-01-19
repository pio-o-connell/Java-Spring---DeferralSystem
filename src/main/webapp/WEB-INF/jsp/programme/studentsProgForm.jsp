<%@ include file="/WEB-INF/jsp/include.jsp"%>



<!--  @author Dale Cusack -->

<form:form method="POST" action="studentsProg"
	modelAttribute="progOfStudent">


	<div class="lecturerDropdown">
		<div class="studentDropdown">
			<form:select path="studentId" id="studentId">
				<form:option value="studentId">Choose Students ID</form:option>
				<c:forEach var="student" items="${studentIdList}">
					<form:option value="${student.studentId}"></form:option>
				</c:forEach>
				<form:errors path="studentId" cssClass="error"
					class="notification error" style="display:block"></form:errors>
			</form:select>
		</div>
	</div>

	<input data-theme="b" type="submit" class="button" data-icon="check"
		value="Search" />
</form:form>


