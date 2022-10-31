package com.example.anholts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_items.view.*

class MyAdapter(val context: Context, val buttonData: List<dataModelItem>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var button_location: TextView
        var lastseen: TextView

        init {
            button_location = itemView.buttonlocation
            lastseen = itemView.lastseen
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.button_location.text = buttonData[position].button_location.toString()
        holder.lastseen.text = buttonData[position].lastseen.toString()
    }

    override fun getItemCount(): Int {
        return buttonData.size
    }
}


