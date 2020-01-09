/**
 * Created by User on 08 Янв., 2020
 */

internal object FormatPhoneNumber {
    fun formatPhone(input: String?): String? {
        var input: String? = input ?: return null
        input = input!!.replace("\\D".toRegex(), "")
        if (input.length == 0) {
            return null
        }
        val builder = StringBuilder()
        if (input.length > 8 && input.length < 12) {
            if (input.length == 10) {
                input = "7$input"
            }
            if (!(input[0] != '7' && input[0] != '8')) {
                builder.append("+ 7 ")
                for (i in 1 until input.length) {

                    if (i == 4) {
                        builder.append(" ")
                    }
                    if (i == 7 || i == 9) {
                        builder.append("-")
                    }
                    builder.append(input[i])
                }
            }
        } else {
            return null
        }
        return builder.toString()
    }

}