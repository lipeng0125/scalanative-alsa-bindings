package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*
import gweb.alsa.MIDISequencer.MIDISequencer.snd_seq_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_event_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_addr_t

/** Sequencer Middle Level Interface
  */
@link("asound")
@extern
object SequencerMiddleLevelInterface {
  // todo 实现宏函数

  /** queue controls - start/stop/continue
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to control
    * @param type
    *   event type
    * @param value
    *   event value
    * @param ev
    *   event instance
    *
    * This function sets up general queue control event and sends it. To send at
    * scheduled time, set the schedule in ev. If ev is NULL, the event is
    * composed locally and sent immediately to the specified queue. In any
    * cases, you need to call snd_seq_drain_output() appropriately to feed the
    * event.
    */
  @name("snd_seq_control_queue")
  def snd_seq_control_queue(
      seq: Ptr[snd_seq_t],
      q: CInt,
      `type`: CInt,
      value: CInt,
      ev: Ptr[snd_seq_event_t]
  ): CInt = extern

  /** create a port - simple version
    *
    * @param seq
    *   sequencer handle
    * @param name
    *   the name of the port
    * @param caps
    *   capability bits
    * @param type
    *   type bits
    * @return
    *   the created port number or negative error code
    *
    * Creates a port with the given capability and type bits.
    */
  @name("snd_seq_create_simple_port")
  def snd_seq_create_simple_port(
      seq: Ptr[snd_seq_t],
      name: Ptr[CChar],
      caps: CUnsignedInt,
      `type`: CUnsignedInt
  ): CInt = extern

  /** delete the port
    *
    * @param seq
    *   sequencer handle
    * @param port
    *   port id
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_delete_simple_port")
  def snd_seq_delete_simple_port(seq: Ptr[snd_seq_t], port: CInt): CInt = extern

  /** simple subscription (w/o exclusive & time conversion)
    *
    * @param seq
    *   sequencer handle
    * @param myport
    *   the port id as receiver
    * @param src_client
    *   sender client id
    * @param src_port
    *   sender port id
    * @return
    *   0 on success or negative error code
    *
    * Connect from the given sender client:port to the given destination port in
    * the current client.
    */
  @name("snd_seq_connect_from")
  def snd_seq_connect_from(
      seq: Ptr[snd_seq_t],
      my_port: CInt,
      src_client: CInt,
      src_port: CInt
  ): CInt = extern

  /** simple subscription (w/o exclusive & time conversion)
    *
    * @param seq
    *   sequencer handle
    * @param myport
    *   the port id as sender
    * @param dest_client
    *   destination client id
    * @param dest_port
    *   destination port id
    * @return
    *   0 on success or negative error code
    *
    * Connect from the given receiver port in the current client to the given
    * destination client:port.
    */
  @name("snd_seq_connect_to")
  def snd_seq_connect_to(
      seq: Ptr[snd_seq_t],
      my_port: CInt,
      dest_client: CInt,
      dest_port: CInt
  ): CInt = extern

  /** simple disconnection
    *
    * @param seq
    *   sequencer handle
    * @param myport
    *   the port id as receiver
    * @param src_client
    *   sender client id
    * @param src_port
    *   sender port id
    * @return
    *   0 on success or negative error code
    *
    * Remove connection from the given sender client:port to the given
    * destination port in the current client.
    */
  @name("snd_seq_disconnect_from")
  def snd_seq_disconnect_from(
      seq: Ptr[snd_seq_t],
      my_port: CInt,
      src_client: CInt,
      src_port: CInt
  ): CInt = extern

  /** simple disconnection
    *
    * @param seq
    *   sequencer handle
    * @param myport
    *   the port id as sender
    * @param dest_client
    *   destination client id
    * @param dest_port
    *   destination port id
    * @return
    *   0 on success or negative error code
    *
    * Remove connection from the given sender client:port to the given
    * destination port in the current client.
    */
  @name("snd_seq_disconnect_to")
  def snd_seq_disconnect_to(
      seq: Ptr[snd_seq_t],
      my_port: CInt,
      dest_client: CInt,
      dest_port: CInt
  ): CInt = extern

  /** set client name
    *
    * @param seq
    *   sequencer handle
    * @param name
    *   name string
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_name")
  def snd_seq_set_client_name(seq: Ptr[snd_seq_t], name: Ptr[CChar]): CInt =
    extern

  /** add client event filter
    *
    * @param seq
    *   sequencer handle
    * @param event_type
    *   event type to be added
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_event_filter")
  def snd_seq_set_client_event_filter(
      seq: Ptr[snd_seq_t],
      event_type: CInt
  ): CInt = extern

  /** set client MIDI protocol version
    *
    * @param seq
    *   sequencer handle
    * @param midi_version
    *   MIDI protocol version to set
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_midi_version")
  def snd_seq_set_client_midi_version(
      seq: Ptr[snd_seq_t],
      midi_version: CInt
  ): CInt = extern

  /** enable/disable client's automatic conversion of UMP/legacy events
    *
    * @param seq
    *   sequencer handle
    * @param enable
    *   0 or 1 to disable/enable the conversion
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_ump_conversion")
  def snd_seq_set_client_ump_conversion(
      seq: Ptr[snd_seq_t],
      enable: CInt
  ): CInt = extern

  /** change the output pool size of the given client
    *
    * @param seq
    *   sequencer handle
    * @param size
    *   output pool size
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_pool_output")
  def snd_seq_set_client_pool_output(seq: Ptr[snd_seq_t], size: CSize): CInt =
    extern

  /** change the output room size of the given client
    *
    * @param seq
    *   sequencer handle
    * @param size
    *   output room size
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_pool_output_room")
  def snd_seq_set_client_pool_output_room(
      seq: Ptr[snd_seq_t],
      size: CSize
  ): CInt = extern

  /** change the input pool size of the given client
    *
    * @param seq
    *   sequencer handle
    * @param size
    *   input pool size
    * @return
    *   0 on success or negative error code
    */
  @name("snd_seq_set_client_pool_input")
  def snd_seq_set_client_pool_input(seq: Ptr[snd_seq_t], size: CSize): CInt =
    extern

  /** wait until all events are processed
    *
    * @param seq
    *   sequencer handle
    * @return
    *   0 on success or negative error code
    *
    * This function waits until all events of this client are processed.
    */
  @name("snd_seq_sync_output_queue")
  def snd_seq_sync_output_queue(seq: Ptr[snd_seq_t]): CInt = extern

  /** parse the given string and get the sequencer address
    *
    * @param seq
    *   sequencer handle
    * @param addr
    *   the address pointer to be returned
    * @param arg
    *   the string to be parsed
    * @return
    *   0 on success or negative error code
    *
    * This function parses the sequencer client and port numbers from the given
    * string. The client and port tokens are separated by either colon or
    * period, e.g. 128:1. When seq is not NULL, the function accepts also a
    * client name not only digit numbers. Actually arg need to be only a prefix
    * of the wanted client. That is, if a client named "Foobar XXL Master 2012"
    * with number 128 is available, then parsing "Foobar" will return the
    * address 128:0 if no other client is an exact match.
    */
  @name("snd_seq_parse_address")
  def snd_seq_parse_address(
      seq: Ptr[snd_seq_t],
      addr: Ptr[snd_seq_addr_t],
      str: Ptr[CChar]
  ): CInt = extern

  /** reset client output pool
    *
    * @param seq
    *   sequencer handle
    * @return
    *   0 on success or negative error code
    *
    * So far, this works identically like snd_seq_drop_output().
    */
  @name("snd_seq_reset_pool_output")
  def snd_seq_reset_pool_output(seq: Ptr[snd_seq_t]): CInt = extern

  /** reset client input pool
    *
    * @param seq
    *   sequencer handle
    * @return
    *   0 on success or negative error code
    *
    * So far, this works identically like snd_seq_drop_input().
    */
  @name("snd_seq_reset_pool_input")
  def snd_seq_reset_pool_input(seq: Ptr[snd_seq_t]): CInt = extern

}
