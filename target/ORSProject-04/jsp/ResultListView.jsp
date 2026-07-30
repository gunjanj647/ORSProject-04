<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.ResultListCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.ResultBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>

<head>

<title>Result List</title>

<link rel="icon" type="image/png"
href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>


<body>


<%@include file="Header.jsp"%>


<div align="center">


<h1 align="center"
style="margin-bottom:-15;color:navy;">
Result List
</h1>



<div style="height:15px;margin-bottom:12px">

<h3>
<font color="red">
<%=ServletUtility.getErrorMessage(request)%>
</font>
</h3>


<h3>
<font color="green">
<%=ServletUtility.getSuccessMessage(request)%>
</font>
</h3>


</div>



<form action="<%=ORSView.RESULT_LIST_CTL%>" method="post">


<%

int pageNo =
ServletUtility.getPageNo(request);

int pageSize =
ServletUtility.getPageSize(request);


int index =
((pageNo-1)*pageSize)+1;


int nextPageSize =
DataUtility.getInt(
request.getAttribute("nextListSize").toString());



List<ResultBean> list =
(List<ResultBean>)ServletUtility.getList(request);


Iterator<ResultBean> it =
list.iterator();



if(list.size()!=0){

%>


<input type="hidden" name="pageNo"
value="<%=pageNo%>">


<input type="hidden" name="pageSize"
value="<%=pageSize%>">





<table style="width:100%">


<tr>

<td align="center">


<label><b>Result ID :</b></label>

<input type="text"
name="resultId"
placeholder="Enter Result ID"
value="<%=ServletUtility.getParameter("resultId",request)%>">


&nbsp;&nbsp;


<label><b>Student ID :</b></label>


<input type="text"
name="studentId"
placeholder="Enter Student ID"
value="<%=ServletUtility.getParameter("studentId",request)%>">


&nbsp;&nbsp;


<label><b>Grade :</b></label>


<input type="text"
name="grade"
placeholder="Enter Grade"
value="<%=ServletUtility.getParameter("grade",request)%>">


&nbsp;&nbsp;


<label><b>Status :</b></label>


<input type="text"
name="resultStatus"
placeholder="Enter Status"
value="<%=ServletUtility.getParameter("resultStatus",request)%>">


&nbsp;


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_SEARCH%>">


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_RESET%>">


</td>

</tr>

</table>



<br>



<table border="1"
style="width:100%;border:groove;">



<tr style="background-color:#e1e6f1e3;">


<th width="5%">
<input type="checkbox" id="selectall"/>
</th>


<th width="5%">S.No</th>


<th width="15%">Result ID</th>


<th width="15%">Student ID</th>


<th width="15%">Percentage</th>


<th width="15%">Grade</th>


<th width="15%">Result Status</th>


<th width="10%">Edit</th>



</tr>




<%

while(it.hasNext()){


ResultBean bean = it.next();


%>



<tr>


<td style="text-align:center;">

<input type="checkbox"
class="case"
name="ids"
value="<%=bean.getId()%>">

</td>



<td style="text-align:center;">
<%=index++%>
</td>



<td style="text-align:center;">
<%=bean.getResultId()%>
</td>



<td style="text-align:center;">
<%=bean.getStudentId()%>
</td>



<td style="text-align:center;">
<%=bean.getPercentage()%>
</td>



<td style="text-align:center;">
<%=bean.getGrade()%>
</td>



<td style="text-align:center;">
<%=bean.getResultStatus()%>
</td>



<td style="text-align:center;">

<a href="ResultCtl?id=<%=bean.getId()%>">
Edit
</a>

</td>



</tr>


<%

}

%>



</table>





<table style="width:100%">


<tr>


<td style="width:25%">


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_PREVIOUS%>"
<%=pageNo>1?"":"disabled"%>>


</td>




<td align="center"
style="width:25%">


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_NEW%>">


</td>




<td align="center"
style="width:25%">


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_DELETE%>">


</td>




<td align="right"
style="width:25%">


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_NEXT%>"
<%=nextPageSize!=0?"":"disabled"%>>


</td>



</tr>


</table>




<%

}


if(list.size()==0){

%>



<table>

<tr>

<td align="right">


<input type="submit"
name="operation"
value="<%=ResultListCtl.OP_BACK%>">


</td>


</tr>


</table>



<%

}

%>



</form>


</div>


<%@include file="Footer.jsp"%>


</body>

</html>