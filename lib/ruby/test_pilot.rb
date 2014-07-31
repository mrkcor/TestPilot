require "capybara"
require "minitest"

Capybara.default_driver = :selenium
Capybara.run_server = false

class TestPilot < Minitest::Runnable
  include Capybara::DSL
  include Minitest::Assertions

  def self.scripts_path=(scripts_path)
    @@scripts_path = scripts_path
  end

  def setup
    begin
      Capybara.reset_sessions!
    rescue Errno::ECONNREFUSED, Errno::EBADF
      page.driver.quit
    end
    Capybara.use_default_driver

    unless @options["app_host"].nil?
      Capybara.app_host = @options["app_host"]
    end
  end

  def fly(&block)
    @options = Hash.new
    if File.exists?("#{@@scripts_path}/test_pilot.rb")
      eval(File.read("#{@@scripts_path}/test_pilot.rb"))
    end
    setup
    instance_exec(&block)
  end

  def method_missing(method_name, *args, &block)
    function_file_path = "#{@@scripts_path}/functions/#{method_name}"
    if (File.exists?(function_file_path))
       define_singleton_method(method_name) do |*args|
         eval(File.read(function_file_path))
       end

       send(method_name, *args, &block)
    else
       super
    end
  end
end
