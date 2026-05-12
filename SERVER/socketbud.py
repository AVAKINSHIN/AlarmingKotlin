import socket as sc
import sqlite3 as sql


def update_part(dfs):
    try:
        return dfs + ' = ?'
    except Exception:
        zp = ''
        for d in range(0, len(dfs) - 1):
            zp = zp + dfs[d] + ' = ? AND '
        zp = zp + dfs[len(dfs) - 1] + ' = ?'
        return zp


class DatabaseSQL:
    def __init__(self, name):
        self.conn = sql.connect(name)
        self.cursor = self.conn.cursor()

    def select_where(self, stolbiki, table, yslovies, values):
        if yslovies == "":
            self.cursor.execute('SELECT ' + stolbiki + ' FROM ' + table)
        else:
            self.cursor.execute('SELECT ' + stolbiki + ' FROM ' + table + ' WHERE ' + update_part(yslovies), values)
        return self.cursor.fetchall()

    def select_single(self, stolbiki, table, yslovies, values):
        df = []
        kl = self.select_where(stolbiki, table, yslovies, values)
        for d in kl:
            if not d[0] in df:
                df.append(d[0])
        return df

    @staticmethod
    def write_answer_df(df):
        writeanswer("")
        f = open("try.txt", "a")
        for d in df:
            f.write(d + "\n")
        f.close()
        return df

    def scenarii_select(self, stolbiki, table, yslovies, values, title):
        df = [title]
        df.extend(self.select_single(stolbiki, table, yslovies, values))
        return self.write_answer_df(df)


def writeanswer(txt):
    f = open("try.txt", "w")
    f.write(txt)
    f.close()


def category_runtime_work(s, gr, db):
    df = db.write_answer_df(["Какое расписание хотите?", "Сегодня", "Завтра", "Текущая неделя",
                             "Следующая неделя"])
    cq, aq = s.accept()
    amount = cq.recv(1024).decode('utf-8')
    if amount == 'ERROR':
        return
    print(amount)
    if len(amount) == 2:
        df = db.select_where("id_pari, para", "Raspisanie", ["day", "gr"], [amount, gr])
        rw = ""
        for d in df:
            for t in d:
                if t == d[0]:
                    o = db.select_single("time", "PairsTime", "id", [t])[0]
                    rw = rw + o.split("-")[0].strip(" ") + "%"
                else:
                    rw = rw + t + "\n"
        writeanswer(rw)
    else:
        df = db.select_single("cz", "ChislitelZnamenatelWeeks", "week", [amount])
        print(df)



def category_select_one_bud(s, gr, db):
    df = db.scenarii_select("dayseek", "DayOfWeek", "", [], "Выберите день недели")
    cq, aq = s.accept()
    amount = cq.recv(1024).decode('utf-8')
    if amount == 'ERROR':
        return
    print(amount)
    while amount not in df:
        amount = cq.recv(1024).decode('utf-8')
        if amount == 'ERROR':
            return
        print(amount)
    day = amount
    df = db.scenarii_select("para", "Raspisanie", ["day", "gr"], [day, gr],
                            "На какую пару вы хотите поставить напоминание?")
    cq, aq = s.accept()
    amount = cq.recv(1024).decode('utf-8')
    if amount == 'ERROR':
        return
    print(amount)
    while amount not in df:
        amount = cq.recv(1024).decode('utf-8')
        if amount == 'ERROR' or amount == '':
            return
        print(amount)
    para = amount
    df = db.select_single("id_pari", "Raspisanie", ["day", "gr", "para"], [day, gr, para])
    df = db.select_single("time", "PairsTime", "id", [df[0]])
    time = df[0]
    df = db.select_single("day", "DayOfWeek", "dayseek", [day])
    if df[0] != "":
        day = df[0]
        writeanswer("Вам было добавлено напоминание\n" + para + " " + "(" + gr + ")\n" + time.split('-')[0].strip(' ') +
                    "\n" + day + " ")
    else:
        writeanswer("Волколаки")


def handle_connection(s, c):
    db = DatabaseSQL('Raspisanie.db')
    amount = c.recv(1024).decode('utf-8')
    print(amount)
    if amount == "1" or amount == "2" or amount == "3" or amount == "4":
        kourse = amount
        df = db.scenarii_select("np", "GroupsOnN", "crs", [amount], "Выберите направление обучения")
        cq, aq = s.accept()
        amount = cq.recv(1024).decode('utf-8')
        if amount == 'ERROR':
            return
        print(amount)
        while amount not in df:
            amount = cq.recv(1024).decode('utf-8')
            if amount == 'ERROR':
                return
            print(amount)
        np = amount
        df = db.scenarii_select("gr", "GroupsOnN", ["crs", "np"], [kourse, np], "Выберите группу обучения")
        cq, aq = s.accept()
        amount = cq.recv(1024).decode('utf-8')
        if amount == 'ERROR':
            return
        print(amount)
        while amount not in df:
            amount = cq.recv(1024).decode('utf-8')
            if amount == 'ERROR':
                return
            print(amount)
        gr = amount
        df = db.write_answer_df(["Что делаем с расписанием, сер?", "Добавление напоминания одной пары",
                                 "Просмотр расписания"])
        cq, aq = s.accept()
        amount = cq.recv(1024).decode('utf-8')
        if amount == 'ERROR':
            return
        print(amount)
        while amount not in df:
            amount = cq.recv(1024).decode('utf-8')
            if amount == 'ERROR':
                return
            print(amount)
        if amount == "Добавление напоминания одной пары":
            category_select_one_bud(s, gr, db)
        else:
            category_runtime_work(s, gr, db)


class FileReceiver:
    def __init__(self):
        self.s = sc.socket(sc.AF_INET, sc.SOCK_STREAM)

    def run(self):
        try:
            self.s.bind(('172.18.204.119', 4004))
            self.s.listen()
            while True:
                c, a = self.s.accept()
                handle_connection(self.s, c)
        except KeyboardInterrupt:
            self.s.detach()
            self.s.close()


fr = FileReceiver()
fr.run()
