# MyMenu 🥗

Android app for weekly meal planning, built with **Clean Architecture**, **Jetpack Compose** and a **multi-module** setup.

---

## Tech stack

| Layer | Library / Tool |
|---|---|
| UI | Jetpack Compose + Material 3 |
| Fonts | Compose Google Fonts |
| State management | Kotlin StateFlow + MVI |
| Async | Kotlin Coroutines |
| DI | Koin 4 |
| Language | Kotlin 2.2 |
| Build | AGP 9.2 + Gradle version catalog |

---

## Module structure

```
mymenu/
├── app/                          # Entry point. Wires Koin graph and hosts NavHost.
│
├── core/
│   ├── domain/                   # Base use-case contracts, UseCaseResult, DispatcherProvider
│   ├── data/                     # Base repository helpers, CoreDataModule (Koin)
│   └── presentation/             # Design system (theme, typography, colors), base MVI classes, shared composables
│
└── feature/
    └── authentication/
        ├── domain/               # AuthRepository interface + LoginUseCase
        ├── data/                 # AuthRepositoryImpl
        ├── presentation/         # Login screen, LoginViewModel, LoginScreenContract
        └── di/                   # Aggregates the 3 Koin modules above into a single authFeatureModule
```

Each feature follows the same vertical slice: **domain → data → presentation → di**.

---

## Architecture

The project follows **Clean Architecture** with three layers per feature:

```
Presentation  ──►  Domain  ──►  Data
(ViewModel)      (UseCase)    (Repository)
```

- **Domain** is a pure Kotlin module. No Android dependencies.
- **Data** depends on domain and implements its interfaces.
- **Presentation** depends on domain only — never on data directly.
- **app** depends on the `:feature:X:di` aggregator — one import per feature.

### MVI

Every screen uses a shared MVI contract defined in `core:presentation`:

```kotlin
// core/presentation
interface Contract {
    interface Intent   // user actions
    interface State    // UI state (data class, immutable)
    interface Effect   // one-shot events (navigation, toasts)
}
```

Each screen declares its own contract object:

```kotlin
object LoginScreenContract {
    data class State(val isLoading: Boolean, val username: String, val password: String)

    sealed interface Intent : Contract.Intent {
        data class OnUserNameChange(val newValue: String) : Intent
        data class OnPasswordChange(val newValue: String) : Intent
        data object OnLoginClick : Intent
    }

    sealed interface Effect : Contract.Effect {
        data class ShowToast(val message: String) : Effect
    }
}
```

ViewModels extend `BaseViewModel<State, Intent, Effect>` and expose a single `onIntent(intent)` entry point.

### Use cases

Use cases return `UseCaseResult<T>` — a sealed class with `Success` and `Error` variants — with a chainable extension API:

```kotlin
loginUseCase.execute(params)
    .onSuccess { /* ... */ }
    .onError   { /* ... */ }
    .finally   { hideLoading() }
```

### Dependency injection

Koin modules are declared close to their layer and aggregated at the feature level:

```
:feature:authentication:di
    └── authFeatureModule = authDataModule + authDomainModule + authPresentationModule

:app
    └── appModules = coreDataModule + authFeatureModule
```

Adding a new feature to the app only requires one line in `AppModules.kt`.

---

## Project setup

1. Clone the repo
2. Open in Android Studio Hedgehog or newer
3. Let Gradle sync finish
4. Run the `app` configuration on a device or emulator

No API keys or external services are required to build.
