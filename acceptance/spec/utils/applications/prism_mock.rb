require_relative './application'

class PrismMock < Application
  def self.instance
    @instance ||= new
  end

  def initialize
    @name = 'prism-mock'
    @dir = ''
    @base_url = 'http://localhost:9999'
    @health_url = "#{@base_url}/prism-webapp/"
    @max_timeout_in_seconds = 60
    @command = './gradlew runPrismMock'
  end
end