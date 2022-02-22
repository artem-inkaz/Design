package ui.smartpro.design.data

import ui.smartpro.design.R

data class Lessons(
    val id: Int,
    val name: String = "",
    val image: Int? = null,
    val time: String = "",
    val type: TYPE? = null
)

enum class TYPE {
    VIDEO,
    NO_VIDEO
}

fun getAllLessons() = mutableListOf(
    Lessons(1,"Математика", R.drawable.book, "8.30", TYPE.VIDEO),
    Lessons(2,"Физика", R.drawable.chemical, "9.30"),
    Lessons(3,"История", R.drawable.board, "10.30", TYPE.VIDEO),
    Lessons(4,"Литература", R.drawable.book, "11.30", TYPE.VIDEO),
    Lessons(5,"Английский", R.drawable.world, "12.45"),
    Lessons(6,"Рисование", R.drawable.art, "13.45"),
    Lessons(7,"Kotlin", R.drawable.disk, "14.45", TYPE.VIDEO)
)
