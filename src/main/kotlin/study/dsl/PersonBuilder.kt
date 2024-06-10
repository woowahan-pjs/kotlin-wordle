package study.dsl

import study.dsl.language.Languages
import study.dsl.language.LanguagesBuilder
import study.dsl.skill.Skills
import study.dsl.skill.SkillsBuilder

class PersonBuilder {
    private lateinit var name: String
    private lateinit var company: String
    private lateinit var skills: Skills
    private lateinit var languages: Languages

    fun build(): Person {
        return Person(name, company, skills, languages)
    }

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(builder: SkillsBuilder.() -> Unit) {
        skills = SkillsBuilder().apply(builder).build()
    }

    fun languages(builder: LanguagesBuilder.() -> Unit) {
        languages = LanguagesBuilder().apply(builder).build()
    }
}
