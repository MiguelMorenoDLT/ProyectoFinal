package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_load.*
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*
import kotlinx.android.synthetic.main.activity_registration.*

class CreateLoad : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_load)

        SaveLoadButton.setOnClickListener {

            val user = Firebase.auth.currentUser
            user?.let {
                val email = user.email
                save_load(email.toString())
            }
        }
    }

    private fun save_load (email: String){


        db.collection("Cargas").document().set(
            hashMapOf(
                "Id" to email,
                "Name_Load" to nameLoadEditText.text.toString(),
                "Weight_Load" to weightLoadEditText.text.toString()
            )
        )
    }
}