<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty modules}">
	<table data-role="table" class="ui-responsive" data-mode=""
		id="myTable">
		<thead>
			<tr>
				<th data-priority="1">Module ID</th>
				<th data-priority="3">CRN Number</th>
				<th>Module Name</th>
				<th data-priority="2">Lecturer ID</th>
				<th data-priority="4">Semester ID</th>

			</tr>
		</thead>

		<tbody>
			<c:forEach var="module" items="${modules}">
				<tr>

					<td><a
						href="<%= request.getContextPath() %>/admin/module/list/id/${module.moduleId}"
						class="ui-btn ui-corner-all">${module.moduleId}</a></td>
					<td><h3>${module.crnNumber}</h3></td>
					<td><h3>${module.name}</h3></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/lecturer/display/id/${module.lectId}"
						class="ui-btn ui-corner-all">${module.lectId}</a></td>
					<td><h3>${module.semesterId}</h3></td>




				</tr>
			</c:forEach>
		</tbody>

	</table>
	<c:if test="${not empty deferrals}">
		<div data-role="main" class="ui-content">
			<a
				href="<%= request.getContextPath() %>/deferral/list/moduleId/${id}"
				class="ui-btn ui-corner-all">View deferrals from this module</a>
		</div>
	</c:if>
	<c:if test="${empty deferrals}">
		<div class="notification warning">
			<p>No deferrals from this module</p>
		</div>
	</c:if>

</c:if>




