package com.example.android108

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val itemList = listOf(
            Item(title = "1234", desc = "desc123", R.drawable.ic_launcher_background),
            Item(title = "title", desc = "title desc", R.drawable.ic_launcher_background),
            Item(title = "new", desc = "desc", R.drawable.ic_launcher_background),
            Item(title = "number", desc = "desc number", R.drawable.ic_launcher_background),
            Item(title = "element", desc = "desc3", R.drawable.ic_launcher_background),
            Item(title = "text", desc = "desc45", R.drawable.ic_launcher_background)
        )

        val adapter = ItemAdapter(
            itemList = itemList,
            onClick = {
                sendData(it)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun sendData(item: Item) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
    }
}