package stage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * stage는 answer을 입력받아서 생성된다
 * stage를 play 할 때 마다 step이 만들어짐
 * play를 할 때 step이 isCorrect -> true이면 맞춘 종료
 * play를 6번 했는데 모든 step이 isCorrect -> false면 틀린 종료
 *
 * stage 상태: 진행중(Progress)/맞춘 종료(Complete)/틀린 종료(Fail) ->
 * 워들 -> 정답: xxx (틀린여부)
 * */
class StageTest {

  @Test
  fun `스테이지가 Progress일 때 play할 수 있음`() {
    val stage = Stage("answer", Stage.State.PROGRESS)
    assertThat(stage.canPlay()).isTrue()
  }

  @Test
  fun `스테이지가 Complete일 때 더이상 play를 할 수 없음`() {
    val stage = Stage("answer", Stage.State.COMPLETE)
    assertThat(stage.canPlay()).isFalse()
    val a = 1
    
  }


  @Test
  fun `스테이지가 Fail일 때 더이상 play를 할 수 없음`() {
    val stage = Stage("answer", Stage.State.FAIL)
    assertThat(stage.canPlay()).isFalse()
  }

  @Test
  fun `스테이지가 play시 정답을 맞추면(step이 isCorrect) Complete`() {
    // given
    val stage = Stage("answer", Stage.State.PROGRESS)

    // when
    stage.play("answer")

    // then
    assertThat(stage.state).isEqualTo(Stage.State.COMPLETE)
  }

  @Test
  fun `스테이지가 play시 6번 모두 정답을 맞추지 못하면(step이 모두 isCorrect false) FAIL`() {
    // given
    val stage = Stage("answer", Stage.State.PROGRESS)

    // when
    stage.play("world")
    stage.play("world")
    stage.play("world")
    stage.play("world")
    stage.play("world")
    stage.play("world")

    // then
    assertThat(stage.state).isEqualTo(Stage.State.FAIL)
  }
}