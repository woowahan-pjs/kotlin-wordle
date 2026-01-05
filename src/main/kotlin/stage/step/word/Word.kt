package stage.step.word

import exception.WordleException

data class Word(val value: String) {
  companion object {
    private val englishRegex = Regex("^[A-Za-z]*")
    private fun validateInput(input: String) {
      if (!input.matches(englishRegex)) {
        throw WordleException("영문만 입력해야합니다.")
      }
      if (input.length != 5) {
        throw WordleException("5글자여야 합니다.")
      }
    }

    private fun checkInDict(isWordInDic: Boolean) {
      if (!isWordInDic) {
        throw WordleException("존재하지 않는 단어입니다.")
      }
    }

    fun fromInput(input: String, dicPolicy: (s: String) -> Boolean = { _ -> true }): Word {
      validateInput(input)
      val word = input.lowercase()
      checkInDict(dicPolicy(word))
      return Word(word)
    }
  }
}
