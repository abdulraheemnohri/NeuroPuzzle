package com.neuropuzzle.app.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator

class SoundManager(context: Context) {
    private val toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

    fun playTap() {
        toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 50)
    }

    fun playSuccess() {
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_PIP, 200)
    }

    fun playFailure() {
        toneGenerator.startTone(ToneGenerator.TONE_SUP_ERROR, 300)
    }
}
