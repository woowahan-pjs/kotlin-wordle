package study.person

import study.language.Language
import study.language.LanguageBuilder
import study.skill.Skill
import study.skill.SkillBuilder

class PersonBuilder {

    lateinit var name: String
    lateinit var company: String
    lateinit var skills: MutableList<Skill>
    lateinit var languages: MutableList<Language>

    fun name(value: String) {
        name = value
    }

    fun company(value: String) {
        company = value
    }

    fun skills(builder: SkillBuilder.() -> Unit) {
        skills = SkillBuilder().apply(builder).build()
    }

    fun languages(builder: LanguageBuilder.() -> Unit) {
        languages = LanguageBuilder().apply(builder).build()
    }

    fun build(): Person {
        return Person(name, company, skills, languages)
    }
}
