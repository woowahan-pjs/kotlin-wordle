package view

import java.util.*

class Input {
  companion object {
    val scanner = Scanner(System.`in`)

    fun write(): String {
      return scanner.nextLine()
    }
  }
}