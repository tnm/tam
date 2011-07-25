package org.tnmx.tam

import java.lang.String

import com.sleepycat.je._

import com.codahale.logula.Logging
import com.codahale.jerkson.Json._

class TamInterface(dbPath: String, dbName: String) extends DatabaseInterface {
  private val db = getDatabase(dbPath, dbName)

  private[tam] def put[A](key: String, value: A) {
    val k = key.getBytes
    val v = generate[A](value).getBytes
    db.put(null, new DatabaseEntry(k), new DatabaseEntry(v))
  }

  private[tam] def get(key: String): String = {
    val k = key.getBytes
    val res = new DatabaseEntry()
    db.get(null, new DatabaseEntry(k), res, null)
    new String(res.getData)
  }

  private[tam] def close() {
    db.close()
    val env = db.getEnvironment()
    env.close
  }
}