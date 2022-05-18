package study.person

import study.language.Language
import study.skill.Skill

data class Person(
    val name: String,
    val company: String,
    val skills: MutableList<Skill>,
    val languages: MutableList<Language>
)
