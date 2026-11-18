# Lab09 · Inventory

Ứng dụng Room có danh sách hàng, thêm hàng, chi tiết, sửa, xóa và bán giảm số lượng tồn. Dữ liệu lưu cục bộ qua SQLite.

## Mở và chạy

Android Studio → Open thư mục `Lab09_Inventory` → JDK 17 hoặc 21 → Android SDK 35 → Run trên Android API 24+.

Hoặc chạy `gradlew.bat :app:assembleDebug` trong thư mục này. APK ở `app/build/outputs/apk/debug/app-debug.apk`.

Trên máy hiện tại `local.properties` trỏ SDK `D:/DATAAPP/Android`; máy khác để Android Studio tạo lại đường dẫn.

## Kiểm tra thủ công

Thêm mặt hàng với tên/giá/số lượng; sửa thông tin, bán một món, xóa có xác nhận. Đóng/mở app kiểm tra dữ liệu còn. Giá và số lượng phải là số không âm.

## Lưu ý

Dữ liệu nằm trên thiết bị; xóa dữ liệu app hoặc gỡ app sẽ mất database.

## Nguồn

Google Developer Training, nhánh main hoàn chỉnh; ZIP room ban đầu chưa hoàn thiện chức năng chỉnh sửa.
