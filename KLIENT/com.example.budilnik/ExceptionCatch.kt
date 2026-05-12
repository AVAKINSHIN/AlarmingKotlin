package com.example.budilnik
class ExceptionCatch
{
    var w = 0
    var h: String
    constructor(q: String, s: String, g: String) { try { w = q.toInt(); h = s } catch (e: Exception) { println(e); h = g} }
    fun intVal(): Int { return w }
    fun expComm(): String { return h }
}
