require "capybara"
require "minitest"

Capybara.default_driver = :selenium

class TestPilot < Minitest::Runnable
  include Capybara::DSL
  include Minitest::Assertions

  def self.root=(root)
    @@root = root
  end

  def setup
    begin
      Capybara.reset_sessions!
    rescue Errno::ECONNREFUSED
      page.driver.quit
    end
    Capybara.use_default_driver
  end

  def fly(&block)
    setup
    instance_exec(&block)
  end

  def method_missing(method_name, *args, &block)
    function_file_path = "#{@@root}/functions/#{method_name}"
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
