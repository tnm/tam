name         := "tam"

version      := "0.1.0"

scalaVersion := "2.9.0-1"

organization := "com.banksimple"

resolvers ++= Seq("Coda Hale's Repository"        at "http://repo.codahale.com/",
                  "BankSimple Nexus Snapshots"    at "http://nexus.banksimple.com/content/repositories/snapshots/",
                  "BankSimple Nexus Releases"     at "http://nexus.banksimple.com/content/repositories/releases/",
                  "Oracle Released Java Packages" at "http://download.oracle.com/maven"
)

libraryDependencies ++= Seq(
  "com.codahale"        %%  "logula"             %  "2.1.3",
  "com.codahale"        %%  "simplespec"         %  "0.3.4",
  "com.sleepycat"       %   "je"                 %  "3.3.75",
  "com.codahale"        %%  "jerkson"            %  "0.3.2"
)

