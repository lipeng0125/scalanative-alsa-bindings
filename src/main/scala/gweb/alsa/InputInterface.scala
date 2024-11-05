package gweb.alsa

import scala.scalanative.unsafe.*

import scala.scalanative.libc.stdio.FILE

/** The input functions present an interface similar to the stdio functions on
  * top of different underlying input sources.
  *
  * The snd_config_load function uses such an input handle to be able to load
  * configurations not only from standard files but also from other sources,
  * e.g. from memory buffers.
  */
@link("asound")
@extern
object InputInterface {

  /** Internal structure for an input object.
    *
    * The ALSA library uses a pointer to this structure as a handle to an input
    * object. Applications don't access its contents directly.
    *
    * typedef struct _snd_input snd_input_t
    */
  opaque type snd_input_t = CStruct0

  /** Input type.
    */
  class snd_input_type_t private (val value: CInt) extends AnyVal

  object snd_input_type_t extends Iota {

    /** Input from a stdio stream.
      */
    val SND_INPUT_STDIO: CInt = iota

    /** Input from a memory buffer.
      */
    val SND_INPUT_BUFFER: CInt = iota
  }

  /** Creates a new input object reading from a file.
    *
    * @param inputp
    *   The functions puts the pointer to the new input object at the address
    *   specified by inputp.
    * @param file
    *   The name of the file to read from.
    * @param mode
    *   The open mode, like fopen(3).
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_input_stdio_open")
  def snd_input_stdio_open(
      inputp: Ptr[Ptr[snd_input_t]],
      file: Ptr[Char],
      mode: Ptr[Char]
  ): CInt = extern

  /** Creates a new input object using an existing stdio FILE pointer.
    *
    * @param inputp
    *   The function puts the pointer to the new input object at the address
    *   specified by inputp.
    * @param fp
    *   The FILE pointer to read from. Reading begins at the current file
    *   position.
    * @param _close
    *   Close flag. Set this to 1 if snd_input_close should close fp by calling
    *   fclose.
    *
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_input_stdio_attach")
  def snd_input_stdio_attach(
      inputp: Ptr[Ptr[snd_input_t]],
      fp: Ptr[FILE],
      _close: CInt
  ): CInt = extern

  /** Creates a new input object from a memory buffer.
    *
    * @param inputp
    *   The function puts the pointer to the new input object at the address
    *   specified by inputp.
    * @param buffer
    *   Address of the input buffer.
    * @param size
    *   Size of the input buffer.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This functions creates a copy of the input buffer, so the application is
    * not required to preserve the buffer after this function has been called.
    */
  @name("snd_input_buffer_open")
  def snd_input_buffer_open(
      inputp: Ptr[Ptr[snd_input_t]],
      buffer: Ptr[Char],
      size: CSSize
  ): CInt = extern

  /** Closes an input handle.
    *
    * @param input
    *   The input handle to be closed.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_input_close")
  def snd_input_close(input: Ptr[snd_input_t]): CInt = extern

  /** Reads formatted input (like fscanf(3)) from an input handle.
    *
    * @param input
    *   The input handle.
    * @param format
    *   Format string in fscanf format.
    * @param args
    *   Other fscanf arguments.
    *
    * @return
    *   The number of input items assigned, or EOF.
    *
    * Bug: Reading from a memory buffer doesn't work.
    */
  @name("snd_input_scanf")
  def snd_input_scanf(
      input: Ptr[snd_input_t],
      format: Ptr[Char],
      args: Any*
  ): CInt = extern

  /** Reads a line from an input handle (like fgets(3)).
    *
    * @param input
    *   The input handle.
    * @param str
    *   Address of the destination buffer.
    * @param size
    *   The size of the destination buffer.
    *
    * @return
    *   Pointer to the buffer if successful, otherwise NULL.
    *
    * Like fgets, the returned string is zero-terminated, and contains the
    * new-line character '\n' if the line fits into the buffer.
    */
  @name("snd_input_gets")
  def snd_input_gets(
      input: Ptr[snd_input_t],
      str: Ptr[Char],
      size: CSSize
  ): Ptr[Char] = extern

  /** Reads a character from an input handle (like fgetc(3)).
    *
    * @param input
    *   The input handle.
    *
    * @return
    *   The character read, or EOF on end of file or error.
    */
  @name("snd_input_getc")
  def snd_input_getc(input: Ptr[snd_input_t]): CInt = extern

  /** Puts the last character read back to an input handle (like ungetc(3)).
    *
    * @param input
    *   The input handle.
    * @param c
    *   The character to push back.
    *
    * @return
    *   The character pushed back, or EOF on error.
    */
  @name("snd_input_ungetc")
  def snd_input_ungetc(input: Ptr[snd_input_t], c: CInt): CInt = extern

}
