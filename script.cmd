@echo off
set "file=nconfig.txt"
echo status > %file%
timeout /t 2 /nobreak >nul
echo status >> %file%
timeout /t 2 /nobreak >nul
java NameServerDriver < %file%
del %file%