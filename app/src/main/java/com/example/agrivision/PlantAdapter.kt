package com.example.agrivision

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.math.log

class PlantAdapter(private val plantList: MutableList<Map<String, String>>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantImage: ImageView
        val plantName: TextView

        val cardButton: CardView
        init {
            // Find our RecyclerView item's ImageView for future use
            plantImage = view.findViewById(R.id.plant_image)
            plantName = view.findViewById(R.id.plant_name)
            cardButton = view.findViewById(R.id.plant_card)

            cardButton.setOnClickListener{ Log.d("Debug","Card clicked") }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.plant_recycler, parent, false)
        Log.d("PlantAdapter", "Creating new ViewHolder")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(plantList[position].getValue("imageURL"))
            .centerCrop()
            .into(holder.plantImage)

        holder.plantName.text = plantList[position].getValue("name")

        Log.d("PlantAdapter", "Binding plant at position $position")
    }

    override fun getItemCount() = plantList.size

}
