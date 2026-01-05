package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import study.dsl.Person
import study.dsl.PersonBuilder
import study.dsl.language.Language
import study.dsl.skill.Hard
import study.dsl.skill.Soft

class DslTest {
    @Test
    @DisplayName("함수 버전으로 나를 소개한다.")
    fun intro() {
        var person = introduce {
            name("kth990303")
            company("wooteco")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }
        person.name shouldBe "kth990303"
        person.company shouldBe "wooteco"
        person.skills.skills shouldContainExactly listOf(
            Soft("A passion for problem solving"),
            Soft("Good communication skills"),
            Hard("Kotlin")
        )
        person.languages.languages shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
}

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}
