package com.example.budilnik.ui.resourses.menu
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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
class TextMenu(var v: String, val w: ArrayList<TextMenuItem>, val ch: Boolean, val ro: Boolean)
{   var tu by mutableStateOf(v)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GetInstance()
    {   var e by remember { mutableStateOf(false) }
        if (!ro) { tu = "" }
        Box {
            ExposedDropdownMenuBox(expanded = e, onExpandedChange = { e = !e}) {
                OutlinedTextField(
                    value = tu, label = { if (!ro) { Text(v) } },
                    onValueChange = { if (!ro) { tu = it; v = tu } },
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = e)},
                    readOnly = ro,
                    modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true).
                    fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = e, onDismissRequest = { e = false })
                {
                    for (n in w)
                    {
                        DropdownMenuItem (text = { Text(n.getTitle()) },
                            onClick = {
                                if (ch) { n.doWork1() } else { n.doWork2() }
                                if (!ro) { tu = n.getTitle(); v = tu }
                                e = false
                            })
                    }
                }
            }
        }
    }
}
