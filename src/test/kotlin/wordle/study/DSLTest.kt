package wordle.study

import org.junit.jupiter.api.Test

class DSLTest {
    @Test
    fun test() {
        val introduce = introduce {
            name("이찬주")
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
        println(introduce)
    }
}

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}

class PersonBuilder {
    lateinit var name: String
    lateinit var company: String
    lateinit var skills: List<Skill>
    lateinit var languages: List<Language>

    fun name(value: String) {
        this.name = value
    }

    fun company(value: String) {
        this.company = value
    }

    fun skills(skillsBuilder: SkillsBuilder.() -> Unit) {
        this.skills = SkillsBuilder().apply(skillsBuilder).build()
    }

    fun languages(languagesBuilder: LanguagesBuilder.() -> Unit) {
        this.languages = LanguagesBuilder().apply(languagesBuilder).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}

class SkillsBuilder {
    var skills: MutableList<Skill> = mutableListOf()

    fun soft(description: String) {
        skills.add(Skill("soft", description))
    }

    fun hard(description: String) {
        skills.add(Skill("hard", description))
    }

    fun build(): List<Skill> {
        return skills
    }
}

class LanguagesBuilder {
    var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(value: Int) {
        languages.add(Language(this, value))
    }

    fun build(): List<Language> {
        return languages
    }
}

data class Person(val name: String, val company: String, val skills: List<Skill>, val languages: List<Language>)

data class Language(val lang: String, val level: Int)

data class Skill(val type: String, val description: String)
