#!/bin/bash

# 解析命令行参数
while getopts ":c:i:" opt; do
  case $opt in
    c) MYSQL_CONTAINER_NAME=$OPTARG ;;
    i) IMPORT_PATH=$OPTARG ;;
    \?) echo "无效的选项: -$OPTARG" >&2; exit 1 ;;
    :) echo "选项 -$OPTARG 需要一个参数" >&2; exit 1 ;;
  esac
done

# 检查必要的参数是否提供
if [ -z "$MYSQL_CONTAINER_NAME" ]; then
  echo "缺少MongoDB容器名参数 (-m) 或 MySQL容器名参数 (-c)"
  exit 1
fi

if [ -z "$IMPORT_PATH" ]; then
  echo "缺少导入路径参数 (-i)"
  exit 1
fi

# 导入MySQL数据
if [ ! -z "$MYSQL_CONTAINER_NAME" ]; then
  IMPORT_FILE=$(ls "$IMPORT_PATH"/*.sql | sort -r | head -n 1)
  mysql < "$IMPORT_FILE"
  echo "MySQL数据已从 $IMPORT_FILE 导入"
fi