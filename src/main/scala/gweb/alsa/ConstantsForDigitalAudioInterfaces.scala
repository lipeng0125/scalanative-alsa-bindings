package gweb.alsa

import scala.scalanative.unsafe.extern
import scala.scalanative.unsafe.link

/** AES/IEC958/CEA-861 channel status bits.
  */
@link("asound")
@extern
object ConstantsForDigitalAudioInterfaces {

  /** 0 = consumer, 1 = professional */
  inline val IEC958_AES0_PROFESSIONAL = (1 << 0)

  /** 0 = audio, 1 = non-audio */
  inline val IEC958_AES0_NONAUDIO = (1 << 1)

  /** mask - emphasis */
  inline val IEC958_AES0_PRO_EMPHASIS = (7 << 2)

  /** emphasis not indicated */
  inline val IEC958_AES0_PRO_EMPHASIS_NOTID = (0 << 2)

  /** no emphasis */
  inline val IEC958_AES0_PRO_EMPHASIS_NONE = (1 << 2)

  /** 50/15us emphasis */
  inline val IEC958_AES0_PRO_EMPHASIS_5015 = (3 << 2)

  /** CCITT J.17 emphasis */
  inline val IEC958_AES0_PRO_EMPHASIS_CCITT = (7 << 2)

  /** source sample frequency: 0 = locked, 1 = unlocked */
  inline val IEC958_AES0_PRO_FREQ_UNLOCKED = (1 << 5)

  /** mask - sample frequency */
  inline val IEC958_AES0_PRO_FS = (3 << 6)

  /** fs not indicated */
  inline val IEC958_AES0_PRO_FS_NOTID = (0 << 6)

  /** 44.1kHz */
  inline val IEC958_AES0_PRO_FS_44100 = (1 << 6)

  /** 48kHz */
  inline val IEC958_AES0_PRO_FS_48000 = (2 << 6)

  /** 32kHz */
  inline val IEC958_AES0_PRO_FS_32000 = (3 << 6)

  /** 0 = copyright, 1 = not copyright */
  inline val IEC958_AES0_CON_NOT_COPYRIGHT = (1 << 2)

  /** mask - emphasis */
  inline val IEC958_AES0_CON_EMPHASIS = (7 << 3)

  /** no emphasis */
  inline val IEC958_AES0_CON_EMPHASIS_NONE = (0 << 3)

  /** 50/15us emphasis */
  inline val IEC958_AES0_CON_EMPHASIS_5015 = (1 << 3)

  /** mask - mode */
  inline val IEC958_AES0_CON_MODE = (3 << 6)

  /** mask - channel mode */
  inline val IEC958_AES1_PRO_MODE = (15 << 0)

  /** mode not indicated */
  inline val IEC958_AES1_PRO_MODE_NOTID = (0 << 0)

  /** stereophonic - ch A is left */
  inline val IEC958_AES1_PRO_MODE_STEREOPHONIC = (2 << 0)

  /** single channel */
  inline val IEC958_AES1_PRO_MODE_SINGLE = (4 << 0)

  /** two channels */
  inline val IEC958_AES1_PRO_MODE_TWO = (8 << 0)

  /** primary/secondary */
  inline val IEC958_AES1_PRO_MODE_PRIMARY = (12 << 0)

  /** vector to byte 3 */
  inline val IEC958_AES1_PRO_MODE_BYTE3 = (15 << 0)

  /** mask - user bits */
  inline val IEC958_AES1_PRO_USERBITS = (15 << 4)

  /** user bits not indicated */
  inline val IEC958_AES1_PRO_USERBITS_NOTID = (0 << 4)

  /** 192-bit structure */
  inline val IEC958_AES1_PRO_USERBITS_192 = (8 << 4)

  /** user defined application */
  inline val IEC958_AES1_PRO_USERBITS_UDEF = (12 << 4)

  /** consumer category */
  inline val IEC958_AES1_CON_CATEGORY = 0x7f

  /** general category */
  inline val IEC958_AES1_CON_GENERAL = 0x00

  /** Laser-optical mask */
  inline val IEC958_AES1_CON_LASEROPT_MASK = 0x07

  /** Laser-optical ID */
  inline val IEC958_AES1_CON_LASEROPT_ID = 0x01

  /** IEC958 CD compatible device */
  inline val IEC958_AES1_CON_IEC908_CD = (IEC958_AES1_CON_LASEROPT_ID | 0x00)

  /** non-IEC958 CD compatible device */
  inline val IEC958_AES1_CON_NON_IEC908_CD =
    (IEC958_AES1_CON_LASEROPT_ID | 0x08)

  /** Mini-Disc device */
  inline val IEC958_AES1_CON_MINI_DISC = (IEC958_AES1_CON_LASEROPT_ID | 0x48)

  /** DVD device */
  inline val IEC958_AES1_CON_DVD = (IEC958_AES1_CON_LASEROPT_ID | 0x18)

  /** Other laser-optical product */
  inline val IEC958_AES1_CON_LASTEROPT_OTHER =
    (IEC958_AES1_CON_LASEROPT_ID | 0x78)

  /** digital<->digital converter mask */
  inline val IEC958_AES1_CON_DIGDIGCONV_MASK = 0x07

  /** digital<->digital converter id */
  inline val IEC958_AES1_CON_DIGDIGCONV_ID = 0x02

  /** PCM coder */
  inline val IEC958_AES1_CON_PCM_CODER = (IEC958_AES1_CON_DIGDIGCONV_ID | 0x00)

  /** Digital signal mixer */
  inline val IEC958_AES1_CON_MIXER = (IEC958_AES1_CON_DIGDIGCONV_ID | 0x10)

  /** Rate converter */
  inline val IEC958_AES1_CON_RATE_CONVERTER =
    (IEC958_AES1_CON_DIGDIGCONV_ID | 0x18)

  /** PCM sampler */
  inline val IEC958_AES1_CON_SAMPLER = (IEC958_AES1_CON_DIGDIGCONV_ID | 0x20)

  /** Digital sound processor */
  inline val IEC958_AES1_CON_DSP = (IEC958_AES1_CON_DIGDIGCONV_ID | 0x28)

  /** Other digital<->digital product */
  inline val IEC958_AES1_CON_DIGDIGCONV_OTHER =
    (IEC958_AES1_CON_DIGDIGCONV_ID | 0x78)

  /** Magnetic device mask */
  inline val IEC958_AES1_CON_MAGNETIC_MASK = 0x07

  /** Magnetic device ID */
  inline val IEC958_AES1_CON_MAGNETIC_ID = 0x03

  /** Digital Audio Tape */
  inline val IEC958_AES1_CON_DAT = (IEC958_AES1_CON_MAGNETIC_ID | 0x00)

  /** Video recorder */
  inline val IEC958_AES1_CON_VCR = (IEC958_AES1_CON_MAGNETIC_ID | 0x08)

  /** Digital compact cassette */
  inline val IEC958_AES1_CON_DCC = (IEC958_AES1_CON_MAGNETIC_ID | 0x40)

  /** Magnetic disc digital audio device */
  inline val IEC958_AES1_CON_MAGNETIC_DISC =
    (IEC958_AES1_CON_MAGNETIC_ID | 0x18)

  /** Other magnetic device */
  inline val IEC958_AES1_CON_MAGNETIC_OTHER =
    (IEC958_AES1_CON_MAGNETIC_ID | 0x78)

  /** Broadcast mask */
  inline val IEC958_AES1_CON_BROADCAST1_MASK = 0x07

  /** Broadcast ID */
  inline val IEC958_AES1_CON_BROADCAST1_ID = 0x04

  /** Digital audio broadcast (Japan) */
  inline val IEC958_AES1_CON_DAB_JAPAN = (IEC958_AES1_CON_BROADCAST1_ID | 0x00)

  /** Digital audio broadcast (Europe) */
  inline val IEC958_AES1_CON_DAB_EUROPE = (IEC958_AES1_CON_BROADCAST1_ID | 0x08)

  /** Digital audio broadcast (USA) */
  inline val IEC958_AES1_CON_DAB_USA = (IEC958_AES1_CON_BROADCAST1_ID | 0x60)

  /** Electronic software delivery */
  inline val IEC958_AES1_CON_SOFTWARE = (IEC958_AES1_CON_BROADCAST1_ID | 0x40)

  /** Used by another standard (IEC 62105) */
  inline val IEC958_AES1_CON_IEC62105 = (IEC958_AES1_CON_BROADCAST1_ID | 0x20)

  /** Other broadcast product */
  inline val IEC958_AES1_CON_BROADCAST1_OTHER =
    (IEC958_AES1_CON_BROADCAST1_ID | 0x78)

  /** Broadcast alternative mask */
  inline val IEC958_AES1_CON_BROADCAST2_MASK = 0x0f

  /** Broadcast alternative ID */
  inline val IEC958_AES1_CON_BROADCAST2_ID = 0x0e

  /** Musical device mask */
  inline val IEC958_AES1_CON_MUSICAL_MASK = 0x07

  /** Musical device ID */
  inline val IEC958_AES1_CON_MUSICAL_ID = 0x05

  /** Synthesizer */
  inline val IEC958_AES1_CON_SYNTHESIZER = (IEC958_AES1_CON_MUSICAL_ID | 0x00)

  /** Microphone */
  inline val IEC958_AES1_CON_MICROPHONE = (IEC958_AES1_CON_MUSICAL_ID | 0x08)

  /** Other musical device */
  inline val IEC958_AES1_CON_MUSICAL_OTHER = (IEC958_AES1_CON_MUSICAL_ID | 0x78)

  /** ADC Mask */
  inline val IEC958_AES1_CON_ADC_MASK = 0x1f

  /** ADC ID */
  inline val IEC958_AES1_CON_ADC_ID = 0x06

  /** ADC without copyright information */
  inline val IEC958_AES1_CON_ADC = (IEC958_AES1_CON_ADC_ID | 0x00)

  /** Other ADC product (with no copyright information) */
  inline val IEC958_AES1_CON_ADC_OTHER = (IEC958_AES1_CON_ADC_ID | 0x60)

  /** ADC Copyright mask */
  inline val IEC958_AES1_CON_ADC_COPYRIGHT_MASK = 0x1f

  /** ADC Copyright ID */
  inline val IEC958_AES1_CON_ADC_COPYRIGHT_ID = 0x16

  /** ADC with copyright information */
  inline val IEC958_AES1_CON_ADC_COPYRIGHT =
    (IEC958_AES1_CON_ADC_COPYRIGHT_ID | 0x00)

  /** Other ADC with copyright information product */
  inline val IEC958_AES1_CON_ADC_COPYRIGHT_OTHER =
    (IEC958_AES1_CON_ADC_COPYRIGHT_ID | 0x60)

  /** Solid memory based products mask */
  inline val IEC958_AES1_CON_SOLIDMEM_MASK = 0x0f

  /** Solid memory based products ID */
  inline val IEC958_AES1_CON_SOLIDMEM_ID = 0x08

  /** Digital audio recorder and player using solid state memory */
  inline val IEC958_AES1_CON_SOLIDMEM_DIGITAL_RECORDER_PLAYER =
    (IEC958_AES1_CON_SOLIDMEM_ID | 0x00)

  /** Other solid state memory based product */
  inline val IEC958_AES1_CON_SOLIDMEM_OTHER =
    (IEC958_AES1_CON_SOLIDMEM_ID | 0x70)

  /** experimental category */
  inline val IEC958_AES1_CON_EXPERIMENTAL = 0x40

  /** this bits depends on the category code */
  inline val IEC958_AES1_CON_ORIGINAL = (1 << 7)

  /** mask - sample bits */
  inline val IEC958_AES2_PRO_SBITS = (7 << 0)

  /** 20-bit - coordination */
  inline val IEC958_AES2_PRO_SBITS_20 = (2 << 0)

  /** 24-bit - main audio */
  inline val IEC958_AES2_PRO_SBITS_24 = (4 << 0)

  /** user defined application */
  inline val IEC958_AES2_PRO_SBITS_UDEF = (6 << 0)

  /** mask - source word length */
  inline val IEC958_AES2_PRO_WORDLEN = (7 << 3)

  /** source word length not indicated */
  inline val IEC958_AES2_PRO_WORDLEN_NOTID = (0 << 3)

  /** 22-bit or 18-bit */
  inline val IEC958_AES2_PRO_WORDLEN_22_18 = (2 << 3)

  /** 23-bit or 19-bit */
  inline val IEC958_AES2_PRO_WORDLEN_23_19 = (4 << 3)

  /** 24-bit or 20-bit */
  inline val IEC958_AES2_PRO_WORDLEN_24_20 = (5 << 3)

  /** 20-bit or 16-bit */
  inline val IEC958_AES2_PRO_WORDLEN_20_16 = (6 << 3)

  /** mask - source number */
  inline val IEC958_AES2_CON_SOURCE = (15 << 0)

  /** source number unspecified */
  inline val IEC958_AES2_CON_SOURCE_UNSPEC = (0 << 0)

  /** mask - channel number */
  inline val IEC958_AES2_CON_CHANNEL = (15 << 4)

  /** channel number unspecified */
  inline val IEC958_AES2_CON_CHANNEL_UNSPEC = (0 << 4)

  /** mask - sample frequency */
  inline val IEC958_AES3_CON_FS = (15 << 0)

  /** 44.1kHz */
  inline val IEC958_AES3_CON_FS_44100 = (0 << 0)

  /** sample frequency non indicated */
  inline val IEC958_AES3_CON_FS_NOTID = (1 << 0)

  /** 48kHz */
  inline val IEC958_AES3_CON_FS_48000 = (2 << 0)

  /** 32kHz */
  inline val IEC958_AES3_CON_FS_32000 = (3 << 0)

  /** 22.05kHz */
  inline val IEC958_AES3_CON_FS_22050 = (4 << 0)

  /** 24kHz */
  inline val IEC958_AES3_CON_FS_24000 = (6 << 0)

  /** 88.2kHz */
  inline val IEC958_AES3_CON_FS_88200 = (8 << 0)

  /** 768kHz */
  inline val IEC958_AES3_CON_FS_768000 = (9 << 0)

  /** 96kHz */
  inline val IEC958_AES3_CON_FS_96000 = (10 << 0)

  /** 176.4kHz */
  inline val IEC958_AES3_CON_FS_176400 = (12 << 0)

  /** 192kHz */
  inline val IEC958_AES3_CON_FS_192000 = (14 << 0)

  /** mask - clock accuracy */
  inline val IEC958_AES3_CON_CLOCK = (3 << 4)

  /** 1000 ppm */
  inline val IEC958_AES3_CON_CLOCK_1000PPM = (0 << 4)

  /** 50 ppm */
  inline val IEC958_AES3_CON_CLOCK_50PPM = (1 << 4)

  /** variable pitch */
  inline val IEC958_AES3_CON_CLOCK_VARIABLE = (2 << 4)

  /** 0 = 20-bit, 1 = 24-bit */
  inline val IEC958_AES4_CON_MAX_WORDLEN_24 = (1 << 0)

  /** mask - sample word length */
  inline val IEC958_AES4_CON_WORDLEN = (7 << 1)

  /** not indicated */
  inline val IEC958_AES4_CON_WORDLEN_NOTID = (0 << 1)

  /** 20-bit or 16-bit */
  inline val IEC958_AES4_CON_WORDLEN_20_16 = (1 << 1)

  /** 22-bit or 18-bit */
  inline val IEC958_AES4_CON_WORDLEN_22_18 = (2 << 1)

  /** 23-bit or 19-bit */
  inline val IEC958_AES4_CON_WORDLEN_23_19 = (4 << 1)

  /** 24-bit or 20-bit */
  inline val IEC958_AES4_CON_WORDLEN_24_20 = (5 << 1)

  /** 21-bit or 17-bit */
  inline val IEC958_AES4_CON_WORDLEN_21_17 = (6 << 1)

  /** mask - original sample frequency */
  inline val IEC958_AES4_CON_ORIGFS = (15 << 4)

  /** original sample frequency not indicated */
  inline val IEC958_AES4_CON_ORIGFS_NOTID = (0 << 4)

  /** 192kHz */
  inline val IEC958_AES4_CON_ORIGFS_192000 = (1 << 4)

  /** 12kHz */
  inline val IEC958_AES4_CON_ORIGFS_12000 = (2 << 4)

  /** 176.4kHz */
  inline val IEC958_AES4_CON_ORIGFS_176400 = (3 << 4)

  /** 96kHz */
  inline val IEC958_AES4_CON_ORIGFS_96000 = (5 << 4)

  /** 8kHz */
  inline val IEC958_AES4_CON_ORIGFS_8000 = (6 << 4)

  /** 88.2kHz */
  inline val IEC958_AES4_CON_ORIGFS_88200 = (7 << 4)

  /** 16kHz */
  inline val IEC958_AES4_CON_ORIGFS_16000 = (8 << 4)

  /** 24kHz */
  inline val IEC958_AES4_CON_ORIGFS_24000 = (9 << 4)

  /** 11.025kHz */
  inline val IEC958_AES4_CON_ORIGFS_11025 = (10 << 4)

  /** 22.05kHz */
  inline val IEC958_AES4_CON_ORIGFS_22050 = (11 << 4)

  /** 32kHz */
  inline val IEC958_AES4_CON_ORIGFS_32000 = (12 << 4)

  /** 48kHz */
  inline val IEC958_AES4_CON_ORIGFS_48000 = (13 << 4)

  /** 44.1kHz */
  inline val IEC958_AES4_CON_ORIGFS_44100 = (15 << 4)

  /** mask - CGMS-A */
  inline val IEC958_AES5_CON_CGMSA = (3 << 0)

  /** copying is permitted without restriction */
  inline val IEC958_AES5_CON_CGMSA_COPYFREELY = (0 << 0)

  /** one generation of copies may be made */
  inline val IEC958_AES5_CON_CGMSA_COPYONCE = (1 << 0)

  /** condition not be used */
  inline val IEC958_AES5_CON_CGMSA_COPYNOMORE = (2 << 0)

  /** no copying is permitted */
  inline val IEC958_AES5_CON_CGMSA_COPYNEVER = (3 << 0)

  /** mask - channel count */
  inline val CEA861_AUDIO_INFOFRAME_DB1CC = (7 << 0)

  /** mask - coding type */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT = (0xf << 4)

  /** refer to stream */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_FROM_STREAM = (0 << 4)

  /** IEC-60958 L-PCM */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_IEC60958 = (1 << 4)

  /** AC-3 */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_AC3 = (2 << 4)

  /** MPEG1 Layers 1 & 2 */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_MPEG1 = (3 << 4)

  /** MPEG1 Layer 3 */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_MP3 = (4 << 4)

  /** MPEG2 Multichannel */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_MPEG2_MULTICH = (5 << 4)

  /** AAC */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_AAC = (6 << 4)

  /** DTS */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_DTS = (7 << 4)

  /** ATRAC */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_ATRAC = (8 << 4)

  /** One Bit Audio */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_ONEBIT = (9 << 4)

  /** Dolby Digital + */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_DOLBY_DIG_PLUS = (10 << 4)

  /** DTS-HD */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_DTS_HD = (11 << 4)

  /** MAT (MLP) */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_MAT = (12 << 4)

  /** DST */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_DST = (13 << 4)

  /** WMA Pro */
  inline val CEA861_AUDIO_INFOFRAME_DB1CT_WMA_PRO = (14 << 4)

  /** mask - sample frequency */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF = (7 << 2)

  /** refer to stream */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_FROM_STREAM = (0 << 2)

  /** 32kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_32000 = (1 << 2)

  /** 44.1kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_44100 = (2 << 2)

  /** 48kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_48000 = (3 << 2)

  /** 88.2kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_88200 = (4 << 2)

  /** 96kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_96000 = (5 << 2)

  /** 176.4kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_176400 = (6 << 2)

  /** 192kHz */
  inline val CEA861_AUDIO_INFOFRAME_DB2SF_192000 = (7 << 2)

  /** mask - sample size */
  inline val CEA861_AUDIO_INFOFRAME_DB2SS = (3 << 0)

  /** refer to stream */
  inline val CEA861_AUDIO_INFOFRAME_DB2SS_FROM_STREAM = (0 << 0)

  /** 16 bits */
  inline val CEA861_AUDIO_INFOFRAME_DB2SS_16BIT = (1 << 0)

  /** 20 bits */
  inline val CEA861_AUDIO_INFOFRAME_DB2SS_20BIT = (2 << 0)

  /** 24 bits */
  inline val CEA861_AUDIO_INFOFRAME_DB2SS_24BIT = (3 << 0)

  /** mask - inhibit downmixing */
  inline val CEA861_AUDIO_INFOFRAME_DB5_DM_INH = (1 << 7)

  /** stereo downmix permitted */
  inline val CEA861_AUDIO_INFOFRAME_DB5_DM_INH_PERMITTED = (0 << 7)

  /** stereo downmis prohibited */
  inline val CEA861_AUDIO_INFOFRAME_DB5_DM_INH_PROHIBITED = (1 << 7)

  /** mask - level-shift values */
  inline val CEA861_AUDIO_INFOFRAME_DB5_LSV = (0xf << 3)

}
