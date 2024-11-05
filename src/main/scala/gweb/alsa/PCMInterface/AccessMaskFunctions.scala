package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_mask_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_access_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object AccessMaskFunctions {

  /** get size of snd_pcm_access_mask_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_pcm_access_mask_sizeof")
  def snd_pcm_access_mask_sizeof(): CSize = extern

  /** allocate an empty snd_pcm_access_mask_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_pcm_access_mask_malloc")
  def snd_pcm_access_mask_malloc(ptr: Ptr[Ptr[snd_pcm_access_mask_t]]): CInt =
    extern

  /** frees a previously allocated snd_pcm_access_mask_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_pcm_access_mask_free")
  def snd_pcm_access_mask_free(obj: Ptr[snd_pcm_access_mask_t]): Unit = extern

  /** copy one snd_pcm_access_mask_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_pcm_access_mask_copy")
  def snd_pcm_access_mask_copy(
      dst: Ptr[snd_pcm_access_mask_t],
      src: Ptr[snd_pcm_access_mask_t]
  ): Unit = extern

  /** reset all bits in a snd_pcm_access_mask_t
    *
    * @param mask
    *   pointer to mask
    */
  @name("snd_pcm_access_mask_none")
  def snd_pcm_access_mask_none(mask: Ptr[snd_pcm_access_mask_t]): Unit = extern

  /** set all bits in a snd_pcm_access_mask_t
    *
    * @param mask
    *   pointer to mask
    */
  @name("snd_pcm_access_mask_any")
  def snd_pcm_access_mask_any(mask: Ptr[snd_pcm_access_mask_t]): Unit = extern

  /** test the presence of an access type in a snd_pcm_access_mask_t
    *
    * @param mask
    *   pointer to mask
    * @param val
    *   access type
    */
  @name("snd_pcm_access_mask_test")
  def snd_pcm_access_mask_test(
      mask: Ptr[snd_pcm_access_mask_t],
      `val`: snd_pcm_access_t
  ): CInt = extern

  /** test, if given a snd_pcm_access_mask_t is empty
    *
    * @param mask
    *   pointer to mask
    */
  @name("snd_pcm_access_mask_empty")
  def snd_pcm_access_mask_empty(mask: Ptr[snd_pcm_access_mask_t]): CInt = extern

  /** make an access type present in a snd_pcm_access_mask_t
    *
    * @param mask
    *   pointer to mask
    * @param val
    *   access type
    */
  @name("snd_pcm_access_mask_set")
  def snd_pcm_access_mask_set(
      mask: Ptr[snd_pcm_access_mask_t],
      `val`: snd_pcm_access_t
  ): Unit = extern

  /** make an access type missing from a snd_pcm_access_mask_t
    *
    * @param mask
    *   pointer to mask
    * @param val
    *   access type
    */
  @name("snd_pcm_access_mask_reset")
  def snd_pcm_access_mask_reset(
      mask: Ptr[snd_pcm_access_mask_t],
      `val`: snd_pcm_access_t
  ): Unit = extern

}
