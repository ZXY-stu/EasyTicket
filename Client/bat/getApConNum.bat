@echo off
set old_num=0
set num=0
set temp=0
:loop
for /f "tokens=3" %%i in ('netsh wlan show hostednetwork ^| findstr ¿Í»§¶Ë') do set num=%%i
if not %num%==100 if not %old_num%==0 (( echo %num%) > ..//deviceInfo//apNum.txt)
set old_num=%num%
goto loop
pause
