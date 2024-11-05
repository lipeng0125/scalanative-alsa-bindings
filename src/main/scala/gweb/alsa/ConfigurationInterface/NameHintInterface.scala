package gweb.alsa.ConfigurationInterface

import scala.scalanative.unsafe.*

/** The name hint interface - get descriptive information about a device (name,
  * description, input/output).
  */
@link("asoud")
@extern
object NameHintInterface {

  /** Get a set of device name hints.
    * @param card
    *   Card number or -1 (means all cards)
    * @param iface
    *   Interface identification (like "pcm", "rawmidi", "timer", "seq")
    * @param hints
    *   Result - array of device name hints
    * @return
    *   zero if success, otherwise a negative error code
    *
    * hints will receive a NULL-terminated array of device name hints, which can
    * be passed to snd_device_name_get_hint to extract usable values. When no
    * longer needed, hints should be passed to snd_device_name_free_hint to
    * release resources.
    *
    * User-defined hints are gathered from namehint.IFACE tree like:
    *
    * <pre> namehint.pcm [
    *
    * myfile "file:FILE=/tmp/soundwave.raw|Save sound output to
    * /tmp/soundwave.raw"
    *
    * myplug "plug:front|Do all conversions for front speakers"
    *
    * ] </pre>
    *
    * Note: The device description is separated with '|' char.
    *
    * Special variables: defaults.namehint.showall specifies if all device
    * definitions are accepted (boolean type).
    */
  @name("snd_device_name_hint")
  def snd_device_name_hint(
      card: CInt,
      iface: Ptr[CChar],
      hints: Ptr[Ptr[CVoidPtr]]
  ): CInt = extern

  /** Free a list of device name hints.
    * @param hints
    *   List to free
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_device_name_free_hint")
  def snd_device_name_free_hint(hints: Ptr[CVoidPtr]): CInt = extern

  /** Extract a value from a hint.
    * @param hint
    *   A pointer to hint
    * @param id
    *   Hint value to extract ("NAME", "DESC", or "IOID", see below)
    * @return
    *   an allocated ASCII string if success, otherwise NULL
    *
    * List of valid IDs: NAME - name of device DESC - description of device IOID
    * \- input / output identification ("Input" or "Output"), NULL means both
    *
    * The return value should be freed when no longer needed.
    */
  @name("snd_device_name_get_hint")
  def snd_device_name_get_hint(hint: CVoidPtr, id: Ptr[CChar]): Ptr[CChar] =
    extern

}
