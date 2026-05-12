package com.example.budilnik
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.Socket
import okhttp3.OkHttpClient
import okhttp3.Request

class GetterField
{
    lateinit var clientSocket: Socket
    lateinit var `in`: BufferedReader
    lateinit var out: BufferedWriter
    suspend fun getServer(v: String): ArrayListReq<String>
    {
        var t = ArrayListReq<String>()
        try
        {
            withContext(Dispatchers.IO) { clientSocket = Socket(fl, 4004)
                out = BufferedWriter(OutputStreamWriter(clientSocket.getOutputStream()))
                out.write(v); out.flush(); clientSocket.close(); out.close()
            }
            delay(2000)
            t = readerPage("http://$fl:8000/")
        }
        catch (e: Exception) { t.add(e.toString()) }
        return t
    }
    fun readerPage(url: String): ArrayListReq<String>
    {
        val t = ArrayListReq<String>()
        val cl = OkHttpClient()
        val rq = Request.Builder().url(url).build()
        try {
            cl.newCall(rq).execute().use { response ->
                if (!response.isSuccessful)
                {
                    throw Exception("Запрос к серверу не был успешен:" +
                            " ${response.code} ${response.message}")
                }
                val fd = response.body!!.string().split("\n")
                for (d in fd) { t.add(d) }
            }
        } catch (e: Exception) { t.add("Ошибка подключения: $e"); }
        return t
    }
}
