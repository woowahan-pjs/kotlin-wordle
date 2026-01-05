package exception

data class WordleException(override val message: String) : RuntimeException() {
}