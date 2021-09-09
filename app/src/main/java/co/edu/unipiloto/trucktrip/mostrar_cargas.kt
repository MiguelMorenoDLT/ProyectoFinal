package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*

class mostrar_cargas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_cargas)

        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString()).addOnCompleteListener{
            if (it.isSuccessful){
                show_cargas(it.result?.user?.email ?: "")
            }else{
                showAlert()
            }
        }
    }
    private fun show_cargas (email: String){

        db.collection("users").document(email).get().addOnSuccessListener { basedatos ->
            if (basedatos.exists()) {
                val cargo: String? = basedatos.getString("Cargas")
                Mostrar_Cargas.text = cargo
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("No se han encontrado cargas")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}