package ir.fallahpoor.cleankmmdemo.domain.model

data class RocketLaunch(
    val id: String,
    val name: String,
    val flightNumber: Long,
    val description: String?,
    val successful: Boolean?,
    val imageUrl: String?
)