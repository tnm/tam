package org.tnmx.tam

import com.sleepycat.je._
import com.codahale.logula.Logging

object TamDatabase {
  /**
   * Opens a BDB database with a given name and file path
   *
   * @param dbName   the name of this database
   * @param dbPath   the path to this database
   */
  def open(dbName: String = "default",
           dbPath: String = "default"): TamDatabase = {
    new TamDatabase(dbName, dbPath)
  }
}

/**
 * A class representing actions against a BDB database.
 */
class TamDatabase(dbName: String , dbPath: String) {
  private val tam = new TamInterface(dbPath, dbName)

  /**
   * Serializes a Scala object of type A into JSON, and inserts it into
   * a BDB database at the key specified by `key`.
   *
   * @param key    the key for this object
   * @param value  the value for this object
   */
  def put[A](key: String, value: A) {
    tam.put(key, value)
  }

  /**
   * Returns the JSON string value of the key specified by `key.
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

  /**
   * Closes the database and environement for this BDB database
   */
  def close() {
    tam.close()
  }
}


