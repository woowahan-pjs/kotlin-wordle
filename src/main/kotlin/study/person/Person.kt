package study.person

import study.language.Languages
import study.skill.Skills

data class Person(val name: String, val company: String, val skills: Skills, val languages: Languages)
