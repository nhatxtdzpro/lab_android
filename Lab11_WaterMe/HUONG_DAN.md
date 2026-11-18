# Lab11 · Water Me

Danh sách cây và lịch nhắc tưới sau 5 giây, 1 ngày, 7 ngày hoặc 30 ngày bằng WorkManager; yêu cầu quyền thông báo trên Android 13+. Lịch mới thay lịch cũ của cùng cây.

## Mở và chạy

Android Studio → Open thư mục `Lab11_WaterMe` → JDK 17 hoặc 21 → Android SDK 35 → Run trên Android API 24+.

Hoặc chạy `gradlew.bat :app:assembleDebug` trong thư mục này. APK ở `app/build/outputs/apk/debug/app-debug.apk`.

Trên máy hiện tại `local.properties` trỏ SDK `D:/DATAAPP/Android`; máy khác để Android Studio tạo lại đường dẫn.

## Kiểm tra thủ công

Cho phép thông báo, chọn một cây và lịch 5 giây; chờ nhắc. Thử đặt lịch lại cho cùng cây. Nếu từ chối quyền, bật lại trong Cài đặt ứng dụng.

## Lưu ý

WorkManager không đảm bảo đúng từng giây; thời gian có thể trễ do Doze, tiết kiệm pin hoặc hệ thống. Force stop có thể ngăn công việc đến khi mở lại app.

## Nguồn

ZIP main của Google Developer Training do bạn cung cấp; bổ sung quyền thông báo và thay lịch theo cây.
