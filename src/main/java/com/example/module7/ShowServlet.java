package com.example.module7;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "showServlet", value = "/show-servlet")
public class ShowServlet extends HttpServlet {
    private String message;


    public void init() {
        message = "My to-do list!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ToDoList toDoList = new ToDoList();
        response.setContentType("text/html");
        toDoList.updateList();


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<p>");
        for(int i = 0; i < toDoList.getLength(); i++){
            out.println("<br>");
            out.println((i + 1) + ": " + toDoList.list.get(i));

        }
        out.println("</p>");
        out.println( "<p>");
        out.println( "<a href=\"show-servlet\">Show List</a>");
        out.println( "<a href=add.jsp>Add Task</a>");
        out.println( "<a href=\"remove.jsp\">Remove Task</a>");
        out.println( "</p>");
        out.println("</body></html>");
    }
    public void destroy() {
    }
}