package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.Iota
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object HookExtension {

  /** PCM hook container
    */
  type snd_pcm_hook_t = CStruct0

  /** PCM hook callback function
    */
  type snd_pcm_hook_func_t = CFuncPtr1[
    Ptr[snd_pcm_hook_t], // hook
    CInt
  ]

  /** type of pcm hook
    */
  class snd_pcm_hook_type_t private (val value: CInt) extends AnyVal

  object snd_pcm_hook_type_t extends Iota {

    /** */
    val SND_PCM_HOOK_TYPE_HW_PARAMS = iota(0)

    /** */
    val SND_PCM_HOOK_TYPE_HW_FREE = iota

    /** */
    val SND_PCM_HOOK_TYPE_CLOSE = iota

    /** */
    val SND_PCM_HOOK_TYPE_LAST = iota(SND_PCM_HOOK_TYPE_CLOSE)

  }

  /** Get PCM handle for a PCM hook.
    *
    * @param hook
    *   PCM hook handle
    * @return
    *   PCM handle
    */
  @name("snd_pcm_hook_get_pcm")
  def snd_pcm_hook_get_pcm(hook: Ptr[snd_pcm_hook_t]): Ptr[snd_pcm_t] = extern

  /** Get callback function private data for a PCM hook.
    *
    * @param hook
    *   PCM hook handle
    * @return
    *   callback function private data
    */
  @name("snd_pcm_hook_get_private")
  def snd_pcm_hook_get_private(hook: Ptr[snd_pcm_hook_t]): CVoidPtr = extern

  /** Set callback function private data for a PCM hook.
    *
    * @param hook
    *   PCM hook handle
    * @param private_data
    *   The private data value
    */
  @name("snd_pcm_hook_set_private")
  def snd_pcm_hook_set_private(
      hook: Ptr[snd_pcm_hook_t],
      private_data: CVoidPtr
  ): Unit = extern

  /** Add a PCM hook at end of hooks chain.
    *
    * @param hookp
    *   Returned PCM hook handle
    * @param pcm
    *   PCM handle
    * @param type
    *   PCM hook type
    * @param func
    *   PCM hook callback function
    * @param private_data
    *   PCM hook private data
    * @return
    *   0 on success otherwise a negative error code
    *
    * Warning: an hook callback function cannot remove an hook of the same type
    * different from itself
    */
  @name("snd_pcm_hook_add")
  def snd_pcm_hook_add(
      hookp: Ptr[Ptr[snd_pcm_hook_t]],
      pcm: Ptr[snd_pcm_t],
      `type`: snd_pcm_hook_type_t,
      func: snd_pcm_hook_func_t,
      private_data: CVoidPtr
  ): CInt = extern

  /** Remove a PCM hook.
    *
    * @param hook
    *   PCM hook handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Warning: an hook callback cannot remove an hook of the same type different
    * from itself
    */
  @name("snd_pcm_hook_remove")
  def snd_pcm_hook_remove(hook: Ptr[snd_pcm_hook_t]): CInt = extern

}
