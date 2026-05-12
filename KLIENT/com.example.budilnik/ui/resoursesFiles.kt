package com.example.budilnik.ui.resourses
import android.os.Environment
import com.example.budilnik.FileR
import java.io.File
val folder: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
val comb = FileR(folder, "comb.txt")
val nDer = FileR(folder, "numberBud.txt")
