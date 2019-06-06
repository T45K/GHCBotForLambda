/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package t45k.ghcbonk

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import t45k.ghcbonk.github.GitHubUser
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class App {
    companion object {
        const val TIME_OF_ONE_DAY: Long = 1000 * 60 * 60 * 24
    }

    @Suppress("JAVA_CLASS_ON_COMPANION")
    val logger = LoggerFactory.getLogger(App.javaClass)

    fun main(args: Array<String>) {
        val properties = Properties()
        properties.load(Files.newBufferedReader(Paths.get(args[0])))

        val timer = Timer()
        val timerTask = object : TimerTask() {
            override fun run() {
                val userName = properties.getProperty("userName")
                val user = GitHubUser(userName)
                val contributionData = user.featchContributionData()
            }
        }

        val date = Date()
        val calendar = Calendar.getInstance()
        calendar.time = date
        val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val format = simpleDateFormat.format(calendar.time)

        if (format.endsWith("00:00:00")) {
            timer.schedule(timerTask, TIME_OF_ONE_DAY)
        } else {
            calendar.add(Calendar.DATE, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            timer.schedule(timerTask, calendar.time, TIME_OF_ONE_DAY);
        }
    }
}

