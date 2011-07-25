package org.tnmx.tam

import com.sleepycat.je._
import com.codahale.logula.Logging

object TamDatabase {
  def open(dbName: String = "default",
           dbPath: String = "default"): TamDatabase = {
    new TamDatabase(dbName, dbPath)
  }
}

class TamDatabase(dbName: String , dbPath: String) {
  private val tam = new TamInterface(dbPath, dbName)

  /**
   * At a key of type A, serialize and put a Scala object into
   * BDB as JSON
   *
   * @param key    the key for this object
   * @param value  the value for this object
   */
  def put[A](key: String, value: A) {
    tam.put(key, value)
  }

  /**
   * Get the JSON value of a given key
   *
   * @param key   the key for this object
   * @return      the value of this key
   */
  def get(key: String): String = {
    try {
      tam.get(key)
    } catch {
      case e: NullPointerException => throw TamException(e)
    }
  }

  def close() {
    tam.close()
  }
}


