<%--
    Document   : index
    Created on : 02-Sep-2022, 7:25:28 pm

    Author     : solai
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Currency Converter</title>
</head>
<body>
<%--@declare id="thisdate"--%><h1>The Currency Value Checker</h1>
<div class="drop-list">
    <div>

        <%--@declare id="amount"--%><form action="servlet" method="get">
        <label for="amount"> Enter Amount  : <input required type="number" name="amount">
            <br><br>

            <label for="fromCurrency">From :</label>
            <select name="fromCurrency" id="fromCurrency" onchange="removeToCurrency()">
                <option selected value="Choose Currency">Choose Currency</option>
            </select>

    </div>
    <br>

    <label for="toCurrency">To :</label>

    <select name="toCurrency" id="toCurrency" >
        <option selected value="Choose Currency">Choose Currency</option>
    </select>

</div>
<br>

<label for="thisDate"> Date: </label>
<input type="date" value="thisDate" min="2002-01-01" name="thisDate"><br><br>

<input type="submit" value="convert">

<h3><%= request.getAttribute("param")!=null?request.getAttribute("param"):"" %></h3>

    </form>
    <script src="js/script.js"></script>
</body>
</html>
