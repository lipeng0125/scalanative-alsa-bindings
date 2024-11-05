package gweb.alsa.PCMInterface
import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_hw_params_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_mask_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_format_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_format_mask_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_subformat_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_subformat_mask_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object HardwareParameters {

  /** Fill params with a full configuration space for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    *
    * The configuration space will be filled with all possible ranges for the
    * PCM device.
    *
    * Note that the configuration space may be constrained by the currently
    * installed configuration on the PCM device. To remove any constrains, free
    * the configuration with snd_pcm_hw_free first.
    */
  @name("snd_pcm_hw_params_any")
  def snd_pcm_hw_params_any(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if hardware supports sample-resolution mmap for given configuration.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_can_mmap_sample_resolution")
  def snd_pcm_hw_params_can_mmap_sample_resolution(
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if hardware does double buffering for start/stop for given
    * configuration.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_is_double")
  def snd_pcm_hw_params_is_double(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware does double buffering for data transfers for given
    * configuration.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_is_batch")
  def snd_pcm_hw_params_is_batch(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware does block transfers for samples for given
    * configuration.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_is_block_transfer")
  def snd_pcm_hw_params_is_block_transfer(
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if timestamps are monotonic for given configuration.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_is_monotonic")
  def snd_pcm_hw_params_is_monotonic(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware supports overrange detection.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_can_overrange")
  def snd_pcm_hw_params_can_overrange(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware supports pause.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_can_pause")
  def snd_pcm_hw_params_can_pause(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware supports resume.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_can_resume")
  def snd_pcm_hw_params_can_resume(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware does half-duplex only.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_is_half_duplex")
  def snd_pcm_hw_params_is_half_duplex(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware does joint-duplex (playback and capture are somewhat
    * correlated)
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_is_joint_duplex")
  def snd_pcm_hw_params_is_joint_duplex(
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if hardware supports synchronized start with sample resolution.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_can_sync_start")
  def snd_pcm_hw_params_can_sync_start(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Check if hardware can disable period wakeups.
    *
    * @param params
    *   Configuration space
    */
  @name("snd_pcm_hw_params_can_disable_period_wakeup")
  def snd_pcm_hw_params_can_disable_period_wakeup(
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if hardware is capable of perfect drain.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    *
    * Perfect drain means that the hardware does not use samples beyond the
    * stream application pointer.
    */
  @name("snd_pcm_hw_params_is_perfect_drain")
  def snd_pcm_hw_params_is_perfect_drain(
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if hardware supports audio wallclock timestamps.
    *
    * @param params
    *   Configuration space
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_supports_audio_wallclock_ts")
  def snd_pcm_hw_params_supports_audio_wallclock_ts(
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Check if hardware supports type of audio timestamps.
    *
    * @param params
    *   Configuration space
    * @param type
    *   Audio timestamp type
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_supports_audio_ts_type")
  def snd_pcm_hw_params_supports_audio_ts_type(
      params: Ptr[snd_pcm_hw_params_t],
      `type`: CInt
  ): CInt = extern

  /** Get rate exact info from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param rate_num
    *   Pointer to returned rate numerator
    * @param rate_den
    *   Pointer to returned rate denominator
    * @return
    *   0 otherwise a negative error code if the info is not available
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_get_rate_numden")
  def snd_pcm_hw_params_get_rate_numden(
      params: Ptr[snd_pcm_hw_params_t],
      rate_num: Ptr[CUnsignedInt],
      rate_den: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Get sample resolution info from a configuration space.
    *
    * @param params
    *   Configuration space
    * @return
    *   sample resolution (in bits) otherwise a negative error code if the info
    *   is not available
    *
    * For linear formats, this function returns sample resolution - used bits
    * starting from the first usable significant bit defined by the format (e.g.
    * bit 31 for S32_LE format or bit 23 for S24_LE format - starting from bit
    * zero). Application may use full sample bit range defined by the format,
    * but additional bits (outside this sample resolution) are stripped (not
    * processed).
    *
    * For non-linear formats, this value may have a special meaning which may be
    * defined in future.
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_get_sbits")
  def snd_pcm_hw_params_get_sbits(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** Get hardware FIFO size info from a configuration space.
    *
    * @param params
    *   Configuration space
    * @return
    *   FIFO size in frames otherwise a negative error code if the info is not
    *   available
    *
    * This function should only be called when the configuration space contains
    * a single configuration. Call snd_pcm_hw_params to choose a single
    * configuration from the configuration space.
    */
  @name("snd_pcm_hw_params_get_fifo_size")
  def snd_pcm_hw_params_get_fifo_size(params: Ptr[snd_pcm_hw_params_t]): CInt =
    extern

  /** get size of snd_pcm_hw_params_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_pcm_hw_params_sizeof")
  def snd_pcm_hw_params_sizeof(): CSize = extern

  /** allocate an invalid snd_pcm_hw_params_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_pcm_hw_params_malloc")
  def snd_pcm_hw_params_malloc(ptr: Ptr[Ptr[snd_pcm_hw_params_t]]): CInt =
    extern

  /** frees a previously allocated snd_pcm_hw_params_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_pcm_hw_params_free")
  def snd_pcm_hw_params_free(obj: Ptr[snd_pcm_hw_params_t]): Unit = extern

  /** copy one snd_pcm_hw_params_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_pcm_hw_params_copy")
  def snd_pcm_hw_params_copy(
      dst: Ptr[snd_pcm_hw_params_t],
      src: Ptr[snd_pcm_hw_params_t]
  ): Unit = extern

  /** Extract access type from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param access
    *   Returned value
    * @return
    *   access type otherwise a negative error code if the configuration space
    *   does not contain a single value
    */
  @name("snd_pcm_hw_params_get_access")
  def snd_pcm_hw_params_get_access(
      params: Ptr[snd_pcm_hw_params_t],
      _access: Ptr[snd_pcm_access_t]
  ): CInt = extern

  /** Verify if an access type is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param access
    *   access type
    * @return
    *   0 if available a negative error code otherwise
    */
  @name("snd_pcm_hw_params_test_access")
  def snd_pcm_hw_params_test_access(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      _access: snd_pcm_access_t
  ): CInt = extern

  /** Restrict a configuration space to contain only one access type.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param access
    *   access type
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_access")
  def snd_pcm_hw_params_set_access(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      _access: snd_pcm_access_t
  ): CInt = extern

  /** Restrict a configuration space to contain only its first access type.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param access
    *   Returned first access type
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_access_first")
  def snd_pcm_hw_params_set_access_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      _access: Ptr[snd_pcm_access_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only its last access type.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param access
    *   Returned last access type
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_access_last")
  def snd_pcm_hw_params_set_access_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      _access: Ptr[snd_pcm_access_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only a set of access types.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param mask
    *   Access mask
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_access_mask")
  def snd_pcm_hw_params_set_access_mask(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      mask: Ptr[snd_pcm_access_mask_t]
  ): CInt = extern

  /** Get access mask from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param mask
    *   Returned Access mask
    */
  @name("snd_pcm_hw_params_get_access_mask")
  def snd_pcm_hw_params_get_access_mask(
      params: Ptr[snd_pcm_hw_params_t],
      mask: Ptr[snd_pcm_access_mask_t]
  ): CInt = extern

  /** Extract format from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param format
    *   returned format
    * @return
    *   format otherwise a negative error code if the configuration space does
    *   not contain a single value
    */
  @name("snd_pcm_hw_params_get_format")
  def snd_pcm_hw_params_get_format(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_format_t]
  ): CInt = extern

  /** Verify if a format is available inside a configuration space for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param format
    *   format
    * @return
    *   0 if available a negative error code otherwise
    */
  @name("snd_pcm_hw_params_test_format")
  def snd_pcm_hw_params_test_format(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: snd_pcm_format_t
  ): CInt = extern

  /** Restrict a configuration space to contain only one format.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param format
    *   format
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_format")
  def snd_pcm_hw_params_set_format(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: snd_pcm_format_t
  ): CInt = extern

  /** Restrict a configuration space to contain only its first format.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param format
    *   Returned first format
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_format_first")
  def snd_pcm_hw_params_set_format_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      format: Ptr[snd_pcm_format_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only its last format.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param format
    *   Returned last format
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_format_last")
  def snd_pcm_hw_params_set_format_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      format: Ptr[snd_pcm_format_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only a set of formats.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param mask
    *   Format mask
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_format_mask")
  def snd_pcm_hw_params_set_format_mask(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      mask: Ptr[snd_pcm_format_mask_t]
  ): CInt = extern

  /** Get format mask from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param mask
    *   Returned Format mask
    */
  @name("snd_pcm_hw_params_get_format_mask")
  def snd_pcm_hw_params_get_format_mask(
      params: Ptr[snd_pcm_hw_params_t],
      mask: Ptr[snd_pcm_format_mask_t]
  ): Unit = extern

  /** Extract subformat from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param subformat
    *   Returned subformat value
    * @return
    *   subformat otherwise a negative error code if the configuration space
    *   does not contain a single value
    */
  @name("snd_pcm_hw_params_get_subformat")
  def snd_pcm_hw_params_get_subformat(
      params: Ptr[snd_pcm_hw_params_t],
      subformat: Ptr[snd_pcm_subformat_t]
  ): CInt = extern

  /** Verify if a subformat is available inside a configuration space for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param subformat
    *   subformat value
    * @return
    *   0 if available a negative error code otherwise
    */
  @name("snd_pcm_hw_params_test_subformat")
  def snd_pcm_hw_params_test_subformat(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      subformat: snd_pcm_subformat_t
  ): CInt = extern

  /** Restrict a configuration space to contain only one subformat.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param subformat
    *   subformat value
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_subformat")
  def snd_pcm_hw_params_set_subformat(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      subformat: snd_pcm_subformat_t
  ): CInt = extern

  /** Restrict a configuration space to contain only its first subformat.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param subformat
    *   Returned subformat
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_subformat_first")
  def snd_pcm_hw_params_set_subformat_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      subformat: Ptr[snd_pcm_subformat_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only its last subformat.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param subformat
    *   Returned subformat
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_subformat_last")
  def snd_pcm_hw_params_set_subformat_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      subformat: Ptr[snd_pcm_subformat_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only a set of subformats.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param mask
    *   Subformat mask
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_subformat_mask")
  def snd_pcm_hw_params_set_subformat_mask(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      mask: Ptr[snd_pcm_subformat_mask_t]
  ): CInt = extern

  /** Get subformat mask from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param mask
    *   Returned Subformat mask
    */
  @name("snd_pcm_hw_params_get_subformat_mask")
  def snd_pcm_hw_params_get_subformat_mask(
      params: Ptr[snd_pcm_hw_params_t],
      mask: Ptr[snd_pcm_subformat_mask_t]
  ): Unit = extern

  /** Extract channels from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned channels count
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    */
  @name("snd_pcm_hw_params_get_channels")
  def snd_pcm_hw_params_get_channels(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Extract minimum channels count from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   minimum channels count
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_get_channels_min")
  def snd_pcm_hw_params_get_channels_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Extract maximum channels count from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   maximum channels count
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_get_channels_max")
  def snd_pcm_hw_params_get_channels_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Verify if a channels count is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   channels count
    * @return
    *   0 if available a negative error code otherwise
    */
  @name("snd_pcm_hw_params_test_channels")
  def snd_pcm_hw_params_test_channels(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** Restrict a configuration space to contain only one channels count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   channels count
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_channels")
  def snd_pcm_hw_params_set_channels(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** Restrict a configuration space with a minimum channels count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   minimum channels count (on return filled with actual minimum)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_channels_min")
  def snd_pcm_hw_params_set_channels_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space with a maximum channels count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   maximum channels count (on return filled with actual maximum)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_channels_max")
  def snd_pcm_hw_params_set_channels_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to have channels counts in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   minimum channels count (on return filled with actual minimum)
    * @param max
    *   maximum channels count (on return filled with actual maximum)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_channels_minmax")
  def snd_pcm_hw_params_set_channels_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[CUnsignedInt],
      max: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to have channels count nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   target channels count, returned chosen channels count
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    */
  @name("snd_pcm_hw_params_set_channels_near")
  def snd_pcm_hw_params_set_channels_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum channels count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   minimum channels count
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_channels_first")
  def snd_pcm_hw_params_set_channels_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum channels count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   maximum channels count
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_channels_last")
  def snd_pcm_hw_params_set_channels_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Extract rate from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_get_rate")
  def snd_pcm_hw_params_get_rate(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract minimum rate from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate minimum rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_rate_min")
  def snd_pcm_hw_params_get_rate_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract maximum rate from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate maximum rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_rate_max")
  def snd_pcm_hw_params_get_rate_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Verify if a rate is available inside a configuration space for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_rate")
  def snd_pcm_hw_params_test_rate(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space to contain only one rate.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_rate")
  def snd_pcm_hw_params_set_rate(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space with a minimum rate.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum rate (on return filled with actual minimum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_rate_min")
  def snd_pcm_hw_params_set_rate_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space with a maximum rate.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum rate (on return filled with actual maximum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact maximum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_rate_max")
  def snd_pcm_hw_params_set_rate_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have rates in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum rate (on return filled with actual minimum)
    * @param mindir
    *   Sub unit direction for minimum (on return filled with actual direction)
    * @param max
    *   approximate maximum rate (on return filled with actual maximum)
    * @param maxdir
    *   Sub unit direction for maximum (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact min/max is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_rate_minmax")
  def snd_pcm_hw_params_set_rate_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[CUnsignedInt],
      mindir: Ptr[CInt],
      max: Ptr[CUnsignedInt],
      maxdir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have rate nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target rate / returned approximate set rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    *
    * target/chosen exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_rate_near")
  def snd_pcm_hw_params_set_rate_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum rate.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned minimum approximate rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_rate_first")
  def snd_pcm_hw_params_set_rate_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum rate.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned maximum approximate rate
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_rate_last")
  def snd_pcm_hw_params_set_rate_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only real hardware rates.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disable, 1 = enable (default) rate resampling
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_rate_resample")
  def snd_pcm_hw_params_set_rate_resample(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** Extract resample state from a configuration space.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disable, 1 = enable rate resampling
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_get_rate_resample")
  def snd_pcm_hw_params_get_rate_resample(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to allow the buffer to be accessible from
    * outside.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disable, 1 = enable (default) exporting buffer
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_export_buffer")
  def snd_pcm_hw_params_set_export_buffer(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** Extract buffer accessibility from a configuration space.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disable, 1 = enable exporting buffer
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_get_export_buffer")
  def snd_pcm_hw_params_get_export_buffer(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to settings without period wakeups.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disable, 1 = enable (default) period wakeup
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function must be called only on devices where non-blocking mode is
    * enabled.
    *
    * To check whether the hardware does support disabling period wakeups, call
    * snd_pcm_hw_params_can_disable_period_wakeup(). If the hardware does not
    * support this mode, standard period wakeups will be generated.
    *
    * Even with disabled period wakeups, the period size/time/count parameters
    * are valid; it is suggested to use
    * snd_pcm_hw_params_set_period_size_last().
    *
    * When period wakeups are disabled, the application must not use any
    * functions that could block on this device. The use of poll should be
    * limited to error cases. The application needs to use an external event or
    * a timer to check the state of the ring buffer and refill it apropriately.
    */
  @name("snd_pcm_hw_params_set_period_wakeup")
  def snd_pcm_hw_params_set_period_wakeup(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** Extract period wakeup flag from a configuration space.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disabled, 1 = enabled period wakeups
    * @return
    *   Zero on success, otherwise a negative error code.
    */
  @name("snd_pcm_hw_params_get_period_wakeup")
  def snd_pcm_hw_params_get_period_wakeup(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Restrict a configuration space to fill the end of playback stream with
    * silence when drain() is invoked.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disabled, 1 = enabled (default) fill the end of the playback stream
    *   with silence when drain() is invoked
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * When disabled, the application should handle the end of stream gracefully
    * (fill the silent samples to align to the period size plus some extra
    * samples for hardware / driver without perfect drain). Note that the rewind
    * may be used for this purpose or the sw_params silencing mechanism.
    */
  @name("snd_pcm_hw_params_set_drain_silence")
  def snd_pcm_hw_params_set_drain_silence(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt
  ): CInt = extern

  /** Extract drain with the filling of silence samples from a configuration
    * space.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   0 = disabled, 1 = enabled
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_get_drain_silence")
  def snd_pcm_hw_params_get_drain_silence(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Extract period time from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate period duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_get_period_time")
  def snd_pcm_hw_params_get_period_time(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract minimum period time from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum period duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_period_time_min")
  def snd_pcm_hw_params_get_period_time_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract maximum period time from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum period duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_period_time_max")
  def snd_pcm_hw_params_get_period_time_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Verify if a period time is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate period duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_period_time")
  def snd_pcm_hw_params_test_period_time(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space to contain only one period time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate period duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_time")
  def snd_pcm_hw_params_set_period_time(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space with a minimum period time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum period duration in us (on return filled with actual
    *   minimum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_time_min")
  def snd_pcm_hw_params_set_period_time_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space with a maximum period time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum period duration in us (on return filled with actual
    *   maximum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact maximum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_time_max")
  def snd_pcm_hw_params_set_period_time_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have period times in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum period duration in us (on return filled with actual
    *   minimum)
    * @param mindir
    *   Sub unit direction for minimum (on return filled with actual direction)
    * @param max
    *   approximate maximum period duration in us (on return filled with actual
    *   maximum)
    * @param maxdir
    *   Sub unit direction for maximum (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact min/max is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_time_minmax")
  def snd_pcm_hw_params_set_period_time_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[CUnsignedInt],
      mindir: Ptr[CInt],
      max: Ptr[CUnsignedInt],
      maxdir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have period time nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target period duration in us / returned chosen approximate
    *   target period duration
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    *
    * target/chosen exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_time_near")
  def snd_pcm_hw_params_set_period_time_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum period time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate period duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_period_time_first")
  def snd_pcm_hw_params_set_period_time_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum period time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned maximum approximate period time
    * @param dir
    *   Sub unit direction
    * @return
    *   approximate period duration in us
    */
  @name("snd_pcm_hw_params_set_period_time_last")
  def snd_pcm_hw_params_set_period_time_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract period size from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_get_period_size")
  def snd_pcm_hw_params_get_period_size(
      params: Ptr[snd_pcm_hw_params_t],
      frames: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract minimum period size from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_period_size_min")
  def snd_pcm_hw_params_get_period_size_min(
      params: Ptr[snd_pcm_hw_params_t],
      frames: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract maximum period size from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_period_size_max")
  def snd_pcm_hw_params_get_period_size_max(
      params: Ptr[snd_pcm_hw_params_t],
      frames: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Verify if a period size is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_period_size")
  def snd_pcm_hw_params_test_period_size(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: snd_pcm_uframes_t,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space to contain only one period size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_size")
  def snd_pcm_hw_params_set_period_size(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: snd_pcm_uframes_t,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space with a minimum period size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum period size in frames (on return filled with actual
    *   minimum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_size_min")
  def snd_pcm_hw_params_set_period_size_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space with a maximum period size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum period size in frames (on return filled with actual
    *   maximum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_size_max")
  def snd_pcm_hw_params_set_period_size_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have period sizes in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum period size in frames (on return filled with actual
    *   minimum)
    * @param mindir
    *   Sub unit direction for minimum (on return filled with actual direction)
    * @param max
    *   approximate maximum period size in frames (on return filled with actual
    *   maximum)
    * @param maxdir
    *   Sub unit direction for maximum (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact min/max is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_size_minmax")
  def snd_pcm_hw_params_set_period_size_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[snd_pcm_uframes_t],
      mindir: Ptr[CInt],
      max: Ptr[snd_pcm_uframes_t],
      maxdir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have period size nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target period size in frames / returned chosen approximate
    *   target period size
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    *
    * target/chosen exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_period_size_near")
  def snd_pcm_hw_params_set_period_size_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum period size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned maximum approximate period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_period_size_first")
  def snd_pcm_hw_params_set_period_size_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum period size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned maximum approximate period size in frames
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_period_size_last")
  def snd_pcm_hw_params_set_period_size_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only integer period sizes.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_period_size_integer")
  def snd_pcm_hw_params_set_period_size_integer(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Extract periods from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_get_periods")
  def snd_pcm_hw_params_get_periods(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract minimum periods count from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_periods_min")
  def snd_pcm_hw_params_get_periods_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract maximum periods count from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_periods_max")
  def snd_pcm_hw_params_get_periods_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Verify if a periods count is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_periods")
  def snd_pcm_hw_params_test_periods(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space to contain only one periods count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_periods")
  def snd_pcm_hw_params_set_periods(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space with a minimum periods count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum periods per buffer (on return filled with actual
    *   minimum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_periods_min")
  def snd_pcm_hw_params_set_periods_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space with a maximum periods count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum periods per buffer (on return filled with actual
    *   maximum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact maximum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_periods_max")
  def snd_pcm_hw_params_set_periods_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have periods counts in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum periods per buffer (on return filled with actual
    *   minimum)
    * @param mindir
    *   Sub unit direction for minimum (on return filled with actual direction)
    * @param max
    *   approximate maximum periods per buffer (on return filled with actual
    *   maximum)
    * @param maxdir
    *   Sub unit direction for maximum (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact min/max is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_periods_minmax")
  def snd_pcm_hw_params_set_periods_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[CUnsignedInt],
      mindir: Ptr[CInt],
      max: Ptr[CUnsignedInt],
      maxdir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have periods count nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target periods per buffer / returned chosen approximate
    *   target periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    *
    * target/chosen exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_periods_near")
  def snd_pcm_hw_params_set_periods_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum periods count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate minimum periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_periods_first")
  def snd_pcm_hw_params_set_periods_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum periods count.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate maximum periods per buffer
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_periods_last")
  def snd_pcm_hw_params_set_periods_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only integer periods counts.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_periods_integer")
  def snd_pcm_hw_params_set_periods_integer(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t]
  ): CInt = extern

  /** Extract buffer time from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned buffer time in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_get_buffer_time")
  def snd_pcm_hw_params_get_buffer_time(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract minimum buffer time from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum buffer duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_buffer_time_min")
  def snd_pcm_hw_params_get_buffer_time_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract maximum buffer time from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum buffer duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_buffer_time_max")
  def snd_pcm_hw_params_get_buffer_time_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Verify if a buffer time is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate buffer duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_buffer_time")
  def snd_pcm_hw_params_test_buffer_time(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space to contain only one buffer time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate buffer duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_buffer_time")
  def snd_pcm_hw_params_set_buffer_time(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: CUnsignedInt,
      dir: CInt
  ): CInt = extern

  /** Restrict a configuration space with a minimum buffer time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum buffer duration in us (on return filled with actual
    *   minimum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact minimum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_buffer_time_min")
  def snd_pcm_hw_params_set_buffer_time_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space with a maximum buffer time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum buffer duration in us (on return filled with actual
    *   maximum)
    * @param dir
    *   Sub unit direction (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact maximum is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_buffer_time_max")
  def snd_pcm_hw_params_set_buffer_time_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have buffer times in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum buffer duration in us (on return filled with actual
    *   minimum)
    * @param mindir
    *   Sub unit direction for minimum (on return filled with actual direction)
    * @param max
    *   approximate maximum buffer duration in us (on return filled with actual
    *   maximum)
    * @param maxdir
    *   Sub unit direction for maximum (on return filled with actual direction)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted/actual exact min/max is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_buffer_time_minmax")
  def snd_pcm_hw_params_set_buffer_time_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[CUnsignedInt],
      mindir: Ptr[CInt],
      max: Ptr[CUnsignedInt],
      maxdir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to have buffer time nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target buffer duration in us / returned chosen approximate
    *   target buffer duration
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    *
    * target/chosen exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_buffer_time_near")
  def snd_pcm_hw_params_set_buffer_time_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum buffer time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate minimum buffer duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_buffer_time_first")
  def snd_pcm_hw_params_set_buffer_time_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum buffered time.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate maximum buffer duration in us
    * @param dir
    *   Sub unit direction
    * @return
    *   0 otherwise a negative error code
    *
    * Actual exact value is <,=,> the approximate one following dir (-1, 0, 1)
    */
  @name("snd_pcm_hw_params_set_buffer_time_last")
  def snd_pcm_hw_params_set_buffer_time_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[CUnsignedInt],
      dir: Ptr[CInt]
  ): CInt = extern

  /** Extract buffer size from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned buffer size in frames
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    */
  @name("snd_pcm_hw_params_get_buffer_size")
  def snd_pcm_hw_params_get_buffer_size(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Extract minimum buffer size from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate minimum buffer size in frames
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_get_buffer_size_min")
  def snd_pcm_hw_params_get_buffer_size_min(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Extract maximum buffer size from a configuration space.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned approximate maximum buffer size in frames
    * @return
    *   0 otherwise a negative error code
    *
    * Exact value is <,=,> the returned one following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_get_buffer_size_max")
  def snd_pcm_hw_params_get_buffer_size_max(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Verify if a buffer size is available inside a configuration space for a
    * PCM.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   buffer size in frames
    * @return
    *   0 if available a negative error code otherwise
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_test_buffer_size")
  def snd_pcm_hw_params_test_buffer_size(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Restrict a configuration space to contain only one buffer size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   buffer size in frames
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    *
    * Wanted exact value is <,=,> val following dir (-1,0,1)
    */
  @name("snd_pcm_hw_params_set_buffer_size")
  def snd_pcm_hw_params_set_buffer_size(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: snd_pcm_uframes_t
  ): CInt = extern

  /** Restrict a configuration space with a minimum buffer size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate minimum buffer size in frames (on return filled with actual
    *   minimum)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_buffer_size_min")
  def snd_pcm_hw_params_set_buffer_size_min(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Restrict a configuration space with a maximum buffer size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate maximum buffer size in frames (on return filled with actual
    *   maximum)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_buffer_size_max")
  def snd_pcm_hw_params_set_buffer_size_max(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Restrict a configuration space to have buffer sizes in a given range.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param min
    *   approximate minimum buffer size in frames (on return filled with actual
    *   minimum)
    * @param max
    *   approximate maximum buffer size in frames (on return filled with actual
    *   maximum)
    * @return
    *   0 otherwise a negative error code if configuration space would become
    *   empty
    */
  @name("snd_pcm_hw_params_set_buffer_size_minmax")
  def snd_pcm_hw_params_set_buffer_size_minmax(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      min: Ptr[snd_pcm_uframes_t],
      max: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Restrict a configuration space to have buffer size nearest to a target.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   approximate target buffer size in frames / returned chosen approximate
    *   target buffer size in frames
    * @return
    *   0 otherwise a negative error code if configuration space is empty
    */
  @name("snd_pcm_hw_params_set_buffer_size_near")
  def snd_pcm_hw_params_set_buffer_size_near(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only its minimum buffer size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned minimum buffer size in frames
    * @return
    *   buffer size in frames
    */
  @name("snd_pcm_hw_params_set_buffer_size_first")
  def snd_pcm_hw_params_set_buffer_size_first(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Restrict a configuration space to contain only its maximum buffer size.
    *
    * @param pcm
    *   PCM handle
    * @param params
    *   Configuration space
    * @param val
    *   Returned maximum buffer size in frames
    * @return
    *   0 otherwise a negative error code
    */
  @name("snd_pcm_hw_params_set_buffer_size_last")
  def snd_pcm_hw_params_set_buffer_size_last(
      pcm: Ptr[snd_pcm_t],
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Get the minimum transfer align value in samples.
    *
    * @param params
    *   Configuration space
    * @param val
    *   Returned minimum align value
    * @return
    *   0 otherwise a negative error code if the configuration space does not
    *   contain a single value
    */
  @name("snd_pcm_hw_params_get_min_align")
  def snd_pcm_hw_params_get_min_align(
      params: Ptr[snd_pcm_hw_params_t],
      `val`: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

}
