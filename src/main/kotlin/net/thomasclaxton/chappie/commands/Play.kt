package net.thomasclaxton.chappie.commands

import PlayerManager
import com.sapher.youtubedl.YoutubeDL
import com.sapher.youtubedl.YoutubeDLRequest
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.entity.Message
import dev.kord.core.entity.channel.VoiceChannel
import dev.kord.voice.AudioFrame
import net.thomasclaxton.chappie.AudioHandler
import net.thomasclaxton.chappie.Command
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object Play : Command {

  const val name = "play"

  private val cachePath: String = File("").absolutePath.plus("\\cache\\")

//  private val youtube = YoutubeService()

  override fun help(): String {
    return "Plays a track"
  }

  override suspend fun execute(client: Kord, message: Message) {
    val queryParts = message.content.split(" ")
    val url = queryParts[1]


    val voiceChannel = client.getChannelOf<VoiceChannel>(Snowflake(276122839407656964))!!

    val playerManager = DefaultAudioPlayerManager()

    val query = "ytsearch: rick roll"

    val player = playerManager.createPlayer()

    val track = suspendCoroutine<AudioTrack> {
      playerManager.loadItem(query, object : AudioLoadResultHandler {
        override fun trackLoaded(track: AudioTrack) {
          it.resume(track)
        }

        override fun playlistLoaded(playlist: AudioPlaylist?) {
          TODO("Not yet implemented")
        }

        override fun noMatches() {

        }

        override fun loadFailed(exception: FriendlyException?) {
          TODO("Not yet implemented")
        }
      })
    }

    voiceChannel.connect {
      selfDeaf = true
      player.playTrack(track)
    }

    message.channel.createMessage("playing track")
  }

  fun ytdl(url: String): String? {
    val ytdlPath: String = File("").absolutePath.plus("\\bin\\youtube-dl.exe")

    val request = YoutubeDLRequest(url, cachePath).apply {
      setOption("ignore-errors")
      setOption("extract-audio")
      setOption("format", "bestaudio")
      setOption("output", "%(id)s.%(ext)s")
      setOption("audio-format \"opus\"")
    }

    YoutubeDL.setExecutablePath(ytdlPath)

    YoutubeDL.execute(request)

    val regex = """http(?:s)?:\/\/(?:m.)?(?:www\.)?youtu(?:\.be\/|be\.com\/(?:watch\?(?:feature=youtu.be\&)?v=|v\/|embed\/|user\/(?:[\w#]+\/)+))([^&#?\n]+)""".toRegex()

    return regex.find(url)?.groups?.toList()?.get(1)?.value
  }
}
