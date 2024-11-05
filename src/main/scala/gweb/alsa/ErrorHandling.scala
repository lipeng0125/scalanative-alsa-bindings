package gweb.alsa

import scala.scalanative.unsafe.*

/** Error handling macros and functions.
  */
@link("asound")
@extern
object ErrorHandling {

  /** Lower boundary of sound error codes.
    */
  inline val SND_ERROR_BEGIN = 500000

  /** Kernel/library protocols are not compatible.
    */
  inline val SND_ERROR_INCOMPATIBLE_VERSION = SND_ERROR_BEGIN + 0

  /** Lisp encountered an error during acall.
    */
  inline val SND_ERROR_ALISP_NIL = SND_ERROR_BEGIN + 1

  /** Error handler callback.
    *
    * A function of this type is called by the ALSA library when an error
    * occurs. This function usually shows the message on the screen, and/or logs
    * it.
    *
    * typedef void(* snd_lib_error_handler_t) (const char *file, int line, const
    * char *function, int err, const char *fmt,...)
    */
  type snd_lib_error_handler_t =
    CFuncPtr6[
      /** @param file Source file name. */
      Ptr[CChar],
      /** @param line Line number. */
      CInt,
      /** @param function Function name. */
      Ptr[CChar],
      /** @param err Value of errno, or 0 if not relevant. */
      CInt,
      /** @param fmt printf(3) format. */
      Ptr[CChar],
      /** @param ... printf(3) arguments. */
      CVarArgList, // todo 应该是 Any*
      /** */
      Unit
    ]

  /** Returns the message for an error code.
    *
    * @param errnum
    *   The error code number, which must be a system error code or an ALSA
    *   error code.
    *
    * @return
    *   The ASCII description of the given numeric error code.
    */
  @name("snd_strerror")
  def snd_strerror(errnum: CInt): Ptr[CChar] = extern

  /** Sets the error handler.
    *
    * @param handler
    *   The pointer to the new error handler function.
    *
    * This function sets a new error handler, or (if handler is NULL) the
    * default one which prints the error messages to stderr.
    */
  @name("snd_lib_error_set_handler")
  def snd_lib_error_set_handler(handler: snd_lib_error_handler_t): CInt = extern

  /** Pointer to the error handler function. For internal use only.
    */
  @name("snd_lib_error")
  var snd_lib_error: snd_lib_error_handler_t = extern

}
