package com.burale.tasktracker;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        if (args.length == 0) {
            System.out.println("Usage: task-cli [command] [arguments]");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":
                if (args.length >= 2) {
                    String description = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));
                    taskManager.addTask(description);
                } else {
                    System.out.println("Usage: task-cli add \"Task description\"");
                }
                break;

            case "update":
                if (args.length >= 3) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        String newDesc = String.join(" ", java.util.Arrays.copyOfRange(args, 2, args.length));
                        taskManager.updateTask(id, newDesc);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task ID.");
                    }
                } else {
                    System.out.println("Usage: task-cli update <id> \"New description\"");
                }
                break;

            case "delete":
                if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        taskManager.deleteTask(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task ID.");
                    }
                } else {
                    System.out.println("Usage: task-cli delete <id>");
                }
                break;

            case "mark-in-progress":
                if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        taskManager.markInProgress(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task ID.");
                    }
                } else {
                    System.out.println("Usage: task-cli mark-in-progress <id>");
                }
                break;

            case "mark-done":
                if (args.length == 2) {
                    try {
                        int id = Integer.parseInt(args[1]);
                        taskManager.markDone(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid task ID.");
                    }
                } else {
                    System.out.println("Usage: task-cli mark-done <id>");
                }
                break;

            case "list":
                if (args.length == 1) {
                    taskManager.listTasks();
                } else if (args.length == 2) {
                    try {
                        String normalizedStatus = args[1].replace('-', '_').toUpperCase();
                        Task.Status status = Task.Status.valueOf(normalizedStatus);
                        taskManager.listByStatus(status);
                    } catch (IllegalArgumentException e) {
                        String supported = java.util.Arrays.stream(Task.Status.values())
                                .map(s -> s.name().toLowerCase().replace('_', '-'))
                                .collect(java.util.stream.Collectors.joining(", "));
                        System.out.println("Invalid status. Supported options: " + supported);
                    }
                } else {
                    System.out.println("Usage: task-cli list [status]");
                }
                break;


            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Available commands: add, update, delete, mark-in-progress, mark-done, list");
        }
    }
}
