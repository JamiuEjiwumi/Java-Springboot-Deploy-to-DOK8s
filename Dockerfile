FROM openjdk:11-jre-stretch
LABEL maintainer="Ejiwumi Jamiu Olatunde "jamiu.ejiwumi@gmail.com""

COPY ./build/libs/biller-manager-service-0.0.1-SNAPSHOT.jar /opt/biller-manager/biller-manager.jar

CMD ["java", "-jar", "/opt/customer-profile/customer-profile.jar"]