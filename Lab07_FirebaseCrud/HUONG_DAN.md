# Lab 7 · Firebase CRUD không đăng nhập

Mở ứng dụng là vào danh sách sinh viên. Có thêm, xem, sửa, xóa có xác nhận và tìm kiếm theo tên/mã. Lab 7 không dùng Firebase Authentication; Lab 8 giữ xác thực riêng.

## Database đang dùng

- Firebase project: **lab07-firebasecrud**.
- Database ID: **default**, không có dấu ngoặc. Đây là database Enterprise / Firestore Native hiện có của bạn, ở asia-southeast1.
- Collection: **students**. Đường dẫn tài liệu: `/students/{documentId}`.
- App kết nối bằng `FirebaseFirestore.getInstance("default")`.

**default và (default) là hai database khác nhau.** Nguyên nhân app không thấy dữ liệu trước đó là app kết nối `(default)`, trong khi database trên web tên `default`. Không cần tạo thêm database hoặc nâng cấp gói để dùng bản hiện tại.

## Xem dữ liệu trên Brave

Mở [Firebase Data của Lab 7](https://console.firebase.google.com/u/3/project/lab07-firebasecrud/firestore/databases/default/data/~2Fstudents~2Fdemo-sv001). Nếu trình duyệt dùng tài khoản Google khác, chọn đúng tài khoản có quyền truy cập project.

Chọn **Data → students → tài liệu sinh viên**. Đã tạo dữ liệu mẫu `demo-sv001`: tên `Sinh viên mẫu`, mã `DEMO001`, điểm `8`. Bản ghi này đã hiển thị trên cả Brave và app LDPlayer.

## Cấu hình và chạy

1. Giữ file `app/google-services.json` đúng package `com.thanhnhat.firebasecrud`.
2. Khi chuyển sang project khác, chọn đúng database ID trong StudentsViewModel.kt; SDK không tự chọn database theo trang bạn đang mở trên web.
3. File [firestore.rules](firestore.rules) đi kèm dùng cho CRUD không đăng nhập trên collection `students`. Rules hiện trên Firebase Console đã có cấu hình tương ứng; phiên kiểm tra này không sửa quyền trên web.
4. Build bằng Android Studio hoặc `gradlew.bat :app:assembleDebug`. JDK 17/21, SDK 35, thiết bị API 24+.

Collection students dùng chung và cho phép truy cập không đăng nhập, phù hợp dữ liệu mẫu cho bài thực hành.

## Kiểm tra CRUD

1. Mở app, kiểm tra có Sinh viên mẫu, mã DEMO001, điểm 8.0.
2. Nhấn **+**, nhập tên/mã/điểm 0–10 → **Lưu**.
3. Xem tài liệu mới ở collection students trên web.
4. Thử tìm kiếm, sửa, xóa và mở lại app để kiểm tra dữ liệu còn.

Dữ liệu cũ nếu có dưới `/users/{uid}/students` được giữ nguyên, không tự chuyển sang collection mới. Khi ngoại tuyến, ghi dữ liệu có thể đợi xác nhận đồng bộ.
