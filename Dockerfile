# 빌드 스테이지
FROM amazoncorretto:21-alpine AS build
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test

# 실행 스테이지
FROM amazoncorretto:21-alpine
WORKDIR /app
# 빌드 스테이지에서 생성된 jar 복사
COPY --from=build /app/build/libs/*.jar application.jar

# 타임존 설정
RUN apk add --no-cache tzdata
ENV TZ=Asia/Seoul

EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "application.jar"]