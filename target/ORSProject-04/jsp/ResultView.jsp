<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.controller.ResultCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.ResultBean"%>

<html>
<head>
<title>Add Result</title>

<link rel="icon" type="image/png"
href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>

<body>

<form action="<%=ORSView.RESULT_CTL%>" method="POST">

<%@include file="Header.jsp"%>


<jsp:useBean id="bean"
class="in.co.rays.proj4.bean.ResultBean"
scope="request"></jsp:useBean>


<div align="center">

<h1 align="center" style="margin-bottom:-15;color:navy">

<%
if(bean != null && bean.getId()>0){
%>
Update Result
<%
}else{
%>
Add Result
<%
}
%>

</h1>


<div style="height:15px;margin-bottom:12px">

<h3>
<font color="green">
<%=ServletUtility.getSuccessMessage(request)%>
</font>
</h3>


<h3>
<font color="red">
<%=ServletUtility.getErrorMessage(request)%>
</font>
</h3>

</div>



<input type="hidden" name="id" value="<%=bean.getId()%>">


<table>


<tr>
<th align="left">Result Id<span style="color:red">*</span></th>

<td>
<input type="text" 
name="resultId"
placeholder="Enter Result Id"
value="<%=DataUtility.getStringData(bean.getResultId())%>">
</td>

<td>
<font color="red">
<%=ServletUtility.getErrorMessage("resultId",request)%>
</font>
</td>

</tr>



<tr>

<th align="left">
Student Id<span style="color:red">*</span>
</th>

<td>
<input type="text"
name="studentId"
placeholder="Enter Student Id"
value="<%=DataUtility.getStringData(bean.getStudentId())%>">
</td>

<td>
<font color="red">
<%=ServletUtility.getErrorMessage("studentId",request)%>
</font>
</td>

</tr>



<tr>

<th align="left">
Percentage<span style="color:red">*</span>
</th>

<td>
<input type="text"
name="percentage"
placeholder="Enter Percentage"
value="<%=bean.getPercentage()%>">
</td>


<td>
<font color="red">
<%=ServletUtility.getErrorMessage("percentage",request)%>
</font>
</td>

</tr>



<tr>

<th align="left">
Grade<span style="color:red">*</span>
</th>

<td>
<input type="text"
name="grade"
placeholder="Enter Grade"
value="<%=DataUtility.getStringData(bean.getGrade())%>">
</td>


<td>
<font color="red">
<%=ServletUtility.getErrorMessage("grade",request)%>
</font>
</td>

</tr>




<tr>

<th align="left">
Result Status<span style="color:red">*</span>
</th>

<td>
<input type="text"
name="resultStatus"
placeholder="Enter Result Status"
value="<%=DataUtility.getStringData(bean.getResultStatus())%>">
</td>


<td>
<font color="red">
<%=ServletUtility.getErrorMessage("resultStatus",request)%>
</font>
</td>

</tr>



<tr>

<th></th>

<td>

<%
if(bean != null && bean.getId()>0){
%>

<input type="submit"
name="operation"
value="<%=ResultCtl.OP_UPDATE%>">


<input type="submit"
name="operation"
value="<%=ResultCtl.OP_CANCEL%>">


<%
}else{
%>


<input type="submit"
name="operation"
value="<%=ResultCtl.OP_SAVE%>">


<input type="submit"
name="operation"
value="<%=ResultCtl.OP_RESET%>">


<%
}
%>

</td>

</tr>


</table>


</div>


</form>


<%@include file="Footer.jsp"%>

</body>
</html>