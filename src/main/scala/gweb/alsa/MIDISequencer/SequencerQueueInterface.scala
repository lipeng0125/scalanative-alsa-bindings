package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import gweb.alsa.MIDISequencer.MIDISequencer.snd_seq_t
import gweb.alsa.TimerInterface.snd_timer_id_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_tick_time_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_real_time_t
import gweb.alsa.Iota

/** Sequencer Queue Interface
  */
@link("asound")
@extern
object SequencerQueueInterface {

  /** special queue ids direct dispatch
    */
  inline val SND_SEQ_QUEUE_DIRECT = 253

  /** queue information container
    */
  type snd_seq_queue_info_t = CStruct0

  /** queue status container
    */
  type snd_seq_queue_status_t = CStruct0

  /** queue tempo container
    */
  type snd_seq_queue_tempo_t = CStruct0

  /** queue timer information container
    */
  type snd_seq_queue_timer_t = CStruct0

  /** sequencer timer sources
    */
  class snd_seq_queue_timer_type_t private (val value: CInt) extends AnyVal

  object snd_seq_queue_timer_type_t extends Iota {

    /** */
    val SND_SEQ_TIMER_ALSA = iota(0)

    /** */
    val SND_SEQ_TIMER_MIDI_CLOCK = iota(1)

    /** */
    val SND_SEQ_TIMER_MIDI_TICK = iota(2)

  }

  /** get size of snd_seq_queue_info_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_queue_info_sizeof")
  def snd_seq_queue_info_sizeof(): CSize = extern

  /** allocate an empty snd_seq_queue_info_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_queue_info_malloc")
  def snd_seq_queue_info_malloc(ptr: Ptr[Ptr[snd_seq_queue_info_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_queue_info_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_queue_info_free")
  def snd_seq_queue_info_free(ptr: Ptr[snd_seq_queue_info_t]): Unit = extern

  /** copy one snd_seq_queue_info_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_queue_info_copy")
  def snd_seq_queue_info_copy(
      dst: Ptr[snd_seq_queue_info_t],
      src: Ptr[snd_seq_queue_info_t]
  ): Unit = extern

  /** Get the queue id of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @return
    *   queue id
    */
  @name("snd_seq_queue_info_get_queue")
  def snd_seq_queue_info_get_queue(info: Ptr[snd_seq_queue_info_t]): CInt =
    extern

  /** Get the name of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @return
    *   name string
    */
  @name("snd_seq_queue_info_get_name")
  def snd_seq_queue_info_get_name(info: Ptr[snd_seq_queue_info_t]): Ptr[CChar] =
    extern

  /** Get the owner client id of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @return
    *   owner client id
    */
  @name("snd_seq_queue_info_get_owner")
  def snd_seq_queue_info_get_owner(info: Ptr[snd_seq_queue_info_t]): CInt =
    extern

  /** Get the lock status of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @return
    *   lock status — non-zero = locked
    */
  @name("snd_seq_queue_info_get_locked")
  def snd_seq_queue_info_get_locked(info: Ptr[snd_seq_queue_info_t]): CInt =
    extern

  /** Get the conditional bit flags of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @return
    *   conditional bit flags
    */
  @name("snd_seq_queue_info_get_flags")
  def snd_seq_queue_info_get_flags(
      info: Ptr[snd_seq_queue_info_t]
  ): CUnsignedInt = extern

  /** Set the name of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @param name
    *   name string
    */
  @name("snd_seq_queue_info_set_name")
  def snd_seq_queue_info_set_name(
      info: Ptr[snd_seq_queue_info_t],
      name: Ptr[CChar]
  ): Unit = extern

  /** Set the owner client id of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @param owner
    *   client id
    */
  @name("snd_seq_queue_info_set_owner")
  def snd_seq_queue_info_set_owner(
      info: Ptr[snd_seq_queue_info_t],
      owner: CInt
  ): Unit = extern

  /** Set the lock status of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @param locked
    *   lock status
    */
  @name("snd_seq_queue_info_set_locked")
  def snd_seq_queue_info_set_locked(
      info: Ptr[snd_seq_queue_info_t],
      locked: CInt
  ): Unit = extern

  /** Set the conditional bit flags of a queue_info container.
    *
    * @param info
    *   queue_info container
    * @param flags
    *   conditional bit flags
    */
  @name("snd_seq_queue_info_set_flags")
  def snd_seq_queue_info_set_flags(
      info: Ptr[snd_seq_queue_info_t],
      flags: CUnsignedInt
  ): Unit = extern

  /** create a queue
    *
    * @param seq
    *   sequencer handle
    * @param info
    *   queue information to initialize
    * @return
    *   the queue id (zero or positive) on success otherwise a negative error
    *   code
    */
  @name("snd_seq_create_queue")
  def snd_seq_create_queue(
      seq: Ptr[snd_seq_t],
      info: Ptr[snd_seq_queue_info_t]
  ): CInt = extern

  /** allocate a queue with the specified name
    *
    * @param seq
    *   sequencer handle
    * @param name
    *   the name of the new queue
    * @return
    *   the queue id (zero or positive) on success otherwise a negative error
    *   code
    */
  @name("snd_seq_alloc_named_queue")
  def snd_seq_alloc_named_queue(seq: Ptr[snd_seq_t], name: Ptr[CChar]): CInt =
    extern

  /** allocate a queue
    *
    * @param seq
    *   sequencer handle
    * @return
    *   the queue id (zero or positive) on success otherwise a negative error
    *   code
    */
  @name("snd_seq_alloc_queue")
  def snd_seq_alloc_queue(handle: Ptr[snd_seq_t]): CInt = extern

  /** delete the specified queue
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to delete
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_free_queue")
  def snd_seq_free_queue(handle: Ptr[snd_seq_t], q: CInt): CInt = extern

  /** obtain queue attributes
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to query
    * @param info
    *   information returned
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_queue_info")
  def snd_seq_get_queue_info(
      seq: Ptr[snd_seq_t],
      q: CInt,
      info: Ptr[snd_seq_queue_info_t]
  ): CInt = extern

  /** change the queue attributes
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to change
    * @param info
    *   information changed
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_queue_info")
  def snd_seq_set_queue_info(
      seq: Ptr[snd_seq_t],
      q: CInt,
      info: Ptr[snd_seq_queue_info_t]
  ): CInt = extern

  /** query the matching queue with the specified name
    *
    * @param seq
    *   sequencer handle
    * @param name
    *   the name string to query
    * @return
    *   the queue id if found or negative error code
    *
    * Searches the matching queue with the specified name string.
    */
  @name("snd_seq_query_named_queue")
  def snd_seq_query_named_queue(seq: Ptr[snd_seq_t], name: Ptr[CChar]): CInt =
    extern

  /** Get the queue usage flag to the client.
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id
    * @return
    *   1 = client is allowed to access the queue, 0 = not allowed, otherwise a
    *   negative error code
    */
  @name("snd_seq_get_queue_usage")
  def snd_seq_get_queue_usage(handle: Ptr[snd_seq_t], q: CInt): CInt = extern

  /** Set the queue usage flag to the client.
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id
    * @param used
    *   non-zero if the client is allowed
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_queue_usage")
  def snd_seq_set_queue_usage(
      handle: Ptr[snd_seq_t],
      q: CInt,
      used: CInt
  ): CInt = extern

  /** get size of snd_seq_queue_status_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_queue_status_sizeof")
  def snd_seq_queue_status_sizeof(): CSize = extern

  /** allocate an empty snd_seq_queue_status_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_queue_status_malloc")
  def snd_seq_queue_status_malloc(ptr: Ptr[Ptr[snd_seq_queue_status_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_queue_status_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_queue_status_free")
  def snd_seq_queue_status_free(ptr: Ptr[snd_seq_queue_status_t]): Unit = extern

  /** copy one snd_seq_queue_status_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_queue_status_copy")
  def snd_seq_queue_status_copy(
      dst: Ptr[snd_seq_queue_status_t],
      src: Ptr[snd_seq_queue_status_t]
  ): Unit = extern

  /** Get the queue id of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   queue id
    */
  @name("snd_seq_queue_status_get_queue")
  def snd_seq_queue_status_get_queue(info: Ptr[snd_seq_queue_status_t]): CInt =
    extern

  /** Get the number of events of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   number of events
    */
  @name("snd_seq_queue_status_get_events")
  def snd_seq_queue_status_get_events(info: Ptr[snd_seq_queue_status_t]): CInt =
    extern

  /** Get the tick time of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   tick time
    */
  @name("snd_seq_queue_status_get_tick_time")
  def snd_seq_queue_status_get_tick_time(
      info: Ptr[snd_seq_queue_status_t]
  ): snd_seq_tick_time_t = extern

  /** Get the real time of a queue_status container.
    *
    * @param info
    *   queue_status container
    */
  @name("snd_seq_queue_status_get_real_time")
  def snd_seq_queue_status_get_real_time(
      info: Ptr[snd_seq_queue_status_t]
  ): Ptr[snd_seq_real_time_t] = extern

  /** Get the running status bits of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   running status bits
    */
  @name("snd_seq_queue_status_get_status")
  def snd_seq_queue_status_get_status(
      info: Ptr[snd_seq_queue_status_t]
  ): CUnsignedInt = extern

  /** obtain the running state of the queue
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to query
    * @param status
    *   pointer to store the current status
    * @return
    *   0 on success otherwise a negative error code
    *
    * Obtains the running state of the specified queue q.
    */
  @name("snd_seq_get_queue_status")
  def snd_seq_get_queue_status(
      handle: Ptr[snd_seq_t],
      q: CInt,
      status: Ptr[snd_seq_queue_status_t]
  ): CInt = extern

  /** get size of snd_seq_queue_tempo_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_queue_tempo_sizeof")
  def snd_seq_queue_tempo_sizeof(): CSize = extern

  /** allocate an empty snd_seq_queue_tempo_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_queue_tempo_malloc")
  def snd_seq_queue_tempo_malloc(ptr: Ptr[Ptr[snd_seq_queue_tempo_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_queue_tempo_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_queue_tempo_free")
  def snd_seq_queue_tempo_free(ptr: Ptr[snd_seq_queue_tempo_t]): Unit = extern

  /** copy one snd_seq_queue_tempo_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_queue_tempo_copy")
  def snd_seq_queue_tempo_copy(
      dst: Ptr[snd_seq_queue_tempo_t],
      src: Ptr[snd_seq_queue_tempo_t]
  ): Unit = extern

  /** Get the queue id of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   queue id
    */
  @name("snd_seq_queue_tempo_get_queue")
  def snd_seq_queue_tempo_get_queue(info: Ptr[snd_seq_queue_tempo_t]): CInt =
    extern

  /** Get the tempo of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   tempo value
    */
  @name("snd_seq_queue_tempo_get_tempo")
  def snd_seq_queue_tempo_get_tempo(
      info: Ptr[snd_seq_queue_tempo_t]
  ): CUnsignedInt = extern

  /** Get the ppq of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   ppq value
    */
  @name("snd_seq_queue_tempo_get_ppq")
  def snd_seq_queue_tempo_get_ppq(info: Ptr[snd_seq_queue_tempo_t]): CInt =
    extern

  /** Get the timer skew value of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   timer skew value
    */
  @name("snd_seq_queue_tempo_get_skew")
  def snd_seq_queue_tempo_get_skew(
      info: Ptr[snd_seq_queue_tempo_t]
  ): CUnsignedInt = extern

  /** Get the timer skew base value of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @return
    *   timer skew base value
    */
  @name("snd_seq_queue_tempo_get_skew_base")
  def snd_seq_queue_tempo_get_skew_base(
      info: Ptr[snd_seq_queue_tempo_t]
  ): CUnsignedInt = extern

  /** Set the tempo of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @param tempo
    *   tempo value
    */
  @name("snd_seq_queue_tempo_set_tempo")
  def snd_seq_queue_tempo_set_tempo(
      info: Ptr[snd_seq_queue_tempo_t],
      tempo: CUnsignedInt
  ): Unit = extern

  /** Set the ppq of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @param ppq
    *   ppq value
    */
  @name("snd_seq_queue_tempo_set_ppq")
  def snd_seq_queue_tempo_set_ppq(
      info: Ptr[snd_seq_queue_tempo_t],
      ppq: CInt
  ): Unit = extern

  /** Set the timer skew value of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @param skew
    *   timer skew value
    *
    * The skew of timer is calculated as skew / base. For example, to play with
    * double speed, pass base * 2 as the skew value.
    */
  @name("snd_seq_queue_tempo_set_skew")
  def snd_seq_queue_tempo_set_skew(
      info: Ptr[snd_seq_queue_tempo_t],
      skew: CUnsignedInt
  ): Unit = extern

  /** Set the timer skew base value of a queue_status container.
    *
    * @param info
    *   queue_status container
    * @param base
    *   timer skew base value
    */
  @name("snd_seq_queue_tempo_set_skew_base")
  def snd_seq_queue_tempo_set_skew_base(
      info: Ptr[snd_seq_queue_tempo_t],
      base: CUnsignedInt
  ): Unit = extern

  /** obtain the current tempo of the queue
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to be queried
    * @param tempo
    *   pointer to store the current tempo
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_queue_tempo")
  def snd_seq_get_queue_tempo(
      handle: Ptr[snd_seq_t],
      q: CInt,
      tempo: Ptr[snd_seq_queue_tempo_t]
  ): CInt = extern

  /** set the tempo of the queue
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to change the tempo
    * @param tempo
    *   tempo information
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_queue_tempo")
  def snd_seq_set_queue_tempo(
      handle: Ptr[snd_seq_t],
      q: CInt,
      tempo: Ptr[snd_seq_queue_tempo_t]
  ): CInt = extern

  /** get size of snd_seq_queue_timer_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_queue_timer_sizeof")
  def snd_seq_queue_timer_sizeof(): CSize = extern

  /** allocate an empty snd_seq_queue_timer_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_queue_timer_malloc")
  def snd_seq_queue_timer_malloc(ptr: Ptr[Ptr[snd_seq_queue_timer_t]]): CInt =
    extern

  /** frees a previously allocated snd_seq_queue_timer_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_queue_timer_free")
  def snd_seq_queue_timer_free(ptr: Ptr[snd_seq_queue_timer_t]): Unit = extern

  /** copy one snd_seq_queue_timer_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_queue_timer_copy")
  def snd_seq_queue_timer_copy(
      dst: Ptr[snd_seq_queue_timer_t],
      src: Ptr[snd_seq_queue_timer_t]
  ): Unit = extern

  /** Get the queue id of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @return
    *   queue id
    */
  @name("snd_seq_queue_timer_get_queue")
  def snd_seq_queue_timer_get_queue(info: Ptr[snd_seq_queue_timer_t]): CInt =
    extern

  /** Get the timer type of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @return
    *   timer type
    */
  @name("snd_seq_queue_timer_get_type")
  def snd_seq_queue_timer_get_type(
      info: Ptr[snd_seq_queue_timer_t]
  ): snd_seq_queue_timer_type_t = extern

  /** Get the timer id of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @return
    *   timer id pointer
    */
  @name("snd_seq_queue_timer_get_id")
  def snd_seq_queue_timer_get_id(
      info: Ptr[snd_seq_queue_timer_t]
  ): Ptr[snd_timer_id_t] = extern

  /** Get the timer resolution of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @return
    *   timer resolution
    */
  @name("snd_seq_queue_timer_get_resolution")
  def snd_seq_queue_timer_get_resolution(
      info: Ptr[snd_seq_queue_timer_t]
  ): CUnsignedInt = extern

  /** Set the timer type of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @param type
    *   timer type
    */
  @name("snd_seq_queue_timer_set_type")
  def snd_seq_queue_timer_set_type(
      info: Ptr[snd_seq_queue_timer_t],
      `type`: snd_seq_queue_timer_type_t
  ): Unit = extern

  /** Set the timer id of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @param id
    *   timer id pointer
    */
  @name("snd_seq_queue_timer_set_id")
  def snd_seq_queue_timer_set_id(
      info: Ptr[snd_seq_queue_timer_t],
      id: Ptr[snd_timer_id_t]
  ): Unit = extern

  /** Set the timer resolution of a queue_timer container.
    *
    * @param info
    *   queue_timer container
    * @param resolution
    *   timer resolution
    */
  @name("snd_seq_queue_timer_set_resolution")
  def snd_seq_queue_timer_set_resolution(
      info: Ptr[snd_seq_queue_timer_t],
      resolution: CUnsignedInt
  ): Unit = extern

  /** obtain the queue timer information
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to query
    * @param timer
    *   pointer to store the timer information
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_queue_timer")
  def snd_seq_get_queue_timer(
      handle: Ptr[snd_seq_t],
      q: CInt,
      timer: Ptr[snd_seq_queue_timer_t]
  ): CInt = extern

  /** set the queue timer information
    *
    * @param seq
    *   sequencer handle
    * @param q
    *   queue id to change the timer
    * @param timer
    *   timer information
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_set_queue_timer")
  def snd_seq_set_queue_timer(
      handle: Ptr[snd_seq_t],
      q: CInt,
      timer: Ptr[snd_seq_queue_timer_t]
  ): CInt = extern

}
