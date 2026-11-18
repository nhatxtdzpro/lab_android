package com.thanhnhat.firebasecrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thanhnhat.firebase.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme {
            Surface(Modifier.fillMaxSize().safeDrawingPadding()) {
                val context = LocalContext.current
                if (remember { firebaseConfigured(context) }) CrudApp() else MissingFirebase()
            }
        } }
    }
}

@Composable
fun CrudApp() {
    StudentScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StudentScreen(model: StudentsViewModel = viewModel()) {
    DisposableEffect(model) { model.observe(); onDispose { model.stop() } }
    var editing by remember { mutableStateOf<Student?>(null) }
    var deleting by remember { mutableStateOf<Student?>(null) }
    var search by rememberSaveable { mutableStateOf("") }
    Scaffold(topBar = { TopAppBar(title = { Text("Lab 7 · Sinh viên") }) }, floatingActionButton = {
        FloatingActionButton(onClick = { editing = Student() }) { Text("+", Modifier.padding(16.dp)) }
    }) { padding ->
        Column(Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp)) {
            OutlinedTextField(search, { search = it }, Modifier.fillMaxWidth(), label = { Text("Tìm tên hoặc mã sinh viên") }, singleLine = true)
            if (model.loading || model.saving) LinearProgressIndicator(Modifier.fillMaxWidth())
            if (model.error.isNotBlank()) Text(model.error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(vertical = 8.dp))
            val filtered = model.students.filter { it.name.contains(search, true) || it.studentCode.contains(search, true) }
            if (filtered.isEmpty() && !model.loading) Text("Chưa có sinh viên phù hợp. Nhấn + để thêm.", Modifier.padding(vertical = 24.dp))
            LazyColumn(contentPadding = PaddingValues(top = 16.dp, bottom = 96.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(filtered, key = { it.id }) { student ->
                    ElevatedCard(Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            Text(student.name, style = MaterialTheme.typography.titleLarge)
                            Text("Mã SV: ${student.studentCode} · Điểm: ${student.score}")
                            Row {
                                TextButton(onClick = { editing = student }, enabled = !model.saving) { Text("Sửa") }
                                TextButton(onClick = { deleting = student }, enabled = !model.saving) { Text("Xóa") }
                            }
                        }
                    }
                }
            }
        }
    }
    editing?.let { student ->
        StudentDialog(student, model.saving, model.error, { editing = null }) { model.save(it) { editing = null } }
    }
    deleting?.let { student ->
        AlertDialog(onDismissRequest = { if (!model.saving) deleting = null }, title = { Text("Xóa sinh viên?") },
            text = { Column { Text(student.name); if (model.error.isNotBlank()) Text(model.error, color = MaterialTheme.colorScheme.error) } },
            confirmButton = { TextButton(onClick = { model.delete(student) { deleting = null } }, enabled = !model.saving) { Text("Xóa") } },
            dismissButton = { TextButton(onClick = { deleting = null }, enabled = !model.saving) { Text("Hủy") } })
    }
}

@Composable
private fun StudentDialog(student: Student, busy: Boolean, error: String, close: () -> Unit, save: (Student) -> Unit) {
    var name by rememberSaveable(student.id) { mutableStateOf(student.name) }
    var code by rememberSaveable(student.id) { mutableStateOf(student.studentCode) }
    var score by rememberSaveable(student.id) { mutableStateOf(if (student.id.isEmpty()) "" else student.score.toString()) }
    val number = score.replace(',', '.').toDoubleOrNull()
    val valid = name.isNotBlank() && code.isNotBlank() && number != null && number.isFinite() && number in 0.0..10.0
    AlertDialog(onDismissRequest = { if (!busy) close() }, title = { Text(if (student.id.isEmpty()) "Thêm sinh viên" else "Sửa sinh viên") },
        text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(name, { name = it }, label = { Text("Họ tên") }, singleLine = true, enabled = !busy)
                OutlinedTextField(code, { code = it }, label = { Text("Mã sinh viên") }, singleLine = true, enabled = !busy)
                OutlinedTextField(score, { score = it }, label = { Text("Điểm (0–10)") }, singleLine = true, enabled = !busy,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), isError = score.isNotEmpty() && (number == null || !number.isFinite() || number !in 0.0..10.0))
                if (error.isNotBlank()) Text(error, color = MaterialTheme.colorScheme.error)
            }
        },
        confirmButton = { TextButton(onClick = { save(Student(student.id, name, code, number ?: 0.0)) }, enabled = valid && !busy) { Text("Lưu") } },
        dismissButton = { TextButton(onClick = close, enabled = !busy) { Text("Hủy") } })
}
