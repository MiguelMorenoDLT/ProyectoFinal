package co.edu.unipiloto.trucktrip
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class TripsDriver : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips_driver)


        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
            Info(email.toString())
        }

    }
    private fun Info (email: String){

        val From = findViewById<TextView>(R.id.FromTextEdit)
        val Until = findViewById<TextView>(R.id.UntilTextEdit)
        val Carga = findViewById<TextView>(R.id.CargaTextEdit)

        db.collection("TripDriver").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos) {
                if (documento.get("Email")?.equals(email) == true) {
                    From.text = documento.get("From").toString()
                    Until.text = documento.get("Until").toString()
                    Carga.text = documento.get("Name_Load").toString()
                }
            }
        }
    }

}