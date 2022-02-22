package ui.smartpro.design.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ui.smartpro.design.R
import ui.smartpro.design.data.Homework
import ui.smartpro.design.databinding.ItemHomeworksRvBinding

class HomeworkAdapter : RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {

    private var homeworkList: List<Homework> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeworkViewHolder(
        ItemHomeworksRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
    )

    override fun getItemCount(): Int = homeworkList.size

    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        holder.bind(homeworkList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeworks(homeworks: List<Homework>) {
        this.homeworkList = homeworks
        notifyDataSetChanged()
    }

    inner class HomeworkViewHolder(private val vb: ItemHomeworksRvBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(homework: Homework) = with(vb) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemHomeworkName.text = homework.subject
                itemHomeworkOverview.text = homework.task
                itemHomeworkTime.text = homework.time.toString()
                homework.image.let { itemImage.setImageResource(R.drawable.book) }
            }
        }
    }
}
