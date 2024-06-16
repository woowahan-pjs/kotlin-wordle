import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File
import java.time.LocalDate
import java.time.Period

class DictionaryTest {

  @ParameterizedTest
  @ValueSource(strings = ["asder", "wrfdx", "bljwq"])
  fun `단어가 존재하지 않으면 false`(word: String) {
    //when
    val result = Dictionary.hasWord(word)

    //then
    assertThat(result).isFalse()
  }

  @ParameterizedTest
  @ValueSource(strings = ["hello", "organ", "mercy"])
  fun `단어가 존재하면 true`(word: String) {
    //when
    val result = Dictionary.hasWord(word)

    //then
    assertThat(result).isTrue()
  }

  @Test
  fun `현재날짜 기준으로 단어를 추출한다`() {
    val now = LocalDate.now()
    val findTodayWord = Dictionary.findTodayWord(now)
    //then
    assertThat(findTodayWord).isNotNull()
  }
}

object Dictionary {
  private const val PATH = "./src/main/resources"
  private const val FILE_NAME = "words.txt"
  private val words: List<String> = File(PATH, FILE_NAME).readLines()
  private val BASE_DATE = LocalDate.of(2021, 6, 19)
  fun hasWord(word: String): Boolean {
    return words.contains(word)
  }

  operator fun get(index: Int): String {
    return words[index]
  }

  fun findTodayWord(nowDate: LocalDate): String {
    val calcDate = nowDate.toEpochDay().minus(BASE_DATE.toEpochDay())
    val index: Int = (calcDate % words.size).toInt()
    return words[index]
  }
}