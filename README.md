# 📰 Latest News App

A modern Android News Application built with Kotlin, MVVM, Clean Architecture, and Hilt. The app fetches the latest articles using [NewsAPI](https://newsapi.org), supports favorites, dark mode, and follows solid Android development principles.

---

## 📁 Project Structure

com.khalil.latestnews

│

├── data

│ ├── datasource

│ │ ├── local (Room Database)

│ │ └── remote (Retrofit API)

│ ├── repository (Implements domain layer)

│ └── model (DTOs, entities)

│

├── domain

│ ├── model (Pure business models)

│ └── usecases (Business logic)

| └── repository

│

├── presentation

│ ├── home

│ ├── favorites

│ ├── details

│ └── splash

│ └── profile

│

├── di (Dependency injection - Hilt modules)

└── utils (Constants, ApiState, etc.)


## 🌟 Bonus Points Handled

- ✅ Used **Clean Architecture** and proper modularization.
- ✅ **Hilt** for Dependency Injection.
- ✅ **Room Database** to store favorite articles.
- ✅ **Kotlin Flow** + `StateFlow` to observe and manage state.
- ✅ **Dark Mode** support using Material3 themes.
- ✅ **Navigation Component** with Safe Args.
- ✅ **DiffUtil + ListAdapter** for efficient RecyclerView updates.
- ✅ **PopupMenu** for article actions (Share, Favorite).

---

## ✨ Enhancements Added

- 🔄 Pull-to-refresh on Home screen using `SwipeRefreshLayout`.
- 🔍 Added article URL linking for external view (optional).
- 🎨 Better image loading and placeholder handling via Glide.
- 🌓 Manual Dark Mode toggle via `AppCompatDelegate`.

---

## 📌 Assumptions & Limitations

- The app uses `publishedAt` or `url` as a unique ID for favorite tracking.
- Articles cannot be edited or searched — just browsed and marked favorite.
- No pagination implemented — it loads articles in one shot.
- No user login or profile.

- ## 📦 APK Download

You can download the APK from the following link:  
🔗 [Download Latest APK](https://drive.google.com/uc?export=download&id=1AbcDEFghIJklMNOPq)

