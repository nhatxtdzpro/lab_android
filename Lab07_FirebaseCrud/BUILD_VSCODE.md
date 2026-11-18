# Build Lab 7 trên VS Code

Mở đúng thư mục `Lab07_FirebaseCrud` bằng VS Code.

- **Ctrl+Shift+B**: build APK debug.
- **Terminal → Run Task → Build và chạy Lab 7 trên LDPlayer**: build, cài APK và mở app. Cần bật LDPlayer trước.
- APK: `app/build/outputs/apk/debug/app-debug.apk`.

Tác vụ nằm trong `.vscode/tasks.json`, đang dùng JDK 17 và Android SDK có sẵn trên máy này. Không cần mở Android Studio để build.

Nếu muốn chạy trực tiếp trong Terminal của VS Code (PowerShell):

```powershell
$env:JAVA_HOME = 'C:\Program Files\JetBrains\IntelliJ IDEA 2023.2.5\jbr'
.\gradlew.bat :app:assembleDebug
```

Đổi máy thì cập nhật JAVA_HOME trong tasks.json, đường dẫn SDK ở local.properties và đường dẫn adb.exe trong các task LDPlayer. Không cần cài extension để thực hiện các tác vụ Gradle này.
