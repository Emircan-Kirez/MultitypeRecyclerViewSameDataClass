package com.emircankirez.multityperecyclerviewsamedata.data

import com.emircankirez.multityperecyclerviewsamedata.model.Item

object DummyData {
    fun getData() : ArrayList<Item>{
        val itemList = ArrayList<Item>()

        for (i in 0..25){
            val item = Item((0..1).random(), "Content $i")
            itemList.add(item)
        }

        return itemList
    }
}