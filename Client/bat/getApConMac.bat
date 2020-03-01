@echo off
setlocal enabledelayedexpansion
:loop
netsh wlan show  hostednetwork | findstr Authenticated > ..//deviceInfo//Mac.txt
 echo start>> ..//deviceInfo//apMac.txt
for  /f  %%i in (..//deviceInfo//Mac.txt) do ( 
(echo %%i)>>..//deviceInfo//apMac.txt 
)
echo end>> ..//deviceInfo//apMac.txt
goto loop
pause




