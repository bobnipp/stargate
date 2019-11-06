require 'capybara/rspec/matchers/match_selector'

class HasPinWithContent <  Capybara::RSpecMatchers::Matchers::MatchSelector
  def initialize(*args)
    @args = args.dup
    @content = args[0].is_a?(Symbol) ? args[1] : args[0]
  end

  def matches?(actual)
    wrap_matches?(actual) do |el|
      pins = el.all('.gmnoprint')
      pins.each do |pin|
        pin.hover
        return true if el.has_content?(@content)
      end
      raise Capybara::ExpectationNotMet, "Pin with content #{@content} not found"
    end
  end

  def does_not_match?(actual)
    wrap_does_not_match?(actual) do |el|
      pins = el.all('.gmnoprint')
      pins.each do |pin|
        pin.hover
        raise Capybara::ExpectationNotMet, "Expected not to find pin with content #{@content}" if el.has_content?(@content)
      end
      return true
    end
  end
end

def have_pin_with_content(*args)
  HasPinWithContent.new(*args)
end