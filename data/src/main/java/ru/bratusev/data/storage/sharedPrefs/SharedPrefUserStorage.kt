package ru.bratusev.data.storage.sharedPrefs

/*private const val SHARED_PREFS_NAME = "shared_prefs_name"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val KEY_DEFAULT_NAME = "Default lastName"

class SharedPrefUserStorage(context: Context) : UserStorage {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun get(): User {
        val firstName = sharedPreferences.getString(KEY_FIRST_NAME, "") ?: KEY_DEFAULT_NAME
        val lastName = sharedPreferences.getString(KEY_LAST_NAME, "Default lastName") ?: KEY_DEFAULT_NAME
        return User(firstName = firstName, lastName = lastName)
    }

    override fun save(user: User): Boolean {
        sharedPreferences.edit().putString(KEY_FIRST_NAME, user.firstName).apply()
        sharedPreferences.edit().putString(KEY_LAST_NAME, user.lastName).apply()
        return true
    }
}*/