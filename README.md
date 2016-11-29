# Gatling simulations

This [giter8](http://www.foundweekends.org/giter8/) template provides a starting point for building Gatling simulations
to test (micro)services. Besides Gatling simulations applying the template also generates code for a *sample*
[Akka http](http://doc.akka.io/docs/akka-http/current/scala.html) service for testing and experimentation.
Consequently the generated code is self-contained: once built (which typically pulls the dependent libraries
from the network) running the simulations no longer requires an Internet connection.


## Quickstart

1. Install `giter8` (platform-dependent; on the Mac `brew install giter8`). You could skip this step if using sbt's `new` command (introduced in [version 0.13.13](http://www.scala-sbt.org/0.13/docs/sbt-new-and-Templates.html))
2. Apply the template; there are prompts for the customizable template fields, with the defaults shown in square brackets. The example below uses `g8`; alternatively with sbt's template resolver the command would be`sbt new polymorphic/gatling-simulation-template.g8`)

```shell
% g8 polymorphic/gatling-simulation-template.g8 
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

Simple Gatling simulations

name [Gatling simulations]:
gatlingVersion [2.2.3]:
gatlingPluginVersion [2.2.0]:
package [com.microworkflow]:

Template applied in ./gatling-simulations
```

3.  Run `sbt` and start the sample service from the sbt prompt. The generated project uses the [sbt-revolver](https://github.com/spray/sbt-revolver) plugin to do so in a forked VM.

```shell
% sbt
[info] Loading project definition from /private/tmp/gatling-simulations/project
[info] Updating {file:/private/tmp/gatling-simulations/project/}gatling-simulations-build...
[info] Resolving org.fusesource.jansi#jansi;1.4 ...
[info] Done updating.
[info] Set current project to simulations (in build file:/private/tmp/gatling-simulations/)
> svc/reStart
[info] Updating {file:/private/tmp/gatling-simulations/}domain...
[info] Resolving jline#jline;2.12.1 ...
[info] Done updating.
[info] Updating {file:/private/tmp/gatling-simulations/}svc...
[info] Resolving jline#jline;2.12.1 ...
[info] Done updating.
[info] Compiling 1 Scala source to /private/tmp/gatling-simulations/domain/target/scala-2.11/classes...
[info] Compiling 3 Scala sources to /private/tmp/gatling-simulations/svc/target/scala-2.11/classes...
[info] Application svc not yet started
[info] Starting application svc in the background ...
svc Starting com.microworkflow.svc.Svc.main()
[success] Total time: 10 s, completed Nov 29, 2016 5:29:52 AM
> svc service http://localhost:8080/ up
```

4. Run the Gatling simulations from the sbt prompt. The generated project uses the [gatling-sbt](http://gatling.io/docs/2.2.3/extensions/sbt_plugin.html) plugin which allows sbt to use Gatling as a test framework

```
> gatling:test
[info] Updating {file:/private/tmp/gatling-simulations/}root...
[info] Resolving jline#jline;2.12.1 ...
[info] Done updating.
[info] Compiling 1 Scala source to /private/tmp/gatling-simulations/target/scala-2.11/classes...
[info] Compiling 2 Scala sources to /private/tmp/gatling-simulations/target/scala-2.11/test-classes...
[info] Compiling 2 Scala sources to /private/tmp/gatling-simulations/target/scala-2.11/gatling-classes...
Simulation com.microworkflow.simulations.ServiceSimulation started...

================================================================================
2016-11-29 05:30:26                                           5s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=16     KO=0     )
> request one                                              (OK=8      KO=0     )
> post one                                                 (OK=8      KO=0     )

---- Local simulation ----------------------------------------------------------
[###########################################################               ] 80%
waiting: 2      / active: 0      / done:8
================================================================================


================================================================================
2016-11-29 05:30:27                                           5s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=20     KO=0     )
> request one                                              (OK=10     KO=0     )
> post one                                                 (OK=10     KO=0     )

---- Local simulation ----------------------------------------------------------
[##########################################################################]100%
waiting: 0      / active: 0      / done:10
================================================================================

Simulation com.microworkflow.simulations.ServiceSimulation completed in 4 seconds
Parsing log file(s)...
Parsing log file(s) done
Generating reports...

================================================================================
---- Global Information --------------------------------------------------------
> request count                                         20 (OK=20     KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                    190 (OK=190    KO=-     )
> mean response time                                    17 (OK=17     KO=-     )
> std deviation                                         40 (OK=40     KO=-     )
> response time 50th percentile                          9 (OK=9      KO=-     )
> response time 75th percentile                         10 (OK=10     KO=-     )
> response time 95th percentile                         34 (OK=34     KO=-     )
> response time 99th percentile                        159 (OK=159    KO=-     )
> mean requests/sec                                      4 (OK=4      KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                            20 (100%)
> 800 ms < t < 1200 ms                                   0 (  0%)
> t > 1200 ms                                            0 (  0%)
> failed                                                 0 (  0%)
================================================================================

Reports generated in 0s.
Please open the following file: /private/tmp/gatling-simulations/target/gatling/servicesimulation-1480426221351/index.html
Global: mean of response time is less than 1500.0 : true
request one: count of failed requests is less than 1.0 : true
post one: count of failed requests is less than 1.0 : true
[info] Simulation ServiceSimulation successful.
Simulation com.microworkflow.simulations.ServiceFunctionalSpec started...

================================================================================
2016-11-29 05:30:30                                           1s elapsed
---- Requests ------------------------------------------------------------------
> Global                                                   (OK=3      KO=0     )
> ping request                                             (OK=1      KO=0     )
> one                                                      (OK=2      KO=0     )

---- ServiceFunctionalSpec -----------------------------------------------------
[##########################################################################]100%
waiting: 0      / active: 0      / done:1
================================================================================

Simulation com.microworkflow.simulations.ServiceFunctionalSpec completed in 0 seconds
Parsing log file(s)...
Parsing log file(s) done
Generating reports...

================================================================================
---- Global Information --------------------------------------------------------
> request count                                          3 (OK=3      KO=0     )
> min response time                                      2 (OK=2      KO=-     )
> max response time                                     25 (OK=25     KO=-     )
> mean response time                                    11 (OK=11     KO=-     )
> std deviation                                         10 (OK=10     KO=-     )
> response time 50th percentile                          5 (OK=5      KO=-     )
> response time 75th percentile                         15 (OK=15     KO=-     )
> response time 95th percentile                         23 (OK=23     KO=-     )
> response time 99th percentile                         25 (OK=25     KO=-     )
> mean requests/sec                                      3 (OK=3      KO=-     )
---- Response Time Distribution ------------------------------------------------
> t < 800 ms                                             3 (100%)
> 800 ms < t < 1200 ms                                   0 (  0%)
> t > 1200 ms                                            0 (  0%)
> failed                                                 0 (  0%)
================================================================================

Reports generated in 0s.
Please open the following file: /private/tmp/gatling-simulations/target/gatling/servicefunctionalspec-1480426229475/index.html
ping request: percentage of failed requests is 0.0 : true
one: percentage of failed requests is 0.0 : true
[info] Simulation ServiceFunctionalSpec successful.
[info] Simulation(s) execution ended.
[success] Total time: 20 s, completed Nov 29, 2016 5:30:31 AM
```

## Details

Applying the template generates a multi-project sbt build comprising three projects:

```
> projects
[info] In file:/Users/dam/Code/OSS/g8test/
[info] 	   domain
[info] 	 * root
[info] 	   svc
```

* The `root` project (default) contains two Gatling simulations:
    * `ServiceSimulation` is a regular Gatling simulation aimed at performance testing, while
    * `ServiceFunctionalSpec` is a functional spec aimed at (surprise!) service functional testing.

    Both simulations reside in the test configuration. The `gatling:test` action above executes all simulations; to run a specific simulation
    use `gatling:testOnly`. For example:

    ```
    > gatling:testOnly com.microworkflow.simulations.ServiceFunctionalSpec
    Simulation com.microworkflow.simulations.ServiceFunctionalSpec started...

    ================================================================================
    2016-11-29 05:40:40                                           1s elapsed
    ---- Requests ------------------------------------------------------------------
    > Global                                                   (OK=3      KO=0     )
    > ping request                                             (OK=1      KO=0     )
    > one                                                      (OK=2      KO=0     )

    ---- ServiceFunctionalSpec -----------------------------------------------------
    [##########################################################################]100%
    waiting: 0      / active: 0      / done:1
    ================================================================================

    Simulation com.microworkflow.simulations.ServiceFunctionalSpec completed in 0 seconds
    Parsing log file(s)...
    Parsing log file(s) done
    Generating reports...

    ================================================================================
    ---- Global Information --------------------------------------------------------
    > request count                                          3 (OK=3      KO=0     )
    > min response time                                      5 (OK=5      KO=-     )
    > max response time                                     21 (OK=21     KO=-     )
    > mean response time                                    10 (OK=10     KO=-     )
    > std deviation                                          8 (OK=8      KO=-     )
    > response time 50th percentile                          5 (OK=5      KO=-     )
    > response time 75th percentile                         13 (OK=13     KO=-     )
    > response time 95th percentile                         19 (OK=19     KO=-     )
    > response time 99th percentile                         21 (OK=21     KO=-     )
    > mean requests/sec                                      3 (OK=3      KO=-     )
    ---- Response Time Distribution ------------------------------------------------
    > t < 800 ms                                             3 (100%)
    > 800 ms < t < 1200 ms                                   0 (  0%)
    > t > 1200 ms                                            0 (  0%)
    > failed                                                 0 (  0%)
    ================================================================================

    Reports generated in 0s.
    Please open the following file: /private/tmp/gatling-simulations/target/gatling/servicefunctionalspec-1480426839283/index.html
    ping request: percentage of failed requests is 0.0 : true
    one: percentage of failed requests is 0.0 : true
    [info] Simulation ServiceFunctionalSpec successful.
    [info] Simulation(s) execution ended.
    [success] Total time: 3 s, completed Nov 29, 2016 5:40:40 AM
    ```

* The `svc` project contains a test service to experiment with. The simulations mentioned above work with this service so it must be up prior to running the simulations.
* The `domain` project contains the domain objects (in this case a single case class) used by the service.

Have fun!
