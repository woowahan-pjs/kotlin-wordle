package domain

enum class Color(val colorBox: String) {
    GREEN("🟩"),
    YELLOW("🟨"),
    GREY("⬜");

    override fun toString(): String = colorBox
}
