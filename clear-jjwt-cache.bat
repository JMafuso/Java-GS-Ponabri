@echo off
echo Clearing local Maven cache for jjwt...
rd /s /q "%USERPROFILE%\.m2\repository\io\jsonwebtoken"
echo Cache cleared.
