package io.github.t45k.ghcbonk.twitter

import java.util.ResourceBundle
import kotlin.test.Test

internal class TwitterClientTest {

    @Test
    fun test() {
        val property: ResourceBundle = ResourceBundle.getBundle("info")
        TwitterClient(
            property.getString("apiKey"),
            property.getString("apiSecret"),
            property.getString("token"),
            property.getString("tokenSecret")
        ).tweet("Hello world via Twitter API")
    }
}
