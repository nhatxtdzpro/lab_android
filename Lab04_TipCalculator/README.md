# Lab04 · Tip Calculator

Tính tiền tip theo hóa đơn/phần trăm, làm tròn lên tùy chọn, nhận dấu phẩy hoặc dấu chấm thập phân và giữ nội dung khi xoay máy.

## Mở và chạy

Android Studio → Open thư mục `Lab04_TipCalculator` → JDK 17 hoặc 21 → Android SDK 35 → Run trên Android API 24+.

Hoặc chạy `gradlew.bat :app:assembleDebug` trong thư mục này. APK ở `app/build/outputs/apk/debug/app-debug.apk`.

Trên máy hiện tại `local.properties` trỏ SDK `D:/DATAAPP/Android`; máy khác để Android Studio tạo lại đường dẫn.

## Kiểm tra thủ công

Nhập 12 và 20: tiền tip bằng 2.4 đơn vị tiền. Bật làm tròn: bằng 3. Chạy gradlew.bat :app:testDebugUnitTest để kiểm tra tính toán.

## Lưu ý

Ký hiệu và cách hiển thị tiền theo locale của thiết bị.

## Nguồn

Google Developer Training, nhánh test_solution; bổ sung xử lý đầu vào và giữ trạng thái.

## Mã nguồn

- [Mã ứng dụng](app/src/main)
- [Hướng dẫn riêng của lab](HUONG_DAN.md)
- [Danh sách các lab và link nộp bài](../DANH_SACH_LINK_NOP_BAI.md)
- [README gốc của mã mẫu](README_UPSTREAM.md)
