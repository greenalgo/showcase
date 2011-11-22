package com.green.ecom.service.inventory.metainformation

import xml.Node

/**
 * Created by IntelliJ IDEA.
 * User: gaurav
 * Date: 21/08/11
 * Time: 11:39 PM
 * To change this template use File | Settings | File Templates.
 */

object XmlTry {
  var root = <model/>

    def main(args: Array[String]) {
      println("Hello, world!")
      var a = <table />
      (<td />)++ a;
      println(a.toString())
      addToModel(<subsection>content</subsection>)
       addToModel(<subsection>content</subsection>)
      println (root.toString)

    }

def addToModel(child:Node) = {
  root = root match {
    case <model>{children@ _*}</model> => <model>{children ++ child}</model>
    case other => other
  }
}

  }