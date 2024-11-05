package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

/** Sequencer Miscellaneous
  */
@link("asound")
@extern
object SequencerMiscellaneous {

  /** set a bit flag
    */
  def snd_seq_set_bit(nr: CInt, array: CVoidPtr): Unit = extern

  /** unset a bit flag
    */
  def snd_seq_unset_bit(nr: CInt, array: CVoidPtr): Unit = extern

  /** change a bit flag
    */
  def snd_seq_change_bit(nr: CInt, array: CVoidPtr): CInt = extern

  /** get a bit flag state
    */
  def snd_seq_get_bit(nr: CInt, array: CVoidPtr): CInt = extern

}
