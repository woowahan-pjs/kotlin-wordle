package study.language

class LanguageBuilder {

    var languages: MutableList<Language> = mutableListOf()

    infix fun String.level(level: Int) {
        languages.add(Language(this, level))
    }

    fun build(): MutableList<Language> {
        return languages
    }
}
