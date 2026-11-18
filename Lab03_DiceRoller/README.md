# Lab03 · Dice Roller

Nút Roll sinh số ngẫu nhiên 1–6; hiển thị drawable tương ứng và mô tả hỗ trợ tiếp cận. Dùng rememberSaveable giữ kết quả khi tạo lại Activity.

## Mở và chạy

Android Studio → Open thư mục `Lab03_DiceRoller` → JDK 17 hoặc 21 → Android SDK 35 → Run trên Android API 24+.

Hoặc chạy `gradlew.bat :app:assembleDebug` trong thư mục này. APK ở `app/build/outputs/apk/debug/app-debug.apk`.

Trên máy hiện tại `local.properties` trỏ SDK `D:/DATAAPP/Android`; máy khác để Android Studio tạo lại đường dẫn.

## Kiểm tra thủ công

Nhấn Roll nhiều lần, đối chiếu ảnh xúc xắc. Xoay thiết bị để kiểm tra kết quả không bị đặt lại.

## Lưu ý

Các lần đổ liên tiếp có thể trùng kết quả do ngẫu nhiên.

## Nguồn

Google Developer Training, nhánh main; bổ sung giữ trạng thái.

## Mã nguồn

- [Mã ứng dụng](app/src/main)
- [Hướng dẫn riêng của lab](HUONG_DAN.md)
- [Danh sách các lab và link nộp bài](../DANH_SACH_LINK_NOP_BAI.md)
- [README gốc của mã mẫu](README_UPSTREAM.md)
