package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import scala.scalanative.runtime.struct
import scala.scalanative.unsafe.Nat.Digit2
import scala.scalanative.unsafe.Tag.Nat8
import scala.scalanative.unsafe.Tag.Nat4
import scala.scalanative.unsafe.Nat._1
import scala.scalanative.unsafe.Nat._6
import gweb.alsa.Iota
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.pollfd
import gweb.alsa.GlobalDefinesAndFunctions.snd_async_handler_t
import gweb.alsa.GlobalDefinesAndFunctions.snd_async_callback_t
import gweb.alsa.GlobalDefinesAndFunctions.snd_htimestamp_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object PCMInterface {

  /** PCM audio timestamp config TODO how to interrop with bit-field
    *
    * todo how to interop with bit-fields
    */
  type snd_pcm_audio_tstamp_config_t = CUnsignedInt

  /** PCM audio timestamp report
    *
    * todo how to interop with bit-fields
    */
  type snd_pcm_audio_tstamp_report_t = CUnsignedInt

  /** PCM area specification
    */
  type snd_pcm_channel_area_t = CStruct3[
    /** base address of channel samples
      */
    CVoidPtr, // addr
    /** offset to first sample in bits
      */
    CUnsignedInt, // first
    /** samples distance in bits
      */
    CUnsignedInt // step
  ]

  /** PCM synchronization ID
    */
  type snd_pcm_sync_id_t = CStruct1[
    /** 8-bit ID
      */
    CArray[CUnsignedChar, Digit2[_1, _6]] // id
    /** 16-bit ID
      */
    | CArray[CUnsignedShort, Nat._8] // id16
    /** 32-bit ID
      */
    | CArray[CUnsignedInt, Nat._4] // id32
  ]

  /** the channel map header
    */
  type snd_pcm_chmap_t = CStruct2[
    /** number of channels
      */
    CUnsignedInt, // channels
    /** channel position array
      */
    CArray[CUnsignedInt, Nat._0] // pos
  ]

  /** the header of array items returned from snd_pcm_query_chmaps()
    */
  type snd_pcm_chmap_query_t = CStruct2[
    /** channel map type
      */
    snd_pcm_chmap_type, // type
    /** available channel map
      */
    snd_pcm_chmap_t // map
  ]

  /** dlsym version for interface entry callback */
  inline val SND_PCM_DLSYM_VERSION = "_dlsym_pcm_001"

  /** Non blocking mode (flag for open mode) */
  inline val SND_PCM_NONBLOCK = true

  /** Async notification (flag for open mode) */
  inline val SND_PCM_ASYNC = true

  /** Return EINTR instead blocking (wait operation) */
  inline val SND_PCM_EINTR = 0x00000080

  /** In an abort state (internal, not allowed for open) */
  inline val SND_PCM_ABORT = 0x00008000

  /** Disable automatic (but not forced!) rate resamplinig */
  inline val SND_PCM_NO_AUTO_RESAMPLE = 0x00010000

  /** Disable automatic (but not forced!) channel conversion */
  inline val SND_PCM_NO_AUTO_CHANNELS = 0x00020000

  /** Disable automatic (but not forced!) format conversion */
  inline val SND_PCM_NO_AUTO_FORMAT = 0x00040000

  /** Disable soft volume control */
  inline val SND_PCM_NO_SOFTVOL = 0x00080000

  /** Infinite wait for snd_pcm_wait() */
  inline val SND_PCM_WAIT_INFINITE = (-1)

  /** Wait for next i/o in snd_pcm_wait() */
  inline val SND_PCM_WAIT_IO = (-10001)

  /** Wait for drain in snd_pcm_wait() */
  inline val SND_PCM_WAIT_DRAIN = (-10002)

  /** channel mapping API version number */
  inline val SND_CHMAP_API_VERSION = ((1 << 16) | (0 << 8) | 1)

  /** bitmask for channel position */
  inline val SND_CHMAP_POSITION_MASK = 0xffff

  /** bit flag indicating the channel is phase inverted */
  inline val SND_CHMAP_PHASE_INVERSE = (0x01 << 16)

  /** bit flag indicating the non-standard channel value */
  inline val SND_CHMAP_DRIVER_SPEC = (0x02 << 16)

  /** PCM generic info container */
  type snd_pcm_info_t = CStruct0

  /** PCM hardware configuration space container
    *
    * snd_pcm_hw_params_t is an opaque structure which contains a set of
    * possible PCM hardware configurations. For example, a given instance might
    * include a range of buffer sizes, a range of period sizes, and a set of
    * several sample formats. Some subset of all possible combinations these
    * sets may be valid, but not necessarily any combination will be valid.
    *
    * When a parameter is set or restricted using a snd_pcm_hw_params_set*
    * function, all of the other ranges will be updated to exclude as many
    * impossible configurations as possible. Attempting to set a parameter
    * outside of its acceptable range will result in the function failing and an
    * error code being returned.
    */
  type snd_pcm_hw_params_t = CStruct0

  /** PCM software configuration container */
  type snd_pcm_sw_params_t = CStruct0

  /** PCM status container */
  type snd_pcm_status_t = CStruct0

  /** PCM access types mask */
  type snd_pcm_access_mask_t = CStruct0

  /** PCM formats mask */
  type snd_pcm_format_mask_t = CStruct0

  /** PCM subformats mask */
  type snd_pcm_subformat_mask_t = CStruct0

  /** Unsigned frames quantity */
  type snd_pcm_uframes_t = CUnsignedLong

  /** Signed frames quantity */
  type snd_pcm_sframes_t = CLong

  /** PCM handle */
  type snd_pcm_t = CStruct0

  /** PCM type */
  type snd_pcm_type_t = _snd_pcm_type

  /** SND_PCM_TYPE_METER scope handle */
  type snd_pcm_scope_t = CStruct0

  /** PCM class
    */
  class snd_pcm_class_t private (val value: CInt) extends AnyVal

  object snd_pcm_class_t extends Iota {

    /** standard device */
    val SND_PCM_CLASS_GENERIC = iota(0)

    /** multichannel device */
    val SND_PCM_CLASS_MULTI = iota

    /** software modem device */
    val SND_PCM_CLASS_MODEM = iota

    /** digitizer device */
    val SND_PCM_CLASS_DIGITIZER = iota

    /** */
    val SND_PCM_CLASS_LAST = iota(SND_PCM_CLASS_DIGITIZER)

  }

  /** PCM subclass
    */
  class snd_pcm_subclass_t private (val value: CInt) extends AnyVal

  object snd_pcm_subclass_t extends Iota {

    /** subdevices are mixed together */
    val SND_PCM_SUBCLASS_GENERIC_MIX = iota(0)

    /** multichannel subdevices are mixed together */
    val SND_PCM_SUBCLASS_MULTI_MIX = iota

    /** */
    val SND_PCM_SUBCLASS_LAST = iota(SND_PCM_SUBCLASS_MULTI_MIX)

  }

  /** PCM stream (direction)
    */
  class snd_pcm_stream_t private (val value: CInt) extends AnyVal

  object snd_pcm_stream_t extends Iota {

    /** Playback stream */
    val SND_PCM_STREAM_PLAYBACK = iota(0)

    /** Capture stream */
    val SND_PCM_STREAM_CAPTURE = iota

    /** */
    val SND_PCM_STREAM_LAST = iota(SND_PCM_STREAM_CAPTURE)

  }

  /** PCM access type
    */
  class snd_pcm_access_t private (val value: CInt) extends AnyVal

  object snd_pcm_access_t extends Iota {

    /** mmap access with simple interleaved channels */
    val SND_PCM_ACCESS_MMAP_INTERLEAVED = iota(0)

    /** mmap access with simple non interleaved channels */
    val SND_PCM_ACCESS_MMAP_NONINTERLEAVED = iota

    /** mmap access with complex placement */
    val SND_PCM_ACCESS_MMAP_COMPLEX = iota

    /** snd_pcm_readi/snd_pcm_writei access */
    val SND_PCM_ACCESS_RW_INTERLEAVED = iota

    /** snd_pcm_readn/snd_pcm_writen access */
    val SND_PCM_ACCESS_RW_NONINTERLEAVED = iota

    /** */
    val SND_PCM_ACCESS_LAST = iota(SND_PCM_ACCESS_RW_NONINTERLEAVED)

  }

  /** PCM sample format
    */
  class snd_pcm_format_t private (val value: CInt) extends AnyVal

  object snd_pcm_format_t extends Iota {

    /** Unknown */
    val SND_PCM_FORMAT_UNKNOWN = iota(-1)

    /** Signed 8 bit */
    val SND_PCM_FORMAT_S8 = iota(0)

    /** Unsigned 8 bit */
    val SND_PCM_FORMAT_U8 = iota

    /** Signed 16 bit Little Endian */
    val SND_PCM_FORMAT_S16_LE = iota

    /** Signed 16 bit Big Endian */
    val SND_PCM_FORMAT_S16_BE = iota

    /** Unsigned 16 bit Little Endian */
    val SND_PCM_FORMAT_U16_LE = iota

    /** Unsigned 16 bit Big Endian */
    val SND_PCM_FORMAT_U16_BE = iota

    /** Signed 24 bit Little Endian using low three bytes in 32-bit word */
    val SND_PCM_FORMAT_S24_LE = iota

    /** Signed 24 bit Big Endian using low three bytes in 32-bit word */
    val SND_PCM_FORMAT_S24_BE = iota

    /** Unsigned 24 bit Little Endian using low three bytes in 32-bit word */
    val SND_PCM_FORMAT_U24_LE = iota

    /** Unsigned 24 bit Big Endian using low three bytes in 32-bit word */
    val SND_PCM_FORMAT_U24_BE = iota

    /** Signed 32 bit Little Endian */
    val SND_PCM_FORMAT_S32_LE = iota

    /** Signed 32 bit Big Endian */
    val SND_PCM_FORMAT_S32_BE = iota

    /** Unsigned 32 bit Little Endian */
    val SND_PCM_FORMAT_U32_LE = iota

    /** Unsigned 32 bit Big Endian */
    val SND_PCM_FORMAT_U32_BE = iota

    /** Float 32 bit Little Endian, Range -1.0 to 1.0 */
    val SND_PCM_FORMAT_FLOAT_LE = iota

    /** Float 32 bit Big Endian, Range -1.0 to 1.0 */
    val SND_PCM_FORMAT_FLOAT_BE = iota

    /** Float 64 bit Little Endian, Range -1.0 to 1.0 */
    val SND_PCM_FORMAT_FLOAT64_LE = iota

    /** Float 64 bit Big Endian, Range -1.0 to 1.0 */
    val SND_PCM_FORMAT_FLOAT64_BE = iota

    /** IEC-958 Little Endian */
    val SND_PCM_FORMAT_IEC958_SUBFRAME_LE = iota

    /** IEC-958 Big Endian */
    val SND_PCM_FORMAT_IEC958_SUBFRAME_BE = iota

    /** Mu-Law */
    val SND_PCM_FORMAT_MU_LAW = iota

    /** A-Law */
    val SND_PCM_FORMAT_A_LAW = iota

    /** Ima-ADPCM */
    val SND_PCM_FORMAT_IMA_ADPCM = iota

    /** MPEG */
    val SND_PCM_FORMAT_MPEG = iota

    /** GSM */
    val SND_PCM_FORMAT_GSM = iota

    /** Signed 20bit Little Endian in 4bytes format, LSB justified */
    val SND_PCM_FORMAT_S20_LE = iota

    /** Signed 20bit Big Endian in 4bytes format, LSB justified */
    val SND_PCM_FORMAT_S20_BE = iota

    /** Unsigned 20bit Little Endian in 4bytes format, LSB justified */
    val SND_PCM_FORMAT_U20_LE = iota

    /** Unsigned 20bit Big Endian in 4bytes format, LSB justified */
    val SND_PCM_FORMAT_U20_BE = iota

    /** Special */
    val SND_PCM_FORMAT_SPECIAL = iota(31)

    /** Signed 24bit Little Endian in 3bytes format */
    val SND_PCM_FORMAT_S24_3LE = iota(32)

    /** Signed 24bit Big Endian in 3bytes format */
    val SND_PCM_FORMAT_S24_3BE = iota

    /** Unsigned 24bit Little Endian in 3bytes format */
    val SND_PCM_FORMAT_U24_3LE = iota

    /** Unsigned 24bit Big Endian in 3bytes format */
    val SND_PCM_FORMAT_U24_3BE = iota

    /** Signed 20bit Little Endian in 3bytes format */
    val SND_PCM_FORMAT_S20_3LE = iota

    /** Signed 20bit Big Endian in 3bytes format */
    val SND_PCM_FORMAT_S20_3BE = iota

    /** Unsigned 20bit Little Endian in 3bytes format */
    val SND_PCM_FORMAT_U20_3LE = iota

    /** Unsigned 20bit Big Endian in 3bytes format */
    val SND_PCM_FORMAT_U20_3BE = iota

    /** Signed 18bit Little Endian in 3bytes format */
    val SND_PCM_FORMAT_S18_3LE = iota

    /** Signed 18bit Big Endian in 3bytes format */
    val SND_PCM_FORMAT_S18_3BE = iota

    /** Unsigned 18bit Little Endian in 3bytes format */
    val SND_PCM_FORMAT_U18_3LE = iota

    /** Unsigned 18bit Big Endian in 3bytes format */
    val SND_PCM_FORMAT_U18_3BE = iota

    /** */
    val SND_PCM_FORMAT_G723_24 = iota

    /** */
    val SND_PCM_FORMAT_G723_24_1B = iota

    /** */
    val SND_PCM_FORMAT_G723_40 = iota

    /** */
    val SND_PCM_FORMAT_G723_40_1B = iota

    /** */
    val SND_PCM_FORMAT_DSD_U8 = iota

    /** */
    val SND_PCM_FORMAT_DSD_U16_LE = iota

    /** */
    val SND_PCM_FORMAT_DSD_U32_LE = iota

    /** */
    val SND_PCM_FORMAT_DSD_U16_BE = iota

    /** */
    val SND_PCM_FORMAT_DSD_U32_BE = iota

    /** */
    val SND_PCM_FORMAT_LAST = iota(SND_PCM_FORMAT_DSD_U32_BE)

    /** Signed 16 bit CPU endian */
    val SND_PCM_FORMAT_S16 = iota(SND_PCM_FORMAT_S16_LE)

    /** Unsigned 16 bit CPU endian */
    val SND_PCM_FORMAT_U16 = iota(SND_PCM_FORMAT_U16_LE)

    /** Signed 24 bit CPU endian */
    val SND_PCM_FORMAT_S24 = iota(SND_PCM_FORMAT_S24_LE)

    /** Unsigned 24 bit CPU endian */
    val SND_PCM_FORMAT_U24 = iota(SND_PCM_FORMAT_U24_LE)

    /** Signed 32 bit CPU endian */
    val SND_PCM_FORMAT_S32 = iota(SND_PCM_FORMAT_S32_LE)

    /** Unsigned 32 bit CPU endian */
    val SND_PCM_FORMAT_U32 = iota(SND_PCM_FORMAT_U32_LE)

    /** Float 32 bit CPU endian */
    val SND_PCM_FORMAT_FLOAT = iota(SND_PCM_FORMAT_FLOAT_LE)

    /** Float 64 bit CPU endian */
    val SND_PCM_FORMAT_FLOAT64 = iota(SND_PCM_FORMAT_FLOAT64_LE)

    /** IEC-958 CPU Endian */
    val SND_PCM_FORMAT_IEC958_SUBFRAME = iota(SND_PCM_FORMAT_IEC958_SUBFRAME_LE)

    /** Signed 20bit in 4bytes format, LSB justified, CPU Endian */
    val SND_PCM_FORMAT_S20 = iota(SND_PCM_FORMAT_S20_LE)

    /** Unsigned 20bit in 4bytes format, LSB justified, CPU Endian */
    val SND_PCM_FORMAT_U20 = iota(SND_PCM_FORMAT_U20_LE)

  }

  /** PCM sample subformat
    */
  class snd_pcm_subformat_t private (val value: CInt) extends AnyVal

  object snd_pcm_subformat_t extends Iota {

    /** Unknown */
    val SND_PCM_SUBFORMAT_UNKNOWN = iota(-1)

    /** Standard */
    val SND_PCM_SUBFORMAT_STD = iota(0)

    /** Maximum bits based on PCM format */
    val SND_PCM_SUBFORMAT_MSBITS_MAX = iota(1)

    /** 20 most significant bits */
    val SND_PCM_SUBFORMAT_MSBITS_20 = iota(2)

    /** 24 most significant bits */
    val SND_PCM_SUBFORMAT_MSBITS_24 = iota(3)

    /** */
    val SND_PCM_SUBFORMAT_LAST = iota(SND_PCM_SUBFORMAT_MSBITS_24)

  }

  /** PCM state
    */
  class snd_pcm_state_t private (val value: CInt) extends AnyVal

  object snd_pcm_state_t extends Iota {

    /** Open */
    val SND_PCM_STATE_OPEN = iota(0)

    /** Setup installed */
    val SND_PCM_STATE_SETUP = iota

    /** Ready to start */
    val SND_PCM_STATE_PREPARED = iota

    /** Running */
    val SND_PCM_STATE_RUNNING = iota

    /** Stopped: underrun (playback) or overrun (capture) detected */
    val SND_PCM_STATE_XRUN = iota

    /** Draining: running (playback) or stopped (capture) */
    val SND_PCM_STATE_DRAINING = iota

    /** Paused */
    val SND_PCM_STATE_PAUSED = iota

    /** Hardware is suspended */
    val SND_PCM_STATE_SUSPENDED = iota

    /** Hardware is disconnected */
    val SND_PCM_STATE_DISCONNECTED = iota

    /** */
    val SND_PCM_STATE_LAST = iota(SND_PCM_STATE_DISCONNECTED)

    /** Private - used internally in the library - do not use */
    val SND_PCM_STATE_PRIVATE1 = iota(1024)

  }

  /** PCM start mode
    */
  class snd_pcm_start_t private (val value: CInt) extends AnyVal

  object snd_pcm_start_t extends Iota {

    /** Automatic start on data read/write */
    val SND_PCM_START_DATA = iota(0)

    /** Explicit start */
    val SND_PCM_START_EXPLICIT = iota

    /** */
    val SND_PCM_START_LAST = iota(SND_PCM_START_EXPLICIT)

  }

  /** PCM xrun mode
    */
  class snd_pcm_xrun_t private (val value: CInt) extends AnyVal

  object snd_pcm_xrun_t extends Iota {

    /** Xrun detection disabled */
    val SND_PCM_XRUN_NONE = iota(0)

    /** Stop on xrun detection */
    val SND_PCM_XRUN_STOP = iota

    /** */
    val SND_PCM_XRUN_LAST = iota(SND_PCM_XRUN_STOP)

  }

  /** PCM timestamp mode
    */
  class snd_pcm_tstamp_t private (val value: CInt) extends AnyVal

  object snd_pcm_tstamp_t extends Iota {

    /** No timestamp */
    val SND_PCM_TSTAMP_NONE = iota(0)

    /** Update timestamp at every hardware position update */
    val SND_PCM_TSTAMP_ENABLE = iota

    /** Equivalent with SND_PCM_TSTAMP_ENABLE, just for compatibility with older
      * versions
      */
    val SND_PCM_TSTAMP_MMAP = iota(SND_PCM_TSTAMP_ENABLE)

    /** */
    val SND_PCM_TSTAMP_LAST = iota(SND_PCM_TSTAMP_ENABLE)

  }

  /** PCM timestamp type
    */
  class snd_pcm_tstamp_type_t private (val value: CInt) extends AnyVal

  object snd_pcm_tstamp_type_t extends Iota {

    /** gettimeofday equivalent */
    val SND_PCM_TSTAMP_TYPE_GETTIMEOFDAY = iota(0)

    /** posix_clock_monotonic equivalent */
    val SND_PCM_TSTAMP_TYPE_MONOTONIC = iota

    /** monotonic_raw (no NTP) */
    val SND_PCM_TSTAMP_TYPE_MONOTONIC_RAW = iota

    /** */
    val SND_PCM_TSTAMP_TYPE_LAST = iota(SND_PCM_TSTAMP_TYPE_MONOTONIC_RAW)

  }

  /** PCM audio timestamp type
    */
  class snd_pcm_audio_tstamp_type_t private (val value: CInt) extends AnyVal

  object snd_pcm_audio_tstamp_type_t extends Iota {

    /** first definition for backwards compatibility only, maps to
      * wallclock/link time for HDAudio playback and DEFAULT/DMA time for
      * everything else
      */
    val SND_PCM_AUDIO_TSTAMP_TYPE_COMPAT = iota(0)

    /** DMA time, reported as per hw_ptr */
    val SND_PCM_AUDIO_TSTAMP_TYPE_DEFAULT = iota(1)

    /** link time reported by sample or wallclock counter, reset on startup */
    val SND_PCM_AUDIO_TSTAMP_TYPE_LINK = iota(2)

    /** link time reported by sample or wallclock counter, not reset on startup
      */
    val SND_PCM_AUDIO_TSTAMP_TYPE_LINK_ABSOLUTE = iota(3)

    /** link time estimated indirectly */
    val SND_PCM_AUDIO_TSTAMP_TYPE_LINK_ESTIMATED = iota(4)

    /** link time synchronized with system time */
    val SND_PCM_AUDIO_TSTAMP_TYPE_LINK_SYNCHRONIZED = iota(5)

    /** */
    val SND_PCM_AUDIO_TSTAMP_TYPE_LAST = iota(
      SND_PCM_AUDIO_TSTAMP_TYPE_LINK_SYNCHRONIZED
    )

  }

  /** PCM type
    */
  class _snd_pcm_type private (val value: CInt) extends AnyVal

  object _snd_pcm_type extends Iota {

    /** Kernel level PCM */
    val SND_PCM_TYPE_HW = iota(0)

    /** Hooked PCM */
    val SND_PCM_TYPE_HOOKS = iota

    /** One or more linked PCM with exclusive access to selected channels */
    val SND_PCM_TYPE_MULTI = iota

    /** File writing plugin */
    val SND_PCM_TYPE_FILE = iota

    /** Null endpoint PCM */
    val SND_PCM_TYPE_NULL = iota

    /** Shared memory client PCM */
    val SND_PCM_TYPE_SHM = iota

    /** INET client PCM (not yet implemented) */
    val SND_PCM_TYPE_INET = iota

    /** Copying plugin */
    val SND_PCM_TYPE_COPY = iota

    /** Linear format conversion PCM */
    val SND_PCM_TYPE_LINEAR = iota

    /** A-Law format conversion PCM */
    val SND_PCM_TYPE_ALAW = iota

    /** Mu-Law format conversion PCM */
    val SND_PCM_TYPE_MULAW = iota

    /** IMA-ADPCM format conversion PCM */
    val SND_PCM_TYPE_ADPCM = iota

    /** Rate conversion PCM */
    val SND_PCM_TYPE_RATE = iota

    /** Attenuated static route PCM */
    val SND_PCM_TYPE_ROUTE = iota

    /** Format adjusted PCM */
    val SND_PCM_TYPE_PLUG = iota

    /** Sharing PCM */
    val SND_PCM_TYPE_SHARE = iota

    /** Meter plugin */
    val SND_PCM_TYPE_METER = iota

    /** Mixing PCM */
    val SND_PCM_TYPE_MIX = iota

    /** Attenuated dynamic route PCM (not yet implemented) */
    val SND_PCM_TYPE_DROUTE = iota

    /** Loopback server plugin (not yet implemented) */
    val SND_PCM_TYPE_LBSERVER = iota

    /** Linear Integer <-> Linear Float format conversion PCM */
    val SND_PCM_TYPE_LINEAR_FLOAT = iota

    /** LADSPA integration plugin */
    val SND_PCM_TYPE_LADSPA = iota

    /** Direct Mixing plugin */
    val SND_PCM_TYPE_DMIX = iota

    /** Jack Audio Connection Kit plugin */
    val SND_PCM_TYPE_JACK = iota

    /** Direct Snooping plugin */
    val SND_PCM_TYPE_DSNOOP = iota

    /** Direct Sharing plugin */
    val SND_PCM_TYPE_DSHARE = iota

    /** IEC958 subframe plugin */
    val SND_PCM_TYPE_IEC958 = iota

    /** Soft volume plugin */
    val SND_PCM_TYPE_SOFTVOL = iota

    /** External I/O plugin */
    val SND_PCM_TYPE_IOPLUG = iota

    /** External filter plugin */
    val SND_PCM_TYPE_EXTPLUG = iota

    /** Mmap-emulation plugin */
    val SND_PCM_TYPE_MMAP_EMUL = iota

    /** */
    val SND_PCM_TYPE_LAST = iota(SND_PCM_TYPE_MMAP_EMUL)

  }

  /** channel map list type
    */
  class snd_pcm_chmap_type private (val value: CInt) extends AnyVal

  object snd_pcm_chmap_type extends Iota {

    /** unspecified channel position */
    val SND_CHMAP_TYPE_NONE = iota(0)

    /** fixed channel position */
    val SND_CHMAP_TYPE_FIXED = iota

    /** freely swappable channel position */
    val SND_CHMAP_TYPE_VAR = iota

    /** pair-wise swappable channel position */
    val SND_CHMAP_TYPE_PAIRED = iota

    /** last entry */
    val SND_CHMAP_TYPE_LAST = iota(SND_CHMAP_TYPE_PAIRED)

  }

  /** channel positions
    */
  class snd_pcm_chmap_position private (val value: CInt) extends AnyVal

  object snd_pcm_chmap_position extends Iota {

    /** unspecified */
    val SND_CHMAP_UNKNOWN = iota(0)

    /** N/A, silent */
    val SND_CHMAP_NA = iota

    /** mono stream */
    val SND_CHMAP_MONO = iota

    /** front left */
    val SND_CHMAP_FL = iota

    /** front right */
    val SND_CHMAP_FR = iota

    /** rear left */
    val SND_CHMAP_RL = iota

    /** rear right */
    val SND_CHMAP_RR = iota

    /** front center */
    val SND_CHMAP_FC = iota

    /** LFE */
    val SND_CHMAP_LFE = iota

    /** side left */
    val SND_CHMAP_SL = iota

    /** side right */
    val SND_CHMAP_SR = iota

    /** rear center */
    val SND_CHMAP_RC = iota

    /** front left center */
    val SND_CHMAP_FLC = iota

    /** front right center */
    val SND_CHMAP_FRC = iota

    /** rear left center */
    val SND_CHMAP_RLC = iota

    /** rear right center */
    val SND_CHMAP_RRC = iota

    /** front left wide */
    val SND_CHMAP_FLW = iota

    /** front right wide */
    val SND_CHMAP_FRW = iota

    /** front left high */
    val SND_CHMAP_FLH = iota

    /** front center high */
    val SND_CHMAP_FCH = iota

    /** front right high */
    val SND_CHMAP_FRH = iota

    /** top center */
    val SND_CHMAP_TC = iota

    /** top front left */
    val SND_CHMAP_TFL = iota

    /** top front right */
    val SND_CHMAP_TFR = iota

    /** top front center */
    val SND_CHMAP_TFC = iota

    /** top rear left */
    val SND_CHMAP_TRL = iota

    /** top rear right */
    val SND_CHMAP_TRR = iota

    /** top rear center */
    val SND_CHMAP_TRC = iota

    /** top front left center */
    val SND_CHMAP_TFLC = iota

    /** top front right center */
    val SND_CHMAP_TFRC = iota

    /** top side left */
    val SND_CHMAP_TSL = iota

    /** top side right */
    val SND_CHMAP_TSR = iota

    /** left LFE */
    val SND_CHMAP_LLFE = iota

    /** right LFE */
    val SND_CHMAP_RLFE = iota

    /** bottom center */
    val SND_CHMAP_BC = iota

    /** bottom left center */
    val SND_CHMAP_BLC = iota

    /** bottom right center */
    val SND_CHMAP_BRC = iota

    /** */
    val SND_CHMAP_LAST = iota(SND_CHMAP_BRC)

  }

  /** Opens a PCM.
    * @param pcmp
    *   Returned PCM handle
    * @param name
    *   ASCII identifier of the PCM handle
    * @param stream
    *   Wanted stream
    * @param mode
    *   Open mode (see SND_PCM_NONBLOCK, SND_PCM_ASYNC)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_open")
  def snd_pcm_open(
      pcm: Ptr[Ptr[snd_pcm_t]],
      name: Ptr[CChar],
      stream: snd_pcm_stream_t,
      mode: CInt
  ): CInt = extern

  /** Opens a PCM using local configuration.
    * @param pcmp
    *   Returned PCM handle
    * @param name
    *   ASCII identifier of the PCM handle
    * @param stream
    *   Wanted stream
    * @param mode
    *   Open mode (see SND_PCM_NONBLOCK, SND_PCM_ASYNC)
    * @param lconf
    *   Local configuration
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_open_lconf")
  def snd_pcm_open_lconf(
      pcm: Ptr[Ptr[snd_pcm_t]],
      name: Ptr[CChar],
      stream: snd_pcm_stream_t,
      mode: CInt,
      lconf: Ptr[snd_config_t]
  ): CInt = extern

  /** Opens a fallback PCM.
    * @param pcmp
    *   Returned PCM handle
    * @param root
    *   Configuration root
    * @param name
    *   ASCII identifier of the PCM handle
    * @param orig_name
    *   The original ASCII name
    * @param stream
    *   Wanted stream
    * @param mode
    *   Open mode (see SND_PCM_NONBLOCK, SND_PCM_ASYNC)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_open_fallback")
  def snd_pcm_open_fallback(
      pcm: Ptr[Ptr[snd_pcm_t]],
      root: Ptr[snd_config_t],
      name: Ptr[CChar],
      orig_name: Ptr[CChar],
      stream: snd_pcm_stream_t,
      mode: CInt
  ): CInt = extern

  /** close PCM handle
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified PCM handle and frees all associated resources.
    */
  @name("snd_pcm_close")
  def snd_pcm_close(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** get identifier of PCM handle
    * @param pcm
    *   PCM handle
    * @return
    *   ascii identifier of PCM handle
    *
    * Returns the ASCII identifier of given PCM handle. It's the same identifier
    * specified in snd_pcm_open().
    */
  @name("snd_pcm_name")
  def snd_pcm_name(pcm: Ptr[snd_pcm_t]): Ptr[CChar] = extern

  /** get type of PCM handle
    * @param pcm
    *   PCM handle
    * @return
    *   type of PCM handle
    *
    * Returns the type snd_pcm_type_t of given PCM handle.
    */
  @name("snd_pcm_type")
  def snd_pcm_type(pcm: Ptr[snd_pcm_t]): snd_pcm_type_t = extern

  /** get stream for a PCM handle
    * @param pcm
    *   PCM handle
    * @return
    *   stream of PCM handle
    *
    * Returns the type snd_pcm_stream_t of given PCM handle.
    */
  @name("snd_pcm_stream")
  def snd_pcm_stream(pcm: Ptr[snd_pcm_t]): snd_pcm_stream_t = extern

  /** get count of poll descriptors for PCM handle
    * @param pcm
    *   PCM handle
    * @return
    *   count of poll descriptors
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_poll_descriptors_count")
  def snd_pcm_poll_descriptors_count(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** get poll descriptors
    * @param pcm
    *   PCM handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    *
    * This function fills the given poll descriptor structs for the specified
    * PCM handle. The poll desctiptor array should have the size returned by
    * ::snd_pcm_poll_descriptors_count() function.
    *
    * The result is intended for direct use with the poll() syscall.
    *
    * For reading the returned events of poll descriptor after poll() system
    * call, use ::snd_pcm_poll_descriptors_revents() function. The field values
    * in pollfd structs may be bogus regarding the stream direction from the
    * application perspective (POLLIN might not imply read direction and POLLOUT
    * might not imply write), but the ::snd_pcm_poll_descriptors_revents()
    * function does the right "demangling".
    *
    * You can use output from this function as arguments for the select()
    * syscall, too. Do not forget to translate POLLIN and POLLOUT events to
    * corresponding FD_SET arrays and demangle events using
    * ::snd_pcm_poll_descriptors_revents() .
    *
    * It is guaranteed that for the given PCM handle, the output poll descriptor
    * structs (and their count) will not change after hardware and software
    * parameters setup. Thus it is valid to call the function once when all
    * parameters are set and reuse its output for the lifetime of the stream
    * parameters.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_poll_descriptors")
  def snd_pcm_poll_descriptors(
      pcm: Ptr[snd_pcm_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get returned events from poll descriptors
    * @param pcm
    *   PCM handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   pointer to the returned (single) event
    * @return
    *   zero if success, otherwise a negative error code
    *
    * This function does "demangling" of the revents mask returned from the
    * poll() syscall to correct semantics (POLLIN = read, POLLOUT = write).
    *
    * Note: The null event also exists. Even if poll() or select() syscall
    * returned that some events are waiting, this function might return empty
    * set of events. In this case, application should do next event waiting
    * using poll() or select().
    *
    * Note: Even if multiple poll descriptors are used (i.e. pfds > 1), this
    * function returns only a single event.
    *
    * The passed in count of poll descriptors must be equal to
    * ::snd_pcm_poll_descriptors_count() and the passed in array must match the
    * array returned by ::snd_pcm_poll_descriptors() (in its full length and
    * original order) with the revent fields updated according to the poll()
    * result. This function will not modify the file descriptor or event field
    * of any element of the given poll descriptor array.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_poll_descriptors_revents")
  def snd_pcm_poll_descriptors_revents(
      pcm: Ptr[snd_pcm_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** set nonblock mode
    * @param pcm
    *   PCM handle
    * @param nonblock
    *   0 = block, 1 = nonblock mode, 2 = abort
    * @return
    *   0 on success otherwise a negative error code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_nonblock")
  def snd_pcm_nonblock(pcm: Ptr[snd_pcm_t], nonblock: CInt): CInt = extern

  /** Add an async handler for a PCM.
    * @param handler
    *   Returned handler handle
    * @param pcm
    *   PCM handle
    * @param callback
    *   Callback function
    * @param private_data
    *   Callback private data
    * @return
    *   0 otherwise a negative error code on failure
    *
    * The asynchronous callback is called when period boundary elapses.
    */
  @name("snd_async_add_pcm_handler")
  def snd_async_add_pcm_handler(
      handler: Ptr[Ptr[snd_async_handler_t]],
      pcm: Ptr[snd_pcm_t],
      callback: snd_async_callback_t,
      private_data: CVoidPtr
  ): CInt = extern

  /** Return PCM handle related to an async handler.
    * @param handler
    *   Async handler handle
    * @return
    *   PCM handle
    */
  @name("snd_async_handler_get_pcm")
  def snd_async_handler_get_pcm(
      handler: Ptr[snd_async_handler_t]
  ): Ptr[snd_pcm_t] = extern

  /** Obtain general (static) information for PCM handle.
    * @param pcm
    *   PCM handle
    * @param info
    *   Information container
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_info")
  def snd_pcm_info(pcm: Ptr[snd_pcm_t], info: Ptr[snd_pcm_info_t]): CInt =
    extern

  /** Retreive current PCM hardware configuration chosen with snd_pcm_hw_params.
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space definition container
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_hw_params_current")
  def snd_pcm_hw_params_current(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Install one PCM hardware configuration chosen from a configuration space
    * and snd_pcm_prepare it.
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space definition container
    * @return
    *   0 on success otherwise a negative error code
    *
    * The configuration is chosen fixing single parameters in this order: first
    * access, first format, first subformat, min channels, min rate, min period
    * time, max buffer size, min tick time. If no mutually compatible set of
    * parameters can be chosen, a negative error code will be returned.
    *
    * After this call, snd_pcm_prepare() is called automatically and the stream
    * is brought to SND_PCM_STATE_PREPARED state.
    *
    * The hardware parameters cannot be changed when the stream is running
    * (active). The software parameters can be changed at any time.
    *
    * The configuration space will be updated to reflect the chosen parameters.
    */
  @name("snd_pcm_hw_params")
  def snd_pcm_hw_params(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Remove PCM hardware configuration and free associated resources.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * The function will also report success if no configuration is set.
    */
  @name("snd_pcm_hw_free")
  def snd_pcm_hw_free(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Return current software configuration for a PCM.
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @return
    *   0 on success otherwise a negative error code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_sw_params_current")
  def snd_pcm_sw_params_current(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t]
  ): CInt = extern

  /** Install PCM software configuration defined by params.
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration container
    * @return
    *   0 on success otherwise a negative error code
    *
    * The software parameters can be changed at any time. The hardware
    * parameters cannot be changed when the stream is running (active).
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_sw_params")
  def snd_pcm_sw_params(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t]
  ): CInt = extern

  /** Prepare PCM for use.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_prepare")
  def snd_pcm_prepare(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Reset PCM position.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Reduce PCM delay to 0.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_reset")
  def snd_pcm_reset(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Obtain status (runtime) information for PCM handle.
    * @param pcm
    *   PCM handle
    * @param status
    *   Status container
    * @return
    *   0 on success otherwise a negative error code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_status")
  def snd_pcm_status(pcm: Ptr[snd_pcm_t], status: Ptr[snd_pcm_status_t]): CInt =
    extern

  /** Start a PCM.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_start")
  def snd_pcm_start(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Stop a PCM dropping pending frames.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * This function stops the PCM immediately. The pending samples on the buffer
    * are ignored.
    *
    * For processing all pending samples, use ::snd_pcm_drain() instead.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_drop")
  def snd_pcm_drop(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Stop a PCM preserving pending frames.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * For playback wait for all pending frames to be played and then stop the
    * PCM. For capture stop PCM permitting to retrieve residual frames.
    *
    * For stopping the PCM stream immediately, use ::snd_pcm_drop() instead.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_drain")
  def snd_pcm_drain(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Pause/resume PCM.
    * @param pcm
    *   PCM handle
    * @param enable
    *   0 = resume, 1 = pause
    * @return
    *   0 on success otherwise a negative error code
    *
    * Note that this function works only on the hardware which supports pause
    * feature. You can check it via ::snd_pcm_hw_params_can_pause() function.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_pause")
  def snd_pcm_pause(pcm: Ptr[snd_pcm_t], enable: CInt): CInt = extern

  /** Return PCM state.
    * @param pcm
    *   PCM handle
    * @return
    *   PCM state snd_pcm_state_t of given PCM handle
    *
    * This is a faster way to obtain only the PCM state without calling
    * ::snd_pcm_status().
    *
    * Note that this function always returns one of the snd_pcm_state_t enum
    * variants. It will never return a negative error code.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_state")
  def snd_pcm_state(pcm: Ptr[snd_pcm_t]): snd_pcm_state_t = extern

  /** (DEPRECATED) Synchronize stream position with hardware
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Note this function does not update the actual r/w pointer for
    * applications. The function snd_pcm_avail_update() have to be called before
    * any mmap begin+commit operation.
    *
    * The function is thread-safe when built with the proper option.
    *
    * This function is deprecated. Use snd_pcm_avail_update() instead.
    */
  @name("snd_pcm_hwsync")
  def snd_pcm_hwsync(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Obtain delay for a running PCM handle.
    * @param pcm
    *   PCM handle
    * @param delayp
    *   Returned delay in frames
    * @return
    *   0 on success otherwise a negative error code
    *
    * For playback the delay is defined as the time that a frame that is written
    * to the PCM stream shortly after this call will take to be actually
    * audible. It is as such the overall latency from the write call to the
    * final DAC.
    *
    * For capture the delay is defined as the time that a frame that was
    * digitized by the audio device takes until it can be read from the PCM
    * stream shortly after this call returns. It is as such the overall latency
    * from the initial ADC to the read call.
    *
    * Please note that hence in case of a playback underrun this value will not
    * necessarily got down to 0.
    *
    * If the application is interested in the fill level of the playback buffer
    * of the device, it should use snd_pcm_avail*() functions. The value
    * returned by that call is not directly related to the delay, since the
    * latter might include some additional, fixed latencies the former does not.
    *
    * Note this function does not update the actual r/w pointer for
    * applications. The function snd_pcm_avail_update() have to be called before
    * any begin+commit operation.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_delay")
  def snd_pcm_delay(pcm: Ptr[snd_pcm_t], delayp: Ptr[snd_pcm_sframes_t]): CInt =
    extern

  /** Resume from suspend, no samples are lost.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * This function can be used when the stream is in the suspend state to do
    * the fine resume from this state. Not all hardware supports this feature,
    * when an -ENOSYS error is returned, use the ::snd_pcm_prepare() function to
    * recovery.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_resume")
  def snd_pcm_resume(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** Obtain last position update hi-res timestamp.
    * @param pcm
    *   PCM handle
    * @param avail
    *   Number of available frames when timestamp was grabbed
    * @param tstamp
    *   Hi-res timestamp
    * @return
    *   0 on success otherwise a negative error code
    *
    * Note this function does not update the actual r/w pointer for
    * applications.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_htimestamp")
  def snd_pcm_htimestamp(
      pcm: Ptr[snd_pcm_t],
      avail: Ptr[snd_pcm_uframes_t],
      tstamp: Ptr[snd_htimestamp_t]
  ): CInt = extern

  /** Return number of frames ready to be read (capture) / written (playback)
    * @param pcm
    *   PCM handle
    * @return
    *   a positive number of frames ready otherwise a negative error code
    *
    * On capture does all the actions needed to transport to application level
    * all the ready frames across underlying layers.
    *
    * The position is synced with hardware (driver) position in the sound ring
    * buffer in this functions.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_avail")
  def snd_pcm_avail(pcm: Ptr[snd_pcm_t]): snd_pcm_sframes_t = extern

  /** Return number of frames ready to be read (capture) / written (playback)
    * @param pcm
    *   PCM handle
    * @return
    *   a positive number of frames ready otherwise a negative error code
    *
    * On capture does all the actions needed to transport to application level
    * all the ready frames across underlying layers.
    *
    * The position is not synced with hardware (driver) position in the sound
    * ring buffer in this function. This function is a light version of
    * snd_pcm_avail() .
    *
    * Using this function is ideal after poll() or select() when audio file
    * descriptor made the event and when application expects just period timing.
    *
    * Also this function might be called after snd_pcm_delay() or
    * snd_pcm_hwsync() functions to move private ring buffer pointers in
    * alsa-lib (the internal plugin chain).
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_avail_update")
  def snd_pcm_avail_update(pcm: Ptr[snd_pcm_t]): snd_pcm_sframes_t = extern

  /** Combine snd_pcm_avail and snd_pcm_delay functions.
    * @param pcm
    *   PCM handle
    * @param availp
    *   Number of available frames in the ring buffer
    * @param delayp
    *   Total I/O latency in frames
    * @return
    *   zero on success otherwise a negative error code
    *
    * The avail and delay values retuned are in sync.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_avail_delay")
  def snd_pcm_avail_delay(
      pcm: Ptr[snd_pcm_t],
      availp: Ptr[snd_pcm_sframes_t],
      delayp: Ptr[snd_pcm_sframes_t]
  ): CInt = extern

  /** Get safe count of frames which can be rewinded.
    * @param pcm
    *   PCM handle
    * @return
    *   a positive number of frames or negative error code
    *
    * Note: The snd_pcm_rewind() can accept bigger value than returned by this
    * function. But it is not guaranteed that output stream will be consistent
    * with bigger value.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_rewindable")
  def snd_pcm_rewindable(pcm: Ptr[snd_pcm_t]): snd_pcm_sframes_t = extern

  /** Move application frame position backward.
    * @param pcm
    *   PCM handle
    * @param frames
    *   wanted displacement in frames
    * @return
    *   a positive number for actual displacement otherwise a negative error
    *   code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_rewind")
  def snd_pcm_rewind(
      pcm: Ptr[snd_pcm_t],
      frames: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Get safe count of frames which can be forwarded.
    * @param pcm
    *   PCM handle
    * @return
    *   a positive number of frames or negative error code
    *
    * Note: The snd_pcm_forward() can accept bigger value than returned by this
    * function. But it is not guaranteed that output stream will be consistent
    * with bigger value.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_forwardable")
  def snd_pcm_forwardable(pcm: Ptr[snd_pcm_t]): snd_pcm_sframes_t = extern

  /** Move application frame position forward.
    * @param pcm
    *   PCM handle
    * @param frames
    *   wanted skip in frames
    * @return
    *   a positive number for actual skip otherwise a negative error code
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_forward")
  def snd_pcm_forward(
      pcm: Ptr[snd_pcm_t],
      frames: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Write interleaved frames to a PCM.
    * @param pcm
    *   PCM handle
    * @param buffer
    *   frames containing buffer
    * @param size
    *   frames to be written
    * @return
    *   a positive number of frames actually written otherwise a negative error
    *   code
    *
    * If the blocking behaviour is selected and it is running, then routine
    * waits until all requested frames are played or put to the playback ring
    * buffer. The returned number of frames can be less only if a signal or
    * underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_writei")
  def snd_pcm_writei(
      pcm: Ptr[snd_pcm_t],
      buffer: CVoidPtr,
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Read interleaved frames from a PCM.
    * @param pcm
    *   PCM handle
    * @param buffer
    *   frames containing buffer
    * @param size
    *   frames to be read
    * @return
    *   a positive number of frames actually read otherwise a negative error
    *   code
    *
    * If the blocking behaviour was selected and it is running, then routine
    * waits until all requested frames are filled. The returned number of frames
    * can be less only if a signal or underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_readi")
  def snd_pcm_readi(
      pcm: Ptr[snd_pcm_t],
      buffer: CVoidPtr,
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Write non interleaved frames to a PCM.
    * @param pcm
    *   PCM handle
    * @param bufs
    *   frames containing buffers (one for each channel)
    * @param size
    *   frames to be written
    * @return
    *   a positive number of frames actually written otherwise a negative error
    *   code
    *
    * If the blocking behaviour is selected and it is running, then routine
    * waits until all requested frames are played or put to the playback ring
    * buffer. The returned number of frames can be less only if a signal or
    * underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_writen")
  def snd_pcm_writen(
      pcm: Ptr[snd_pcm_t],
      bufs: Ptr[CVoidPtr],
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Read non interleaved frames to a PCM.
    * @param pcm
    *   PCM handle
    * @param bufs
    *   frames containing buffers (one for each channel)
    * @param size
    *   frames to be read
    * @return
    *   a positive number of frames actually read otherwise a negative error
    *   code
    *
    * If the blocking behaviour was selected and it is running, then routine
    * waits until all requested frames are filled. The returned number of frames
    * can be less only if a signal or underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_readn")
  def snd_pcm_readn(
      pcm: Ptr[snd_pcm_t],
      bufs: Ptr[CVoidPtr],
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Wait for a PCM to become ready.
    * @param pcm
    *   PCM handle
    * @param timeout
    *   maximum time in milliseconds to wait, a -1 value means infinity
    *   (SND_PCM_WAIT_INFINITE), see also SND_PCM_WAIT_IO and SND_PCM_WAIT_DRAIN
    * @return
    *   a positive value on success otherwise a negative error code (-EPIPE for
    *   the xrun and -ESTRPIPE for the suspended status, others for general
    *   errors)
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_wait")
  def snd_pcm_wait(pcm: Ptr[snd_pcm_t], timeout: CInt): CInt = extern

  /** Link two PCMs.
    * @param pcm1
    *   first PCM handle
    * @param pcm2
    *   first PCM handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * The two PCMs will start/stop/prepare in sync.
    */
  @name("snd_pcm_link")
  def snd_pcm_link(pcm1: Ptr[snd_pcm_t], pcm2: Ptr[snd_pcm_t]): CInt = extern

  /** Remove a PCM from a linked group.
    * @param pcm
    *   PCM handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_unlink")
  def snd_pcm_unlink(pcm: Ptr[snd_pcm_t]): CInt = extern

  /** !brief Query the available channel maps
    * @param pcm
    *   PCM handle to query
    * @return
    *   the NULL-terminated array of integer pointers, each of which contains
    *   the channel map. A channel map is represented by an integer array,
    *   beginning with the channel map type, followed by the number of channels,
    *   and the position of each channel. Return NULL in case of an error.
    *
    * Note: the caller is requested to release the returned value via
    * snd_pcm_free_chmaps().
    */
  @name("snd_pcm_query_chmaps")
  def snd_pcm_query_chmaps(
      pcm: Ptr[snd_pcm_t]
  ): Ptr[Ptr[snd_pcm_chmap_query_t]] = extern

  /** !brief Query the available channel maps
    * @param card
    *   the card number
    * @param dev
    *   the PCM device number
    * @param subdev
    *   the PCM substream index
    * @param stream
    *   the direction of PCM stream
    * @return
    *   the NULL-terminated array of integer pointers, or NULL at error.
    *
    * This function works like snd_pcm_query_chmaps() but it takes the card,
    * device, substream and stream numbers instead of the already opened
    * snd_pcm_t instance, so that you can query available channel maps of a PCM
    * before actually opening it.
    *
    * As the parameters stand, the query is performed only to the hw PCM
    * devices, not the abstracted PCM object in alsa-lib.
    */
  @name("snd_pcm_query_chmaps_from_hw")
  def snd_pcm_query_chmaps_from_hw(
      card: CInt,
      dev: CInt,
      subdev: CInt,
      stream: snd_pcm_stream_t
  ): Ptr[Ptr[snd_pcm_chmap_query_t]] = extern

  /** !brief Release the channel map array allocated via snd_pcm_query_chmaps
    * @param maps
    *   the array pointer to release
    */
  @name("snd_pcm_free_chmaps")
  def snd_pcm_free_chmaps(maps: Ptr[Ptr[snd_pcm_chmap_query_t]]): Unit = extern

  /** !brief Get the current channel map
    * @param pcm
    *   PCM instance
    * @return
    *   the current channel map, or NULL if error
    *
    * Note: the caller is requested to release the returned value via free()
    */
  @name("snd_pcm_get_chmap")
  def snd_pcm_get_chmap(pcm: Ptr[snd_pcm_t]): Ptr[snd_pcm_chmap_t] = extern

  /** !brief Configure the current channel map
    * @param pcm
    *   PCM instance
    * @param map
    *   the channel map to write
    * @return
    *   zero if succeeded, or a negative error code
    */
  @name("snd_pcm_set_chmap")
  def snd_pcm_set_chmap(pcm: Ptr[snd_pcm_t], map: Ptr[snd_pcm_chmap_t]): CInt =
    extern

  /** !brief Get a name string for a channel map type as query results
    * @param val
    *   Channel position
    * @return
    *   The string corresponding to the given type, or NULL
    */
  @name("snd_pcm_chmap_type_name")
  def snd_pcm_chmap_type_name(`val`: snd_pcm_chmap_type): Ptr[CChar] = extern

  /** !brief Get a name string for a standard channel map position
    * @param val
    *   Channel position
    * @return
    *   The string corresponding to the given position, or NULL
    */
  @name("snd_pcm_chmap_name")
  def snd_pcm_chmap_name(`val`: snd_pcm_chmap_position): Ptr[CChar] = extern

  /** !brief Get a longer name string for a standard channel map position
    * @param val
    *   Channel position
    * @return
    *   The string corresponding to the given position, or NULL
    */
  @name("snd_pcm_chmap_long_name")
  def snd_pcm_chmap_long_name(`val`: snd_pcm_chmap_position): Ptr[CChar] =
    extern

  /** !brief Print the channels in chmap on the buffer
    * @param map
    *   The channel map to print
    * @param maxlen
    *   The maximal length to write (including NUL letter)
    * @param buf
    *   The buffer to write
    * @return
    *   The actual string length or a negative error code
    */
  @name("snd_pcm_chmap_print")
  def snd_pcm_chmap_print(
      map: Ptr[snd_pcm_chmap_t],
      maxlen: CSize,
      buf: Ptr[CChar]
  ): CInt = extern

  /** !brief Convert from string to channel position
    * @param str
    *   The string to parse
    * @return
    *   The channel position value or -1 as an error
    */
  @name("snd_pcm_chmap_from_string")
  def snd_pcm_chmap_from_string(str: Ptr[CChar]): CUnsignedInt = extern

  /** !brief Convert from string to channel map
    * @param str
    *   The string to parse
    * @return
    *   The channel map
    *
    * Note: the caller is requested to release the returned value via free()
    */
  @name("snd_pcm_chmap_parse_string")
  def snd_pcm_chmap_parse_string(str: Ptr[CChar]): Ptr[snd_pcm_chmap_t] = extern

  /** Recover the stream state from an error or suspend.
    * @param pcm
    *   PCM handle
    * @param err
    *   error number
    * @param silent
    *   do not print error reason
    * @return
    *   0 when error code was handled successfuly, otherwise a negative error
    *   code
    *
    * This a high-level helper function building on other functions.
    *
    * This functions handles -EINTR (interrupted system call), -EPIPE (overrun
    * or underrun) and -ESTRPIPE (stream is suspended) error codes trying to
    * prepare given stream for next I/O.
    *
    * Note that this function returs the original error code when it is not
    * handled inside this function (for example -EAGAIN is returned back).
    */
  @name("snd_pcm_recover")
  def snd_pcm_recover(pcm: Ptr[snd_pcm_t], err: CInt, silent: CInt): CInt =
    extern

  /** Set the hardware and software parameters in a simple way.
    * @param pcm
    *   PCM handle
    * @param format
    *   required PCM format
    * @param access
    *   required PCM access
    * @param channels
    *   required PCM channels
    * @param rate
    *   required sample rate in Hz
    * @param soft_resample
    *   0 = disallow alsa-lib resample stream, 1 = allow resampling
    * @param latency
    *   required overall latency in us
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_set_params")
  def snd_pcm_set_params(
      pcm: Ptr[snd_pcm_t],
      format: snd_pcm_format_t,
      access: snd_pcm_access_t,
      channels: CUnsignedInt,
      rate: CUnsignedInt,
      soft_resample: CInt,
      latency: CUnsignedInt
  ): CInt = extern

  /** Get the transfer size parameters in a simple way.
    * @param pcm
    *   PCM handle
    * @param buffer_size
    *   PCM ring buffer size in frames
    * @param period_size
    *   PCM period size in frames
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_get_params")
  def snd_pcm_get_params(
      pcm: Ptr[snd_pcm_t],
      buffer_size: Ptr[snd_pcm_uframes_t],
      period_size: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

}
