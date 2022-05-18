package study

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

/*
introduce {
  name("박재성")
  company("우아한형제들")
  skills {
    soft ("A passion for problem solving")
    soft ("Good communication skills")
    hard ("Kotlin")
  }
  languages {
    "Korean" level 5
    "English" level 3
  }
}
 */
class DslTest {

    @Test
    fun `자기소개 함수 버전`() {
        val person = introduce {
            name("김범수")
            company("우아한형제들")
            skills {
                soft("코틀린")
                soft("자바")
                hard("운동")
            }
            languages {
                "Korean" level 100
                "English" level 10
                "Japanese" level 0
            }
        }

        assertSoftly(person) {
            it.name shouldBe "김범수"
            it.company shouldBe "우아한형제들"
            it.skills.soft shouldHaveSize 2
            it.skills.hard shouldHaveSize 1
            it.languages.values shouldHaveSize 3
            it.languages.values shouldContainExactly listOf(
                Language("Korean", 100),
                Language("English", 10),
                Language("Japanese", 0)
            )
        }
    }
}

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}

class PersonBuilder {
    lateinit var name: String
    lateinit var company: String
    lateinit var skills: Skills
    lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(builder: Skills.() -> Unit) {
        skills = Skills(mutableListOf(), mutableListOf()).apply(builder)
    }

    fun languages(builder: Languages.() -> Unit) {
        languages = Languages(mutableListOf()).apply(builder)
    }

    fun build(): Person = Person(name, company, skills, languages)
}

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)

class Skills(val soft: MutableList<String>, val hard: MutableList<String>) {
    fun soft(value: String) {
        soft.add(value)
    }

    fun hard(value: String) {
        hard.add(value)
    }
}

class Languages(val values: MutableList<Language>) {

    infix fun String.level(level: Int) {
        values.add(Language(this, level))
    }
}

data class Language(val value: String, val level: Int)



