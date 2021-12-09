package us.reachmobi.sportapp.util

open class UIEvent<out T>(
    private val type: T,
    private val value: Any? = null,
) {

    fun getData(): Pair<T, Any?>? {
        return Pair(type, value)
    }
}
