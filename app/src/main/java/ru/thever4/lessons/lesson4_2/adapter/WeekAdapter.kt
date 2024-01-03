package ru.thever4.lessons.lesson4_2.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import ru.thever4.lessons.lesson4_2.data.Lesson
import ru.thever4.lessons.lesson4_2.databinding.SheduleItemBinding

class WeekAdapter : RecyclerView.Adapter<WeekAdapter.LessonViewHolder>() {

    private val lessonList: MutableList<Lesson> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SheduleItemBinding.inflate(inflater, parent, false)
        return LessonViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(lessonList[position])
    }

    override fun getItemCount(): Int = lessonList.size

    fun submitList(lessons: List<Lesson>) {
        lessonList.clear()
        lessonList.addAll(lessons)
    }

    class LessonViewHolder(private val binding: SheduleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(lesson: Lesson) = with(binding) {
            timeStart.text = lesson.startTime.toString()
            timeEnd.text = lesson.endTime.toString()
            title.text = lesson.name
            audience.text = "Аудитория: ${lesson.cabinet}"
            teacher.text = "Преподаватель: ${lesson.lecturer}"
        }
    }
}