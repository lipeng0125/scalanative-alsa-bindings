package gweb.alsa
import scala.scalanative.unsafe.*

import scala.scalanative.posix.fcntl

val SND_HWDEP_OPEN_READ = (fcntl.O_RDONLY)

val SND_HWDEP_OPEN_WRITE = (fcntl.O_WRONLY)

val SND_HWDEP_OPEN_DUPLEX = (fcntl.O_RDWR)

val SND_HWDEP_OPEN_NONBLOCK = (fcntl.O_NONBLOCK)

/** The Hardware Dependant Interface.
  */
@link("asound")
@extern
object HardwareDependantInterface {
  inline val SND_HWDEP_DLSYM_VERSION = "_dlsym_hwdep_001"

  /** HwDep information container
    */
  type snd_hwdep_info_t = CStruct0

  /** HwDep DSP status container
    */
  type snd_hwdep_dsp_status_t = CStruct0

  /** HwDep DSP image container
    */
  type snd_hwdep_dsp_image_t = CStruct0

  /** HwDep handle
    */
  type snd_hwdep_t = CStruct0

  /** HwDep interface
    */
  class snd_hwdep_iface_t private (val value: CInt) extends AnyVal

  object snd_hwdep_iface_t extends Iota {

    /** OPL2 raw driver */
    val SND_HWDEP_IFACE_OPL2 = iota(0)

    /** OPL3 raw driver */
    val SND_HWDEP_IFACE_OPL3 = iota

    /** OPL4 raw driver */
    val SND_HWDEP_IFACE_OPL4 = iota

    /** SB16CSP driver */
    val SND_HWDEP_IFACE_SB16CSP = iota

    /** EMU10K1 driver */
    val SND_HWDEP_IFACE_EMU10K1 = iota

    /** YSS225 driver */
    val SND_HWDEP_IFACE_YSS225 = iota

    /** ICS2115 driver */
    val SND_HWDEP_IFACE_ICS2115 = iota

    /** Ensoniq SoundScape ISA card (MC68EC000) */
    val SND_HWDEP_IFACE_SSCAPE = iota

    /** Digigram VX cards */
    val SND_HWDEP_IFACE_VX = iota

    /** Digigram miXart cards */
    val SND_HWDEP_IFACE_MIXART = iota

    /** Tascam US122, US224 & US428 usb */
    val SND_HWDEP_IFACE_USX2Y = iota

    /** EmuX wavetable */
    val SND_HWDEP_IFACE_EMUX_WAVETABLE = iota

    /** Bluetooth audio */
    val SND_HWDEP_IFACE_BLUETOOTH = iota

    /** Tascam US122, US224 & US428 raw USB PCM */
    val SND_HWDEP_IFACE_USX2Y_PCM = iota

    /** Digigram PCXHR */
    val SND_HWDEP_IFACE_PCXHR = iota

    /** SB Extigy/Audigy2NX remote control */
    val SND_HWDEP_IFACE_SB_RC = iota

    /** HD-audio */
    val SND_HWDEP_IFACE_HDA = iota

    /** direct access to usb stream */
    val SND_HWDEP_IFACE_USB_STREAM = iota

    /** TC DICE FireWire device */
    val SND_HWDEP_IFACE_FW_DICE = iota

    /** Echo Audio Fireworks based device */
    val SND_HWDEP_IFACE_FW_FIREWORKS = iota

    /** BridgeCo BeBoB based device */
    val SND_HWDEP_IFACE_FW_BEBOB = iota

    /** Oxford OXFW970/971 based device */
    val SND_HWDEP_IFACE_FW_OXFW = iota

    /** */
    val SND_HWDEP_IFACE_FW_DIGI00X = iota

    /** */
    val SND_HWDEP_IFACE_FW_TASCAM = iota

    /** */
    val SND_HWDEP_IFACE_LINE6 = iota

    /** */
    val SND_HWDEP_IFACE_FW_MOTU = iota

    /** */
    val SND_HWDEP_IFACE_FW_FIREFACE = iota

    /** last known hwdep interface */
    val SND_HWDEP_IFACE_LAST = iota(SND_HWDEP_IFACE_FW_FIREFACE)

  }

  /** HwDep handle type
    */
  class snd_hwdep_type_t private (val value: CInt) extends AnyVal

  object snd_hwdep_type_t extends Iota {

    /** Kernel level HwDep */
    val SND_HWDEP_TYPE_HW = iota

    /** Shared memory client HwDep (not yet implemented) */
    val SND_HWDEP_TYPE_SHM = iota

    /** INET client HwDep (not yet implemented) */
    val SND_HWDEP_TYPE_INET = iota

  }

  /** Opens a new connection to the HwDep interface.
    *
    * @param hwdep
    *   Returned handle (NULL if not wanted)
    * @param name
    *   ASCII identifier of the HwDep handle
    * @param mode
    *   Open mode
    * @return
    *   0 on success otherwise a negative error code
    *
    * Opens a new connection to the HwDep interface specified with an ASCII
    * identifier and mode.
    */
  @name("snd_hwdep_open")
  def snd_hwdep_open(
      hwdep: Ptr[Ptr[snd_hwdep_t]],
      name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** close HwDep handle
    *
    * @param hwdep
    *   HwDep handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified HwDep handle and frees all associated resources.
    */
  @name("snd_hwdep_close")
  def snd_hwdep_close(hwdep: Ptr[snd_hwdep_t]): CInt = extern

  /** get poll descriptors
    *
    * @param hwdep
    *   HwDep handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    */
  @name("snd_hwdep_poll_descriptors")
  def snd_hwdep_poll_descriptors(
      hwdep: Ptr[snd_hwdep_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get count of poll descriptors for HwDep handle
    *
    * @param hwdep
    *   HwDep handle
    * @return
    *   count of poll descriptors
    */
  @name("snd_hwdep_poll_descriptors_count")
  def snd_hwdep_poll_descriptors_count(hwdep: Ptr[snd_hwdep_t]): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param hwdep
    *   HwDep handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_hwdep_poll_descriptors_revents")
  def snd_hwdep_poll_descriptors_revents(
      hwdep: Ptr[snd_hwdep_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** set nonblock mode
    *
    * @param hwdep
    *   HwDep handle
    * @param nonblock
    *   0 = block, 1 = nonblock mode
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hwdep_nonblock")
  def snd_hwdep_nonblock(hwdep: Ptr[snd_hwdep_t], nonblock: CInt): CInt = extern

  /** get information about HwDep handle
    *
    * @param hwdep
    *   HwDep handle
    * @param info
    *   pointer to a snd_hwdep_info_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hwdep_info")
  def snd_hwdep_info(
      hwdep: Ptr[snd_hwdep_t],
      info: Ptr[snd_hwdep_info_t]
  ): CInt = extern

  /** get the DSP status information
    *
    * @param hwdep
    *   HwDep handle
    * @param info
    *   pointer to a snd_hwdep_dsp_status_t structure to be filled
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hwdep_dsp_status")
  def snd_hwdep_dsp_status(
      hwdep: Ptr[snd_hwdep_t],
      status: Ptr[snd_hwdep_dsp_status_t]
  ): CInt = extern

  /** load the DSP block
    *
    * @param hwdep
    *   HwDep handle
    * @param block
    *   pointer to a snd_hwdep_dsp_image_t structure to transfer
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hwdep_dsp_load")
  def snd_hwdep_dsp_load(
      hwdep: Ptr[snd_hwdep_t],
      block: Ptr[snd_hwdep_dsp_image_t]
  ): CInt = extern

  /** do hardware dependent ioctl
    *
    * @param hwdep
    *   HwDep handle
    * @param request
    *   ioctl command
    * @param arg
    *   ioctl argument
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hwdep_ioctl")
  def snd_hwdep_ioctl(
      hwdep: Ptr[snd_hwdep_t],
      request: CUnsignedInt,
      arg: CVoidPtr
  ): CInt = extern

  /** write bytes using HwDep handle
    *
    * @param hwdep
    *   HwDep handle
    * @param buffer
    *   buffer containing bytes to write
    * @param size
    *   output buffer size in bytes
    */
  @name("snd_hwdep_write")
  def snd_hwdep_write(
      hwdep: Ptr[snd_hwdep_t],
      buffer: CVoidPtr,
      size: CSize
  ): CSSize = extern

  /** read bytes using HwDep handle
    *
    * @param hwdep
    *   HwDep handle
    * @param buffer
    *   buffer to store the input bytes
    * @param size
    *   input buffer size in bytes
    */
  @name("snd_hwdep_read")
  def snd_hwdep_read(
      hwdep: Ptr[snd_hwdep_t],
      buffer: CVoidPtr,
      size: CSize
  ): CSSize = extern

  /** get size of the snd_hwdep_info_t structure in bytes
    *
    * @return
    *   size of the snd_hwdep_info_t structure in bytes
    */
  @name("snd_hwdep_info_sizeof")
  def snd_hwdep_info_sizeof(): CSize = extern

  /** allocate a new snd_hwdep_info_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_hwdep_info_t structure using the standard malloc C
    * library function.
    */
  @name("snd_hwdep_info_malloc")
  def snd_hwdep_info_malloc(ptr: Ptr[Ptr[snd_hwdep_info_t]]): CInt = extern

  /** frees the snd_hwdep_info_t structure
    *
    * @param info
    *   pointer to the snd_hwdep_info_t structure to free
    *
    * Frees the given snd_hwdep_info_t structure using the standard free C
    * library function.
    */
  @name("snd_hwdep_info_free")
  def snd_hwdep_info_free(obj: Ptr[snd_hwdep_info_t]): Unit = extern

  /** copy one snd_hwdep_info_t structure to another
    *
    * @param dst
    *   destination snd_hwdep_info_t structure
    * @param src
    *   source snd_hwdep_info_t structure
    */
  @name("snd_hwdep_info_copy")
  def snd_hwdep_info_copy(
      dst: Ptr[snd_hwdep_info_t],
      src: Ptr[snd_hwdep_info_t]
  ): Unit = extern

  /** get hwdep device number
    *
    * @param info
    *   pointer to a snd_hwdep_info_t structure
    * @return
    *   hwdep device number
    */
  @name("snd_hwdep_info_get_device")
  def snd_hwdep_info_get_device(obj: Ptr[snd_hwdep_info_t]): CUnsignedInt =
    extern

  /** get hwdep card number
    *
    * @param obj
    *   pointer to a snd_hwdep_info_t structure
    * @return
    *   hwdep card number
    */
  @name("snd_hwdep_info_get_card")
  def snd_hwdep_info_get_card(obj: Ptr[snd_hwdep_info_t]): CInt = extern

  /** get hwdep driver identifier
    *
    * @param obj
    *   pointer to a snd_hwdep_info_t structure
    * @return
    *   hwdep driver identifier
    */
  @name("snd_hwdep_info_get_id")
  def snd_hwdep_info_get_id(obj: Ptr[snd_hwdep_info_t]): Ptr[CChar] = extern

  /** get hwdep driver name
    *
    * @param obj
    *   pointer to a snd_hwdep_info_t structure
    * @return
    *   hwdep driver name
    */
  @name("snd_hwdep_info_get_name")
  def snd_hwdep_info_get_name(obj: Ptr[snd_hwdep_info_t]): Ptr[CChar] = extern

  /** get hwdep protocol interface
    *
    * @param obj
    *   pointer to a snd_hwdep_info_t structure
    * @return
    *   hwdep protocol interface
    */
  @name("snd_hwdep_info_get_iface")
  def snd_hwdep_info_get_iface(obj: Ptr[snd_hwdep_info_t]): snd_hwdep_iface_t =
    extern

  /** set hwdep device number
    *
    * @param obj
    *   pointer to a snd_hwdep_info_t structure
    * @param val
    *   hwdep device
    */
  @name("snd_hwdep_info_set_device")
  def snd_hwdep_info_set_device(
      obj: Ptr[snd_hwdep_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** get size of the snd_hwdep_dsp_status_t structure in bytes
    *
    * @return
    *   size of the snd_hwdep_dsp_status_t structure in bytes
    */
  @name("snd_hwdep_dsp_status_sizeof")
  def snd_hwdep_dsp_status_sizeof(): CSize = extern

  /** allocate a new snd_hwdep_dsp_status_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_hwdep_dsp_status_t structure using the standard malloc
    * C library function.
    */
  @name("snd_hwdep_dsp_status_malloc")
  def snd_hwdep_dsp_status_malloc(ptr: Ptr[Ptr[snd_hwdep_dsp_status_t]]): CInt =
    extern

  /** frees the snd_hwdep_dsp_status_t structure
    *
    * @param info
    *   pointer to the snd_hwdep_dsp_status_t structure to free
    *
    * Frees the given snd_hwdep_dsp_status_t structure using the standard free C
    * library function.
    */
  @name("snd_hwdep_dsp_status_free")
  def snd_hwdep_dsp_status_free(obj: Ptr[snd_hwdep_dsp_status_t]): Unit = extern

  /** copy one snd_hwdep_dsp_status_t structure to another
    *
    * @param dst
    *   destination snd_hwdep_dsp_status_t structure
    * @param src
    *   source snd_hwdep_dsp_status_t structure
    */
  @name("snd_hwdep_dsp_status_copy")
  def snd_hwdep_dsp_status_copy(
      dst: Ptr[snd_hwdep_dsp_status_t],
      src: Ptr[snd_hwdep_dsp_status_t]
  ): Unit = extern

  /** get the driver version of dsp loader
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_status_t structure
    * @return
    *   the driver version
    */
  @name("snd_hwdep_dsp_status_get_version")
  def snd_hwdep_dsp_status_get_version(
      obj: Ptr[snd_hwdep_dsp_status_t]
  ): CUnsignedInt = extern

  /** get the driver id of dsp loader
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_status_t structure
    * @return
    *   the driver id string
    */
  @name("snd_hwdep_dsp_status_get_id")
  def snd_hwdep_dsp_status_get_id(
      obj: Ptr[snd_hwdep_dsp_status_t]
  ): Ptr[CChar] = extern

  /** get number of dsp blocks
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_status_t structure
    * @return
    *   number of dsp blocks
    */
  @name("snd_hwdep_dsp_status_get_num_dsps")
  def snd_hwdep_dsp_status_get_num_dsps(
      obj: Ptr[snd_hwdep_dsp_status_t]
  ): CUnsignedInt = extern

  /** get the bit flags of the loaded dsp blocks
    *
    * @param info
    *   pointer to a snd_hwdep_dsp_status_t structure
    * @return
    *   the big flags of the loaded dsp blocks
    */
  @name("snd_hwdep_dsp_status_get_dsp_loaded")
  def snd_hwdep_dsp_status_get_dsp_loaded(
      obj: Ptr[snd_hwdep_dsp_status_t]
  ): CUnsignedInt = extern

  /** get the chip status of dsp loader
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_status_t structure
    * @return
    *   non-zero if all DSP blocks are loaded and the chip is ready
    */
  @name("snd_hwdep_dsp_status_get_chip_ready")
  def snd_hwdep_dsp_status_get_chip_ready(
      obj: Ptr[snd_hwdep_dsp_status_t]
  ): CUnsignedInt = extern

  /** get size of the snd_hwdep_dsp_image_t structure in bytes
    *
    * @return
    *   size of the snd_hwdep_dsp_image_t structure in bytes
    */
  @name("snd_hwdep_dsp_image_sizeof")
  def snd_hwdep_dsp_image_sizeof(): CSize = extern

  /** allocate a new snd_hwdep_dsp_image_t structure
    *
    * @param info
    *   returned pointer
    * @return
    *   0 on success otherwise a negative error code if fails
    *
    * Allocates a new snd_hwdep_dsp_image_t structure using the standard malloc
    * C library function.
    */
  @name("snd_hwdep_dsp_image_malloc")
  def snd_hwdep_dsp_image_malloc(ptr: Ptr[Ptr[snd_hwdep_dsp_image_t]]): CInt =
    extern

  /** frees the snd_hwdep_dsp_image_t structure
    *
    * @param info
    *   pointer to the snd_hwdep_dsp_image_t structure to free
    *
    * Frees the given snd_hwdep_dsp_image_t structure using the standard free C
    * library function.
    */
  @name("snd_hwdep_dsp_image_free")
  def snd_hwdep_dsp_image_free(obj: Ptr[snd_hwdep_dsp_image_t]): Unit = extern

  /** copy one snd_hwdep_dsp_image_t structure to another
    *
    * @param dst
    *   destination snd_hwdep_dsp_image_t structure
    * @param src
    *   source snd_hwdep_dsp_image_t structure
    */
  @name("snd_hwdep_dsp_image_copy")
  def snd_hwdep_dsp_image_copy(
      dst: Ptr[snd_hwdep_dsp_image_t],
      src: Ptr[snd_hwdep_dsp_image_t]
  ): Unit = extern

  /** get the DSP block index
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @return
    *   the index of the DSP block
    */
  @name("snd_hwdep_dsp_image_get_index")
  def snd_hwdep_dsp_image_get_index(
      obj: Ptr[snd_hwdep_dsp_image_t]
  ): CUnsignedInt = extern

  /** get the name of the DSP block
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @return
    *   the name string of the DSP block
    */
  @name("snd_hwdep_dsp_image_get_name")
  def snd_hwdep_dsp_image_get_name(
      obj: Ptr[snd_hwdep_dsp_image_t]
  ): Ptr[CChar] = extern

  /** get the image pointer of the DSP block
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @return
    *   the image pointer of the DSP block
    */
  @name("snd_hwdep_dsp_image_get_image")
  def snd_hwdep_dsp_image_get_image(obj: Ptr[snd_hwdep_dsp_image_t]): CVoidPtr =
    extern

  /** get the length of the DSP block
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @return
    *   the length of the DSP block in bytes
    */
  @name("snd_hwdep_dsp_image_get_length")
  def snd_hwdep_dsp_image_get_length(obj: Ptr[snd_hwdep_dsp_image_t]): CSize =
    extern

  /** set the DSP block index
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @param index
    *   the index value to set
    */
  @name("snd_hwdep_dsp_image_set_index")
  def snd_hwdep_dsp_image_set_index(
      obj: Ptr[snd_hwdep_dsp_image_t],
      _index: CUnsignedInt
  ): Unit = extern

  /** set the name of the DSP block
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @param name
    *   the name string
    */
  @name("snd_hwdep_dsp_image_set_name")
  def snd_hwdep_dsp_image_set_name(
      obj: Ptr[snd_hwdep_dsp_image_t],
      name: Ptr[CChar]
  ): Unit = extern

  /** set the DSP block image pointer
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @param image
    *   the DSP image pointer
    */
  @name("snd_hwdep_dsp_image_set_image")
  def snd_hwdep_dsp_image_set_image(
      obj: Ptr[snd_hwdep_dsp_image_t],
      buffer: CVoidPtr
  ): Unit = extern

  /** set the DSP block length
    *
    * @param obj
    *   pointer to a snd_hwdep_dsp_image_t structure
    * @param length
    *   the length of the DSP block
    */
  @name("snd_hwdep_dsp_image_set_length")
  def snd_hwdep_dsp_image_set_length(
      obj: Ptr[snd_hwdep_dsp_image_t],
      length: CSize
  ): Unit = extern

}
