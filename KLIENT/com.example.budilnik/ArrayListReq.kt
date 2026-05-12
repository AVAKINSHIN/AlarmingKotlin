package com.example.budilnik
open class ArrayListReq<E> : ArrayList<E>()
{
    fun add2(e1: E, e2: E) { add(e1); add(e2) }
    fun add4(e1: E, e2: E, e3: E, e4: E) { add2(e1, e2); add2(e3, e4) }
    fun clear2(bm: ArrayListReq<E>) { clear(); bm.clear() }
}
