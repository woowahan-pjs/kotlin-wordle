package study

import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import study.language.Language
import study.skill.HardSkill
import study.skill.SoftSkill

class Introduce {

    @Test
    fun `자기소개`() {
        val person = introduce {
            name("제로")
            company("우아한테크코스")
            skills {
                soft("A passion for problem solving")
                soft("common communication skills")
                hard("Kotlin")
            }
            languages {
                "Korean" level 2
                "English" level 1
            }
        }

        person.name shouldBe "제로"
        person.company shouldBe "우아한테크코스"
        person.skills shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("common communication skills"),
            HardSkill("Kotlin")
        )
        person.languages shouldContainExactly listOf(
            Language("Korean", 2),
            Language("English", 1)
        )
    }
}
