package co.edu.unipiloto.trucktrip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_mostrar_cargas.*

class mostrar_cargas : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_cargas)

        getAllDocuments()
    }

    private fun getAllDocuments (){
        val spinner = findViewById<Spinner>(R.id.spinner)
        val arr = ArrayList<String>()

        db.collection("Cargas").get().addOnSuccessListener { basedatos ->
            for (documento in basedatos ){
                arr.add(documento.get("Name_Load").toString())
            }
            arr.forEach{
                println(it)
            }
            val adaptor = ArrayAdapter(this, android.R.layout.simple_spinner_item, arr)
            spinner.adapter = adaptor
        }
    }
}