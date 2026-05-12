package com.example.budilnik
import com.example.budilnik.ui.resourses.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.budilnik.ui.screens.S
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnsafeIntentLaunch", "UnusedMaterial3ScaffoldPaddingParameter")
@Suppress("SYNTHETIC_PROPERTY_WITHOUT_JAVA_ORIGIN")
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity()
{
    lateinit var cx: Context
    lateinit var dbH: DatabaseR
    lateinit var dbR: SQLiteDatabase
    fun startAddActivity()
    {
        intent = Intent(cx, EditActivity::class.java)
        y = false; nD = -1; cx.startActivity(intent)
    }
    fun startSettingsActivity()
    {
        intent = Intent(cx, SettingsActivity::class.java)
        cx.startActivity(intent)
    }
    fun startResponceActivity(uy: Int, pt: Int)
    {
        intent = Intent(cx, ResponseActivity::class.java)
        scj = uy; pu = pt; cx.startActivity(intent)
    }
    @Composable fun RowScope.BudilnikName(weight: Float, n: Int, kcol: Color)
    {
        bu[n].PrintBudilnik(mod.weight(weight).padding(8.dp), kcol)
    }
    @Composable fun DrawerSheet(drawerState: DrawerState, scope: CoroutineScope)
    {
        ModalDrawerSheet {
            Column(
                modifier = mod.padding(horizontal = 16.dp).verticalScroll(rememberScrollState())
            ) {
                S(12)
                Text("Меню", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                Text("Основное меню", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = {  Text("Добавить задачу") },
                    selected = false,
                    icon = { Icon(add, "") },
                    onClick = { startAddActivity() }
                )
                NavigationDrawerItem(
                    label = { Text("Выход из приложения") },
                    selected = false,
                    icon = { Icon(exit, "") },
                    onClick = { finish() }
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text("Взаимодействие с сервером", modifier = Modifier.padding(16.dp))
                NavigationDrawerItem(
                    label = { Text("Работа с расписанием") },
                    selected = false,
                    icon = { Icon(datetime, "") },
                    onClick = { pu = 1; startResponceActivity(2, 1) }
                )
                NavigationDrawerItem(
                    label = { Text("Скоро добавятся новые функции") },
                    selected = false,
                    icon = { Icon(ret, "") },
                    onClick = { /* Handle click */ }
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Настройки") },
                    selected = false,
                    icon = { Icon(settings, contentDescription = null) },
                    onClick = { startSettingsActivity() }
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Скрыть меню") },
                    selected = false,
                    icon = { Icon(podlog, contentDescription = null) },
                    onClick = { scope.launch { drawerState.close() } }
                )
            }
        }
    }
    @Composable fun HomeScreen()
    {
        val cf = LocalConfiguration.current
        val sW = cf.screenWidthDp.dp
        val sH = cf.screenHeightDp.dp
        var enot by remember { mutableStateOf(false) }
        if (enot)
        {
            AlertDialog(
                onDismissRequest = { enot = false }, modifier = mod, icon = { I(dialogIcon, "") },
                title = { Text(greter) }, text = { Text(deleter) }, iconContentColor = teal700,
                confirmButton = { Button(onClick = { enot = false; bu.remove(bu[nD])
                    nD = -1; bu.obnovlenie(dbR); reload() },
                    colors = ColorB(Color.Green)) { Text("Да") }}, shape = AlertDialogDefaults.shape,
                dismissButton = { Button(onClick = { enot = false; nD = -1 },
                    colors = ColorB(Color.Red)) { Text("Нет") }},
                containerColor = Color.White, titleContentColor = Color.Black, textContentColor = Color.Blue,
            )
        }
        Column(verticalArrangement = Arrangement.Top, modifier = s)
        {
            S(45)
            Button(onClick = { startAddActivity() }, modifier = c, colors = ColorB(Color.Blue))
            { Text(text = "Добавить задачу") }
            Button(onClick = { finish() }, modifier = c, colors = ColorB(Color.Red))
            { Text(text = "Выход из приложения") }
            if (!bu.isEmpty)
            {
                LazyColumn {
                    for (n in 0..bu.size - 1)
                    {
                        var kcol = Color.White; var tcol = Color.Black
                        if (bu[n].dt.compareTo(getTodayDate()) == (-1).toShort())
                        {
                            if (bu[n].p > 0) {
                                while (bu[n].dt.compareTo(getTodayDate()) == (-1).toShort())
                                { bu[n].dt = bu[n].dt.addDay(bu[n].p) }
                            }
                            else { kcol = Color.Red; tcol = Color.White }
                        }
                        item {
                            Row(modifier = mod.border(1.dp, teal700).padding(8.dp)
                                .background(kcol),
                                verticalAlignment = Alignment.CenterVertically)
                            {
                                BudilnikName(0.5f, n, tcol)
                                Button(onClick = {
                                    intent = Intent(cx, EditActivity::class.java)
                                    y = true; nD = n; cx.startActivity(intent)
                                                 }, colors = ColorB(teal700)) { Text("Edit") }
                                IconButton(onClick = { nD = n; enot = true })
                                { I(delete, "Delete") }
                            }
                        }
                    }
                    if (sH > sW) { item { S(65) } }
                    else { item {S(45)} }
                }
            }
            else { S(45) }
        }
    }
    @Composable fun HomeScreenSk()
    {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ModalNavigationDrawer(drawerState=drawerState, drawerContent = { DrawerSheet(drawerState, scope) } )
        {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Row{ Text("Мои задачи", modifier = mod.weight(0.5f).padding(8.dp))
                            IconButton(onClick = { mA.finish() }) { IU("") } }
                                      }, colors = ColorT(teal700),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } })
                        { Icon(podlog, "Раскрыть меню") } }) },
                content = { HomeScreen() }, bottomBar = {
                    BottomAppBar(containerColor = teal700) {
                        Row(modifier = s, horizontalArrangement = w) {
                            IconButton(onClick = {
                                intent = Intent(cx, EditActivity::class.java)
                                y = false; nD = -1; cx.startActivity(intent)
                            }) { I(add, "Добавить задачу") }
                            IconButton(onClick = {startSettingsActivity()}) { I(settings, "Настройки") }
                            IconButton(onClick = { deleteEnots() }) { I(enoti, "Файловый дроп") }
                            IconButton(onClick = { finish() }) { I(exit, "Выход") }
                            IconButton(onClick = { startResponceActivity(0, 0)
                            }) { I(kontact, "Учётная запись") }
                        }
                    }
                })
        }
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState); enableEdgeToEdge(); createFiles(); mA = this
        setContent { cx = LocalContext.current; dbH = DatabaseR(cx, "Budilniki.db")
            dbR = dbH.writableDatabase; bu.loadbudilniks(dbR); HomeScreenSk() }

    }
    override fun onStart() { super.onStart() }
    override fun onResume() { super.onResume() }
    override fun onPause() { super.onPause() }
    override fun onStop() { super.onStop() }
    override fun onRestart() { super.onRestart() }
    override fun onDestroy() { this.dbH.close(); bu.clear(); super.onDestroy() }
}
