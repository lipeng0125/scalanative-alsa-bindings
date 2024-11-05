package gweb.alsa

import scala.scalanative.unsafe.*
import scala.scalanative.unsafe.Nat.Digit2
import scala.scalanative.unsafe.Nat.*
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_t

/** todo 补完
  */
@link("asound")
@extern
object ExternalControlPluginSDK {

  /** Handle of control ext
    *
    * External control plugin handle
    */
  type snd_ctl_ext_t =
    CStruct14[
      /** protocol version; SND_CTL_EXT_VERSION must be filled here before
        * calling snd_ctl_ext_create()
        */
      CUnsignedInt, // version
      /** Index of this card; must be filled before calling snd_ctl_ext_create()
        */
      CInt, // card_idx
      /** ID string of this card; must be filled before calling
        * snd_ctl_ext_create()
        */
      CArray[CChar, Digit2[_1, _6]], // id
      /** Driver name of this card; must be filled before calling
        * snd_ctl_ext_create()
        */
      CArray[CChar, Digit2[_1, _6]], // driver
      /** short name of this card; must be filled before calling
        * snd_ctl_ext_create()
        */
      CArray[CChar, Digit2[_3, _2]], // name
      /** Long name of this card; must be filled before calling
        * snd_ctl_ext_create()
        */
      CArray[CChar, Digit2[_8, _0]], // longname
      /** Mixer name of this card; must be filled before calling
        * snd_ctl_ext_create()
        */
      CArray[CChar, Digit2[_8, _0]], // mixername
      /** poll descriptor
        */
      CInt, // poll_fd
      /** callbacks of this plugin; must be filled before calling
        * snd_pcm_ioplug_create()
        */
      Ptr[snd_ctl_ext_callback_t], // callback
      /** private data, which can be used freely in the driver callbacks
        */
      CVoidPtr, // private_data
      /** control handle filled by snd_ctl_ext_create()
        */
      Ptr[snd_ctl_t], // handle
      /** non-block mode; read-only
        */
      CInt, // nonblock
      /** events subscribed; read-only
        */
      CInt, // subscribed
      /** optional TLV data for the control (since protocol 1.0.1)
        */
      Ptr[snd_ctl_ext_tlv_rw_t] // c
      | Ptr[CUnsignedInt] // p
      // tlv
    ]

  // todo 补完该类
  type snd_ctl_ext_callback_t = CStruct0

}
