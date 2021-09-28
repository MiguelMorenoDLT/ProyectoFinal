package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_load.*
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*
import kotlinx.android.synthetic.main.activity_registration.*

class mostrar_cargas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_cargas)

        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            getAllDocuments(email.toString())
        }
    }

    private fun getAllDocuments (email: String){
        val spinner = findViewById<Spinner>(R.id.spinnerCarga)
        val arr = ArrayList<String>()

        db.collection("Cargas").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos ){
                arr.add(documento.get("Name_Load").toString())
            }

            val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_item, arr)
            spinner.adapter = adaptor
        }

        RequeastTransportationButton.setOnClickListener() {

                    val vr: String = spinnerCarga.selectedItem.toString()

                    db.collection("Viajes").document().set(
                        hashMapOf(
                            "Id" to email,
                            "Name_Load" to vr,
                            "From" to FromEditText.text.toString(),
                            "Until" to UntilEditText.text.toString()
                        )
                    )
                Toast.makeText(this, "Solicitud Realizada", Toast.LENGTH_SHORT).show();
            }

        }
    }
