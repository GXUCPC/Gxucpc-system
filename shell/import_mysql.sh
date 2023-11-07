#!/bin/bash

# 解析命令行参数
while getopts ":c:i:u:p:" opt; do
  case $opt in
    c) MYSQL_CONTAINER_NAME=$OPTARG ;;
    i) IMPORT_FILE=$OPTARG ;;
    u) MYSQL_USERNAME=$OPTARG ;;
    p) MYSQL_PASSWORD=$OPTARG ;;
    \?) echo "无效的选项: -$OPTARG" >&2; exit 1 ;;
    :) echo "选项 -$OPTARG 需要一个参数" >&2; exit 1 ;;
  esac
done

# 检查必要的参数是否提供
if [ -z "$MYSQL_CONTAINER_NAME" ]; then
  echo "缺少MySQL容器名参数 (-c)"
  exit 1
fi

if [ -z "$MYSQL_USERNAME" ] || [ -z "$MYSQL_PASSWORD" ]; then
  echo "缺少MySQL容器连接参数 (-u | -p)"
  exit 1
fi


if [ -z "$IMPORT_FILE" ]; then
  echo "缺少导入路径参数 (-i)"
  exit 1
fi

# 导入MySQL数据
if [ ! -z "$MYSQL_CONTAINER_NAME" ]; then
  docker cp "$IMPORT_FILE" "$MYSQL_CONTAINER_NAME:backup.sql"
  docker exec "$MYSQL_CONTAINER_NAME" mysql -u"$MYSQL_USERNAME" -p"$MYSQL_PASSWORD" -e "create database if not exists gxucpc; use gxucpc; source /backup.sql;"
  echo "MySQL数据已从 $IMPORT_FILE 导入"
fi