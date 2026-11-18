package com.thanhnhat.firebase

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp

fun firebaseConfigured(context: Context): Boolean =
    FirebaseApp.getApps(context).isNotEmpty() || FirebaseApp.initializeApp(context) != null

@Composable
fun MissingFirebase() {
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Cần kết nối Firebase", style = MaterialTheme.typography.headlineMedium)
        Text("Thêm google-services.json vào thư mục app, rồi build lại ứng dụng.")
        Text("Kết nối database Firestore default và áp dụng firestore.rules theo HUONG_DAN.md của Lab 7.")
    }
}
