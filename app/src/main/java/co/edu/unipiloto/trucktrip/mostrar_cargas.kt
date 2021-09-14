package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_load.*
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*
import kotlinx.android.synthetic.main.activity_registration.*

class mostrar_cargas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_cargas)

        getAllDocuments()


        RequeastTransportationButton.setOnClickListener() {

           spinnerCarga.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long)
                {
                    val vr: String = spinnerCarga[p2].toString()
                    println(" ESTE ES EL VALOR DEL SPINNER $vr")

                    db.collection("Viajes").document().set(
                        hashMapOf(
                            "Name_Load" to vr,
                            "From" to FromEditText.text.toString(),
                            "Until" to UntilEditText.text.toString()
                        )
                    )
                }
               override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
            Toast.makeText(this, "Solicitud Realizada", Toast.LENGTH_SHORT).show();
        }
    }



    private fun getAllDocuments (){
        val spinner = findViewById<Spinner>(R.id.spinnerCarga)
        val arr = ArrayList<String>()

        db.collection("Cargas").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos ){
                arr.add(documento.get("Name_Load").toString())
            }
            /*arr.forEach{
                println(it)
            }*/
            val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_item, arr)
            spinner.adapter = adaptor
        }
    }
}