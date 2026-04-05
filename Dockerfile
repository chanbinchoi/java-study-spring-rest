# 베이스 이미지 지정
FROM openjdk:27-ea-trixie

# 수정된 부분: target 폴더 내의 jar 파일을 student-app.jar라는 이름으로 복사
COPY target/*.jar student-app.jar
EXPOSE 8080

# 컨테이너 시작 시 실행될 고정 명령어
ENTRYPOINT ["java", "-jar", "/student-app.jar"]