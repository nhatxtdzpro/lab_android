package com.thanhnhat.firebase

import android.content.Context
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

fun firebaseConfigured(context: Context): Boolean =
    FirebaseApp.getApps(context).isNotEmpty() || FirebaseApp.initializeApp(context) != null

class AccountViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    var user: FirebaseUser? by mutableStateOf(auth.currentUser)
        private set
    var busy by mutableStateOf(false)
        private set
    var message by mutableStateOf("")
        private set
    private val observer = FirebaseAuth.AuthStateListener { user = it.currentUser }
    init { auth.addAuthStateListener(observer) }

    fun submit(email: String, password: String, register: Boolean) {
        if (busy) return
        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() || password.length < 6) {
            message = "Nhập email hợp lệ và mật khẩu ít nhất 6 ký tự."
            return
        }
        busy = true
        message = ""
        val task = if (register) auth.createUserWithEmailAndPassword(email.trim(), password)
            else auth.signInWithEmailAndPassword(email.trim(), password)
        task.addOnCompleteListener {
            busy = false
            message = if (it.isSuccessful) {
                if (register) "Tạo tài khoản thành công." else "Đăng nhập thành công."
            } else it.exception?.localizedMessage ?: "Không kết nối được Firebase."
        }
    }

    fun logout() { if (!busy) { auth.signOut(); message = "" } }
    override fun onCleared() { auth.removeAuthStateListener(observer) }
}

@Composable
fun MissingFirebase() {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Cần kết nối Firebase", style = MaterialTheme.typography.headlineMedium)
        Text("Thêm file google-services.json của ứng dụng vào thư mục app rồi Sync và chạy lại.")
        Text("Bật Authentication → Email/Password trong Firebase Console.")
        Text("Xem HUONG_DAN.md trong dự án để cấu hình đúng package.")
    }
}

@Composable
fun LoginPanel(account: AccountViewModel) {
    var email by rememberSaveable { mutableStateOf("") }
    // Mật khẩu chỉ giữ trong bộ nhớ, không lưu vào saved instance state.
    var password by remember { mutableStateOf("") }
    var register by rememberSaveable { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().imePadding().verticalScroll(rememberScrollState()).padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(if (register) "Tạo tài khoản" else "Đăng nhập", style = MaterialTheme.typography.headlineMedium)
        Text(if (register) "Nhập email và mật khẩu ít nhất 6 ký tự." else "Nhập email và mật khẩu để tiếp tục.")
        OutlinedTextField(email, { email = it }, Modifier.fillMaxWidth(), label = { Text("Email") }, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), enabled = !account.busy)
        OutlinedTextField(password, { password = it }, Modifier.fillMaxWidth(), label = { Text("Mật khẩu") }, singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), visualTransformation = PasswordVisualTransformation(), enabled = !account.busy)
        Button(onClick = { account.submit(email, password, register) }, enabled = !account.busy, modifier = Modifier.fillMaxWidth()) {
            Text(if (register) "Đăng ký" else "Đăng nhập")
        }
        TextButton(onClick = { register = !register }, enabled = !account.busy) {
            Text(if (register) "Đã có tài khoản? Đăng nhập" else "Chưa có tài khoản? Đăng ký")
        }
        if (account.busy) LinearProgressIndicator(Modifier.fillMaxWidth())
        if (account.message.isNotBlank()) Text(account.message, color = MaterialTheme.colorScheme.primary)
    }
}
