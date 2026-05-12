package com.example.budilnik
import com.example.budilnik.ui.screens.*
import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.budilnik.ui.resourses.goHome
import com.example.budilnik.ui.resourses.exitApp
import com.example.budilnik.ui.resourses.mB
import com.example.budilnik.ui.resourses.bu
import com.example.budilnik.ui.resourses.reload
class SettingsActivity: ComponentActivity()
{
    lateinit var dBH: SQLiteOpenHelper
    lateinit var dbW: SQLiteDatabase
    var q = this
    @Composable
    fun SettingsShow()
    {
        val fm = FunctionMenu("Смена часового пояса", true)
        val h = ArrayList<MapF>()
        h.add(MapF("GMT + 0", "0"))
        h.add(MapF("GMT + 3", "3"))
        val cf = LocalConfiguration.current
        val sW = cf.screenWidthDp.dp
        val sH = cf.screenHeightDp.dp
        LazyColumn(modifier = s, verticalArrangement = w) {
            item { S(45) }
            item { fm.GetInstance(h) }
            item { OutlinedTextField(value = fl, onValueChange = { fl = it },
                label = { Text("CONFIG IP") }, modifier = c
            ) }
            item { Button(onClick= {
                    bu.readersaveappend(FileR(folder, fl), dbW); hp = 0; reload()
                                   }, colors = ColorB(teal700)) { Text("Импорт из файла") } }
            item { Button(onClick= { fm.setComponent(MapF("GMT + 0)", "0")); hp = 0 }, colors = ColorB(teal700))
            { Text("Сбросить настройки") } }
            item { Button(onClick = {finish()}) { Text("Вернуться назад") } }
            if (sH > sW) { item { S(65) } }
            else { item { S(45) } }
        }
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeIntentLaunch")
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState); mB.add(this)
        if (!nDer.isEmpty())
        {
            try { nD = nDer.readyText().toInt(); sp = true }
            catch (e: Exception) { sp = false; println(e) }
        }
        setContent {
            val cx = LocalContext.current
            dBH = DatabaseR(cx, "Budilniki.db")
            dbW = dBH.writableDatabase
            Scaffold(
                topBar = { Baron("Настройки", q) }, content = { SettingsShow() },
                bottomBar = {
                    BottomAppBar(containerColor = teal700) {
                        Row(modifier = s, horizontalArrangement = w) {
                            IconButton(onClick = { goHome() }) { I(home, "Домой") }
                            if (!sp) {
                                IconButton(onClick = {
                                    intent = Intent(cx, EditActivity::class.java)
                                    y = false; nD = -1; cx.startActivity(intent)
                                }) { I(add, "Добавить будильник") }
                            }
                            IconButton(onClick = { deleteEnots() }) { I(enoti, "Файловый дроп") }
                            IconButton(onClick = { exitApp() }) { I(exit, "Выход") }
                        }
                    }
                })
        }
    }
    override fun onStart() { super.onStart() } override fun onResume() { super.onResume() }
    override fun onPause() { super.onPause() } override fun onStop() { super.onStop() }
    override fun onRestart() { super.onRestart() }
    override fun onDestroy() { mB.remove(this); dBH.close(); super.onDestroy() }
}
