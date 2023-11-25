package com.example.anvanced_android

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class ProductAdapterItem(var context:Context,var list: List<Products>):
    RecyclerView.Adapter<ProductAdapterItem.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView =itemView.findViewById(R.id.img1)
        val title :TextView = itemView.findViewById(R.id.title)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterItem.ViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.products_item_list,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductAdapterItem.ViewHolder, position: Int) {
        Glide.with(context).load(list[position].image).into(holder.rvImage)
        holder.title.text= list[position].title

    }

    override fun getItemCount(): Int {
        return list.count()
    }


}