package com.example.budilnik
import com.example.budilnik.ui.resourses.*
import com.example.budilnik.ui.resourses.menu.*
import com.example.budilnik.ui.screens.budilnikManager
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.MenuProvider
import com.example.budilnik.ui.screens.S
import com.example.budilnik.ui.screens.Sp
import com.example.budilnik.ui.screens.mn
import com.example.budilnik.ui.screens.mnH
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
class EditActivity: ComponentActivity()
{
    val gl = CoroutineScope(Dispatchers.Default)
    var ti = true; lateinit var dbH: DatabaseR; lateinit var dbW: SQLiteDatabase
    var dTR = (if (nD == -1) { getTodayBudilnik() } else { pereopr() })
    var v by mutableStateOf(dTR.getName())
    var p by mutableIntStateOf(dTR.p)
    var pu by mutableStateOf(p.toString())
    val rd = DateMenu("День", dTR, 90); val rm = DateMenu("Месяц", dTR, 85)
    val rg = DateMenu("Год", dTR, 105); val rh = DateMenu("Час", dTR, 85)
    val rmi = DateMenu("Минута", dTR, 85); var se = ""; var enotik by  mutableStateOf(false)
    fun rmo(): TimedD
    { return mn(rg.getDark(), rm.getDark(), rd.getDark(), rh.getDark(), rmi.getDark()) }
    fun callzlocoroutine()
    { gl.launch { while (ti) { dTR = Budilnik(v, rmo(), 0); delay(500) }} }
    fun pereopr(): Budilnik { return Budilnik(bu[nD].v, bu[nD].dt, bu[nD].p) }
    fun homeR() { comb.writeData(""); nDer.writeData(""); nD = -1; reload(); finish() }
    fun save()
    {
        f.clear(); val ds = rd.getDark(); val ms = rm.getDark(); val gs = rg.getDark()
        val hs = rh.getDark(); val mis = rmi.getDark(); reload()
        val u = ExeptionsCin(gs, ms, ds, hs, mis); f = u.exeptionsJob()
        try { dTR.p = pu.toInt() }
        catch (e: Exception) { print(e); f.add("В поле повторы должно быть введено число.") }
        for (d in 0..bu.size-1)
        {
            if (d != nD)
            {
                if (bu[d].v == dTR.v)
                {
                    f.add("Простите, но напоминание с таким названием уже существует.")
                    f.add("Пожалуйста, измените название вашего напоминания.")
                }
            }
        }
        if (f.isEmpty()) { if (y) { bu[nD] = dTR } else { bu.add(dTR) }; bu.obnovlenie(dbW); homeR() }
        else { se = ""; for (d in f) { se = se + d + "\n" }; enotik = true }
    }
    fun unsave() { comb.writeData(""); nD = -1; nDer.writeData(""); homeR() }
    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    fun EditScreen()
    {
        pm.clear2(bm)
        pm.add(TextMenuItem("Сегодня", getTodayDate(), rg, rm, rd))
        pm.add(TextMenuItem("Завтра", getTodayDate().addDay(1), rg, rm, rd))
        pm.add(TextMenuItem("Послезавтра", getTodayDate().addDay(2), rg, rm, rd))
        pm.add(TextMenuItem("Через неделю", getTodayDate().addWeek(1), rg, rm, rd))
        pm.add(TextMenuItem("Через месяц", getTodayDate().addMonth(1), rg, rm, rd))
        pm.add(TextMenuItem("Через год", getTodayDate().addYear(1), rg, rm, rd))
        bm.add(TextMenuItem("С петухами (5:00)", mnH("5", "0"), rh, rmi))
        bm.add(TextMenuItem("В центр по пробкам (6:00)", mnH("6", "0"), rh, rmi))
        bm.add(TextMenuItem("На работу (7:00)", mnH("7", "0"), rh, rmi))
        bm.add(TextMenuItem("Четверть после полудня (12:15)", mnH("12", "15"), rh, rmi))
        bm.add(TextMenuItem("Просмотр вечерних фильмов (18:30)", mnH("18", "30"), rh, rmi))
        bm.add(TextMenuItem("Ночная смена (22:00)", mnH("22", "0"), rh, rmi))
        val med = TextMenu("Введите или выберите дату", pm, ch = true, ro = true)
        val ted = TextMenu("Введите или выберите время", bm, ch = false, ro = true)
        val cf = LocalConfiguration.current; val sW = cf.screenWidthDp.dp; val sH = cf.screenHeightDp.dp
        if (enotik)
        {
            AlertDialog(
                onDismissRequest = { enotik = false }, modifier = mod, icon = { I(dialogIcon, "") },
                title = { Text(greter) }, text = { Text(se) }, iconContentColor = teal700,
                confirmButton = { Button(onClick = { enotik = false }, colors = ColorB(Color.Green))
                { Text("ОК") }}, shape = AlertDialogDefaults.shape,
                containerColor = Color.White, titleContentColor = Color.Black, textContentColor = Color.Blue,
            )
        }
        LazyColumn(modifier = s, verticalArrangement = w) {
            item { S(45) }
            item {
                OutlinedTextField(
                    value = v, onValueChange = { v = it; dTR.v = v },
                    label = { Text("Название задачи") }, modifier = c
                )
            }
            item { Sp() }
            item {
                LazyRow (modifier = c, horizontalArrangement = w) { item { med.GetInstance() } }
            }
            item { Sp() }
            item {
                LazyRow(modifier = c, horizontalArrangement = w) {
                    item { rd.GetInstanceDay(true, rm, rg, 1, 31) }
                    item { Sp() }
                    item { rm.GetInstance(1, 12) }
                    item { Sp() }
                    item { rg.GetInstance(getTodayDate().getYear(), getTodayDate().getYear() + 5) }
                }
            }
            item { Sp() }
            item {
                LazyRow (modifier = c, horizontalArrangement = w) {
                    item { ted.GetInstance() }
                }
            }
            item { Sp() }
            item {
                LazyRow(modifier = c, horizontalArrangement = w) {
                    item { rh.GetInstance(0, 23) }
                    item { Sp() }
                    item { rmi.GetInstance(0, 59) }
                }
            }
            item { Sp() }
            item {
                OutlinedTextField(
                    value = pu, onValueChange = { pu = it },
                    label = { Text("Повторение задачи (по дням)") }, modifier = c
                )
            }
            item { Sp() }
            item {
                Button(onClick= { save() }, modifier = c, colors = ColorB(Color.Green))
                { Text("Сохранить задачу") }
            }
            item { Sp() }
            item {
                Button(onClick={ unsave() }, modifier = c, colors = ColorB(Color.Red))
                { Text("Вернуться без сохранения")}
            }
            if (sH > sW) { item { S(65) } }
            else { item { S(45)} }
        }
        callzlocoroutine()
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnsafeIntentLaunch")
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState); sp = true; createFiles()
        if (nDer.isEmpty()) { nDer.writeData(nD.toString()) }
        else { try { nD = nDer.readyText().toInt() } catch (e: Exception) { println(e) } }
        if (comb.isEmpty())
        {
            dTR = (if (nD == -1) { getTodayBudilnik() } else { pereopr() })
            comb.writeBudilnik(dTR)
        }
        else { dTR = budilnikManager(comb.readyText()) }
        rg.setComponent(dTR.getYear().toString()); rm.setComponent(dTR.getMonth().toString())
        rd.setComponent(dTR.getDay().toString()); rh.setComponent(dTR.getHour().toString())
        rmi.setComponent(dTR.getMinute().toString()); v = dTR.getName()
        mB.add(this); mA.setContent {  }
        setContent {
            val cx = LocalContext.current
            dbH = DatabaseR(cx, "Budilniki.db"); dbW = dbH.writableDatabase
            var e by remember { mutableStateOf(false) }
            Scaffold(topBar = {
                TopAppBar(
                    title = {
                        Row(modifier = s, horizontalArrangement = w)
                        {
                            if (y) { Text("Редактирование", modifier = mod.weight(0.5f).padding(8.dp)) }
                            else { Text("Добавление", modifier = mod.weight(0.5f).padding(8.dp)) }
                            IconButton(onClick = {
                                dTR.dt = dTR.dt.addToDate(getZeroTimedD())
                            }) { I(calen, "Исправить дату") }
                            IconButton (onClick = {e = !e}) { I(moreVert, "Меню") }
                            DropdownMenu(expanded = e, onDismissRequest = { e = false })
                            {
                                DropdownMenuItem(text = { Text("Сохранить") }, onClick = { save() })
                                DropdownMenuItem(text = { Text("Настройки") }, onClick = {
                                    intent = Intent(cx, SettingsActivity::class.java)
                                        cx.startActivity(intent)
                                })
                                DropdownMenuItem(
                                    text = { Text("Вернуться без сохранения") },
                                    onClick = { unsave() }
                                )
                            }
                        }
                    }, colors = ColorT(teal700),
                    navigationIcon = { IconButton(onClick = { unsave() }) { IU("2") } })
            },
                content = { EditScreen() }, bottomBar = {
                    BottomAppBar(containerColor = teal700) {
                        Row(modifier = s, horizontalArrangement = w) {
                            IconButton(onClick = { goHome() }) { I(home, "Домой") }
                            IconButton(onClick = {
                                intent = Intent(cx, SettingsActivity::class.java)
                                cx.startActivity(intent)
                            }) { I(settings, "Настройки") }
                            IconButton(onClick = { deleteEnots() }) { I(enoti, "Файловый дроп") }
                            IconButton(onClick = { exitApp() }) { I(exit, "Выход") }
                        }
                    }
                })
        }
    }
    override fun onStart() { super.onStart() }
    override fun onResume() { super.onResume() }
    override fun onPause() { super.onPause() }
    override fun onStop() { ti = false; gl.cancel(); mA.setContent { mA.HomeScreenSk() }; super.onStop() }
    override fun onRestart() { ti = true; callzlocoroutine(); super.onRestart(); mA.setContent {} }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
    override fun onDestroy()
    {
        mA.setContent { mA.HomeScreenSk() }; ti = false; gl.cancel()
        mB.remove(this); nD = -1; sp = false; super.onDestroy()
    }
}
