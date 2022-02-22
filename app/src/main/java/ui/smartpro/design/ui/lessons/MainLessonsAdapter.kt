package ui.smartpro.design.ui.lessons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import de.hdodenhof.circleimageview.CircleImageView
import ui.smartpro.design.R
import ui.smartpro.design.repository.OnItemViewClickListener
import ui.smartpro.design.base.BaseViewHolder
import ui.smartpro.design.data.Lessons
import ui.smartpro.design.data.TYPE

class MainLessonsAdapter(
    private var onItemViewClickListener:
    OnItemViewClickListener
) : RecyclerView.Adapter<BaseViewHolder>() {

    private var lessonsList: List<Lessons> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_ONE -> MainLessonsViewHolder(
                inflater.inflate(R.layout.item_lessons_rv, parent, false) as View
            )
            else -> MainLessonsNoVideoViewHolder(
                inflater.inflate(
                    R.layout.item_lessons_no_video_rv, parent,
                    false
                ) as View
            )

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(lessonsList[position])
        holder.itemView.setOnClickListener {
            onItemViewClickListener.onItemViewClick(lessonsList[position])
        }
    }

    override fun getItemCount(): Int = lessonsList.size

    override fun getItemViewType(position: Int): Int {
        return when {
            lessonsList[position].type == TYPE.VIDEO -> TYPE_ONE
            else -> TYPE_TWO
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setLessons(organisationsNear: List<Lessons>) {
        this.lessonsList = organisationsNear
        notifyDataSetChanged()
    }

    inner class MainLessonsViewHolder(view: View) :
        BaseViewHolder(view) {


        override fun bind(lessonsData: Lessons) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.item_name).text = lessonsData.name
                itemView.findViewById<TextView>(R.id.item_time).text = lessonsData.time
                lessonsData.image?.let {
                    itemView.findViewById<CircleImageView>(R.id.item_image).setImageResource(it)
                }
                itemView.findViewById<ShapeableImageView>(R.id.item_open_in).setOnClickListener {
                    onItemViewClickListener.onItemViewClick(lessonsList[position])
                }
            }
        }
    }

    inner class MainLessonsNoVideoViewHolder(view: View) :
        BaseViewHolder(view) {

        override fun bind(lessonsData: Lessons) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.item_name).text = lessonsData.name
                itemView.findViewById<TextView>(R.id.item_time).text = lessonsData.time
                lessonsData.image?.let {
                    itemView.findViewById<CircleImageView>(R.id.item_image).setImageResource(it)
                }
            }
        }
    }

    companion object {
        private const val TYPE_ONE = 0
        private const val TYPE_TWO = 1
    }
}


