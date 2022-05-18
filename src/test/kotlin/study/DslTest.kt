package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun introduce() {
        val person = introduce {
            name("박재성")
            company("우아한형제들")
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

        assertThat(person.toString()).isEqualTo(
            "Person(name=박재성, company=우아한형제들, " +
                    "skills=Skills(softSkills=[A passion for problem solving, Good communication skills], hardSkills=[Kotlin]), " +
                    "languages=Languages(languages=[Korean level : 5, English level : 3]))")
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
    var languages: MutableList<String> = mutableListOf()

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


