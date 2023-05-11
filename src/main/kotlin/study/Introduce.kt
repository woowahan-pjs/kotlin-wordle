package study

import study.person.Person
import study.person.PersonBuilder

fun introduce(builder: PersonBuilder.() -> Unit): Person {
    return PersonBuilder().apply(builder).build()
}
