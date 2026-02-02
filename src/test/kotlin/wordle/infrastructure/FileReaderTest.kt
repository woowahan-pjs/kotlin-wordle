package wordle.infrastructure

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class FileReaderTest {
    @Test
    @DisplayName("읽어온 파일을 List<String> 형식으로 반환한다")
    fun test01() {
        // arrange
        val name = "testWord.txt"

        // act
        val sut = FileReader.read(name)

        // assert
        assertSoftly {
            it.assertThat(sut).size().isEqualTo(2)
            it.assertThat(sut).containsExactlyInAnyOrder("zin", "devlife")
        }
    }

    @Test
    @DisplayName("전달받은 파일 이름이 존재하지 않으면 에러가 발생한다")
    fun test02() {
        // arrange
        val name = "notExistFile.txt"

        // act & assert
        assertThatThrownBy { FileReader.read(name) }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
