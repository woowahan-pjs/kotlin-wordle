package study.person

import study.language.Languages
import study.language.LanguagesBuilder
import study.skill.Skills
import study.skill.SkillsBuilder

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
