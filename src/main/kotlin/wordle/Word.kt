package wordle

data class Word(private val word: String) {

    init {
        require(word.length == 5) { "단어의 길이는 5글자여야 합니다." }
        require(Regex("[a-zA-Z]*").matches(word)) { "단어에 영어가 아닌 글자나 공백이 포함될 수 없습니다." }
        // 게임이 시작되면 한번에 word.text를 가져와서 읽어온 다음에 비교하는게 낫다
        // 매번 읽는거 -> 비효율적
        // Words List<Word> <- words.text
    }
}
