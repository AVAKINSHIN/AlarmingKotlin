package com.example.budilnik
import java.util.Calendar
class ExeptionsCin(val g: String, val m: String, val d: String, val h: String, val mi: String)
{
    fun exeptionsJob(): ArrayListString
    {
        var j: Int
        val f = ArrayListString()
        var ce = false
        var ed = false
        var x = ExceptionCatch(g, "", god_num)
        if (x.expComm() == "")
        {
            j = x.intVal(); ed = true
            if (j < Calendar.getInstance().get(Calendar.YEAR)) { f.add(data_exp); ce = true }
        }
        else { f.add(x.expComm()) }
        x = ExceptionCatch (m, "", framer("Месяц", "1", "12"))
        if (x.expComm() == "")
        {
            j = x.intVal()
            if ((j < 1 || j > 12)) { f.add(framer2("Месяц", "1", "12")) }
            else
            {
                if (j < Calendar.getInstance().get(Calendar.MONTH) + 1) {
                    if (ed)
                    {
                        if (g.toInt() <= Calendar.getInstance().get(Calendar.YEAR) && !ce)
                        { f.add(data_exp); ce = true }
                    }
                }
            }
        }
        else { f.add(x.expComm()); ed = false }
        x = ExceptionCatch (d, "", framer("День", "1", "31"))
        if (x.expComm() == "")
        {
            j = x.intVal()
            if (ed)
            {
                if (j < Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                    m.toInt() <= Calendar.getInstance().get(Calendar.MONTH) + 1
                    && g.toInt() <= Calendar.getInstance().get(Calendar.YEAR) && !ce) {f.add(data_exp); ce = true }
                else
                {
                    if (m.toInt() == 2)
                    {
                        if (g.toInt() % 4 == 0)
                        { if (j < 1 || j > 29) { f.add(daymonth("2", "1", "29")) } }
                        else
                        { if (j < 1 || j > 28) { f.add(daymonth("2", "1", "28")) } }
                    }
                    else
                    {
                        if (m.toInt() == 4 || m.toInt() == 6 || m.toInt() == 9 || m.toInt() == 11)
                        { if (j < 1 || j > 30) { f.add(daymonth(m, "1", "30")) } }
                        else { if (j < 1 || j > 31) { f.add(daymonth(m, "1", "31")) } }
                    }
                }
            }
        }
        else { f.add(x.expComm()); ed = false }
        x = ExceptionCatch (h, "", framer("Час", "0", "23"))
        if (x.expComm() == "")
        {
            j = x.intVal()
            if (j < 0 || j > 23) { f.add(framer2("Час", "0", "23")) }
            else
            {
                if (ed && j < Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                        && d.toInt() <= Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                        m.toInt() <= Calendar.getInstance().get(Calendar.MONTH) + 1
                        && g.toInt() <= Calendar.getInstance().get(Calendar.YEAR) && !ce)
                {f.add(data_exp); ce = true}
            }
        }
        else { f.add(x.expComm())}
        x = ExceptionCatch (mi, "", framer("Минута", "0", "59"))
        if (x.expComm() == "")
        {
            j = x.intVal()
            if (j < 0 || j > 59) { f.add(framer2("Минута", "0", "59")) }
            else
            {
                if (ed && j < Calendar.getInstance().get(Calendar.MINUTE)
                    && h.toInt() <= Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                    && d.toInt() <= Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                    m.toInt() <= Calendar.getInstance().get(Calendar.MONTH) + 1
                    && g.toInt() <= Calendar.getInstance().get(Calendar.YEAR) && !ce) {f.add(data_exp)}
            }
        }
        else { f.add(x.expComm()) }
        return f
    }
}
