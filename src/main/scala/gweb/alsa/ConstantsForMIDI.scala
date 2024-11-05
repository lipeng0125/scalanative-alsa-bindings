package gweb.alsa

import scala.scalanative.unsafe.link
import scala.scalanative.unsafe.extern

/** Constants for MIDI v1.0.
  */
@link("asound")
@extern
object ConstantsForMIDI {

  /** Number of channels per port/cable. */
  inline val MIDI_CHANNELS = 16

  /** Channel number for GM drums */
  inline val MIDI_GM_DRUM_CHANNEL = (10 - 1)
}
