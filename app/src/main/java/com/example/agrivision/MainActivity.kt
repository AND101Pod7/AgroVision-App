package com.example.agrivision
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var origPlantList:  MutableList<Map<String, String>>
    private lateinit var rvPlants: RecyclerView
    private lateinit var editText: AppCompatEditText
    //private lateinit var filteredPlantList: MutableList<Map<String, String>>

    var plantName = ""
    var plantImageURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlants = findViewById(R.id.plant_list)
        origPlantList = mutableListOf()

        getPlantImageURL()
        Log.d("plantImageURL", "plant image URL set")

        editText = findViewById(R.id.search_bar)
        editText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().toLowerCase(Locale.getDefault())
            Log.d("EditText", "Text changed: $query")
            filterWithQuery(query)
        }//??? doOnTextChanged and filterWithQuery

    }//end OnCreate method

    private fun filterWithQuery(query: String) {
        val filteredList: MutableList<Map<String, String>> = mutableListOf()
        for (plant in origPlantList) {
            if (plant["name"]!!.toLowerCase(Locale.getDefault()).contains(query)) {
                filteredList.add(plant)
            }
        }
        val adapter = PlantAdapter(filteredList)
        rvPlants.adapter = adapter
    }

    /*private fun attachAdapter(list: MutableList<Map<String, String>>) { } */
    private fun getPlantImageURL() {
        val client = AsyncHttpClient()
        val url = "https://perenual.com/api/species-list"
        val params = RequestParams()
        params["key"] = "sk-UD1D644ca5c0ecefa534"

        //this isn't working
        params["edible"] = "1"

        client.get(url, params, object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val plantsArray = json.jsonObject.getJSONArray("data")
                Log.d("Plant List", plantsArray.toString())
                // iterate thru the plants array to get individual plant objects
                for (i in 0 until plantsArray.length()) {
                    val plant = plantsArray.getJSONObject(i)
                    plantName = plant.getString("common_name")
                    Log.d("Plant Name", plantName)

                    //in JSON example, original_url is nested inside the default_image object which is imageObject here
                    val imageObject = plant.getJSONObject("default_image")

                    if (imageObject.getString("original_url").isNotEmpty()) {
                        plantImageURL = imageObject.getString("original_url")
                    } else {
                        // if the plant doesn't have an image, call the function again to get a new plant
                        getPlantImageURL()
                    }
                    Log.d("Plant Image", "plant image loaded")

                    origPlantList.add(mapOf("imageURL" to plantImageURL , "name" to plantName))
                }//end of for loop

                val adapter = PlantAdapter(origPlantList)
                rvPlants.adapter = adapter
                Log.d("Plant Adapter", "Adapter set")
                rvPlants.setLayoutManager(GridLayoutManager(this@MainActivity,2))

            }//end of onSuccess

            override fun onFailure(statusCode: Int, headers: Headers?, response: String, throwable: Throwable?) {
                Log.d("Plant Load Error", response)
            }//end onFailure
        })
    }//end getPlantImageURL

 /* private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getPlantImageURL()
            Glide.with(this)
                .load(plantImageURL)
                .fitCenter()
                .into(imageView)
            val plantName = findViewById<TextView>(R.id.plant_name)
            plantName.text = title
        }
    }
    */

}//end mainActivity


