FROM java:8

MAINTAINER "boyStar~ 814411929@qq.com"

VOLUME /tmp

ADD easySpring-0.0.1-SNAPSHOT.jar app.jar

RUN bash -c 'touch /app.jar'

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 8181

ENTRYPOINT ["java", "-jar", "app.jar"]