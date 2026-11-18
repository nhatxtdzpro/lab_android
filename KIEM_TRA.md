# Kết quả kiểm tra

- 10/10 dự án đã build APK debug thành công bằng Gradle.
- 5/5 unit test JVM đạt: Tip Calculator (3), Mars Photos (2).
- 10/10 ứng dụng đã được cài và mở trên LDPlayer 9, Android 9 / API 28.
- Chữ ký các APK debug đã được kiểm tra bằng apksigner.

## Các bản cập nhật

- Lab 7: CRUD không đăng nhập, dùng Cloud Firestore database ID `default` (không có dấu ngoặc), collection `students`. Đã xác nhận bản ghi mẫu từ Firebase Console hiển thị trong app. Bản ghi được tạo trên web; chưa kiểm thử toàn bộ chu trình thêm/sửa/xóa trong app.
- Lab 8: chỉ đăng ký email/mật khẩu, đăng nhập và đăng xuất; đã bỏ xác minh email và đặt lại mật khẩu. Đã build, cài và mở bản mới. Chưa tự tạo tài khoản thử hoặc nhập thông tin đăng nhập của người dùng trong lần kiểm tra này.
- Mã nguồn không chứa google-services.json cá nhân, cache build, APK, log hay ảnh chụp màn hình chạy thử.

## Phạm vi

Các bài được triển khai theo tên lab và tài liệu Android Basics with Compose. Lab 1 tạm gồm Happy Birthday, Compose Article, Task Manager và Compose Quadrant vì chưa có đề chi tiết của bốn bài.

Các kiểm tra trên được thực hiện trong quá trình phát triển ngày 18/11/2026. Những bài kiểm thử thiết bị/instrumentation có sẵn chưa được chạy toàn bộ; việc mở được app không thay thế kiểm thử hết các chức năng.
