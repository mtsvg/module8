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
    <title>Add</title>
</head>
<body>

<h1>
    Enter a task to add to the to-do list
</h1>
<form name= "myForm" action="AddServlet" method="post">
    <label for="task">Task</label>
    <input id="task" type="text" name="tasks">
    <button type="submit">Submit</button>
</form>

</body>
</html>
