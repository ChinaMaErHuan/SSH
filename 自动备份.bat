@echo off
set datevar=%date:~,4%%date:~5,2%%date:~8,2%
set ym=%date:~0,4%-%date:~5,2%
@echo 开始程序目录备份
XCOPY E:\ProgramFiles\tomcat\apache-tomcat-7.0.57\webapps\cms E:\程序备份\CMS内容管理项目备份\资源文件\back%datevar%  /y /e /i
@echo 程序目录备份完成
@echo 正在进行数据库备份
mysqldump  -u root -p 147852 tzcms>E:\程序备份\CMS内容管理项目备份\数据库备份\back%datevar%\tz_cms.sql
@echo 数据库备份完成 E:\程序备份\CMS内容管理项目备份\数据库备份\back%datevar%\tz_cms.sql
@echo on
Pause