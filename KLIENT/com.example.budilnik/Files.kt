package com.example.budilnik
import android.os.Environment
import java.io.File
val folder: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
val comb = FileR(folder, "comb.txt"); val data = FileR(folder, "data.txt")
val nDer = FileR(folder, "numberBud.txt")
val enot = FileR(folder, "enot.txt")
val jokerter = FileR(folder, "jokerter.txt"); val gratewat = FileR(folder, "gratewat.txt")
val dozd = FileR(folder, "dozd.txt")
fun createFiles()
{
    if (!comb.exists()) { comb.createNewFile() }; if (!data.exists()) { data.createNewFile() }
    if (!nDer.exists()) { nDer.createNewFile() }
}
fun createEnots()
{
    if (!enot.exists()) { enot.createNewFile() }; if (!jokerter.exists()) { jokerter.createNewFile() }
    if (!gratewat.exists()) { gratewat.createNewFile() }; if (!dozd.exists()) { dozd.createNewFile() }
}
fun deleteEnots()
{
    if (enot.exists()) { enot.writeData(""); enot.delete() }
    if (jokerter.exists()) { jokerter.writeData(""); jokerter.delete() }
    if (gratewat.exists()) { gratewat.writeData(""); gratewat.delete() }
    if (dozd.exists()) { dozd.writeData(""); dozd.delete() }
}
