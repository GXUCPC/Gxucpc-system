# 该镜像需要依赖的基础镜像
FROM openjdk:8-jdk-alpine
# 将当前目录下的jar包复制到docker容器的/目录下
ADD target/Gxucpc-system-0.0.1-SNAPSHOT.jar /Gxucpc-system-0.0.1-SNAPSHOT.jar
# 声明服务运行在80端口
EXPOSE 80
# 指定docker容器启动时运行jar包
ENTRYPOINT ["java", "-jar","/Gxucpc-system-0.0.1-SNAPSHOT.jar"]
# 指定维护者的名字
MAINTAINER Gxuca