package study.dsl

import study.dsl.language.Languages
import study.dsl.skill.Skills

data class Person(
    val name: String, val company: String,
    val skills: Skills, val languages: Languages
)