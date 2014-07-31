# TestPilot

TestPilot is a Java Swing GUI application that runs JRuby scripts to perform
acceptance tests. Instead of inventing a custom DSL to drive Selenium it uses
Capybara to do so.

# Requirements

Before you get started ensure that you Java 8 and Firefox are installed.

# Building

You can build TestPilot in Netbeans, if you open it as an existing project it
should just work.

# Getting started

Download the binary distribution here: https://github.com/mkremer/TestPilot/releases/latest.
Unzip it at a location of your own preference. To start the application navigate
there with your file explorer and double click on TestPilot.jar.

## Customizing your setup

You can customize the setup for all your scripts by selecting the customizations
option from the File menu, this opens up scripts/test_pilot.rb which is always
evaluated before your scripts run. You can change the Capybara app host here by
setting an options setting and you can apply custom code of your own design:

```ruby
@options["app_host"] = "http://www.5ec.nl"

# Your code here
```

## Writing and running scripts

Scripts are organized in directories, before you start working on scripts you
should open a directory using the "Open directory" option in the File menu. When
you first start this will open up scripts/suites (relative to TestPilot.jar).
Create a new directory here and open that using the "Open" button.

In the "Directory" section on your screen you'll see the files in this directory
(there won't be any if you just got started). If you type some text like:

```ruby
visit "http://www.google.com"
```

And then save it (using the "Save" option in the File menu) you'll see that it
gets added to the list. The grey dot indicates you haven't yet run this file in
your current session. If you use the "Run" option in the File menu a browser
window will pop up and navigate to google.com, also the grey dot will have
become a green dot indicating that the script did not raise any errors.

You can run all the scripts in the directory that you have opened with the "Run
directory" option in the File menu, the dots in front of the filenames in the
directory section will be updated to show which scripts ran without error.

The capabilities of Capybara are well documented on its own site:
https://github.com/jnicklas/capybara

To test the correctness of the pages that your scripts visit you can make use
of Minitest's assertions like so:

```ruby
visit "http://www.5ec.nl"
assert page.has_content?("Kremer's Blog")
```

Assertions that fail will lead to a failed test, which will result in a red dot
icon.

See http://docs.seattlerb.org/minitest/Minitest/Assertions.html for a list of
the available assertion functions.

## Adding your own custom functions

Its very likely that you'll want to put certain building blocks (like logging
into the application that you're testing) in custom functions that you can reuse
in your scripts.

If you open the scripts/functions directory and save a file then a function with
matching name will be available in your test scripts. I recommend that you keep
to the convention that the only argument to each function is a Hash with options.

If you put the following in scripts/google_search:

```ruby
visit "http://www.google.com"
fill_in "q", with: arguments[:search_for]
click_on "gbqfb"
```

You can then in any of your scripts use to go to Google.com and enter a search
term like so:

```ruby
google_search search_for: "Capybara"
```
