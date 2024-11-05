package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_channel_area_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_sframes_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object DirectAccessFunctions {

  /** Application request to access a portion of direct (mmap) area.
    *
    * @param pcm
    *   PCM handle
    * @param areas
    *   Returned mmap channel areas
    * @param offset
    *   Returned mmap area offset in area steps (== frames)
    * @param frames
    *   mmap area portion size in frames (wanted on entry, contiguous available
    *   on exit)
    * @return
    *   0 on success otherwise a negative error code
    *
    * It is necessary to call the snd_pcm_avail_update() function directly
    * before this call. Otherwise, this function can return a wrong count of
    * available frames.
    *
    * The function should be called before a sample-direct area can be accessed.
    * The resulting size parameter is always less or equal to the input count of
    * frames and can be zero, if no frames can be processed (the ring buffer is
    * full).
    *
    * See the snd_pcm_mmap_commit() function to finish the frame processing in
    * the direct areas.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_mmap_begin")
  def snd_pcm_mmap_begin(
      pcm: Ptr[snd_pcm_t],
      areas: Ptr[Ptr[snd_pcm_channel_area_t]],
      offset: Ptr[snd_pcm_uframes_t],
      frames: Ptr[snd_pcm_uframes_t]
  ): CInt = extern

  /** Application has completed the access to area requested with
    * snd_pcm_mmap_begin.
    *
    * @param pcm
    *   PCM handle
    * @param offset
    *   area offset in area steps (== frames)
    * @param frames
    *   area portion size in frames
    * @return
    *   count of transferred frames otherwise a negative error code
    *
    * You should pass this function the offset value that snd_pcm_mmap_begin()
    * returned. The frames parameter should hold the number of frames you have
    * written or read to/from the audio buffer. The frames parameter must never
    * exceed the contiguous frames count that snd_pcm_mmap_begin() returned.
    * Each call to snd_pcm_mmap_begin() must be followed by a call to
    * snd_pcm_mmap_commit().
    *
    * Example:
    *
    * Look to the Sine-wave generator example for more details about the
    * generate_sine function.
    *
    * The function is thread-safe when built with the proper option.
    */
  @name("snd_pcm_mmap_commit")
  def snd_pcm_mmap_commit(
      pcm: Ptr[snd_pcm_t],
      offset: snd_pcm_uframes_t,
      frames: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Write interleaved frames to a PCM using direct buffer (mmap)
    *
    * @param pcm
    *   PCM handle
    * @param buffer
    *   frames containing buffer
    * @param size
    *   frames to be written
    * @return
    *   a positive number of frames actually written otherwise a negative error
    *   code
    *
    * If the blocking behaviour is selected, then routine waits until all
    * requested bytes are played or put to the playback ring buffer. The count
    * of bytes can be less only if a signal or underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    */
  @name("snd_pcm_mmap_writei")
  def snd_pcm_mmap_writei(
      pcm: Ptr[snd_pcm_t],
      buffer: CVoidPtr,
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Read interleaved frames from a PCM using direct buffer (mmap)
    *
    * @param pcm
    *   PCM handle
    * @param buffer
    *   frames containing buffer
    * @param size
    *   frames to be read
    * @return
    *   a positive number of frames actually read otherwise a negative error
    *   code
    *
    * If the blocking behaviour was selected, then routine waits until all
    * requested bytes are filled. The count of bytes can be less only if a
    * signal or underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    */
  @name("snd_pcm_mmap_readi")
  def snd_pcm_mmap_readi(
      pcm: Ptr[snd_pcm_t],
      buffer: CVoidPtr,
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Write non interleaved frames to a PCM using direct buffer (mmap)
    *
    * @param pcm
    *   PCM handle
    * @param bufs
    *   frames containing buffers (one for each channel)
    * @param size
    *   frames to be written
    * @return
    *   a positive number of frames actually written otherwise a negative error
    *   code
    *
    * If the blocking behaviour is selected, then routine waits until all
    * requested bytes are played or put to the playback ring buffer. The count
    * of bytes can be less only if a signal or underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    */
  @name("snd_pcm_mmap_writen")
  def snd_pcm_mmap_writen(
      pcm: Ptr[snd_pcm_t],
      bufs: Ptr[CVoidPtr],
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

  /** Read non interleaved frames to a PCM using direct buffer (mmap)
    *
    * @param pcm
    *   PCM handle
    * @param bufs
    *   frames containing buffers (one for each channel)
    * @param size
    *   frames to be written
    * @return
    *   a positive number of frames actually read otherwise a negative error
    *   code
    *
    * If the blocking behaviour was selected, then routine waits until all
    * requested bytes are filled. The count of bytes can be less only if a
    * signal or underrun occurred.
    *
    * If the non-blocking behaviour is selected, then routine doesn't wait at
    * all.
    */
  @name("snd_pcm_mmap_readn")
  def snd_pcm_mmap_readn(
      pcm: Ptr[snd_pcm_t],
      bufs: Ptr[CVoidPtr],
      size: snd_pcm_uframes_t
  ): snd_pcm_sframes_t = extern

}
