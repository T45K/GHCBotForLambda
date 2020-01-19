package t45k.ghcbonk

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import t45k.ghcbonk.github.ContributionData
import t45k.ghcbonk.github.GitHubUser
import t45k.ghcbonk.twitter.Tweeter
import java.io.InputStream
import java.net.URL
import java.util.Properties
import java.util.ResourceBundle

class MyLambda : RequestHandler<Unit, String> {
    override fun handleRequest(input: Unit, context: Context?): String {
        return try {
            val propertyBundle: ResourceBundle = ResourceBundle.getBundle("info")
            val userName: String = propertyBundle.getString("userName")
            val user = GitHubUser(userName)
            val contributionData: ContributionData = user.fetchContributionData()
            val tweeter = Tweeter(propertyBundle)
            tweeter.tweet(contributionData)

            "success"
        } catch (e: Exception) {
            e.toString()
        }
    }
}
