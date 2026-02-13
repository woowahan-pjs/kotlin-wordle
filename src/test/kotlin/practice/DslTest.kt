package practice

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class DslTest {

    @Test
    fun `자기소개 프러퍼티 버전`() {
        val person = introduce {
            name = "최수연"
            age = 26
        }
        assertThat(person.name).isEqualTo("최수연")
        assertThat(person.age).isEqualTo(26)
    }

    @Test
    fun `자기소개 함수 버전`() {
        val person = introduce {
            name("최수연")
            age(26)
        }
        assertThat(person.name).isEqualTo("최수연")
        assertThat(person.age).isEqualTo(26)
    }
}

private fun introduce(builder: PersonBuilder.() -> Unit): Person {
// 람다에서 함수 형식을 지정가능
    return PersonBuilder().apply(builder).build()
}

class PersonBuilder {
    lateinit var name: String
    var age: Int = 0

    fun name(value: String) {
        name = value
    }

    fun age(value: Int) {
        age = value
    }

    fun build(): Person {
        return Person(name, age)
    }
}

data class Person(val name: String, val age: Int)
