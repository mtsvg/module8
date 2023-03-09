package com.example.module7;

import jakarta.persistence.*;
import tasktable.TasktableEntity;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    //make and arraylist
    public ArrayList<String> list = new ArrayList<String>();
    //add item to list
    public void addToList (String task) {
        list.add(task);
    }

    //populate list from database
    public  void updateList(){

        EntityManagerFactory entityManagerFactory3 = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager3 = entityManagerFactory3.createEntityManager();
        EntityTransaction transaction3 = entityManager3.getTransaction();
        try {
            transaction3.begin();

            TasktableEntity tasktableEntity = new TasktableEntity();
            Query allQuery = entityManager3.createNativeQuery("SELECT * from tasktable");
            for(int i = 0; i< allQuery.getResultList().size(); i++){
                list.add(allQuery.getResultList().get(i).toString());
            }
            System.out.println(allQuery.getResultList().get(0));
            transaction3.commit();



        } finally {
            if (transaction3.isActive()) {
                transaction3.rollback();
            }
            entityManager3.close();
            entityManagerFactory3.close();
        }
    }
    //removes and item from the list
    public void removeFromList (int index) {
        list.remove(index);
    }

    // displays the list
    public void displayList(){
        for(int i = 0; i < list.size(); i++){
            System.out.println(i + " : " + list.get(i));

        }
    }

    //print the directions
    public void howTo() {
        System.out.println("Type 1 to display directions");
        System.out.println("Type 2 to display the to-do list");
        System.out.println("Type 3 to add a task to the list");
        System.out.println("Type 4 to remove a task from the list");
        System.out.println("Type 5 to quit");



    }
    // gets the task at the specified position of the arraylist
    public String getTask(int i) {
        return list.get(i);
    }
    // gets the length of the list
    //used when checking if it is populated
    public int getLength(){
        return list.size();
    }
}
