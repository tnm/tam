package org.tnmx.tam

import java.io.File
import com.sleepycat.je._
import java.lang.Boolean

trait DatabaseInterface {
  /**
   * Make full directory path for this database
   *
   * @param dbPath  the full path for this database
   */
  private def makeDirs(dbPath: String, createDir: Boolean): File = {
    val file = new File(dbPath)
    if (createDir == true) file.mkdirs()
    file
  }

  /**
   * Set and return an environment config
   */
  private def returnEnvConfig(create: Boolean) = {
    val envConfig = new EnvironmentConfig()
    envConfig.setAllowCreate(create)
    envConfig
  }

  /**
   * Set and return a database config
   */
  private def returnDbConfig(create: Boolean) = {
    val dbConfig = new DatabaseConfig
    dbConfig.setAllowCreate(create)
    dbConfig.setTemporary(false)
    dbConfig
  }

  /**
   * Setup database
   */
  private def setupDb(dbPath: String, dbName: String, create: Boolean = true) = {
    val envConfig = returnEnvConfig(create)
    val dbConfig  = returnDbConfig(create)
    val file = makeDirs(dbPath, true)
    val env = new Environment(file, envConfig)
    val db = env.openDatabase(null, dbName, dbConfig)
    db
  }

  /**
   * Return a BDB database object, creating one if it doesn't already exist
   *
   * @param dbPath  the file path for this database
   * @param dbName  the name of this database
   * @return        a Database object
   */
  private[tam] def getDatabase(dbPath: String, dbName: String): Database = {
    setupDb(dbPath, dbName)
  }
}

