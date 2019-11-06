require_relative './application'

class StargateApi < Application
  def initialize
    @name = 'stargate-api'
    @dir = ''
    @base_url = 'http://localhost:8081'
    @health_url = "#{@base_url}/actuator/health"
    @max_timeout_in_seconds = 120
    @command = 'SPRING_PROFILES_ACTIVE=acceptance ./gradlew bootRun'
  end
end
