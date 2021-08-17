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
    <title>Show Fedora</title>
</head>
<body>

<h4>Fedora URL:</h4>
${fedora.url}

<h4> Fedora Collection:</h4>
${fedora.collection}

<h4>Document Type:</h4>
${fedora.document}

<h4>Path:</h4>
/mnt/fedora/raw/${fedora.collection}${fedora.path}

<h4>
    Added Collection: ${fedora.collection}
    <br>
    Added ${count} Books
</h4>

<a href="/fedora/askSettings">Back</a>
</body>
</html>
