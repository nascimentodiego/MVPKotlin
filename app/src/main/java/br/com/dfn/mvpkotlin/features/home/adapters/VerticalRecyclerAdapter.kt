package br.com.dfn.mvpkotlin.features.home.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.dfn.mvpkotlin.R
import br.com.dfn.starwarskotlin.core.model.Film


class VerticalRecyclerAdapter(var mList: List<Film>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private lateinit var mContext: Context

    private inner class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitleView: TextView = itemView.findViewById(R.id.txt_title)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        mContext = viewGroup!!.context
        val v1 = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_film_item, viewGroup, false)
        return CellViewHolder(v1)
    }

    override fun getItemCount(): Int {
        return if (mList == null) 0 else mList.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder?, position: Int) {
        val cellViewHolder = viewHolder as CellViewHolder

        cellViewHolder.mTitleView.text = mList[position].title
    }
}