package com.example.agrivision
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
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
    var searchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchBar:EditText = findViewById(R.id.search_bar)
        val searchButton:Button = findViewById(R.id.confirm_button)

        rvPlants = findViewById(R.id.plant_list)
        plantList = mutableListOf()

        getPlantImageURL()
        searchButton.setOnClickListener{searchForPlant(search = searchBar)} // When search button is clicked


    }
    private fun searchForPlant(search:EditText){
        var str = search.getText().toString()
        val regex = "[^\\p{Alpha}+]"
       //str = str.replace(regex.toRegex(), "+")
        Log.d("confirm_Button", "Search Button Clicked! Search for ${str}, $searchQuery")
        searchQuery = str
        plantList.clear()
        getPlantImageURL()

    }
    private fun getPlantImageURL() {
        val client = AsyncHttpClient()
        val url = "https://perenual.com/api/species-list"

        val params = RequestParams()
        params["key"] = "sk-UD1D644ca5c0ecefa534"
        params["page"] = "1"
        //don't think this is working
        params["edible"] = "1"
        params["q"] = searchQuery
        Log.d("Plant params", params.toString())


       client.get(url, params, object: JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val plantsArray = json.jsonObject.getJSONArray("data")
                Log.d("Json String",json.toString())
                if(plantsArray.length() == 0){
                    //display when there are no results
                    Log.d("Plant results", "search_query")

                }
                Log.d("adapter update", "$plantList")
                // iterate thru the plants array to get individual plant objects
                for (i in 0 until plantsArray.length()) {
                    try {
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

                            Log.d("Plant Image", "No plant Image available")
                        }
                        Log.d("Plant Image", "plant image loaded")

                        plantList.add(mapOf("imageURL" to plantImageURL, "name" to plantName))
                    } catch(e:org.json.JSONException){
                        Log.d("Error","Error")

                    }
                    }//end of for loop
                val adapter = PlantAdapter(plantList)
                rvPlants.adapter = adapter

                rvPlants.layoutManager = GridLayoutManager(this@MainActivity, 2)

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
    private fun getPlantDetails(){}

}


