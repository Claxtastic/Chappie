package net.thomasclaxton.chappie.commands

import dev.kord.core.Kord
import dev.kord.core.entity.Message
import net.thomasclaxton.chappie.Command

object Ping : Command {

  const val name = "ping"

  override fun help(): String {
    return "Test command"
  }

  override suspend fun execute(client: Kord, message: Message) {
    message.channel.createMessage("Pong!")
  }
}