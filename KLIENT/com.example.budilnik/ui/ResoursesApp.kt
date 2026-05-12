package com.example.budilnik.ui.resourses
import android.annotation.SuppressLint
import com.example.budilnik.MainActivity
import androidx.activity.ComponentActivity
import com.example.budilnik.ArrayListReq
import com.example.budilnik.ArrayListString
import com.example.budilnik.ArrayListBudilnik
import com.example.budilnik.ui.resourses.menu.TextMenuItem
import androidx.activity.compose.setContent
var bu = ArrayListBudilnik()
var pm = ArrayListReq<TextMenuItem>()
var bm = ArrayListReq<TextMenuItem>()
var f = ArrayListString()
@SuppressLint("StaticFieldLeak")
lateinit var mA: MainActivity
var mB = ArrayListReq<ComponentActivity>()
fun reload() {mA.setContent {}; mA.setContent { mA.HomeScreenSk() }}
fun goHome() { for (d in mB) { d.finish() }; comb.writeData(""); nDer.writeData(""); reload() }
fun exitApp() { goHome(); mA.finish() }
