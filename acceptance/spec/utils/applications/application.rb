class Application
  @pid

  def run
    if healthcheck_is_200? @health_url
      raise "\nApplication #{@name} is already up. Please shut down the application.".red
    else
      start_app

      at_exit {
        puts "shutting down #{@name} | killing pid #{@pid}".yellow
        Process.kill('TERM', @pid)

        puts "\n d[ x_x ]b  |  #{@name} is ".magenta + "ded\n".red
      }
    end
  end

  def start_app
    `rm #{@name}*.log`

    project_level = File.expand_path("../../../..", __dir__)

    puts "\nAttempting to start #{@name} at #{@base_url} with command: #{@command}".yellow

    @pid = Process.spawn(
      @command,
      chdir: "#{project_level}/#{@dir}",
      pgroup: true,
      out: "#{@name}.std.log",
      err: "#{@name}.err.log"
    )

    wait_for_app_to_start
  end

  def wait_for_app_to_stop
    attempts = 0
    until !(healthcheck_is_200? @health_url)
      if attempts >= max_timeout_in_seconds
        print_error
        raise "#{@name} could not be stopped. See logs above".red
      end

      attempts += 1
      sleep 1
    end
  end

  def wait_for_app_to_start
    attempts = 0
    until healthcheck_is_200? @health_url
      if attempts >= max_timeout_in_seconds
        print_error
        raise "#{@name} never came up. See logs above".red
      end

      if address_in_use? @name
        print_error
        raise "Address #{@base_url} already in use. See logs above".red
      end

      if build_failed? @name
        print_error
        raise 'Build failed. See logs above'.red
      end

      attempts += 1
      sleep 1
      print "\r[#{attempts} / #{max_timeout_in_seconds}] Waiting for #{@name} to start".yellow
    end
    puts "\n\n d[ 0_0 ]b  |  #{@name} ".light_magenta + "started".green + " @ #{@base_url}".light_magenta
  end

  def print_error
    puts "\n\n"
    puts `cat *.log`
    puts "\n\n"
    puts 'Failure!!!'.red
    puts "\n\n"
  end

  def address_in_use?(name)
    `cat #{name}*`.include? 'Address already in use'
  end

  def healthcheck_is_200?(healthcheck_url)
    HTTParty.get(healthcheck_url).code == 200
  rescue Errno::ECONNREFUSED, Errno::ENETUNREACH, Errno::EADDRNOTAVAIL
    false
  end

  def build_failed?(name)
    `cat #{name}*`.include? 'FAILURE: Build failed with an exception.'
  end

  def max_timeout_in_seconds
    @max_timeout_in_seconds || 20
  end

  def stop
    puts "\nshutting down #{@name} | killing pid #{@pid}\n".yellow
    Process.kill('TERM', @pid)

    wait_for_app_to_stop

    puts "\n d[ x_x ]b  |  #{@name} is ".magenta + "ded\n".red
  end
end
