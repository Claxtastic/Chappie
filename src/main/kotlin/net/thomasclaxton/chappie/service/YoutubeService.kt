package net.thomasclaxton.chappie.service

import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.model.SearchResult
import net.thomasclaxton.chappie.env

private val YOUTUBE_KEY = env["youtubeToken"]!!

class YoutubeService {

  private val youtube = YouTube.Builder(NetHttpTransport(), GsonFactory()) { }.setApplicationName("chappie-discord").build()

  fun searchForVideo(query: String): SearchResult {
    val search = youtube.search().list(listOf("id", "snippet"))

    search.key = YOUTUBE_KEY
    search.q = query
    search.type = listOf("video")

    val searchResponse = search.execute()

    return searchResponse.items.first()
  }
}
