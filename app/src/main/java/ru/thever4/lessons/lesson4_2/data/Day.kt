package ru.thever4.lessons.lesson4_2.data

import java.time.LocalDate

data class Day(
    val ofWeek: Days,
    val lessons: List<Lesson>,
)

