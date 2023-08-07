package com.example.agrivision

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class DetailsAdapter(private val detailList: MutableList<Map<String, String>>):AppCompatActivity() {

    class ViewHolder (view: View)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.plant_details)


        val detailsBackgroundTint:View = findViewById(R.id.plant_details_background_tint)
        detailsBackgroundTint.setOnClickListener{
            hideCard() // close activity
        }

    }

    private fun hideCard(){
        finish()
    }

}