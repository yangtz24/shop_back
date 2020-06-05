#!/usr/bin/env bash
APP_NAME='shop-back'
PROJECT_ID='basketboy'
#docker_registry='123.56.143.183:5000'
#container_name='shop'
# image_tag=${docker_registry}/${project_id}:${POM_VERSION}
# docker tag ${project_id}:${POM_VERSION} ${image_tag}
# echo '-------rmi Docker Repository------'
# docker rmi ${image_tag}
# docker rmi ${project_id}:${POM_VERSION}
#echo '-------pull Docker Repository------'
#docker pull ${docker_registry}/basketboy/${container_name}
echo '----------select container----------'
if docker ps -a | grep ${APP_NAME}; then
echo '---------stop container---------'
docker stop ${APP_NAME}
echo '---------rm container-----------'
docker rm ${APP_NAME}
echo '---------container end--------'
fi
#echo '--------select image----------'
#if docker images -a | grep ${PROJECT_ID}/shop:0.0.1-SNAPSHOT; then
#echo '-----rm images--------'
#docker rmi ${PROJECT_ID}/shop:0.0.1-SNAPSHOT 

echo '---------run container----------'
docker run -p 8080:8888 --network shop --name ${APP_NAME} \
-v /etc/localtime:/etc/localtime \
-v /opt/app/${APP_NAME}/logs:/var/logs \
-d ${PROJECT_ID}/shop:0.0.1-SNAPSHOT
echo '----start container----'
