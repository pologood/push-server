#!/bin/sh
HOME=/data/app/push-server

LIB=$HOME/lib
CLASSPATH=$HOME/conf:$LIB/*
echo $CLASSPATH

LANG=zh_CN.UTF-8
LC_ALL=zh_CN.UTF-8
export LANG LC_ALL

MAINCLASS=com.easou.novelpush.Bootstrap
JVMOPT='-Xms512m -Xmx512m'
echo  the program is starting...
java $JVMOPT -cp $CLASSPATH $MAINCLASS >> $HOME/log/stdout.log 2>&1 &
