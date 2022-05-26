package study.data

import org.junit.jupiter.api.Test

class DataClassTest {
    @Test
    fun getter() {
        val noDataClass = NoDataClass("NoDataClass")
        val dataClass = DataClass("DataClass")

        println(noDataClass.name)
        println(dataClass.name)
    }
}

class NoDataClass(val name: String) {
}


data class DataClass(val name:String) {
}

