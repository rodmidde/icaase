VAGRANTFILE_API_VERSION = "2"

path = "#{File.dirname(__FILE__)}"

require 'yaml'
require path + '/homestead.rb'

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  #config.vbguest.auto_update = false
  Homestead.configure(config, YAML::load(File.read(path + '/Homestead.yaml')))
end
