package net.thomasclaxton.chappie

import PlayerManager
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import dev.kord.voice.AudioFrame
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame as LavaFrame
import dev.kord.voice.AudioProvider

class AudioHandler(
  val manager: PlayerManager,
  val player: AudioPlayer
) : AudioEventAdapter(), AudioProvider {

  private val queue = arrayListOf<AudioTrack>()

  /** the latest frame of audio from Lavaplayer **/
  private var lastFrame: LavaFrame? = null

  fun addTrack(track: AudioTrack) {
    player.playTrack(track)
  }

  override fun onTrackEnd(player: AudioPlayer, track: AudioTrack, endReason: AudioTrackEndReason) {
    if (queue.isEmpty()) {
      player.isPaused = false
    } else {
      player.playTrack(queue.removeAt(0))
    }
  }

  private fun canProvide(): Boolean {
    lastFrame = player.provide()
    return lastFrame != null
  }

  override fun provide(): AudioFrame? {
    return if (canProvide()) AudioFrame(lastFrame!!.data)
    else null
  }
}