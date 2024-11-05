package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_type_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_stream_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_format_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_subformat_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_tstamp_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_state_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object DescriptionFunctions {

  /** get name of PCM type
    *
    * @param type
    *   PCM type
    * @return
    *   ascii name of PCM type
    */
  @name("snd_pcm_type_name")
  def snd_pcm_type_name(`type`: snd_pcm_type_t): Ptr[CChar] = extern

  /** get name of PCM stream type
    *
    * @param stream
    *   PCM stream type
    * @return
    *   ascii name of PCM stream type
    */
  @name("snd_pcm_stream_name")
  def snd_pcm_stream_name(stream: snd_pcm_stream_t): Ptr[CChar] = extern

  /** get name of PCM access type
    *
    * @param acc
    *   PCM access type
    * @return
    *   ascii name of PCM access type
    */
  @name("snd_pcm_access_name")
  def snd_pcm_access_name(_access: snd_pcm_access_t): Ptr[CChar] = extern

  /** get name of PCM sample format
    *
    * @param format
    *   PCM sample format
    * @return
    *   ascii name of PCM sample format
    */
  @name("snd_pcm_format_name")
  def snd_pcm_format_name(format: snd_pcm_format_t): Ptr[CChar] = extern

  /** get description of PCM sample format
    *
    * @param format
    *   PCM sample format
    * @return
    *   ascii description of PCM sample format
    */
  @name("snd_pcm_format_description")
  def snd_pcm_format_description(format: snd_pcm_format_t): Ptr[CChar] = extern

  /** get name of PCM sample subformat
    *
    * @param subformat
    *   PCM sample subformat
    * @return
    *   ascii name of PCM sample subformat
    */
  @name("snd_pcm_subformat_name")
  def snd_pcm_subformat_name(subformat: snd_pcm_subformat_t): Ptr[CChar] =
    extern

  /** get description of PCM sample subformat
    *
    * @param subformat
    *   PCM sample subformat
    * @return
    *   ascii description of PCM sample subformat
    */
  @name("snd_pcm_subformat_description")
  def snd_pcm_subformat_description(
      subformat: snd_pcm_subformat_t
  ): Ptr[CChar] = extern

  /** get PCM sample subformat from name
    *
    * @param name
    *   PCM sample subformat name (case insensitive)
    * @return
    *   PCM sample subformat
    */
  @name("snd_pcm_subformat_value")
  def snd_pcm_subformat_value(name: Ptr[CChar]): snd_pcm_subformat_t = extern

  /** get PCM sample format from name
    *
    * @param name
    *   PCM sample format name (case insensitive)
    * @return
    *   PCM sample format
    */
  @name("snd_pcm_format_value")
  def snd_pcm_format_value(name: Ptr[CChar]): snd_pcm_format_t = extern

  /** get name of PCM tstamp mode setting
    *
    * @param mode
    *   PCM tstamp mode
    * @return
    *   ascii name of PCM tstamp mode setting
    */
  @name("snd_pcm_tstamp_mode_name")
  def snd_pcm_tstamp_mode_name(mode: snd_pcm_tstamp_t): Ptr[CChar] = extern

  /** get name of PCM state
    *
    * @param state
    *   PCM state
    * @return
    *   ascii name of PCM state
    */
  @name("snd_pcm_state_name")
  def snd_pcm_state_name(state: snd_pcm_state_t): Ptr[CChar] = extern

}
