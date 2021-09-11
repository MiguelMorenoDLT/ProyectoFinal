package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*
import kotlinx.android.synthetic.main.activity_registration.*

class mostrar_cargas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_cargas)

        /*val user = Firebase.auth.currentUser
        user?.let {
             val email = user.email
             show_cargas(email.toString())
        }*/
        getAllDocuments()

    }
    private fun show_cargas (email: String){

        db.collection("Cargas").document(email).get().addOnSuccessListener { basedatos ->
            if (basedatos.exists()) {
                val cargo: String? = basedatos.getString("Cargas")
                Mostrar_Cargas.text = cargo
            }
        }
    }

    private fun getAllDocuments (){
        val spinner = findViewById<Spinner>(R.id.idSpinner)
        var lista = listOf("")

        db.collection("Cargas").get().addOnSuccessListener { basedatos ->
           for (documento in basedatos ){
               //Mostrar_Cargas.text = documento.get("Name_Load").toString()
               lista = listOf(documento.get("Name_Load").toString())
           }
            val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_item,lista)
            spinner.adapter = adaptor
        }
    }
}