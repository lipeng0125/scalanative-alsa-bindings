package gweb.alsa

import scala.scalanative.unsafe.*
import gweb.alsa.TopologyInterface.SND_TPLG_MAX_CHAN
import scala.scalanative.runtime.struct
import scala.scalanative.annotation.align
import scala.language.experimental
import scala.io.Source

@link("asound")
@extern
object TopologyInterface {

  /** Template type for all TLV objects.
    */
  type snd_tplg_tlv_template = CStruct1[
    /** TLV type SNDRV_CTL_TLVT_
      */
    CInt // type
  ]

  /** Template type for TLV Scale objects.
    */
  type snd_tplg_tlv_dbscale_template =
    CStruct4[
      /** TLV type header */
      snd_tplg_tlv_template, // hdr
      /** dB minimum value in 0.1dB */
      CInt, // min
      /** dB step size in 0.1dB */
      CInt, // step
      /** is min dB value mute ? */
      CInt // mute
    ]

  /** Template type for single channel mapping.
    */
  type snd_tplg_channel_elem = CStruct4[
    /** size in bytes of this structure */
    CInt, // size
    /** channel control register */
    CInt, // reg
    /** channel shift for control bits */
    CInt, // shift
    /** ID maps to Left, Right, LFE etc */
    CInt // id
  ]

  /** Template type for channel mapping.
    */
  type snd_tplg_channel_map_template =
    CStruct2[
      /** number of channel mappings */
      CInt, // 	num_channels
      /** mapping
        * @see
        *   SND_TPLG_MAX_CHAN
        */
      CArray[snd_tplg_channel_elem, Nat._8] // channel
    ]

  /** Template type for private data objects.
    */
  type snd_tplg_pdata_template = CStruct2[
    /** data length */
    CUnsignedInt, // length
    /** data */
    CVoidPtr // data
  ]

  /** Template type for object operations mapping.
    */
  type snd_tplg_io_ops_template = CStruct3[
    /** get callback ID */
    CInt, // get
    /** put callback ID */
    CInt, // put
    /** info callback ID */
    CInt // info
  ]

  /** Template type for control objects.
    */
  type snd_tplg_ctl_template =
    CStruct5[
      /** Control type */
      CInt, // type
      /** Control name */
      Ptr[CChar], // name
      /** Control access */
      CInt, // access
      /** operations */
      snd_tplg_io_ops_template, // ops
      /** non NULL means we have TLV data */
      Ptr[snd_tplg_tlv_template] // tlv
      /** scale TLV data */
      | Ptr[snd_tplg_tlv_dbscale_template] // tlv_scale
    ]

  opaque type snd_soc_tplg_private = CStruct0

  /** Template type for mixer control objects.
    */
  type snd_tplg_mixer_template = CStruct7[
    /** control type header */
    snd_tplg_ctl_template, // hdr
    /** channel map */
    Ptr[snd_tplg_channel_map_template], // map
    /** min value for mixer */
    CInt, // min
    /** max value for mixer */
    CInt, // max
    /** max value for platform control */
    CInt, // platform_max
    /** whether controls bits are inverted */
    CInt, // invert
    /** control private data */
    Ptr[snd_soc_tplg_private] // priv
  ]

  /** Template type for enumerated control objects.
    */
  type snd_tplg_enum_template = CStruct7[
    /** control type header */
    snd_tplg_ctl_template, // hdr
    /** channel map */
    Ptr[snd_tplg_channel_map_template], // map
    /** number of enumerated items in control */
    CInt, // items
    /** register mask size */
    CInt, // mask
    /** control text items */
    Ptr[Ptr[CChar]], // texts
    /** control value items */
    Ptr[Ptr[CInt]], // values
    /** control private data */
    Ptr[snd_soc_tplg_private] // priv
  ]

  /** Template type for TLV Scale objects.
    */
  type snd_tplg_bytes_template = CStruct7[
    /** control type header
      */
    snd_tplg_ctl_template, // hdr
    /** max byte control value
      */
    CInt, // max
    /** byte control mask
      */
    CInt, // mask
    /** base register
      */
    CInt, // base
    /** number of registers
      */
    CInt, // num_regs
    /** ops mapping
      */
    snd_tplg_io_ops_template, // ext_ops
    /** control private data
      */
    Ptr[snd_soc_tplg_private] // priv
  ]

  /** Template type for single DAPM graph element.
    */
  type snd_tplg_graph_elem = CStruct3[
    /** source widget name */
    Ptr[CChar], // src
    /** control name or NULL if no control */
    Ptr[CChar], // ctl
    /** sink widget name */
    Ptr[CChar] // sink
  ]

  /** Template type for array of DAPM graph elements.
    */
  type snd_tplg_graph_template =
    CStruct2[
      /** Number of graph elements
        */
      CInt, // count
      /** graph elements
        */
      CArray[snd_tplg_graph_elem, Nat._0] // elem
    ]

  /** Template type for DAPM widget objects.
    */
  type snd_tplg_widget_template =
    CStruct14[
      /** SND_SOC_DAPM_CTL */
      CInt, // id
      /** widget name */
      Ptr[CChar], // name
      /** stream name (certain widgets only) */
      Ptr[CChar], // sname
      /** negative reg = no direct dapm */
      CInt, // reg
      /** bits to shift */
      CInt, // shift
      /** non-shifted mask */
      CInt, // mask
      /** sort within widget type */
      CInt, // subseq
      /** invert the power bit */
      CUnsignedInt, // invert
      /** kept enabled over suspend */
      CUnsignedInt, // ignore_suspend
      /** PM event sequence flags */
      CUnsignedShort, // event_flags
      /** PM event sequence type */
      CUnsignedShort, // event_type
      /** widget private data */
      Ptr[snd_soc_tplg_private], // priv
      /** Number of controls used by widget */
      CInt, // num_ctls
      /** array of widget controls */
      CArray[Ptr[snd_tplg_ctl_template], Nat._0] // ctl
    ]

  /** Stream configurations.
    */
  type snd_tplg_stream_template =
    CStruct6[
      /** name of the stream config
        */
      Ptr[CChar], // name
      /** SNDRV_PCM_FMTBIT_*
        */
      CInt, // format
      /** SNDRV_PCM_RATE_*
        */
      CInt, // rate
      /** size of period in bytes
        */
      CInt, // period_bytes
      /** size of buffer in bytes.
        */
      CInt, // buffer_bytes
      /** number of channels
        */
      CInt // channels
    ]

  /** Stream Capabilities.
    */
  type snd_tplg_stream_caps_template = CStruct14[
    /** name of the stream caps
      */
    Ptr[CChar], // name
    /** supported formats SNDRV_PCM_FMTBIT_*
      */
    CUnsignedLong, // formats
    /** supported rates SNDRV_PCM_RATE_*
      */
    CUnsignedInt, // rates
    /** min rate
      */
    CUnsignedInt, // rate_min
    /** max rate
      */
    CUnsignedInt, // rate_max
    /** min channels
      */
    CUnsignedInt, // channels_min
    /** max channels
      */
    CUnsignedInt, // channels_max
    /** min number of periods
      */
    CUnsignedInt, // periods_min
    /** max number of periods
      */
    CUnsignedInt, // periods_max
    /** min period size bytes
      */
    CUnsignedInt, // period_size_min
    /** max period size bytes
      */
    CUnsignedInt, // period_size_max
    /** min buffer size bytes
      */
    CUnsignedInt, // buffer_size_min
    /** min buffer size bytes
      */
    CUnsignedInt, // buffer_size_max
    /** number of bits of content
      */
    CUnsignedInt // sig_bits
  ]

  /** Template type for PCM (FE DAI & DAI links).
    */
  type snd_tplg_pcm_template = CStruct13[
    /** PCM stream name */
    Ptr[CChar], // pcm_name
    /** DAI name */
    Ptr[CChar], // dai_name
    /** unique ID - used to match */
    CUnsignedInt, // pcm_id
    /** unique ID - used to match */
    CUnsignedInt, // dai_id
    /** supports playback mode */
    CUnsignedInt, // playback
    /** supports capture mode */
    CUnsignedInt, // capture
    /** 1 = compressed; 0 = PCM */
    CUnsignedInt, // compress
    /** playback & capture for DAI */
    CArray[Ptr[snd_tplg_stream_caps_template], Nat._2], // caps
    /** bitmask of flags to configure */
    CUnsignedInt, // flag_mask
    /** flag value SND_SOC_TPLG_LNK_FLGBIT_* */
    CUnsignedInt, // flags
    /** private data */
    Ptr[snd_soc_tplg_private], //	priv
    /** number of supported configs */
    CInt, // num_streams
    /** supported configs */
    CArray[snd_tplg_stream_template, Nat._0] // stream [0]
  ]

  /** Template type to describe a physical link runtime supported hardware
    * config, i.e. hardware audio formats.
    */
  type snd_tplg_hw_config_template =
    CStruct20[
      /** unique ID - - used to match
        */
      CInt, // id
      /** SND_SOC_DAI_FORMAT_ format value
        */
      CUnsignedInt, // fmt
      /** SND_SOC_TPLG_DAI_CLK_GATE_ value
        */
      CUnsignedChar, // clock_gated
      /** 1 for inverted BCLK, 0 for normal
        */
      CUnsignedChar, // invert_bclk
      /** 1 for inverted frame clock, 0 for normal
        */
      CUnsignedChar, // invert_fsync
      /** SND_SOC_TPLG_BCLK_ value
        */
      CUnsignedChar, // bclk_provider
      /** SND_SOC_TPLG_FSYNC_ value
        */
      CUnsignedChar, // fsync_provider
      /** SND_SOC_TPLG_MCLK_ value
        */
      CUnsignedChar, // mclk_direction
      /** for 32bit alignment
        */
      CUnsignedShort, // reserved
      /** MCLK or SYSCLK freqency in Hz
        */
      CUnsignedInt, // mclk_rate
      /** BCLK freqency in Hz
        */
      CUnsignedInt, // bclk_rate
      /** frame clock in Hz
        */
      CUnsignedInt, // fsync_rate
      /** number of TDM slots in use
        */
      CUnsignedInt, // tdm_slots
      /** width in bits for each slot
        */
      CUnsignedInt, // tdm_slot_width
      /** bit mask for active Tx slots
        */
      CUnsignedInt, // tx_slots
      /** bit mask for active Rx slots
        */
      CUnsignedInt, // rx_slots
      /** number of Tx channels
        */
      CUnsignedInt, // tx_channels
      /** array of slot number
        */
      Ptr[CUnsignedInt], // tx_chanmap
      /** number of Rx channels
        */
      CUnsignedInt, // rx_channels
      /** array of slot number
        */
      Ptr[CUnsignedInt] // rx_chanmap
    ]

  /** Template type for physical DAI. It can be used to configure backend DAIs
    * for DPCM.
    */
  type snd_tplg_dai_template = CStruct8[
    /** DAI name */
    Ptr[CChar], // dai_name
    /** unique ID - used to match */
    CUnsignedInt, // dai_id
    /** supports playback mode */
    CUnsignedInt, // playback
    /** supports capture mode */
    CUnsignedInt, // capture
    /** playback & capture for DAI */
    CArray[Ptr[snd_tplg_stream_caps_template], Nat._2], // caps
    /** bitmask of flags to configure */
    CUnsignedInt, // 	flag_mask
    /** SND_SOC_TPLG_DAI_FLGBIT_* */
    CUnsignedInt, // flags
    /** private data */
    Ptr[snd_soc_tplg_private] // priv
  ]

  /** Template type for physical DAI Links.
    */
  type snd_tplg_link_template = CStruct11[
    /** link name, used to match
      */
    Ptr[CChar], // name
    /** unique ID - used to match with existing physical links
      */
    CInt, // id
    /** link stream name, used to match */
    Ptr[CChar], // stream_name
    /** number of configs
      */
    CInt, // num_streams
    /** supported configs
      */
    Ptr[snd_tplg_stream_template], // stream
    /** supported HW configs
      */
    Ptr[snd_tplg_hw_config_template], // hw_config
    /** number of hw configs
      */
    CInt, // num_hw_configs
    /** default hw config ID for init
      */
    CInt, // default_hw_config_id
    /** bitmask of flags to configure
      */
    CUnsignedInt, // flag_mask
    /** SND_SOC_TPLG_LNK_FLGBIT_* flag value
      */
    CUnsignedInt, // flags
    /** private data
      */
    Ptr[snd_soc_tplg_private] // priv
  ]

  /** Generic Template Object.
    */
  type snd_tplg_obj_template_t = CStruct5[
    /** template object type
      */
    snd_tplg_type, // type
    /** group index for object
      */
    CInt, // index
    /** optional vendor specific version details
      */
    CInt, // version
    /** optional vendor specific type info
      */
    CInt, // vendor_type
    /** DAPM widget
      */
    Ptr[snd_tplg_widget_template] // widget
    /** Mixer control
      */
    | Ptr[snd_tplg_mixer_template] // mixer
    /** Bytes control
      */
    | Ptr[snd_tplg_bytes_template] // bytes_ctl
    /** Enum control
      */
    | Ptr[snd_tplg_enum_template] // enum_ctl
    /** Graph elements
      */
    | Ptr[snd_tplg_graph_template] // graph
    /** PCM elements
      */
    | Ptr[snd_tplg_pcm_template] // pcm
    /** physical DAI Links
      */
    | Ptr[snd_tplg_link_template] // link
    /** Physical DAI
      */
    | Ptr[snd_tplg_dai_template] // dai
  ]

  /** Maximum number of channels supported in one control
    */
  inline val SND_TPLG_MAX_CHAN = 8

  /** Fit for all user cases */
  inline val SND_TPLG_INDEX_ALL = 0

  /** Flags for the snd_tplg_create Verbose output */
  inline val SND_TPLG_CREATE_VERBOSE = (1 << 0)

  /** Do not sort DAPM objects by index */
  inline val SND_TPLG_CREATE_DAPM_NOSORT = (1 << 1)

  /** sort identifiers */
  inline val SND_TPLG_SAVE_SORT = (1 << 0)

  /** create the structure by group index */
  inline val SND_TPLG_SAVE_GROUPS = (1 << 1)

  /** unchecked output for debugging */
  inline val SND_TPLG_SAVE_NOCHECK = (1 << 16)

  /** Topology context
    */
  type snd_tplg_t = CStruct0

  /** Topology object types
    */
  class snd_tplg_type private (val value: CInt) extends AnyVal

  object snd_tplg_type extends Iota {

    /** TLV Data */
    val SND_TPLG_TYPE_TLV = iota(0)

    /** Mixer control */
    val SND_TPLG_TYPE_MIXER = iota

    /** Enumerated control */
    val SND_TPLG_TYPE_ENUM = iota

    /** Text data */
    val SND_TPLG_TYPE_TEXT = iota

    /** Private data */
    val SND_TPLG_TYPE_DATA = iota

    /** Byte control */
    val SND_TPLG_TYPE_BYTES = iota

    /** PCM Stream configuration */
    val SND_TPLG_TYPE_STREAM_CONFIG = iota

    /** PCM Stream capabilities */
    val SND_TPLG_TYPE_STREAM_CAPS = iota

    /** PCM stream device */
    val SND_TPLG_TYPE_PCM = iota

    /** DAPM widget */
    val SND_TPLG_TYPE_DAPM_WIDGET = iota

    /** DAPM graph elements */
    val SND_TPLG_TYPE_DAPM_GRAPH = iota

    /** BE DAI link */
    val SND_TPLG_TYPE_BE = iota

    /** Hostless codec <-> codec link */
    val SND_TPLG_TYPE_CC = iota

    /** Topology manifest */
    val SND_TPLG_TYPE_MANIFEST = iota

    /** Vendor tokens */
    val SND_TPLG_TYPE_TOKEN = iota

    /** Vendor tuples */
    val SND_TPLG_TYPE_TUPLE = iota

    /** Physical DAI link */
    val SND_TPLG_TYPE_LINK = iota

    /** Link HW config */
    val SND_TPLG_TYPE_HW_CONFIG = iota

    /** Physical DAI */
    val SND_TPLG_TYPE_DAI = iota

  }

  /** Return the version of the topology library.
    *
    * @return
    *   A static string with the version number.
    */
  @name("snd_tplg_version")
  def snd_tplg_version(): Ptr[CChar] = extern

  /** Create a new topology parser instance.
    *
    * @return
    *   New topology parser instance
    */
  @name("snd_tplg_new")
  def snd_tplg_new(): Ptr[snd_tplg_t] = extern

  /** Create a new topology parser instance.
    *
    * @return
    *   New topology parser instance
    */
  @name("snd_tplg_create")
  def snd_tplg_create(flags: CInt): Ptr[snd_tplg_t] = extern

  /** Free a topology parser instance.
    *
    * @param tplg
    *   Topology parser instance
    */
  @name("snd_tplg_free")
  def snd_tplg_free(tplg: Ptr[snd_tplg_t]): Unit = extern

  /** Load topology from the text buffer.
    *
    * @param tplg
    *   Topology instance.
    * @param buf
    *   Text buffer.
    * @param size
    *   Text buffer size in bytes.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_load")
  def snd_tplg_load(tplg: Ptr[snd_tplg_t], buf: Ptr[CChar], size: CSize): CInt =
    extern

  /** Parse and build topology text file into binary file.
    *
    * @param tplg
    *   Topology instance.
    * @param infile
    *   Topology text input file to be parsed
    * @param outfile
    *   Binary topology output file.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_build_file")
  def snd_tplg_build_file(
      tplg: Ptr[snd_tplg_t],
      infile: Ptr[CChar],
      outfile: Ptr[CChar]
  ): CInt = extern

  /** Enable verbose reporting of binary file output.
    *
    * @param tplg
    *   Topology Instance
    * @param verbose
    *   Enable verbose output level if non zero
    */
  @name("snd_tplg_verbose")
  def snd_tplg_verbose(tplg: Ptr[snd_tplg_t], verbose: CInt): Unit = extern

  /** Register topology template object.
    *
    * @param tplg
    *   Topology instance.
    * @param t
    *   Template object.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_add_object")
  def snd_tplg_add_object(
      tplg: Ptr[snd_tplg_t],
      t: Ptr[snd_tplg_obj_template_t]
  ): CInt = extern

  /** Build all registered topology data into binary file.
    *
    * @param tplg
    *   Topology instance.
    * @param outfile
    *   Binary topology output file.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_build")
  def snd_tplg_build(tplg: Ptr[snd_tplg_t], outfile: Ptr[CChar]): CInt = extern

  /** Build all registered topology data into memory.
    *
    * @param tplg
    *   Topology instance.
    * @param bin
    *   Binary topology output buffer (malloc).
    * @param size
    *   Binary topology output buffer size in bytes.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_build_bin")
  def snd_tplg_build_bin(
      tplg: Ptr[snd_tplg_t],
      bin: Ptr[CVoidPtr],
      size: Ptr[CSSize]
  ): CInt = extern

  /** Attach private data to topology manifest.
    *
    * @param tplg
    *   Topology instance.
    * @param data
    *   Private data.
    * @param len
    *   Length of data in bytes.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_set_manifest_data")
  def snd_tplg_set_manifest_data(
      tplg: Ptr[snd_tplg_t],
      data: CVoidPtr,
      len: CInt
  ): CInt = extern

  /** Set an optional vendor specific version number.
    *
    * @param tplg
    *   Topology instance.
    * @param version
    *   Vendor specific version number.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_set_version")
  def snd_tplg_set_version(tplg: Ptr[snd_tplg_t], version: CUnsignedInt): CInt =
    extern

  /** Save the topology to the text configuration string.
    *
    * @param tplg
    *   Topology instance.
    * @param dst
    *   A pointer to string with result (malloc).
    * @param flags
    *   save mode
    * @return
    *   Zero on success, otherwise a negative error code
    *
    * Valid flags are
    */
  @name("snd_tplg_save")
  def snd_tplg_save(
      tplg: Ptr[snd_tplg_t],
      dst: Ptr[Ptr[CChar]],
      flags: CInt
  ): CInt = extern

  /** Decode the binary topology contents.
    *
    * @param tplg
    *   Topology instance.
    * @param bin
    *   Binary topology input buffer.
    * @param size
    *   Binary topology input buffer size.
    * @param dflags
    *   - not used, must be set to 0.
    * @return
    *   Zero on success, otherwise a negative error code
    */
  @name("snd_tplg_decode")
  def snd_tplg_decode(
      tplg: Ptr[snd_tplg_t],
      bin: CVoidPtr,
      size: CSize,
      dflags: CInt
  ): CInt = extern

}
