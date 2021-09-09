package net.thomasclaxton.chappie

import dev.kord.core.Kord
import dev.kord.core.entity.Message

interface Command {

  fun help(): String

  suspend fun execute(client: Kord, message: Message)
}
