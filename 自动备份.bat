@echo off
set datevar=%date:~,4%%date:~5,2%%date:~8,2%
set ym=%date:~0,4%-%date:~5,2%
@echo ��ʼ����Ŀ¼����
XCOPY E:\ProgramFiles\tomcat\apache-tomcat-7.0.57\webapps\cms E:\���򱸷�\CMS���ݹ�����Ŀ����\��Դ�ļ�\back%datevar%  /y /e /i
@echo ����Ŀ¼�������
@echo ���ڽ������ݿⱸ��
mysqldump  -u root -p 147852 tzcms>E:\���򱸷�\CMS���ݹ�����Ŀ����\���ݿⱸ��\back%datevar%\tz_cms.sql
@echo ���ݿⱸ����� E:\���򱸷�\CMS���ݹ�����Ŀ����\���ݿⱸ��\back%datevar%\tz_cms.sql
@echo on
Pause