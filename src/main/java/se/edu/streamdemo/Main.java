package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();


        System.out.println("Printing all data ...");
        //printAllData(tasksData);
        //printDataUsingStreams(tasksData);


        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStreams(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));


        ArrayList<Task> filteredlist = filterList(tasksData, "11");
        printAllData(filteredlist);

        System.out.println("Total number of deadlines using streams: " + countDeadlinesUsingStreams(tasksData));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStreams(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(task -> task instanceof Deadline)
                .count(); //count returns a long, so need to cast


        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data using iteration...");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams (ArrayList<Task> tasks) {
        System.out.println("Printing data using streams ...");
        tasks.stream()
                .forEach(System.out::println);
    }



    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using iteration...");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }



    public static ArrayList<Task> filterList(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredlist = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());
        return filteredlist;
    }
    public static void printDeadlinesUsingStreams (ArrayList<Task> tasks) {
        System.out.println("Printing deadlines using streams using streams ...");
        tasks.stream()   //use parallelStream() for large data sets(faster)
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);
    }

}

