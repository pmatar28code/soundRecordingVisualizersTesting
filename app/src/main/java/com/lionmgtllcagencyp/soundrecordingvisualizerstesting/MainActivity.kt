package com.lionmgtllcagencyp.soundrecordingvisualizerstesting

import android.media.AudioFormat
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import omrecorder.AudioChunk

import omrecorder.PullTransport

import omrecorder.OmRecorder
import omrecorder.PullTransport.OnAudioChunkPulledListener
import omrecorder.Recorder
import omrecorder.AudioRecordConfig

import omrecorder.PullableSource
import android.os.Environment

import androidx.annotation.NonNull
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var recorder: Recorder? = null
        recorder = OmRecorder.wav(
            PullTransport.Default(
                mic()
            ) { audioChunk ->
                animateVoice((audioChunk.maxAmplitude() / 200.0).toFloat())


              }, file()
        )

        recorder.startRecording()
        recorder.stopRecording()
    }
    private fun mic(): PullableSource? {
        return PullableSource.Default(
            AudioRecordConfig.Default(
                MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                AudioFormat.CHANNEL_IN_MONO, 44100
            )
        )
    }

    private fun animateVoice(maxPeak: Float) {
        //recordButton.animate().scaleX(1 + maxPeak).scaleY(1 + maxPeak).setDuration(10).start()
    }

    private fun file(): File {
        return File(Environment.getExternalStorageDirectory(), "kailashdabhi.pcm")
    }
}