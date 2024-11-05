package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_format_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_subformat_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t
import gweb.alsa.Iota

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object SimpleSetupFunctions {

  /** Simple PCM latency type
    */
  class snd_spcm_latency_t private (val value: CInt) extends AnyVal

  object snd_spcm_latency_t extends Iota {

    /** standard latency - for standard playback or capture (estimated latency
      * in one direction 350ms)
      */
    val SND_SPCM_LATENCY_STANDARD = iota(0)

    /** medium latency - software phones etc. (estimated latency in one
      * direction maximally 25ms
      */
    val SND_SPCM_LATENCY_MEDIUM = iota

    /** realtime latency - realtime applications (effect processors etc.)
      * (estimated latency in one direction 5ms and better)
      */
    val SND_SPCM_LATENCY_REALTIME = iota

  }

  /** Simple PCM xrun type
    */
  class snd_spcm_xrun_type_t private (val value: CInt) extends AnyVal

  object snd_spcm_xrun_type_t extends Iota {

    /** driver / library will ignore all xruns, the stream runs forever */
    val SND_SPCM_XRUN_IGNORE = iota(0)

    /** driver / library stops the stream when an xrun occurs */
    val SND_SPCM_XRUN_STOP = iota

  }

  /** Simple PCM duplex type
    */
  class snd_spcm_duplex_type_t private (val value: CInt) extends AnyVal

  object snd_spcm_duplex_type_t extends Iota {

    /** liberal duplex - the buffer and period sizes might not match */
    val SND_SPCM_DUPLEX_LIBERAL = iota(0)

    /** pedantic duplex - the buffer and period sizes MUST match */
    val SND_SPCM_DUPLEX_PEDANTIC = iota

  }

  /** Set up a simple PCM.
    *
    * @param pcm
    *   PCM handle
    * @param rate
    *   Sample rate
    * @param channels
    *   Number of channels
    * @param format
    *   PCM format
    * @param subformat
    *   PCM subformat
    * @param latency
    *   Latency type
    * @param access
    *   PCM acceess type
    * @param xrun_type
    *   XRUN type
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_spcm_init")
  def snd_spcm_init(
      pcm: Ptr[snd_pcm_t],
      rate: CUnsignedInt,
      channels: CUnsignedInt,
      format: snd_pcm_format_t,
      subformat: snd_pcm_subformat_t,
      latency: snd_spcm_latency_t,
      _access: snd_pcm_access_t,
      xrun_type: snd_spcm_xrun_type_t
  ): CInt = extern

  /** Initialize simple PCMs in the duplex mode.
    *
    * @param playback_pcm
    *   PCM handle for playback
    * @param capture_pcm
    *   PCM handle for capture
    * @param rate
    *   Sample rate
    * @param channels
    *   Number of channels
    * @param format
    *   PCM format
    * @param subformat
    *   PCM subformat
    * @param latency
    *   Latency type
    * @param access
    *   PCM acceess type
    * @param xrun_type
    *   XRUN type
    * @param duplex_type
    *   Duplex mode
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_spcm_init_duplex")
  def snd_spcm_init_duplex(
      playback_pcm: Ptr[snd_pcm_t],
      capture_pcm: Ptr[snd_pcm_t],
      rate: CUnsignedInt,
      channels: CUnsignedInt,
      format: snd_pcm_format_t,
      subformat: snd_pcm_subformat_t,
      latency: snd_spcm_latency_t,
      _access: snd_pcm_access_t,
      xrun_type: snd_spcm_xrun_type_t,
      duplex_type: snd_spcm_duplex_type_t
  ): CInt = extern

  /** Get the set up of simple PCM.
    *
    * @param pcm
    *   PCM handle
    * @param rate
    *   Pointer to store the current sample rate
    * @param buffer_size
    *   Pointer to store the current buffer size
    * @param period_size
    *   Pointer to store the current period size
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_spcm_init_get_params")
  def snd_spcm_init_get_params(
      pcm: Ptr[snd_pcm_t],
      rate: Ptr[CUnsignedInt],
      buffer_size: Ptr[snd_pcm_uframes_t],
      period_size: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

}
