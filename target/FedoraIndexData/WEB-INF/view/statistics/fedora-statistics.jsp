<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
        Created by IntelliJ IDEA.
        User: andrey.buchmann
        Date: 09.08.21
        Time: 15:44
        To change this template use File | Settings | File Templates.
        --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Statistics in Fedora</title>
</head>
<body>
<h2>Display statistics</h2>
<form:form action="statistics_show" modelAttribute="fedora">
    <h4>Choose Fedora URL:</h4>
    <form:select path="url">
        <form:options items="${fedora.urls}"></form:options>
    </form:select>
    <h4>Choose Fedora Collection:</h4>
    <form:select path="collection">
        <form:options items="${fedora.collections}"></form:options>
    </form:select>
    <input type="submit" value="Display statistics">
</form:form>

<a href="/fedora">Back</a>

</body>
</html>
