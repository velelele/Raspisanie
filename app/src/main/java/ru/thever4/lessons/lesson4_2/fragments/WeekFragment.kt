package ru.thever4.lessons.lesson4_2.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.thever4.lessons.lesson4_2.adapter.DaysAdapter
import ru.thever4.lessons.lesson4_2.data.Day
import ru.thever4.lessons.lesson4_2.data.ScheduleData
import ru.thever4.lessons.lesson4_2.databinding.FragmentWeekBinding

class WeekFragment : Fragment() {

    private lateinit var binding: FragmentWeekBinding
    private val daysAdapter = DaysAdapter()

    private var currentWeek = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeekBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rViewWeek.layoutManager = LinearLayoutManager(requireContext())
        binding.rViewWeek.adapter = daysAdapter
        updateSchedule()
        binding.changeWeekButton.setOnClickListener {
            currentWeek = if (currentWeek == 1) 2 else 1
            updateSchedule()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateSchedule() {
        val scheduleForCurrentWeek: List<Day> =
            if (currentWeek == 1) ScheduleData.scheduleFirstWeek else ScheduleData.scheduleSecondWeek
        daysAdapter.submitList(scheduleForCurrentWeek)
        binding.dayInfo.text = if (currentWeek == 1) "Первая неделя" else "Вторая неделя"
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeekFragment()
    }
}