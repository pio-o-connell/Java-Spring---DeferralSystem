<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Dale Cusack, modified by Julia Foden -->

<h4>${message}</h4>


<c:if test="${not empty programmes}">
	<table data-role="table" class="ui-responsive" data-mode=""
		id="myTable">
		<thead>
			<tr>
				<th data-priority="3">Programme ID</th>
				<th data-priority="4">Number Years</th>
				<th data-priority="1">Coordinator</th>
				<th data-priority="2">Programme year</th>
				<th data-priority="5">Delete</th>
				<th data-priority="6">Modify</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="programme" items="${programmes}" varStatus="status">
				<tr>
					<td><a
						href="<%= request.getContextPath() %>/admin/programme/display/id/${programme.programmeId}"
						class="ui-btn ui-corner-all">${programme.programmeId}</a></td>
					<td><h3>${programme.numYears}</h3></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/lecturer/display/id/${programme.coordinatorId}"
						class="ui-btn ui-corner-all">${programme.coordinatorId}</a></td>
					<td><h3>${programme.progYear}</h3></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/programme/delete/${status.current.programmeId}"
						class="ui-btn ui-corner-all">Delete</a></td>
					<td><a
						href="<%= request.getContextPath() %>/admin/programme/modifyProgBtn/${status.current.programmeId}"
						class="ui-btn ui-corner-all">Modify</a></td>


				</tr>
			</c:forEach>
		</tbody>

	</table>

</c:if>

<c:if test="${empty programmes}">
	<div class="notification warning">No Programmes.</div>
</c:if>
