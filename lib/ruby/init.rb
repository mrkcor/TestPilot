require "capybara"
require "minitest"

Capybara.default_driver = :selenium

class TestPilot < Minitest::Runnable
  include Capybara::DSL
  include Minitest::Assertions

  def setup
    Capybara.reset_sessions!
    Capybara.use_default_driver
  end

  def fly(&block)
    setup
    instance_exec(&block)
  end
end
