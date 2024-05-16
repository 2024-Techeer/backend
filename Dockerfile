# Bellsoft Liberica OpenJDK 17 이미지를 기반으로 설정
FROM bellsoft/liberica-openjdk-alpine:17

# 작업 디렉토리 설정
WORKDIR /app

# 소스 코드 및 Gradle Wrapper를 컨테이너에 복사
COPY . /app

# gradlew 파일에 실행 권한 부여
RUN chmod +x ./gradlew

# 프로젝트 빌드
RUN ./gradlew build --no-daemon

# Java 실행 명령 고정
ENTRYPOINT ["java", "-jar"]

# 기본으로 실행할 JAR 파일 및 기타 매개변수
CMD ["build/libs/spring-0.0.1-SNAPSHOT.jar"]

