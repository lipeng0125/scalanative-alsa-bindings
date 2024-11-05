package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sw_params_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_tstamp_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_tstamp_type_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object SoftwareParameters {

  /** get size of snd_pcm_sw_params_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_pcm_sw_params_sizeof")
  def snd_pcm_sw_params_sizeof(): CSize = extern

  /** allocate an invalid snd_pcm_sw_params_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_pcm_sw_params_malloc")
  def snd_pcm_sw_params_malloc(ptr: Ptr[Ptr[snd_pcm_sw_params_t]]): CInt =
    extern

  /** frees a previously allocated snd_pcm_sw_params_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_pcm_sw_params_free")
  def snd_pcm_sw_params_free(obj: Ptr[snd_pcm_sw_params_t]): Unit = extern

  /** copy one snd_pcm_sw_params_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_pcm_sw_params_copy")
  def snd_pcm_sw_params_copy(
      dst: Ptr[snd_pcm_sw_params_t],
      src: Ptr[snd_pcm_sw_params_t]
  ): Unit = extern

  /** Get boundary for ring pointers from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned boundary in frames
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_get_boundary")
  def snd_pcm_sw_params_get_boundary(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Set timestamp mode inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Timestamp mode
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_set_tstamp_mode")
  def snd_pcm_sw_params_set_tstamp_mode(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_tstamp_t
  ): CInt = extern

  /** Get timestamp mode from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned timestamp
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_get_tstamp_mode")
  def snd_pcm_sw_params_get_tstamp_mode(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_tstamp_t]
  ): CInt = extern

  /** Set timestamp type inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Timestamp type
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_set_tstamp_type")
  def snd_pcm_sw_params_set_tstamp_type(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_tstamp_type_t
  ): CInt = extern

  /** Get timestamp type from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned timestamp type
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_get_tstamp_type")
  def snd_pcm_sw_params_get_tstamp_type(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_tstamp_type_t]
  ): CInt = extern

  /** Set avail min inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Minimum avail frames to consider PCM ready
    * @return
    *   0 otherwise a negative error code
    *
    * Note: This is similar to setting an OSS wakeup point. The valid values for
    * 'val' are determined by the specific hardware. Most PC sound cards can
    * only accept power of 2 frame counts (i.e. 512, 1024, 2048). You cannot use
    * this as a high resolution timer - it is limited to how often the sound
    * card hardware raises an interrupt.
    */
  @name("snd_pcm_sw_params_set_avail_min")
  def snd_pcm_sw_params_set_avail_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Get avail min from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   returned minimum available frames to consider PCM ready
    * @return
    *   0 otherwise a negative error code
    *
    * This is a threshold value when the PCM stream is considered as ready for
    * another read/write operation or poll event.
    */
  @name("snd_pcm_sw_params_get_avail_min")
  def snd_pcm_sw_params_get_avail_min(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Set period event inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   0 = disable period event, 1 = enable period event
    * @return
    *   0 otherwise a negative error code
    *
    * An poll (select) wakeup event is raised if enabled.
    */
  @name("snd_pcm_sw_params_set_period_event")
  def snd_pcm_sw_params_set_period_event(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: CInt
  ): CInt = extern

  /** Get period event from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   returned period event state
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_get_period_event")
  def snd_pcm_sw_params_get_period_event(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[CInt]
  ): CInt = extern

  /** Set start threshold inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Start threshold in frames
    * @return
    *   0 otherwise a negative error code
    *
    * PCM is automatically started when playback frames available to PCM are >=
    * threshold or when requested capture frames are >= threshold
    */
  @name("snd_pcm_sw_params_set_start_threshold")
  def snd_pcm_sw_params_set_start_threshold(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Get start threshold from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned start threshold in frames
    * @return
    *   0 otherwise a negative error code
    *
    * PCM is automatically started when playback frames available to PCM are >=
    * threshold or when requested capture frames are >= threshold
    */
  @name("snd_pcm_sw_params_get_start_threshold")
  def snd_pcm_sw_params_get_start_threshold(
      paramsm: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Set stop threshold inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Stop threshold in frames
    * @return
    *   0 otherwise a negative error code
    *
    * PCM is automatically stopped in SND_PCM_STATE_XRUN state when available
    * frames is >= threshold. If the stop threshold is equal to boundary (also
    * software parameter - sw_param) then automatic stop will be disabled (thus
    * device will do the endless loop in the ring buffer).
    */
  @name("snd_pcm_sw_params_set_stop_threshold")
  def snd_pcm_sw_params_set_stop_threshold(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Get stop threshold from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned stop threshold in frames
    * @return
    *   0 otherwise a negative error code
    *
    * PCM is automatically stopped in SND_PCM_STATE_XRUN state when available
    * frames is >= threshold. If the stop threshold is equal to boundary (also
    * software parameter - sw_param) then automatic stop will be disabled (thus
    * device will do the endless loop in the ring buffer).
    */
  @name("snd_pcm_sw_params_get_stop_threshold")
  def snd_pcm_sw_params_get_stop_threshold(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Set silence threshold inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Silence threshold in frames
    * @return
    *   0 otherwise a negative error code
    *
    * A portion of playback buffer is overwritten with silence (see
    * snd_pcm_sw_params_set_silence_size) when playback underrun is nearer than
    * silence threshold.
    */
  @name("snd_pcm_sw_params_set_silence_threshold")
  def snd_pcm_sw_params_set_silence_threshold(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Get silence threshold from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned silence threshold in frames
    * @return
    *   0 otherwise a negative error value
    *
    * A portion of playback buffer is overwritten with silence (see
    * snd_pcm_sw_params_set_silence_size) when playback underrun is nearer than
    * silence threshold.
    */
  @name("snd_pcm_sw_params_get_silence_threshold")
  def snd_pcm_sw_params_get_silence_threshold(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Set silence size inside a software configuration container.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Silence size in frames (0 for disabled)
    * @return
    *   0 otherwise a negative error code
    *
    * A portion of playback buffer is overwritten with silence when playback
    * underrun is nearer than silence threshold (see
    * snd_pcm_sw_params_set_silence_threshold)
    *
    * When drain silence (see snd_pcm_hw_params_get_drain_silence) is disabled,
    * this will also apply for draining, i.e. silence is written also when the
    * drain end is nearer than the silence threshold.
    *
    * The special case is when silence size value is equal or greater than
    * boundary. The unused portion of the ring buffer (initial written samples
    * are untouched) is filled with silence at start. Later, only just processed
    * sample area is filled with silence. Note: silence_threshold must be set to
    * zero.
    */
  @name("snd_pcm_sw_params_set_silence_size")
  def snd_pcm_sw_params_set_silence_size(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Get silence size from a software configuration container.
    *
    * @param params
    *   Software configuration container
    * @param val
    *   Returned silence size in frames (0 for disabled)
    * @return
    *   0 otherwise a negative error code
    *
    * A portion of playback buffer is overwritten with silence when playback
    * underrun is nearer than silence threshold (see
    * snd_pcm_sw_params_set_silence_threshold)
    */
  @name("snd_pcm_sw_params_get_silence_size")
  def snd_pcm_sw_params_get_silence_size(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

}
