package nl.joerihofman.kotlinmusicplayer

import nl.joerihofman.kotlinmusicplayer.collector.mixcloud.MixcloudCollector

fun main() {
    val mixcloudCollector = MixcloudCollector()
    mixcloudCollector.getMusic()
}