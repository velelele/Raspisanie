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
import ru.thever4.lessons.lesson4_2.data.Days
import ru.thever4.lessons.lesson4_2.data.ScheduleData
import ru.thever4.lessons.lesson4_2.databinding.FragmentTodayBinding
import java.time.DayOfWeek
import java.time.LocalDate

class DayFragment : Fragment() {

    private lateinit var binding: FragmentTodayBinding
    private val daysAdapter = DaysAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rViewToday.layoutManager = LinearLayoutManager(requireContext())
        binding.rViewToday.adapter = daysAdapter


        val todayDayOfWeek = LocalDate.now().dayOfWeek


        val todayData = ScheduleData.scheduleFirstWeek.filter { day ->
            getDayOfWeekFromString(day.ofWeek) == todayDayOfWeek
        }


        daysAdapter.submitList(todayData)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDayOfWeekFromString(dayString: Days): DayOfWeek {
        return when (dayString) {
            Days.MONDAY -> DayOfWeek.MONDAY
            Days.TUESDAY -> DayOfWeek.TUESDAY
            Days.WEDNESDAY -> DayOfWeek.WEDNESDAY
            Days.THURSDAY -> DayOfWeek.THURSDAY
            Days.FRIDAY -> DayOfWeek.FRIDAY
            Days.SATURDAY -> DayOfWeek.SATURDAY
            Days.SUNDAY -> DayOfWeek.SUNDAY
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DayFragment()
    }
}