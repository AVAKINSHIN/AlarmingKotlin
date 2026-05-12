package com.example.budilnik
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
val add = Icons.Default.Add
val home = Icons.Default.Home
val delete = Icons.Default.Delete
val moreVert = Icons.Default.MoreVert
val back = Icons.AutoMirrored.Filled.ArrowBack
val podlog = Icons.Default.Menu
val ret = Icons.Default.Info
val datetime = Icons.Default.DateRange
val exit = Icons.AutoMirrored.Filled.ExitToApp
val settings = Icons.Default.Settings
val dialogIcon = Icons.Default.Warning
val calen = Icons.Default.DateRange
val kontact = Icons.Default.AccountBox
val enoti = Icons.Default.Refresh
@Composable
fun I(u: ImageVector, s: String) { Icon(u, contentDescription = s) }
@Composable
fun IU(co: String)
{
    if (co == "") { I(exit, "Выйти из приложения") }
    else { I(back, "Назад") }
}
