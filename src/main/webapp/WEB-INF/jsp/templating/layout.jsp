<%@ include file="/WEB-INF/jsp/include.jsp"%> 
<html>  
<head>
<title>
<tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.js"></script>
<style>
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}

td{
 border-top: 1px solid #d6d6d6;
 border-bottom: 1px solid #d6d6d6;
}
.error { color: #ff0000; }
.errorblock { 
color: #000; 
background-color: #ffEEEE;
border: 3px solid #ff0000; 
padding: 8px; 
margin: 16px; }
</style>

		
</head>

<body>

<div data-role="page">

<!--header--> 
<div data-role="header" data-position="fixed" data-theme="b" > 	
		<tiles:insertAttribute name="header"  />
</div> 

<!--main body with a panel to the left -->
		
<div data-role="main" class="ui-content" id="pageone">	
	<a href="#myPanelDefault" class="ui-btn ui-btn-inline ui-corner-all ui-shadow">Menu Options</a>
	<tiles:insertAttribute name="body" />
</div>			
		
<!--footer-->	
<div  data-role="footer" data-position="fixed" data-theme="b">
	<tiles:insertAttribute name="footer" />
</div>


<!-- Panel -->
<div data-role="panel" id="myPanelDefault" style="text-align:center;"> 
<div data-role="collapsibleset">
 
 <div id="panelImage">
<h1><a href="<%= request.getContextPath() %>/home">Home</a></h1>
</div>
	
	<div class="menuPosition">
 <div data-role="collapsible">
    <h1 data-icon="search">Add New</h1>
    <ul data-role="listview" data-inset="true">
       <li><a href="<%= request.getContextPath() %>/admin/lecturer/addNew">Lecturer</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/module/addNew">Module</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/programme/addNew">Programme</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/student/addNew">Student</a></li>
 	</ul>     
  </div>
  

  
  
  <div data-role="collapsible">
    <h1 data-icon="search">List All</h1>
    <ul data-role="listview" data-inset="true">
      <li><a href="<%= request.getContextPath() %>/admin/deferral/listall">Deferrals</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/lecturer/listall">Lecturers</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/module/listall">Modules</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/programme/listall">Programmes</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/student/listall">Students</a></li>
 	</ul>     
  </div>
  
  <div data-role="collapsible">

    <h1 data-icon="search">Advanced Search</h1>
    <ul data-role="listview" data-inset="true">
      <li><a href="<%= request.getContextPath() %>/admin/deferral/advancedlist">Deferral</a></li>
 	  <li><a href="<%= request.getContextPath() %>/admin/lecturer/advancedlist">Lecturer</a></li>
 	  <li><a href="<%= request.getContextPath() %>/admin/module/advancedlist">Module</a></li>
 	  <li><a href="<%= request.getContextPath() %>/admin/programme/details">Programme By Id</a></li>
 	  <li><a href="<%= request.getContextPath() %>/admin/programme/ofStudent">Student's Programme</a></li>
 	  <li><a href="<%= request.getContextPath() %>/admin/student/advancedlist">Student By Id</a></li>
 	</ul>     
  </div>
  
 <div data-role="collapsible">
    <h1 data-icon="search">Apply for Deferral</h1>
    <ul data-role="listview" data-inset="true">
      <li><a href="<%= request.getContextPath() %>/admin/deferral/addModuleDeferralPage1">Deferral by Module</a></li>
      <li><a href="<%= request.getContextPath() %>/admin/deferral/addProgrammeDeferralPage1">Deferral by Programme</a></li>
     </ul>     
  </div> 
		
  </div>  
  

<a href="#pageone" data-rel="close" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-left">Close Menu</a>
</div> 
</div>	

</div>
</body>  
</html>