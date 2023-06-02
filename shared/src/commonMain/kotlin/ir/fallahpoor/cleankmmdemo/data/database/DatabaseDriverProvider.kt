package ir.fallahpoor.cleankmmdemo.data.database

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverProvider {
    fun getDriver(): SqlDriver
}