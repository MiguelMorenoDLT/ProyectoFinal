package co.edu.unipiloto.trucktrip

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.cerrarButton
import android.widget.ArrayAdapter

enum class ProviderType{
    BASIC
}
class RegistrationActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?: "")


        //Guardado de datos

        val prefs = getSharedPreferences(getString(R.string.pr), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

    }
    private fun setup(email: String, provider: String) {


        title = "Inicio"

        val spinner = findViewById<Spinner>(R.id.idSpinner)
        val lista = resources.getStringArray(R.array.user_type)

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adapter

        val vr: String = spinner.getSelectedItem().toString()

        emailtextView.text = email
        provedortextView.text = provider


        cerrarButton.setOnClickListener{

            val prefs = getSharedPreferences(getString(R.string.pr), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
        guardarButton.setOnClickListener{

            db.collection("users").document(email).set(
                hashMapOf(
                    "Primary_Key" to vr,
                    "Provider" to provider,
                    "First_name" to nameText.text.toString(),
                    "Last_name" to lastnameText.text.toString(),
                    "Id" to cedulaText.text.toString(),
                    "Address" to addressText.text.toString(),
                    "Phone" to phoneText.text.toString())
            )

        }

    }
}