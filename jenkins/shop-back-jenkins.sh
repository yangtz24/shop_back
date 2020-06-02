#!/usr/bin/env bash
app_name='shop'
docker stop ${app_name}
echo '---------stop container---------'
docker rm ${app_name}
echo '---------rm container-----------'
docker run -p 8080:8888 --network shop --name ${app_name} \
-v /etc/localtime:/etc/localtime \
-v /opt/app/${app_name}/logs:/var/logs \
-d basketboy/shop-docker-file:0.0.1
echo '----start container----'