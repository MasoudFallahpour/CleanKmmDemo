package ir.fallahpoor.cleankmmdemo.data.network.api.model

import ir.fallahpoor.cleankmmdemo.data.database.RocketLaunchLocalModel
import ir.fallahpoor.cleankmmdemo.domain.model.RocketLaunch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunchRemoteModel(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("flight_number")
    val flightNumber: Long,
    @SerialName("details")
    val description: String?,
    @SerialName("success")
    val successful: Boolean?,
    @SerialName("links")
    val links: Links
)

fun List<RocketLaunchRemoteModel>.toRocketLaunches(): List<RocketLaunch> =
    this.map { it.toRocketLaunch() }

private fun RocketLaunchRemoteModel.toRocketLaunch(): RocketLaunch {
    val imageUrl = if (this.links.flickr.originalImages.isNotEmpty()) {
        this.links.flickr.originalImages.first()
    } else {
        null
    }
    return RocketLaunch(
        id = this.id,
        name = this.name,
        flightNumber = this.flightNumber,
        description = this.description,
        successful = this.successful,
        imageUrl = imageUrl
    )
}

fun List<RocketLaunchRemoteModel>.toRocketLaunchLocalModels(): List<RocketLaunchLocalModel> =
    this.map { it.toRocketLaunchLocalModel() }

private fun RocketLaunchRemoteModel.toRocketLaunchLocalModel(): RocketLaunchLocalModel {
    val imageUrl = if (this.links.flickr.originalImages.isNotEmpty()) {
        this.links.flickr.originalImages.first()
    } else {
        null
    }
    return RocketLaunchLocalModel(
        id = this.id,
        name = this.name,
        flightNumber = this.flightNumber,
        description = this.description,
        successful = this.successful.toLong(),
        imageUrl = imageUrl
    )
}

private fun Boolean?.toLong(): Long? = when {
    this == null -> null
    this == true -> 1
    else -> 0
}