#/bin/sh
java -version
java -Dconfig=$CONFIG --add-opens java.base/java.lang=ALL-UNNAMED -jar /tmp/Discord2TelegramBot.jar