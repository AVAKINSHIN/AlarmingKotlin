package com.example.budilnik
import com.example.budilnik.ui.screens.budilnikManager
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
class FileR(parent: File, child: String): File(parent, child)
{
    var t = ""
    fun kirillitsa(w: Char): String
    {
        return when (w) {
            'А' -> "*%A%"; 'Б' -> "*%B%"; 'В' -> "*%V%"; 'Г' -> "*%G%"; 'Д' -> "*%D%"; 'Е' -> "*%E%"
            'Ё' -> "*%E:"; 'Ж' -> "*%G:"; 'З' -> "*%Z%"; 'И' -> "*%I%"; 'Й' -> "*%I:"; 'К' -> "*%K%"
            'Л' -> "*%L%"; 'М' -> "*%M%"; 'Н' -> "*%H%"; 'О' -> "*%O%"; 'П' -> "*%P%"; 'Р' -> "*%R%"
            'С' -> "*%S%"; 'Т' -> "*%T%"; 'У' -> "*%Y%"; 'Ф' -> "*%F%"; 'Х' -> "*%X%"; 'Ц' -> "*%C%"
            'Ч' -> "*%J%"; 'Ш' -> "*%Q%"; 'Щ' -> "*%Q:"; 'Ъ' -> "*%W%"; 'Ы' -> "*%U:"; 'Ь' -> "*%W:"
            'Э' -> "*%J:"; 'Ю' -> "*%U%"; 'Я' -> "*%A:"; 'а' -> "*%a%"; 'б' -> "*%b%"; 'в' -> "*%v%"
            'г' -> "*%g%"; 'д' -> "*%d%"; 'е' -> "*%e%"; 'ё' -> "*%e:"; 'ж' -> "*%g:"; 'з' -> "*%z%"
            'и' -> "*%i%"; 'й' -> "*%i:"; 'к' -> "*%k%"; 'л' -> "*%l%"; 'м' -> "*%m%"; 'н' -> "*%h%"
            'о' -> "*%o%"; 'п' -> "*%p%"; 'р' -> "*%r%"; 'с' -> "*%s%"; 'т' -> "*%t%"; 'у' -> "*%y%"
            'ф' -> "*%f%"; 'х' -> "*%x%"; 'ц' -> "*%c%"; 'ч' -> "*%j%"; 'ш' -> "*%q%"; 'щ' -> "*%q:"
            'ъ' -> "*%w%"; 'ы' -> "*%u:"; 'ь' -> "*%w:"; 'э' -> "*%j:"; 'ю' -> "*%u%"; 'я' -> "*%a:"
            else -> w.toString()
        }
    }
    fun reverseKirillitsa(w: String): String
    {
        return when (w) {
            "*%A%" -> "А"; "*%B%" -> "Б"; "*%V%" -> "В"; "*%G%" -> "Г"; "*%D%" -> "Д"; "*%E%" -> "Е"
            "*%E:" -> "Ё"; "*%G:" -> "Ж"; "*%Z%" -> "З"; "*%I%" -> "И"; "*%I:" -> "Й"; "*%K%" -> "К"
            "*%L%" -> "Л"; "*%M%" -> "М"; "*%H%" -> "Н"; "*%O%" -> "О"; "*%P%" -> "П"; "*%R%" -> "Р"
            "*%S%" -> "С"; "*%T%" -> "Т"; "*%Y%" -> "У"; "*%F%" -> "Ф"; "*%X%" -> "Х"; "*%C%" -> "Ц"
            "*%J%" -> "Ч"; "*%Q%" -> "Ш"; "*%Q:" -> "Щ"; "*%W%" -> "Ъ"; "*%U:" -> "Ы"; "*%W:" -> "Ь"
            "*%J:" -> "Э"; "*%U%" -> "Ю"; "*%A:" -> "Я"; "*%a%" -> "а"; "*%b%" -> "б"; "*%v%" -> "в"
            "*%g%" -> "г"; "*%d%" -> "д"; "*%e%" -> "е"; "*%e:" -> "ё"; "*%g:" -> "ж"; "*%z%" -> "з"
            "*%i%" -> "и"; "*%i:" -> "й"; "*%k%" -> "к"; "*%l%" -> "л"; "*%m%" -> "м"; "*%h%" -> "н"
            "*%o%" -> "о"; "*%p%" -> "п"; "*%r%" -> "р"; "*%s%" -> "с"; "*%t%" -> "т"; "*%y%" -> "у"
            "*%f%" -> "ф"; "*%x%" -> "х"; "*%c%" -> "ц"; "*%j%" -> "ч"; "*%q%" -> "ш"; "*%q:" -> "щ"
            "*%w%" -> "ъ"; "*%u:" -> "ы"; "*%w:" -> "ь"; "*%j:" -> "э"; "*%u%" -> "ю"; "*%a:" -> "я"
            else -> w
        }
    }
    fun convertString(a: String): String
    {
        var s = ""
        for (d in 0..a.length - 1) { s = s + kirillitsa(a[d]) }
        return s
    }
    fun convertStringReverse(a: String): String
    {   var s = ""; var fo = 0
        while (fo < a.length)
        {   for (d in fo..a.length - 1)
            {   if (a[d] == '*')
                {   var p = "*"
                    try
                    { p = p + a[d + 1] + a[d + 2] + a[d + 3]; s = s + reverseKirillitsa(p); fo = d + 4; break }
                    catch (e: Exception) { println(e); s = s + a[d] }
                }
                else { s = s + a[d] }
                if (d == a.length - 1) { fo = a.length }
            }
            if (fo >= a.length) { break }
        }
        return s
    }
    fun readyText(): String
    {   var fIS: FileInputStream? = null
        try
        {
            fIS = FileInputStream(this)
            var d = -1; val bf = StringBuffer()
            while (fIS.read().also { d = it } != -1) { bf.append(d.toChar()) }
            return convertStringReverse(bf.toString())
        }
        catch (e: java.lang.Exception) { println(e) }
        finally
        {
            if (fIS != null)
            { try { fIS.close() } catch (e: IOException) { println(e) } }
        }
        return ""
    }
    fun isEmpty(): Boolean { return readyText() == "" }
    fun reader(): ArrayListBudilnik
    {
        val bu = ArrayListBudilnik()
        t = readyText()
        if (t != "")
        {
            try
            {
                val h = t.split("\n")
                for (n in h) { if (n != "") { bu.add(budilnikManager(n)) } }
            }
            catch (e: Exception) { println(e); if (t != "") { bu.add(budilnikManager(t)) } }
        }
        return bu
    }
    fun readerText(): ArrayListReq<String>
    {
        val gs = ArrayListReq<String>()
        t = readyText()
        if (t != "")
        {
            try
            {
                val h = t.split("\n")
                for (n in h) {if (n !="") { gs.add(n) } }
            }
            catch (e: Exception) { println(e); if (t != "") { gs.add(t) } }
        }
        return gs
    }
    fun writeData(data: String)
    {
        val dataR = convertString(data)
        var fOS: FileOutputStream? = null
        try { fOS = FileOutputStream(this); fOS.write(dataR.toByteArray()) }
        catch (e: Exception) { println(e) }
        finally
        {
            if (fOS != null)
            {
                try { fOS.close() }
                catch (e: IOException) { println(e) }
            }
        }
    }
    fun writeBudilnik(n: Budilnik) { writeData(n.stringBudilnik()) }
    fun appendBudilnikFile(n: Budilnik) { appendText(convertString(n.stringBudilnik() + "\n")) }
    fun writeBudilnikArrayList(bu: ArrayList<Budilnik>) { writeData(""); for (n in bu) { appendBudilnikFile(n) } }
    fun appendyText(s: String) { appendText(convertString(s)) }
    fun appendArrayList(f: ArrayListReq<String>) { for (n in f) { appendText(convertString(n + "\n"))} }
    fun deleterBudilnik(bu: ArrayList<Budilnik>, o: Budilnik) { bu.remove(o); writeBudilnikArrayList(bu) }
}
