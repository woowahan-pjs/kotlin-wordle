package wordle.study.person

import wordle.study.person.language.Languages
import wordle.study.person.skill.Skills

data class Person(
    val name: String,
    val company: String,
    val skills: Skills,
    val languages: Languages
)
