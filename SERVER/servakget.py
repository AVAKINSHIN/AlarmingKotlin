from fastapi import FastAPI
from fastapi.responses import HTMLResponse
import uvicorn
app = FastAPI()
f = open('try.txt', 'a')
f.close()


@app.get("/", response_class=HTMLResponse)
def read_root():
    fi = open('try.txt', 'r')
    fg = fi.read()
    fi.close()
    return fg


uvicorn.run(app, host="172.18.204.119", port=8000)
