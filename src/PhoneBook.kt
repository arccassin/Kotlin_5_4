import java.util.*
import kotlin.collections.HashSet

/**
 * Created by User on 08 Янв., 2020
 */
internal class PhoneBook {
    val hashMapKeyPhone = HashMap<String, String>()  //key - phone,   value - account
    val treeMapKeyAccount = TreeMap<String, String>() //key - account, value - phone

    fun getAccountKeyPhone(phone: String): String? {
        return hashMapKeyPhone.get(phone)
    }

    fun getPhoneKeyAccount(account: String): String? {
      return treeMapKeyAccount.get(account)
    }

    fun getList(): String {
        val builder = StringBuilder()
        var inc = 1
        for (map in treeMapKeyAccount) {
            builder.append(phoneBookRecordToString(map.key, map.value, inc++))
        }
        return builder.toString()
    }

    fun phoneBookRecordToString(account: String?, phone: String?, index: Int = -1): String{
        if (index > 0) {
            return String.format("%2d - %30s: %s\n", index, account, phone)
        } else{
            return String.format("\n%s: %s", account, phone)
        }
    }

    fun addPhoneBookRecord(account: String?, phone: String?){
        if ((account != null) and (phone != null)){
        hashMapKeyPhone.put(phone!!, account!!)
        treeMapKeyAccount.put(account!!, phone!!)}
    }
}