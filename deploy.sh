#!/usr/bin/env bash

if [ -z "$1" ]
then
	echo "usage: ./deploy.sh tag..."
	exit 1
else
    ./gradlew clean build -x test
    for tag in $*
    do
        docker build -t registry.git.vipteens.cn/school/cloud/gateway:${tag} ./gateway
        docker push registry.git.vipteens.cn/school/cloud/gateway:${tag}

        docker build -t registry.git.vipteens.cn/school/cloud/registry:${tag} ./registry
        docker push registry.git.vipteens.cn/school/cloud/registry:${tag}

        docker build -t registry.git.vipteens.cn/school/cloud/monitor:${tag} ./monitor
        docker push registry.git.vipteens.cn/school/cloud/monitor:${tag}
    done
fi