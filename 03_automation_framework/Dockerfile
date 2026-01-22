FROM bellsoft/liberica-openjdk-alpine:25.0.1
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ENTRYPOINT  java -cp 'libs/*' \
            -Dselenium.grid.enabled=true \
            -Dselenium.grid.hub-host=${HUB_HOST} \
            -Dbrowser=${BROWSER} \
            org.testng.TestNG \
            suite/${TEST_SUITE} \
            -threadcount ${THREAD_COUNT}