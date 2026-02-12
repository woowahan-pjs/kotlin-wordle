package study.skill

class SkillBuilder {

    var skills: MutableList<Skill> = mutableListOf()

    fun soft(value: String) {
        skills.add(SoftSkill(value))
    }

    fun hard(value: String) {
        skills.add(HardSkill(value))
    }

    fun build(): MutableList<Skill> {
        return skills
    }
}
