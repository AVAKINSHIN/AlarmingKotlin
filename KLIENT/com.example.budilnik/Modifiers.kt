package com.example.budilnik
import com.example.budilnik.ui.screens.mn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import java.util.Calendar
val mod = Modifier
val c = Modifier.fillMaxWidth()
val s = Modifier.fillMaxSize()
val w = Arrangement.Center
val k = TextAlign.Center
fun modifierColor(f: Color): Modifier { return Modifier.fillMaxWidth().background(f) }
fun getTodayDate(): TimedD
{
    val gd = Calendar.getInstance().get(Calendar.YEAR)
    val md = Calendar.getInstance().get(Calendar.MONTH) + 1
    val dd = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    val hd = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val mid = Calendar.getInstance().get(Calendar.MINUTE)
    val r = TimedD(gd, md, dd, hd, mid)
    return r.setZoned(hp, 0)
}
fun getTodayDayOfWeek(): Int { return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) }
fun addTodayDayOfWeek(p: Int): Int
{
    val fg = getTodayDayOfWeek(); var kj = fg + p
    if (kj < 1) { while (kj < 1) { kj = kj + 7; if (kj in 1..7) { return kj } } }
    if (kj > 7) { while (kj > 7) { kj = kj - 7; if (kj in 1..7) { return kj } } }
    return kj
}
fun getZeroTimedD(): TimedD { return TimedD(0, 0, 0,0, 0)}
fun getTodayBudilnik(): Budilnik { return Budilnik("", getTodayDate(), 0) }
fun getZeroBudilnik(): Budilnik { return Budilnik("", getZeroTimedD(), 0) }
fun gron(n: Int): String
{
    if (n.toString().length <= 1) { return "0$n" }
    return n.toString()
}
fun gronny(n: String): String
{
    if (n.length > 2) { return "${n[n.length - 2]}${n[n.length - 1]}" }
    return n
}
fun textToTimedD(a: String): TimedD
{
    try
    {
        val w = a.split(" ")
        try
        {
            val g = w[0].split("-")
            try
            {
                val j = w[1].split(":")
                try { return mn(g[2], g[1], g[0], j[0], j[1]) }
                catch (e: Exception) { print(e); return getTodayDate() }
            }
            catch (e: Exception)
            {
                print(e)
                try { return mn(g[2], g[1], g[0], "0", "0") }
                catch (e: Exception) { print(e); return getTodayDate() }
            }
        }
        catch (e: Exception) { print(e); return getTodayDate() }
    }
    catch (e: Exception) { print(e); return getTodayDate(); }
}
