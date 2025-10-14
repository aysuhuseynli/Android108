package com.example.android108

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android108.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var itemList: List<Item> = emptyList()
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         itemList = listOf(
            Item(title = "1234", desc = "desc123", R.drawable.ic_launcher_background),
            Item(title = "title", desc = "title desc", R.drawable.ic_launcher_background),
            Item(title = "new", desc = "desc", R.drawable.ic_launcher_background),
            Item(title = "number", desc = "desc number", R.drawable.ic_launcher_background),
            Item(title = "element", desc = "desc3", R.drawable.ic_launcher_background),
            Item(title = "text", desc = "desc45", R.drawable.ic_launcher_background)
        )

        adapter = ItemAdapter(
            onClick = {
                sendData(it)
            },
            setSelectedMode = {
                binding.binBtn.visibility = if (it) View.VISIBLE else View.GONE
            }
        )
        adapter.submitList(itemList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        binding.searchBox.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {

            }

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                searchFilter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    fun sendData(item: Item) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("item", item)
        startActivity(intent)
    }

    fun searchFilter(query: String){
        val filteredList = if (query.isNotEmpty()){
            itemList.filter { it.title.contains(query, ignoreCase = true) }
        } else{
            itemList
        }

        adapter.submitList(filteredList)
        adapter.notifyDataSetChanged()
    }
}