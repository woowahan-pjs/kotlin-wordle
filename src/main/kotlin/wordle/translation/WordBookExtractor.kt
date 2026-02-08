package wordle.translation

import wordle.domain.AnswerWordValidator
import wordle.domain.WordBook
import wordle.io.FileReader

/**
 * 파일로부터 단어장을 추출합니다.
 */
object WordBookExtractor {
    /**
     * [FileReader]를 이용해
     * 파일을 읽어 단어장 객체로 반환합니다.
     *
     * @param fileName 단어장으로 만들기 위한 파일명
     * @return [WordBook] 단어장
     */
    fun extract(fileName: String): WordBook = WordBook.from(AnswerWordValidator(), FileReader.read(fileName))
}
