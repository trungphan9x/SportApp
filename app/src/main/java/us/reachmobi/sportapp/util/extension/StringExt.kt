package us.reachmobi.sportapp.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.toReadableTimeWIB(dateString: String): String {
    var time: Date? = null
    if (dateString.contains("-"))
        time = if (this.contains("+"))
            SimpleDateFormat("yyyy-MM-dd HH:mm:ssz").parse("$dateString ${this}")
        else SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("$dateString ${this}")
    else if (dateString.contains("/"))
        time = if (this.contains("+"))
            SimpleDateFormat("yyyy/MM/dd HH:mm:ssz").parse("$dateString ${this}")
        else SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("$dateString ${this}")

    if (time == null) return "-"
    return SimpleDateFormat("EEEE, dd MMM yyyy\nHH:mm z", Locale.US).format(time)
}