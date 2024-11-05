package gweb.alsa.MixerInterface

import scala.scalanative.unsafe.*

import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.MixerInterface.MixerInterface.snd_mixer_t
import gweb.alsa.MixerInterface.MixerInterface.snd_mixer_class_t
import gweb.alsa.MixerInterface.MixerInterface.snd_mixer_elem_t
import gweb.alsa.Iota

/** The simple mixer interface.
  */
@link("asound")
@extern
object SimpleMixerInterface {

  /** Mixer simple element - register options
    */
  type snd_mixer_selem_regopt =
    CStruct5[
      /** structure version
        */
      CInt, // ver
      /** v1: abstract layer selection
        */
      snd_mixer_selem_regopt_abstract, // abstract
      /** v1: device name (must be NULL when playback_pcm or capture_pcm !=
        * NULL)
        */
      Ptr[CChar], // device
      /** v1: playback PCM connected to mixer device (NULL == none)
        */
      Ptr[
        snd_pcm_t
      ], // playback_pcm
      /** v1: capture PCM connected to mixer device (NULL == none)
        */
      Ptr[snd_pcm_t] // capture_pcm
    ]

  /** Mixer simple element identifier
    */
  type snd_mixer_selem_id_t = CStruct0

  /** Mixer simple element channel identifier
    */
  class snd_mixer_selem_channel_id_t private (val value: CInt) extends AnyVal

  object snd_mixer_selem_channel_id_t extends Iota {

    /** Unknown */
    val SND_MIXER_SCHN_UNKNOWN = iota(-1)

    /** Front left */
    val SND_MIXER_SCHN_FRONT_LEFT = iota(0)

    /** Front right */
    val SND_MIXER_SCHN_FRONT_RIGHT = iota

    /** Rear left */
    val SND_MIXER_SCHN_REAR_LEFT = iota

    /** Rear right */
    val SND_MIXER_SCHN_REAR_RIGHT = iota

    /** Front center */
    val SND_MIXER_SCHN_FRONT_CENTER = iota

    /** Woofer */
    val SND_MIXER_SCHN_WOOFER = iota

    /** Side Left */
    val SND_MIXER_SCHN_SIDE_LEFT = iota

    /** Side Right */
    val SND_MIXER_SCHN_SIDE_RIGHT = iota

    /** Rear Center */
    val SND_MIXER_SCHN_REAR_CENTER = iota

    /** */
    val SND_MIXER_SCHN_LAST = iota(31)

    /** Mono (Front left alias) */
    val SND_MIXER_SCHN_MONO = iota(SND_MIXER_SCHN_FRONT_LEFT)

  }

  /** Mixer simple element - register options - abstraction level
    */
  class snd_mixer_selem_regopt_abstract private (val value: CInt) extends AnyVal

  object snd_mixer_selem_regopt_abstract extends Iota {

    /** no abstraction - try use all universal controls from driver */
    val SND_MIXER_SABSTRACT_NONE = iota(0)

    /** basic abstraction - Master,PCM,CD,Aux,Record-Gain etc. */
    val SND_MIXER_SABSTRACT_BASIC = iota

  }

  /** Return name of mixer simple element channel.
    *
    * @param channel
    *   mixer simple element channel identifier
    * @return
    *   channel name
    */
  @name("snd_mixer_selem_channel_name")
  def snd_mixer_selem_channel_name(
      channel: snd_mixer_selem_channel_id_t
  ): Ptr[CChar] = extern

  /** Register mixer simple element class.
    *
    * @param mixer
    *   Mixer handle
    * @param options
    *   Options container
    * @param classp
    *   Pointer to returned mixer simple element class handle (or NULL)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_register")
  def snd_mixer_selem_register(
      mixer: Ptr[snd_mixer_t],
      options: Ptr[snd_mixer_selem_regopt],
      classp: Ptr[Ptr[snd_mixer_class_t]]
  ): CInt = extern

  /** Get mixer simple element identifier.
    *
    * @param elem
    *   Mixer simple element handle
    * @param id
    *   returned mixer simple element identifier
    */
  @name("snd_mixer_selem_get_id")
  def snd_mixer_selem_get_id(
      element: Ptr[snd_mixer_elem_t],
      id: Ptr[snd_mixer_selem_id_t]
  ): Unit = extern

  /** Get name part of mixer simple element identifier.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   name part of simple element identifier
    */
  @name("snd_mixer_selem_get_name")
  def snd_mixer_selem_get_name(elem: Ptr[snd_mixer_elem_t]): Ptr[CChar] = extern

  /** Get index part of mixer simple element identifier.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   index part of simple element identifier
    */
  @name("snd_mixer_selem_get_index")
  def snd_mixer_selem_get_index(elem: Ptr[snd_mixer_elem_t]): CUnsignedInt =
    extern

  /** Find a mixer simple element.
    *
    * @param mixer
    *   Mixer handle
    * @param id
    *   Mixer simple element identifier
    * @return
    *   mixer simple element handle or NULL if not found
    */
  @name("snd_mixer_find_selem")
  def snd_mixer_find_selem(
      mixer: Ptr[snd_mixer_t],
      id: Ptr[snd_mixer_selem_id_t]
  ): Ptr[snd_mixer_elem_t] = extern

  /** Get info about the active state of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if not active, 1 if active
    */
  @name("snd_mixer_selem_is_active")
  def snd_mixer_selem_is_active(elem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** Get info about channels of playback stream of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if not mono, 1 if mono
    */
  @name("snd_mixer_selem_is_playback_mono")
  def snd_mixer_selem_is_playback_mono(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Get info about channels of playback stream of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   Mixer simple element channel identifier
    * @return
    *   0 if channel is not present, 1 if present
    */
  @name("snd_mixer_selem_has_playback_channel")
  def snd_mixer_selem_has_playback_channel(
      obj: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t
  ): CInt = extern

  /** Get info about channels of capture stream of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if not mono, 1 if mono
    */
  @name("snd_mixer_selem_is_capture_mono")
  def snd_mixer_selem_is_capture_mono(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Get info about channels of capture stream of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   Mixer simple element channel identifier
    * @return
    *   0 if channel is not present, 1 if present
    */
  @name("snd_mixer_selem_has_capture_channel")
  def snd_mixer_selem_has_capture_channel(
      obj: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t
  ): CInt = extern

  /** Return info about capture switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   group for switch exclusivity (see
    *   snd_mixer_selem_has_capture_switch_exclusive)
    */
  @name("snd_mixer_selem_get_capture_group")
  def snd_mixer_selem_get_capture_group(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return true if mixer simple element has only one volume control for both
    * playback and capture.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 separated control, 1 common control
    */
  @name("snd_mixer_selem_has_common_volume")
  def snd_mixer_selem_has_common_volume(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return info about playback volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if no control is present, 1 if it's present
    */
  @name("snd_mixer_selem_has_playback_volume")
  def snd_mixer_selem_has_playback_volume(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return info about playback volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if control is separated per channel, 1 if control acts on all channels
    *   together
    */
  @name("snd_mixer_selem_has_playback_volume_joined")
  def snd_mixer_selem_has_playback_volume_joined(
      elem: Ptr[snd_mixer_elem_t]
  ): CInt = extern

  /** Return info about capture volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if no control is present, 1 if it's present
    */
  @name("snd_mixer_selem_has_capture_volume")
  def snd_mixer_selem_has_capture_volume(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return info about capture volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if control is separated per channel, 1 if control acts on all channels
    *   together
    */
  @name("snd_mixer_selem_has_capture_volume_joined")
  def snd_mixer_selem_has_capture_volume_joined(
      elem: Ptr[snd_mixer_elem_t]
  ): CInt = extern

  /** Return true if mixer simple element has only one switch control for both
    * playback and capture.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 separated control, 1 common control
    */
  @name("snd_mixer_selem_has_common_switch")
  def snd_mixer_selem_has_common_switch(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return info about playback switch control existence of a mixer simple
    * element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if no control is present, 1 if it's present
    */
  @name("snd_mixer_selem_has_playback_switch")
  def snd_mixer_selem_has_playback_switch(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return info about playback switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if control is separated per channel, 1 if control acts on all channels
    *   together
    */
  @name("snd_mixer_selem_has_playback_switch_joined")
  def snd_mixer_selem_has_playback_switch_joined(
      elem: Ptr[snd_mixer_elem_t]
  ): CInt = extern

  /** Return info about capture switch control existence of a mixer simple
    * element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if no control is present, 1 if it's present
    */
  @name("snd_mixer_selem_has_capture_switch")
  def snd_mixer_selem_has_capture_switch(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return info about capture switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if control is separated per channel, 1 if control acts on all channels
    *   together
    */
  @name("snd_mixer_selem_has_capture_switch_joined")
  def snd_mixer_selem_has_capture_switch_joined(
      elem: Ptr[snd_mixer_elem_t]
  ): CInt = extern

  /** Return info about capture switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 if control is separated per element, 1 if control acts on other
    *   elements too (i.e. only one active at a time inside a group)
    */
  @name("snd_mixer_selem_has_capture_switch_exclusive")
  def snd_mixer_selem_has_capture_switch_exclusive(
      elem: Ptr[snd_mixer_elem_t]
  ): CInt = extern

  /** Return corresponding dB value to an integer playback volume for a mixer
    * simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   value to be converted to dB range
    * @param dBvalue
    *   pointer to returned dB value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_ask_playback_vol_dB")
  def snd_mixer_selem_ask_playback_vol_dB(
      elem: Ptr[snd_mixer_elem_t],
      value: CLong,
      dBvalue: Ptr[CLong]
  ): CInt = extern

  /** Return corresponding dB value to an integer capture volume for a mixer
    * simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   value to be converted to dB range
    * @param dBvalue
    *   pointer to returned dB value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_ask_capture_vol_dB")
  def snd_mixer_selem_ask_capture_vol_dB(
      elem: Ptr[snd_mixer_elem_t],
      value: CLong,
      dBvalue: Ptr[CLong]
  ): CInt = extern

  /** Return corresponding integer playback volume for given dB value for a
    * mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   value to be converted to dB range
    * @param dir
    *   rounding mode - rounds up if dir > 0, round to nearest if dir == 0,
    *   rounds down if dir < 0
    * @param dBvalue
    *   pointer to returned dB value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_ask_playback_dB_vol")
  def snd_mixer_selem_ask_playback_dB_vol(
      elem: Ptr[snd_mixer_elem_t],
      dBvalue: CLong,
      dir: CInt,
      value: Ptr[CLong]
  ): CInt = extern

  /** Return corresponding integer capture volume for given dB value for a mixer
    * simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param dBvalue
    *   dB value to be converted to integer range
    * @param value
    *   pointer to returned integer value
    * @param dir
    *   rounding mode - rounds up if dir > 0, round to nearest if dir == 0,
    *   rounds down if dir < 0
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_ask_capture_dB_vol")
  def snd_mixer_selem_ask_capture_dB_vol(
      elem: Ptr[snd_mixer_elem_t],
      dBvalue: CLong,
      dir: CInt,
      value: Ptr[CLong]
  ): CInt = extern

  /** Return value of playback volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   pointer to returned value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_get_playback_volume")
  def snd_mixer_selem_get_playback_volume(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: Ptr[CLong]
  ): CInt = extern

  /** Return value of capture volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   pointer to returned value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_get_capture_volume")
  def snd_mixer_selem_get_capture_volume(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: Ptr[CLong]
  ): CInt = extern

  /** Return value of playback volume in dB control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   pointer to returned value (dB * 100)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_get_playback_dB")
  def snd_mixer_selem_get_playback_dB(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: Ptr[CLong]
  ): CInt = extern

  /** Return value of capture volume in dB control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   pointer to returned value (dB * 100)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_get_capture_dB")
  def snd_mixer_selem_get_capture_dB(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: Ptr[CLong]
  ): CInt = extern

  /** Return value of playback switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   pointer to returned value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_get_playback_switch")
  def snd_mixer_selem_get_playback_switch(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: Ptr[CInt]
  ): CInt = extern

  /** Return value of capture switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   pointer to returned value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_get_capture_switch")
  def snd_mixer_selem_get_capture_switch(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: Ptr[CInt]
  ): CInt = extern

  /** Set value of playback volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_playback_volume")
  def snd_mixer_selem_set_playback_volume(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: CLong
  ): CInt = extern

  /** Set value of capture volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_capture_volume")
  def snd_mixer_selem_set_capture_volume(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: CLong
  ): CInt = extern

  /** Set value in dB of playback volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   control value in dB * 100
    * @param dir
    *   rounding mode - rounds up if dir > 0, round to nearest if dir == 0,
    *   rounds down if dir < 0
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_playback_dB")
  def snd_mixer_selem_set_playback_dB(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: CLong,
      dir: CInt
  ): CInt = extern

  /** Set value in dB of capture volume control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   control value in dB * 100
    * @param dir
    *   rounding mode - rounds up if dir > 0, round to nearest if dir == 0,
    *   rounds down if dir < 0
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_capture_dB")
  def snd_mixer_selem_set_capture_dB(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: CLong,
      dir: CInt
  ): CInt = extern

  /** Set value of playback volume control for all channels of a mixer simple
    * element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_playback_volume_all")
  def snd_mixer_selem_set_playback_volume_all(
      elem: Ptr[snd_mixer_elem_t],
      value: CLong
  ): CInt = extern

  /** Set value of capture volume control for all channels of a mixer simple
    * element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_capture_volume_all")
  def snd_mixer_selem_set_capture_volume_all(
      elem: Ptr[snd_mixer_elem_t],
      value: CLong
  ): CInt = extern

  /** Set value in dB of playback volume control for all channels of a mixer
    * simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   control value in dB * 100
    * @param dir
    *   rounding mode - rounds up if dir > 0, round to nearest if dir == 0,
    *   rounds down if dir < 0
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_playback_dB_all")
  def snd_mixer_selem_set_playback_dB_all(
      elem: Ptr[snd_mixer_elem_t],
      value: CLong,
      dir: CInt
  ): CInt = extern

  /** Set value in dB of capture volume control for all channels of a mixer
    * simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   control value in dB * 100
    * @param dir
    *   rounding mode - rounds up if dir > 0, round to nearest if dir == 0,
    *   rounds down if dir < 0
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_capture_dB_all")
  def snd_mixer_selem_set_capture_dB_all(
      elem: Ptr[snd_mixer_elem_t],
      value: CLong,
      dir: CInt
  ): CInt = extern

  /** Set value of playback switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_playback_switch")
  def snd_mixer_selem_set_playback_switch(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: CInt
  ): CInt = extern

  /** Set value of capture switch control of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_capture_switch")
  def snd_mixer_selem_set_capture_switch(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      value: CInt
  ): CInt = extern

  /** Set value of playback switch control for all channels of a mixer simple
    * element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_playback_switch_all")
  def snd_mixer_selem_set_playback_switch_all(
      elem: Ptr[snd_mixer_elem_t],
      value: CInt
  ): CInt = extern

  /** Set value of capture switch control for all channels of a mixer simple
    * element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param value
    *   control value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_selem_set_capture_switch_all")
  def snd_mixer_selem_set_capture_switch_all(
      elem: Ptr[snd_mixer_elem_t],
      value: CInt
  ): CInt = extern

  /** Get range for playback volume of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param min
    *   Pointer to returned minimum
    * @param max
    *   Pointer to returned maximum
    */
  @name("snd_mixer_selem_get_playback_volume_range")
  def snd_mixer_selem_get_playback_volume_range(
      elem: Ptr[snd_mixer_elem_t],
      min: Ptr[CLong],
      max: Ptr[CLong]
  ): CInt = extern

  /** Get range in dB for playback volume of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param min
    *   Pointer to returned minimum (dB * 100)
    * @param max
    *   Pointer to returned maximum (dB * 100)
    */
  @name("snd_mixer_selem_get_playback_dB_range")
  def snd_mixer_selem_get_playback_dB_range(
      elem: Ptr[snd_mixer_elem_t],
      min: Ptr[CLong],
      max: Ptr[CLong]
  ): CInt = extern

  /** Set range for playback volume of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param min
    *   minimum volume value
    * @param max
    *   maximum volume value
    */
  @name("snd_mixer_selem_set_playback_volume_range")
  def snd_mixer_selem_set_playback_volume_range(
      elem: Ptr[snd_mixer_elem_t],
      min: CLong,
      max: CLong
  ): CInt = extern

  /** Get range for capture volume of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param min
    *   Pointer to returned minimum
    * @param max
    *   Pointer to returned maximum
    */
  @name("snd_mixer_selem_get_capture_volume_range")
  def snd_mixer_selem_get_capture_volume_range(
      elem: Ptr[snd_mixer_elem_t],
      min: Ptr[CLong],
      max: Ptr[CLong]
  ): CInt = extern

  /** Get range in dB for capture volume of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param min
    *   Pointer to returned minimum (dB * 100)
    * @param max
    *   Pointer to returned maximum (dB * 100)
    */
  @name("snd_mixer_selem_get_capture_dB_range")
  def snd_mixer_selem_get_capture_dB_range(
      elem: Ptr[snd_mixer_elem_t],
      min: Ptr[CLong],
      max: Ptr[CLong]
  ): CInt = extern

  /** Set range for capture volume of a mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @param min
    *   minimum volume value
    * @param max
    *   maximum volume value
    */
  @name("snd_mixer_selem_set_capture_volume_range")
  def snd_mixer_selem_set_capture_volume_range(
      elem: Ptr[snd_mixer_elem_t],
      min: CLong,
      max: CLong
  ): CInt = extern

  /** Return true if mixer simple element is an enumerated control.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 normal volume/switch control, 1 enumerated control
    */
  @name("snd_mixer_selem_is_enumerated")
  def snd_mixer_selem_is_enumerated(elem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** Return true if mixer simple enumerated element belongs to the playback
    * direction.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 no playback direction, 1 playback direction
    */
  @name("snd_mixer_selem_is_enum_playback")
  def snd_mixer_selem_is_enum_playback(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return true if mixer simple enumerated element belongs to the capture
    * direction.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   0 no capture direction, 1 capture direction
    */
  @name("snd_mixer_selem_is_enum_capture")
  def snd_mixer_selem_is_enum_capture(elem: Ptr[snd_mixer_elem_t]): CInt =
    extern

  /** Return the number of enumerated items of the given mixer simple element.
    *
    * @param elem
    *   Mixer simple element handle
    * @return
    *   the number of enumerated items, otherwise a negative error code
    */
  @name("snd_mixer_selem_get_enum_items")
  def snd_mixer_selem_get_enum_items(elem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** get the enumerated item string for the given mixer simple element
    *
    * @param elem
    *   Mixer simple element handle
    * @param item
    *   the index of the enumerated item to query
    * @param maxlen
    *   the maximal length to be stored
    * @param buf
    *   the buffer to store the name string
    * @return
    *   0 if successful, otherwise a negative error code
    */
  @name("snd_mixer_selem_get_enum_item_name")
  def snd_mixer_selem_get_enum_item_name(
      elem: Ptr[snd_mixer_elem_t],
      idx: CUnsignedInt,
      maxlen: CSize,
      str: Ptr[CChar]
  ): CInt = extern

  /** get the current selected enumerated item for the given mixer simple
    * element
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param itemp
    *   the pointer to store the index of the enumerated item
    * @return
    *   0 if successful, otherwise a negative error code
    */
  @name("snd_mixer_selem_get_enum_item")
  def snd_mixer_selem_get_enum_item(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      idxp: Ptr[CUnsignedInt]
  ): CInt = extern

  /** set the current selected enumerated item for the given mixer simple
    * element
    *
    * @param elem
    *   Mixer simple element handle
    * @param channel
    *   mixer simple element channel identifier
    * @param item
    *   the enumerated item index
    * @return
    *   0 if successful, otherwise a negative error code
    */
  @name("snd_mixer_selem_set_enum_item")
  def snd_mixer_selem_set_enum_item(
      elem: Ptr[snd_mixer_elem_t],
      channel: snd_mixer_selem_channel_id_t,
      idx: CUnsignedInt
  ): CInt = extern

  /** get size of snd_mixer_selem_id_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_mixer_selem_id_sizeof")
  def snd_mixer_selem_id_sizeof(): CSize = extern

  /** allocate an invalid snd_mixer_selem_id_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_mixer_selem_id_malloc")
  def snd_mixer_selem_id_malloc(ptr: Ptr[Ptr[snd_mixer_selem_id_t]]): CInt =
    extern

  /** frees a previously allocated snd_mixer_selem_id_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_mixer_selem_id_free")
  def snd_mixer_selem_id_free(obj: Ptr[snd_mixer_selem_id_t]): Unit = extern

  /** copy one snd_mixer_selem_id_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_mixer_selem_id_copy")
  def snd_mixer_selem_id_copy(
      dst: Ptr[snd_mixer_selem_id_t],
      src: Ptr[snd_mixer_selem_id_t]
  ): Unit = extern

  /** Get name part of a mixer simple element identifier.
    *
    * @param obj
    *   Mixer simple element identifier
    * @return
    *   name part
    */
  @name("snd_mixer_selem_id_get_name")
  def snd_mixer_selem_id_get_name(obj: Ptr[snd_mixer_selem_id_t]): Ptr[CChar] =
    extern

  /** Get index part of a mixer simple element identifier.
    *
    * @param obj
    *   Mixer simple element identifier
    * @return
    *   index part
    */
  @name("snd_mixer_selem_id_get_index")
  def snd_mixer_selem_id_get_index(
      obj: Ptr[snd_mixer_selem_id_t]
  ): CUnsignedInt = extern

  /** Set name part of a mixer simple element identifier.
    *
    * @param obj
    *   Mixer simple element identifier
    * @param val
    *   name part
    */
  @name("snd_mixer_selem_id_set_name")
  def snd_mixer_selem_id_set_name(
      obj: Ptr[snd_mixer_selem_id_t],
      `val`: Ptr[CChar]
  ): Unit = extern

  /** Set index part of a mixer simple element identifier.
    *
    * @param obj
    *   Mixer simple element identifier
    * @param val
    *   index part
    */
  @name("snd_mixer_selem_id_set_index")
  def snd_mixer_selem_id_set_index(
      obj: Ptr[snd_mixer_selem_id_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Parse ASCII simple mixer element identifier.
    *
    * @param dst
    *   Parsed simple mixer element identifier
    * @param str
    *   Mixer simple element ASCII representation
    */
  @name("snd_mixer_selem_id_parse")
  def snd_mixer_selem_id_parse(
      dst: Ptr[snd_mixer_selem_id_t],
      str: Ptr[CChar]
  ): CInt = extern

}
