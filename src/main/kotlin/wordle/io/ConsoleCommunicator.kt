package wordle.io

class ConsoleCommunicator(
    private val printer: Printer,
    private val scanner: Scanner,
) : Communicator {
    override fun requestInput(): String {
        printer.requestInput()

        return scanner.input()
    }
}
