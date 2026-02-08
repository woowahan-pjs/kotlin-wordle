package wordle.io

import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.ValueSource

class FileReaderTest {
    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = [" "])
    @DisplayName("파일을 읽을 때, 파일 이름이 빈 값의 경우 예외가 발생합니다.")
    fun test01(name: String) {
        // act & assert
        Assertions
            .assertThatThrownBy { FileReader.read(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("파일명을 입력해주세요.")
    }

    @Test
    @DisplayName("전달받은 파일 이름이 존재하지 않으면 예외가 발생합니다.")
    fun test02() {
        // arrange
        val name = "notExistFile.txt"

        // act & assert
        Assertions
            .assertThatThrownBy { FileReader.read(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("파일이 존재하지 않습니다.")
    }

    @Test
    @DisplayName("읽어온 파일을 List<String> 형식으로 반환합니다.")
    fun test03() {
        // arrange
        val name = "testWord.txt"

        // act
        val sut = FileReader.read(name)

        // assert
        assertSoftly {
            it.assertThat(sut).size().isEqualTo(2)
            it.assertThat(sut).containsExactlyInAnyOrder("zinzo", "devli")
        }
    }
}
