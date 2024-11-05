package gweb.alsa.ControlInterface

import scala.scalanative.unsafe.*
import gweb.alsa.ControlInterface.ControlInterface.snd_sctl_t
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_t
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t

/** The setup control interface - set or modify control elements from a
  * configuration file.
  */
@link("asound")
@extern
object SetupControlInterface {

  /** Build setup control handle.
    *
    * @param sctl
    *   Result - setup control handle
    * @param handle
    *   Master control handle
    * @param conf
    *   Setup configuration
    * @param private_data
    *   Private data for runtime evaluation
    * @param mode
    *   Build mode - SND_SCTL_xxxx
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_sctl_build")
  def snd_sctl_build(
      ctl: Ptr[Ptr[snd_sctl_t]],
      handle: Ptr[snd_ctl_t],
      config: Ptr[snd_config_t],
      private_data: Ptr[snd_config_t],
      mode: CInt
  ): CInt = extern

  /** Free setup control handle.
    *
    * @param sctl
    *   Setup control handle
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_sctl_free")
  def snd_sctl_free(handle: Ptr[snd_sctl_t]): CInt = extern

  /** Install given values to control elements.
    *
    * @param h
    *   Setup control handle
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_sctl_install")
  def snd_sctl_install(handle: Ptr[snd_sctl_t]): CInt = extern

  /** Remove (restore) previous values from control elements.
    *
    * @param h
    *   Setup control handle
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_sctl_remove")
  def snd_sctl_remove(handle: Ptr[snd_sctl_t]): CInt = extern

}
