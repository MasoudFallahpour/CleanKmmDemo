package ir.fallahpoor.cleankmmdemo.data.network.api.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Links(
    @SerialName("flickr")
    val flickr: Flickr
)