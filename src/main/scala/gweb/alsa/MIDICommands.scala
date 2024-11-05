package gweb.alsa
import scala.scalanative.unsafe.*
import scala.scalanative.posix.libgen

/** MIDI command codes.
  */
@link("asound")
@extern
object MIDICommands {

  /** note off */
  inline val MIDI_CMD_NOTE_OFF = 0x80

  /** note on */
  inline val MIDI_CMD_NOTE_ON = 0x90

  /** key pressure */
  inline val MIDI_CMD_NOTE_PRESSURE = 0xa0

  /** control change */
  inline val MIDI_CMD_CONTROL = 0xb0

  /** program change */
  inline val MIDI_CMD_PGM_CHANGE = 0xc0

  /** channel pressure */
  inline val MIDI_CMD_CHANNEL_PRESSURE = 0xd0

  /** pitch bender */
  inline val MIDI_CMD_BENDER = 0xe0

  /** sysex (system exclusive) begin */
  inline val MIDI_CMD_COMMON_SYSEX = 0xf0

  /** MTC quarter frame */
  inline val MIDI_CMD_COMMON_MTC_QUARTER = 0xf1

  /** song position */
  inline val MIDI_CMD_COMMON_SONG_POS = 0xf2

  /** song select */
  inline val MIDI_CMD_COMMON_SONG_SELECT = 0xf3

  /** tune request */
  inline val MIDI_CMD_COMMON_TUNE_REQUEST = 0xf6

  /** end of sysex */
  inline val MIDI_CMD_COMMON_SYSEX_END = 0xf7

  /** clock */
  inline val MIDI_CMD_COMMON_CLOCK = 0xf8

  /** start */
  inline val MIDI_CMD_COMMON_START = 0xfa

  /** continue */
  inline val MIDI_CMD_COMMON_CONTINUE = 0xfb

  /** stop */
  inline val MIDI_CMD_COMMON_STOP = 0xfc

  /** active sensing */
  inline val MIDI_CMD_COMMON_SENSING = 0xfe

  /** reset */
  inline val MIDI_CMD_COMMON_RESET = 0xff

}
