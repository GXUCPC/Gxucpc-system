#!/bin/bash

# 例子
# bash export_mongodb.sh -m gxucpc-mongodb -o /srv/containers/gxucpc/export

# 解析命令行参数
while getopts ":m:o:" opt; do
  case $opt in
    m) MONGO_CONTAINER_NAME=$OPTARG ;;
    o) EXPORT_PATH=$OPTARG ;;
    \?) echo "无效的选项: -$OPTARG" >&2; exit 1 ;;
    :) echo "选项 -$OPTARG 需要一个参数" >&2; exit 1 ;;
  esac
done

# 检查必要的参数是否提供
if [ -z "$MONGO_CONTAINER_NAME" ]; then
  echo "缺少MonGooDB容器名称参数 (-m)"
  exit 1
fi

if [ -z "$EXPORT_PATH" ]; then
  echo "缺少导出路径参数 (-o)"
  exit 1
fi

# 导出MongoDB数据
if [ ! -z "$MONGO_CONTAINER_NAME" ]; then
  EXPORT_FILE="$EXPORT_PATH/mongodb_$(date +'%Y%m%d%H%M%S').tar.gz"
  docker exec "$MONGO_CONTAINER_NAME" mongodump --archive="$EXPORT_FILE" --gzip
  echo "MongoDB数据已导出到 $EXPORT_FILE"
fi