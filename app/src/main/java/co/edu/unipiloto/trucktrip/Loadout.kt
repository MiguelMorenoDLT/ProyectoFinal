package co.edu.unipiloto.trucktrip

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_loadout.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.cerrarButton

class Loadout : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loadout)

        cerrarButton.setOnClickListener{

            val prefs = getSharedPreferences(getString(R.string.pr), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }


        loadoutButton.setOnClickListener{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString()).addOnCompleteListener{
                if (it.isSuccessful){
                        cargas(it.result?.user?.email ?: "")
                }else{
                    showAlert()
                }
            }
          }
        }

    private fun cargas (email: String){

        db.collection("users").document(email).get().addOnSuccessListener { basedatos ->
            if (basedatos.exists()) {
                val cargo: String? = basedatos.getString("Cargas")
                Mostrar_Cargas.text = cargo
            }
        }
        val intent = Intent(this, mostrar_cargas::class.java)
        startActivity(intent)
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