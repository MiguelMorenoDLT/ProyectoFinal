package co.edu.unipiloto.trucktrip

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_registration.cerrarButton
import kotlinx.android.synthetic.main.activity_truck_manager.*

class TruckManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_truck_manager)


        tripsButton.setOnClickListener(){
            val intent = Intent(this, See_transports::class.java)
            startActivity(intent)
        }

        RegisterTruckButton.setOnClickListener(){
            val intent = Intent(this, Register_Truck::class.java)
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