package com.burale.tasktracker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final File file = new File("tasks.json");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Task> tasks;

    public TaskManager() {
        tasks = loadTasks();
    }

    // Load tasks from JSON
    private List<Task> loadTasks() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Save tasks to JSON
    private void saveTasks() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generate next available ID
    private int generateNextId() {
        return tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
    }

    // Add new task
    public void addTask(String description) {
        Task task = new Task(generateNextId(), description);
        tasks.add(task);
        saveTasks();
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    // Update task
    public void updateTask(int id, String newDescription) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setDescription(newDescription);
            saveTasks();
            System.out.println("Task updated successfully.");
        }
    }

    // Delete task
    public void deleteTask(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            tasks.remove(task);
            saveTasks();
            System.out.println("Task deleted.");
        }
    }

    // Mark as in-progress
    public void markInProgress(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus(Task.Status.IN_PROGRESS);
            saveTasks();
            System.out.println("Task marked as in-progress.");
        }
    }

    // Mark as done
    public void markDone(int id) {
        Task task = findTaskById(id);
        if (task != null) {
            task.setStatus(Task.Status.DONE);
            saveTasks();
            System.out.println("Task marked as done.");
        }
    }

    // List all tasks
    public void listTasks() {
        tasks.forEach(System.out::println);
    }

    // List by status
    public void listByStatus(Task.Status status) {
        tasks.stream()
                .filter(t -> t.getStatus() == status)
                .forEach(System.out::println);
    }

    // Utility: find task
    private Task findTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseGet(() -> {
                    System.out.println("Task not found.");
                    return null;
                });
    }
}
