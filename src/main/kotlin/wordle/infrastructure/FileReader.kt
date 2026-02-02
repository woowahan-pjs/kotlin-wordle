package wordle.infrastructure

import java.io.File

object FileReader {
    fun read(name: String): List<String> {
        val file = readFile(name)
        val list = mutableListOf<String>()
        file.forEachLine {
            list.add(it.trim())
        }
        return list
    }

    private fun readFile(name: String): File {
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource(name) ?: throw IllegalArgumentException("파일이 존재하지 않습니다: $name")
        val result = File(resource.file)
        return result
    }
}
