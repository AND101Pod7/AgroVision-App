package com.example.agrivision
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    private lateinit var plantList:  MutableList<Map<String, String>>
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

    }

    private fun getPlantImageURL() {
        val client = AsyncHttpClient()
        val url = "https://perenual.com/api/species-list?key=sk-UD1D644ca5c0ecefa534"

        val params = RequestParams()
        params["key"] = "sk-UD1D644ca5c0ecefa534"
        //don't think this is working
        params["page"] = "1"
        params["edible"] = "1"

        client.get(url, params, object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val plantsArray = json.jsonObject.getJSONArray("data")
                Log.d("Plant List", plantsArray.toString())
                // iterate thru the plants array to get individual plant objects
                for (i in 0 until 30) {
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

                    plantList.add(mapOf("imageURL" to plantImageURL , "name" to plantName))
                }//end of for loop
                val adapter = PlantAdapter(plantList)
                rvPlants.adapter = adapter
                rvPlants.setLayoutManager(GridLayoutManager(this@MainActivity,2))

                Log.d("Plant Adapter", "Adapter set")

            }//end of onSuccess
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.d("Plant Load Error", response)
            }
        })
    }
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
}


