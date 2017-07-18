FROM java:8-jre
MAINTAINER Alexey Zatsepin <alexzatsepin7@gmail.com>
ADD ./target/shrinker.jar /app/
CMD ["java", "-jar", "/app/shrinker.jar"]
EXPOSE 8080