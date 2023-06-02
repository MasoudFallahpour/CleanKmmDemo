package ir.fallahpoor.cleankmmdemo.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DatabaseDriverProvider {
    actual fun getDriver(): SqlDriver = NativeSqliteDriver(Database.Schema, "database.db")
}