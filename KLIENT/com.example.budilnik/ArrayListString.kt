package com.example.budilnik
import android.database.sqlite.SQLiteDatabase
import com.example.budilnik.Zaproses.insert
class ArrayListString: ArrayListReq<String>()
{
    fun loadallbudilniks(db: SQLiteDatabase)
    {
        clear()
        val crs =
        Zaproses.select(db, arrayOf("Username", "name", "datetime", "povtors"), "Budilniki", null, null)
        with(crs)
        {
            while (moveToNext())
            {
                add("${getString(getColumnIndexOrThrow("Username"))}, " +
                    "${getString(getColumnIndexOrThrow("name"))}, " +
                    "${getString(getColumnIndexOrThrow("datetime"))}, " +
                    "${getInt(getColumnIndexOrThrow("povtors"))}")
            }
        }
        crs.close()
    }
    fun savealldata(db: SQLiteDatabase, n: String, p: String) { for (d in this) { insert(db, n, p, d) }; clear() }
    fun loadallusers(db: SQLiteDatabase)
    {
        clear()
        val crs =
            Zaproses.select(db, arrayOf("name", "password", "status"), "Users", null, null)
        with(crs)
        {
            while (moveToNext())
            { add("${getString(getColumnIndexOrThrow("name"))}, " +
                    "${getString(getColumnIndexOrThrow("password"))}, " +
                    "${getString(getColumnIndexOrThrow("status"))}")
            }
        }
        crs.close()
    }
}
