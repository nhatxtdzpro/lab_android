package com.thanhnhat.firebaseauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thanhnhat.firebase.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize().safeDrawingPadding()) {
                    val context = LocalContext.current
                    if (remember { firebaseConfigured(context) }) AuthApp() else MissingFirebase()
                }
            }
        }
    }
}

@Composable
fun AuthApp(account: AccountViewModel = viewModel()) {
    val user = account.user
    if (user == null) LoginPanel(account)
    else Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Đăng nhập thành công", style = MaterialTheme.typography.headlineMedium)
        Text("Email: ${user.email.orEmpty()}")
        Button(onClick = account::logout, enabled = !account.busy) { Text("Đăng xuất") }
        if (account.busy) LinearProgressIndicator(Modifier.fillMaxWidth())
        if (account.message.isNotBlank()) Text(account.message)
    }
}
