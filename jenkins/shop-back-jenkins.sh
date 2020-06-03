#!/usr/bin/env bash
app_name='shop'
project_id='basketboy'
docker_registry='123.56.143.183:5000'
container_name='shop'
# image_tag=${docker_registry}/${project_id}:${POM_VERSION}
# docker tag ${project_id}:${POM_VERSION} ${image_tag}
# echo '-------rmi Docker Repository------'
# docker rmi ${image_tag}
# docker rmi ${project_id}:${POM_VERSION}
echo '-------pull Docker Repository------'
docker pull ${docker_registry}/basketboy/${container_name}
docker stop ${app_name}
echo '---------stop container---------'
docker rm ${app_name}
echo '---------rm container-----------'
docker run -p 8080:8888 --network shop --name ${app_name} \
-v /etc/localtime:/etc/localtime \
-v /opt/app/${app_name}/logs:/var/logs \
-d ${project_id}/${app_name}:0.0.1
echo '----start container----'