FROM bellsoft/liberica-openjdk-alpine:25.0.1
RUN apk add curl jq
WORKDIR /home/selenium-docker
ADD target/docker-resources ./
ADD runner.sh runner.sh
RUN dos2unix runner.sh
ENTRYPOINT sh runner.sh
#ENTRYPOINT  java -cp 'libs/*' \
#            -Dselenium.grid.enabled=true \
#            -Dselenium.grid.hub-host=${HUB_HOST} \
#            -Dbrowser=${BROWSER} \
#            org.testng.TestNG \
#            suite/${TEST_SUITE} \
#            -threadcount ${THREAD_COUNT}