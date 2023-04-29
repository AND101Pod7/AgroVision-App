package com.example.agrivision
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var plantList:  MutableList<Pair<String, String>>
    private lateinit var rvPlants: RecyclerView

    var plantName = ""
    var plantImageURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlants = findViewById(R.id.plant_list)
        plantList = mutableListOf()

        getPlantImageURL()
        Log.d("plantImageURL", "plant image URL set")

        val adapter = PlantAdapter(plantList)
        rvPlants.adapter = adapter
        rvPlants.layoutManager = LinearLayoutManager(this)

        Log.d("Plant Adapter", "Adapter set")

    }

    private fun getPlantImageURL() {
        val client = AsyncHttpClient()
        val url = "https://perenual.com/api/species-list"
        val params = RequestParams()
        params["key"] = "sk-UD1D644ca5c0ecefa534"
        //don't think this is working
        params["edible"] = "true"

        client.get(url, params, object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val plantsArray = json.jsonObject.getJSONArray("data")
                Log.d("Plant List", plantsArray.toString())
                // iterate thru the plants array to get individual plant objects
                for (i in 0 until plantsArray.length()) {
                    val plant = plantsArray.getJSONObject(i)
                    plantName = plant.getString("common_name")
                    Log.d("Plant Name", plantName)

                    //in JSON example, original_url is nested inside the default_image object which is imageObject here
                    val imageObject = plant.getJSONObject("default_image")

                    if (imageObject.has("original_url")) {
                        plantImageURL = imageObject.getString("original_url")
                    } else {
                        // if the plant doesn't have an image, call the function again to get a new plant
                        getPlantImageURL()
                    }
                    Log.d("Plant Image", "plant image loaded")

                    plantList.add(Pair(plantImageURL, plantName))
                }

            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Plant Load Error", errorResponse)
            }
        })
    }
    private fun getNextImage(button: Button, imageView: ImageView) {
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
}


