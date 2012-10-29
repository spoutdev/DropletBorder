DropletBorder
=============
World border "Droplet" plugin for the Spout platform to demonstrate the usage of map and player limiting using the scheduler.

Like the project? Feel free to [donate] to help continue development!

## What is Spout?
Spout is an open source multi-threaded Java voxel game framework and platform that opens the doors for infinite possibilities and expansion at the fingertips of gamers. Through the use of our platform, Spout and its API, developers can create "game" plugins, which use our client and server platform as a base. Players will be able to swap between games on-the-fly as they switch to a server running a different game plugin, or a set of game plugins.

[![Spout][Logo]][Homepage]  
[Homepage] | [Forums] | [Twitter] | [Facebook]

## The License
DropletBorder and all "Droplet" plugins for the Spout platform are licensed under the [MIT License][License].

Copyright (c) 2012, Spout LLC <<http://www.spout.org/>>  

## Getting the Source
The latest and greatest source can be found here on [GitHub][Source].  
Download the latest builds from our [build server][Builds]. [![Build Status](http://build.spout.org/job/DropletBorder/badge/icon)][Builds]

## Compiling the Source
DropletBorder uses Maven to handle its dependencies.

* Install [Maven 2 or 3](http://maven.apache.org/download.html)  
* Checkout this repo and run: `mvn clean package`

## Installing the Plugin
Simply place the latest [compiled jar][Builds] in your Spout server's plugins folder.  
Change the `config.yml` located under `plugins/DropletBorder` as needed.

## Contributing to the Project
Track and submit issues and bugs on our [issue tracker][Issues].  
[Share the love!][Donate] Donations help make development possible!

## Coding and Pull Request Conventions
* Generally follow the Oracle coding standards.
* No spaces, only tabs for indentation.
* No trailing whitespaces on new lines.
* 200 column limit for readability.
* Pull requests must compile, work, and be formatted properly.
* Sign-off on ALL your commits - this indicates you agree to the terms of our license.
* No merges should be included in pull requests unless the pull request's purpose is a merge.
* Number of commits in a pull request should be kept to *one commit* and all additional commits must be *squashed*.
* You may have more than one commit in a pull request if the commits are separate changes, otherwise squash them.
* For clarification, see the full pull request guidelines [here](http://spout.in/prguide).

**Please follow the above conventions if you want your pull request(s) accepted.**

[Logo]: http://cdn.spout.org/img/logo/spout_new.png
[Homepage]: http://www.spout.org
[Forums]: http://forums.spout.org
[License]: http://cdn.spout.org/license/mit.txt
[Source]: https://github.com/Droplets/DropletBorder
[Builds]: http://build.spout.org/job/DropletBorder
[Issues]: http://issues.spout.org/browse/droplet
[Wiki]: http://wiki.spout.org/display/droplet
[Twitter]: http://spout.in/twitter
[Facebook]: http://spout.in/facebook
[Donate]: http://spout.in/donate
