/**
 * Created by User on 08 Янв., 2020
 */

internal class Controller {
    val commandList = "LIST"
    private var status: InputStatus = InputStatus.WAIT_NEW_INPUT
    private val phoneBook: PhoneBook
    private var accountWaitedValue: String? = null
    private var phoneWaitedValue: String? = null

    constructor(phoneBook: PhoneBook) {
        this.phoneBook = phoneBook
    }

    fun read(input: String?){
        if (input == null){
            return
        }
        when(status){
            InputStatus.WAIT_NEW_INPUT -> {
                if (input.equals(commandList)){
                        println(phoneBook.getList())
                } else {
                    val phoneNumber = FormatPhoneNumber.formatPhone(input)
                    //если это телефон
                    if (phoneNumber != null){
                        val account = phoneBook.getAccountKeyPhone(phoneNumber)
                        if (account == null){
                            status = InputStatus.WAIT_ACCOUNT
                            phoneWaitedValue = phoneNumber
                            println("Новый телефон, введите имя контакта")
                        } else {
                            println("Запись с таким телефоном уже есть:")
                            println(phoneBook.phoneBookRecordToString(account, phoneNumber))
                        }
                    } else {
                        val account = input
                        val phone = phoneBook.getPhoneKeyAccount(account);
                        if (phone == null){
                            status = InputStatus.WAIT_PHONE
                            accountWaitedValue = account
                            println("Новое имя, введите телефон")
                        } else{
                            println("Запись с таким именем уже есть:")
                            println(phoneBook.phoneBookRecordToString(account, phone))
                        }
                    }
                }
            }
            InputStatus.WAIT_ACCOUNT -> {
                val account = input
                phoneBook.addPhoneBookRecord(account, phoneWaitedValue);
                println(phoneBook.phoneBookRecordToString(account, phoneWaitedValue))
                println("Новая запись добавлена")
                status = InputStatus.WAIT_NEW_INPUT
            }
            InputStatus.WAIT_PHONE -> {
                val phone = FormatPhoneNumber.formatPhone(input)
                if (phone == null){
                    println("\"$input\" - Неверный формат номера телефона")
                    status = InputStatus.WAIT_NEW_INPUT
                    return
                }
                phoneBook.addPhoneBookRecord(accountWaitedValue, phone);
                println(phoneBook.phoneBookRecordToString(accountWaitedValue, phone))
                println("Новая запись добавлена")
                status = InputStatus.WAIT_NEW_INPUT
            }
        }
    }
}

enum class InputStatus{
    WAIT_PHONE, WAIT_ACCOUNT, WAIT_NEW_INPUT
}