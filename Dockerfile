FROM openjdk:21-slim-bullseye
COPY target/discord2telegrambot-*-jar-with-dependencies.jar /tmp/Discord2TelegramBot.jar
COPY tools/start-bot.sh /tmp/start.sh
WORKDIR /tmp/
RUN ls -la /tmp/

ENTRYPOINT ["/bin/sh", "/tmp/start.sh"]
