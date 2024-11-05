package gweb.alsa.PCMInterface
import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_info_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_stream_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_class_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_subclass_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sync_id_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object StreamInformation {

  /** get size of snd_pcm_info_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_pcm_info_sizeof")
  def snd_pcm_info_sizeof(): CSize = extern

  /** allocate an invalid snd_pcm_info_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_pcm_info_malloc")
  def snd_pcm_info_malloc(ptr: Ptr[Ptr[snd_pcm_info_t]]): CInt = extern

  /** frees a previously allocated snd_pcm_info_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_pcm_info_free")
  def snd_pcm_info_free(obj: Ptr[snd_pcm_info_t]): Unit = extern

  /** copy one snd_pcm_info_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_pcm_info_copy")
  def snd_pcm_info_copy(
      dst: Ptr[snd_pcm_info_t],
      src: Ptr[snd_pcm_info_t]
  ): Unit = extern

  /** Get device from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   device number
    */
  @name("snd_pcm_info_get_device")
  def snd_pcm_info_get_device(obj: Ptr[snd_pcm_info_t]): CUnsignedInt = extern

  /** Get subdevice from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   subdevice number
    */
  @name("snd_pcm_info_get_subdevice")
  def snd_pcm_info_get_subdevice(obj: Ptr[snd_pcm_info_t]): CUnsignedInt =
    extern

  /** Get stream (direction) from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   stream
    */
  @name("snd_pcm_info_get_stream")
  def snd_pcm_info_get_stream(obj: Ptr[snd_pcm_info_t]): snd_pcm_stream_t =
    extern

  /** Get card from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   card number otherwise a negative error code if not associable to a card
    */
  @name("snd_pcm_info_get_card")
  def snd_pcm_info_get_card(obj: Ptr[snd_pcm_info_t]): CInt = extern

  /** Get id from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   short id of PCM
    */
  @name("snd_pcm_info_get_id")
  def snd_pcm_info_get_id(obj: Ptr[snd_pcm_info_t]): Ptr[CChar] = extern

  /** Get name from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   name of PCM
    */
  @name("snd_pcm_info_get_name")
  def snd_pcm_info_get_name(obj: Ptr[snd_pcm_info_t]): Ptr[CChar] = extern

  /** Get subdevice name from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   name of used PCM subdevice
    */
  @name("snd_pcm_info_get_subdevice_name")
  def snd_pcm_info_get_subdevice_name(obj: Ptr[snd_pcm_info_t]): Ptr[CChar] =
    extern

  /** Get class from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   class of PCM
    */
  @name("snd_pcm_info_get_class")
  def snd_pcm_info_get_class(obj: Ptr[snd_pcm_info_t]): snd_pcm_class_t = extern

  /** Get subclass from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   subclass of PCM
    */
  @name("snd_pcm_info_get_subclass")
  def snd_pcm_info_get_subclass(obj: Ptr[snd_pcm_info_t]): snd_pcm_subclass_t =
    extern

  /** Get subdevices count from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   subdevices total count of PCM
    */
  @name("snd_pcm_info_get_subdevices_count")
  def snd_pcm_info_get_subdevices_count(
      obj: Ptr[snd_pcm_info_t]
  ): CUnsignedInt = extern

  /** Get available subdevices count from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   available subdevices count of PCM
    */
  @name("snd_pcm_info_get_subdevices_avail")
  def snd_pcm_info_get_subdevices_avail(
      obj: Ptr[snd_pcm_info_t]
  ): CUnsignedInt = extern

  /** Get hardware synchronization ID from a PCM info container.
    *
    * @param obj
    *   PCM info container
    * @return
    *   hardware synchronization ID
    */
  @name("snd_pcm_info_get_sync")
  def snd_pcm_info_get_sync(obj: Ptr[snd_pcm_info_t]): snd_pcm_sync_id_t =
    extern

  /** Set wanted device inside a PCM info container (see snd_ctl_pcm_info)
    *
    * @param obj
    *   PCM info container
    * @param val
    *   Device number
    */
  @name("snd_pcm_info_set_device")
  def snd_pcm_info_set_device(
      obj: Ptr[snd_pcm_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set wanted subdevice inside a PCM info container (see snd_ctl_pcm_info)
    *
    * @param obj
    *   PCM info container
    * @param val
    *   Subdevice number
    */
  @name("snd_pcm_info_set_subdevice")
  def snd_pcm_info_set_subdevice(
      obj: Ptr[snd_pcm_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set wanted stream inside a PCM info container (see snd_ctl_pcm_info)
    *
    * @param obj
    *   PCM info container
    * @param val
    *   Stream
    */
  @name("snd_pcm_info_set_stream")
  def snd_pcm_info_set_stream(
      obj: Ptr[snd_pcm_info_t],
      `val`: snd_pcm_stream_t
  ): Unit = extern

}
