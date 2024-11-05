package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.pollfd
import gweb.alsa.Iota

/** MIDI Sequencer Interface. See Sequencer interface page for more details.
  */
@link("asound")
@extern
object MIDISequencer {

  /** dlsym version for interface entry callback
    */
  inline val SND_SEQ_DLSYM_VERSION = "_dlsym_seq_001"

  /** sequencer opening stream types open for output (write)
    */
  inline val SND_SEQ_OPEN_OUTPUT = 1

  /** open for input (read)
    */
  inline val SND_SEQ_OPEN_INPUT = 2

  /** open for both input and output (read/write)
    */
  inline val SND_SEQ_OPEN_DUPLEX = (SND_SEQ_OPEN_OUTPUT | SND_SEQ_OPEN_INPUT)

  /** sequencer opening mode non-blocking mode (flag to open mode)
    */
  inline val SND_SEQ_NONBLOCK = 0x0001

  /** special client (port) ids unknown source
    */
  inline val SND_SEQ_ADDRESS_UNKNOWN = 253

  /** send event to all subscribed ports
    */
  inline val SND_SEQ_ADDRESS_SUBSCRIBERS = 254

  /** send event to all queues/clients/ports/channels
    */
  inline val SND_SEQ_ADDRESS_BROADCAST = 255

  /** known client numbers system client
    */
  inline val SND_SEQ_CLIENT_SYSTEM = 0

  /** Sequencer handle
    */
  type snd_seq_t = CStruct0

  /** system information container
    */
  type snd_seq_system_info_t = CStruct0

  /** sequencer handle type
    */
  class snd_seq_type_t private (val value: CInt) extends AnyVal

  object snd_seq_type_t extends Iota {

    /** hardware */
    val SND_SEQ_TYPE_HW = iota

    /** shared memory (NYI) */
    val SND_SEQ_TYPE_SHM = iota

    /** network (NYI) */
    val SND_SEQ_TYPE_INET = iota

  }

  /** Open the ALSA sequencer.
    *
    * @param seqp
    *   Pointer to a snd_seq_t pointer. This pointer must be kept and passed to
    *   most of the other sequencer functions.
    * @param name
    *   The sequencer's "name". This is not a name you make up for your own
    *   purposes; it has special significance to the ALSA library. Usually you
    *   need to pass "default" here.
    * @param streams
    *   The read/write mode of the sequencer. Can be one of three values:
    *   SND_SEQ_OPEN_OUTPUT - open the sequencer for output only
    *   SND_SEQ_OPEN_INPUT - open the sequencer for input only
    *   SND_SEQ_OPEN_DUPLEX - open the sequencer for output and input
    * @return
    *   0 on success otherwise a negative error code
    *
    * Creates a new handle and opens a connection to the kernel sequencer
    * interface. After a client is created successfully, an event with
    * SND_SEQ_EVENT_CLIENT_START is broadcast to announce port.
    */
  @name("snd_seq_open")
  def snd_seq_open(
      handle: Ptr[Ptr[snd_seq_t]],
      name: Ptr[CChar],
      streams: CInt,
      mode: CInt
  ): CInt = extern

  /** Open the ALSA sequencer using local configuration.
    *
    * @param seqp
    *   Pointer to a snd_seq_t pointer.
    * @param name
    *   The name to open
    * @param streams
    *   The read/write mode of the sequencer.
    * @param mode
    *   Optional modifier
    * @param lconf
    *   Local configuration
    * @return
    *   0 on success otherwise a negative error code
    *
    * See the snd_seq_open() function for further details. The extension is that
    * the given configuration is used to resolve abstract name.
    */
  @name("snd_seq_open_lconf")
  def snd_seq_open_lconf(
      handle: Ptr[Ptr[snd_seq_t]],
      name: Ptr[CChar],
      streams: CInt,
      mode: CInt,
      lconf: Ptr[snd_config_t]
  ): CInt = extern

  /** get identifier of sequencer handle
    *
    * @param seq
    *   sequencer handle
    * @return
    *   ASCII identifier of sequencer handle
    *
    * Returns the ASCII identifier of the given sequencer handle. It's the same
    * identifier specified in snd_seq_open().
    */
  @name("snd_seq_name")
  def snd_seq_name(seq: Ptr[snd_seq_t]): Ptr[CChar] = extern

  /** get type of sequencer handle
    *
    * @param seq
    *   sequencer handle
    * @return
    *   type of sequencer handle
    *
    * Returns the type snd_seq_type_t of the given sequencer handle.
    */
  @name("snd_seq_type")
  def snd_seq_type(seq: Ptr[snd_seq_t]): snd_seq_type_t = extern

  /** Close the sequencer.
    *
    * @param seq
    *   Handle returned from snd_seq_open()
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the sequencer client and releases its resources. After a client is
    * closed, an event with SND_SEQ_EVENT_CLIENT_EXIT is broadcast to announce
    * port. The connection between other clients are disconnected. Call this
    * just before exiting your program.
    */
  @name("snd_seq_close")
  def snd_seq_close(handle: Ptr[snd_seq_t]): CInt = extern

  /** Returns the number of poll descriptors.
    *
    * @param seq
    *   sequencer handle
    * @param events
    *   the poll events to be checked (POLLIN and POLLOUT)
    * @return
    *   the number of poll descriptors.
    *
    * Get the number of poll descriptors. The polling events to be checked can
    * be specified by the second argument. When both input and output are
    * checked, pass POLLIN|POLLOUT
    */
  @name("snd_seq_poll_descriptors_count")
  def snd_seq_poll_descriptors_count(
      handle: Ptr[snd_seq_t],
      events: CShort
  ): CInt = extern

  /** Get poll descriptors.
    *
    * @param seq
    *   sequencer handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @param events
    *   polling events to be checked (POLLIN and POLLOUT)
    * @return
    *   count of filled descriptors
    *
    * Get poll descriptors assigned to the sequencer handle. Since a sequencer
    * handle can duplex streams, you need to set which direction(s) is/are
    * polled in events argument. When POLLIN bit is specified, the incoming
    * events to the ports are checked.
    *
    * To check the returned poll-events, call snd_seq_poll_descriptors_revents()
    * instead of reading the pollfd structs directly.
    */
  @name("snd_seq_poll_descriptors")
  def snd_seq_poll_descriptors(
      handle: Ptr[snd_seq_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt,
      events: CShort
  ): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param seq
    *   sequencer handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_seq_poll_descriptors_revents")
  def snd_seq_poll_descriptors_revents(
      seq: Ptr[snd_seq_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** Set nonblock mode.
    *
    * @param seq
    *   sequencer handle
    * @param nonblock
    *   0 = block, 1 = nonblock mode
    * @return
    *   0 on success otherwise a negative error code
    *
    * Change the blocking mode of the given client. In block mode, the client
    * falls into sleep when it fills the output memory pool with full events.
    * The client will be woken up after a certain amount of free space becomes
    * available.
    */
  @name("snd_seq_nonblock")
  def snd_seq_nonblock(handle: Ptr[snd_seq_t], nonblock: CInt): CInt = extern

  /** Get the client id.
    *
    * @param seq
    *   sequencer handle
    * @return
    *   the client id
    *
    * Returns the id of the specified client. If an error occurs, function
    * returns the negative error code. A client id is necessary to inquiry or to
    * set the client information. A user client is assigned from 128 to 191.
    */
  @name("snd_seq_client_id")
  def snd_seq_client_id(handle: Ptr[snd_seq_t]): CInt = extern

  /** Return the size of output buffer.
    *
    * @param seq
    *   sequencer handle
    * @return
    *   the size of output buffer in bytes
    *
    * Obtains the size of output buffer. This buffer is used to store decoded
    * byte-stream of output events before transferring to sequencer.
    */
  @name("snd_seq_get_output_buffer_size")
  def snd_seq_get_output_buffer_size(handle: Ptr[snd_seq_t]): CSize = extern

  /** Return the size of input buffer.
    *
    * @param seq
    *   sequencer handle
    * @return
    *   the size of input buffer in bytes
    *
    * Obtains the size of input buffer. This buffer is used to read byte-stream
    * of input events from sequencer.
    */
  @name("snd_seq_get_input_buffer_size")
  def snd_seq_get_input_buffer_size(handle: Ptr[snd_seq_t]): CSize = extern

  /** Change the size of output buffer.
    *
    * @param seq
    *   sequencer handle
    * @param size
    *   the size of output buffer to be changed in bytes
    * @return
    *   0 on success otherwise a negative error code
    *
    * Changes the size of output buffer.
    */
  @name("snd_seq_set_output_buffer_size")
  def snd_seq_set_output_buffer_size(
      handle: Ptr[snd_seq_t],
      size: CSize
  ): CInt = extern

  /** Resize the input buffer.
    *
    * @param seq
    *   sequencer handle
    * @param size
    *   the size of input buffer to be changed in bytes
    * @return
    *   0 on success otherwise a negative error code
    *
    * Changes the size of input buffer.
    */
  @name("snd_seq_set_input_buffer_size")
  def snd_seq_set_input_buffer_size(handle: Ptr[snd_seq_t], size: CSize): CInt =
    extern

  /** Get size of snd_seq_system_info_t.
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_system_info_sizeof")
  def snd_seq_system_info_sizeof(): CSize = extern

  /** Allocate an empty snd_seq_system_info_t using standard malloc.
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_system_info_malloc")
  def snd_seq_system_info_malloc(ptr: Ptr[Ptr[snd_seq_system_info_t]]): CInt =
    extern

  /** Frees a previously allocated snd_seq_system_info_t.
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_system_info_free")
  def snd_seq_system_info_free(ptr: Ptr[snd_seq_system_info_t]): Unit = extern

  /** Copy one snd_seq_system_info_t to another.
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_system_info_copy")
  def snd_seq_system_info_copy(
      dst: Ptr[snd_seq_system_info_t],
      src: Ptr[snd_seq_system_info_t]
  ): Unit = extern

  /** Get maximum number of queues.
    *
    * @param info
    *   snd_seq_system_info_t container
    * @return
    *   maximum number of queues
    */
  @name("snd_seq_system_info_get_queues")
  def snd_seq_system_info_get_queues(info: Ptr[snd_seq_system_info_t]): CInt =
    extern

  /** Get maximum number of clients.
    *
    * @param info
    *   snd_seq_system_info_t container
    * @return
    *   maximum number of clients
    */
  @name("snd_seq_system_info_get_clients")
  def snd_seq_system_info_get_clients(info: Ptr[snd_seq_system_info_t]): CInt =
    extern

  /** Get maximum number of ports.
    *
    * @param info
    *   snd_seq_system_info_t container
    * @return
    *   maximum number of ports
    */
  @name("snd_seq_system_info_get_ports")
  def snd_seq_system_info_get_ports(info: Ptr[snd_seq_system_info_t]): CInt =
    extern

  /** Get maximum number of channels.
    *
    * @param info
    *   snd_seq_system_info_t container
    * @return
    *   maximum number of channels
    */
  @name("snd_seq_system_info_get_channels")
  def snd_seq_system_info_get_channels(info: Ptr[snd_seq_system_info_t]): CInt =
    extern

  /** Get the current number of clients.
    *
    * @param info
    *   snd_seq_system_info_t container
    * @return
    *   current number of clients
    */
  @name("snd_seq_system_info_get_cur_clients")
  def snd_seq_system_info_get_cur_clients(
      info: Ptr[snd_seq_system_info_t]
  ): CInt = extern

  /** Get the current number of queues.
    *
    * @param info
    *   snd_seq_system_info_t container
    * @return
    *   current number of queues
    */
  @name("snd_seq_system_info_get_cur_queues")
  def snd_seq_system_info_get_cur_queues(
      info: Ptr[snd_seq_system_info_t]
  ): CInt = extern

  /** obtain the sequencer system information
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   the pointer to be stored
    * @return
    *   0 on success otherwise a negative error code
    *
    * Stores the global system information of ALSA sequencer system. The
    * returned data contains the maximum available numbers of queues, clients,
    * ports and channels.
    */
  @name("snd_seq_system_info")
  def snd_seq_system_info(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_system_info_t]
  ): CInt = extern

}
