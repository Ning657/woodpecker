#删除自动化测试数据库
drop database if exists autotest;

#创建自动化测试数据库
create database if not exists autotest default character set utf8 collate utf8_general_ci;