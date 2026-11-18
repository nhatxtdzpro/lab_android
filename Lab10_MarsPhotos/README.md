# Lab10 · Mars Photos

Retrofit gọi REST, Kotlin serialization đọc JSON, Coil tải ảnh vào lưới, ViewModel/repository phân chia UI và dữ liệu; có trạng thái tải/lỗi/thử lại.

## Mở và chạy

Android Studio → Open thư mục `Lab10_MarsPhotos` → JDK 17 hoặc 21 → Android SDK 35 → Run trên Android API 24+.

Hoặc chạy `gradlew.bat :app:assembleDebug` trong thư mục này. APK ở `app/build/outputs/apk/debug/app-debug.apk`.

Trên máy hiện tại `local.properties` trỏ SDK `D:/DATAAPP/Android`; máy khác để Android Studio tạo lại đường dẫn.

## Kiểm tra thủ công

Bật internet để tải lưới ảnh. Tắt mạng và mở lại app để kiểm tra lỗi, bật mạng rồi Retry. Chạy gradlew.bat :app:testDebugUnitTest để kiểm tra repository/ViewModel bằng fake data.

## Lưu ý

Cần mạng và dịch vụ ảnh bên ngoài còn hoạt động; unit test dùng fake data không chứng minh endpoint thật luôn hoạt động.

## Nguồn

ZIP main của Google Developer Training do bạn cung cấp.

## Mã nguồn

- [Mã ứng dụng](app/src/main)
- [Hướng dẫn riêng của lab](HUONG_DAN.md)
- [Danh sách các lab và link nộp bài](../DANH_SACH_LINK_NOP_BAI.md)
- [README gốc của mã mẫu](README_UPSTREAM.md)
