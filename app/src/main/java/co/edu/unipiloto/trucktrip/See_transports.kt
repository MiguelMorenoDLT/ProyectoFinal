package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_see_transports.*

class See_transports : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_transports)
/*
        val carga : String
        val viaje : String

        db.collection("Viajes").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos ){
                CargaText.text = "Carga: "+documento.get("Name_Load").toString()
                ViajeText.text = "Viaje: "+documento.get("From").toString() + "-" + documento.get("Until").toString()
            }
        }
*/
        val spinner = findViewById<Spinner>(R.id.SpinnerTrips)
        val arr = ArrayList<String>()

        db.collection("Viajes").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos ){
                arr.add("Carga: "+ documento.get("Name_Load").toString() + " Viaje: "+documento.get("From").toString() + "-" + documento.get("Until").toString())
            }

            val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_item, arr)
            spinner.adapter = adaptor
        }
    }
}