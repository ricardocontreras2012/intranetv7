@echo off
setlocal enabledelayedexpansion

for %%f in (*Persistence.java) do (
    set "old=%%~nxf"
    set "new=!old:Persistence=Repository!"
    echo Renombrando "%%f" → "!new!"
    ren "%%f" "!new!"
)
