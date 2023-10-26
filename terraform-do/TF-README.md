## [ALPHA VERSION] Terraform for quick deployment D2T Bot Docker Image to DigitalOcean Droplet  

### Note: ⚠️ Will deploy :latest Docker Image 

1. Create **API token** in **DigitalOcean Console** https://cloud.digitalocean.com/account/api/tokens  
2. Create **SSH key** in your DigitalOcean Team Console
3. Create valid `chats.properties` and `system.properties` from example config files and put it in this folder
4. Export **DigitalOcean API token** by command `export DO_PAT="<YOUR_TOKEN>"` 
5. Run Terraform Plan **and validate changes** by command 
```
terraform plan \
-var "do_ssh_key_name=<ssh_key_name_from_step_2>" \
-var "do_token=${DO_PAT}" \
-var "ssh_key=<local_path_to_ssh_key_from_step_2>" \
-var "bot_config=system.properties" \
-var "bot_chats_config=chats.properties"
```
6. **Apply Terraform changes** by command 
```
terraform apply \
-var "do_ssh_key_name=<ssh_key_name_from_step_2>" \
-var "do_token=${DO_PAT}" \
-var "ssh_key=<local_path_to_ssh_key_from_step_2>" \
-var "bot_config=system.properties" \
-var "bot_chats_config=chats.properties"
```
7. SSH to your host **and validate** Bot  by command `docker logs d2tbot`

### FAQ
#### Why is it deploying Docker Image for DigitalOcean Droplet? Not the DigitalOcean App Platform? Why do you use Docker Image instead of single JAR file?
We need it for testing the Docker Image. We shared it; maybe someone will use it also.
If you need it, you can create, modify, and deploy it another way from [README](https://github.com/AlekseySpiridonov/Discord2TelegramBot/blob/dev/README.md).  
  
#### How can I change region for deployment? Or instance size? Name? Another parameters?
Please edit `provider.tf` file.  
  
#### Issues, problem, limitations....
Sorry, but project in ALPHA stage.   
