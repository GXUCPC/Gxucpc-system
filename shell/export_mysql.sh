#!/bin/bash

# 例子
# bash export_mysql.sh -c gxucpc-mysql -p mysql114514 -d gxucpc -u root -o /srv/containers/gxucpc/export

# 解析命令行参数
while getopts ":c:p:d:u:o:" opt; do
  case $opt in
    c) MYSQL_CONTAINER_NAME=$OPTARG ;;
    p) MYSQL_PASSWORD=$OPTARG ;;
    d) MYSQL_DATABASE=$OPTARG ;;
    u) MYSQL_USERNAME=$OPTARG ;;
    o) EXPORT_PATH=$OPTARG ;;
    \?) echo "无效的选项: -$OPTARG" >&2; exit 1 ;;
    :) echo "选项 -$OPTARG 需要一个参数" >&2; exit 1 ;;
  esac
done

# 检查必要的参数是否提供
if [ -z "$MYSQL_CONTAINER_NAME" ]; then
  echo "缺少Mysql容器名称 (-c)"
  exit 1
fi

if [ -z "$EXPORT_PATH" ]; then
  echo "缺少导出路径参数 (-o)"
  exit 1
fi

if [ -z "$MYSQL_PASSWORD" ] || [ -z "$MYSQL_DATABASE" ] || [ -z "$MYSQL_USERNAME" ]; then
    echo "缺少MySQL连接参数 (-h, -p, -d, -u)"
    exit 1
fi

# 导出MySQL数据
if [ ! -z "$MYSQL_CONTAINER_NAME" ]; then
  EXPORT_FILE="$EXPORT_PATH/mysql_$(date +'%Y%m%d%H%M%S').sql"
  docker exec "$MYSQL_CONTAINER_NAME" mysqldump -u "$MYSQL_USERNAME" -p"$MYSQL_PASSWORD" "$MYSQL_DATABASE" > "$EXPORT_FILE"
  echo "MySQL数据已导出到 $EXPORT_FILE"
fi