package ir.fallahpoor.cleankmmdemo.data.network.api.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Flickr(
    @SerialName("small")
    val smallImages: List<String> = emptyList(),
    @SerialName("original")
    val originalImages: List<String> = emptyList()
)