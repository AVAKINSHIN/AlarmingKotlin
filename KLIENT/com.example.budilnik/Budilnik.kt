package com.example.budilnik
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
class Budilnik
{
    var v: String
    var dt: TimedD
    var p: Int
    constructor(ve: String, dte: TimedD, pe: Int) { v = ve; dt = dte; p = pe }
    fun getName(): String { return v } fun getTimedD(): TimedD { return dt } fun getYear(): Int { return dt.getYear() }
    fun getMonth(): Int { return dt.getMonth() } fun getDay(): Int { return dt.getDay() }
    fun getHour(): Int { return dt.getHour() }
    fun getMinute(): Int { return dt.getMinute() }
    fun compareTo(b: Budilnik): Short { return dt.compareTo(b.getTimedD()) }
    suspend fun doWork() { dt.doWork() }
    @Composable
    fun PrintBudilnik(modi: Modifier, color: Color)
    {
        Text(v + "\n" + gron(getDay()) + "." + gron(getMonth()) + "." +
                gron(getYear()) + " " + gron(getHour()) + ":" + gron(getMinute()) + "\n"
                + p + " дней интервал", modi, color = color)
    }
    fun stringBudilnik(): String
    {
        return v + " " + getYear().toString() + " " + getMonth().toString() + " " + getDay().toString() +
        " " + getHour().toString() + " " + getMinute().toString() + " " + p
    }
}
