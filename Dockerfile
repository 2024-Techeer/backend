# Bellsoft Liberica OpenJDK 17 이미지를 기반으로 설정
FROM bellsoft/liberica-openjdk-alpine:17 AS builder

#gradlew 복사
COPY gradlew .

#gradle 복사
COPY gradle gradle

#build.gradle 복사
COPY build.gradle .

#settings.gradle 복사
COPY settings.gradle .

#웹어플리케이션 소스 복사
COPY src src

#gradlew 실행 권한 부여
RUN chmod +x ./gradlew

#gradlew를 통해 실행 가능한 jar파일 생성
RUN ./gradlew bootJar

# 최종 이미지
FROM bellsoft/liberica-openjdk-alpine:17

# builder 스테이지에서 생성된 jar 파일을 현재 이미지로 복사
COPY --from=builder /home/gradle/build/libs/spring-0.0.1-SNAPSHOT.jar /app/spring-0.0.1-SNAPSHOT.jar

# Java 실행 명령 고정
ENTRYPOINT ["java", "-jar", "/app/spring-0.0.1-SNAPSHOT.jar"]


