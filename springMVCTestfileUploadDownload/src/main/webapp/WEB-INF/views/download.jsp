<%--
  Created by IntelliJ IDEA.
  User: ysj
  Date: 2019/5/23
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>Click the link to download</h4>
<a href="download?filename=${requestScope.fileinfo.file.originalFilename}">${requestScope.fileinfo.file.originalFilename}</a>
</body>
</html>
