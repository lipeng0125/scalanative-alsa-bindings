package gweb.alsa

import scala.scalanative.unsafe.*
import gweb.alsa.MixerInterface.SimpleMixerInterface.snd_mixer_selem_id_t
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_elem_id_t

/** The ALSA Use Case manager interface. See ALSA Use Case Interface page for
  * more details.
  *
  * ALSA Use Case Interface
  *
  * The use case manager works by configuring the sound card ALSA kcontrols to
  * change the hardware digital and analog audio routing to match the requested
  * device use case. The use case manager kcontrol configurations are stored in
  * easy to modify text files.An audio use case can be defined by a verb and
  * device parameter. The verb describes the use case action i.e. a phone call,
  * listening to music, recording a conversation etc. The device describes the
  * physical audio capture and playback hardware i.e. headphones, phone handset,
  * bluetooth headset, etc.It's intended clients will mostly only need to set
  * the use case verb and device for each system use case change (as the verb
  * and device parameters cover most audio use cases).However there are times
  * when a use case has to be modified at runtime. e.g.
  *
  * <ul>
  *
  * <li>Incoming phone call when the device is playing music</li>
  *
  * <li>Recording sections</li>
  *
  * <li>of a phone call Playing tones during a call. </li>
  *
  * </ul>
  *
  * In order to allow asynchronous runtime use case adaptations, we have a third
  * optional modifier parameter that can be used to further configure the use
  * case during live audio runtime.This interface allows clients to :-
  *
  * <ul>
  *
  * <li>Query the supported use case verbs, devices and modifiers for the
  * machine.</li>
  *
  * <li>Set and Get use case verbs, devices and modifiers for the machine.</li>
  *
  * <li>Get the ALSA PCM playback and capture device PCMs for use case verb, use
  * case device and modifier.</li>
  *
  * <li>Get the TQ parameter for each use case verb, use case device and
  * modifier.</li>
  *
  * <li>Get the ALSA master playback and capture volume/switch kcontrols or
  * mixer elements for each use case.</li>
  *
  * </ul>
  */
@link("asound")
@extern
object UseCaseInterface {

  /** Inactive Verb
    */
  inline val SND_USE_CASE_VERB_INACTIVE = "Inactive"

  /** HiFi Verb
    */
  inline val SND_USE_CASE_VERB_HIFI = "HiFi"

  /** HiFi Low Power Verb
    */
  inline val SND_USE_CASE_VERB_HIFI_LOW_POWER = "HiFi Low Power"

  /** Voice Verb
    */
  inline val SND_USE_CASE_VERB_VOICE = "Voice"

  /** Voice Low Power Verb
    */
  inline val SND_USE_CASE_VERB_VOICE_LOW_POWER = "Voice Low Power"

  /** Voice Call Verb
    */
  inline val SND_USE_CASE_VERB_VOICECALL = "Voice Call"

  /** Voice Call IP Verb
    */
  inline val SND_USE_CASE_VERB_IP_VOICECALL = "Voice Call IP"

  /** FM Analog Radio Verb
    */
  inline val SND_USE_CASE_VERB_ANALOG_RADIO = "FM Analog Radio"

  /** FM Digital Radio Verb
    */
  inline val SND_USE_CASE_VERB_DIGITAL_RADIO = "FM Digital Radio"

  /** None Device
    */
  inline val SND_USE_CASE_DEV_NONE = "None"

  /** Speaker Device
    */
  inline val SND_USE_CASE_DEV_SPEAKER = "Speaker"

  /** Line Device
    */
  inline val SND_USE_CASE_DEV_LINE = "Line"

  /** Microphone Device
    */
  inline val SND_USE_CASE_DEV_MIC = "Mic"

  /** Headphones Device
    */
  inline val SND_USE_CASE_DEV_HEADPHONES = "Headphones"

  /** Headset Device
    */
  inline val SND_USE_CASE_DEV_HEADSET = "Headset"

  /** Handset Device
    */
  inline val SND_USE_CASE_DEV_HANDSET = "Handset"

  /** Bluetooth Device
    */
  inline val SND_USE_CASE_DEV_BLUETOOTH = "Bluetooth"

  /** Earpiece Device
    */
  inline val SND_USE_CASE_DEV_EARPIECE = "Earpiece"

  /** SPDIF Device
    */
  inline val SND_USE_CASE_DEV_SPDIF = "SPDIF"

  /** HDMI / DisplayPort Device
    */
  inline val SND_USE_CASE_DEV_HDMI = "HDMI"

  /** USB Device (multifunctional)
    */
  inline val SND_USE_CASE_DEV_USB = "USB"

  /** Direct Device (no channel remapping), (e.g. ProAudio usage)
    */
  inline val SND_USE_CASE_DEV_DIRECT = "Direct"

  /** Capture Voice Modifier
    */
  inline val SND_USE_CASE_MOD_CAPTURE_VOICE = "Capture Voice"

  /** Capture Music Modifier
    */
  inline val SND_USE_CASE_MOD_CAPTURE_MUSIC = "Capture Music"

  /** Play Music Modifier
    */
  inline val SND_USE_CASE_MOD_PLAY_MUSIC = "Play Music"

  /** Play Voice Modifier
    */
  inline val SND_USE_CASE_MOD_PLAY_VOICE = "Play Voice"

  /** Play Tone Modifier
    */
  inline val SND_USE_CASE_MOD_PLAY_TONE = "Play Tone"

  /** Echo Reference Modifier
    */
  inline val SND_USE_CASE_MOD_ECHO_REF = "Echo Reference"

  /** TQ - Tone Quality
    *
    * The interface allows clients to determine the audio TQ required for each
    * use case verb and modifier. It's intended as an optional hint to the audio
    * driver in order to lower power consumption. Music Tone Quality
    */
  inline val SND_USE_CASE_TQ_MUSIC = "Music"

  /** Voice Tone Quality
    */
  inline val SND_USE_CASE_TQ_VOICE = "Voice"

  /** Tones Tone Quality
    */
  inline val SND_USE_CASE_TQ_TONES = "Tones"

  /** use case container
    */
  type snd_use_case_mgr_t = CStruct0

  /** Create an identifier.
    *
    * @param fmt
    *   Format (sprintf like)
    * @param ...
    *   Optional arguments for sprintf like format
    * @return
    *   Allocated string identifier or NULL on error
    */
  @name("snd_use_case_identifier")
  def snd_use_case_identifier(fmt: Ptr[CChar], args: Any*): Ptr[CChar] = extern

  /** Free a string list.
    *
    * @param list
    *   The string list to free
    * @param items
    *   Count of strings
    * @return
    *   Zero if success, otherwise a negative error code
    */
  @name("snd_use_case_free_list")
  def snd_use_case_free_list(list: Ptr[Ptr[CChar]], items: CInt): CInt = extern

  /** Obtain a list of entries.
    *
    * @param uc_mgr
    *   Use case manager (may be NULL - card list)
    * @param identifier
    *   (may be NULL - card list)
    * @param list
    *   Returned allocated list
    * @return
    *   Number of list entries if success, otherwise a negative error code
    *
    * Defined identifiers:
    *
    * Note that at most one of the supported/conflicting devs lists has any
    * entries, and when neither is present, all devices are supported.
    */
  @name("snd_use_case_get_list")
  def snd_use_case_get_list(
      uc_mgr: Ptr[snd_use_case_mgr_t],
      identifier: Ptr[CChar],
      list: Ptr[Ptr[Ptr[CChar]]]
  ): CInt = extern

  /** Get current - string.
    *
    * @param uc_mgr
    *   Use case manager
    * @param identifier
    *
    * @param value
    *   Value pointer
    * @return
    *   Zero if success, otherwise a negative error code
    *
    * Note: The returned string is dynamically allocated, use free() to
    * deallocate this string. (Yes, the value parameter shouldn't be marked as
    * "const", but it's too late to fix it, sorry about that.)
    *
    * Known identifiers:
    *
    * Recommended names for values:
    */
  @name("snd_use_case_get")
  def snd_use_case_get(
      uc_mgr: Ptr[snd_use_case_mgr_t],
      identifier: Ptr[CChar],
      value: Ptr[Ptr[CChar]]
  ): CInt = extern

  /** Get current - integer.
    *
    * @param uc_mgr
    *   Use case manager
    * @param identifier
    *
    * @param value
    *   result
    * @return
    *   Zero if success, otherwise a negative error code
    *
    * Known identifiers:
    */
  @name("snd_use_case_geti")
  def snd_use_case_geti(
      uc_mgr: Ptr[snd_use_case_mgr_t],
      identifier: Ptr[CChar],
      value: Ptr[CLong]
  ): CInt = extern

  /** Set new.
    *
    * @param uc_mgr
    *   Use case manager
    * @param identifier
    *
    * @param value
    *   Value
    * @return
    *   Zero if success, otherwise a negative error code
    *
    * Known identifiers:
    */
  @name("snd_use_case_set")
  def snd_use_case_set(
      uc_mgr: Ptr[snd_use_case_mgr_t],
      identifier: Ptr[CChar],
      value: Ptr[CChar]
  ): CInt = extern

  /** Open and initialise use case core for sound card.
    *
    * @param uc_mgr
    *   Returned use case manager pointer
    * @param card_name
    *   Sound card name.
    * @return
    *   zero if success, otherwise a negative error code
    *
    * By default only first card is used when the driver card name or long name
    * is passed in the card_name argument.
    *
    * The "strict:" prefix in the card_name defines that there is no driver name
    * / long name matching. The straight configuration is used.
    *
    * The "hw:" prefix in the card_name will load the configuration for the ALSA
    * card specified by the card index (value) or the card string identificator.
    *
    * The sound card might be also composed from several physical sound cards
    * (for the default and strict card_name). The application cannot expect that
    * the device names will refer only one ALSA sound card in this case.
    */
  @name("snd_use_case_mgr_open")
  def snd_use_case_mgr_open(
      uc_mgr: Ptr[Ptr[snd_use_case_mgr_t]],
      card_name: Ptr[CChar]
  ): CInt = extern

  /** Reload and re-parse use case configuration files for sound card.
    *
    * @param uc_mgr
    *   Use case manager
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_use_case_mgr_reload")
  def snd_use_case_mgr_reload(uc_mgr: Ptr[snd_use_case_mgr_t]): CInt = extern

  /** Close use case manager.
    *
    * @param uc_mgr
    *   Use case manager
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_use_case_mgr_close")
  def snd_use_case_mgr_close(uc_mgr: Ptr[snd_use_case_mgr_t]): CInt = extern

  /** Reset use case manager verb, device, modifier to deafult settings.
    *
    * @param uc_mgr
    *   Use case manager
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_use_case_mgr_reset")
  def snd_use_case_mgr_reset(uc_mgr: Ptr[snd_use_case_mgr_t]): CInt = extern

  /** Parse control element identifier.
    *
    * @param dst
    *   Element identifier
    * @param ucm_id
    *   Use case identifier
    * @param value
    *   String value to be parsed
    * @return
    *   Zero if success, otherwise a negative error code
    */
  @name("snd_use_case_parse_ctl_elem_id")
  def snd_use_case_parse_ctl_elem_id(
      dst: Ptr[snd_ctl_elem_id_t],
      ucm_id: Ptr[CChar],
      value: Ptr[CChar]
  ): CInt = extern

  /** Parse mixer element identifier.
    *
    * @param dst
    *   Simple mixer element identifier
    * @param ucm_id
    *   Use case identifier
    * @param value
    *   String value to be parsed
    * @return
    *   Zero if success, otherwise a negative error code
    */
  @name("snd_use_case_parse_selem_id")
  def snd_use_case_parse_selem_id(
      dst: Ptr[snd_mixer_selem_id_t],
      ucm_id: Ptr[CChar],
      value: Ptr[CChar]
  ): CInt = extern

}
