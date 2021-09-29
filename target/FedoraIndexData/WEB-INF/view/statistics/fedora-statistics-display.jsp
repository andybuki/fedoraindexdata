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

<h4>Fedora URL:</h4>
${fedora.url}

<h4> Fedora Collection:</h4>
${fedora.collection}

<h4> In Collection: ${fedora.collection} are: ${count_books} books</h4>

<h4> In Collection: ${fedora.collection} are: ${count_pages} pages</h4>

<h4> In Collection: ${fedora.collection} are: ${count_images} images</h4>
<a href="/fedora/statistics">Back</a>

</body>
</html>
