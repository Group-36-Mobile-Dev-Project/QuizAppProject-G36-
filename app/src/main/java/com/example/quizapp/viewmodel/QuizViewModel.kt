import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Question
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getQuestionsByCategory(category: String) {
        _isLoading.value = true
        viewModelScope.launch {
            db.collection("questions")
                .whereEqualTo("category", category)
                .get()
                .addOnSuccessListener { result ->
                    val questionsList = result.map { doc ->
                        val options = (doc.get("options") as? List<*>)?.filterIsInstance<String>() ?: emptyList()
                        Question(
                            id = doc.id,
                            category = doc.getString("category") ?: "",
                            question = doc.getString("question") ?: "",
                            options = options.shuffled(),
                            correctAnswer = doc.getString("correctAnswer") ?: ""
                        )
                    }.shuffled()

                    _questions.value = questionsList.take(10)
                    _isLoading.value = false
                }
                .addOnFailureListener {
                    _isLoading.value = false
                }
        }
    }

    var currentQuestionIndex = mutableIntStateOf(0)
    var score = mutableIntStateOf(0)
}
