package com.example.budilnik
const val data_exp = "Данная дата уже прошла."
const val god_num = "В поле Год должно быть введено число не меньше текущего года  и не больше 2147483647"
const val deltaprint = "Ваш будильник не был добавлен по следующим причинам: "
const val edit = "Редактирование будильника"
const val deleter = "Вы действительно хотите удалить будильник?"
const val saver = "Хотите сохранить будильник?"
const val greter = "Подтверждение действия"
const val exiter = "Кажется, вы кое-что забыли"
const val intened = "Ваш будильник не был добавлен по следующим причинам: "
const val load = "Сервер загрузился"
const val cont = "Хотите продолжить?"
var hp = 0
const val o: Short = 0
fun framer(a: String, b: String, c: String): String { return "В поле $a должно быть введено число от $b до $c" }
fun framer2(a: String, b: String, c: String): String { return "Поле $a должно содержать число от $b до $c" }
fun daymonth(a: String, b: String, c: String): String
{ return "Минимальное число для дня месяца $a - $b, максимальное - $c." }
