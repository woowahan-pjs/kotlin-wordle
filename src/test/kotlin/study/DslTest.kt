package study

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import study.language.Language
import study.skill.HardSkill
import study.skill.SoftSkill

class DslTest : FunSpec({

    test("dsl introduce 만들어 보기") {
        val resume = introduce {
            name("이하은")
            company("우아한테크코스")
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

        resume.name shouldBe "이하은"
        resume.company shouldBe "우아한테크코스"
        resume.skills.values shouldContainExactly listOf(
            SoftSkill("A passion for problem solving"),
            SoftSkill("Good communication skills"),
            HardSkill("Kotlin")
        )
        resume.languages.values shouldContainExactly listOf(
            Language("Korean", 5),
            Language("English", 3)
        )
    }
})
