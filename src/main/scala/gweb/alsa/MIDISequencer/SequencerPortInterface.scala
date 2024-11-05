package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import gweb.alsa.MIDISequencer.MIDISequencer.snd_seq_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_addr_t

/** Sequencer Port Interface
  */
@link("asound")
@extern
object SequencerPortInterface {

  /** known port numbers system timer port
    */
  inline val SND_SEQ_PORT_SYSTEM_TIMER = 0

  /** system announce port
    */
  inline val SND_SEQ_PORT_SYSTEM_ANNOUNCE = 1

  /** port capabilities (32 bits) readable from this port
    */
  inline val SND_SEQ_PORT_CAP_READ = (1 << 0)

  /** writable to this port
    */
  inline val SND_SEQ_PORT_CAP_WRITE = (1 << 1)

  /** allow read subscriptions
    */
  inline val SND_SEQ_PORT_CAP_SYNC_READ = (1 << 2)

  /** allow write subscriptions
    */
  inline val SND_SEQ_PORT_CAP_SYNC_WRITE = (1 << 3)

  /** allow read/write duplex
    */
  inline val SND_SEQ_PORT_CAP_DUPLEX = (1 << 4)

  /** allow read subscription
    */
  inline val SND_SEQ_PORT_CAP_SUBS_READ = (1 << 5)

  /** allow write subscription
    */
  inline val SND_SEQ_PORT_CAP_SUBS_WRITE = (1 << 6)

  /** routing not allowed
    */
  inline val SND_SEQ_PORT_CAP_NO_EXPORT = (1 << 7)

  /** inactive port
    */
  inline val SND_SEQ_PORT_CAP_INACTIVE = (1 << 8)

  /** UMP Endpoint port
    */
  inline val SND_SEQ_PORT_CAP_UMP_ENDPOINT = (1 << 9)

  /** port direction Unknown
    */
  inline val SND_SEQ_PORT_DIR_UNKNOWN = 0

  /** Input only
    */
  inline val SND_SEQ_PORT_DIR_INPUT = 1

  /** Output only
    */
  inline val SND_SEQ_PORT_DIR_OUTPUT = 2

  /** Input/output bidirectional
    */
  inline val SND_SEQ_PORT_DIR_BIDIRECTION = 3

  /** Messages sent from/to this port have device-specific semantics.
    */
  inline val SND_SEQ_PORT_TYPE_SPECIFIC = (1 << 0)

  /** This port understands MIDI messages.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_GENERIC = (1 << 1)

  /** This port is compatible with the General MIDI specification.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_GM = (1 << 2)

  /** This port is compatible with the Roland GS standard.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_GS = (1 << 3)

  /** This port is compatible with the Yamaha XG specification.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_XG = (1 << 4)

  /** This port is compatible with the Roland MT-32.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_MT32 = (1 << 5)

  /** This port is compatible with the General MIDI 2 specification.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_GM2 = (1 << 6)

  /** This port is a UMP port.
    */
  inline val SND_SEQ_PORT_TYPE_MIDI_UMP = (1 << 7)

  /** This port understands SND_SEQ_EVENT_SAMPLE_xxx messages (these are not
    * MIDI messages).
    */
  inline val SND_SEQ_PORT_TYPE_SYNTH = (1 << 10)

  /** Instruments can be downloaded to this port (with SND_SEQ_EVENT_INSTR_xxx
    * messages sent directly).
    */
  inline val SND_SEQ_PORT_TYPE_DIRECT_SAMPLE = (1 << 11)

  /** Instruments can be downloaded to this port (with SND_SEQ_EVENT_INSTR_xxx
    * messages sent directly or through a queue).
    */
  inline val SND_SEQ_PORT_TYPE_SAMPLE = (1 << 12)

  /** This port is implemented in hardware.
    */
  inline val SND_SEQ_PORT_TYPE_HARDWARE = (1 << 16)

  /** This port is implemented in software.
    */
  inline val SND_SEQ_PORT_TYPE_SOFTWARE = (1 << 17)

  /** Messages sent to this port will generate sounds.
    */
  inline val SND_SEQ_PORT_TYPE_SYNTHESIZER = (1 << 18)

  /** This port may connect to other devices (whose characteristics are not
    * known).
    */
  inline val SND_SEQ_PORT_TYPE_PORT = (1 << 19)

  /** This port belongs to an application, such as a sequencer or editor.
    */
  inline val SND_SEQ_PORT_TYPE_APPLICATION = (1 << 20)

  /** port information container
    */
  type snd_seq_port_info_t = CStruct0

  /** get size of snd_seq_port_info_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_port_info_sizeof")
  def snd_seq_port_info_sizeof(): CSize = extern

  /** allocate an empty snd_seq_port_info_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_port_info_malloc")
  def snd_seq_port_info_malloc(ptr: Ptr[Ptr[snd_seq_port_info_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_port_info_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_port_info_free")
  def snd_seq_port_info_free(ptr: Ptr[snd_seq_port_info_t]): Unit = extern

  /** copy one snd_seq_port_info_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_port_info_copy")
  def snd_seq_port_info_copy(
      dst: Ptr[snd_seq_port_info_t],
      src: Ptr[snd_seq_port_info_t]
  ): Unit = extern

  /** Get client id of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   client id
    */
  @name("snd_seq_port_info_get_client")
  def snd_seq_port_info_get_client(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get port id of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   port id
    */
  @name("snd_seq_port_info_get_port")
  def snd_seq_port_info_get_port(info: Ptr[snd_seq_port_info_t]): CInt = extern

  /** Get client/port address of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   client/port address pointer
    */
  @name("snd_seq_port_info_get_addr")
  def snd_seq_port_info_get_addr(
      info: Ptr[snd_seq_port_info_t]
  ): Ptr[snd_seq_addr_t] = extern

  /** Get the name of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   name string
    */
  @name("snd_seq_port_info_get_name")
  def snd_seq_port_info_get_name(info: Ptr[snd_seq_port_info_t]): Ptr[CChar] =
    extern

  /** Get the capability bits of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   capability bits
    */
  @name("snd_seq_port_info_get_capability")
  def snd_seq_port_info_get_capability(
      info: Ptr[snd_seq_port_info_t]
  ): CUnsignedInt = extern

  /** Get the type bits of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   port type bits
    */
  @name("snd_seq_port_info_get_type")
  def snd_seq_port_info_get_type(info: Ptr[snd_seq_port_info_t]): CUnsignedInt =
    extern

  /** Get the midi channels of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   number of midi channels (default 0)
    */
  @name("snd_seq_port_info_get_midi_channels")
  def snd_seq_port_info_get_midi_channels(
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** Get the midi voices of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   number of midi voices (default 0)
    */
  @name("snd_seq_port_info_get_midi_voices")
  def snd_seq_port_info_get_midi_voices(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get the synth voices of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   number of synth voices (default 0)
    */
  @name("snd_seq_port_info_get_synth_voices")
  def snd_seq_port_info_get_synth_voices(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get the number of read subscriptions of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   number of read subscriptions
    */
  @name("snd_seq_port_info_get_read_use")
  def snd_seq_port_info_get_read_use(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get the number of write subscriptions of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   number of write subscriptions
    */
  @name("snd_seq_port_info_get_write_use")
  def snd_seq_port_info_get_write_use(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get the port-specified mode of a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   1 if port id is specified at creation
    */
  @name("snd_seq_port_info_get_port_specified")
  def snd_seq_port_info_get_port_specified(
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** Get the time-stamping mode of the given port in a port_info container.
    *
    * @param info
    *   port_info container
    * @return
    *   1 if the port updates timestamps of incoming events
    */
  @name("snd_seq_port_info_get_timestamping")
  def snd_seq_port_info_get_timestamping(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get whether the time-stamping of the given port is real-time mode.
    *
    * @param info
    *   port_info container
    * @return
    *   1 if the time-stamping is in the real-time mode
    */
  @name("snd_seq_port_info_get_timestamp_real")
  def snd_seq_port_info_get_timestamp_real(
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** Get the queue id to update timestamps.
    *
    * @param info
    *   port_info container
    * @return
    *   the queue id to get the timestamps
    */
  @name("snd_seq_port_info_get_timestamp_queue")
  def snd_seq_port_info_get_timestamp_queue(
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** Get the direction of the port.
    *
    * @param info
    *   port_info container
    * @return
    *   the direction of the port
    */
  @name("snd_seq_port_info_get_direction")
  def snd_seq_port_info_get_direction(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Get the UMP Group assigned to the port.
    *
    * @param info
    *   port_info container
    * @return
    *   0 for no conversion, or the (1-based) UMP Group number assigned to the
    *   port
    */
  @name("snd_seq_port_info_get_ump_group")
  def snd_seq_port_info_get_ump_group(info: Ptr[snd_seq_port_info_t]): CInt =
    extern

  /** Set the client id of a port_info container.
    *
    * @param info
    *   port_info container
    * @param client
    *   client id
    */
  @name("snd_seq_port_info_set_client")
  def snd_seq_port_info_set_client(
      info: Ptr[snd_seq_port_info_t],
      client: CInt
  ): Unit = extern

  /** Set the port id of a port_info container.
    *
    * @param info
    *   port_info container
    * @param port
    *   port id
    */
  @name("snd_seq_port_info_set_port")
  def snd_seq_port_info_set_port(
      info: Ptr[snd_seq_port_info_t],
      port: CInt
  ): Unit = extern

  /** Set the client/port address of a port_info container.
    *
    * @param info
    *   port_info container
    * @param addr
    *   client/port address
    */
  @name("snd_seq_port_info_set_addr")
  def snd_seq_port_info_set_addr(
      info: Ptr[snd_seq_port_info_t],
      addr: Ptr[snd_seq_addr_t]
  ): Unit = extern

  /** Set the name of a port_info container.
    *
    * @param info
    *   port_info container
    * @param name
    *   name string
    */
  @name("snd_seq_port_info_set_name")
  def snd_seq_port_info_set_name(
      info: Ptr[snd_seq_port_info_t],
      name: Ptr[CChar]
  ): Unit = extern

  /** set the capability bits of a port_info container
    *
    * @param info
    *   port_info container
    * @param capability
    *   capability bits
    */
  @name("snd_seq_port_info_set_capability")
  def snd_seq_port_info_set_capability(
      info: Ptr[snd_seq_port_info_t],
      capability: CUnsignedInt
  ): Unit = extern

  /** Get the type bits of a port_info container.
    *
    * @param info
    *   port_info container
    * @param type
    *   port type bits
    */
  @name("snd_seq_port_info_set_type")
  def snd_seq_port_info_set_type(
      info: Ptr[snd_seq_port_info_t],
      `type`: CUnsignedInt
  ): Unit = extern

  /** set the midi channels of a port_info container
    *
    * @param info
    *   port_info container
    * @param channels
    *   midi channels (default 0)
    */
  @name("snd_seq_port_info_set_midi_channels")
  def snd_seq_port_info_set_midi_channels(
      info: Ptr[snd_seq_port_info_t],
      channels: CInt
  ): Unit = extern

  /** set the midi voices of a port_info container
    *
    * @param info
    *   port_info container
    * @param voices
    *   midi voices (default 0)
    */
  @name("snd_seq_port_info_set_midi_voices")
  def snd_seq_port_info_set_midi_voices(
      info: Ptr[snd_seq_port_info_t],
      voices: CInt
  ): Unit = extern

  /** set the synth voices of a port_info container
    *
    * @param info
    *   port_info container
    * @param voices
    *   synth voices (default 0)
    */
  @name("snd_seq_port_info_set_synth_voices")
  def snd_seq_port_info_set_synth_voices(
      info: Ptr[snd_seq_port_info_t],
      voices: CInt
  ): Unit = extern

  /** Set the port-specified mode of a port_info container.
    *
    * @param info
    *   port_info container
    * @param val
    *   non-zero if specifying the port id at creation
    */
  @name("snd_seq_port_info_set_port_specified")
  def snd_seq_port_info_set_port_specified(
      info: Ptr[snd_seq_port_info_t],
      `val`: CInt
  ): Unit = extern

  /** Set the time-stamping mode of the given port.
    *
    * @param info
    *   port_info container
    * @param enable
    *   non-zero if updating the timestamps of incoming events
    */
  @name("snd_seq_port_info_set_timestamping")
  def snd_seq_port_info_set_timestamping(
      info: Ptr[snd_seq_port_info_t],
      enable: CInt
  ): Unit = extern

  /** Set whether the timestime is updated in the real-time mode.
    *
    * @param info
    *   port_info container
    * @param enable
    *   non-zero if updating the timestamps in real-time mode
    */
  @name("snd_seq_port_info_set_timestamp_real")
  def snd_seq_port_info_set_timestamp_real(
      info: Ptr[snd_seq_port_info_t],
      realtime: CInt
  ): Unit = extern

  /** Set the queue id for timestamping.
    *
    * @param info
    *   port_info container
    * @param queue
    *   the queue id to get timestamps
    */
  @name("snd_seq_port_info_set_timestamp_queue")
  def snd_seq_port_info_set_timestamp_queue(
      info: Ptr[snd_seq_port_info_t],
      queue: CInt
  ): Unit = extern

  /** Set the direction of the port.
    *
    * @param info
    *   port_info container
    * @param direction
    *   the port direction
    */
  @name("snd_seq_port_info_set_direction")
  def snd_seq_port_info_set_direction(
      info: Ptr[snd_seq_port_info_t],
      direction: CInt
  ): Unit = extern

  /** Set the UMP Group assigned to the port.
    *
    * @param info
    *   port_info container
    * @param ump_group
    *   0 for no conversion, or the (1-based) UMP Group number
    */
  @name("snd_seq_port_info_set_ump_group")
  def snd_seq_port_info_set_ump_group(
      info: Ptr[snd_seq_port_info_t],
      ump_group: CInt
  ): Unit = extern

  /** create a sequencer port on the current client
    *
    * @param seq
    *   sequencer handle
    * @param port
    *   port information for the new port
    * @return
    *   0 on success otherwise a negative error code
    *
    * Creates a sequencer port on the current client. The attributes of created
    * port is specified in info argument.
    *
    * The client field in info argument is overwritten with the current client
    * id. The port id to be created can be specified via
    * snd_seq_port_info_set_port_specified. You can get the created port id by
    * reading the port pointer via snd_seq_port_info_get_port.
    *
    * Each port has the capability bit-masks to specify the access capability of
    * the port from other clients. The capability bit flags are defined as
    * follows:
    *
    * Each port has also the type bitmasks defined as follows:
    *
    * A port may contain specific midi channels, midi voices and synth voices.
    * These values could be zero as default.
    */
  @name("snd_seq_create_port")
  def snd_seq_create_port(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** delete a sequencer port on the current client
    *
    * @param seq
    *   sequencer handle
    * @param port
    *   port to be deleted
    * @return
    *   0 on success otherwise a negative error code
    *
    * Deletes the existing sequencer port on the current client.
    */
  @name("snd_seq_delete_port")
  def snd_seq_delete_port(handle: Ptr[snd_seq_t], port: CInt): CInt = extern

  /** obtain the information of a port on the current client
    *
    * @param seq
    *   sequencer handle
    * @param port
    *   port id to get
    * @param info
    *   pointer information returns
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_port_info")
  def snd_seq_get_port_info(
      handle: Ptr[snd_seq_t],
      port: CInt,
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** obtain the information of a port on an arbitrary client
    *
    * @param seq
    *   sequencer handle
    * @param client
    *   client id to get
    * @param port
    *   port id to get
    * @param info
    *   pointer information returns
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_any_port_info")
  def snd_seq_get_any_port_info(
      handle: Ptr[snd_seq_t],
      client: CInt,
      port: CInt,
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** set the information of a port on the current client
    *
    * @param seq
    *   sequencer handle
    * @param port
    *   port to be set
    * @param info
    *   port information to be set
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_port_info")
  def snd_seq_set_port_info(
      handle: Ptr[snd_seq_t],
      port: CInt,
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

  /** query the next matching port
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   query pattern and result
    *
    * Queries the next matching port on the client specified in info argument.
    * The search begins at the next port specified in port field of info
    * argument. For finding the first port at a certain client, give -1.
    *
    * If a matching port is found, its attributes are stored on info and
    * function returns zero. Otherwise, a negative error code is returned.
    */
  @name("snd_seq_query_next_port")
  def snd_seq_query_next_port(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_port_info_t]
  ): CInt = extern

}
