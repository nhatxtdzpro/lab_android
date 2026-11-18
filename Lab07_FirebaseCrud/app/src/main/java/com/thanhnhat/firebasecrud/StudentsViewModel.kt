package com.thanhnhat.firebasecrud

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration

data class Student(val id: String = "", val name: String = "", val studentCode: String = "", val score: Double = 0.0)

class StudentsViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance("default")
    private var listener: ListenerRegistration? = null
    private var observing = false
    private val collection = db.collection("students")
    var students by mutableStateOf(emptyList<Student>())
        private set
    var loading by mutableStateOf(false)
        private set
    var saving by mutableStateOf(false)
        private set
    var error by mutableStateOf("")
        private set

    fun observe() {
        if (observing) return
        observing = true
        loading = true
        listener = collection.addSnapshotListener { snapshot, failure ->
            if (!observing) return@addSnapshotListener
            loading = false
            error = failure?.let(::message).orEmpty()
            if (snapshot != null) students = snapshot.documents.map { doc ->
                Student(doc.id, doc.getString("name").orEmpty(), doc.getString("studentCode").orEmpty(), doc.getDouble("score") ?: 0.0)
            }.sortedBy { it.name.lowercase() }
        }
    }

    fun save(student: Student, onSuccess: () -> Unit) {
        if (saving) return
        saving = true
        error = ""
        val data = mapOf("name" to student.name.trim(), "studentCode" to student.studentCode.trim(), "score" to student.score)
        val ref = if (student.id.isEmpty()) collection.document() else collection.document(student.id)
        ref.set(data).addOnCompleteListener {
            saving = false
            if (it.isSuccessful) onSuccess() else error = it.exception?.let(::message) ?: "Lưu thất bại."
        }
    }

    fun delete(student: Student, onSuccess: () -> Unit) {
        if (saving) return
        saving = true
        error = ""
        collection.document(student.id).delete().addOnCompleteListener {
            saving = false
            if (it.isSuccessful) onSuccess() else error = it.exception?.let(::message) ?: "Xóa thất bại."
        }
    }

    private fun message(failure: Exception): String = when ((failure as? FirebaseFirestoreException)?.code) {
        FirebaseFirestoreException.Code.NOT_FOUND -> "Chưa tìm thấy database Firestore default. Kiểm tra database trong Firebase Console."
        FirebaseFirestoreException.Code.PERMISSION_DENIED -> "Firestore chưa cho phép CRUD. Hãy áp dụng file firestore.rules của Lab 7 trong Firebase Console."
        FirebaseFirestoreException.Code.UNAVAILABLE -> "Chưa kết nối được Firestore. Kiểm tra mạng và thử mở lại ứng dụng."
        else -> failure.localizedMessage ?: "Không truy cập được dữ liệu sinh viên."
    }

    fun stop() { observing = false; listener?.remove(); listener = null; students = emptyList(); error = ""; loading = false }
    override fun onCleared() { stop() }
}
