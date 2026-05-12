package com.example.budilnik.ui.resourses.menu
import com.example.budilnik.TimedD
import com.example.budilnik.getZeroBudilnik
class TextMenuItem
{
    var vA: String
    var dTP: TimedD
    var r1: DateMenu
    var r2: DateMenu
    var r3: DateMenu
    constructor(v: String, dT: TimedD, rg: DateMenu, rm: DateMenu, rd: DateMenu)
    { vA = v; dTP = dT; r1 = rg; r2 = rm; r3 = rd }
    constructor(v: String, dT: TimedD, rh: DateMenu, rmi: DateMenu)
    { vA = v; dTP = dT; r1 = rh; r2 = rmi; r3 = DateMenu("", getZeroBudilnik(), 0) }
    fun getTitle(): String { return vA }
    fun doWork1() { r1.setComponent(dTP.g.toString()); r2.setComponent(dTP.m.toString()); r3.setComponent(dTP.d.toString()) }
    fun doWork2() { r1.setComponent(dTP.h.toString()); r2.setComponent(dTP.mi.toString()) }
}
