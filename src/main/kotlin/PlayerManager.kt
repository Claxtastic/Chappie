import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager
import net.thomasclaxton.chappie.AudioHandler

class PlayerManager : DefaultAudioPlayerManager() {

  val player: AudioPlayer
  val handler: AudioHandler

  init {
    AudioSourceManagers.registerRemoteSources(this)
    AudioSourceManagers.registerLocalSource(this)
    source(YoutubeAudioSourceManager::class.java).setPlaylistPageCount(10)

    player = createPlayer()
    handler = AudioHandler(this, player)
    player.addListener(handler)
  }
}
