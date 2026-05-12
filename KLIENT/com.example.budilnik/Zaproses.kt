package com.example.budilnik
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
object Zaproses
{
    fun create(db: SQLiteDatabase, name: String, c: String)
    { db.execSQL("CREATE TABLE IF NOT EXISTS $name (ID INTEGER PRIMARY KEY AUTOINCREMENT, ${c})") }
    fun createbudilniks(db: SQLiteDatabase)
    { create(db, "Budilniki", "Username TEXT, name TEXT, datetime TEXT, povtors INTEGER") }
    fun createusers(db: SQLiteDatabase) { create(db, "Users", "name TEXT, password TEXT, status TEXT") }
    fun insert(db: SQLiteDatabase, n: String, p: String, s: String) { db.execSQL("INSERT INTO $n (${p}) VALUES (${s})") }
    fun update(db: SQLiteDatabase, n: String, p: String, s: String) { db.execSQL("UPDATE $n SET $p WHERE $s ") }
    fun delete(db: SQLiteDatabase, n: String, p: String) { db.execSQL("DELETE FROM $n WHERE $p ") }
    fun select(db: SQLiteDatabase, g: Array<String>?, n: String, sl: String?, p: Array<String>?): Cursor
    { return db.query(n, g, sl, p, null, null, null) }
    fun droptable(db: SQLiteDatabase, name: String) { db.execSQL("DROP TABLE IF EXISTS $name ") }
}
