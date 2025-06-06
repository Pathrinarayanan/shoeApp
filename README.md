# 👟 Shoe App UI (Jetpack Compose)

A clean and modern **Shoe Shopping App UI** built with **Jetpack Compose**, showcasing with **floating bottom navigation bar**, stylish gradients, and an elegant product card layout.

---

## ✨ Features

- 🔥 Beautiful product card UI with ratings, price, and images
- 🎨 Gradient styles and rounded containers
- 📱 Floating Bottom Navigation Bar
- 🖼️ Remote image loading using [Coil](https://github.com/coil-kt/coil)
- ⚡ Responsive and smooth layout

---

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/e043b4ba-2a00-4267-8ab6-1ce5c3302b56)


---

## 🚀 Tech Stack

- Kotlin
- Jetpack Compose
- Coil (Image loading)
- Mocky (for mock API)
- Material3

---

## 🧠 What You'll Learn

- UI layout in Compose using `Column`, `Row`, `LazyColumn`
- Custom component design with gradients
- Handling network images with Coil and API connections with Retrofit
- Floating bottom navigation implementation

---
## ⚠️ Mocky API Note
Mocky links may expire or stop working after some time.
If the data doesn't load when you clone the project:

Go to https://mocky.io

Paste your data.json (used in the project)

Generate a new API link

Replace the old URL inside ApiService.kt with the new one

```
interface ApiService {
    @GET("YOUR_NEW_MOCKY_PATH")
    suspend fun getData(): ShoeData
}
```

## 🏁 Getting Started

1. Clone the repo:
```bash
git clone https://github.com/Pathrinarayanan/shoeApp.git
