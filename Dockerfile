# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 将当前目录下的jar包复制到docker容器的/目录下
RUN mkdir /app

ADD target/Gxucpc-system-0.0.1-SNAPSHOT.jar /app/Gxucpc-system-0.0.1-SNAPSHOT.jar

# 声明服务运行在8080
EXPOSE 8080
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/app/Gxucpc-system-0.0.1-SNAPSHOT.jar"]
# 指定维护者的名字
MAINTAINER Gxuca

