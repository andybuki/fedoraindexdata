<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: andrey.buchmann
  Date: 09.08.21
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Fedora</title>
</head>
<body>
<h2>Delete Fedora Collection Part</h2>
<h4>Fedora URL:</h4>
${fedora.url}

<h4> Fedora Collection:</h4>
${fedora.collection}

<h4>Document Type:</h4>
${fedora.document}

<h4>
    Deleted Collection Part: ${fedora.collection}
    <br>
    Deleted ${count} Books
</h4>

<a href="/fedora/delete">Back</a>
</body>
</html>
