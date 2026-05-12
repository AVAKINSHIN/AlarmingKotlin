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
class SingleMenu(val v: String)
{
    var tu by mutableStateOf(v)
    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun GetInstance(h: ArrayList<String>)
    {
        var e by remember { mutableStateOf(false) }
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
                    {
                        DropdownMenuItem (text = { Text(n) },
                            onClick = { tu = n; e = false })
                    }
                }
            }
        }
    }
    fun getComponent(): String { return tu }
}
