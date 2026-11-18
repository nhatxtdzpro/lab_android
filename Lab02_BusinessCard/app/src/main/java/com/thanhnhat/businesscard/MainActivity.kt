package com.thanhnhat.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { BusinessCard() } }
    }
}

// Thay thông tin bên dưới bằng thông tin cá nhân trước khi nộp bài.
@Composable
fun BusinessCard() {
    Column(Modifier.fillMaxSize().background(Color(0xFFD2E8D4)).safeDrawingPadding()
        .verticalScroll(rememberScrollState()).padding(32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(100.dp))
        Box(Modifier.size(120.dp).background(Color(0xFF073042)), contentAlignment = Alignment.Center) {
            Icon(Icons.Default.Android, "Android logo", Modifier.size(100.dp), tint = Color(0xFF3DDC84))
        }
        Text("Thanh Nhật", Modifier.padding(top = 16.dp), fontSize = 42.sp, color = Color(0xFF163B30))
        Text("Android Developer", Modifier.padding(top = 8.dp), color = Color(0xFF006C4C), fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(100.dp))
        Contact(Icons.Default.Phone, "+84 900 000 000")
        Contact(Icons.Default.Share, "@thanhnhat")
        Contact(Icons.Default.Email, "thanhnhat@example.com")
    }
}

@Composable
private fun Contact(icon: ImageVector, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = Color(0xFF006C4C))
        Text(value, Modifier.padding(start = 24.dp), color = Color(0xFF163B30))
    }
}
