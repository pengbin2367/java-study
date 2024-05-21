sudo pacman -S docker
sudo systemctl start docker.service
sudo systemctl enable docker.service
sudo groupadd docker
sudo gpasswd -a pengbin docker
newgrp docker
docker run hello-world

aliyun mirror config
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

Install tomcat
docker pull tomcat
docker run -d -p 8080:8080 --name t1 tomcat 
docker ps
docker exec -it 1450f03922b1 bash
rm -rf webapps
mv webapps.dist webapps


Install MySQL
docker pull mysql

docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
docker exec -it d88fa27d259f bash
mysql -uroot -proot

docker run -p 3306:3306 --privileged=true -v /docker/mysql/log:/var/log/mysql -v /docker/mysql/data:/var/lib/mysql -v /docker/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=root --name mysql -d mysql


Install Redis

docker run -p 6379:6379 --name redis --privileged=true -v /docker/redis/data:/data -v /docker/redis/redis.conf:/etc/redis/redis.conf -d redis redis-server /etc/redis/redis.conf