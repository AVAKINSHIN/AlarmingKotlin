package com.example.budilnik
import com.example.budilnik.ui.resourses.f
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseR(context: Context, nameDB: String): SQLiteOpenHelper(context, nameDB, null, 1)
{
    override fun onCreate(db: SQLiteDatabase) { Zaproses.createbudilniks(db); Zaproses.createusers(db) }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        f.loadallbudilniks(db); Zaproses.droptable(db, "Budilniki"); Zaproses.createbudilniks(db)
        f.savealldata(db, "Budilniki", "Username, name, datetime, povtors")
        f.loadallusers(db); Zaproses.droptable(db, "Users"); Zaproses.createusers(db)
        f.savealldata(db, "Users", "name, password, status")
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { onUpgrade(db, oldVersion, newVersion) }
}
