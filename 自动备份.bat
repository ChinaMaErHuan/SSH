@echo off
set "Ymd=%date:~,4%%date:~5,2%%date:~8,2%"
@echo 开始程序目录备份
XCOPY E:\ProgramFiles\tomcat\apache-tomcat-7.0.57\webapps\ROOT E:\程序备份\CMS内容管理项目备份\back%Ymd%  /y /e /i
@echo 程序目录备份完成
@echo 正在进行数据库备份
mysqldump --opt -u root --password=147852 tzcms >E:\程序备份\CMS内容管理项目备份\back%Ymd%\moon.sql
@echo 数据库备份完成E:\moon_%Ymd%.sql
@echo on