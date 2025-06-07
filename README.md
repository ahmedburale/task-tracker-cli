# 📌 Task Tracker CLI

A command-line Java application to track and manage your tasks. This project helps you learn filesystem I/O, user input handling, and JSON-based storage — all without frameworks.

## 🚀 Features

- Add, update, and delete tasks
- Mark tasks as TODO, IN_PROGRESS, or DONE
- List tasks or filter by status
- Persistent storage using `tasks.json`

## 📦 Usage Examples

> 📁 Assumes you're running from project root: `task-tracker-cli`

> 💡 These examples require your compiled `.class` files and Jackson dependencies to be included in the classpath.

```bash
# Add a new task
java -cp "target\classes;C:\Users\catab\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.17.0\jackson-core-2.17.0.jar;C:\Users\catab\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.17.0\jackson-databind-2.17.0.jar;C:\Users\catab\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.17.0\jackson-annotations-2.17.0.jar;C:\Users\catab\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.17.0\jackson-datatype-jsr310-2.17.0.jar" com.burale.tasktracker.Main add "Buy groceries"

# List all tasks
java -cp "target\classes;C:\Users\catab\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.17.0\jackson-core-2.17.0.jar;C:\Users\catab\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.17.0\jackson-databind-2.17.0.jar;C:\Users\catab\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.17.0\jackson-annotations-2.17.0.jar;C:\Users\catab\.m2\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.17.0\jackson-datatype-jsr310-2.17.0.jar" com.burale.tasktracker.Main list

# Update a task
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main update 1 "New description"

# Delete a task
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main delete 1

# Mark a task as in progress or done
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main mark-in-progress 1
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main mark-done 1

# List tasks by status
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main list todo
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main list done
java -cp "target\classes;[same classpath]" com.burale.tasktracker.Main list in-progress

---

## 🌐 Project Page

This project was completed as part of the [Roadmap.sh Task Tracker Project](https://roadmap.sh/projects/task-tracker).

