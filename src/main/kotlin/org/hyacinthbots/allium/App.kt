/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package org.hyacinthbots.allium

import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.modules.extra.mappings.extMappings
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.Snowflake
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.shedaniel.linkie.utils.readText
import org.hyacinthbots.allium.extensions.*
import java.io.File
import java.util.*

val TEST_SERVER_ID = Snowflake(
    env("TEST_SERVER").toLong()  // Get the test server ID from the env vars or a .env file
)

val TEST_SERVER_CHANNEL_ID = Snowflake(
    env("TEST_CHANNEL").toLong() // Get the test channel ID from the env vars or a .env file
)

var splashes = JsonArray()

private val TOKEN = env("TOKEN")   // Get the bot' token from the env vars or a .env file
suspend fun main() {
    splashes = JsonParser.parseString({}.javaClass.getResource("/splashes.json")?.readText()).asJsonObject.getAsJsonArray("splashes")
    if (File("./logs/latest.log").exists()) {
        File("./logs/latest.log").renameTo(File("./logs/log-${File("./logs/").listFiles()?.size}"))
        withContext(Dispatchers.IO) {
            File("./logs/latest.log").createNewFile()
        }
    } else {
        withContext(Dispatchers.IO) {
            File("./logs/").mkdirs()
            File("./logs/latest.log").createNewFile()
        }
    }
    val bot = ExtensibleBot(TOKEN) {
        extensions {
            add(::EventHooks)
            add(::Modrinth)
            add(::About)
            add(::PresenceUpdater)
            extMappings {  }
        }
        i18n {
            defaultLocale = Locale.ENGLISH
        }
    }
    bot.start()
}
