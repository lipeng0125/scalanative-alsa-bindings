package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.Iota
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_start_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_xrun_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sw_params_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_hw_params_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object DeprecatedFunctions {

  /** (DEPRECATED) get name of PCM start mode setting
    *
    * @param mode
    *   PCM start mode
    * @return
    *   ascii name of PCM start mode setting
    */
  @name("snd_pcm_start_mode_name")
  def snd_pcm_start_mode_name(mode: snd_pcm_start_t): Ptr[CChar] = extern

  /** (DEPRECATED) get name of PCM xrun mode setting
    *
    * @param mode
    *   PCM xrun mode
    * @return
    *   ascii name of PCM xrun mode setting
    */
  @name("snd_pcm_xrun_mode_name")
  def snd_pcm_xrun_mode_name(mode: snd_pcm_xrun_t): Ptr[CChar] = extern

  /** (DEPRECATED) Set start mode inside a software configuration container
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Start mode
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_set_start_mode")
  def snd_pcm_sw_params_set_start_mode(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_start_t
  ): CInt = extern

  /** (DEPRECATED) Get start mode from a software configuration container
    *
    * @param params
    *   Software configuration container
    * @return
    *   start mode
    */
  @name("snd_pcm_sw_params_get_start_mode")
  def snd_pcm_sw_params_get_start_mode(
      params: Ptr[snd_pcm_sw_params_t]
  ): snd_pcm_start_t = extern

  /** (DEPRECATED) Set xrun mode inside a software configuration container
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Xrun mode
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_set_xrun_mode")
  def snd_pcm_sw_params_set_xrun_mode(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_xrun_t
  ): CInt = extern

  /** (DEPRECATED) Get xrun mode from a software configuration container
    *
    * @param params
    *   Software configuration container
    * @return
    *   xrun mode
    */
  @name("snd_pcm_sw_params_get_xrun_mode")
  def snd_pcm_sw_params_get_xrun_mode(
      params: Ptr[snd_pcm_sw_params_t]
  ): snd_pcm_xrun_t = extern

  /** (DEPRECATED) Set xfer align inside a software configuration container
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Chunk size (frames are attempted to be transferred in chunks)
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_set_xfer_align")
  def snd_pcm_sw_params_set_xfer_align(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** (DEPRECATED) Get xfer align from a software configuration container
    *
    * @param params
    *   Software configuration container
    * @param val
    *   returned chunk size (frames are attempted to be transferred in chunks)
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_get_xfer_align")
  def snd_pcm_sw_params_get_xfer_align(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** (DEPRECATED) Set minimum number of ticks to sleep inside a software
    * configuration container
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Software configuration container
    * @param val
    *   Minimum ticks to sleep or 0 to disable the use of tick timer
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_set_sleep_min")
  def snd_pcm_sw_params_set_sleep_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_sw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** (DEPRECATED) Get minimum numbers of ticks to sleep from a software
    * configuration container
    *
    * @param params
    *   Software configuration container
    * @param val
    *   returned minimum number of ticks to sleep or 0 if tick timer is disabled
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_sw_params_get_sleep_min")
  def snd_pcm_sw_params_get_sleep_min(
      params: Ptr[snd_pcm_sw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** (DEPRECATED) Extract tick time from a configuration space
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_get_tick_time")
  def snd_pcm_hw_params_get_tick_time(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Extract minimum tick time from a configuration space
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate minimum tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_tick_time_min")
  def snd_pcm_hw_params_get_tick_time_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Extract maximum tick time from a configuration space
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate maximum tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_tick_time_max")
  def snd_pcm_hw_params_get_tick_time_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Verify if a tick time is available inside a configuration
    * space for a PCM
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_tick_time")
  def snd_pcm_hw_params_test_tick_time(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space to contain only one tick time
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_tick_time")
  def snd_pcm_hw_params_set_tick_time(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space with a minimum tick time
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum tick duration in us (on return filled with actual
    *   minimum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_tick_time_min")
  def snd_pcm_hw_params_set_tick_time_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space with a maximum tick time
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum tick duration in us (on return filled with actual
    *   maximum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact maximum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_tick_time_max")
  def snd_pcm_hw_params_set_tick_time_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space to have tick times in a given
    * range
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum tick duration in us (on return filled with actual
    *   minimum)
    * @param mindir
    *   Sub unit direction for minimum (on return filled with actual direction)
    * @param max
    *   approximate maximum tick duration in us (on return filled with actual
    *   maximum)
    * @param maxdir
    *   Sub unit direction for maximum (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact min/max is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_tick_time_minmax")
  def snd_pcm_hw_params_set_tick_time_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[CUnsignedInt],
      mindir: Ptr[CInt],
      max: Ptr[CUnsignedInt],
      maxdir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space to have tick time nearest to a
    * target
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target tick duration in us / returned chosen approximate
    *   target tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    *
    * target/chosen exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_tick_time_near")
  def snd_pcm_hw_params_set_tick_time_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space to contain only its minimum
    * tick time
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate minimum tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_tick_time_first")
  def snd_pcm_hw_params_set_tick_time_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** (DEPRECATED) Restrict a configuration space to contain only its maximum
    * tick time
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate maximum tick duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_tick_time_last")
  def snd_pcm_hw_params_set_tick_time_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

}
