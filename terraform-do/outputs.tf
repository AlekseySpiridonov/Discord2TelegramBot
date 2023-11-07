output "instance_public_ip" {
  description = "Public IPv4 address of the instance"
  value       = digitalocean_droplet.d2t-bot.ipv4_address
}
