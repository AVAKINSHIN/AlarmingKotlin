package com.example.budilnik
import android.database.sqlite.SQLiteDatabase
import com.example.budilnik.Zaproses.delete
import com.example.budilnik.Zaproses.insert
import com.example.budilnik.ui.resourses.bu

class ArrayListBudilnik: ArrayListReq<Budilnik>()
{
    fun loadbudilniks(db: SQLiteDatabase)
    { val crs = Zaproses.
    select(db, arrayOf("name", "datetime", "povtors"), "Budilniki", "Username = ?", arrayOf(cu))
        with(crs) {
            while (moveToNext()) {
                add(Budilnik(getString(getColumnIndexOrThrow("name")),
                    textToTimedD(getString(getColumnIndexOrThrow("datetime"))),
                    getInt(getColumnIndexOrThrow("povtors"))))
            }
        }
        crs.close()
    }
    fun savebudilniks(db: SQLiteDatabase)
    {
        for (d in this)
        {
            insert(db, "Budilniki", "Username, name, datetime, povtors",
                "\'$cu\', \'${d.v}\', \'${d.dt.toStrToDatabase()}\', ${d.p}")
        }
    }
    fun obnovlenie(db: SQLiteDatabase) { delete(db, "Budilniki", "Username = \'${cu}\'"); bu.savebudilniks(db) }
    fun readersaveappend(f: FileR, db: SQLiteDatabase)
    {
        val g = f.reader()
        for (d in g)
        {
            insert(db, "Budilniki", "Username, name, datetime, povtors",
            "\'$cu\', \'${d.v}\', \'${d.dt.toStrToDatabase()}\', ${d.p}")
            add(d)
        }
    }
}
