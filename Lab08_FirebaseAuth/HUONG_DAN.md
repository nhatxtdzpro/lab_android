# Lab 8 · Đăng ký và đăng nhập đơn giản

Ứng dụng chỉ có **đăng ký tài khoản bằng email/mật khẩu, đăng nhập và đăng xuất**. Đăng ký thành công là vào ngay màn hình tài khoản. Không có gửi email xác minh, kiểm tra trạng thái xác minh hoặc quên/đặt lại mật khẩu.

## Cấu hình

1. File `app/google-services.json` phải đúng Android package **com.thanhnhat.firebaseauth**.
2. Trong Firebase Console → Authentication → Sign-in method, bật **Email/Password**.
3. Build lại sau khi thêm hoặc đổi JSON. Lab 8 không dùng Firestore.

## Sử dụng

1. Mở app, chọn **Chưa có tài khoản? Đăng ký**.
2. Nhập email hợp lệ về định dạng và mật khẩu ít nhất 6 ký tự → **Đăng ký**.
3. App vào ngay màn hình **Đăng nhập thành công**, hiển thị email và nút **Đăng xuất**.
4. Đăng xuất rồi dùng cùng email/mật khẩu để đăng nhập lại.
5. Firebase SDK giữ phiên đăng nhập khi đóng/mở app. Mật khẩu không được ghi vào log hoặc saved instance state.

Tài khoản được tạo trong Firebase Authentication, không phải tạo hộp thư Gmail mới. Có thể xem tài khoản tại Authentication → Users trên Firebase Console.

## Build và chạy

Android Studio hoặc VS Code, JDK 17/21, SDK 35, thiết bị API 24+.

```powershell
$env:JAVA_HOME = 'C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.5\jbr'
.\gradlew.bat :app:assembleDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
