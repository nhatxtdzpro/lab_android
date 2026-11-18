# Lab05 · Cupcake

Luồng đặt bánh nhiều màn hình: số lượng, hương vị, ngày nhận, tổng tiền và chia sẻ đơn hàng qua Android Sharesheet.

## Mở và chạy

Android Studio → Open thư mục `Lab05_Cupcake` → JDK 17 hoặc 21 → Android SDK 35 → Run trên Android API 24+.

Hoặc chạy `gradlew.bat :app:assembleDebug` trong thư mục này. APK ở `app/build/outputs/apk/debug/app-debug.apk`.

Trên máy hiện tại `local.properties` trỏ SDK `D:/DATAAPP/Android`; máy khác để Android Studio tạo lại đường dẫn.

## Kiểm tra thủ công

Thử đặt 1/6/12 bánh, chọn vị và ngày nhận; dùng Back, Cancel, Send Order để kiểm tra điều hướng và chia sẻ.

## Lưu ý

Chỉ mô phỏng đặt hàng; không gửi đơn tới cửa hàng hay thanh toán thật.

## Nguồn

Google Developer Training, nhánh main.
