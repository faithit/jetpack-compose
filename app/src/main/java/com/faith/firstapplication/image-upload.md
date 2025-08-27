# 📸 Image Upload with Cloudinary & Firebase

This guide explains how to handle **image upload** in your Android project using **Supabase** or **Cloudinary**.

---

## 🚀 Overview
- Pick an image from your app.
- Upload the image to **Cloudinary**.
- Use **Retrofit API** to retrieve the **image URL**.
- Store the image URL in **Firebase Realtime Database**.

---

## 📱 Example from Student Project
In our demo project:
- We added an **image picker** in the **Add Student Screen**.
- The selected image was uploaded to **Cloudinary**.
- The **image URL** was stored in **Firebase Realtime Database**.

---

## 🔧 Cloudinary Setup
1. Go to [Cloudinary](https://cloudinary.com/) and create an account.
2. In your **Cloudinary dashboard**:
    - Navigate to **Settings → Upload**.
    - Add an **Upload Preset**.
    - Configure:
        - **Preset name** (e.g. `students`)
        - **Signing mode → Unsigned**
        - **Asset folder name** (optional)

---

## 💻 Code Changes

### StudentViewModel
```kotlin
val cloudinaryUrl = "https://api.cloudinary.com/v1_1/<your_cloud_name>/image/upload"
// Replace <your_cloud_name> with your Cloudinary cloud name (found in your dashboard)

val uploadPreset = "students"
// Replace "students" with the upload preset you created

Retrofit API
@Multipart
@POST("v1_1/<your_cloud_name>/image/upload")
// Replace <your_cloud_name> with your Cloudinary cloud name
