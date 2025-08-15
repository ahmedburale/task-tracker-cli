package com.burale.tasktracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    @TempDir
    Path tempDir;

    @Test
    void markInProgressUpdatesStatusAndTimestamp() throws Exception {
        String originalUserDir = System.getProperty("user.dir");
        System.setProperty("user.dir", tempDir.toString());
        try {
            TaskManager manager = new TaskManager();
            manager.addTask("Test task");
            manager.markInProgress(1);

            Field tasksField = TaskManager.class.getDeclaredField("tasks");
            tasksField.setAccessible(true);
            @SuppressWarnings("unchecked")
            List<Task> tasks = (List<Task>) tasksField.get(manager);
            Task task = tasks.get(0);

            assertEquals(Task.Status.IN_PROGRESS, task.getStatus());
            assertTrue(task.getUpdatedAt().isAfter(task.getCreatedAt()));
        } finally {
            System.setProperty("user.dir", originalUserDir);
        }
    }
}

