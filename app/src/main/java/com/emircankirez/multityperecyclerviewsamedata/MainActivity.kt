package com.emircankirez.multityperecyclerviewsamedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.emircankirez.multityperecyclerviewsamedata.adapter.ItemAdapter
import com.emircankirez.multityperecyclerviewsamedata.data.DummyData
import com.emircankirez.multityperecyclerviewsamedata.databinding.ActivityMainBinding
import com.emircankirez.multityperecyclerviewsamedata.model.Item
import com.emircankirez.multityperecyclerviewsamedata.model.ItemType

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener{
    private lateinit var binding : ActivityMainBinding
    private lateinit var itemList : ArrayList<Item>
    private lateinit var adapter : ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemList = DummyData.getData()
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(itemList, this)
        binding.rvItems.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val item = itemList[position]
        when(item.viewType){
            ItemType.CALL.ordinal -> {
                Toast.makeText(this@MainActivity, "Call: ${item.content}", Toast.LENGTH_SHORT).show()
            }
            ItemType.EMAIL.ordinal ->{
                Toast.makeText(this@MainActivity, "Email: ${item.content}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onItemLongClick(position: Int) {
        val item = itemList.removeAt(position)
        adapter.notifyItemRemoved(position)
        when(item.viewType){
            ItemType.CALL.ordinal -> {
                Toast.makeText(this@MainActivity, "Call: ${item.content} deleted!", Toast.LENGTH_SHORT).show()
            }
            ItemType.EMAIL.ordinal ->{
                Toast.makeText(this@MainActivity, "Email: ${item.content} deleted!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}