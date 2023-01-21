<%@ page import="org.model.Currency" %>
<%@ page import="java.util.List" %>
<%@ page import="org.dao.CurncyDao" %>
<%@ page contentType="txt/html" pageEncoding="UTF-8"%>

<%
String fromCurrency = request.getParameter("fromCurrency");
List<Currency> curnc = CurncyDao.currencies;
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
</head>
<body>

    <form>
<div>
<label for="toCurrency">To :</label>
       <select name="toCurrency" id="toCurrency" >
       <option value="Choose Currency">Choose Currency</option>
       <%
           String fromCur = request.getParameter("fromCurrency");
           String toCurrenc;
           for(Currency curc:curnc)
           {
           if(!(curc.equals(fromCur)))
           %>
           <option value="<%=curc%>"> <%=curc%> </option>
           <%}
        %>
        <%toCurrenc = request.getParameter("toCurrency");%>
       </select>

       </div>
    </form>

       <br></br>

</body>
</html>