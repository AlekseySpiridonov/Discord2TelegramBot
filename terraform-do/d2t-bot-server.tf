resource "digitalocean_droplet" "d2t-bot" {
  image = "ubuntu-22-04-x64"
  name = "d2t-bot"
  region = "fra1"
  size = "s-1vcpu-512mb-10gb"
  ssh_keys = [
    data.digitalocean_ssh_key.terraform.id
  ]

  connection {
    host = self.ipv4_address
    user = "root"
    type = "ssh"
    private_key = file(var.ssh_key)
    timeout = "2m"
  }

  provisioner "file" {
    source      = "${var.bot_config}"
    destination = "system.properties"
  }

  provisioner "file" {
    source      = "${var.bot_chats_config}"
    destination = "chats.properties"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt update",
      "sudo DEBIAN_FRONTEND=noninteractive apt install docker.io -qq --yes --force-yes",
      "sudo usermod -aG docker $USER",
      "export BOT_CONFIG=/data/system.properties && export BOT_CHATS_CONFIG=/data/chats.properties",
      "export BOT_DOCKER_IMAGE=ghcr.io/alekseyspiridonov/discord2telegrambot/d2t-bot",
      "docker run -d --restart=always --net=host -e CONFIG=$BOT_CONFIG -e CHATS_FOR_SYNC=$BOT_CHATS_CONFIG -v /root/:/data/ --name d2tbot $BOT_DOCKER_IMAGE",
      "sleep 30",
      "docker logs d2tbot"
    ]
  }
}
