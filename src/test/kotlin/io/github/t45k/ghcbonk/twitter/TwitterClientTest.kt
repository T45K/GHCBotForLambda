package io.github.t45k.ghcbonk.twitter

import java.util.ResourceBundle
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore("Using real API")
internal class TwitterClientTest {

    @Test
    fun test() {
        val property: ResourceBundle = ResourceBundle.getBundle("info")
        TwitterClient(
            property.getString("apiKey"),
            property.getString("apiSecret"),
            property.getString("token"),
            property.getString("tokenSecret")
        )
            .tweet("Hello\nworld\nvia\nTwitter")
            .let(::println)
    }
}
