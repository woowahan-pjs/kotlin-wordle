package wordle.study.dsl.person

import wordle.study.dsl.person.language.Languages
import wordle.study.dsl.person.skill.Skills

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
)
