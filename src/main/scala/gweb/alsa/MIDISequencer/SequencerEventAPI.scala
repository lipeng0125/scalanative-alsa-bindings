package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import gweb.alsa.MIDISequencer.MIDISequencer.snd_seq_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_addr_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_event_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_timestamp_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_ump_event_t

/** Sequencer Event API
  */
@link("asound")
@extern
object SequencerEventAPI {

  /** Remove conditional flags Flush input queues
    */
  inline val SND_SEQ_REMOVE_INPUT = (1 << 0)

  /** Flush output queues
    */
  inline val SND_SEQ_REMOVE_OUTPUT = (1 << 1)

  /** Restrict by destination q:client:port
    */
  inline val SND_SEQ_REMOVE_DEST = (1 << 2)

  /** Restrict by channel
    */
  inline val SND_SEQ_REMOVE_DEST_CHANNEL = (1 << 3)

  /** Restrict to before time
    */
  inline val SND_SEQ_REMOVE_TIME_BEFORE = (1 << 4)

  /** Restrict to time or after
    */
  inline val SND_SEQ_REMOVE_TIME_AFTER = (1 << 5)

  /** Time is in ticks
    */
  inline val SND_SEQ_REMOVE_TIME_TICK = (1 << 6)

  /** Restrict to event type
    */
  inline val SND_SEQ_REMOVE_EVENT_TYPE = (1 << 7)

  /** Do not flush off events
    */
  inline val SND_SEQ_REMOVE_IGNORE_OFF = (1 << 8)

  /** Restrict to events with given tag
    */
  inline val SND_SEQ_REMOVE_TAG_MATCH = (1 << 9)

  /** event removal conditionals
    */
  type snd_seq_remove_events_t = CStruct0

  /** In the former version, this function was used to release the event pointer
    * which was allocated by snd_seq_event_input(). In the current version, the
    * event record is not allocated, so you don't have to call this function any
    * more.
    */
  @name("snd_seq_free_event")
  def snd_seq_free_event(ev: Ptr[snd_seq_event_t]): CInt = extern

  /** calculates the (encoded) byte-stream size of the event
    *
    * @param ev
    *   the event
    * @return
    *   the size of decoded bytes
    */
  @name("snd_seq_event_length")
  def snd_seq_event_length(ev: Ptr[snd_seq_event_t]): CSSize = extern

  /** output an event
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   event to be output
    * @return
    *   the number of remaining events or a negative error code
    *
    * An event is once expanded on the output buffer. The output buffer will be
    * drained automatically if it becomes full.
    *
    * If events remain unprocessed on output buffer before drained, the size of
    * total byte data on output buffer is returned. If the output buffer is
    * empty, this returns zero.
    */
  @name("snd_seq_event_output")
  def snd_seq_event_output(
      handle: Ptr[snd_seq_t],
      ev: Ptr[snd_seq_event_t]
  ): CInt = extern

  /** output an event onto the lib buffer without draining buffer
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   event to be output
    * @return
    *   the byte size of remaining events. -EAGAIN if the buffer becomes full.
    *
    * This function doesn't drain buffer unlike snd_seq_event_output().
    */
  @name("snd_seq_event_output_buffer")
  def snd_seq_event_output_buffer(
      handle: Ptr[snd_seq_t],
      ev: Ptr[snd_seq_event_t]
  ): CInt = extern

  /** output an event directly to the sequencer NOT through output buffer
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   event to be output
    * @return
    *   the byte size sent to sequencer or a negative error code
    *
    * This function sends an event to the sequencer directly not through the
    * output buffer. When the event is a variable length event, a temporary
    * buffer is allocated inside alsa-lib and the data is copied there before
    * actually sent.
    */
  @name("snd_seq_event_output_direct")
  def snd_seq_event_output_direct(
      handle: Ptr[snd_seq_t],
      ev: Ptr[snd_seq_event_t]
  ): CInt = extern

  /** retrieve an event from sequencer
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   event pointer to be stored
    *
    * Obtains an input event from sequencer. The event is created via
    * snd_seq_create_event(), and its pointer is stored on ev argument.
    *
    * This function firstly receives the event byte-stream data from sequencer
    * as much as possible at once. Then it retrieves the first event record and
    * store the pointer on ev. By calling this function sequentially, events are
    * extracted from the input buffer.
    *
    * If there is no input from sequencer, function falls into sleep in blocking
    * mode until an event is received, or returns -EAGAIN error in non-blocking
    * mode. Occasionally, this function may return -ENOSPC error. This means
    * that the input FIFO of sequencer overran, and some events are lost. Once
    * this error is returned, the input FIFO is cleared automatically.
    *
    * Function returns the byte size of remaining events on the input buffer if
    * an event is successfully received. Application can determine from the
    * returned value whether to call input once more or not.
    */
  @name("snd_seq_event_input")
  def snd_seq_event_input(
      handle: Ptr[snd_seq_t],
      ev: Ptr[Ptr[snd_seq_event_t]]
  ): CInt = extern

  /** check events in input buffer
    *
    * @return
    *   the byte size of remaining input events on input buffer.
    *
    * If events remain on the input buffer of user-space, function returns the
    * total byte size of events on it. If fetch_sequencer argument is non-zero,
    * this function checks the presence of events on sequencer FIFO When events
    * exist, they are transferred to the input buffer, and the number of
    * received events are returned. If fetch_sequencer argument is zero and no
    * events remain on the input buffer, function simply returns zero.
    */
  @name("snd_seq_event_input_pending")
  def snd_seq_event_input_pending(
      seq: Ptr[snd_seq_t],
      fetch_sequencer: CInt
  ): CInt = extern

  /** drain output buffer to sequencer
    *
    * @param seq
    *   sequencer handle
    * @return
    *   0 when all events are drained and sent to sequencer. When events still
    *   remain on the buffer, the byte size of remaining events are returned. On
    *   error a negative error code is returned.
    *
    * This function drains all pending events on the output buffer. The function
    * returns immediately after the events are sent to the queues regardless
    * whether the events are processed or not. To get synchronization with the
    * all event processes, use snd_seq_sync_output_queue() after calling this
    * function.
    */
  @name("snd_seq_drain_output")
  def snd_seq_drain_output(handle: Ptr[snd_seq_t]): CInt = extern

  /** return the size of pending events on output buffer
    *
    * @param seq
    *   sequencer handle
    * @return
    *   the byte size of total of pending events
    */
  @name("snd_seq_event_output_pending")
  def snd_seq_event_output_pending(seq: Ptr[snd_seq_t]): CInt = extern

  /** extract the first event in output buffer
    *
    * @param seq
    *   sequencer handle
    * @param ev_res
    *   event pointer to be extracted
    * @return
    *   0 on success otherwise a negative error code
    *
    * Extracts the first event in output buffer. If ev_res is NULL, just remove
    * the event.
    */
  @name("snd_seq_extract_output")
  def snd_seq_extract_output(
      handle: Ptr[snd_seq_t],
      ev: Ptr[Ptr[snd_seq_event_t]]
  ): CInt = extern

  /** remove all events on output buffer
    *
    * @param seq
    *   sequencer handle
    *
    * Removes all events on both user-space output buffer and output memory pool
    * on kernel.
    */
  @name("snd_seq_drop_output")
  def snd_seq_drop_output(handle: Ptr[snd_seq_t]): CInt = extern

  /** remove all events on user-space output buffer
    *
    * @param seq
    *   sequencer handle
    *
    * Removes all events on user-space output buffer. Unlike
    * snd_seq_drain_output(), this function doesn't remove events on output
    * memory pool of sequencer.
    */
  @name("snd_seq_drop_output_buffer")
  def snd_seq_drop_output_buffer(handle: Ptr[snd_seq_t]): CInt = extern

  /** clear input buffer and and remove events in sequencer queue
    *
    * @param seq
    *   sequencer handle
    */
  @name("snd_seq_drop_input")
  def snd_seq_drop_input(handle: Ptr[snd_seq_t]): CInt = extern

  /** remove all events on user-space input FIFO
    *
    * @param seq
    *   sequencer handle
    */
  @name("snd_seq_drop_input_buffer")
  def snd_seq_drop_input_buffer(handle: Ptr[snd_seq_t]): CInt = extern

  /** get size of snd_seq_remove_events_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_remove_events_sizeof")
  def snd_seq_remove_events_sizeof(): CSize = extern

  /** allocate an empty snd_seq_remove_events_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_remove_events_malloc")
  def snd_seq_remove_events_malloc(
      ptr: Ptr[Ptr[snd_seq_remove_events_t]]
  ): CInt = extern

  /** frees a previously allocated snd_seq_remove_events_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_remove_events_free")
  def snd_seq_remove_events_free(ptr: Ptr[snd_seq_remove_events_t]): Unit =
    extern

  /** copy one snd_seq_remove_events_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_remove_events_copy")
  def snd_seq_remove_events_copy(
      dst: Ptr[snd_seq_remove_events_t],
      src: Ptr[snd_seq_remove_events_t]
  ): Unit = extern

  /** Get the removal condition bits.
    *
    * @param info
    *   remove_events container
    * @return
    *   removal condition bits
    */
  @name("snd_seq_remove_events_get_condition")
  def snd_seq_remove_events_get_condition(
      info: Ptr[snd_seq_remove_events_t]
  ): CUnsignedInt = extern

  /** Get the queue as removal condition.
    *
    * @param info
    *   remove_events container
    * @return
    *   queue id
    */
  @name("snd_seq_remove_events_get_queue")
  def snd_seq_remove_events_get_queue(
      info: Ptr[snd_seq_remove_events_t]
  ): CInt = extern

  /** Get the event timestamp as removal condition.
    *
    * @param info
    *   remove_events container
    * @return
    *   time stamp
    */
  @name("snd_seq_remove_events_get_time")
  def snd_seq_remove_events_get_time(
      info: Ptr[snd_seq_remove_events_t]
  ): Ptr[snd_seq_timestamp_t] = extern

  /** Get the event destination address as removal condition.
    *
    * @param info
    *   remove_events container
    * @return
    *   destination address
    */
  @name("snd_seq_remove_events_get_dest")
  def snd_seq_remove_events_get_dest(
      info: Ptr[snd_seq_remove_events_t]
  ): Ptr[snd_seq_addr_t] = extern

  /** Get the event channel as removal condition.
    *
    * @param info
    *   remove_events container
    * @return
    *   channel number
    */
  @name("snd_seq_remove_events_get_channel")
  def snd_seq_remove_events_get_channel(
      info: Ptr[snd_seq_remove_events_t]
  ): CInt = extern

  /** Get the event type as removal condition.
    *
    * @param info
    *   remove_events container
    * @return
    *   event type
    */
  @name("snd_seq_remove_events_get_event_type")
  def snd_seq_remove_events_get_event_type(
      info: Ptr[snd_seq_remove_events_t]
  ): CInt = extern

  /** Get the event tag id as removal condition.
    *
    * @param info
    *   remove_events container
    * @return
    *   tag id
    */
  @name("snd_seq_remove_events_get_tag")
  def snd_seq_remove_events_get_tag(info: Ptr[snd_seq_remove_events_t]): CInt =
    extern

  /** Set the removal condition bits.
    *
    * @param info
    *   remove_events container
    * @param flags
    *   removal condition bits
    */
  @name("snd_seq_remove_events_set_condition")
  def snd_seq_remove_events_set_condition(
      info: Ptr[snd_seq_remove_events_t],
      flags: CUnsignedInt
  ): Unit = extern

  /** Set the queue as removal condition.
    *
    * @param info
    *   remove_events container
    * @param queue
    *   queue id
    */
  @name("snd_seq_remove_events_set_queue")
  def snd_seq_remove_events_set_queue(
      info: Ptr[snd_seq_remove_events_t],
      queue: CInt
  ): Unit = extern

  /** Set the timestamp as removal condition.
    *
    * @param info
    *   remove_events container
    * @param time
    *   timestamp pointer
    */
  @name("snd_seq_remove_events_set_time")
  def snd_seq_remove_events_set_time(
      info: Ptr[snd_seq_remove_events_t],
      time: Ptr[snd_seq_timestamp_t]
  ): Unit = extern

  /** Set the destination address as removal condition.
    *
    * @param info
    *   remove_events container
    * @param addr
    *   destination address
    */
  @name("snd_seq_remove_events_set_dest")
  def snd_seq_remove_events_set_dest(
      info: Ptr[snd_seq_remove_events_t],
      addr: Ptr[snd_seq_addr_t]
  ): Unit = extern

  /** Set the channel as removal condition.
    *
    * @param info
    *   remove_events container
    * @param channel
    *   channel number
    */
  @name("snd_seq_remove_events_set_channel")
  def snd_seq_remove_events_set_channel(
      info: Ptr[snd_seq_remove_events_t],
      channel: CInt
  ): Unit = extern

  /** Set the event type as removal condition.
    *
    * @param info
    *   remove_events container
    * @param type
    *   event type
    */
  @name("snd_seq_remove_events_set_event_type")
  def snd_seq_remove_events_set_event_type(
      info: Ptr[snd_seq_remove_events_t],
      `type`: CInt
  ): Unit = extern

  /** Set the event tag as removal condition.
    *
    * @param info
    *   remove_events container
    * @param tag
    *   tag id
    */
  @name("snd_seq_remove_events_set_tag")
  def snd_seq_remove_events_set_tag(
      info: Ptr[snd_seq_remove_events_t],
      tag: CInt
  ): Unit = extern

  /** remove events on input/output buffers and pools
    *
    * @param seq
    *   sequencer handle
    * @param rmp
    *   remove event container
    *
    * Removes matching events with the given condition from input/output buffers
    * and pools. The removal condition is specified in rmp argument.
    */
  @name("snd_seq_remove_events")
  def snd_seq_remove_events(
      handle: Ptr[snd_seq_t],
      info: Ptr[snd_seq_remove_events_t]
  ): CInt = extern

  /** output a UMP event
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   UMP event to be output
    * @return
    *   the number of remaining events or a negative error code
    *
    * Just like snd_seq_event_output(), it puts an event onto the buffer,
    * draining the buffer automatically when needed, but the event is
    * snd_seq_ump_event_t type instead snd_seq_event_t.
    *
    * Calling this function is allowed only when the client is set to
    * SND_SEQ_CLIENT_UMP_MIDI_1_0 or SND_SEQ_CLIENT_UMP_MIDI_2_0.
    *
    * The flushing and clearing of the buffer is done via the same functions,
    * snd_seq_event_drain_output() and snd_seq_drop_output().
    */
  @name("snd_seq_ump_event_output")
  def snd_seq_ump_event_output(
      seq: Ptr[snd_seq_t],
      ev: Ptr[snd_seq_ump_event_t]
  ): CInt = extern

  /** output an event onto the lib buffer without draining buffer
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   UMP event to be output
    * @return
    *   the byte size of remaining events. -EAGAIN if the buffer becomes full.
    *
    * This is a UMP event version of snd_seq_event_output_buffer().
    */
  @name("snd_seq_ump_event_output_buffer")
  def snd_seq_ump_event_output_buffer(
      seq: Ptr[snd_seq_t],
      ev: Ptr[snd_seq_ump_event_t]
  ): CInt = extern

  /** extract the first UMP event in output buffer
    *
    * @param seq
    *   sequencer handle
    * @param ev_res
    *   UMP event pointer to be extracted
    * @return
    *   0 on success otherwise a negative error code
    *
    * This is a UMP event version of snd_seq_extract_output().
    */
  @name("snd_seq_ump_extract_output")
  def snd_seq_ump_extract_output(
      seq: Ptr[snd_seq_t],
      ev_res: Ptr[Ptr[snd_seq_ump_event_t]]
  ): CInt = extern

  /** output a UMP event directly to the sequencer NOT through output buffer
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   UMP event to be output
    * @return
    *   the byte size sent to sequencer or a negative error code
    *
    * This is a UMP event version of snd_seq_event_output_direct().
    */
  @name("snd_seq_ump_event_output_direct")
  def snd_seq_ump_event_output_direct(
      seq: Ptr[snd_seq_t],
      ev: Ptr[snd_seq_ump_event_t]
  ): CInt = extern

  /** retrieve a UMP event from sequencer
    *
    * @param seq
    *   sequencer handle
    * @param ev
    *   UMP event pointer to be stored
    *
    * Like snd_seq_event_input(), this reads out the input event, but in
    * snd_seq_ump_event_t type instead of snd_seq_event_t type.
    *
    * Calling this function is allowed only when the client is set to
    * SND_SEQ_CLIENT_UMP_MIDI_1_0 or SND_SEQ_CLIENT_UMP_MIDI_2_0.
    *
    * For other input operations, the same function like
    * snd_seq_event_input_pending() or snd_seq_drop_input() can be still used.
    */
  @name("snd_seq_ump_event_input")
  def snd_seq_ump_event_input(
      seq: Ptr[snd_seq_t],
      ev: Ptr[Ptr[snd_seq_ump_event_t]]
  ): CInt = extern

}
