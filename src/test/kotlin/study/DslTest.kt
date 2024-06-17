package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person = introduce {
            name("조조그린")
            company("우아한테크코스")
            skills {
                soft("A passion for problem solving")
                soft("Good communication skills")
                hard("Java")
            }
            languages {
                "Korean" level 5
                "English" level 3
            }
        }

        assertThat(person.toString()).isEqualTo("Person(name=조조그린, company=우아한테크코스, skills=Skills(softSkills=[A passion for problem solving, Good communication skills], hardSkills=[Java]), languages=Languages(languages=[Korean level : 5, English level : 3]))")
    }
}

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(input: String) {
        name = input
    }

    fun company(input: String) {
        company = input
    }

    fun skills(builder: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(builder).build()
    }

    fun languages(builder: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(builder).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    private val softSkills: MutableList<String> = mutableListOf()
    private val hardSkills: MutableList<String> = mutableListOf()

    fun soft(input: String) {
        this.softSkills.add(input)
    }

    fun hard(input: String) {
        this.hardSkills.add(input)
    }

    fun build(): Skills {
        return Skills(softSkills, hardSkills)
    }
}

class LanguagesBuilder {
    private var languages: MutableList<String> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(plus(" level : ") + value)
    }

    fun build(): Languages {
        return Languages(languages)
    }
}

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)

data class Skills(val softSkills: List<String>, val hardSkills: List<String>)

data class Languages(val languages: List<String>)
