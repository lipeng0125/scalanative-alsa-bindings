package gweb.alsa
import scala.scalanative.unsafe.*

/** MIDI controller numbers.
  */
@link("asound")
@extern
object MIDIControllers {

  /** Bank selection */
  inline val MIDI_CTL_MSB_BANK = 0x00

  /** Modulation */
  inline val MIDI_CTL_MSB_MODWHEEL = 0x01

  /** Breath */
  inline val MIDI_CTL_MSB_BREATH = 0x02

  /** Foot */
  inline val MIDI_CTL_MSB_FOOT = 0x04

  /** Portamento time */
  inline val MIDI_CTL_MSB_PORTAMENTO_TIME = 0x05

  /** Data entry */
  inline val MIDI_CTL_MSB_DATA_ENTRY = 0x06

  /** Main volume */
  inline val MIDI_CTL_MSB_MAIN_VOLUME = 0x07

  /** Balance */
  inline val MIDI_CTL_MSB_BALANCE = 0x08

  /** Panpot */
  inline val MIDI_CTL_MSB_PAN = 0x0a

  /** Expression */
  inline val MIDI_CTL_MSB_EXPRESSION = 0x0b

  /** Effect1 */
  inline val MIDI_CTL_MSB_EFFECT1 = 0x0c

  /** Effect2 */
  inline val MIDI_CTL_MSB_EFFECT2 = 0x0d

  /** General purpose 1 */
  inline val MIDI_CTL_MSB_GENERAL_PURPOSE1 = 0x10

  /** General purpose 2 */
  inline val MIDI_CTL_MSB_GENERAL_PURPOSE2 = 0x11

  /** General purpose 3 */
  inline val MIDI_CTL_MSB_GENERAL_PURPOSE3 = 0x12

  /** General purpose 4 */
  inline val MIDI_CTL_MSB_GENERAL_PURPOSE4 = 0x13

  /** Bank selection */
  inline val MIDI_CTL_LSB_BANK = 0x20

  /** Modulation */
  inline val MIDI_CTL_LSB_MODWHEEL = 0x21

  /** Breath */
  inline val MIDI_CTL_LSB_BREATH = 0x22

  /** Foot */
  inline val MIDI_CTL_LSB_FOOT = 0x24

  /** Portamento time */
  inline val MIDI_CTL_LSB_PORTAMENTO_TIME = 0x25

  /** Data entry */
  inline val MIDI_CTL_LSB_DATA_ENTRY = 0x26

  /** Main volume */
  inline val MIDI_CTL_LSB_MAIN_VOLUME = 0x27

  /** Balance */
  inline val MIDI_CTL_LSB_BALANCE = 0x28

  /** Panpot */
  inline val MIDI_CTL_LSB_PAN = 0x2a

  /** Expression */
  inline val MIDI_CTL_LSB_EXPRESSION = 0x2b

  /** Effect1 */
  inline val MIDI_CTL_LSB_EFFECT1 = 0x2c

  /** Effect2 */
  inline val MIDI_CTL_LSB_EFFECT2 = 0x2d

  /** General purpose 1 */
  inline val MIDI_CTL_LSB_GENERAL_PURPOSE1 = 0x30

  /** General purpose 2 */
  inline val MIDI_CTL_LSB_GENERAL_PURPOSE2 = 0x31

  /** General purpose 3 */
  inline val MIDI_CTL_LSB_GENERAL_PURPOSE3 = 0x32

  /** General purpose 4 */
  inline val MIDI_CTL_LSB_GENERAL_PURPOSE4 = 0x33

  /** Sustain pedal */
  inline val MIDI_CTL_SUSTAIN = 0x40

  /** Portamento */
  inline val MIDI_CTL_PORTAMENTO = 0x41

  /** Sostenuto */
  inline val MIDI_CTL_SOSTENUTO = 0x42

  /** Sostenuto (a typo in the older version) */
  inline val MIDI_CTL_SUSTENUTO = 0x42

  /** Soft pedal */
  inline val MIDI_CTL_SOFT_PEDAL = 0x43

  /** Legato foot switch */
  inline val MIDI_CTL_LEGATO_FOOTSWITCH = 0x44

  /** Hold2 */
  inline val MIDI_CTL_HOLD2 = 0x45

  /** SC1 Sound Variation */
  inline val MIDI_CTL_SC1_SOUND_VARIATION = 0x46

  /** SC2 Timbre */
  inline val MIDI_CTL_SC2_TIMBRE = 0x47

  /** SC3 Release Time */
  inline val MIDI_CTL_SC3_RELEASE_TIME = 0x48

  /** SC4 Attack Time */
  inline val MIDI_CTL_SC4_ATTACK_TIME = 0x49

  /** SC5 Brightness */
  inline val MIDI_CTL_SC5_BRIGHTNESS = 0x4a

  /** SC6 */
  inline val MIDI_CTL_SC6 = 0x4b

  /** SC7 */
  inline val MIDI_CTL_SC7 = 0x4c

  /** SC8 */
  inline val MIDI_CTL_SC8 = 0x4d

  /** SC9 */
  inline val MIDI_CTL_SC9 = 0x4e

  /** SC10 */
  inline val MIDI_CTL_SC10 = 0x4f

  /** General purpose 5 */
  inline val MIDI_CTL_GENERAL_PURPOSE5 = 0x50

  /** General purpose 6 */
  inline val MIDI_CTL_GENERAL_PURPOSE6 = 0x51

  /** General purpose 7 */
  inline val MIDI_CTL_GENERAL_PURPOSE7 = 0x52

  /** General purpose 8 */
  inline val MIDI_CTL_GENERAL_PURPOSE8 = 0x53

  /** Portamento control */
  inline val MIDI_CTL_PORTAMENTO_CONTROL = 0x54

  /** E1 Reverb Depth */
  inline val MIDI_CTL_E1_REVERB_DEPTH = 0x5b

  /** E2 Tremolo Depth */
  inline val MIDI_CTL_E2_TREMOLO_DEPTH = 0x5c

  /** E3 Chorus Depth */
  inline val MIDI_CTL_E3_CHORUS_DEPTH = 0x5d

  /** E4 Detune Depth */
  inline val MIDI_CTL_E4_DETUNE_DEPTH = 0x5e

  /** E5 Phaser Depth */
  inline val MIDI_CTL_E5_PHASER_DEPTH = 0x5f

  /** Data Increment */
  inline val MIDI_CTL_DATA_INCREMENT = 0x60

  /** Data Decrement */
  inline val MIDI_CTL_DATA_DECREMENT = 0x61

  /** Non-registered parameter number */
  inline val MIDI_CTL_NONREG_PARM_NUM_LSB = 0x62

  /** Non-registered parameter number */
  inline val MIDI_CTL_NONREG_PARM_NUM_MSB = 0x63

  /** Registered parameter number */
  inline val MIDI_CTL_REGIST_PARM_NUM_LSB = 0x64

  /** Registered parameter number */
  inline val MIDI_CTL_REGIST_PARM_NUM_MSB = 0x65

  /** All sounds off */
  inline val MIDI_CTL_ALL_SOUNDS_OFF = 0x78

  /** Reset Controllers */
  inline val MIDI_CTL_RESET_CONTROLLERS = 0x79

  /** Local control switch */
  inline val MIDI_CTL_LOCAL_CONTROL_SWITCH = 0x7a

  /** All notes off */
  inline val MIDI_CTL_ALL_NOTES_OFF = 0x7b

  /** Omni off */
  inline val MIDI_CTL_OMNI_OFF = 0x7c

  /** Omni on */
  inline val MIDI_CTL_OMNI_ON = 0x7d

  /** Mono1 */
  inline val MIDI_CTL_MONO1 = 0x7e

  /** Mono2 */
  inline val MIDI_CTL_MONO2 = 0x7f

}
