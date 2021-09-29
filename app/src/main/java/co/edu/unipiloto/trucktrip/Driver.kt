package co.edu.unipiloto.trucktrip

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.cerrarButton
import kotlinx.android.synthetic.main.activity_see_transports.*
import kotlinx.android.synthetic.main.activity_truck_manager.*

val db = FirebaseFirestore.getInstance()

class Driver : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver)

        val DriverText = findViewById<TextView>(R.id.driverText)
        val DriverTruck = findViewById<TextView>(R.id.driverTruckText)
        val DriverLoadout = findViewById<TextView>(R.id.driverloadoutText)

        db.collection("TripDriver").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos ){

                DriverText.text = "Conductor: " + documento.get("Name_Driver").toString() + " " + documento.get("Last_Name_Driver").toString()
                DriverTruck.text = "Camión: " + documento.get("Truck").toString() + " " + documento.get("Loading_Capacity").toString()
                DriverLoadout.text = "Carga: " + documento.get("Name_Load").toString()
            }
        }

        TripButton.setOnClickListener() {
            val intent = Intent(this, TripsDriver::class.java)
            startActivity(intent)
        }

        cerrarButton.setOnClickListener{

            val prefs = getSharedPreferences(getString(R.string.pr), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}