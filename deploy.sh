#!/bin/bash

if [ ! "$(docker network ls | grep jamiu)" ];
then
    echo "************************************* jamiu Network not Running, Creating one *******************************"
    docker network create jamiu
    docker network connect jamiu mysql
else
    echo "************************************* jamiu Network Running, Moving on *******************************"
fi

echo "************************************* Start the database container if not running *******************************"
if [ ! "$(docker ps -q -f name=mysql)" ];
then
  docker start mysql
fi

echo "************************************* Creating Database if not exists *******************************"
docker exec -i mysql mysql -ucreator -pI_only_create! <<< "create database IF NOT EXISTS billermanager;"


if [ ! "$(docker ps -q -f name=billermanager-service)" ]
then
        echo "************************************* No instance of billermanager Service Running Container *******************************"
        if [ "$(docker ps -aq -f name=billermanager-service)" ]
        then
            echo "************************************* Removing Previous non-running Container *******************************"
            docker rm billermanager-service
        fi
else
        echo "************************************* Killing Previous Container *******************************"
        docker kill billermanager-service

        echo "************************************* Removing Previous Container *******************************"
        docker rm billermanager-service
fi

echo "************************************* Starting the new Container *******************************"
docker pull jamiu-limited/images:billermanager-service
docker run -d -p 8020:8020 --name billermanager-service --network jamiu jamiu-limited/images:billermanager-service