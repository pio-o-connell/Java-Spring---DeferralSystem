<%@ include file="/WEB-INF/jsp/include.jsp"%>

<!--  @author Julia Foden -->

<form:form method="POST" action="moduleSearchResult"
	modelAttribute="module">



	<div class="moduleDropdown">
		<form:select path="moduleId" id="moduleId">
			<form:option value="moduleId">Choose Module's ID</form:option>
			<c:forEach var="module" items="${moduleIdList}">
				<form:option value="${module.moduleId}"></form:option>
			</c:forEach>
			<form:errors path="moduleId" cssClass="error"
				class="notification error" style="display:block"></form:errors>
		</form:select>
	</div>


	<input data-theme="b" type="submit" data-icon="search" value="Search" />
</form:form>

