require "capybara"

Capybara.default_driver = :selenium

class TestPilot
  include Capybara::DSL

  def setup
    Capybara.reset_sessions!
    Capybara.use_default_driver
  end

  def fly(&block)
    setup
    instance_exec(&block)
  end
end
