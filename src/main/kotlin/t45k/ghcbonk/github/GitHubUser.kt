package t45k.ghcbonk.github

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class GitHubUser(private val userName: String) {
    companion object {
        private const val GITHUB_URL_PREFIX = "https://github.com/"
    }

    fun fetchGitHubUserPage(): Document = Jsoup.connect(GITHUB_URL_PREFIX + userName).get()
}
