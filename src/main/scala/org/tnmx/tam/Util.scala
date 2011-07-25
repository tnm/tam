package org.tnmx.tam

object TamException {
  def apply(cause: NullPointerException): TamException = {
    val message = cause match {
      case e: NullPointerException => { "Key does not exist" }
    }
    new TamException(message, cause)
  }
}

class TamException(message: String, cause: Throwable) extends Exception(message, cause)
