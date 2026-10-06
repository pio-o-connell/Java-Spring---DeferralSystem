<%@ include file="/WEB-INF/jsp/include.jsp"%>



<c:if test="${not empty students}">


	<div class="message">
		<h4>${message}</h4>
	</div>

	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="studentTable">


		<thead>
			<tr>
				<th data-priority="2">Student Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th data-priority="3">Email</th>
				<th data-priority="6">Modify</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="student" items="${students}" varStatus="status">
				<tr>
					<td><a
						href="<%= request.getContextPath() %>/admin/student/display/id/${status.current.studentId}"
						class="ui-btn ui-corner-all">${student.studentId}</a></td>
					<td><h3>${student.firstName}</h3></td>
					<td><h3>${student.lastName}</h3></td>
					<td><h3>${student.email}</h3></td>

					<td><a
						href="<%= request.getContextPath() %>/admin/student/modify/id/${status.current.studentId}"
						class="ui-btn ui-corner-all">Modify</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:if>

<c:if test="${empty students}">
	<div class="notification warning">No students.</div>
</c:if>








