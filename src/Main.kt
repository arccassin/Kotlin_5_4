import java.util.*
import kotlin.collections.HashSet

/**
 * Created by User on 08 Янв., 2020
 */

fun main(args: Array<String>) {
    val commandExit = "exit"
    val controller = Controller(phoneBook = PhoneBook())
    do {
        val inline = readLine()
        if (inline.equals(commandExit)){
            break;
        }
        controller.read(inline)
    } while (!inline.equals(commandExit) or (inline == null))
}

