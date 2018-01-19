<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Niall McCarthy, modified by Julia Foden -->


<c:if test="${not empty deferrals}">
	<h4>${message}</h4>



	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="myTable">
		<thead>
			<tr>
				<th data-priority="1">Deferral ID</th>
				<th data-priority="1">Student ID</th>
				<th data-priority="1">Lecturer ID</th>
				<th data-priority="1">Programme ID</th>
				<th data-priority="1">Module ID</th>
				<th data-priority="4">Approval</th>
				<th data-priority="4">Withdrawal Form</th>
				<th data-priority="4">Delete</th>
				<th data-priority="5">Modify</th>
			</tr>
		</thead>

		<tbody>


			<c:forEach var="deferral" items="${deferrals}">
				<tr>

					<td><h3>${deferral.defId}</h3></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/student/display/id/${deferral.studentId}"
						class="ui-btn ui-corner-all">${deferral.studentId}</a></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/lecturer/display/id/${deferral.lectId}"
						class="ui-btn ui-corner-all">${deferral.lectId}</a></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/programme/display/id/${deferral.programmeId}"
						class="ui-btn ui-corner-all">${deferral.programmeId}</a></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/module/list/id/${deferral.moduleId}"
						class="ui-btn ui-corner-all">${deferral.moduleId}</a></td>
					<c:choose>
						<c:when test="${deferral.approval=='True'}">
							<td>${deferral.approval}</td>
						</c:when>
						<c:otherwise>

							<td><a
								href="<%= request.getContextPath() %>/admin/deferral/listall/${deferral.moduleId}/${deferral.studentId}"
								class="ui-btn ui-corner-all">Confirm Approval</a></td>
						</c:otherwise>
					</c:choose>
					<td><a
						href="<%= request.getContextPath() %>/admin/deferral/downloadForm/id/${deferral.defId}"
						download target="_blank" class="ui-btn ui-corner-all">Download
							Withdrawal Form</a></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/deferral/delete/id/${deferral.defId}"
						class="ui-btn ui-corner-all">Delete</a></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/deferral/modify/id/${deferral.defId}"
						class="ui-btn ui-corner-all">Modify</a></td>



				</tr>
			</c:forEach>
		</tbody>

	</table>


</c:if>

<c:if test="${empty deferrals}">
	<div class="notification warning">No deferals found.</div>
</c:if>
