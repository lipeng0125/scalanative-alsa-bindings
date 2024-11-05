package gweb.alsa.MIDISequencer
import scala.scalanative.unsafe.*
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_event_t

/** Sequencer event <-> MIDI byte stream coder
  */
@link("asound")
@extern
object SequencerEventMIDIByteStreamCoder {

  /** container for sequencer midi event parsers
    */
  type snd_midi_event_t = CStruct0

  /** Creates a MIDI event parser.
    *
    * @param bufsize
    *   [in] Size of the buffer used for encoding; this should be large enough
    *   to hold the largest MIDI message to be encoded.
    * @param rdev
    *   [out] The new MIDI event parser.
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function creates and initializes a MIDI parser object that can be
    * used to convert a MIDI byte stream to sequencer events (encoding) and/or
    * to convert sequencer events to a MIDI byte stream (decoding).
    */
  @name("snd_midi_event_new")
  def snd_midi_event_new(
      bufsize: CSize,
      rdev: Ptr[Ptr[snd_midi_event_t]]
  ): CInt = extern

  /** Resizes the MIDI message encoding buffer.
    *
    * @param dev
    *   MIDI event parser.
    * @param bufsize
    *   The new buffer size.
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function resizes the buffer that is used to hold partially encoded
    * MIDI messages.
    *
    * If there is a partially encoded message in the buffer, it is dropped.
    */
  @name("snd_midi_event_resize_buffer")
  def snd_midi_event_resize_buffer(
      dev: Ptr[snd_midi_event_t],
      bufsize: CSize
  ): CInt = extern

  /** Frees a MIDI event parser.
    *
    * @param dev
    *   MIDI event parser.
    *
    * Frees a MIDI event parser.
    */
  @name("snd_midi_event_free")
  def snd_midi_event_free(dev: Ptr[snd_midi_event_t]): Unit = extern

  /** Resets MIDI encode/decode parsers.
    *
    * @param dev
    *   MIDI event parser.
    *
    * This function resets both encoder and decoder of the MIDI event parser.
    */
  @name("snd_midi_event_init")
  def snd_midi_event_init(dev: Ptr[snd_midi_event_t]): Unit = extern

  /** Resets MIDI encode parser.
    *
    * @param dev
    *   MIDI event parser.
    *
    * This function resets the MIDI encoder of the parser dev. Any partially
    * encoded MIDI message is dropped, and running status state is cleared.
    */
  @name("snd_midi_event_reset_encode")
  def snd_midi_event_reset_encode(dev: Ptr[snd_midi_event_t]): Unit = extern

  /** Resets MIDI decode parser.
    *
    * @param dev
    *   MIDI event parser.
    *
    * This function resets the MIDI decoder of the parser dev. The next decoded
    * message does not use running status from before the call to
    * snd_midi_event_reset_decode.
    */
  @name("snd_midi_event_reset_decode")
  def snd_midi_event_reset_decode(dev: Ptr[snd_midi_event_t]): Unit = extern

  /** Enables/disables MIDI command merging.
    *
    * @param dev
    *   MIDI event parser.
    * @param on
    *   0 to enable MIDI command merging, 1 to always write the command byte.
    *
    * This function enables or disables MIDI command merging (running status).
    *
    * When MIDI command merging is not disabled, snd_midi_event_decode is
    * allowed to omit any status byte that is identical to the previous status
    * byte.
    */
  @name("snd_midi_event_no_status")
  def snd_midi_event_no_status(dev: Ptr[snd_midi_event_t], on: CInt): Unit =
    extern

  /** Encodes bytes to sequencer event.
    *
    * @param dev
    *   [in] MIDI event parser.
    * @param buf
    *   [in] Buffer containing bytes of a raw MIDI stream.
    * @param count
    *   [in] Number of bytes in buf.
    * @param ev
    *   [out] Sequencer event.
    * @return
    *   The number of bytes consumed, or a negative error code.
    *
    * This function tries to use up to count bytes from the beginning of the
    * buffer to encode a sequencer event. If a complete MIDI message has been
    * encoded, the sequencer event is written to ev; otherwise, ev->type is set
    * to SND_SEQ_EVENT_NONE, and further bytes are required to complete a
    * message.
    *
    * The buffer in dev is used to hold any bytes of a not-yet-complete MIDI
    * message. If a System Exclusive message is larger than the buffer, the
    * message is split into multiple parts, and a sequencer event is returned at
    * the end of each part.
    *
    * Any bytes that are not part of a valid MIDI message are silently ignored,
    * i.e., they are consumed without signaling an error.
    *
    * When this function returns a system exclusive sequencer event (ev->type is
    * SND_SEQ_EVENT_SYSEX), the data pointer (ev->data.ext.ptr) points into the
    * MIDI event parser's buffer. Therefore, the sequencer event can only be
    * used as long as that buffer remains valid, i.e., until the next call to
    * snd_midi_event_encode, snd_midi_event_encode_byte,
    * snd_midi_event_resize_buffer, snd_midi_event_init,
    * snd_midi_event_reset_encode, or snd_midi_event_free for that MIDI event
    * parser.
    *
    * This function can generate any sequencer event that corresponds to a MIDI
    * message, i.e.:
    *
    * Some implementations may also be able to generate the following events for
    * a sequence of controller change messages:
    */
  @name("snd_midi_event_encode")
  def snd_midi_event_encode(
      dev: Ptr[snd_midi_event_t],
      buf: Ptr[CUnsignedChar],
      count: CLong,
      ev: Ptr[snd_seq_event_t]
  ): CLong = extern

  /** Encodes byte to sequencer event.
    *
    * @param dev
    *   [in] MIDI event parser.
    * @param c
    *   [in] A byte of a raw MIDI stream.
    * @param ev
    *   [out] Sequencer event.
    * @return
    *   1 if a sequenver event has been completed, 0 if more bytes are required
    *   to complete an event, or a negative error code.
    *
    * This function tries to use the byte c to encode a sequencer event. If a
    * complete MIDI message has been encoded, the sequencer event is written to
    * ev; otherwise, further bytes are required to complete a message.
    *
    * See also the description of snd_midi_event_encode.
    */
  @name("snd_midi_event_encode_byte")
  def snd_midi_event_encode_byte(
      dev: Ptr[snd_midi_event_t],
      c: CInt,
      ev: Ptr[snd_seq_event_t]
  ): CInt = extern

  /** Decodes sequencer event to MIDI byte stream.
    *
    * @param dev
    *   [in] MIDI event parser.
    * @param buf
    *   [out] Buffer for the resulting MIDI byte stream.
    * @param count
    *   [in] Number of bytes in buf.
    * @param ev
    *   [in] The sequencer event to decode.
    * @return
    *   The number of bytes written to buf, or a negative error code.
    *
    * This function tries to decode the sequencer event into one or more MIDI
    * messages, and writes the raw MIDI byte(s) into buf.
    *
    * The generated MIDI messages may use running status, unless disabled with
    * snd_midi_event_no_status.
    *
    * The required buffer size for a sequencer event it as most 12 bytes, except
    * for System Exclusive events (ev->type == SND_SEQ_EVENT_SYSEX) which can
    * have any length (as specified by ev->data.ext.len).
    *
    * The following sequencer events correspond to MIDI messages:
    */
  @name("snd_midi_event_decode")
  def snd_midi_event_decode(
      dev: Ptr[snd_midi_event_t],
      buf: Ptr[CUnsignedChar],
      count: CLong,
      ev: Ptr[snd_seq_event_t]
  ): CLong = extern

}
