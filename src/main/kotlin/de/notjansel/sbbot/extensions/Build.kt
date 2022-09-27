package de.notjansel.sbbot.extensions

import com.kotlindiscord.kord.extensions.extensions.Extension
import com.kotlindiscord.kord.extensions.extensions.publicSlashCommand
import com.kotlindiscord.kord.extensions.types.respond

class Build : Extension() {
    override val name = "build"
    override suspend fun setup() {
        publicSlashCommand {
            name = "build"
            description = "Displays what build the bot is on."
            action {
                respond {
                    content = "The Bot is on build @version@"
                }
            }
        }
    }
}
