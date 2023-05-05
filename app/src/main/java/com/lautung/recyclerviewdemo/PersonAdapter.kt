package com.lautung.recyclerviewdemo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class PersonAdapter(context: Context, private var datas:ArrayList<PersonInfo>) : RecyclerView.Adapter<PersonAdapter.DiffVH>() {

    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    fun setDatas(mDatas: ArrayList<PersonInfo>) {
        datas = mDatas
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffVH {

        return DiffVH(mInflater.inflate(R.layout.item_person, parent, false))
    }

    override fun onBindViewHolder(holder: DiffVH, position: Int) {
        holder.tv_index?.text= datas[position].index.toString()
        holder.tv_name?.text=datas[position].index.toString()
    }

    override fun onBindViewHolder(holder: DiffVH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder,position)
        }else{
            val payload= payloads[0] as Bundle
            var bean=datas[position]
            payload.keySet()
            for ( key in payload.keySet() ) {
                when{
                    key.equals("KEY_INDEX")-> holder.tv_index?.text = bean.index.toString()
                    key.equals("KEY_NAME")-> holder.tv_name?.text = bean.name
                }
            }
        }
    }

    override fun getItemCount(): Int=datas.size

    class DiffVH(itemView: View) :ViewHolder(itemView) {
         var tv_index: TextView? = null
         var tv_name: TextView? = null
        init {
            tv_index = itemView.findViewById(R.id.tv_index);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }


    class DiffUtilCallBack(val newlist:List<PersonInfo>,val oldlist:List<PersonInfo>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int=oldlist.size

        override fun getNewListSize(): Int =newlist.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean=oldlist[oldItemPosition].index==newlist[newItemPosition].index

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldlist[oldItemPosition].name==newlist[newItemPosition].name
        }

    }

}