# Bộ bài thực hành Android · Thanh Nhật

Có **10 dự án Android độc lập**, tương ứng Lab 1–5 và 7–11; không có Lab 6 theo xác nhận của bạn. Mỗi thư mục mở riêng bằng Android Studio, không mở cả thư mục này như một dự án Gradle.

| Lab | Thư mục | Nội dung |
| --- | --- | --- |
| 1 | [Lab01_Basics](Lab01_Basics) | Một app có menu chọn 4 bài: Happy Birthday, Compose Article, Task Manager, Compose Quadrant |
| 2 | [Lab02_BusinessCard](Lab02_BusinessCard) | Danh thiếp, logo Android, tên, nghề nghiệp và liên hệ |
| 3 | [Lab03_DiceRoller](Lab03_DiceRoller) | Đổ xúc xắc ngẫu nhiên, hiển thị 6 mặt, giữ kết quả khi xoay máy |
| 4 | [Lab04_TipCalculator](Lab04_TipCalculator) | Nhập hóa đơn và phần trăm tip, tùy chọn làm tròn |
| 5 | [Lab05_Cupcake](Lab05_Cupcake) | Chọn số bánh, hương vị, ngày nhận, xem tổng tiền, chia sẻ đơn hàng |
| 7 | [Lab07_FirebaseCrud](Lab07_FirebaseCrud) | CRUD sinh viên trực tiếp bằng Cloud Firestore, không đăng nhập; collection students |
| 8 | [Lab08_FirebaseAuth](Lab08_FirebaseAuth) | Đăng ký email/mật khẩu, đăng nhập và đăng xuất; không xác minh email |
| 9 | [Lab09_Inventory](Lab09_Inventory) | Quản lý hàng tồn kho bằng Room, thêm/sửa/xóa/bán hàng |
| 10 | [Lab10_MarsPhotos](Lab10_MarsPhotos) | Retrofit + JSON + Coil, tải ảnh Sao Hỏa và xử lý tải/lỗi/thử lại |
| 11 | [Lab11_WaterMe](Lab11_WaterMe) | WorkManager lên lịch nhắc tưới cây, thông báo và quyền Android 13+ |

## Link nộp bài

Xem **[bảng link trực tiếp cho từng lab](DANH_SACH_LINK_NOP_BAI.md)**. Ví dụ, nộp Lab 7 bằng [link Lab07_FirebaseCrud](https://github.com/nhatxtdzpro/lab_android/tree/main/Lab07_FirebaseCrud).

Các thư mục trong bảng phía trên cũng mở thẳng mã nguồn của từng bài. Mỗi lab có README và hướng dẫn chạy riêng.

## Build bằng VS Code

Mở thư mục lab, đặt JAVA_HOME trỏ tới JDK 17/21 và cấu hình Android SDK 35 qua local.properties hoặc ANDROID_HOME, rồi chạy `gradlew.bat :app:assembleDebug` trong Terminal. Lab 7 có tác vụ Ctrl+Shift+B và tác vụ build/chạy LDPlayer trong `.vscode/tasks.json`; cần chỉnh đường dẫn JDK/ADB nếu dùng máy khác.

## Mở và chạy

1. Android Studio → **Open** → chọn thư mục lab, ví dụ `Lab03_DiceRoller`.
2. Chọn Gradle JDK **17 hoặc 21**, cài Android SDK Platform **35** nếu thiếu, chờ Gradle Sync.
3. Chọn điện thoại hoặc máy ảo Android 7.0 / API 24 trở lên → Run.
4. Lab 7–8: làm theo `HUONG_DAN.md` để thêm Firebase. App vẫn build khi chưa có cấu hình, nhưng sẽ hiển thị hướng dẫn kết nối thay vì thực hiện thao tác Firebase.

`local.properties` không được đưa lên Git. Trên máy đang phát triển, SDK ở `D:/DATAAPP/Android`. Khi clone về máy khác, để Android Studio tạo file này hoặc tự cấu hình `sdk.dir` theo đường dẫn SDK của máy đó.

## Build toàn bộ

Trong PowerShell tại thư mục này:

```powershell
.\build-all.ps1 -RunTests
# Hoặc chỉ định JDK:
.\build-all.ps1 -JdkHome 'C:\duong-dan\jdk-17' -RunTests
# Hoặc chỉ build một bài:
.\build-all.ps1 -Labs Lab09_Inventory
```

Script lưu APK debug vào `APKs/`, log và bảng kết quả vào `build-logs/`. Kiểm thử JVM có sẵn ở Tip Calculator và Mars Photos được chạy khi thêm `-RunTests`. Các bài kiểm thử thiết bị cần điện thoại/máy ảo kết nối; dùng `gradlew.bat :app:connectedDebugAndroidTest` trong thư mục lab tương ứng.

## Phạm vi đề bài

Trang giao bài e-learning không đọc được trong phiên làm việc. Vì vậy chức năng được triển khai theo tên lab và nội dung khóa Android Basics with Compose, chưa đối chiếu được rubric riêng của giảng viên.

Lab 1 chỉ có tên “4 bài cơ bản”, nên **tạm giả định** là bốn bài giao diện trong bảng. Đây là một dự án có bốn màn hình, không phải bốn dự án tách riêng. Hướng dẫn Kotlin do bạn gửi được lưu làm tài liệu tham khảo; nếu bốn bài của lớp có tên khác thì cần chỉnh Lab 1 theo đề cụ thể.

Business Card dùng tên Thanh Nhật; số điện thoại, email và tài khoản mạng xã hội là thông tin mẫu, cần thay trước khi nộp. Các app mẫu giữ nhiều nhãn tiếng Anh của khóa học gốc.

## Nguồn

- [Android Basics with Compose](https://developer.android.com/courses/android-basics-compose/course).
- [Kotlin quick guide do bạn cung cấp](https://developer.android.com/courses/android-basics-kotlin/kotlin-quick-guide?hl=vi).
- [Business Card codelab](https://developer.android.com/codelabs/basic-android-kotlin-compose-business-card).
- Mã mẫu của Google: [Dice Roller](https://github.com/google-developer-training/basic-android-kotlin-compose-training-dice-roller), [Tip Calculator](https://github.com/google-developer-training/basic-android-kotlin-compose-training-tip-calculator/tree/test_solution), [Cupcake](https://github.com/google-developer-training/basic-android-kotlin-compose-training-cupcake), [Inventory](https://github.com/google-developer-training/basic-android-kotlin-compose-training-inventory-app), [Mars Photos](https://github.com/google-developer-training/basic-android-kotlin-compose-training-mars-photos), [Water Me](https://github.com/google-developer-training/basic-android-kotlin-compose-training-waterme).
- [Firebase Android setup](https://firebase.google.com/docs/android/setup), [Email/Password Auth](https://firebase.google.com/docs/auth/android/password-auth), [Cloud Firestore](https://firebase.google.com/docs/firestore/quickstart).

Các lab dựa trên mã Google giữ file LICENSE và ghi chú bản quyền của tác giả. Inventory dùng source nhánh `main` hoàn chỉnh vì ZIP nhánh `room` ban đầu chưa có chức năng sửa/xóa/bán hàng đầy đủ. Các ZIP đầu vào được giữ nguyên trên máy phát triển và không được đưa lên repository.
