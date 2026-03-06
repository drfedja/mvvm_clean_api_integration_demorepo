# GitHub Test App

A simple Android application built around the GitHub API, designed to demonstrate **scalable architecture** with **MVVM**, **coroutines**, and **dependency injection** (Hilt). The app is implemented in **Jetpack Compose** and is structured to be easily testable.

---

## Features

- **Two screens**
  1. **User Repos Screen**
     - Displays a list of repositories for a given user.
     - Shows the number of opened issues for each repository.
     - Clicking on a repository navigates to the Repo Details screen.
  2. **Repo Details Screen**
     - Displays the user's avatar and name, repository name, number of forks, and watchers in the header.
     - Below the header, a scrollable list of repository tags is displayed, including **commit name** and **SHA** for each tag.

- **Network & API**
  - Uses GitHub REST API.
  - Supports mocking API calls via DI, making it easy to test without hitting real endpoints.
  - API endpoints used:
    - User details: [https://api.github.com/users/octocat](https://api.github.com/users/octocat)
    - User repos: [https://api.github.com/users/octocat/repos](https://api.github.com/users/octocat/repos)
    - Repo details: [https://api.github.com/repos/octocat/{repo}](https://api.github.com/repos/octocat/{repo})
    - Repo tags: [https://api.github.com/repos/octocat/{repo}/tags](https://api.github.com/repos/octocat/{repo}/tags)

---

## Architecture

- **MVVM** architecture:
  - `ViewModel` handles state management using `StateFlow`.
  - `ScreenState` sealed interface represents `Loading`, `Success`, and `Failure` states.
- **Dependency Injection** with Hilt.
- **Coroutines** for async API calls.
- **Clean separation of layers**:
  - `Data Layer` for network calls.
  - `Domain Layer` for use cases.
  - `UI Layer` for composables and screen state.

---

## Testing

- **Unit Tests**:
  - All `ViewModels` and `UseCases` are covered with unit tests.
  - Coverage level: **>90%**
  - API calls are mocked to ensure deterministic testing.

---

## Technical Highlights

- **Jetpack Compose** for UI:
  - LazyColumn for lists.
  - AsyncImage (Coil) for avatar images.
  - Proper handling of `Loading` and `Error` states.
- **ViewModel-driven navigation**:
  - Parameters (`userName` and `repoName`) passed via navigation to next screen.
- **Testable setup**:
  - API calls can be mocked by replacing the repository in DI.
  - Use cases are isolated and easily unit-testable.
  
---

## Screenshots

*(Add screenshots of the app here if needed)*

---

## How to Run

1. Clone the repository:
   ```bash
   git clone <repository_url>
