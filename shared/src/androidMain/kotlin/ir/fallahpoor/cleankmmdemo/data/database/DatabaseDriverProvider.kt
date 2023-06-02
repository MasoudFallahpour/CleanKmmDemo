package ir.fallahpoor.cleankmmdemo.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseDriverProvider(private val context: Context) {
    actual fun getDriver(): SqlDriver = AndroidSqliteDriver(Database.Schema, context, "database.db")
}