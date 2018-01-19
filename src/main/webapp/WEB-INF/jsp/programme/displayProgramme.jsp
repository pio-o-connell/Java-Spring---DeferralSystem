<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Dale Cusack, modified by Julia Foden-->

<h3>${message}</h3>

<table data-role="table" class="ui-responsive" data-mode="" id="myTable">
	<thead>
		<tr>
			<th data-priority="3">Programme ID</th>
			<th data-priority="4">Number Years</th>
			<th data-priority="1">Coordinator</th>
			<th data-priority="2">Programme year</th>
		</tr>
	</thead>

	<tbody>

		<tr>
			<td><h3>${programmeId}</h3></td>
			<td><h3>${numYears}</h3></td>
			<td><a
				href="<%= request.getContextPath() %>/admin/lecturer/display/id/${coordinatorId}"
				class="ui-btn ui-corner-all">${coordinatorId}</a></td>
			<td><h3>${progYear}</h3></td>
		</tr>

	</tbody>

</table>

<c:if test="${not empty deferrals}">
	<div data-role="main" class="ui-content">
		<a
			href="<%= request.getContextPath() %>/admin/deferral/list/id/${programmeId}"
			class="ui-btn ui-corner-all">View deferrals from this programme</a>
	</div>
</c:if>
<c:if test="${empty deferrals}">
	<div class="notification warning">
		<p>No deferrals from this programme</p>
	</div>
</c:if>