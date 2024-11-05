package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import scala.scalanative.unsafe.Nat._5
import scala.scalanative.unsafe.Nat._6
import scala.scalanative.unsafe.Nat._2
import scala.scalanative.unsafe.Nat.Digit3
import gweb.alsa.Iota

/** Sequencer Event Type Checks
  */
@link("asound")
@extern
object SequencerEventTypeChecks {
  // todo 实现宏

  object $_ extends Iota {

    /** */
    val SND_SEQ_EVFLG_RESULT = iota

    /** */
    val SND_SEQ_EVFLG_NOTE = iota

    /** */
    val SND_SEQ_EVFLG_CONTROL = iota

    /** */
    val SND_SEQ_EVFLG_QUEUE = iota

    /** */
    val SND_SEQ_EVFLG_SYSTEM = iota

    /** */
    val SND_SEQ_EVFLG_MESSAGE = iota

    /** */
    val SND_SEQ_EVFLG_CONNECTION = iota

    /** */
    val SND_SEQ_EVFLG_SAMPLE = iota

    /** */
    val SND_SEQ_EVFLG_USERS = iota

    /** */
    val SND_SEQ_EVFLG_INSTR = iota

    /** */
    val SND_SEQ_EVFLG_QUOTE = iota

    /** */
    val SND_SEQ_EVFLG_NONE = iota

    /** */
    val SND_SEQ_EVFLG_RAW = iota

    /** */
    val SND_SEQ_EVFLG_FIXED = iota

    /** */
    val SND_SEQ_EVFLG_VARIABLE = iota

    /** */
    val SND_SEQ_EVFLG_VARUSR = iota

  }

  object $__ extends Iota {

    /** */
    val SND_SEQ_EVFLG_NOTE_ONEARG = iota

    /** */
    val SND_SEQ_EVFLG_NOTE_TWOARG = iota

  }

  object $___ extends Iota {

    /** */
    val SND_SEQ_EVFLG_QUEUE_NOARG = iota

    /** */
    val SND_SEQ_EVFLG_QUEUE_TICK = iota

    /** */
    val SND_SEQ_EVFLG_QUEUE_TIME = iota

    /** */
    val SND_SEQ_EVFLG_QUEUE_VALUE = iota

  }

  @name("snd_seq_event_types")
  var snd_seq_event_types: CArray[CUnsignedInt, Digit3[_2, _5, _6]] = extern
}
