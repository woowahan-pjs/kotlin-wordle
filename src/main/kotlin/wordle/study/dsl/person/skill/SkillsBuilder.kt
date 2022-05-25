package wordle.study.dsl.person.skill

class SkillsBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(Soft(value))
    }

    fun hard(value: String) {
        skills.add(Hard(value))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
