# Gatling simulations

This [giter8](http://www.foundweekends.org/giter8/) template provides a starting point for using Gatling to test
(micro)services. The multi-project sbt build comprises three projects:

    % sbt
    [info] Loading project definition from /Users/dam/Code/OSS/g8test/project
    [info] Set current project to simulations (in build file:/Users/dam/Code/OSS/g8test/)
    > projects
    [info] In file:/Users/dam/Code/OSS/g8test/
    [info] 	   domain
    [info] 	 * root
    [info] 	   svc

* The 'root' project (default) contains two Gatling simulations: `ServiceSimulation` is a regular Gatling simulation
aimed at performance testing, while `ServiceFunctionalSpec` is a functional spec aimed at (surprise!) service functional
testing. Both simulations reside in the test configuration.
* The `svc` project contains a simple Akka-http service to experiment with. The simulations mentioned above work with
this service.
* The `domain` project contains the domain object used by the service.

## Usage





