<%@ include file="/WEB-INF/jsp/include.jsp"%>



<!--  @author Dale Cusack -->

<form:form method="POST" action="displayAProgToModify"
	modelAttribute="aProgToModify">

	<div class="ui-field-contain">
		<form:label path="programmeId">Programmes Id</form:label>
		<form:input path="programmeId" placeholder="programme Id to search"
			value="" />
	</div>

	<input data-theme="b" type="submit" class="button" data-icon="check"
		value="Programme To Modify" />
</form:form>
