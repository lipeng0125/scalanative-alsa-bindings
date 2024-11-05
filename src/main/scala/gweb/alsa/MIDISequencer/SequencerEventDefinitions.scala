package gweb.alsa.MIDISequencer
import scala.scalanative.unsafe.*

import scala.scalanative.unsafe.Nat.Digit2
import scala.scalanative.unsafe.Tag.Nat3
import gweb.alsa.Iota

/** Sequencer Event Definitions
  */
@link("asound")
@extern
object SequencerEventDefinitions {

  /** Sequencer event address
    */
  type snd_seq_addr_t = CStruct2[
    /** Client id
      */
    CUnsignedChar, // client
    /** Port id
      */
    CUnsignedChar // port
  ]

  /** Connection (subscription) between ports
    */
  type snd_seq_connect_t = CStruct2[
    /** sender address
      */
    snd_seq_addr_t, // sender
    /** destination address
      */
    snd_seq_addr_t // dest
  ]

  /** Real-time data record
    */
  type snd_seq_real_time = CStruct2[
    /** seconds
      */
    CUnsignedInt, // tv_sec
    /** nanoseconds
      */
    CUnsignedInt // tv_nsec
  ]

  type snd_seq_real_time_t = snd_seq_real_time

  /** unioned time stamp
    */
  type snd_seq_timestamp_t = CStruct2[
    /** tick-time
      */
    snd_seq_tick_time_t, // tick
    /** real-time
      */
    snd_seq_real_time // time
  ]

  /** Note event
    */
  type snd_seq_ev_note_t = CStruct5[
    /** channel number
      */
    CUnsignedChar, // channel
    /** note
      */
    CUnsignedChar, // note
    /** velocity
      */
    CUnsignedChar, // velocity
    /** note-off velocity; only for SND_SEQ_EVENT_NOTE
      */
    CUnsignedChar, // off_velocity
    /** duration until note-off; only for SND_SEQ_EVENT_NOTE
      */
    CUnsignedInt // duration
  ]

  /** Controller event
    */
  type snd_seq_ev_ctrl_t =
    CStruct4[
      /** channel number
        */
      CUnsignedChar, // channel
      /** reserved
        */
      CArray[CUnsignedChar, Nat._3], // unused
      /** control parameter
        */
      CUnsignedInt, // param
      /** control value
        */
      CInt // value
    ]

  /** generic set of bytes (12x8 bit)
    */
  type snd_seq_ev_raw8_t = CStruct1[
    /** 8 bit value
      */
    CArray[CUnsignedChar, Digit2[Nat._1, Nat._2]] // d
  ]

  /** generic set of integers (3x32 bit)
    */
  type snd_seq_ev_raw32_t = CStruct1[
    /** 32 bit value
      */
    CArray[CUnsignedInt, Nat._3] // d
  ]

  /** external stored data
    */
  type snd_seq_ev_ext = CStruct2[
    /** length of data
      */
    CUnsignedInt, // len
    /** pointer to data (note: can be 64-bit)
      */
    CVoidPtr // ptr
  ]

  /** Result events
    */
  type snd_seq_result_t = CStruct2[
    /** processed event type
      */
    CInt, // event
    /** status
      */
    CInt // result
  ]

  /** Queue skew values
    */
  type snd_seq_queue_skew_t = CStruct2[
    /** skew value
      */
    CUnsignedInt, // value
    /** skew base
      */
    CUnsignedInt // base
  ]

  /** queue timer control
    */
  type snd_seq_ev_queue_control_t =
    CStruct3[
      /** affected queue
        */
      CUnsignedChar, // queue
      /** reserved
        */
      CArray[CUnsignedChar, Nat._3], // unused
      /** data value union
        */
      /** affected value (e.g. tempo)
        */
      CInt // value
      /** time
        */
      | snd_seq_timestamp_t // time
      /** sync position
        */
      | CInt // position
      /** queue skew
        */
      | snd_seq_queue_skew_t //  skew
      /** any data
        */
      | CArray[CUnsignedInt, Nat._2] // d32
      /** any data
        */
      | CArray[CUnsignedChar, Nat._8] // d8
      // 	param
    ]

  /** Sequencer event data
    */
  type snd_seq_event_data_t = CStruct10[
    /** note information
      */
    snd_seq_ev_note_t, //	note
    /** MIDI control information
      */
    snd_seq_ev_ctrl_t, // control
    /** raw8 data
      */
    snd_seq_ev_raw8_t, //	raw8
    /** raw32 data
      */
    snd_seq_ev_raw32_t, // raw32
    /** external data
      */
    snd_seq_ev_ext_t, //	ext
    /** queue control
      */
    snd_seq_ev_queue_control_t, // 	queue
    /** timestamp
      */
    snd_seq_timestamp_t, //	time
    /** address
      */
    snd_seq_addr_t, // addr
    /** connect information
      */
    snd_seq_connect_t, // connect
    /** operation result code
      */
    snd_seq_result_t //	result
  ]

  /** Sequencer event
    */
  type snd_seq_event_t = CStruct8[
    /** event type
      */
    snd_seq_event_type_t, // type
    /** event flags
      */
    CUnsignedChar, //	flags
    /** tag
      */
    CUnsignedChar, //	tag
    /** schedule queue
      */
    CUnsignedChar, //	queue
    /** schedule time
      */
    snd_seq_timestamp_t, //	time
    /** source address
      */
    snd_seq_addr_t, //	source
    /** destination address
      */
    snd_seq_addr_t, //	dest
    /** event data...
      */
    snd_seq_event_data_t //	data
  ]

  /** UMP sequencer event; compatible with legacy sequencer event
    */
  type snd_seq_ump_event_t = CStruct8[
    /** event type
      */
    snd_seq_event_type_t, // type
    /** event flags
      */
    CUnsignedChar, // flags
    /** tag
      */
    CUnsignedChar, // tag
    /** schedule queue
      */
    CUnsignedChar, // queue
    /** schedule time
      */
    snd_seq_timestamp_t, // time
    /** source address
      */
    snd_seq_addr_t, // source
    /** destination address
      */
    snd_seq_addr_t, // dest
    /** (shared) legacy data
      */
    snd_seq_event_data_t // data
    /** UMP data bytes
      */
    | CArray[CUnsignedInt, Nat._4] // ump
  ]

  /** Event mode flags
    *
    * NOTE: only 8 bits available! timestamp in clock ticks
    */
  inline val SND_SEQ_TIME_STAMP_TICK = (0 << 0)

  /** timestamp in real time
    */
  inline val SND_SEQ_TIME_STAMP_REAL = (1 << 0)

  /** mask for timestamp bits
    */
  inline val SND_SEQ_TIME_STAMP_MASK = (1 << 0)

  /** absolute timestamp
    */
  inline val SND_SEQ_TIME_MODE_ABS = (0 << 1)

  /** relative to current time
    */
  inline val SND_SEQ_TIME_MODE_REL = (1 << 1)

  /** mask for time mode bits
    */
  inline val SND_SEQ_TIME_MODE_MASK = (1 << 1)

  /** fixed event size
    */
  inline val SND_SEQ_EVENT_LENGTH_FIXED = (0 << 2)

  /** variable event size
    */
  inline val SND_SEQ_EVENT_LENGTH_VARIABLE = (1 << 2)

  /** variable event size - user memory space
    */
  inline val SND_SEQ_EVENT_LENGTH_VARUSR = (2 << 2)

  /** mask for event length bits
    */
  inline val SND_SEQ_EVENT_LENGTH_MASK = (3 << 2)

  /** normal priority
    */
  inline val SND_SEQ_PRIORITY_NORMAL = (0 << 4)

  /** event should be processed before others
    */
  inline val SND_SEQ_PRIORITY_HIGH = (1 << 4)

  /** mask for priority bits
    */
  inline val SND_SEQ_PRIORITY_MASK = (1 << 4)

  /** UMP packet event
    */
  inline val SND_SEQ_EVENT_UMP = (1 << 5)

  /** Sequencer event data type
    */
  type snd_seq_event_type_t = CUnsignedChar

  /** (MIDI) Tick-time data record
    */
  type snd_seq_tick_time_t = CUnsignedInt

  /** external stored data
    */
  type snd_seq_ev_ext_t = snd_seq_ev_ext

  /** Sequencer event type
    */
  class snd_seq_event_type private (val value: CInt) extends AnyVal

  object snd_seq_event_type extends Iota {

    /** system status; event data type = snd_seq_result_t */
    val SND_SEQ_EVENT_SYSTEM = iota(0)

    /** returned result status; event data type = snd_seq_result_t */
    val SND_SEQ_EVENT_RESULT = iota

    /** note on and off with duration; event data type = snd_seq_ev_note_t */
    val SND_SEQ_EVENT_NOTE = iota(5)

    /** note on; event data type = snd_seq_ev_note_t */
    val SND_SEQ_EVENT_NOTEON = iota

    /** note off; event data type = snd_seq_ev_note_t */
    val SND_SEQ_EVENT_NOTEOFF = iota

    /** key pressure change (aftertouch); event data type = snd_seq_ev_note_t */
    val SND_SEQ_EVENT_KEYPRESS = iota

    /** controller; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_CONTROLLER = iota(10)

    /** program change; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_PGMCHANGE = iota

    /** channel pressure; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_CHANPRESS = iota

    /** pitchwheel; event data type = snd_seq_ev_ctrl_t; data is from -8192 to
      * 8191)
      */
    val SND_SEQ_EVENT_PITCHBEND = iota

    /** 14 bit controller value; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_CONTROL14 = iota

    /** 14 bit NRPN; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_NONREGPARAM = iota

    /** 14 bit RPN; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_REGPARAM = iota

    /** SPP with LSB and MSB values; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_SONGPOS = iota(20)

    /** Song Select with song ID number; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_SONGSEL = iota

    /** midi time code quarter frame; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_QFRAME = iota

    /** SMF Time Signature event; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_TIMESIGN = iota

    /** SMF Key Signature event; event data type = snd_seq_ev_ctrl_t */
    val SND_SEQ_EVENT_KEYSIGN = iota

    /** MIDI Real Time Start message; event data type =
      * snd_seq_ev_queue_control_t
      */
    val SND_SEQ_EVENT_START = iota(30)

    /** MIDI Real Time Continue message; event data type =
      * snd_seq_ev_queue_control_t
      */
    val SND_SEQ_EVENT_CONTINUE = iota

    /** MIDI Real Time Stop message; event data type =
      * snd_seq_ev_queue_control_t
      */
    val SND_SEQ_EVENT_STOP = iota

    /** Set tick queue position; event data type = snd_seq_ev_queue_control_t */
    val SND_SEQ_EVENT_SETPOS_TICK = iota

    /** Set real-time queue position; event data type =
      * snd_seq_ev_queue_control_t
      */
    val SND_SEQ_EVENT_SETPOS_TIME = iota

    /** (SMF) Tempo event; event data type = snd_seq_ev_queue_control_t */
    val SND_SEQ_EVENT_TEMPO = iota

    /** MIDI Real Time Clock message; event data type =
      * snd_seq_ev_queue_control_t
      */
    val SND_SEQ_EVENT_CLOCK = iota

    /** MIDI Real Time Tick message; event data type =
      * snd_seq_ev_queue_control_t
      */
    val SND_SEQ_EVENT_TICK = iota

    /** Queue timer skew; event data type = snd_seq_ev_queue_control_t */
    val SND_SEQ_EVENT_QUEUE_SKEW = iota

    /** Sync position changed; event data type = snd_seq_ev_queue_control_t */
    val SND_SEQ_EVENT_SYNC_POS = iota

    /** Tune request; event data type = none */
    val SND_SEQ_EVENT_TUNE_REQUEST = iota(40)

    /** Reset to power-on state; event data type = none */
    val SND_SEQ_EVENT_RESET = iota

    /** Active sensing event; event data type = none */
    val SND_SEQ_EVENT_SENSING = iota

    /** Echo-back event; event data type = any type */
    val SND_SEQ_EVENT_ECHO = iota(50)

    /** OSS emulation raw event; event data type = any type */
    val SND_SEQ_EVENT_OSS = iota

    /** New client has connected; event data type = snd_seq_addr_t */
    val SND_SEQ_EVENT_CLIENT_START = iota(60)

    /** Client has left the system; event data type = snd_seq_addr_t */
    val SND_SEQ_EVENT_CLIENT_EXIT = iota

    /** Client status/info has changed; event data type = snd_seq_addr_t */
    val SND_SEQ_EVENT_CLIENT_CHANGE = iota

    /** New port was created; event data type = snd_seq_addr_t */
    val SND_SEQ_EVENT_PORT_START = iota

    /** Port was deleted from system; event data type = snd_seq_addr_t */
    val SND_SEQ_EVENT_PORT_EXIT = iota

    /** Port status/info has changed; event data type = snd_seq_addr_t */
    val SND_SEQ_EVENT_PORT_CHANGE = iota

    /** Ports connected; event data type = snd_seq_connect_t */
    val SND_SEQ_EVENT_PORT_SUBSCRIBED = iota

    /** Ports disconnected; event data type = snd_seq_connect_t */
    val SND_SEQ_EVENT_PORT_UNSUBSCRIBED = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR0 = iota(90)

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR1 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR2 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR3 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR4 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR5 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR6 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR7 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR8 = iota

    /** user-defined event; event data type = any (fixed size) */
    val SND_SEQ_EVENT_USR9 = iota

    /** system exclusive data (variable length); event data type =
      * snd_seq_ev_ext_t
      */
    val SND_SEQ_EVENT_SYSEX = iota(130)

    /** error event; event data type = snd_seq_ev_ext_t */
    val SND_SEQ_EVENT_BOUNCE = iota

    /** reserved for user apps; event data type = snd_seq_ev_ext_t */
    val SND_SEQ_EVENT_USR_VAR0 = iota(135)

    /** reserved for user apps; event data type = snd_seq_ev_ext_t */
    val SND_SEQ_EVENT_USR_VAR1 = iota

    /** reserved for user apps; event data type = snd_seq_ev_ext_t */
    val SND_SEQ_EVENT_USR_VAR2 = iota

    /** reserved for user apps; event data type = snd_seq_ev_ext_t */
    val SND_SEQ_EVENT_USR_VAR3 = iota

    /** reserved for user apps; event data type = snd_seq_ev_ext_t */
    val SND_SEQ_EVENT_USR_VAR4 = iota

    /** NOP; ignored in any case */
    val SND_SEQ_EVENT_NONE = iota(255)

  }
}
