package com.example.agrivision

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlantAdapter (private val plantList: MutableList<String>) : RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val plantImage: ImageView
        val plantName: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            plantImage = view.findViewById(R.id.plant_image)
            plantName = view.findViewById(R.id.plant_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_plant_item, parent, false)
        Log.d("PlantAdapter", "Creating new ViewHolder")

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(plantList[position])
            .centerCrop()
            .into(holder.plantImage)

        holder.plantName.text = plantList[position]

        Log.d("PlantAdapter", "Binding plant at position $position")
    }

    override fun getItemCount() = plantList.size
}
