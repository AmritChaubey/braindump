package com.example.braindump

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_braindump.view.*

class ListAdaptor(

    private val lists: MutableList<List>

) : RecyclerView.Adapter<ListAdaptor.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.items_braindump,
            parent,
            false
             )
        )
    }

    fun addList(list: List){
        lists.add(list)
        notifyItemInserted(lists.size-1)
    }
    fun deleteList(){
        lists.removeAll {
            list -> list.checkeditem
        }
        notifyDataSetChanged()
    }

    private fun togglestick( tvBraindumpTitle: TextView, checkeditem: Boolean ){
        if (checkeditem){
            tvBraindumpTitle.paintFlags = tvBraindumpTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            tvBraindumpTitle.paintFlags = tvBraindumpTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val curlist = lists[position]
        holder.itemView.apply {
            tvBraindumpTitle.text = curlist.title
            cbBraindumpDone.isChecked = curlist.checkeditem
            togglestick(tvBraindumpTitle, curlist.checkeditem)
            cbBraindumpDone.setOnCheckedChangeListener { _, checkeditem ->
                togglestick(tvBraindumpTitle, checkeditem)
                curlist.checkeditem = !curlist.checkeditem
            }
        }
    }

    override fun getItemCount(): Int {
    return lists.size
    }
}