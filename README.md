# Discord 2 Telegram cross posting bot
## What is it?
The bot can cross post messages between **Discord Text Group Chat** and **Telegram Group Chat**.    
## How it is working?
You should create 2 bots: Telegram and Discord.  
After creating bots fills config with bot's credentials:
```
telegram_token=""
telegram_botname=""
....
discord_token=""
```
Last thing of config file is IDs of Discord and Telegram chat for synchronisation.  **\\\TBD: How find this IDs without DEBUG.**  
Setup path to this file by:
```
chats_for_sync=src/main/resources/chats.properties.example
```
and fill `chats_for_sync` file with IDs for synchronisation.  
```
discord_chat_id=telegram_chat_id
```
**WARNING!  Chat IDs MUST be unique!!!**  \\\TBD: Add validations  
Example of config file: `src/main/resources/system.properties.example`.  
Example of chat sync config file `src/main/resources/chats.properties.example`.  
Bots will monitoring Telegram and Discord chats and cross post message between chats.  
For example:  
Discod chat  
![d2t](https://github.com/AlekseySpiridonov/Discord2TelegramBot/blob/assets/images/d2t.png?raw=true)  
  
Telegram chat  
![t2d](https://github.com/AlekseySpiridonov/Discord2TelegramBot/blob/assets/images/t2d.png?raw=true)  

## How to start bot?
1. Create config files by example: `src/main/resources/system.properties.example` and `src/main/resources/chats.properties.example`.  
2. Run Java application by command:
```
mvn -B package --file pom.xml && java -Dconfig=$PATH$/system.properties -jar $PATH$/discord2telegrambot-*-jar-with-dependencies.jar
```
   OR  
   Run Docker image:  
```
docker run -d --restart=always --net=host -e CONFIG=/data/$CONFIG_FILE_NAME$ -v $PATH_FOR_CONFIG_DIRECTORY$:/data/ --name discord2telegrambot ghcr.io/alekseyspiridonov/discord2telegrambot/d2t-bot
```

## Known issues \limitations
Supports ONLY text messages.  
Values of chat IDs MUST be unique. You can sync only ONE Discord chat with ONE Telegram chat.  
Can't ignore some messages (for example messages from another bots).  
