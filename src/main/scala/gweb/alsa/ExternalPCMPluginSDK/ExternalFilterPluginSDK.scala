package gweb.alsa.ExternalPCMPluginSDK

import scala.scalanative.unsafe.*

import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_stream_t
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.Iota

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object ExternalFilterPluginSDK {

  /** Protocol major version
    */
  inline val SND_PCM_EXTPLUG_VERSION_MAJOR = 1

  /** Protocol minor version
    */
  inline val SND_PCM_EXTPLUG_VERSION_MINOR = 0

  /** Protocol tiny version
    */
  inline val SND_PCM_EXTPLUG_VERSION_TINY = 2

  /** Filter-plugin protocol version
    */
  inline val SND_PCM_EXTPLUG_VERSION =
    ((SND_PCM_EXTPLUG_VERSION_MAJOR << 16) | (SND_PCM_EXTPLUG_VERSION_MINOR << 8) | (SND_PCM_EXTPLUG_VERSION_TINY))

  /** Handle of external filter plugin
    */
  type snd_pcm_extplug_t = CStruct0

  /** Callback table of extplug
    */
  type snd_pcm_extplug_callback_t = CStruct0

  /** hw constraints for extplug
    */
  object $ extends Iota {

    /** format */
    val SND_PCM_EXTPLUG_HW_FORMAT = iota

    /** channels */
    val SND_PCM_EXTPLUG_HW_CHANNELS = iota

    /** max number of hw constraints */
    val SND_PCM_EXTPLUG_HW_PARAMS = iota

  }

  /** Create an extplug instance.
    *
    * @param extplug
    *   the extplug handle
    * @param name
    *   name of the PCM
    * @param root
    *   configuration tree root
    * @param slave_conf
    *   slave configuration root
    * @param stream
    *   stream direction
    * @param mode
    *   PCM open mode
    * @return
    *   0 if successful, or a negative error code
    *
    * Creates the extplug instance based on the given handle. The slave_conf
    * argument is mandatory, and usually taken from the config tree of the PCM
    * plugin as "slave" config value. name, root, stream and mode arguments are
    * the values used for opening the PCM.
    *
    * The callback is the mandatory field of extplug handle. At least, start,
    * stop and pointer callbacks must be set before calling this function.
    */
  @name("snd_pcm_extplug_create")
  def snd_pcm_extplug_create(
      ext: Ptr[snd_pcm_extplug_t],
      name: Ptr[CChar],
      root: Ptr[snd_config_t],
      slave_conf: Ptr[snd_config_t],
      stream: snd_pcm_stream_t,
      mode: CInt
  ): CInt = extern

  /** Delete the extplug instance.
    *
    * @param extplug
    *   the extplug handle to delete
    * @return
    *   0 if successful, or a negative error code
    *
    * The destructor of extplug instance. Closes the PCM and deletes the
    * associated resources.
    */
  @name("snd_pcm_extplug_delete")
  def snd_pcm_extplug_delete(ext: Ptr[snd_pcm_extplug_t]): CInt = extern

  /** Reset extplug parameters.
    *
    * @param extplug
    *   the extplug handle
    *
    * Resets the all parameters for the given extplug handle.
    */
  @name("snd_pcm_extplug_params_reset")
  def snd_pcm_extplug_params_reset(ext: Ptr[snd_pcm_extplug_t]): Unit = extern

  /** Set master parameter as the list.
    *
    * @param extplug
    *   the extplug handle
    * @param type
    *   parameter type
    * @param num_list
    *   number of available values
    * @param list
    *   the list of available values
    * @return
    *   0 if successful, or a negative error code
    *
    * Sets the master parameter as the list. The available values of the given
    * parameter type of this PCM (as input) is restricted to the ones of the
    * given list.
    */
  @name("snd_pcm_extplug_set_param_list")
  def snd_pcm_extplug_set_param_list(
      extplug: Ptr[snd_pcm_extplug_t],
      `type`: CInt,
      num_list: CUnsignedInt,
      list: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Set master parameter as the min/max values.
    *
    * @param extplug
    *   the extplug handle
    * @param type
    *   parameter type
    * @param min
    *   the minimum value
    * @param max
    *   the maximum value
    * @return
    *   0 if successful, or a negative error code
    *
    * Sets the master parameter as the min/max values. The available values of
    * the given parameter type of this PCM (as input) is restricted between the
    * given minimum and maximum values.
    */
  @name("snd_pcm_extplug_set_param_minmax")
  def snd_pcm_extplug_set_param_minmax(
      extplug: Ptr[snd_pcm_extplug_t],
      `type`: CInt,
      min: CUnsignedInt,
      max: CUnsignedInt
  ): CInt = extern

  /** Set slave parameter as the list.
    *
    * @param extplug
    *   the extplug handle
    * @param type
    *   parameter type
    * @param num_list
    *   number of available values
    * @param list
    *   the list of available values
    * @return
    *   0 if successful, or a negative error code
    *
    * Sets the slave parameter as the list. The available values of the given
    * parameter type of the slave PCM is restricted to the ones of the given
    * list.
    */
  @name("snd_pcm_extplug_set_slave_param_list")
  def snd_pcm_extplug_set_slave_param_list(
      extplug: Ptr[snd_pcm_extplug_t],
      `type`: CInt,
      num_list: CUnsignedInt,
      list: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Set slave parameter as the min/max values.
    *
    * @param extplug
    *   the extplug handle
    * @param type
    *   parameter type
    * @param min
    *   the minimum value
    * @param max
    *   the maximum value
    * @return
    *   0 if successful, or a negative error code
    *
    * Sets the slave parameter as the min/max values. The available values of
    * the given parameter type of the slave PCM is restricted between the given
    * minimum and maximum values.
    */
  @name("snd_pcm_extplug_set_slave_param_minmax")
  def snd_pcm_extplug_set_slave_param_minmax(
      extplug: Ptr[snd_pcm_extplug_t],
      `type`: CInt,
      min: CUnsignedInt,
      max: CUnsignedInt
  ): CInt = extern

  /** Keep the client and slave format/channels the same if requested. This is
    * for example useful if this extplug does not support any channel
    * conversion.
    *
    * @param extplug
    *   the extplug handle
    * @param type
    *   parameter type
    * @param keep_link
    *   if 1 the parameter identified by type will be kept the same for the
    *   client and slave PCM of this extplug
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_pcm_extplug_set_param_link")
  def snd_pcm_extplug_set_param_link(
      extplug: Ptr[snd_pcm_extplug_t],
      `type`: CInt,
      keep_link: CInt
  ): CInt = extern

}
