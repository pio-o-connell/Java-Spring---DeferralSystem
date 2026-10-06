<%@ include file="/WEB-INF/jsp/include.jsp"%>



<c:if test="${not empty lecturers}">
	<div class="message">
		<h4>${message}</h4>
	</div>

	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="lecturerTable">
		<thead>
			<tr>
				<th data-priority="1">Lecturer Id</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th data-priority="3">Email</th>
				<th data-priority="2">Modify</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="lecturer" items="${lecturers}" varStatus="status">
				<tr>
					<td><a
						href="<%= request.getContextPath() %>/admin/lecturer/display/id/${status.current.lectId}"
						class="ui-btn ui-corner-all">${status.current.lectId}</a></td>
					<td><h3>${lecturer.firstName}</h3></td>
					<td><h3>${lecturer.lastName}</h3></td>
					<td><h3>${lecturer.email}</h3></td>
					<%-- <td><a href="<%= request.getContextPath() %>/lecturer/delete/id/${status.current.lectId}"
						class="ui-btn ui-corner-all">Delete</a></td> --%>
					<td><a
						href="<%= request.getContextPath() %>/admin/lecturer/modify/id/${status.current.lectId}"
						class="ui-btn ui-corner-all">Modify</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>

<c:if test="${empty lecturers}">
	<div class="notification warning">No lecturers.</div>
</c:if>



