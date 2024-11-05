package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_format_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sframes_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_channel_area_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object HelperFunctions {

  /** Return sign info for a PCM sample linear format.
    *
    * @param format
    *   Format
    * @return
    *   0 unsigned, 1 signed, a negative error code if format is not linear
    */
  @name("snd_pcm_format_signed")
  def snd_pcm_format_signed(format: snd_pcm_format_t): CInt = extern

  /** Return sign info for a PCM sample linear format.
    *
    * @param format
    *   Format
    * @return
    *   0 signed, 1 unsigned, a negative error code if format is not linear
    */
  @name("snd_pcm_format_unsigned")
  def snd_pcm_format_unsigned(format: snd_pcm_format_t): CInt = extern

  /** Return linear info for a PCM sample format.
    *
    * @param format
    *   Format
    * @return
    *   0 non linear, 1 linear
    */
  @name("snd_pcm_format_linear")
  def snd_pcm_format_linear(format: snd_pcm_format_t): CInt = extern

  /** Return float info for a PCM sample format.
    *
    * @param format
    *   Format
    * @return
    *   0 non float, 1 float
    */
  @name("snd_pcm_format_float")
  def snd_pcm_format_float(format: snd_pcm_format_t): CInt = extern

  /** Return endian info for a PCM sample format.
    *
    * @param format
    *   Format
    * @return
    *   0 big endian, 1 little endian, a negative error code if endian
    *   independent
    */
  @name("snd_pcm_format_little_endian")
  def snd_pcm_format_little_endian(format: snd_pcm_format_t): CInt = extern

  /** Return endian info for a PCM sample format.
    *
    * @param format
    *   Format
    * @return
    *   0 little endian, 1 big endian, a negative error code if endian
    *   independent
    */
  @name("snd_pcm_format_big_endian")
  def snd_pcm_format_big_endian(format: snd_pcm_format_t): CInt = extern

  /** Return endian info for a PCM sample format.
    *
    * @param format
    *   Format
    * @return
    *   0 swapped, 1 CPU endian, a negative error code if endian independent
    */
  @name("snd_pcm_format_cpu_endian")
  def snd_pcm_format_cpu_endian(format: snd_pcm_format_t): CInt = extern

  /** Return the bit-width of the format.
    *
    * @param format
    *   Sample format
    * @return
    *   the bit-width of the format, or a negative error code if not applicable
    */
  @name("snd_pcm_format_width")
  def snd_pcm_format_width(format: snd_pcm_format_t): CInt = extern

  /** Return the physical bit-width of the format (bits needed to store a PCM
    * sample)
    *
    * @param format
    *   Sample format
    * @return
    *   the physical bit-width of the format, or a negative error code if not
    *   applicable
    */
  @name("snd_pcm_format_physical_width")
  def snd_pcm_format_physical_width(format: snd_pcm_format_t): CInt = extern

  /** Compose a PCM sample linear format.
    *
    * @param width
    *   Nominal bits per sample
    * @param pwidth
    *   Physical bit width of the format
    * @param unsignd
    *   Sign: 0 signed, 1 unsigned
    * @param big_endian
    *   Endian: 0 little endian, 1 big endian
    * @return
    *   The matching format type, or SND_PCM_FORMAT_UNKNOWN if no match
    */
  @name("snd_pcm_build_linear_format")
  def snd_pcm_build_linear_format(
      width: CInt,
      pwidth: CInt,
      unsignd: CInt,
      big_endian: CInt
  ): snd_pcm_format_t = extern

  /** Return bytes needed to store a quantity of PCM sample.
    *
    * @param format
    *   Sample format
    * @param samples
    *   Samples count
    * @return
    *   bytes needed, a negative error code if not integer or unknown
    */
  @name("snd_pcm_format_size")
  def snd_pcm_format_size(format: snd_pcm_format_t, samples: CSize): CSSize =
    extern

  /** Return 8 bit expressing silence for a PCM sample format.
    *
    * @param format
    *   Sample format
    * @return
    *   silence 8 bit word
    */
  @name("snd_pcm_format_silence")
  def snd_pcm_format_silence(format: snd_pcm_format_t): Byte = extern

  /** Return 16 bit expressing silence for a PCM sample format.
    *
    * @param format
    *   Sample format
    * @return
    *   silence 16 bit word
    */
  @name("snd_pcm_format_silence_16")
  def snd_pcm_format_silence_16(format: snd_pcm_format_t): CUnsignedShort =
    extern

  /** Return 32 bit expressing silence for a PCM sample format.
    *
    * @param format
    *   Sample format
    * @return
    *   silence 32 bit word
    */
  @name("snd_pcm_format_silence_32")
  def snd_pcm_format_silence_32(format: snd_pcm_format_t): CUnsignedInt = extern

  /** Return 64 bit expressing silence for a PCM sample format.
    *
    * @param format
    *   Sample format
    * @return
    *   silence 64 bit word
    */
  @name("snd_pcm_format_silence_64")
  def snd_pcm_format_silence_64(format: snd_pcm_format_t): CUnsignedLong =
    extern

  /** Silence a PCM samples buffer.
    *
    * @param format
    *   Sample format
    * @param data
    *   Buffer
    * @param samples
    *   Samples count
    * @return
    *   0 if successful or a negative error code
    */
  @name("snd_pcm_format_set_silence")
  def snd_pcm_format_set_silence(
      format: snd_pcm_format_t,
      buf: CVoidPtr,
      samples: CUnsignedInt
  ): CInt = extern

  /** Convert bytes in frames for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param bytes
    *   quantity in bytes
    * @return
    *   quantity expressed in frames
    */
  @name("snd_pcm_bytes_to_frames")
  def snd_pcm_bytes_to_frames(
      pcm: Ptr[snd_pcm_t],
      bytes: CSSize
  ): snd_pcm_sframes_t = extern

  /** Convert frames in bytes for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param frames
    *   quantity in frames
    * @return
    *   quantity expressed in bytes
    */
  @name("snd_pcm_frames_to_bytes")
  def snd_pcm_frames_to_bytes(
      pcm: Ptr[snd_pcm_t],
      frames: snd_pcm_sframes_t
  ): CSSize = extern

  /** Convert bytes in samples for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param bytes
    *   quantity in bytes
    * @return
    *   quantity expressed in samples
    */
  @name("snd_pcm_bytes_to_samples")
  def snd_pcm_bytes_to_samples(pcm: Ptr[snd_pcm_t], bytes: CSSize): CLong =
    extern

  /** Convert samples in bytes for a PCM.
    *
    * @param pcm
    *   PCM handle
    * @param samples
    *   quantity in samples
    * @return
    *   quantity expressed in bytes
    */
  @name("snd_pcm_samples_to_bytes")
  def snd_pcm_samples_to_bytes(pcm: Ptr[snd_pcm_t], samples: CLong): CSSize =
    extern

  /** Silence an area.
    *
    * @param dst_area
    *   area specification
    * @param dst_offset
    *   offset in frames inside area
    * @param samples
    *   samples to silence
    * @param format
    *   PCM sample format
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_area_silence")
  def snd_pcm_area_silence(
      dst_channel: Ptr[snd_pcm_channel_area_t],
      dst_offset: snd_pcm_uframes_t,
      samples: CUnsignedInt,
      format: snd_pcm_format_t
  ): CInt = extern

  /** Silence one or more areas.
    *
    * @param dst_areas
    *   areas specification (one for each channel)
    * @param dst_offset
    *   offset in frames inside area
    * @param channels
    *   channels count
    * @param frames
    *   frames to silence
    * @param format
    *   PCM sample format
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_areas_silence")
  def snd_pcm_areas_silence(
      dst_channels: Ptr[snd_pcm_channel_area_t],
      dst_offset: snd_pcm_uframes_t,
      channels: CUnsignedInt,
      frames: snd_pcm_uframes_t,
      format: snd_pcm_format_t
  ): CInt = extern

  /** Copy an area.
    *
    * @param dst_area
    *   destination area specification
    * @param dst_offset
    *   offset in frames inside destination area
    * @param src_area
    *   source area specification
    * @param src_offset
    *   offset in frames inside source area
    * @param samples
    *   samples to copy
    * @param format
    *   PCM sample format
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_area_copy")
  def snd_pcm_area_copy(
      dst_channel: Ptr[snd_pcm_channel_area_t],
      dst_offset: snd_pcm_uframes_t,
      src_channel: Ptr[snd_pcm_channel_area_t],
      src_offset: snd_pcm_uframes_t,
      samples: CUnsignedInt,
      format: snd_pcm_format_t
  ): CInt = extern

  /** Copy one or more areas.
    *
    * @param dst_areas
    *   destination areas specification (one for each channel)
    * @param dst_offset
    *   offset in frames inside destination area
    * @param src_areas
    *   source areas specification (one for each channel)
    * @param src_offset
    *   offset in frames inside source area
    * @param channels
    *   channels count
    * @param frames
    *   frames to copy
    * @param format
    *   PCM sample format
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_areas_copy")
  def snd_pcm_areas_copy(
      dst_channels: Ptr[snd_pcm_channel_area_t],
      dst_offset: snd_pcm_uframes_t,
      src_channels: Ptr[snd_pcm_channel_area_t],
      src_offset: snd_pcm_uframes_t,
      channels: CUnsignedInt,
      frames: snd_pcm_uframes_t,
      format: snd_pcm_format_t
  ): CInt = extern

  /** Copy one or more areas.
    *
    * @param dst_channels
    *   destination areas specification (one for each channel)
    * @param dst_offset
    *   offset in frames inside destination area
    * @param dst_size
    *   size in frames of the destination buffer
    * @param src_channels
    *   source areas specification (one for each channel)
    * @param src_offset
    *   offset in frames inside source area
    * @param src_size
    *   size in frames of the source buffer
    * @param channels
    *   channels count
    * @param frames
    *   frames to copy
    * @param format
    *   PCM sample format
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_areas_copy_wrap")
  def snd_pcm_areas_copy_wrap(
      dst_channels: Ptr[snd_pcm_channel_area_t],
      dst_offset: snd_pcm_uframes_t,
      dst_size: snd_pcm_uframes_t,
      src_channels: Ptr[snd_pcm_channel_area_t],
      src_offset: snd_pcm_uframes_t,
      src_size: snd_pcm_uframes_t,
      channels: CUnsignedInt,
      frames: snd_pcm_uframes_t,
      format: snd_pcm_format_t
  ): CInt = extern

}
