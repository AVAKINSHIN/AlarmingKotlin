package com.example.budilnik
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
class FunctionMenu(val v: String, val y: Boolean)
{   var va = 0; var tu by mutableStateOf(v)
    fun setComponent(p: MapF) { va = p.getValue2() }
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun GetInstance(h: ArrayList<MapF>)
    {   var e by remember { mutableStateOf(false) }
        Box {
            ExposedDropdownMenuBox(expanded = e, onExpandedChange = { e = !e}) {
                OutlinedTextField(
                    value = tu, label = { Text(v) }, onValueChange = { tu = it }, readOnly = true,
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = e)},
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true).
                    fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = e, onDismissRequest = { e = false })
                {
                    for (n in h)
                    {   DropdownMenuItem (text = { Text(n.getValue1()) },
                            onClick = {
                                if (y) { va = n.getValue2() }
                                else { n.doWork() }
                                tu = n.getValue1(); e = false
                            })
                    }
                }
            }
        }
    }
    fun getIntResult(): Int { return va }
}
