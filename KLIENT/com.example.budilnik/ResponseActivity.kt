package com.example.budilnik
import com.example.budilnik.ui.resourses.bu
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Row
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.budilnik.ui.resourses.reload
import com.example.budilnik.ui.screens.S
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
class ResponseActivity : ComponentActivity()
{
    var gh = 0
    val ft = CoroutineScope(Dispatchers.IO)
    val ser = GetterField()
    var t = ArrayListReq<String>()
    var yut by mutableStateOf(false)
    var cm = ArrayListReq<MapF>()
    var af = ArrayListReq<String>()
    lateinit var timebud: TimedD
    lateinit var mk: FunctionMenu
    lateinit var pk: SingleMenu
    var pokemon = ""
    val cx = this
    fun amazon(fdi: String): String
    {
        val k = fdi.replace("\\s".toRegex(), "")
        return when (k)
        {
            "Сегодня" -> getDayOfWeek(getTodayDayOfWeek())
            "Завтра" -> getDayOfWeek(addTodayDayOfWeek(1))
            "Текущая неделя" -> getCurrentWeek(); "Следующая неделя" -> getNextWeek()
            else -> fdi
        }
    }
    fun pavlin(fdi: String): Int
    {
        val k = fdi.replace("\\s".toRegex(), "")
        return when (k) { "Сегодня" -> 0; "Завтра" -> 1; else -> 2 }
    }
    fun generateMenu(vt: String)
    {
        af.clear(); pk = SingleMenu(vt)
        for (d in t) { if (d != "") { af.add(d) } }
    }
    fun dlauncher(v: String) { ft.launch { t = ser.getServer(v); yut = true } }
    fun dich(hr: String): Int
    {
        if (hr == "Сгенерировать будильник по расписанию") { return 2; }
        return 1
    }
    @Composable fun ConfurmRememberButton()
    {
        Button(onClick = { pokemon = pk.getComponent(); dlauncher(pokemon) }, modifier = c)
        { Text("Работаем дальше") }
    }
    @Composable fun ConfurmValuesButton()
    { Button(onClick = { gh = pavlin(pk.getComponent())
        dlauncher(amazon(pk.getComponent())) }, modifier = c) { Text("Парсим расписание") } }
    @Composable fun ConfurmButton()
    { Button(onClick = { dlauncher(pk.getComponent()) }, modifier = c) { Text("Получить данные") } }
    @Composable fun ConfurmRaseButton()
    { Button(onClick = { dlauncher(mk.getIntResult().toString()) }, modifier = c) { Text("Получить данные") } }
    @Composable fun ReseiveButton(hr: String)
    { Button(onClick = { pu = 1; scj = dich(hr); podoroznik() }, modifier = c) { Text("Дальше") } }
    @Composable fun BackButton()
    { Button(onClick = {scj = 0; podoroznik() }, modifier = c) { Text("Назад") }}
    @Composable fun CancelButton()
    { Button(onClick = { dlauncher("ERROR"); pu = 0; finish(); reload() }, modifier = c)
    { Text("Возврат на главный экран") }}
    @Composable fun SamParsing()
    {
        LazyColumn (modifier = s, verticalArrangement = w)
        {
            item { Text("Сер, ваши материалы успешно добавлены") }
            item { CancelButton() }
        }
    }
    @Composable fun ParsingExcelButton()
    { Button(onClick = { setContent { SamParsing() } }, modifier = c) { Text("Парсим в Excel") }}
    @Composable fun ItogiParsinga()
    {
        LazyColumn (modifier = s, verticalArrangement = w)
        {
            if (gh == 1 || gh == 0)
            {
                ft.launch { t = ser.readerPage("http://$fl:8000/") }
                item { Text("Сер, мы получили следующие пары на " +
                        getDayOfWeekFull(addTodayDayOfWeek(gh)))}
                item { Row {
                    Text("Время  пары")
                    Text("Пара", mod.weight(0.5f).padding(8.dp))
                } }
                for (d in t)
                {
                    try
                    {
                        val jk = d.split(("%"))
                        item { Row {
                            for (k in 0..jk.size-1)
                            {
                                if (k == 0) { Text(jk[k])}
                                else { Text(jk[k], mod.weight(0.5f).padding(8.dp)) }
                            }
                        } }
                    }
                    catch(e: Exception)
                    {
                        item { Text(d) }
                        print(e)
                    }
                }
            }
            item { ParsingExcelButton() }
            item { CancelButton() }
        }
    }
    fun scxeme_show_many(hr: String)
    {
        jokerter.appendArrayList(t)
        if (t.isEmpty())
        {
            ft.launch { t = ser.readerPage("http://$fl:8000/") }
            setContent {
                LazyColumn (modifier = s, verticalArrangement = w)
                {
                    for (d in t) { item { Text(d) } }
                    item { CancelButton() }
                }
            }
        }
        else
        {
            if (pu == 5)
            { generateMenu("Какое расписание хотите?"); setContent { GetOptions(20, hr, true) } }
            else { setContent { ItogiParsinga() } }
        }
    }
    fun scxeme(hr: String)
    {
        gratewat.writeData(hr)
        if (hr == "Сгенерировать будильник по расписанию")
        {
            if (pu == 1)
            {
                try { t.clear(); } catch (e: Exception) { print(e) }
                t.add4("1", "2", "3", "4")
                jokerter.writeData("Выберите курс\n"); jokerter.appendArrayList(t)
                generateMenu("Выберите курс"); setContent { GetOptions(1, hr, true) }
            }
            else
            {
                jokerter.appendArrayList(t); val vt = t[0]; t.remove(t[0])
                if (t.isEmpty())
                {
                    ft.launch { t = ser.readerPage("http://$fl:8000/") }
                    setContent {
                        LazyColumn (modifier = s, verticalArrangement = w)
                        {
                            for (d in t) { item { Text(d) } }
                            item { CancelButton() }
                        }
                    }
                }
                else
                {
                    if (pu == 3)
                    {
                        generateMenu("Выберите группу обучения")
                        setContent { GetOptions(1, hr, true) }
                    }
                    else
                    {
                        if (pu >= 5 && pokemon == "Просмотр расписания") { scxeme_show_many(hr) }
                        else
                        {
                            if (pu == 5)
                            {
                                generateMenu("Выберите день недели")
                                setContent { GetOptions(1, hr, true) }
                            }
                            else
                            {
                                if (pu == 6)
                                {
                                    generateMenu("На какую пару вы хотите поставить напоминание?")
                                    setContent { GetOptions(1, hr, true) }
                                }
                                else
                                {
                                    if (pu < 6)
                                    {
                                        val joker = (if (pu == 4) {10} else {1})
                                        generateMenu(vt); setContent { GetOptions(joker, hr, true) }
                                    }
                                    else
                                    {
                                        ft.launch { t = ser.readerPage("http://$fl:8000/") }
                                        val kh = getTodayDayOfWeek()
                                        val gt = getDayOfWeekReverse(t[2])
                                        timebud = (if (kh == gt) { getTodayDate().addDay(7) }
                                        else
                                        {
                                            if (gt - kh < 0) { getTodayDate().addDay(7 + (gt - kh)) }
                                            else { getTodayDate().addDay(gt - kh) }
                                        })
                                        bu.add(Budilnik(t[0],
                                            TimedD(timebud.getYear(), timebud.getMonth(),
                                                timebud.getDay(),
                                                t[1].split(":")[0].replace("\\s".toRegex(), "")
                                                    .toInt(),
                                                t[1].split(":")[1].replace("\\s".toRegex(), "")
                                                    .toInt()), 7))
                                        val dbH = DatabaseR(cx, "Budilniki.db")
                                        val dbW = dbH.writableDatabase; bu.obnovlenie(dbW); dbW.close(); dbH.close()
                                        setContent {
                                            LazyColumn(modifier = s, verticalArrangement = w)
                                            {
                                                item { Text(vt) }
                                                for (d in t) { item { Text(d) } }
                                                item { CancelButton() }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun GetScreen()
    {
        val cf = LocalConfiguration.current
        val sW = cf.screenWidthDp.dp
        val sH = cf.screenHeightDp.dp
        LazyColumn (modifier = s, verticalArrangement = w)
        {
            item { S(45) }
            for (d in t) { item { Text(d, modifier = c, textAlign = k) } }
            item { Button(onClick = { finish() }, modifier = c) { Text("Выход с сервера") } }
            if (sH > sW) { item { S(65) } }
            else { item { S(45) } }
        }
    }
    @Composable
    fun ServerVindow(spp: Boolean, hr: String)
    {
        if (yut)
        {
            AlertDialog(onDismissRequest = { yut = false }, modifier = mod, icon = { I(dialogIcon, "") },
                title = { Text(load) }, text = { Text(cont) }, iconContentColor = teal700,
                confirmButton = { Button(onClick = {
                    if (spp) { yut = false; pu++; scxeme(hr)}
                    else { yut = false; setContent { GetScreen() } }
                },
                    colors = ColorB(Color.Green)) { Text("Да") }}, shape = AlertDialogDefaults.shape,
                dismissButton = { Button(onClick = { yut = false }, colors = ColorB(Color.Red)) { Text("Нет") }},
                containerColor = Color.White, titleContentColor = Color.Black, textContentColor = Color.Blue
            )
        }
    }
    @Composable
    fun GetOptions(qw: Int, hr: String, spp: Boolean)
    {
        ServerVindow(spp, hr)
        LazyColumn (modifier = s, verticalArrangement = w)
        {
            if (qw == -1) { item { mk.GetInstance(cm) } }
            else { item { pk.GetInstance(af) } }
            if (qw == 1)
            { item{ dozd.appendyText(pk.getComponent() + "\n"); ConfurmButton() } }
            else
            {
                if (qw == 0)
                { dozd.appendyText(pk.getComponent() + "\n"); item { ReseiveButton(hr) } }
                else
                {
                    if (qw == 10)
                    { item{ dozd.appendyText(pk.getComponent() + "\n"); ConfurmRememberButton() } }
                    else
                    {
                        if (qw == 20)
                        {item{dozd.appendyText(pk.getComponent()+ "\n"); ConfurmValuesButton()} }
                        else
                        { item{dozd.appendyText(mk.getIntResult().toString() + "\n"); ConfurmRaseButton()} }
                    }
                }
            }
            item { CancelButton() }
        }
    }
    fun initiliaze0()
    {
        t.add2("Авторизация/регистрация пользователя", "Сгенерировать будильник по расписанию")
        generateMenu("Выберите вариант событий")
        setContent { GetOptions(0, pk.getComponent(), true) }
    }
    @Composable
    fun LoginScreen()
    {
        LazyColumn (modifier = s, verticalArrangement = w)
        {
            item { Text("Данная функция находится в разработке") }
            item { BackButton() }
        }
    }
    fun podoroznik()
    {
        if (scj == 0) { initiliaze0() }
        else
        {
            if (scj == 1) { setContent { LoginScreen() } }
            else { scxeme("Сгенерировать будильник по расписанию") }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        podoroznik()
    }
    override fun onStart() { super.onStart() } override fun onResume() { super.onResume() }
    override fun onPause() { super.onPause() } override fun onStop() { super.onStop() }
    override fun onRestart() { super.onRestart() }
    override fun onDestroy() { ft.cancel(); cm.clear(); reload(); super.onDestroy() }
}
