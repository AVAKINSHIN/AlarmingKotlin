package com.example.budilnik
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
var nD = -1
var y = true
var sp = false
var cu = "Guest"
var scj = 0
var pu = 0

var fl by mutableStateOf("172.18.204.119")
fun getDayOfWeek(n: Int): String
{
    return when (n)
    {
        1 -> "Вс"; 2 -> "Пн"; 3 -> "Вт"; 4 -> "Ср"
        5 -> "Чт"; 6 -> "Пт"; 7 -> "Сб"; else -> n.toString()
    }
}
fun getDayOfWeekFull(n: Int): String
{
    return when (n)
    {
        1 -> "Воскресенье"; 2 -> "Понедельник"; 3 -> "Вторник"; 4 -> "Среда"
        5 -> "Четверг"; 6 -> "Пятница"; 7 -> "Суббота"; else -> n.toString()
    }
}
fun getDayOfWeekReverse(n: String): Int
{
    val k = n.replace("\\s".toRegex(), "")
    return when (k)
    {
        "Воскресенье" -> 1; "Понедельник" -> 2; "Вторник" -> 3; "Среда" -> 4
        "Четверг" -> 5; "Пятница" -> 6; "Суббота" -> 7; else -> 8
    }
}
fun getCurrentWeek(): String
{
    val dt = getTodayDate().addDay(getTodayDayOfWeek() - 2)
    val df = dt.addDay(6)
    return dt.getDateString() + "-" + df.getDateString()
}
fun getNextWeek(): String
{
    val dt = getTodayDate().addDay(getTodayDayOfWeek() - 2).addDay(7)
    val df = dt.addDay(6)
    return dt.getDateString() + "-" + df.getDateString()
}
