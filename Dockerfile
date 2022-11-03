# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 将当前目录下的jar包复制到docker容器的/目录下
RUN mkdir /app && \
    cd /app && \
    sed -i s/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g /etc/apk/repositories && \
    apk add --update --no-cache curl jq py3-configobj py3-pip py3-setuptools python3 python3-dev && \
    apk add --no-cache gcc g++ libffi-dev make zlib-dev libcec-dev libtool && \
    wget https://bootstrap.pypa.io/pip/3.6/get-pip.py && \
    python3 get-pip.py

ADD src/main/resources/python/requirements.txt /app/requirements.txt
ADD target/Gxucpc-system-0.0.1-SNAPSHOT.jar /app/Gxucpc-system-0.0.1-SNAPSHOT.jar
ADD src/main/resources/python/scoreboard.py /app/scoreboard.py

RUN cd /app &&\
    pip install -r requirements.txt
# 声明服务运行在8080
EXPOSE 8080
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/app/Gxucpc-system-0.0.1-SNAPSHOT.jar"]
# 指定维护者的名字
MAINTAINER Gxuca

