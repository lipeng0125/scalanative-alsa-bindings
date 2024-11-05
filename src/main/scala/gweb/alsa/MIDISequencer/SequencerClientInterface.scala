package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import gweb.alsa.MIDISequencer.MIDISequencer.snd_seq_t
import gweb.alsa.Iota

/** Sequencer Client Interface
  */
@link("asound")
@extern
object SequencerClientInterface {

  /** client information container
    */
  type snd_seq_client_info_t = CStruct0

  /** client pool information container
    */
  type snd_seq_client_pool_t = CStruct0

  /** client types
    */
  class snd_seq_client_type_t private (val value: CInt) extends AnyVal

  object snd_seq_client_type_t extends Iota {

    /** user client */
    val SND_SEQ_USER_CLIENT = iota(1)

    /** kernel client */
    val SND_SEQ_KERNEL_CLIENT = iota(2)

  }

  /** client MIDI version
    */
  object $ extends Iota {

    /** Legacy client
      */
    val SND_SEQ_CLIENT_LEGACY_MIDI = iota(0)

    /** UMP MIDI 1.0
      */
    val SND_SEQ_CLIENT_UMP_MIDI_1_0 = iota(1)

    /** UMP MIDI 2.0
      */
    val SND_SEQ_CLIENT_UMP_MIDI_2_0 = iota(2)
  }

  /** get size of snd_seq_client_info_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_client_info_sizeof")
  def snd_seq_client_info_sizeof(): CSize = extern

  /** allocate an empty snd_seq_client_info_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_client_info_malloc")
  def snd_seq_client_info_malloc(ptr: Ptr[Ptr[snd_seq_client_info_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_client_info_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_client_info_free")
  def snd_seq_client_info_free(ptr: Ptr[snd_seq_client_info_t]): Unit = extern

  /** copy one snd_seq_client_info_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_client_info_copy")
  def snd_seq_client_info_copy(
      dst: Ptr[snd_seq_client_info_t],
      src: Ptr[snd_seq_client_info_t]
  ): Unit = extern

  /** Get client id of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   client id
    */
  @name("snd_seq_client_info_get_client")
  def snd_seq_client_info_get_client(info: Ptr[snd_seq_client_info_t]): CInt =
    extern

  /** Get client type of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   client type
    *
    * The client type is either SND_SEQ_KERNEL_CLIENT or SND_SEQ_USER_CLIENT for
    * kernel or user client respectively.
    */
  @name("snd_seq_client_info_get_type")
  def snd_seq_client_info_get_type(
      info: Ptr[snd_seq_client_info_t]
  ): snd_seq_client_type_t = extern

  /** Get the name of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   name string
    */
  @name("snd_seq_client_info_get_name")
  def snd_seq_client_info_get_name(
      info: Ptr[snd_seq_client_info_t]
  ): Ptr[CChar] = extern

  /** Get the broadcast filter usage of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   1 if broadcast is accepted
    */
  @name("snd_seq_client_info_get_broadcast_filter")
  def snd_seq_client_info_get_broadcast_filter(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get the error-bounce usage of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   1 if error-bounce is enabled
    */
  @name("snd_seq_client_info_get_error_bounce")
  def snd_seq_client_info_get_error_bounce(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get the sound card number.
    *
    * @param info
    *   client_info container
    * @return
    *   card number or -1 if value is not available.
    *
    * Only available for SND_SEQ_KERNEL_CLIENT clients.
    *
    * The card number can be used to query state about the hardware device
    * providing this client, by concatenating "hw:CARD=" with the card number
    * and using it as the name parameter to snd_ctl_open().
    */
  @name("snd_seq_client_info_get_card")
  def snd_seq_client_info_get_card(info: Ptr[snd_seq_client_info_t]): CInt =
    extern

  /** Get the owning PID.
    *
    * @param info
    *   client_info container
    * @return
    *   pid or -1 if value is not available.
    *
    * Only available for SND_SEQ_USER_CLIENT clients.
    */
  @name("snd_seq_client_info_get_pid")
  def snd_seq_client_info_get_pid(info: Ptr[snd_seq_client_info_t]): CInt =
    extern

  /** (DEPRECATED) Get the event filter bitmap of a client_info container
    *
    * @param info
    *   client_info container
    * @return
    *   NULL if no event filter, or pointer to event filter bitmap
    *
    * Use snd_seq_client_info_event_filter_check() instead.
    */
  @name("snd_seq_client_info_get_event_filter")
  def snd_seq_client_info_get_event_filter(
      info: Ptr[snd_seq_client_info_t]
  ): Ptr[CUnsignedChar] = extern

  /** Get the number of opened ports of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   number of opened ports
    */
  @name("snd_seq_client_info_get_num_ports")
  def snd_seq_client_info_get_num_ports(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get the number of lost events of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   number of lost events
    */
  @name("snd_seq_client_info_get_event_lost")
  def snd_seq_client_info_get_event_lost(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get the MIDI protocol version number of a client_info container.
    *
    * @param info
    *   client_info container
    * @return
    *   MIDI protocol version
    */
  @name("snd_seq_client_info_get_midi_version")
  def snd_seq_client_info_get_midi_version(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get the UMP group filter status.
    *
    * @param info
    *   client_info container
    * @param group
    *   0-based group index
    * @return
    *   0 if the group is filtered / disabled, 1 if it's processed
    */
  @name("snd_seq_client_info_get_ump_group_enabled")
  def snd_seq_client_info_get_ump_group_enabled(
      info: Ptr[snd_seq_client_info_t],
      group: CInt
  ): CInt = extern

  /** Get the UMP groupless message handling status.
    *
    * @param info
    *   client_info container
    * @return
    *   1 if UMP groupless messages is processed, 0 if filtered/disabled
    */
  @name("snd_seq_client_info_get_ump_groupless_enabled")
  def snd_seq_client_info_get_ump_groupless_enabled(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get the automatic conversion mode for UMP.
    *
    * @param info
    *   client_info container
    * @return
    *   1 if the conversion is enabled, 0 if not
    */
  @name("snd_seq_client_info_get_ump_conversion")
  def snd_seq_client_info_get_ump_conversion(
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Set the client id of a client_info container.
    *
    * @param info
    *   client_info container
    * @param client
    *   client id
    */
  @name("snd_seq_client_info_set_client")
  def snd_seq_client_info_set_client(
      info: Ptr[snd_seq_client_info_t],
      client: CInt
  ): Unit = extern

  /** Set the name of a client_info container.
    *
    * @param info
    *   client_info container
    * @param name
    *   name string
    */
  @name("snd_seq_client_info_set_name")
  def snd_seq_client_info_set_name(
      info: Ptr[snd_seq_client_info_t],
      name: Ptr[CChar]
  ): Unit = extern

  /** Set the broadcast filter usage of a client_info container.
    *
    * @param info
    *   client_info container
    * @param val
    *   non-zero if broadcast is accepted
    */
  @name("snd_seq_client_info_set_broadcast_filter")
  def snd_seq_client_info_set_broadcast_filter(
      info: Ptr[snd_seq_client_info_t],
      `val`: CInt
  ): Unit = extern

  /** Set the error-bounce usage of a client_info container.
    *
    * @param info
    *   client_info container
    * @param val
    *   non-zero if error is bounced
    */
  @name("snd_seq_client_info_set_error_bounce")
  def snd_seq_client_info_set_error_bounce(
      info: Ptr[snd_seq_client_info_t],
      `val`: CInt
  ): Unit = extern

  /** (DEPRECATED) Set the event filter bitmap of a client_info container
    *
    * @param info
    *   client_info container
    * @param filter
    *   event filter bitmap, pass NULL for no event filtering
    *
    * Use snd_seq_client_info_event_filter_add instead.
    */
  @name("snd_seq_client_info_set_event_filter")
  def snd_seq_client_info_set_event_filter(
      info: Ptr[snd_seq_client_info_t],
      filter: Ptr[CUnsignedChar]
  ): Unit = extern

  /** Set the MIDI protocol version of a client_info container.
    *
    * @param info
    *   client_info container
    * @param midi_version
    *   MIDI protocol version to set
    */
  @name("snd_seq_client_info_set_midi_version")
  def snd_seq_client_info_set_midi_version(
      info: Ptr[snd_seq_client_info_t],
      midi_version: CInt
  ): Unit = extern

  /** Set the UMP group filter status.
    *
    * @param info
    *   client_info container
    * @param group
    *   0-based group index
    * @param enable
    *   0 to filter/disable the group, non-zero to enable
    */
  @name("snd_seq_client_info_set_ump_group_enabled")
  def snd_seq_client_info_set_ump_group_enabled(
      info: Ptr[snd_seq_client_info_t],
      group: CInt,
      enable: CInt
  ): Unit = extern

  /** Enable/disable the UMP groupless message handling.
    *
    * @param info
    *   client_info container
    * @param enable
    *   enable the UMP groupless messages
    */
  @name("snd_seq_client_info_set_ump_groupless_enabled")
  def snd_seq_client_info_set_ump_groupless_enabled(
      info: Ptr[snd_seq_client_info_t],
      enable: CInt
  ): Unit = extern

  /** Set the automatic conversion mode for UMP.
    *
    * @param info
    *   client_info container
    * @param enable
    *   0 or 1 for disabling/enabling the conversion
    */
  @name("snd_seq_client_info_set_ump_conversion")
  def snd_seq_client_info_set_ump_conversion(
      info: Ptr[snd_seq_client_info_t],
      enable: CInt
  ): Unit = extern

  /** Disable event filtering of a client_info container.
    *
    * @param info
    *   client_info container
    *
    * Remove all event types added with snd_seq_client_info_event_filter_add and
    * clear the event filtering flag of this client_info container.
    */
  @name("snd_seq_client_info_event_filter_clear")
  def snd_seq_client_info_event_filter_clear(
      info: Ptr[snd_seq_client_info_t]
  ): Unit = extern

  /** Add an event type to the event filtering of a client_info container.
    *
    * @param info
    *   client_info container
    * @param event_type
    *   event type to be added
    *
    * Set the event filtering flag of this client_info and add the specified
    * event type to the filter bitmap of this client_info container.
    */
  @name("snd_seq_client_info_event_filter_add")
  def snd_seq_client_info_event_filter_add(
      info: Ptr[snd_seq_client_info_t],
      event_type: CInt
  ): Unit = extern

  /** Remove an event type from the event filtering of a client_info container.
    *
    * @param info
    *   client_info container
    * @param event_type
    *   event type to be removed
    *
    * Removes the specified event from the filter bitmap of this client_info
    * container. It will not clear the event filtering flag, use
    * snd_seq_client_info_event_filter_clear instead.
    */
  @name("snd_seq_client_info_event_filter_del")
  def snd_seq_client_info_event_filter_del(
      info: Ptr[snd_seq_client_info_t],
      event_type: CInt
  ): Unit = extern

  /** Check if an event type is present in the event filtering of a client_info
    * container.
    *
    * @param info
    *   client_info container
    * @param event_type
    *   event type to be checked
    * @return
    *   1 if the event type is present, 0 otherwise
    *
    * Test if the event type is in the filter bitmap of this client_info
    * container.
    */
  @name("snd_seq_client_info_event_filter_check")
  def snd_seq_client_info_event_filter_check(
      info: Ptr[snd_seq_client_info_t],
      event_type: CInt
  ): CInt = extern

  /** obtain the current client information
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   the pointer to be stored
    * @return
    *   0 on success otherwise a negative error code
    *
    * Obtains the information of the current client stored on info. client and
    * type fields are ignored.
    */
  @name("snd_seq_get_client_info")
  def snd_seq_get_client_info(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** obtain the information of the given client
    *
    * @param seq
    *   sequencer handle
    * @param client
    *   client id
    * @param info
    *   the pointer to be stored
    * @return
    *   0 on success otherwise a negative error code
    *
    * Obtains the information of the client with a client id specified by info
    * argument. The obtained information is written on info parameter.
    */
  @name("snd_seq_get_any_client_info")
  def snd_seq_get_any_client_info(
      handle: Ptr[snd_seq_t],
      client: CInt,
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** set the current client information
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   the client info data to set
    * @return
    *   0 on success otherwise a negative error code
    *
    * Obtains the information of the current client stored on info. client and
    * type fields are ignored.
    */
  @name("snd_seq_set_client_info")
  def snd_seq_set_client_info(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** query the next client
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   query pattern and result
    *
    * Queries the next client. The search begins at the client with an id one
    * greater than client field in info. If a client is found, its attributes
    * are stored in info, and zero is returned. Otherwise returns a negative
    * error code.
    */
  @name("snd_seq_query_next_client")
  def snd_seq_query_next_client(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_client_info_t]
  ): CInt = extern

  /** Get UMP Endpoint information.
    *
    * @param seq
    *   sequencer handle
    * @param client
    *   client number to query
    * @param info
    *   the pointer to store snd_ump_endpoint_info_t data
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_ump_endpoint_info")
  def snd_seq_get_ump_endpoint_info(
      seq: Ptr[snd_seq_t],
      client: CInt,
      info: CVoidPtr
  ): CInt = extern

  /** Get UMP Block information.
    *
    * @param seq
    *   sequencer handle
    * @param client
    *   sequencer client number to query
    * @param blk
    *   UMP block number (0-based) to query
    * @param info
    *   the pointer to store snd_ump_block_info_t data
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_ump_block_info")
  def snd_seq_get_ump_block_info(
      seq: Ptr[snd_seq_t],
      client: CInt,
      blk: CInt,
      info: CVoidPtr
  ): CInt = extern

  /** Set UMP Endpoint information to the current client.
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   the pointer to send snd_ump_endpoint_info_t data
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_ump_endpoint_info")
  def snd_seq_set_ump_endpoint_info(seq: Ptr[snd_seq_t], info: CVoidPtr): CInt =
    extern

  /** Set UMP Block information to the current client.
    *
    * @param seq
    *   sequencer handle
    * @param blk
    *   UMP block number (0-based) to send
    * @param info
    *   the pointer to send snd_ump_block_info_t data
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_ump_block_info")
  def snd_seq_set_ump_block_info(
      seq: Ptr[snd_seq_t],
      blk: CInt,
      info: CVoidPtr
  ): CInt = extern

  /** get size of snd_seq_client_pool_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_client_pool_sizeof")
  def snd_seq_client_pool_sizeof(): CSize = extern

  /** allocate an empty snd_seq_client_pool_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_client_pool_malloc")
  def snd_seq_client_pool_malloc(ptr: Ptr[Ptr[snd_seq_client_pool_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_client_pool_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_client_pool_free")
  def snd_seq_client_pool_free(ptr: Ptr[snd_seq_client_pool_t]): Unit = extern

  /** copy one snd_seq_client_pool_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_client_pool_copy")
  def snd_seq_client_pool_copy(
      dst: Ptr[snd_seq_client_pool_t],
      src: Ptr[snd_seq_client_pool_t]
  ): Unit = extern

  /** Get the client id of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @return
    *   client id
    */
  @name("snd_seq_client_pool_get_client")
  def snd_seq_client_pool_get_client(info: Ptr[snd_seq_client_pool_t]): CInt =
    extern

  /** Get the output pool size of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @return
    *   output pool size
    */
  @name("snd_seq_client_pool_get_output_pool")
  def snd_seq_client_pool_get_output_pool(
      info: Ptr[snd_seq_client_pool_t]
  ): CSize = extern

  /** Get the input pool size of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @return
    *   input pool size
    */
  @name("snd_seq_client_pool_get_input_pool")
  def snd_seq_client_pool_get_input_pool(
      info: Ptr[snd_seq_client_pool_t]
  ): CSize = extern

  /** Get the output room size of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @return
    *   output room size
    */
  @name("snd_seq_client_pool_get_output_room")
  def snd_seq_client_pool_get_output_room(
      info: Ptr[snd_seq_client_pool_t]
  ): CSize = extern

  /** Get the available size on output pool of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @return
    *   available output size
    */
  @name("snd_seq_client_pool_get_output_free")
  def snd_seq_client_pool_get_output_free(
      info: Ptr[snd_seq_client_pool_t]
  ): CSize = extern

  /** Get the available size on input pool of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @return
    *   available input size
    */
  @name("snd_seq_client_pool_get_input_free")
  def snd_seq_client_pool_get_input_free(
      info: Ptr[snd_seq_client_pool_t]
  ): CSize = extern

  /** Set the output pool size of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @param size
    *   output pool size
    */
  @name("snd_seq_client_pool_set_output_pool")
  def snd_seq_client_pool_set_output_pool(
      info: Ptr[snd_seq_client_pool_t],
      size: CSize
  ): Unit = extern

  /** Set the input pool size of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @param size
    *   input pool size
    */
  @name("snd_seq_client_pool_set_input_pool")
  def snd_seq_client_pool_set_input_pool(
      info: Ptr[snd_seq_client_pool_t],
      size: CSize
  ): Unit = extern

  /** Set the output room size of a queue_info container.
    *
    * @param info
    *   client_pool container
    * @param size
    *   output room size
    */
  @name("snd_seq_client_pool_set_output_room")
  def snd_seq_client_pool_set_output_room(
      info: Ptr[snd_seq_client_pool_t],
      size: CSize
  ): Unit = extern

  /** obtain the pool information of the current client
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   information to be stored
    */
  @name("snd_seq_get_client_pool")
  def snd_seq_get_client_pool(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_client_pool_t]
  ): CInt = extern

  /** set the pool information
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   information to update
    *
    * Sets the pool information of the current client. The client field in info
    * is replaced automatically with the current id.
    */
  @name("snd_seq_set_client_pool")
  def snd_seq_set_client_pool(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_client_pool_t]
  ): CInt = extern

}
