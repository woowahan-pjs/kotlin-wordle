package wordle.io

import wordle.domain.Round

/**
 * 값을 출력해주는 객체입니다.
 */
class ConsolePrinter : Printer {
    // TODO: 6번을 외부로부터 입력 받는 형식으로 수정필요
    /**
     * Wordle 게임 시작을 안내하는 인삿말을 출력합니다.
     */
    override fun introduce() {
        println(
            "WORDLE을 6번 만에 맞춰 보세요.\n" +
                "시도의 결과는 타일의 색 변화로 나타납니다.",
        )
    }

    /**
     * 값 입력을 요구하는 메세지를 출력합니다.
     */
    override fun requestInput() {
        println("정답을 입력해 주세요.")
    }

    /**
     * tile을 출력합니다.
     */
    override fun viewTile(tile: String) {
        println()
        println(tile)
        println()
    }

    /**
     * 최종 결과를 출력합니다.
     */
    override fun result(
        count: Int,
        round: Round,
        tile: String,
    ) {
        println("$round/$count")
        viewTile(tile)
    }

    /**
     * 에러 메세지를 출력합니다.
     */
    override fun error(message: String) {
        println(message)
    }
}
