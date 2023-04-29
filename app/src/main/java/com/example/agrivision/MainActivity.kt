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
    private lateinit var plantList: MutableList<String>
    private lateinit var rvPlants: RecyclerView

    var plantImageURL = ""
    var imagePath = ""
    var plantName = ""

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
        val url = "https://permapeople.org/api/plants?last_id=100"
        val headers = RequestHeaders()
        headers["x-permapeople-key-id"] = "JPQEwO900cMh"
        headers["x-permapeople-key-secret"] = "7834ece4-413a-48c6-ac48-a804e0167d9e"
        val params = RequestParams()

        client.get(url, headers, params, object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val plantsArray = json.jsonObject.getJSONArray("plants")
                Log.d("Plant List", plantsArray.toString())
                // Iterate over the plants array to get individual plant objects
                for (i in 0 until plantsArray.length()) {
                    val plant = plantsArray.getJSONObject(i)
                    val plantName = plant.getString("name")
                    Log.d("Plant Name", plantName)
                    // get other plant data here...

                    plantList.add(plantName)
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


