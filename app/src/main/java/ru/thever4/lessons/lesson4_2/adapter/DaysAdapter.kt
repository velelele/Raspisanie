package ru.thever4.lessons.lesson4_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.thever4.lessons.lesson4_2.data.Day
import ru.thever4.lessons.lesson4_2.databinding.DayOfWeekBinding

class DaysAdapter : RecyclerView.Adapter<DaysAdapter.WeekViewHolder>() {

    private val list: MutableList<Day> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DayOfWeekBinding.inflate(inflater, parent, false)
        return WeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun clearList() {
        this.list.clear()
        notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }

    fun submitList(newList: List<Day>) {
        this.list.clear()
        this.list.addAll(newList)
        notifyDataSetChanged() // Уведомляем адаптер об изменениях
    }

    class WeekViewHolder(private val binding: DayOfWeekBinding) : RecyclerView.ViewHolder(binding.root) {
        private var adapter: WeekAdapter = WeekAdapter()

        fun bind(day: Day) = with(binding) {
            binding.dayOfWeekRecyclerView.adapter = adapter
            binding.dayOfWeekRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
            adapter.submitList(day.lessons)
            binding.dayOfWeek.text = day.ofWeek.toString()
        }
    }
}
