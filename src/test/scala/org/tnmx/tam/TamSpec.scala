package org.tnmx.tam
import com.codahale.simplespec.Spec

class TamSpec extends Spec {
  class `A TamDatabase` {
    def `can be opened, closed, and re-opened` {
      val t = TamDatabase.open()
      t.put("foo", List[Int](1,2,3))
      t.close()

      val t2 = TamDatabase.open()
      t2.get("foo") must beEqualTo("[1,2,3]")
    }
  }

  class `Putting a key to the database` {
    def `can write a JSON list to the database` {
      val t = TamDatabase.open()
      t.put("little_list", List[String]("Churchill", "Winslow", "Times"))
      t.get("little_list") must beEqualTo("""["Churchill","Winslow","Times"]""")
      t.close()
    }

    def `can write a JSON hash to the database` {
      val t = TamDatabase.open()
      t.put("little_map", Map[String, Int]("Jane" -> 300, "Tom" -> 200))
      t.get("little_map") must beEqualTo("""{"Jane":300,"Tom":200}""")
      t.close()
    }

    def `can serialize and write more complex JSON to the database` {
      val t= TamDatabase.open()
      t.put("complex_json", List[Map[String, Any]](Map("name" -> "Jess", "place" -> "San Francisco", "age" -> 28),
                                                   Map("name" -> "Stacy", "place" -> "San Diego", "age" -> 27)))
      t.get("complex_json") must beEqualTo("""[{"name":"Jess","place":"San Francisco","age":28},{"name":"Stacy","place":"San Diego","age":27}]""")
      t.close()
    }
  }

  class `Getting a key from the database` {
    def `returns the relevant value as JSON for an existent key` {
      val t = TamDatabase.open()
      t.put("a_simple_map", Map[String, String]("foo" -> "bar", "name" -> "Smith"))
      t.get("a_simple_map") must beEqualTo("""{"foo":"bar","name":"Smith"}""")
      t.close()
    }

    def `returns complex JSON from the database with an existent key` {
      val t = TamDatabase.open()
      t.put("very_complex_json", List(Map("place" -> List(Map("famous" -> "sure"),
                                                          Map("name"   -> "Waterloo"))),
                                      Map("more"  -> List(Map("details"   -> 300),
                                                          Map("something" -> "anything?")))))
      t.get("very_complex_json") must beEqualTo("""[{"place":[{"famous":"sure"},{"name":"Waterloo"}]},{"more":[{"details":300},{"something":"anything?"}]}]""")
    }

    def `throws a TamException if the specified key doesn't exist` {
      val t = TamDatabase.open()
      t.get("nothingness") must throwA[TamException]
    }
  }
}