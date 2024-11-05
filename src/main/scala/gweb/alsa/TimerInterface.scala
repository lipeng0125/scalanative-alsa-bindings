package gweb.alsa

import scala.scalanative.unsafe.*
import gweb.alsa.GlobalDefinesAndFunctions.snd_htimestamp_t
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.GlobalDefinesAndFunctions.snd_async_handler_t
import gweb.alsa.GlobalDefinesAndFunctions.snd_async_callback_t

/** Timer Interface. See Timer interface page for more details.
  */
@link("asound")
@extern
object TimerInterface {

  /** dlsym version for interface entry callback
    */
  inline val SND_TIMER_DLSYM_VERSION = "_dlsym_timer_001"

  /** dlsym version for interface entry callback
    */
  inline val SND_TIMER_QUERY_DLSYM_VERSION = "_dlsym_timer_query_001"

  /** global timer - system
    */
  inline val SND_TIMER_GLOBAL_SYSTEM = 0

  /** global timer - RTC
    */
  inline val SND_TIMER_GLOBAL_RTC = 1 /* Obsoleted, due to enough legacy. */

  /** global timer - HPET
    */
  inline val SND_TIMER_GLOBAL_HPET = 2

  /** global timer - HRTIMER
    */
  inline val SND_TIMER_GLOBAL_HRTIMER = 3

  /** timer open mode flag - non-blocking behaviour
    */
  inline val SND_TIMER_OPEN_NONBLOCK = (1 << 0)

  /** use timestamps and event notification - enhanced read
    */
  inline val SND_TIMER_OPEN_TREAD = (1 << 1)

  /** timer read structure
    */
  type snd_timer_read_t = CStruct2[
    /** tick resolution in nanoseconds
      */
    CUnsignedInt, // resolution
    /** count of happened ticks
      */
    CUnsignedInt // ticks
  ]

  /** timer tstamp + event read structure
    */
  type snd_timer_tread_t =
    CStruct3[
      /** Timer event
        */
      snd_timer_event_t,
      /** Time stamp of each event
        */
      snd_htimestamp_t,
      /** Event value
        */
      CUnsignedInt
    ]

  /** timer identification structure
    */
  type snd_timer_id_t = CStruct0

  /** timer global info structure
    */
  type snd_timer_ginfo_t = CStruct0

  /** timer global params structure
    */
  type snd_timer_gparams_t = CStruct0

  /** timer global status structure
    */
  type snd_timer_gstatus_t = CStruct0

  /** timer info structure
    */
  type snd_timer_info_t = CStruct0

  /** timer params structure
    */
  type snd_timer_params_t = CStruct0

  /** timer status structure
    */
  type snd_timer_status_t = CStruct0

  /** timer query handle
    */
  type snd_timer_query_t = CStruct0

  /** timer handle
    */
  type snd_timer_t = CStruct0

  /** timer master class
    */
  class snd_timer_class_t private (val value: CInt) extends AnyVal

  object snd_timer_class_t extends Iota {

    /** invalid */
    val SND_TIMER_CLASS_NONE = iota(-1)

    /** slave timer */
    val SND_TIMER_CLASS_SLAVE = iota(0)

    /** global timer */
    val SND_TIMER_CLASS_GLOBAL = iota

    /** card timer */
    val SND_TIMER_CLASS_CARD = iota

    /** PCM timer */
    val SND_TIMER_CLASS_PCM = iota

    /** last timer */
    val SND_TIMER_CLASS_LAST = iota(SND_TIMER_CLASS_PCM)

  }

  /** timer slave class
    */
  class snd_timer_slave_class_t private (val value: CInt) extends AnyVal

  object snd_timer_slave_class_t extends Iota {

    /** none */
    val SND_TIMER_SCLASS_NONE = iota(0)

    /** for internal use */
    val SND_TIMER_SCLASS_APPLICATION = iota

    /** sequencer timer */
    val SND_TIMER_SCLASS_SEQUENCER = iota

    /** OSS sequencer timer */
    val SND_TIMER_SCLASS_OSS_SEQUENCER = iota

    /** last slave timer */
    val SND_TIMER_SCLASS_LAST = iota(SND_TIMER_SCLASS_OSS_SEQUENCER)

  }

  /** timer read event identification
    */
  class snd_timer_event_t private (val value: CInt) extends AnyVal

  object snd_timer_event_t extends Iota {

    /** */
    val SND_TIMER_EVENT_RESOLUTION = iota(0)

    /** */
    val SND_TIMER_EVENT_TICK = iota

    /** */
    val SND_TIMER_EVENT_START = iota

    /** */
    val SND_TIMER_EVENT_STOP = iota

    /** */
    val SND_TIMER_EVENT_CONTINUE = iota

    /** */
    val SND_TIMER_EVENT_PAUSE = iota

    /** */
    val SND_TIMER_EVENT_EARLY = iota

    /** */
    val SND_TIMER_EVENT_SUSPEND = iota

    /** */
    val SND_TIMER_EVENT_RESUME = iota

    /** */
    val SND_TIMER_EVENT_MSTART = iota(SND_TIMER_EVENT_START + 10)

    /** */
    val SND_TIMER_EVENT_MSTOP = iota(SND_TIMER_EVENT_STOP + 10)

    /** */
    val SND_TIMER_EVENT_MCONTINUE = iota(SND_TIMER_EVENT_CONTINUE + 10)

    /** */
    val SND_TIMER_EVENT_MPAUSE = iota(SND_TIMER_EVENT_PAUSE + 10)

    /** */
    val SND_TIMER_EVENT_MSUSPEND = iota(SND_TIMER_EVENT_SUSPEND + 10)

    /** */
    val SND_TIMER_EVENT_MRESUME = iota(SND_TIMER_EVENT_RESUME + 10)

  }

  /** timer handle type
    */
  class snd_timer_type_t private (val value: CInt) extends AnyVal

  object snd_timer_type_t extends Iota {

    /** Kernel level HwDep */
    val SND_TIMER_TYPE_HW = iota(0)

    /** Shared memory client timer (not yet implemented) */
    val SND_TIMER_TYPE_SHM = iota

    /** INET client timer (not yet implemented) */
    val SND_TIMER_TYPE_INET = iota

  }

  /** Opens a new connection to the timer query interface.
    *
    * @param timer
    *   Returned handle (NULL if not wanted)
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
  @name("snd_timer_query_open")
  def snd_timer_query_open(
      handle: Ptr[Ptr[snd_timer_query_t]],
      name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** Opens a new connection to the timer query interface using local
    * configuration.
    *
    * @param timer
    *   Returned handle (NULL if not wanted)
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
  @name("snd_timer_query_open_lconf")
  def snd_timer_query_open_lconf(
      handle: Ptr[Ptr[snd_timer_query_t]],
      name: Ptr[CChar],
      mode: CInt,
      lconf: Ptr[snd_config_t]
  ): CInt = extern

  /** close timer query handle
    *
    * @param timer
    *   timer handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified timer handle and frees all associated resources.
    */
  @name("snd_timer_query_close")
  def snd_timer_query_close(handle: Ptr[snd_timer_query_t]): CInt = extern

  /** obtain the next timer identification
    *
    * @param timer
    *   timer handle
    * @param tid
    *   timer identification
    * @return
    *   0 on success otherwise a negative error code
    *
    * if tid->dev_class is -1, then the first device is returned if result
    * tid->dev_class is -1, no more devices are left
    */
  @name("snd_timer_query_next_device")
  def snd_timer_query_next_device(
      handle: Ptr[snd_timer_query_t],
      tid: Ptr[snd_timer_id_t]
  ): CInt = extern

  /** obtain the timer global information
    *
    * @param timer
    *   timer handle
    * @param info
    *   timer information
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_query_info")
  def snd_timer_query_info(
      handle: Ptr[snd_timer_query_t],
      info: Ptr[snd_timer_ginfo_t]
  ): CInt = extern

  /** set the timer global parameters
    *
    * @param timer
    *   timer handle
    * @param params
    *   timer parameters
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_query_params")
  def snd_timer_query_params(
      handle: Ptr[snd_timer_query_t],
      params: Ptr[snd_timer_gparams_t]
  ): CInt = extern

  /** get the timer global status
    *
    * @param timer
    *   timer handle
    * @param status
    *   timer status
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_query_status")
  def snd_timer_query_status(
      handle: Ptr[snd_timer_query_t],
      status: Ptr[snd_timer_gstatus_t]
  ): CInt = extern

  /** Opens a new connection to the timer interface.
    *
    * @param timer
    *   Returned handle (NULL if not wanted)
    * @param name
    *   ASCII identifier of the timer handle
    * @param mode
    *   Open mode
    * @return
    *   0 on success otherwise a negative error code
    *
    * Opens a new connection to the timer interface specified with an ASCII
    * identifier and mode.
    */
  @name("snd_timer_open")
  def snd_timer_open(
      handle: Ptr[Ptr[snd_timer_t]],
      name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** Opens a new connection to the timer interface using local configuration.
    *
    * @param timer
    *   Returned handle (NULL if not wanted)
    * @param name
    *   ASCII identifier of the timer handle
    * @param mode
    *   Open mode
    * @param lconf
    *   Local configuration
    * @return
    *   0 on success otherwise a negative error code
    *
    * Opens a new connection to the timer interface specified with an ASCII
    * identifier and mode.
    */
  @name("snd_timer_open_lconf")
  def snd_timer_open_lconf(
      handle: Ptr[Ptr[snd_timer_t]],
      name: Ptr[CChar],
      mode: CInt,
      lconf: Ptr[snd_config_t]
  ): CInt = extern

  /** close timer handle
    *
    * @param timer
    *   timer handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified timer handle and frees all associated resources.
    */
  @name("snd_timer_close")
  def snd_timer_close(handle: Ptr[snd_timer_t]): CInt = extern

  /** Add an async handler for a timer.
    *
    * @param handler
    *   Returned handler handle
    * @param timer
    *   timer handle
    * @param callback
    *   Callback function
    * @param private_data
    *   Callback private data
    * @return
    *   0 otherwise a negative error code on failure
    *
    * The asynchronous callback is called when new timer event occurs.
    */
  @name("snd_async_add_timer_handler")
  def snd_async_add_timer_handler(
      handler: Ptr[Ptr[snd_async_handler_t]],
      timer: Ptr[snd_timer_t],
      callback: snd_async_callback_t,
      private_data: CVoidPtr
  ): CInt = extern

  /** Return timer handle related to an async handler.
    *
    * @param handler
    *   Async handler handle
    * @return
    *   timer handle
    */
  @name("snd_async_handler_get_timer")
  def snd_async_handler_get_timer(
      handler: Ptr[snd_async_handler_t]
  ): Ptr[snd_timer_t] = extern

  /** get count of poll descriptors for timer handle
    *
    * @param timer
    *   timer handle
    * @return
    *   count of poll descriptors
    */
  @name("snd_timer_poll_descriptors_count")
  def snd_timer_poll_descriptors_count(handle: Ptr[snd_timer_t]): CInt = extern

  /** get poll descriptors
    *
    * @param timer
    *   timer handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    */
  @name("snd_timer_poll_descriptors")
  def snd_timer_poll_descriptors(
      handle: Ptr[snd_timer_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param timer
    *   timer handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_timer_poll_descriptors_revents")
  def snd_timer_poll_descriptors_revents(
      timer: Ptr[snd_timer_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** get information about timer handle
    *
    * @param timer
    *   timer handle
    * @param info
    *   pointer to a snd_timer_info_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_info")
  def snd_timer_info(
      handle: Ptr[snd_timer_t],
      timer: Ptr[snd_timer_info_t]
  ): CInt = extern

  /** set parameters for timer handle
    *
    * @param timer
    *   timer handle
    * @param params
    *   pointer to a snd_timer_params_t structure
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_params")
  def snd_timer_params(
      handle: Ptr[snd_timer_t],
      params: Ptr[snd_timer_params_t]
  ): CInt = extern

  /** get status from timer handle
    *
    * @param timer
    *   timer handle
    * @param status
    *   pointer to a snd_timer_status_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_status")
  def snd_timer_status(
      handle: Ptr[snd_timer_t],
      status: Ptr[snd_timer_status_t]
  ): CInt = extern

  /** start the timer
    *
    * @param timer
    *   timer handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_start")
  def snd_timer_start(handle: Ptr[snd_timer_t]): CInt = extern

  /** stop the timer
    *
    * @param timer
    *   timer handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_stop")
  def snd_timer_stop(handle: Ptr[snd_timer_t]): CInt = extern

  /** continue the timer
    *
    * @param timer
    *   timer handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_timer_continue")
  def snd_timer_continue(handle: Ptr[snd_timer_t]): CInt = extern

  /** read bytes using timer handle
    *
    * @param timer
    *   timer handle
    * @param buffer
    *   buffer to store the input bytes
    * @param size
    *   input buffer size in bytes
    */
  @name("snd_timer_read")
  def snd_timer_read(
      handle: Ptr[snd_timer_t],
      buffer: CVoidPtr,
      size: CSize
  ): CSSize = extern

  /** get size of the snd_timer_id_t structure in bytes
    *
    * @return
    *   size of the snd_timer_id_t structure in bytes
    */
  @name("snd_timer_id_sizeof")
  def snd_timer_id_sizeof(): CSize = extern

  /** allocate a new snd_timer_id_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_timer_id_t structure using the standard malloc C
    * library function.
    */
  @name("snd_timer_id_malloc")
  def snd_timer_id_malloc(ptr: Ptr[Ptr[snd_timer_id_t]]): CInt = extern

  /** frees the snd_timer_id_t structure
    *
    * @param info
    *   pointer to the snd_timer_id_t structure to free
    *
    * Frees the given snd_timer_id_t structure using the standard free C library
    * function.
    */
  @name("snd_timer_id_free")
  def snd_timer_id_free(obj: Ptr[snd_timer_id_t]): Unit = extern

  /** copy one snd_timer_id_t structure to another
    *
    * @param dst
    *   destination snd_timer_id_t structure
    * @param src
    *   source snd_timer_id_t structure
    */
  @name("snd_timer_id_copy")
  def snd_timer_id_copy(
      dst: Ptr[snd_timer_id_t],
      src: Ptr[snd_timer_id_t]
  ): Unit = extern

  /** set timer class
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @param dev_class
    *   class of timer device
    */
  @name("snd_timer_id_set_class")
  def snd_timer_id_set_class(id: Ptr[snd_timer_id_t], dev_class: CInt): Unit =
    extern

  /** get timer class
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @return
    *   timer class
    */
  @name("snd_timer_id_get_class")
  def snd_timer_id_get_class(id: Ptr[snd_timer_id_t]): CInt = extern

  /** set timer sub-class
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @param dev_sclass
    *   sub-class of timer device
    */
  @name("snd_timer_id_set_sclass")
  def snd_timer_id_set_sclass(id: Ptr[snd_timer_id_t], dev_sclass: CInt): Unit =
    extern

  /** get timer sub-class
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @return
    *   timer sub-class
    */
  @name("snd_timer_id_get_sclass")
  def snd_timer_id_get_sclass(id: Ptr[snd_timer_id_t]): CInt = extern

  /** set timer card
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @param card
    *   card number
    */
  @name("snd_timer_id_set_card")
  def snd_timer_id_set_card(id: Ptr[snd_timer_id_t], card: CInt): Unit = extern

  /** get timer card
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @return
    *   timer card number
    */
  @name("snd_timer_id_get_card")
  def snd_timer_id_get_card(id: Ptr[snd_timer_id_t]): CInt = extern

  /** set timer device
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @param device
    *   device number
    */
  @name("snd_timer_id_set_device")
  def snd_timer_id_set_device(id: Ptr[snd_timer_id_t], device: CInt): Unit =
    extern

  /** get timer device
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @return
    *   timer device number
    */
  @name("snd_timer_id_get_device")
  def snd_timer_id_get_device(id: Ptr[snd_timer_id_t]): CInt = extern

  /** set timer subdevice
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @param subdevice
    *   subdevice number
    */
  @name("snd_timer_id_set_subdevice")
  def snd_timer_id_set_subdevice(
      id: Ptr[snd_timer_id_t],
      subdevice: CInt
  ): Unit = extern

  /** get timer subdevice
    *
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @return
    *   timer subdevice number
    */
  @name("snd_timer_id_get_subdevice")
  def snd_timer_id_get_subdevice(id: Ptr[snd_timer_id_t]): CInt = extern

  /** get size of the snd_timer_ginfo_t structure in bytes
    *
    * @return
    *   size of the snd_timer_ginfo_t structure in bytes
    */
  @name("snd_timer_ginfo_sizeof")
  def snd_timer_ginfo_sizeof(): CSize = extern

  /** allocate a new snd_timer_ginfo_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_timer_info_t structure using the standard malloc C
    * library function.
    */
  @name("snd_timer_ginfo_malloc")
  def snd_timer_ginfo_malloc(ptr: Ptr[Ptr[snd_timer_ginfo_t]]): CInt = extern

  /** frees the snd_timer_ginfo_t structure
    *
    * @param info
    *   pointer to the snd_timer_ginfo_t structure to free
    *
    * Frees the given snd_timer_info_t structure using the standard free C
    * library function.
    */
  @name("snd_timer_ginfo_free")
  def snd_timer_ginfo_free(obj: Ptr[snd_timer_ginfo_t]): Unit = extern

  /** copy one snd_timer_info_t structure to another
    *
    * @param dst
    *   destination snd_timer_info_t structure
    * @param src
    *   source snd_timer_info_t structure
    */
  @name("snd_timer_ginfo_copy")
  def snd_timer_ginfo_copy(
      dst: Ptr[snd_timer_ginfo_t],
      src: Ptr[snd_timer_ginfo_t]
  ): Unit = extern

  /** set timer identification
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @param tid
    *   pointer to snd_timer_id_t structure
    * @return
    *   zero on success otherwise a negative error number
    */
  @name("snd_timer_ginfo_set_tid")
  def snd_timer_ginfo_set_tid(
      obj: Ptr[snd_timer_ginfo_t],
      tid: Ptr[snd_timer_id_t]
  ): CInt = extern

  /** get timer identification
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   pointer to snd_timer_id_t
    */
  @name("snd_timer_ginfo_get_tid")
  def snd_timer_ginfo_get_tid(
      obj: Ptr[snd_timer_ginfo_t]
  ): Ptr[snd_timer_id_t] = extern

  /** get timer flags
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   timer flags
    */
  @name("snd_timer_ginfo_get_flags")
  def snd_timer_ginfo_get_flags(obj: Ptr[snd_timer_ginfo_t]): CUnsignedInt =
    extern

  /** get associated card with timer
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   associated card
    */
  @name("snd_timer_ginfo_get_card")
  def snd_timer_ginfo_get_card(obj: Ptr[snd_timer_ginfo_t]): CInt = extern

  /** get timer identification
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   timer identification
    */
  @name("snd_timer_ginfo_get_id")
  def snd_timer_ginfo_get_id(obj: Ptr[snd_timer_ginfo_t]): Ptr[CChar] = extern

  /** get timer name
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   timer name
    */
  @name("snd_timer_ginfo_get_name")
  def snd_timer_ginfo_get_name(obj: Ptr[snd_timer_ginfo_t]): Ptr[CChar] = extern

  /** get timer resolution in ns
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   timer resolution in ns
    */
  @name("snd_timer_ginfo_get_resolution")
  def snd_timer_ginfo_get_resolution(
      obj: Ptr[snd_timer_ginfo_t]
  ): CUnsignedLong = extern

  /** get timer minimal resolution in ns
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   timer minimal resolution in ns
    */
  @name("snd_timer_ginfo_get_resolution_min")
  def snd_timer_ginfo_get_resolution_min(
      obj: Ptr[snd_timer_ginfo_t]
  ): CUnsignedLong = extern

  /** get timer maximal resolution in ns
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   timer maximal resolution in ns
    */
  @name("snd_timer_ginfo_get_resolution_max")
  def snd_timer_ginfo_get_resolution_max(
      obj: Ptr[snd_timer_ginfo_t]
  ): CUnsignedLong = extern

  /** get current timer clients
    *
    * @param obj
    *   pointer to snd_timer_ginfo_t structure
    * @return
    *   current timer clients
    */
  @name("snd_timer_ginfo_get_clients")
  def snd_timer_ginfo_get_clients(obj: Ptr[snd_timer_ginfo_t]): CUnsignedInt =
    extern

  /** get size of the snd_timer_info_t structure in bytes
    *
    * @return
    *   size of the snd_timer_info_t structure in bytes
    */
  @name("snd_timer_info_sizeof")
  def snd_timer_info_sizeof(): CSize = extern

  /** allocate a new snd_timer_info_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_timer_info_t structure using the standard malloc C
    * library function.
    */
  @name("snd_timer_info_malloc")
  def snd_timer_info_malloc(ptr: Ptr[Ptr[snd_timer_info_t]]): CInt = extern

  /** frees the snd_timer_info_t structure
    *
    * @param info
    *   pointer to the snd_timer_info_t structure to free
    *
    * Frees the given snd_timer_info_t structure using the standard free C
    * library function.
    */
  @name("snd_timer_info_free")
  def snd_timer_info_free(obj: Ptr[snd_timer_info_t]): Unit = extern

  /** copy one snd_timer_info_t structure to another
    *
    * @param dst
    *   destination snd_timer_info_t structure
    * @param src
    *   source snd_timer_info_t structure
    */
  @name("snd_timer_info_copy")
  def snd_timer_info_copy(
      dst: Ptr[snd_timer_info_t],
      src: Ptr[snd_timer_info_t]
  ): Unit = extern

  /** determine, if timer is slave
    *
    * @param info
    *   pointer to snd_timer_info_t structure
    * @return
    *   nonzero if timer is slave
    */
  @name("snd_timer_info_is_slave")
  def snd_timer_info_is_slave(info: Ptr[snd_timer_info_t]): CInt = extern

  /** get timer card
    *
    * @param info
    *   pointer to snd_timer_info_t structure
    * @return
    *   timer card number
    */
  @name("snd_timer_info_get_card")
  def snd_timer_info_get_card(info: Ptr[snd_timer_info_t]): CInt = extern

  /** get timer id
    *
    * @param info
    *   pointer to snd_timer_info_t structure
    * @return
    *   timer id
    */
  @name("snd_timer_info_get_id")
  def snd_timer_info_get_id(info: Ptr[snd_timer_info_t]): Ptr[CChar] = extern

  /** get timer name
    *
    * @param info
    *   pointer to snd_timer_info_t structure
    * @return
    *   timer name
    */
  @name("snd_timer_info_get_name")
  def snd_timer_info_get_name(info: Ptr[snd_timer_info_t]): Ptr[CChar] = extern

  /** get timer resolution in us
    *
    * @param info
    *   pointer to snd_timer_info_t structure
    * @return
    *   timer resolution
    */
  @name("snd_timer_info_get_resolution")
  def snd_timer_info_get_resolution(info: Ptr[snd_timer_info_t]): CLong = extern

  /** get size of the snd_timer_params_t structure in bytes
    *
    * @return
    *   size of the snd_timer_params_t structure in bytes
    */
  @name("snd_timer_params_sizeof")
  def snd_timer_params_sizeof(): CSize = extern

  /** allocate a new snd_timer_params_t structure
    *
    * @param params
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_timer_params_t structure using the standard malloc C
    * library function.
    */
  @name("snd_timer_params_malloc")
  def snd_timer_params_malloc(ptr: Ptr[Ptr[snd_timer_params_t]]): CInt = extern

  /** frees the snd_timer_params_t structure
    *
    * @param params
    *   pointer to the snd_timer_params_t structure to free
    *
    * Frees the given snd_timer_params_t structure using the standard free C
    * library function.
    */
  @name("snd_timer_params_free")
  def snd_timer_params_free(obj: Ptr[snd_timer_params_t]): Unit = extern

  /** copy one snd_timer_params_t structure to another
    *
    * @param dst
    *   destination snd_timer_params_t structure
    * @param src
    *   source snd_timer_params_t structure
    */
  @name("snd_timer_params_copy")
  def snd_timer_params_copy(
      dst: Ptr[snd_timer_params_t],
      src: Ptr[snd_timer_params_t]
  ): Unit = extern

  /** set timer auto start
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @param auto_start
    *   The boolean value to set
    */
  @name("snd_timer_params_set_auto_start")
  def snd_timer_params_set_auto_start(
      params: Ptr[snd_timer_params_t],
      auto_start: CInt
  ): CInt = extern

  /** determine if timer has auto start flag
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @return
    *   nonzero if timer has auto start flag
    */
  @name("snd_timer_params_get_auto_start")
  def snd_timer_params_get_auto_start(params: Ptr[snd_timer_params_t]): CInt =
    extern

  /** set timer exclusive use
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @param exclusive
    *   The boolean value to set
    */
  @name("snd_timer_params_set_exclusive")
  def snd_timer_params_set_exclusive(
      params: Ptr[snd_timer_params_t],
      exclusive: CInt
  ): CInt = extern

  /** determine if timer has exclusive flag
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @return
    *   nonzero if timer has exclusive flag
    */
  @name("snd_timer_params_get_exclusive")
  def snd_timer_params_get_exclusive(params: Ptr[snd_timer_params_t]): CInt =
    extern

  /** set timer early event
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @param early_event
    *   The boolean value to set
    */
  @name("snd_timer_params_set_early_event")
  def snd_timer_params_set_early_event(
      params: Ptr[snd_timer_params_t],
      early_event: CInt
  ): CInt = extern

  /** determine if timer has early event flag
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @return
    *   nonzero if timer has early event flag set
    */
  @name("snd_timer_params_get_early_event")
  def snd_timer_params_get_early_event(params: Ptr[snd_timer_params_t]): CInt =
    extern

  /** set timer ticks
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @param ticks
    *   Ticks to set
    */
  @name("snd_timer_params_set_ticks")
  def snd_timer_params_set_ticks(
      params: Ptr[snd_timer_params_t],
      ticks: CLong
  ): Unit = extern

  /** get timer ticks
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @return
    *   timer ticks
    */
  @name("snd_timer_params_get_ticks")
  def snd_timer_params_get_ticks(params: Ptr[snd_timer_params_t]): CLong =
    extern

  /** set timer queue size (32-1024)
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @param queue_size
    *   The queue size to set
    */
  @name("snd_timer_params_set_queue_size")
  def snd_timer_params_set_queue_size(
      params: Ptr[snd_timer_params_t],
      queue_size: CLong
  ): Unit = extern

  /** get queue size
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @return
    *   queue size
    */
  @name("snd_timer_params_get_queue_size")
  def snd_timer_params_get_queue_size(params: Ptr[snd_timer_params_t]): CLong =
    extern

  /** set timer event filter
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @param filter
    *   The event filter bits to set
    */
  @name("snd_timer_params_set_filter")
  def snd_timer_params_set_filter(
      params: Ptr[snd_timer_params_t],
      filter: CUnsignedInt
  ): Unit = extern

  /** get timer event filter
    *
    * @param params
    *   pointer to snd_timer_params_t structure
    * @return
    *   timer event filter
    */
  @name("snd_timer_params_get_filter")
  def snd_timer_params_get_filter(
      params: Ptr[snd_timer_params_t]
  ): CUnsignedInt = extern

  /** get size of the snd_timer_status_t structure in bytes
    *
    * @return
    *   size of the snd_timer_status_t structure in bytes
    */
  @name("snd_timer_status_sizeof")
  def snd_timer_status_sizeof(): CSize = extern

  /** allocate a new snd_timer_status_t structure
    *
    * @param status
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_timer_status_t structure using the standard malloc C
    * library function.
    */
  @name("snd_timer_status_malloc")
  def snd_timer_status_malloc(ptr: Ptr[Ptr[snd_timer_status_t]]): CInt = extern

  /** frees the snd_timer_status_t structure
    *
    * @param status
    *   pointer to the snd_timer_status_t structure to free
    *
    * Frees the given snd_timer_status_t structure using the standard free C
    * library function.
    */
  @name("snd_timer_status_free")
  def snd_timer_status_free(obj: Ptr[snd_timer_status_t]): Unit = extern

  /** copy one snd_timer_status_t structure to another
    *
    * @param dst
    *   destination snd_timer_status_t structure
    * @param src
    *   source snd_timer_status_t structure
    */
  @name("snd_timer_status_copy")
  def snd_timer_status_copy(
      dst: Ptr[snd_timer_status_t],
      src: Ptr[snd_timer_status_t]
  ): Unit = extern

  /** get timestamp
    *
    * @param status
    *   pointer to snd_timer_status_t structure
    * @return
    *   timestamp
    */
  @name("snd_timer_status_get_timestamp")
  def snd_timer_status_get_timestamp(
      status: Ptr[snd_timer_status_t]
  ): snd_htimestamp_t = extern

  /** get resolution in us
    *
    * @param status
    *   pointer to snd_timer_status_t structure
    * @return
    *   resolution
    */
  @name("snd_timer_status_get_resolution")
  def snd_timer_status_get_resolution(status: Ptr[snd_timer_status_t]): CLong =
    extern

  /** get master tick lost count
    *
    * @param status
    *   pointer to snd_timer_status_t structure
    * @return
    *   master tick lost count
    */
  @name("snd_timer_status_get_lost")
  def snd_timer_status_get_lost(status: Ptr[snd_timer_status_t]): CLong = extern

  /** get overrun count
    *
    * @param status
    *   pointer to snd_timer_status_t structure
    * @return
    *   overrun count
    */
  @name("snd_timer_status_get_overrun")
  def snd_timer_status_get_overrun(status: Ptr[snd_timer_status_t]): CLong =
    extern

  /** get count of used queue elements
    *
    * @param status
    *   pointer to snd_timer_status_t structure
    * @return
    *   count of used queue elements
    */
  @name("snd_timer_status_get_queue")
  def snd_timer_status_get_queue(status: Ptr[snd_timer_status_t]): CLong =
    extern

  /** (DEPRECATED) get maximum timer ticks
    *
    * @param info
    *   pointer to snd_timer_info_t structure
    * @return
    *   maximum timer ticks
    */
  @name("snd_timer_info_get_ticks")
  def snd_timer_info_get_ticks(info: Ptr[snd_timer_info_t]): CLong = extern

}
