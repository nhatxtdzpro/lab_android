# Lab 8 PIZZERIA Firebase Authentication

Dự án lấy từ [HuyNguyenChan/LAB8](https://github.com/HuyNguyenChan/LAB8), commit `1fc6771b5efd472c50b0be7b6a7b7f5fdc534db5`. Giữ nguyên giao diện và logic Sign In, Sign Up và Home của dự án mẫu.

## Firebase của bạn

- Application ID: **com.thanhnhat.firebaseauth**, khớp Android app trong file `app/google-services.json` hiện có trên máy.
- Namespace và package Kotlin: **com.example.lab8**, giữ theo source gốc. Namespace có thể khác applicationId.
- Giữ JSON Firebase riêng trong thư mục `app`. File này không được commit lên GitHub.
- Trong Firebase Console, bật Authentication → Email/Password. Không yêu cầu xác minh email.

## Build

Dùng Android SDK 36, JDK 17/21. Dự án giữ Android Gradle Plugin 8.13.0, Gradle 8.13 và Kotlin 2.0.21 của bản gốc.

Chạy `gradlew.bat :app:assembleDebug` trong thư mục lab. Cấu hình SDK trong `local.properties`. Khi clone về máy khác, cần thêm JSON đúng Firebase project trước khi build.

## Luồng sử dụng

1. Sign In bằng email và mật khẩu.
2. Sign Up để tạo tài khoản; thành công sẽ chuyển tới Home.
3. SignOut để quay lại màn hình đăng nhập.

Các biểu tượng Google/Facebook và nút START ORDER giữ theo source mẫu, hiện chưa có chức năng đăng nhập mạng xã hội hoặc đặt hàng.

APK: `app/build/outputs/apk/debug/app-debug.apk`.

Component launcher khi dùng ADB: `com.thanhnhat.firebaseauth/com.example.lab8.MainActivity`.
