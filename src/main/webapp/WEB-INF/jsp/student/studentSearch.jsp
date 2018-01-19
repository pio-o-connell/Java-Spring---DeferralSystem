<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Julia Foden -->

<form:form method="POST" action="studentSearchResult" modelAttribute="student">
	
				
 		
 		<div class="studentDropdown">
		<form:select path="studentId" id="studentId">
			<form:option value="studentId">Choose Student's ID</form:option>
			<c:forEach var="student" items="${studentIdList}">
				<form:option value="${student.studentId}"></form:option>
			</c:forEach>
			<form:errors path="studentId" cssClass="error"
			class="notification error" style="display:block"></form:errors>
		</form:select>
		</div>
		
 		
 		<input data-theme="b" type="submit" class="button" data-icon="search" value="Search" />
  	</form:form> 