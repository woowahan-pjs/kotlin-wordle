package wordle

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import wordle.domain.Stage
import wordle.domain.Word

internal class StageTest {

    @Test
    fun `스테이지를 만들면 PROGRESS`() {
        val stage = Stage("hello")

        assertAll(
            { assertThat(stage.state).isEqualTo(Stage.State.PROGRESS) },
            { assertThat(stage.finished).isFalse() }
        )
    }

    @Test
    fun `스테이지가 play시 정답을 맞추면 Complete`() {
        // given
        val stage = Stage("hello")

        // when
        val newStage = stage.play(Word("hello"))

        // then
        assertAll(
            { assertThat(newStage.state).isEqualTo(Stage.State.COMPLETE) },
            { assertThat(newStage.finished).isTrue() }
        )
    }

    @Test
    fun `스테이지가 play시 정답을 맞추지 못하면 Complete`() {
        // given
        val stage = Stage("hello")

        // when
        val newStage = stage.play(Word("wrong"))

        // then
        assertAll(
            { assertThat(newStage.state).isEqualTo(Stage.State.PROGRESS) },
            { assertThat(newStage.finished).isFalse() }
        )
    }

    @Test
    fun `스테이지가 play시 6번 모두 정답을 맞추지 못하면 FAIL`() {
        // given
        val stage = Stage("hello")

        //when
        val failedStage = stage
            .play(Word("wrong"))
            .play(Word("wrong"))
            .play(Word("wrong"))
            .play(Word("wrong"))
            .play(Word("wrong"))
            .play(Word("wrong"))

        // then
        assertAll(
            { assertThat(failedStage.state).isEqualTo(Stage.State.FAIL) },
            { assertThat(failedStage.finished).isTrue() }
        )
    }
}
