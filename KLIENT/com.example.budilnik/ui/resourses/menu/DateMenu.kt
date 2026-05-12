package com.example.budilnik.ui.resourses.menu
import com.example.budilnik.Budilnik
import com.example.budilnik.ui.resourses.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
class DateMenu(val l: String, var dTR: Budilnik, val s: Int)
{
    var tu by mutableStateOf(getDark())
    fun setBudilnikData(z: String)
    {
        var w: Int
        try { w = z.toInt() } catch (e: Exception) { println(e); return }
        if (l == "День") { dTR.dt.d = w; return }
        if (l == "Месяц") { dTR.dt.m = w; return }
        if (l == "Год") { dTR.dt.g = w; return }
        if (l == "Час") { dTR.dt.h = w; return }
        dTR.dt.mi = w; comb.writeBudilnik(dTR)
    }
    fun setComponent(t: String) { tu = t; setBudilnikData(tu) }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GetInstanceDay(gt: Boolean, jo: DateMenu, je: DateMenu, v: Int, vi: Int) {
        var ed by remember { mutableStateOf(false) }
        Box {
            ExposedDropdownMenuBox(expanded = ed, onExpandedChange = { ed = !ed }) {
                OutlinedTextField(
                    value = tu, label = { Text(l) }, onValueChange = { tu = it; setBudilnikData(tu) },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = ed) },
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true)
                        .width(s.dp)
                )
                ExposedDropdownMenu(expanded = ed, onDismissRequest = { ed = false })
                {
                    var st = v; var fi = vi
                    if (gt) { st = 1
                        fi = (if (jo.getDark() == "2") {
                            if (je.getDark().toInt() % 4 == 0) { 29 } else { 28 }
                        } else {
                            if (jo.getDark() == "4" || jo.getDark() == "6" || jo.getDark() == "9"
                                || jo.getDark() == "11") { 30 } else { 31 }
                        })
                    }
                    for (n in st..fi) {
                        DropdownMenuItem(text = { Text(n.toString()) },
                            onClick = { tu = n.toString(); setBudilnikData(n.toString()); ed = false })
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GetInstance(v: Int, vi: Int) { GetInstanceDay(false, DateMenu(l, dTR, s), DateMenu(l, dTR, s), v, vi) }
    fun getDark(): String {
        if (l == "День") { return dTR.dt.d.toString() }
        if (l == "Месяц") { return dTR.dt.m.toString() }
        if (l == "Год") { return dTR.dt.g.toString() }
        if (l == "Час") { return dTR.dt.h.toString() }
        return dTR.dt.mi.toString()
    }
}
