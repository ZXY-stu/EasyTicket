@echo off
set old_num=0
set num=0
set temp=0
set a=0
:loop

for  /f "tokens=3"  %%i in ('netsh wlan show hostednetwork ^| findstr ״̬') do ( set num=%%i
 set a=1
 )
  if %a%==1 (
  ( echo %num%) > ..//deviceInfo//status.txt
  set /a a=0
  )else ( echo null11) > ..//deviceInfo//status.txt
 
goto loop
pause
