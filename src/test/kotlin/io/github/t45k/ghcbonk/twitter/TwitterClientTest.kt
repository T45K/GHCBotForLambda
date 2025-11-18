package io.github.t45k.ghcbonk.twitter

import kotlin.test.Test
import org.junit.Ignore

@Ignore("Using real API")
internal class TwitterClientTest {

    @Test
    fun test() {
        val response = TwitterClient(
            System.getenv("apiKey"),
            System.getenv("apiSecret"),
            System.getenv("token"),
            System.getenv("tokenSecret")
        )
            .tweet("Hello world via API")

        println(response.code)
        println(response.body!!.string())
    }
}
