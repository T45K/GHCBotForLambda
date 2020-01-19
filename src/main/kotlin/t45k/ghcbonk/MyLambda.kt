package t45k.ghcbonk

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import t45k.ghcbonk.github.ContributionData
import t45k.ghcbonk.github.GitHubUser
import t45k.ghcbonk.twitter.Tweeter
import java.util.Properties

class MyLambda : RequestHandler<Unit, String> {
    override fun handleRequest(input: Unit, context: Context?): String {
        return try {
            val properties = Properties()
            properties.load(this.javaClass.getResourceAsStream("/resources/test.properties"))
            val userName: String = properties.getProperty("userName")
            val user = GitHubUser(userName)
            val contributionData: ContributionData = user.fetchContributionData()
            val tweeter = Tweeter(properties)
            tweeter.tweet(contributionData)

            "success"
        } catch (e: Exception) {
            e.toString()
        }
    }
}
