package wordle.study.dsl.person

import wordle.study.dsl.person.language.Languages
import wordle.study.dsl.person.language.LanguagesBuilder
import wordle.study.dsl.person.skill.Skills
import wordle.study.dsl.person.skill.SkillsBuilder

class PersonBuilder {
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
