package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.OutputInterface.snd_output_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_hw_params_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sw_params_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_status_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object DebugFunctions {

  /** Dump PCM info.
    *
    * @param pcm
    *   PCM handle
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_dump")
  def snd_pcm_dump(pcm: Ptr[snd_pcm_t], out: Ptr[snd_output_t]): CInt = extern

  /** Dump current hardware setup for PCM.
    *
    * @param pcm
    *   PCM handle
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_dump_hw_setup")
  def snd_pcm_dump_hw_setup(pcm: Ptr[snd_pcm_t], out: Ptr[snd_output_t]): CInt =
    extern

  /** Dump current software setup for PCM.
    *
    * @param pcm
    *   PCM handle
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_dump_sw_setup")
  def snd_pcm_dump_sw_setup(pcm: Ptr[snd_pcm_t], out: Ptr[snd_output_t]): CInt =
    extern

  /** Dump current setup (hardware and software) for PCM.
    *
    * @param pcm
    *   PCM handle
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_dump_setup")
  def snd_pcm_dump_setup(pcm: Ptr[snd_pcm_t], out: Ptr[snd_output_t]): CInt =
    extern

  /** Dump a PCM hardware configuration space.
    *
    * @param params
    *   Configuration space
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_hw_params_dump")
  def snd_pcm_hw_params_dump(
      params: Ptr[snd_pcm_hw_params_t],
      out: Ptr[snd_output_t]
  ): CInt = extern

  /** Dump a software configuration.
    *
    * @param params
    *   Software configuration container
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_sw_params_dump")
  def snd_pcm_sw_params_dump(
      params: Ptr[snd_pcm_sw_params_t],
      out: Ptr[snd_output_t]
  ): CInt = extern

  /** Dump status.
    *
    * @param status
    *   Status container
    * @param out
    *   Output handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_status_dump")
  def snd_pcm_status_dump(
      status: Ptr[snd_pcm_status_t],
      out: Ptr[snd_output_t]
  ): CInt = extern

}
