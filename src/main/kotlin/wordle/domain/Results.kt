package wordle.domain

/**
 * Wordle 한 판에 대한 결과 목록을 관리하는 객체입니다.
 */
@JvmInline
value class Results(
    val values: List<Result>,
) : Iterable<Result> {
    /**
     * 정답 여부를 확인합니다.
     *
     * @return 정답 여부
     */
    fun isAnswer(): Boolean = values.all { it == Result.CORRECT }

    override fun iterator(): Iterator<Result> = values.iterator()
}
