<%--
  Created by IntelliJ IDEA.
  User: ysj
  Date: 2019/5/23
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>File Upload</h4>
<form action="upload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Please select the uploaded file:</td>
            <td><input type="file" name="file" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="upload"></td>
        </tr>
    </table>
</form>
</body>
</html>
