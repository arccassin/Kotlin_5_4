/**
 * Created by User on 08 Янв., 2020
 */

internal class Controller {
    val commandList = "LIST"
    private val phoneBook: PhoneBook

    constructor(phoneBook: PhoneBook) {
        this.phoneBook = phoneBook
    }

    fun read(input: String?){
        if (input == null){
            return
        }

        if (input.equals(commandList)){
            println(phoneBook.getList())
            return
        }
        val phone = FormatPhoneNumber.formatPhone(input)
        //this is phone
        if (phone != null){
            var account = phoneBook.getAccountKeyPhone(phone)
            if (account == null){
                println("Новый телефон, введите имя контакта")
                account = readLine()
                phoneBook.addPhoneBookRecord(account, phone);
                println(phoneBook.phoneBookRecordToString(account, phone))
                println("Новая запись добавлена")
            } else {
                println("Запись с таким телефоном уже есть:")
                println(phoneBook.phoneBookRecordToString(account, phone))
            }
        } else {
            val account = input
            var phone = phoneBook.getPhoneKeyAccount(account)
            if (phone == null){
                println("Новое имя, введите телефон")
                while (true) {
                    val inline = readLine()
                    phone = FormatPhoneNumber.formatPhone(inline)
                    if (phone == null) {
                        println("\"$inline\" - Неверный формат номера телефона, попробуйте еще раз")
                        continue
                    } else {
                        break;
                    }
                }
                println(phoneBook.phoneBookRecordToString(account, phone))
                println("Новая запись добавлена")
            } else {
                println("Запись с таким именем уже есть:")
                println(phoneBook.phoneBookRecordToString(account, phone))
            }
        }
    }
}
