package gweb.alsa

import scala.scalanative.unsafe.*

import scala.scalanative.libc.stdio.FILE

/** The output functions present an interface similar to the stdio functions on
  * top of different underlying output destinations.
  *
  * Many PCM debugging functions (snd_pcm_xxx_dump_xxx) use such an output
  * handle to be able to write not only to the screen but also to other
  * destinations, e.g. to files or to memory buffers.
  */
@link("asound")
@extern
object OutputInterface {

  /** Internal structure for an output object.
    *
    * The ALSA library uses a pointer to this structure as a handle to an output
    * object. Applications don't access its contents directly.
    *
    * typedef struct _snd_output snd_output_t
    */
  opaque type snd_output_t = CStruct0

  /** Output type.
    */
  class snd_output_type_t private (val value: CInt) extends AnyVal

  object snd_output_type_t extends Iota {

    /** Output to a stdio stream.
      */
    val SND_OUTPUT_STDIO: CInt = iota

    /** Output to a memory buffer.
      */
    val SND_OUTPUT_BUFFER: CInt = iota
  }

  /** Creates a new output object writing to a file.
    *
    * @param outputp
    *   The function puts the pointer to the new output object at the address
    *   specified by outputp.
    * @param file
    *   The name of the file to open.
    * @param mode
    *   The open mode, like fopen(3).
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  def snd_output_stdio_open(
      outputp: Ptr[Ptr[snd_output_t]],
      file: Ptr[CChar],
      mode: Ptr[CChar]
  ): CInt = extern

  /** Creates a new output object using an existing stdio FILE pointer.
    *
    * @param outputp
    *   The function puts the pointer to the new output object at the address
    *   specified by outputp.
    * @param fp
    *   The FILE pointer to write to. Characters are written to the file
    *   starting at the current file position.
    * @param _close
    *   Close flag. Set this to 1 if snd_output_close should close fp by calling
    *   fclose.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  def snd_output_stdio_attach(
      outputp: Ptr[Ptr[snd_output_t]],
      fp: Ptr[FILE],
      _close: CInt
  ): CInt = extern

  /** Creates a new output object with an auto-extending memory buffer.
    *
    * @param outputp
    *   The function puts the pointer to the new output object at the address
    *   specified by outputp.
    *
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  def snd_output_buffer_open(outputp: Ptr[Ptr[snd_output_t]]): CInt = extern

  /** Returns the address of the buffer of a SND_OUTPUT_BUFFER output handle.
    *
    * @param output
    *   The output handle.
    * @param buf
    *   The functions puts the current address of the buffer at the address
    *   specified by buf.
    *
    * @return
    *   The current size of valid data in the buffer.
    *
    * The address of the buffer may become invalid when output functions or
    * snd_output_close are called.
    */
  def snd_output_buffer_string(
      output: Ptr[snd_output_t],
      buf: Ptr[Ptr[CChar]]
  ): CSize = extern

  /** Returns the address of the buffer of a SND_OUTPUT_BUFFER output handle.
    *
    * @param output
    *   The output handle.
    * @param buf
    *   The functions puts the current address of the buffer at the address
    *   specified by buf.
    *
    * @return
    *   The current size of valid data in the buffer.
    *
    * The internal buffer is empty after this call. The caller has the
    * responsibility to clean the buffer using the free() call.
    */
  def snd_output_buffer_steal(
      output: Ptr[snd_output_t],
      buf: Ptr[Ptr[CChar]]
  ): CSize = extern

  /** Closes an output handle.
    *
    * @param output
    *   The output handle to be closed.
    *
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  def snd_output_close(output: Ptr[snd_output_t]): CInt = extern

  /** Writes formatted output (like fprintf(3)) to an output handle.
    *
    * @param output
    *   The output handle.
    * @param format
    *   Format string in fprintf format.
    * @param args
    *   Other fprintf arguments.
    * @return
    *   The number of characters written, or a negative error code.
    */
  def snd_output_printf(
      output: Ptr[snd_output_t],
      format: Ptr[CChar],
      args: Any*
  ): CInt = extern

  /** Writes formatted output (like fprintf(3)) to an output handle.
    *
    * @param output
    *   The output handle.
    * @param format
    *   Format string in fprintf format.
    * @param args
    *   Other fprintf arguments.
    * @return
    *   The number of characters written, or a negative error code.
    */
  def snd_output_vprintf(
      output: Ptr[snd_output_t],
      format: Ptr[CChar],
      args: CVarArgList
  ): CInt = extern

  /** Writes a string to an output handle (like fputs(3)).
    *
    * @param output
    *   The output handle.
    * @param str
    *   Pointer to the string.
    * @return
    *   Zero if successful, otherwise a negative error code or EOF.
    */
  def snd_output_puts(output: Ptr[snd_output_t], str: Ptr[CChar]): CInt = extern

  /** Writes a character to an output handle (like putc(3)).
    *
    * @param output
    *   The output handle.
    * @param c
    *   The character.
    *
    * @return
    *   Zero if successful, otherwise a negative error code or EOF.
    */
  def snd_output_putc(output: Ptr[snd_output_t], c: CInt): CInt = extern

  /** Flushes an output handle (like fflush(3)).
    *
    * @param output
    *   The output handle.
    * @return
    *   Zero if successful, otherwise EOF.
    *
    * If the underlying destination is a stdio stream, this function calls
    * fflush. If the underlying destination is a memory buffer, the write
    * position is reset to the beginning of the buffer. =:-o
    */
  def snd_output_flush(output: Ptr[snd_output_t]): CInt = extern

}
