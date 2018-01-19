<%@ include file="/WEB-INF/jsp/include.jsp"%>

<h2 class=ui-field-contains2>${ message}</h2>
<table data-role="table" class="ui-responsive" data-mode="" id="myTable">
	<thead>
		<tr>
			<th data-priority="5">Module ID</th>
			<th data-priority="3">CRN Number</th>
			<th data-priority="4">Module Name</th>
			<th data-priority="1">Lecturer ID</th>
			<th data-priority="2">Semester ID</th>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td>${moduleId}</td>
			<td>${crnNumber}</td>
			<td>${name}</td>
			<td>${lectId}</td>
			<td>${semesterId}</td>

		</tr>
	</tbody>

</table>


<c:if test="${empty moduleId}">
	<div class="notification warning">No modules.</div>
</c:if>
