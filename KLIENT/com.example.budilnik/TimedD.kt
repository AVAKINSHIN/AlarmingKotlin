package com.example.budilnik
import android.os.Build
import kotlinx.coroutines.delay
import java.time.Instant
class TimedD(var g: Int, var m: Int, var d: Int, var h: Int, var mi: Int)
{

    fun getYear(): Int { return g }
    fun getMonth(): Int { return m }
    fun getDay(): Int { return d }
    fun getHour(): Int { return h }
    fun getMinute(): Int { return mi }
    fun normalTime(hd: Int, mid: Int): Boolean { return hd >= 0 && hd <= 23 && mid >= 0 && mid <= 59 }
    fun normalMonth(md: Int): Boolean { return md >= 1 && md <= 12 }
    fun normalDate(gd: Int, md: Int, dd: Int): Boolean
    {
        if (!normalMonth(md)) { return false }
        if (md == 2)
        {
            if (gd % 4 == 0) { return dd >= 1 && dd <= 29 }
            return dd >= 1 && dd <= 28
        }
        if (md == 4 || md == 6 || md == 9 || md == 11) { return dd >= 1 && dd <= 30 }
        return dd >= 1 && dd <= 31
    }
    fun compareTo(r: TimedD): Short
    {
        if (g > r.getYear()) { return 1 }
        if (g < r.getYear()) { return -1 }
        if (m > r.getMonth()) { return 1 }
        if (m < r.getMonth()) { return -1 }
        if (d > r.getDay()) { return 1 }
        if (d < r.getDay()) { return -1 }
        if (h > r.getHour()) { return 1 }
        if (h < r.getHour()) { return -1 }
        if (mi > r.getMinute()) { return 1 }
        if (mi < r.getMinute()) { return -1 }
        return 0
    }
    fun addYear(gd: Int): TimedD
    {
        var ga: Int
        try { ga = g + gd }
        catch (e: ArithmeticException)
        { println(e); return this }
        return TimedD(ga, m, d, h, mi)
    }
    fun addMonth(md: Int): TimedD
    {
        var ma: Int; var gd = g
        try { ma = m + md }
        catch (e: ArithmeticException) { println(e); return this }
        while (!normalMonth(ma))
        {
            if (ma > 12)
            {
                try { ma = ma - 12; gd++ }
                catch (e: ArithmeticException) { println(e); break }
            }
            else
            {
                if (ma < 1)
                {
                    try { ma = ma + 12; gd-- }
                    catch (e: ArithmeticException) { println(e); break }
                }
                else { break }
            }
        }
        return TimedD(gd, ma, d, h, mi)
    }
    fun addDayBlock(ma: Int, da: Int): TimedD
    {
        val r = addMonth(ma)
        val dd: Int
        try { dd = r.getDay() + da }
        catch (e: ArithmeticException) { println(e); return TimedD(0, 1, 1, 0, 0) }
        return TimedD(r.getYear(), r.getMonth(), dd, r.getHour(), r.getMinute())
    }
    fun addDay(dd: Int): TimedD
    {
        var r = TimedD(g, m, d + dd, h, mi)
        while(!normalDate(r.getYear(), r.getMonth(), r.getDay()))
        {
            if (!normalMonth(r.getMonth())) { r = r.addMonth(0) }
            else
            {
                if (r.getMonth() == 2)
                {
                    if (r.getYear() % 4 == 0)
                    {
                        if (r.getDay() >= 1 && r.getDay() <= 29) { break }
                        else
                        {
                            r = (if (r.getDay() > 29) { r.addDayBlock(1, -29) }
                            else
                            { r.addDayBlock(-1, 29) }).also { it }
                        }
                    }
                    else
                    {
                        if (r.getDay() >= 1 && r.getDay() <= 28) { break }
                        else
                        {
                            r = (if (r.getDay() > 28) { r.addDayBlock(1, -28) }
                            else
                            { r.addDayBlock(-1, 28) }).also { it }
                        }
                    }
                }
                else
                {
                    if (r.getMonth() == 4 || r.getMonth() == 6 || r.getMonth() == 9 || r.getMonth() == 11)
                    {
                        if (r.getDay() >= 1 && r.getDay() <= 30) { break }
                        else
                        {
                            r = (if (r.getDay() > 30) { r.addDayBlock(1, -30) }
                            else
                            { r.addDayBlock(-1, 30) }).also { it }
                        }
                    }
                    else
                    {
                        if (r.getDay() >= 1 && r.getDay() <= 31) { break }
                        else
                        {
                            r = (if (r.getDay() > 31) { r.addDayBlock(1, -31) }
                            else { r.addDayBlock(-1, 31) }).also { it }
                        }
                    }
                }
            }
        }
        return r
    }
    fun addWeek(wd: Int): TimedD { return addDay(wd * 7) }
    fun addToDate(r: TimedD): TimedD
    {
        var mid: Int; var hd: Int; var dd = d
        try { mid = mi + r.getMinute(); hd = h + r.getHour() } catch (e: ArithmeticException) { println(e); return this }
        while (!normalTime(hd, mid))
        {
            if (mid > 59)
            { try { mid = mid - 60; hd++ } catch (e: ArithmeticException) { println(e); break } }
            else
            {
                if (mid < 0)
                { try { mid = mid + 60; hd-- } catch (e: ArithmeticException) { println(e); break } }
                else
                {
                    if (hd > 23)
                    { try { hd = hd - 24; dd++ } catch (e: ArithmeticException) { println(e); break } }
                    else
                    {
                        if (hd < 0)
                        { try { hd = hd + 24; dd-- } catch (e: ArithmeticException) { println(e); break } }
                        else { break }
                    }
                }
            }

        }
        var p = TimedD(g, m, dd, hd, mid)
        p = p.addYear(r.getYear()); p = p.addMonth(r.getMonth())
        return p.addDay(r.getDay())
    }
    fun setZoned(k: Int, w: Int): TimedD { return addToDate(TimedD(0, 0, 0, k, w)) }
    fun toStrToManager(): String
    { return gron(g) + "-" + gron(m) + "-" + gron(d) + "T" + gron(h) + ":" + gron(mi) + ":00Z" }
    fun getDateString(): String
    { return gron(d) + "." + gron(m) + "." + gronny(gron(g)) }
    fun toStrToDatabase(): String
    { return gron(d) + "-" + gron(m) + "-" + gron(g) + " " + gron(h) + ":" + gron(mi) }
    suspend fun doWork() { while (compareTo(getTodayDate()) > o) { delay(60000) } }
}
