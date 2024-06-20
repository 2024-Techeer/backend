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

# MySQL 클라이언트 설치
RUN apk update && apk add mysql-client

# wait-for-it.sh와 init_data.sh 복사
COPY wait-for-it.sh init_data.sh /app/backend/

# 실행 권한 추가
RUN chmod +x /app/backend/wait-for-it.sh /app/backend/init_data.sh

# .env 파일 복사
COPY ../.env /app/backend/.env

# build이미지에서 build/libs/*.jar 파일을 app.jar로 복사
COPY --from=builder build/libs/*.jar app.jar

# jar 파일 실행
ENTRYPOINT ["/bin/sh", "-c", "/app/backend/wait-for-it.sh mysqldb:3306 -- /app/backend/init_data.sh && java -jar /app.jar"]

# 볼륨 지정
VOLUME /tmp
