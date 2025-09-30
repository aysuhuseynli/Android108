package com.example.android108

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = Intent()
        val item = intent.getParcelableExtra<Item>("item")

        val image = findViewById<ImageView>(R.id.image)
        val description = findViewById<TextView>(R.id.description)

        item?.let {
            image.setImageResource(it.image)
            description.text = it.desc
        }
    }
}