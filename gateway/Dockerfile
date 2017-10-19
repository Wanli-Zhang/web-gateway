FROM registry.git.vipteens.cn/infras/openjdk:8u121-jre-alpine-fontconfig

WORKDIR /usr/share/gateway
ADD ./build/libs/gateway-2.6.5.jar gateway.jar

EXPOSE 8060 8000

ENV PROFILE_OPTS="" \
    DEBUG_OPTS="" \
    JVM_OPTS=""

ENTRYPOINT java $DEBUG_OPTS -Djava.security.egd=file:/dev/./urandom $PROFILE_OPTS  -jar gateway.jar  $JVM_OPTS


