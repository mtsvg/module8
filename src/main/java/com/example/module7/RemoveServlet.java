package com.example.module7;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import tasktable.TasktableEntity;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ToDoList toDoList = new ToDoList();
        response.setContentType("text/html");
        toDoList.updateList();


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Whick task number would you like to remove?</h1>");
        out.println("<p>");
        for(int i = 0; i < toDoList.getLength(); i++){
            out.println("<br>");
            out.println((i + 1) + ": " + toDoList.list.get(i));

        }
        out.println("<form name= \"myForm\" action=\"AddServlet\" method=\"post\">\n" +
                "    <label for=\"task\">Task</label>\n" +
                "    <input id=\"task\" type=\"text\" name=\"taskNumber\">\n" +
                "    <button type=\"submit\">Submit</button>\n" +
                "</form>");
        out.println("</p>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ToDoList toDoList = new ToDoList();
        response.setContentType("text/html");
        toDoList.updateList();

        EntityManagerFactory entityManagerFactory2 = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager2 = entityManagerFactory2.createEntityManager();
        EntityTransaction transaction2 = entityManager2.getTransaction();




        // remove a task from the list and confirm which task it was to the user
        System.out.println("Which number would you like to remove: ");
         int removeIndex = Integer.parseInt(request.getParameter("taskNumber"));
         removeIndex = removeIndex -1;
        String removedTask = toDoList.getTask(removeIndex);
        toDoList.removeFromList(removeIndex);
        System.out.println("Removed Task: '" + removedTask + "'");




        try {
            PrintWriter out = response.getWriter();
            transaction2.begin();

            TasktableEntity tasktableEntity = new TasktableEntity();
            tasktableEntity.setTask(removedTask);
            entityManager2.remove(entityManager2.merge(tasktableEntity));
            transaction2.commit();
            out.println( "<p>");
            out.println( "'"+ removedTask + "' was removed from the list");
            out.println( "</p>");
            out.println( "<p>");
            out.println( "<a href=\"show-servlet\">Show List</a>");
            out.println( "<a href=add.jsp>Add Task</a>");
            out.println( "<a href=\"remove.jsp\">Remove Task</a>");
            out.println( "</p>");

        } finally {
            if (transaction2.isActive()) {
                transaction2.rollback();
            }
            entityManager2.close();
            entityManagerFactory2.close();
        }
    }
}
