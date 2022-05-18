package study.dsl.skill

class SkillsBuilder {
    private var skills: MutableList<Skill> = mutableListOf()

    fun soft(name: String) {
        skills.add(Soft(name))
    }

    fun hard(name: String) {
        skills.add(Hard(name))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
