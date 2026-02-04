package study.skill

class SkillsBuilder {

    private val skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): Skills {
        return Skills(skills)
    }
}
