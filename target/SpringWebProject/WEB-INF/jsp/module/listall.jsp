<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty modules}">

<h4>${message}</h4>

<table  data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
    <th data-priority="1">Module ID </th>
    <th data-priority="3">CRN Number</th>
    <th >Module Name</th>
    <th data-priority="2">Lecturer ID</th>
    <th data-priority="4">Semester ID</th>    
    <th data-priority="5">Delete</th> 
    <th data-priority="6">Modify</th>
    </tr>
  </thead>

  <tbody>
  
      <c:forEach var="module" items="${modules}">
    <tr>
      
      <td><a href="<%= request.getContextPath() %>/admin/module/list/id/${module.moduleId}"
						class="ui-btn ui-corner-all">${module.moduleId}</a></td>
      <td><h3>${module.crnNumber}</h3></td>
      <td><h3>${module.name}</h3></td>
      <td><a href="<%= request.getContextPath() %>/admin/lecturer/display/id/${module.lectId}"
						class="ui-btn ui-corner-all">${module.lectId}</a></td> 
      <td><h3>${module.semesterId}</h3></td>   
    
              
      <td><a href="<%= request.getContextPath() %>/admin/module/delete/id/${module.moduleId}/crn/${module.crnNumber}"
						class="ui-btn ui-corner-all">Delete</a></td> 
	  <td><a href="<%= request.getContextPath() %>/admin/module/modify/id/${module.moduleId}/crn/${module.crnNumber}"
						class="ui-btn ui-corner-all">Modify</a></td> 
		
               
    </tr>
     </c:forEach>
  </tbody>
 
</table>	
		
	</c:if>
	
	<c:if test="${empty modules}">
	<div class="notification warning">
		No modules.
	</div>
	</c:if> 
