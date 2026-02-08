package wordle.io

import java.io.File

/**
 * 파일명을 입력받아 classpath에 존재하는 파일을 읽어
 * 목록으로 반환합니다.
 */
object FileReader {
    /**
     * 파일 이름을 읽어 목록(List<String>)으로 반환합니다.
     *
     * 파일명은 필수입니다.
     *
     * @param name 읽을 파일명
     * @return 파일의 각 라인 리스트
     * @throws IllegalArgumentException 파일명이 비어 있거나 파일이 존재하지 않는 경우
     */
    fun read(name: String): List<String> {
        require(name.isNotBlank()) { "파일명을 입력해주세요." }

        return readFile(name)
            .readLines()
            .map { it.trim() }
    }

    /**
     * classpath에서 파일을 읽어 유효성 검증 후,
     * 이를 파일로 반환합니다.
     *
     * @param name 읽을 파일명
     * @return 읽어온 파일
     * @throws IllegalArgumentException classpath에 존재하지 않거나, 읽어온 파일이 존재하지 않는 경우
     */
    private fun readFile(name: String): File {
        val file: File = loadClasspathFile(name)

        requireExists(file)

        return file
    }

    /**
     * classpath에 파일을 읽어 파일로 반환합니다.
     *
     * @param name 읽을 파일명
     * @return 읽어온 파일
     * @throws IllegalArgumentException classpath에 존재하지 않는 경우
     */
    private fun loadClasspathFile(name: String): File {
        val classLoader = javaClass.classLoader
        val resource =
            requireNotNull(classLoader.getResource(name)) {
                NOT_EXIST_MESSAGE
            }

        return File(resource.file)
    }

    /**
     * 생성한 파일이 존재하는지 확인합니다.
     *
     * @param file classpath로 부터 생성한 파일
     * @throws IllegalArgumentException 생성한 파일이 존재하지 않는 경우
     */
    private fun requireExists(file: File) {
        require(file.exists()) {
            NOT_EXIST_MESSAGE
        }
    }

    const val NOT_EXIST_MESSAGE = "파일이 존재하지 않습니다."
}
