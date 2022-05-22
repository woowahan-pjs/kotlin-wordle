package study

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun `페퍼 자기소개 프러퍼티 버전`() {
        val profile = introduce {
            name("최수연")
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
        assertThat(profile.name).isEqualTo("최수연")
        assertThat(profile.company).isEqualTo("우아한테크코스")
        assertThat(profile.skills.soft).contains("A passion for problem solving", "Good communication skills")
        assertThat(profile.skills.hard).contains("Kotlin")
        assertThat(profile.languages.languages).extracting("type", "level")
            .contains(
            Tuple.tuple("Korean", 5),
            Tuple.tuple("English", 3)
        )
    }
}

fun introduce(builder: ProfileBuilder.() -> Unit): Profile {
    return ProfileBuilder().apply(builder).build()
}

class ProfileBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(skillValue: Skills.() -> Unit) {
        skills = Skills(mutableListOf(), mutableListOf()).apply(skillValue)
    }

    fun languages(languageSkill: Languages.() -> Unit) {
        languages = Languages(mutableListOf()).apply(languageSkill)
    }

    fun build(): Profile {
        return Profile(name, company, skills, languages)
    }
}

data class Profile(val name: String, val company: String, val skills: Skills, val languages: Languages)

data class Skills(val hard: MutableList<String>, val soft: MutableList<String>) {
    fun hard(value: String) {
        hard.add(value)
    }

    fun soft(value: String) {
        soft.add(value)
    }
}

data class Languages(val languages: MutableList<Language>) {
    infix fun String.level(value: Int) = languages.add(Language(this, value))
}

data class Language(val type: String, val level: Int)
