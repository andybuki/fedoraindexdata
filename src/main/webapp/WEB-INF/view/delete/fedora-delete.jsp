<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: andrey.buchmann
  Date: 09.08.21
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Fedora </title>
</head>
<body>
<h2>Delete Fedora Collection Part</h2>

<form:form action="showDelete" modelAttribute="fedora">
    <h4>Choose Fedora URL:</h4>
    <form:select path="url">
        <form:options items="${fedora.urls}"></form:options>
    </form:select>
    <h4>Choose Fedora Collection:</h4>
    <form:select path="collection">
        <form:options items="${fedora.collections}"></form:options>
    </form:select>
    <h4>Choose Fedora Document Type:</h4>
    <form:select path="document">
        <form:options items="${fedora.documents}"></form:options>
    </form:select>

    <input type="submit" value="Delete collection Part">
</form:form>

<a href="/fedora">Back</a>
</body>
</html>
