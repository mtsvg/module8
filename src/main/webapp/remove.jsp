<%--
  Created by IntelliJ IDEA.
  User: matthew
  Date: 3/5/23
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove</title>
</head>
<body>

<form name= "myForm" action="RemoveServlet" method="post">
    <label for="task">Task</label>
    <input id="task" type="number" name="taskNumber">
    <button type="submit">Submit</button>
</form>
</body>
</html>
