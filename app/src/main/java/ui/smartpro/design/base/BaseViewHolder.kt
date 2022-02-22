package ui.smartpro.design.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ui.smartpro.design.data.Lessons

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(lessonsData: Lessons)


}