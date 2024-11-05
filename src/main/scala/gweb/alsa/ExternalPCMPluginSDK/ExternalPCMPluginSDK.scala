package gweb.alsa.ExternalPCMPluginSDK

import scala.scalanative.unsafe.*
import gweb.alsa.SND_DLSYM_BUILD_VERSION
import gweb.alsa.PCMInterface.PCMInterface.SND_PCM_DLSYM_VERSION
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_elem_id_t

/** Define the object entry for external PCM plugins
  */
inline def SND_PCM_PLUGIN_ENTRY(name: String): String =
  s"_snd_pcm_${name}_open"

/** Define the symbols of the given plugin with versions
  */
inline def SND_PCM_PLUGIN_SYMBOL(name: String): String =
  SND_DLSYM_BUILD_VERSION(SND_PCM_PLUGIN_ENTRY(name), SND_PCM_DLSYM_VERSION);

// todo 实现宏函数

@link("asound")
@extern
object ExternalPCMPluginSDK {

  /** Parse control element id from the config.
    *
    * @param conf
    *   the config tree to parse
    * @param ctl_id
    *   the pointer to store the resultant control element id
    * @param cardp
    *   the pointer to store the card index
    * @param cchannelsp
    *   the pointer to store the number of channels (optional)
    * @param hwctlp
    *   the pointer to store the h/w control flag (optional)
    * @return
    *   0 if successful, or a negative error code
    *
    * cchannelsp and hwctlp arguments are optional. Set NULL if not necessary.
    */
  @name("snd_pcm_parse_control_id")
  def snd_pcm_parse_control_id(
      conf: Ptr[snd_config_t],
      ctl_id: Ptr[snd_ctl_elem_id_t],
      cardp: Ptr[CInt],
      cchannelsp: Ptr[CInt],
      hwctlp: Ptr[CInt]
  ): CInt = extern

}
