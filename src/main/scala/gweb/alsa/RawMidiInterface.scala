package gweb.alsa

import scala.scalanative.unsafe.*
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.GlobalDefinesAndFunctions.snd_htimestamp_t
import scala.scalanative.posix.time.timespec

/** The RawMidi Interface. See RawMidi interface page for more details.
  */
@link("asound")
@extern
object RawMidiInterface {

  /** dlsym version for interface entry callback
    */
  inline val SND_RAWMIDI_DLSYM_VERSION = "_dlsym_rawmidi_001"

  /** Append (flag to open mode)
    */
  inline val SND_RAWMIDI_APPEND = true

  /** Non blocking mode (flag to open mode)
    */
  inline val SND_RAWMIDI_NONBLOCK = true

  /** Write sync mode (Flag to open mode)
    */
  inline val SND_RAWMIDI_SYNC = true

  /** rawmidi info bit flags
    */
  inline val SND_RAWMIDI_INFO_UMP = 0x00000008 /* rawmidi is UMP */

  /** RawMidi information container
    */
  type snd_rawmidi_info_t = CStruct0

  /** RawMidi settings container
    */
  type snd_rawmidi_params_t = CStruct0

  /** RawMidi status container
    */
  type snd_rawmidi_status_t = CStruct0

  /** RawMidi handle
    */
  type snd_rawmidi_t = CStruct0

  /** RawMidi stream (direction)
    */
  class snd_rawmidi_stream_t private (val value: CInt) extends AnyVal

  object snd_rawmidi_stream_t extends Iota {

    /** Output stream */
    val SND_RAWMIDI_STREAM_OUTPUT = iota(0)

    /** Input stream */
    val SND_RAWMIDI_STREAM_INPUT = iota

    /** */
    val SND_RAWMIDI_STREAM_LAST = iota(SND_RAWMIDI_STREAM_INPUT)

  }

  /** RawMidi type
    */
  class snd_rawmidi_type_t private (val value: CInt) extends AnyVal

  object snd_rawmidi_type_t extends Iota {

    /** Kernel level RawMidi */
    val SND_RAWMIDI_TYPE_HW = iota

    /** Shared memory client RawMidi (not yet implemented) */
    val SND_RAWMIDI_TYPE_SHM = iota

    /** INET client RawMidi (not yet implemented) */
    val SND_RAWMIDI_TYPE_INET = iota

    /** Virtual (sequencer) RawMidi */
    val SND_RAWMIDI_TYPE_VIRTUAL = iota

  }

  /** Type of clock used with rawmidi timestamp
    */
  class snd_rawmidi_clock_t private (val value: CInt) extends AnyVal

  object snd_rawmidi_clock_t extends Iota {

    /** */
    val SND_RAWMIDI_CLOCK_NONE = iota(0)

    /** */
    val SND_RAWMIDI_CLOCK_REALTIME = iota(1)

    /** */
    val SND_RAWMIDI_CLOCK_MONOTONIC = iota(2)

    /** */
    val SND_RAWMIDI_CLOCK_MONOTONIC_RAW = iota(3)

  }

  /** Select the read mode (standard or with timestamps)
    */
  class snd_rawmidi_read_mode_t private (val value: CInt) extends AnyVal

  object snd_rawmidi_read_mode_t extends Iota {

    /** */
    val SND_RAWMIDI_READ_STANDARD = iota(0)

    /** */
    val SND_RAWMIDI_READ_TSTAMP = iota(1)

  }

  /** Opens a new connection to the RawMidi interface.
    *
    * @param inputp
    *   Returned input handle (NULL if not wanted)
    * @param outputp
    *   Returned output handle (NULL if not wanted)
    * @param name
    *   ASCII identifier of the RawMidi handle
    * @param mode
    *   Open mode
    * @return
    *   0 on success otherwise a negative error code
    *
    * Opens a new connection to the RawMidi interface specified with an ASCII
    * identifier and mode.
    */
  @name("snd_rawmidi_open")
  def snd_rawmidi_open(
      in_rmidi: Ptr[Ptr[snd_rawmidi_t]],
      out_rmidi: Ptr[Ptr[snd_rawmidi_t]],
      name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** Opens a new connection to the RawMidi interface using local configuration.
    *
    * @param inputp
    *   Returned input handle (NULL if not wanted)
    * @param outputp
    *   Returned output handle (NULL if not wanted)
    * @param name
    *   ASCII identifier of the RawMidi handle
    * @param mode
    *   Open mode
    * @param lconf
    *   Local configuration
    * @return
    *   0 on success otherwise a negative error code
    *
    * Opens a new connection to the RawMidi interface specified with an ASCII
    * identifier and mode.
    */
  @name("snd_rawmidi_open_lconf")
  def snd_rawmidi_open_lconf(
      in_rmidi: Ptr[Ptr[snd_rawmidi_t]],
      out_rmidi: Ptr[Ptr[snd_rawmidi_t]],
      name: Ptr[CChar],
      mode: CInt,
      lconf: Ptr[snd_config_t]
  ): CInt = extern

  /** close RawMidi handle
    *
    * @param rawmidi
    *   RawMidi handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified RawMidi handle and frees all associated resources.
    */
  @name("snd_rawmidi_close")
  def snd_rawmidi_close(rmidi: Ptr[snd_rawmidi_t]): CInt = extern

  /** get count of poll descriptors for RawMidi handle
    *
    * @param rawmidi
    *   RawMidi handle
    * @return
    *   count of poll descriptors
    */
  @name("snd_rawmidi_poll_descriptors_count")
  def snd_rawmidi_poll_descriptors_count(rmidi: Ptr[snd_rawmidi_t]): CInt =
    extern

  /** get poll descriptors
    *
    * @param rawmidi
    *   RawMidi handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    */
  @name("snd_rawmidi_poll_descriptors")
  def snd_rawmidi_poll_descriptors(
      rmidi: Ptr[snd_rawmidi_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param rawmidi
    *   rawmidi RawMidi handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_rawmidi_poll_descriptors_revents")
  def snd_rawmidi_poll_descriptors_revents(
      rawmidi: Ptr[snd_rawmidi_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revent: Ptr[CUnsignedShort]
  ): CInt = extern

  /** set nonblock mode
    *
    * @param rawmidi
    *   RawMidi handle
    * @param nonblock
    *   0 = block, 1 = nonblock mode
    * @return
    *   0 on success otherwise a negative error code
    *
    * The nonblock mode cannot be used when the stream is in SND_RAWMIDI_APPEND
    * state.
    */
  @name("snd_rawmidi_nonblock")
  def snd_rawmidi_nonblock(rmidi: Ptr[snd_rawmidi_t], nonblock: CInt): CInt =
    extern

  /** get size of the snd_rawmidi_info_t structure in bytes
    *
    * @return
    *   size of the snd_rawmidi_info_t structure in bytes
    */
  @name("snd_rawmidi_info_sizeof")
  def snd_rawmidi_info_sizeof(): CSize = extern

  /** allocate a new snd_rawmidi_info_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_rawmidi_params_t structure using the standard malloc C
    * library function.
    */
  @name("snd_rawmidi_info_malloc")
  def snd_rawmidi_info_malloc(ptr: Ptr[Ptr[snd_rawmidi_info_t]]): CInt = extern

  /** frees the snd_rawmidi_info_t structure
    *
    * @param info
    *   pointer to the snd_rawmidi_info_t structure to free
    *
    * Frees the given snd_rawmidi_params_t structure using the standard free C
    * library function.
    */
  @name("snd_rawmidi_info_free")
  def snd_rawmidi_info_free(obj: Ptr[snd_rawmidi_info_t]): Unit = extern

  /** copy one snd_rawmidi_info_t structure to another
    *
    * @param dst
    *   destination snd_rawmidi_info_t structure
    * @param src
    *   source snd_rawmidi_info_t structure
    */
  @name("snd_rawmidi_info_copy")
  def snd_rawmidi_info_copy(
      dst: Ptr[snd_rawmidi_info_t],
      src: Ptr[snd_rawmidi_info_t]
  ): Unit = extern

  /** get rawmidi device number
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi device number
    */
  @name("snd_rawmidi_info_get_device")
  def snd_rawmidi_info_get_device(obj: Ptr[snd_rawmidi_info_t]): CUnsignedInt =
    extern

  /** get rawmidi subdevice number
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi subdevice number
    */
  @name("snd_rawmidi_info_get_subdevice")
  def snd_rawmidi_info_get_subdevice(
      obj: Ptr[snd_rawmidi_info_t]
  ): CUnsignedInt = extern

  /** get rawmidi stream identification
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi stream identification
    */
  @name("snd_rawmidi_info_get_stream")
  def snd_rawmidi_info_get_stream(
      obj: Ptr[snd_rawmidi_info_t]
  ): snd_rawmidi_stream_t = extern

  /** get rawmidi card number
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi card number
    */
  @name("snd_rawmidi_info_get_card")
  def snd_rawmidi_info_get_card(obj: Ptr[snd_rawmidi_info_t]): CInt = extern

  /** get rawmidi flags
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi flags
    */
  @name("snd_rawmidi_info_get_flags")
  def snd_rawmidi_info_get_flags(obj: Ptr[snd_rawmidi_info_t]): CUnsignedInt =
    extern

  /** get rawmidi hardware driver identifier
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi hardware driver identifier
    */
  @name("snd_rawmidi_info_get_id")
  def snd_rawmidi_info_get_id(obj: Ptr[snd_rawmidi_info_t]): Ptr[CChar] = extern

  /** get rawmidi hardware driver name
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi hardware driver name
    */
  @name("snd_rawmidi_info_get_name")
  def snd_rawmidi_info_get_name(obj: Ptr[snd_rawmidi_info_t]): Ptr[CChar] =
    extern

  /** get rawmidi subdevice name
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi subdevice name
    */
  @name("snd_rawmidi_info_get_subdevice_name")
  def snd_rawmidi_info_get_subdevice_name(
      obj: Ptr[snd_rawmidi_info_t]
  ): Ptr[CChar] = extern

  /** get rawmidi count of subdevices
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi count of subdevices
    */
  @name("snd_rawmidi_info_get_subdevices_count")
  def snd_rawmidi_info_get_subdevices_count(
      obj: Ptr[snd_rawmidi_info_t]
  ): CUnsignedInt = extern

  /** get rawmidi available count of subdevices
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @return
    *   rawmidi available count of subdevices
    */
  @name("snd_rawmidi_info_get_subdevices_avail")
  def snd_rawmidi_info_get_subdevices_avail(
      obj: Ptr[snd_rawmidi_info_t]
  ): CUnsignedInt = extern

  /** set rawmidi device number
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @param val
    *   device number
    */
  @name("snd_rawmidi_info_set_device")
  def snd_rawmidi_info_set_device(
      obj: Ptr[snd_rawmidi_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** set rawmidi subdevice number
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @param val
    *   subdevice number
    */
  @name("snd_rawmidi_info_set_subdevice")
  def snd_rawmidi_info_set_subdevice(
      obj: Ptr[snd_rawmidi_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** set rawmidi stream identifier
    *
    * @param info
    *   pointer to a snd_rawmidi_info_t structure
    * @param val
    *   rawmidi stream identifier
    */
  @name("snd_rawmidi_info_set_stream")
  def snd_rawmidi_info_set_stream(
      obj: Ptr[snd_rawmidi_info_t],
      `val`: snd_rawmidi_stream_t
  ): Unit = extern

  /** get information about RawMidi handle
    *
    * @param rawmidi
    *   RawMidi handle
    * @param info
    *   pointer to a snd_rawmidi_info_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_info")
  def snd_rawmidi_info(
      rmidi: Ptr[snd_rawmidi_t],
      info: Ptr[snd_rawmidi_info_t]
  ): CInt = extern

  /** get size of the snd_rawmidi_params_t structure in bytes
    *
    * @return
    *   size of the snd_rawmidi_params_t structure in bytes
    */
  @name("snd_rawmidi_params_sizeof")
  def snd_rawmidi_params_sizeof(): CSize = extern

  /** allocate the snd_rawmidi_params_t structure
    *
    * @param params
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_rawmidi_params_t structure using the standard malloc C
    * library function.
    */
  @name("snd_rawmidi_params_malloc")
  def snd_rawmidi_params_malloc(ptr: Ptr[Ptr[snd_rawmidi_params_t]]): CInt =
    extern

  /** frees the snd_rawmidi_params_t structure
    *
    * @param params
    *   pointer to the snd_rawmidi_params_t structure to free
    *
    * Frees the given snd_rawmidi_params_t structure using the standard free C
    * library function.
    */
  @name("snd_rawmidi_params_free")
  def snd_rawmidi_params_free(obj: Ptr[snd_rawmidi_params_t]): Unit = extern

  /** copy one snd_rawmidi_params_t structure to another
    *
    * @param dst
    *   destination snd_rawmidi_params_t structure
    * @param src
    *   source snd_rawmidi_params_t structure
    */
  @name("snd_rawmidi_params_copy")
  def snd_rawmidi_params_copy(
      dst: Ptr[snd_rawmidi_params_t],
      src: Ptr[snd_rawmidi_params_t]
  ): Unit = extern

  /** set rawmidi I/O ring buffer size
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to a snd_rawmidi_params_t structure
    * @param val
    *   size in bytes
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_params_set_buffer_size")
  def snd_rawmidi_params_set_buffer_size(
      rmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t],
      `val`: CSize
  ): CInt = extern

  /** get rawmidi I/O ring buffer size
    *
    * @param params
    *   pointer to a snd_rawmidi_params_t structure
    * @return
    *   size of rawmidi I/O ring buffer in bytes
    */
  @name("snd_rawmidi_params_get_buffer_size")
  def snd_rawmidi_params_get_buffer_size(
      params: Ptr[snd_rawmidi_params_t]
  ): CSize = extern

  /** set minimum available bytes in rawmidi I/O ring buffer for wakeup
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to a snd_rawmidi_params_t structure
    * @param val
    *   desired value
    */
  @name("snd_rawmidi_params_set_avail_min")
  def snd_rawmidi_params_set_avail_min(
      rmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t],
      `val`: CSize
  ): CInt = extern

  /** get minimum available bytes in rawmidi I/O ring buffer for wakeup
    *
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @return
    *   minimum available bytes
    */
  @name("snd_rawmidi_params_get_avail_min")
  def snd_rawmidi_params_get_avail_min(
      params: Ptr[snd_rawmidi_params_t]
  ): CSize = extern

  /** set no-active-sensing action on snd_rawmidi_close()
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @param val
    *   value: 0 = enable to send the active sensing message, 1 = disable
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_params_set_no_active_sensing")
  def snd_rawmidi_params_set_no_active_sensing(
      rmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t],
      `val`: CInt
  ): CInt = extern

  /** get no-active-sensing action status
    *
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @return
    *   the current status (0 = enable, 1 = disable the active sensing message)
    */
  @name("snd_rawmidi_params_get_no_active_sensing")
  def snd_rawmidi_params_get_no_active_sensing(
      params: Ptr[snd_rawmidi_params_t]
  ): CInt = extern

  /** set read mode
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @param val
    *   type of read_mode
    * @return
    *   0 on success, otherwise a negative error code.
    *
    * Notable error codes: -EINVAL - "val" is invalid -ENOTSUP - mode is not
    * supported
    */
  @name("snd_rawmidi_params_set_read_mode")
  def snd_rawmidi_params_set_read_mode(
      rawmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t],
      `val`: snd_rawmidi_read_mode_t
  ): CInt = extern

  /** get current read mode
    *
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @return
    *   the current read mode (see enum)
    */
  @name("snd_rawmidi_params_get_read_mode")
  def snd_rawmidi_params_get_read_mode(
      params: Ptr[snd_rawmidi_params_t]
  ): snd_rawmidi_read_mode_t = extern

  /** sets clock type for tstamp type framing
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @param val
    *   one of the SND_RAWMIDI_CLOCK_* constants
    * @return
    *   0 on success, otherwise a negative error code.
    *
    * Notable error codes: -EINVAL - "val" is invalid -ENOTSUP - Kernel is too
    * old to support framing.
    */
  @name("snd_rawmidi_params_set_clock_type")
  def snd_rawmidi_params_set_clock_type(
      rawmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t],
      `val`: snd_rawmidi_clock_t
  ): CInt = extern

  /** get current clock type (for tstamp type framing)
    *
    * @param params
    *   pointer to snd_rawmidi_params_t structure
    * @return
    *   the current clock type (one of the SND_RAWMIDI_CLOCK_* constants)
    */
  @name("snd_rawmidi_params_get_clock_type")
  def snd_rawmidi_params_get_clock_type(
      params: Ptr[snd_rawmidi_params_t]
  ): snd_rawmidi_clock_t = extern

  /** set parameters about rawmidi stream
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to a snd_rawmidi_params_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_params")
  def snd_rawmidi_params(
      rmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t]
  ): CInt = extern

  /** get current parameters about rawmidi stream
    *
    * @param rawmidi
    *   RawMidi handle
    * @param params
    *   pointer to a snd_rawmidi_params_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_params_current")
  def snd_rawmidi_params_current(
      rmidi: Ptr[snd_rawmidi_t],
      params: Ptr[snd_rawmidi_params_t]
  ): CInt = extern

  /** get size of the snd_rawmidi_status_t structure in bytes
    *
    * @return
    *   size of the snd_rawmidi_status_t structure in bytes
    */
  @name("snd_rawmidi_status_sizeof")
  def snd_rawmidi_status_sizeof(): CSize = extern

  /** allocate the snd_rawmidi_status_t structure
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_rawmidi_status_t structure using the standard malloc C
    * library function.
    */
  @name("snd_rawmidi_status_malloc")
  def snd_rawmidi_status_malloc(ptr: Ptr[Ptr[snd_rawmidi_status_t]]): CInt =
    extern

  /** frees the snd_rawmidi_status_t structure
    *
    * @param status
    *   pointer to the snd_rawmidi_status_t structure to free
    *
    * Frees the given snd_rawmidi_status_t structure using the standard free C
    * library function.
    */
  @name("snd_rawmidi_status_free")
  def snd_rawmidi_status_free(obj: Ptr[snd_rawmidi_status_t]): Unit = extern

  /** copy one snd_rawmidi_status_t structure to another
    *
    * @param dst
    *   destination snd_rawmidi_status_t structure
    * @param src
    *   source snd_rawmidi_status_t structure
    */
  @name("snd_rawmidi_status_copy")
  def snd_rawmidi_status_copy(
      dst: Ptr[snd_rawmidi_status_t],
      src: Ptr[snd_rawmidi_status_t]
  ): Unit = extern

  /** get the start timestamp
    *
    * @param status
    *   pointer to a snd_rawmidi_status_t structure
    * @param tstamp
    *   returned timestamp value
    */
  @name("snd_rawmidi_status_get_tstamp")
  def snd_rawmidi_status_get_tstamp(
      obj: Ptr[snd_rawmidi_status_t],
      ptr: Ptr[snd_htimestamp_t]
  ): Unit = extern

  /** get current available bytes in the rawmidi I/O ring buffer
    *
    * @param status
    *   pointer to a snd_rawmidi_status_t structure
    * @return
    *   current available bytes in the rawmidi I/O ring buffer
    */
  @name("snd_rawmidi_status_get_avail")
  def snd_rawmidi_status_get_avail(obj: Ptr[snd_rawmidi_status_t]): CSize =
    extern

  /** get count of xruns
    *
    * @param status
    *   pointer to a snd_rawmidi_status_t structure
    * @return
    *   count of xruns
    */
  @name("snd_rawmidi_status_get_xruns")
  def snd_rawmidi_status_get_xruns(obj: Ptr[snd_rawmidi_status_t]): CSize =
    extern

  /** get status of rawmidi stream
    *
    * @param rawmidi
    *   RawMidi handle
    * @param status
    *   pointer to a snd_rawmidi_status_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_status")
  def snd_rawmidi_status(
      rmidi: Ptr[snd_rawmidi_t],
      status: Ptr[snd_rawmidi_status_t]
  ): CInt = extern

  /** drain all bytes in the rawmidi I/O ring buffer
    *
    * @param rawmidi
    *   RawMidi handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Waits until all MIDI bytes are not drained (sent) to the hardware device.
    */
  @name("snd_rawmidi_drain")
  def snd_rawmidi_drain(rmidi: Ptr[snd_rawmidi_t]): CInt = extern

  /** drop all bytes in the rawmidi I/O ring buffer immediately
    *
    * @param rawmidi
    *   RawMidi handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_rawmidi_drop")
  def snd_rawmidi_drop(rmidi: Ptr[snd_rawmidi_t]): CInt = extern

  /** write MIDI bytes to MIDI stream
    *
    * @param rawmidi
    *   RawMidi handle
    * @param buffer
    *   buffer containing MIDI bytes
    * @param size
    *   output buffer size in bytes
    */
  @name("snd_rawmidi_write")
  def snd_rawmidi_write(
      rmidi: Ptr[snd_rawmidi_t],
      buffer: CVoidPtr,
      size: CSize
  ): CSSize = extern

  /** read MIDI bytes from MIDI stream
    *
    * @param rawmidi
    *   RawMidi handle
    * @param buffer
    *   buffer to store the input MIDI bytes
    * @param size
    *   input buffer size in bytes
    */
  @name("snd_rawmidi_read")
  def snd_rawmidi_read(
      rmidi: Ptr[snd_rawmidi_t],
      buffer: CVoidPtr,
      size: CSize
  ): CSSize = extern

  /** read MIDI bytes from MIDI stream with timestamp
    *
    * @param rawmidi
    *   RawMidi handle
    * @param tstamp
    *   [out] timestamp for the returned MIDI bytes
    * @param buffer
    *   buffer to store the input MIDI bytes
    * @param size
    *   input buffer size in bytes
    */
  @name("snd_rawmidi_tread")
  def snd_rawmidi_tread(
      rmidi: Ptr[snd_rawmidi_t],
      tstamp: Ptr[timespec],
      buffer: CVoidPtr,
      size: CSize
  ): CSSize = extern

  /** get identifier of RawMidi handle
    *
    * @param rawmidi
    *   a RawMidi handle
    * @return
    *   ascii identifier of RawMidi handle
    *
    * Returns the ASCII identifier of given RawMidi handle. It's the same
    * identifier specified in snd_rawmidi_open().
    */
  @name("snd_rawmidi_name")
  def snd_rawmidi_name(rmidi: Ptr[snd_rawmidi_t]): Ptr[CChar] = extern

  /** get type of RawMidi handle
    *
    * @param rawmidi
    *   a RawMidi handle
    * @return
    *   type of RawMidi handle
    *
    * Returns the type snd_rawmidi_type_t of given RawMidi handle.
    */
  @name("snd_rawmidi_type")
  def snd_rawmidi_type(rmidi: Ptr[snd_rawmidi_t]): snd_rawmidi_type_t = extern

  /** get stream (direction) of RawMidi handle
    *
    * @param rawmidi
    *   a RawMidi handle
    * @return
    *   stream of RawMidi handle
    *
    * Returns the stream snd_rawmidi_stream_t of given RawMidi handle.
    */
  @name("snd_rawmidi_stream")
  def snd_rawmidi_stream(rawmidi: Ptr[snd_rawmidi_t]): snd_rawmidi_stream_t =
    extern

}
