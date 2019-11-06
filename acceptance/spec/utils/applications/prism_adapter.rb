require_relative './application'

class PrismAdapter < Application
  def initialize
    @name = 'prism-adapter'
    @dir = ''
    @base_url = 'http://localhost:8082'
    @health_url = "#{@base_url}/actuator/health"
    @max_timeout_in_seconds = 60
    @command = './gradlew runPrismAdapter'
  end
end