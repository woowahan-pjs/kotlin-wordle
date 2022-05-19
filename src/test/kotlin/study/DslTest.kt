package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import wordle.study.person.Person
import wordle.study.person.PersonBuilder
import wordle.study.person.language.Language
import wordle.study.person.skill.Hard
import wordle.study.person.skill.Soft

class DslTest {
    @Test
    fun `자기소개 테스트`() {
        val person = introduce {
            name("ASH")
            company("우아한 형제들")
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

        assertThat(person.name).isEqualTo("ASH")
        assertThat(person.company).isEqualTo("우아한 형제들")
        assertThat(person.skills.skills)
            .hasSize(3)
            .contains(
                Soft("A passion for problem solving"),
                Soft("Good communication skills"),
                Hard("Kotlin")
            )
        assertThat(person.languages.languages)
            .hasSize(2)
            .contains(
                Language("Korean", 5),
                Language("English", 3)
            )
    }
}

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}
