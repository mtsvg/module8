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

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ToDoList toDoList = new ToDoList();
        response.setContentType("text/html");
        String taskToAdd = request.getParameter("tasks");
        toDoList.updateList();
        toDoList.addToList(taskToAdd);

        try {
            transaction.begin();

            TasktableEntity tasktableEntity = new TasktableEntity();
            tasktableEntity.setTask(taskToAdd);
            entityManager.persist(tasktableEntity);
            transaction.commit();



        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
            entityManagerFactory.close();
        }


        PrintWriter out = response.getWriter();
        out.println("Task: '" + taskToAdd + "' was added to the list");
        out.println( "<p>");
        out.println( "<a href=\"show-servlet\">Show List</a>");
        out.println( "<a href=add.jsp>Add Task</a>");
        out.println( "<a href=\"remove.jsp\">Remove Task</a>");
        out.println( "</p>");
    }
}
