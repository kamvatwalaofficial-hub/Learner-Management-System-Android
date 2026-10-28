# Learner Management System (Android)

**A native Android app for managing students, instructors, modules and tasks, with separate dashboards for administrators, instructors and students.**

I built this app in 2025 as an academic project, a year after building my first learner management system as a full-stack web application. It extended my experience from web to mobile development, working directly with Android activities, layouts and an on-device SQLite database.

---

## 🎥 Video Demo

<!-- Replace the link below with the unlisted YouTube URL -->
[▶ Watch the demo](#)

---

## Features

| Role | What they can do |
| --- | --- |
| **Administrator** | Create students and instructors (each with their own login), create modules, and log out |
| **Instructor** | Create tasks with a due date and assign them to a module chosen from a list |
| **Student** | View all tasks, with their due dates and modules |

- **Role-based login:** one login screen sends each user to the dashboard for their role.
- **Account creation by the administrator:** a profile and its login account are saved together in a single database transaction, and duplicate usernames are rejected.
- **On-device storage:** all data is stored locally in SQLite, so the app works without an internet connection.
- **Input validation:** every form checks for empty fields before saving.

## Tech Stack

| Layer | Technology |
| --- | --- |
| Language | Java |
| Platform | Android SDK (target SDK 35, minimum SDK 30 / Android 11) |
| UI | XML layouts, AppCompat, Material Components |
| Database | SQLite via `SQLiteOpenHelper` |
| Build | Gradle (Kotlin DSL), Android Studio |

### Data model

```
users ─── login accounts (username, password, role)
students        instructors        modules ──< tasks
```

## Running the App

1. Open the project in **Android Studio**.
2. Let Gradle sync, then run the `app` configuration on an emulator or an Android 11+ device.
3. Log in with the default administrator account: **username** `admin`, **password** `admin123`.
4. Create an instructor and a student (each with a username and password), log out, and log in as each of them.

## Security Notes and What I Would Improve

As I have moved into cybersecurity, I reviewed this project with a security lens. What it does well:

- Login uses **parameterised SQL queries**, which protect against SQL injection.
- Only the launcher screen is exported; every other screen is internal to the app.
- The password field is masked.

What I would change before using it with real users:

- **Hash passwords.** Passwords are stored in plain text. I would hash them with a slow, salted algorithm such as PBKDF2 or bcrypt.
- **Remove the default administrator password.** `admin123` is written into the code. I would require a new password on first login.
- **Protect the local database.** Android backup is enabled, so the database could be copied off the device. I would exclude it from backups and encrypt it, for example with SQLCipher.
- **Track task completion.** The database already has a `student_tasks` table for this, but the screens do not use it yet.
- **Improve usability:** date pickers instead of free-text dates, module names instead of IDs in the task list, and editing and deleting records.

## Changes Since Submission

The first commit in this repository is the app as I originally submitted it. The second commit fixes a login bug I found while preparing it for my portfolio: roles were stored in lowercase but compared with capitalised names, so no one could log in. I also added username and password fields when creating students and instructors, so all three roles can sign in, and added a Log Out button for administrators.

---

**Kamva Twala** · [kamvatwala.com](https://kamvatwala.com)
