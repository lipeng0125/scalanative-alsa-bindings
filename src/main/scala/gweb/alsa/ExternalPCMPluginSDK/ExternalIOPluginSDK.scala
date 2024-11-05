package gweb.alsa.ExternalPCMPluginSDK

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_stream_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_state_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_format_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sframes_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_channel_area_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_hw_params_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sw_params_t
import gweb.alsa.pollfd
import gweb.alsa.OutputInterface.snd_output_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_chmap_query_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_chmap_t
import gweb.alsa.Iota

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object ExternalIOPluginSDK {

  /** Handle of ioplug
    *
    * I/O plugin handle
    */
  type snd_pcm_ioplug = CStruct20[
    /** protocol version; SND_PCM_IOPLUG_VERSION must be filled here before
      * calling snd_pcm_ioplug_create()
      */
    CUnsignedInt, // version
    /** name of this plugin; must be filled before calling
      * snd_pcm_ioplug_create()
      */
    Ptr[CChar], // name
    /** SND_PCM_IOPLUG_FLAG_XXX */
    CUnsignedInt, // flags
    /** poll file descriptor */
    CInt, // poll_fd
    /** poll events */
    CUnsignedInt, // poll_events
    /** pseudo mmap mode */
    CUnsignedInt, // mmap_rw
    /** callbacks of this plugin; must be filled before calling
      * snd_pcm_ioplug_create()
      *
      * // todo 解决循环引用
      */
    CVoidPtr, // callback
    /** private data, which can be used freely in the driver callbacks */
    CVoidPtr, // private_data
    /** PCM handle filled by snd_pcm_ioplug_create() */
    Ptr[snd_pcm_t], // pcm
    /** stream direcion; read-only */
    snd_pcm_stream_t, // stream
    /** current PCM state; read-only */
    snd_pcm_state_t, // state
    /** application pointer; read-only */
    // todo 实现volatile
    snd_pcm_uframes_t, // appl_ptr
    /** hw pointer; read-only */
    // todo 实现volatile
    snd_pcm_uframes_t, // hw_ptr
    /** non-block mode; read-only */
    CInt, // nonblock
    /** access type; filled after hw_params is called */
    snd_pcm_access_t, // access
    /** PCM format; filled after hw_params is called */
    snd_pcm_format_t, // format
    /** number of channels; filled after hw_params is called */
    CUnsignedInt, // channels
    /** rate; filled after hw_params is called */
    CUnsignedInt, // rate
    /** period size; filled after hw_params is called */
    snd_pcm_uframes_t, // period_size
    /** buffer size; filled after hw_params is called */
    snd_pcm_uframes_t // buffer_size
  ]

  type snd_pcm_ioplug_t = snd_pcm_ioplug

  /** Callback table of ioplug
    */
  type snd_pcm_ioplug_callback = CStruct20[
    /** start the PCM; required, called inside mutex lock
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // start
    /** stop the PCM; required, called inside mutex lock
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], //	stop
    /** get the current DMA position; required, called inside mutex lock
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      /** @return
        *   buffer position up to buffer_size or when
        *   SND_PCM_IOPLUG_FLAG_BOUNDARY_WA flag is set up to boundary or a
        *   negative error code for Xrun
        */
      snd_pcm_sframes_t
    ], // pointer
    /** transfer the data; optional, called inside mutex lock
      */
    CFuncPtr4[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_pcm_channel_area_t], // areas
      snd_pcm_uframes_t, // offset
      CSize, // size
      snd_pcm_sframes_t
    ], // transfer
    /** close the PCM; optional
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // close
    /** hw_params; optional
      */
    CFuncPtr2[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_pcm_hw_params_t], // params
      CInt
    ], // hw_params
    /** hw_free; optional
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // hw_free
    /** sw_params; optional
      */
    CFuncPtr2[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_pcm_sw_params_t], // params
      CInt
    ], // sw_params
    /** prepare; optional
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // prepare
    /** drain; optional
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // drain
    /** toggle pause; optional, called inside mutex lock
      */
    CFuncPtr2[
      Ptr[snd_pcm_ioplug_t], // io
      CInt, // enable
      CInt
    ], // pause
    /** resume; optional
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // resume
    /** poll descriptors count; optional
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      CInt
    ], // poll_descriptors_count
    /** poll descriptors; optional
      */
    CFuncPtr3[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[pollfd], // pfd
      CUnsignedInt, // space
      CInt
    ], // poll_descriptors
    /** mangle poll events; optional
      */
    CFuncPtr4[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[pollfd], // pfd
      CUnsignedInt, // nfds
      Ptr[CUnsignedShort], // revents
      CInt
    ], // poll_revents
    /** dump; optional
      */
    CFuncPtr2[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_output_t], // out
      Unit
    ], // dump
    /** get the delay for the running PCM; optional; since v1.0.1
      */
    CFuncPtr2[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_pcm_sframes_t], // delayp
      CInt
    ], // delay
    /** query the channel maps; optional; since v1.0.2
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[Ptr[snd_pcm_chmap_query_t]]
    ], // query_chmaps
    /** get the channel map; optional; since v1.0.2
      */
    CFuncPtr1[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_pcm_chmap_t]
    ], // get_chmap
    /** set the channel map; optional; since v1.0.2
      */
    CFuncPtr2[
      Ptr[snd_pcm_ioplug_t], // io
      Ptr[snd_pcm_chmap_t], // map
      CInt
    ] // set_chmap
  ]

  type snd_pcm_ioplug_callback_t = snd_pcm_ioplug_callback

  /** list up this PCM */
  inline val SND_PCM_IOPLUG_FLAG_LISTED = (1 << 0)

  /** monotonic timestamps */
  inline val SND_PCM_IOPLUG_FLAG_MONOTONIC = (1 << 1)

  /** hw pointer wrap around at boundary instead of buffer_size */
  inline val SND_PCM_IOPLUG_FLAG_BOUNDARY_WA = (1 << 2)

  /** Protocol major version */
  inline val SND_PCM_IOPLUG_VERSION_MAJOR = 1

  /** Protocol minor version */
  inline val SND_PCM_IOPLUG_VERSION_MINOR = 0

  /** Protocol tiny version */
  inline val SND_PCM_IOPLUG_VERSION_TINY = 2

  /** IO-plugin protocol version */
  inline val SND_PCM_IOPLUG_VERSION = ((SND_PCM_IOPLUG_VERSION_MAJOR << 16) |
    (SND_PCM_IOPLUG_VERSION_MINOR << 8) |
    (SND_PCM_IOPLUG_VERSION_TINY))

  /** hw constraints for ioplug
    */
  object $ extends Iota {

    /** access type */
    val SND_PCM_IOPLUG_HW_ACCESS = iota(0)

    /** format */
    val SND_PCM_IOPLUG_HW_FORMAT = iota

    /** channels */
    val SND_PCM_IOPLUG_HW_CHANNELS = iota

    /** rate */
    val SND_PCM_IOPLUG_HW_RATE = iota

    /** period bytes */
    val SND_PCM_IOPLUG_HW_PERIOD_BYTES = iota

    /** buffer bytes */
    val SND_PCM_IOPLUG_HW_BUFFER_BYTES = iota

    /** number of periods */
    val SND_PCM_IOPLUG_HW_PERIODS = iota

    /** max number of hw constraints */
    val SND_PCM_IOPLUG_HW_PARAMS = iota
  }

  /** Create an ioplug instance.
    *
    * @param ioplug
    *   the ioplug handle
    * @param name
    *   name of PCM
    * @param stream
    *   stream direction
    * @param mode
    *   PCM open mode
    * @return
    *   0 if successful, or a negative error code
    *
    * Creates the ioplug instance.
    *
    * The callback is the mandatory field of ioplug handle. At least, start,
    * stop and pointer callbacks must be set before calling this function.
    */
  @name("snd_pcm_ioplug_create")
  def snd_pcm_ioplug_create(
      io: Ptr[snd_pcm_ioplug_t],
      name: Ptr[CChar],
      stream: snd_pcm_stream_t,
      mode: CInt
  ): CInt = extern

  /** Delete the ioplug instance.
    *
    * @param ioplug
    *   the ioplug handle
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_pcm_ioplug_delete")
  def snd_pcm_ioplug_delete(io: Ptr[snd_pcm_ioplug_t]): CInt = extern

  /** Reinitialize the poll and mmap status.
    *
    * @param ioplug
    *   the ioplug handle
    * @return
    *   0 if successful, or a negative error code
    *
    * Reinitializes the poll and the mmap status of the PCM. Call this function
    * to propagate the status change in the ioplug instance to its PCM
    * internals.
    */
  @name("snd_pcm_ioplug_reinit_status")
  def snd_pcm_ioplug_reinit_status(ioplug: Ptr[snd_pcm_ioplug_t]): CInt = extern

  /** Get mmap area of ioplug.
    *
    * @param ioplug
    *   the ioplug handle
    * @return
    *   the mmap channel areas if available, or NULL
    *
    * Returns the mmap channel areas if available. When mmap_rw field is not
    * set, this function always returns NULL.
    */
  @name("snd_pcm_ioplug_mmap_areas")
  def snd_pcm_ioplug_mmap_areas(
      ioplug: Ptr[snd_pcm_ioplug_t]
  ): Ptr[snd_pcm_channel_area_t] = extern

  /** Reset ioplug parameters.
    *
    * @param ioplug
    *   the ioplug handle
    *
    * Resets the all parameters for the given ioplug handle.
    */
  @name("snd_pcm_ioplug_params_reset")
  def snd_pcm_ioplug_params_reset(io: Ptr[snd_pcm_ioplug_t]): Unit = extern

  /** Set parameter as the min/max values.
    *
    * @param ioplug
    *   the ioplug handle
    * @param type
    *   parameter type
    * @param min
    *   the minimum value
    * @param max
    *   the maximum value
    * @return
    *   0 if successful, or a negative error code
    *
    * Sets the parameter as the min/max values. The available values of the
    * given parameter type is restricted between the given minimum and maximum
    * values.
    */
  @name("snd_pcm_ioplug_set_param_minmax")
  def snd_pcm_ioplug_set_param_minmax(
      io: Ptr[snd_pcm_ioplug_t],
      `type`: CInt,
      min: CUnsignedInt,
      max: CUnsignedInt
  ): CInt = extern

  /** Set parameter as the list.
    *
    * @param ioplug
    *   the ioplug handle
    * @param type
    *   parameter type
    * @param num_list
    *   number of available values
    * @param list
    *   the list of available values
    * @return
    *   0 if successful, or a negative error code
    *
    * Sets the parameter as the list. The available values of the given
    * parameter type is restricted to the ones of the given list.
    */
  @name("snd_pcm_ioplug_set_param_list")
  def snd_pcm_ioplug_set_param_list(
      io: Ptr[snd_pcm_ioplug_t],
      `type`: CInt,
      num_list: CUnsignedInt,
      list: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Change the ioplug PCM status.
    *
    * @param ioplug
    *   the ioplug handle
    * @param state
    *   the PCM status
    * @return
    *   zero if successful or a negative error code
    *
    * Changes the PCM status of the ioplug to the given value. This function can
    * be used for external plugins to notify the status change, e.g. XRUN.
    */
  @name("snd_pcm_ioplug_set_state")
  def snd_pcm_ioplug_set_state(
      ioplug: Ptr[snd_pcm_ioplug_t],
      state: snd_pcm_state_t
  ): CInt = extern

  /** Get the available frames. This function can be used to calculate the the
    * available frames before calling snd_pcm_avail_update()
    *
    * @param ioplug
    *   the ioplug handle
    * @param hw_ptr
    *   hardware pointer in frames
    * @param appl_ptr
    *   application pointer in frames
    * @return
    *   available frames for the application
    */
  @name("snd_pcm_ioplug_avail")
  def snd_pcm_ioplug_avail(
      ioplug: Ptr[snd_pcm_ioplug_t],
      hw_ptr: snd_pcm_uframes_t,
      appl_ptr: snd_pcm_uframes_t
  ): snd_pcm_uframes_t = extern

  /** Get the available frames. This function can be used to calculate the the
    * available frames before calling snd_pcm_avail_update()
    *
    * @param ioplug
    *   the ioplug handle
    * @param hw_ptr
    *   hardware pointer in frames
    * @param appl_ptr
    *   application pointer in frames
    * @return
    *   available frames for the hardware
    */
  @name("snd_pcm_ioplug_hw_avail")
  def snd_pcm_ioplug_hw_avail(
      ioplug: Ptr[snd_pcm_ioplug_t],
      hw_ptr: snd_pcm_uframes_t,
      appl_ptr: snd_pcm_uframes_t
  ): snd_pcm_uframes_t = extern

}
