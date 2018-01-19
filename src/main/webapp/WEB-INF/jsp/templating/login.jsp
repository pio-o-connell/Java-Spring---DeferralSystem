<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id="login-box">

<c:if test="${not empty error}">
		<div class="error">${error}</div>
	</c:if>
	<c:if test="${not empty msg}">
		<div class="msg">${msg}</div>
	</c:if>
		
<form name="f" action="<c:url value='/SpringWebProject/j_spring_security_check'/>" method="POST">

  <div class="ui-field-contain">
    <label for="fullname">Username:</label>
    <input type="text" name="j_username">
  </div>
  
  <div class="ui-field-contain">
    <label for="fullname">Password:</label>
    <input type="password" name="j_password">
  </div>

<input name="submit" type="submit" value="Login" />
</form>
</div>