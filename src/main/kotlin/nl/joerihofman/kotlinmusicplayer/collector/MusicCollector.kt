package nl.joerihofman.kotlinmusicplayer.collector

import nl.joerihofman.kotlinmusicplayer.Song

interface MusicCollector {

    fun getMusic(): List<Song>

}