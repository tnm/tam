tam
========

**easy-to-use, JSON-only Scala interface to BDB Java Edition**

overview
----------

**tam** is a straight-forward Scala interface to Berkeley Database Java Edition
that uses jerkson to automatically serialize your Scala objects into JSON before storing
them. **tam** exposes `put`, `get`, and `delete` methods to BDB, abstracting the various
configuration and environment settings of the Java API.

setup
-----------

If you're using Maven, place the following in your `pom.xml`:


If you're using SBT 0.10.x, place the following your `build.sbt`:


If you're using SBT 0.7.x, place the following in your project file:



how to use
-------------

First, import:

    import org.tnmx.tam._

Now, open a database. We'll give this one a name `people_db`, with a path of `db/people_db`. `open`
will return a TamDatabase object.

    val db = TamDatabase.open("people_db", "db/people_db")

Take a Scala object and store it as JSON with a key name of `Jessica`:

    val jessicaObject = Map("name" -> "Jessica", "location" -> "San Francisco")
    db.put("Jessica", jessicaObject)

Get the object back. **tam** will return to you the JSON.

    db.get("Jessica")
    >> {"name":"Jessica","location":"San Francisco"}

When we're done, close our database and its environment.

    db.close()


license
----------

Copyright (c) 2011 Ted Nyman

Published under the BSD License, see LICENSE
