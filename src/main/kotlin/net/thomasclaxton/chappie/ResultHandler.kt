package net.thomasclaxton.chappie

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.kord.core.entity.Message

class ResultHandler(val message: Message, val audioHandler: AudioHandler) : AudioLoadResultHandler {

  override fun trackLoaded(track: AudioTrack) {
    audioHandler.addTrack(track)
  }

  override fun playlistLoaded(playlist: AudioPlaylist?) {
    TODO("Not yet implemented")
  }

  override fun noMatches() {
    TODO("Not yet implemented")
  }

  override fun loadFailed(exception: FriendlyException?) {
    TODO("Not yet implemented")
  }
}
