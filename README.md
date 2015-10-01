icaase
======
Advanced Programming Course Advanced Software Engineering

Vagrant usage
======
1. Install [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
2. Install [Vagrant](https://www.vagrantup.com/downloads.html)
3. Create a public SSH key, using the code below. If you're using Windows, run it with Git Bash.

``` bash
cd ~ && ssh-keygen -t rsa -C "you@homestead"
```
Next, run the following commands from your project's folder
``` bash
vagrant up && vagrant ssh #(if it requests a password, it's: vagrant)
```
- If you get an error, download the Virtual Machine likely failed. To resume run the same command again. If that fails immediately, run this command and try again: "rm -rf ~/.vagrant.d/tmp/*"

Wait until the machine has booted and run the following commands from within the Vagrant box.
``` bash
sudo chmod +x provision.sh
sudo ./provision.sh
```
Run the following to create a database, the password it will ask for is "secret"
``` bash
echo "create database dare2date" | mysql -u homestead -p
```
**Thats it! Your development environment is now up and running ;)**
