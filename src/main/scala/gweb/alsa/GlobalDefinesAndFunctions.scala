package gweb.alsa

import scala.scalanative.annotation.alwaysinline
import scala.scalanative.unsafe.*

/** Return 'x' argument as string.
  */
inline def _STIRNG(x: Any): String = s"$x"

/** Helper macro for SND_DLSYM_BUILD_VERSION.
  */
inline def __SND_DLSYM_VERSION(name: Any, version: Any): String =
  s"_$name$version"

/** Appends the build version to the name of a versioned dynamic symbol.
  */
inline def SND_DLSYM_BUILD_VERSION(name: Any, version: Any): String =
  __SND_DLSYM_VERSION(name, version)

inline def SND_DLSYM_VERSION(version: Any): String = _STIRNG(version)

/** alloca helper macro.
  */
@alwaysinline
@inline
inline def __snd_alloca[T](using zone: Zone): Ptr[T] = {
  stackalloc[T]()
  ???
}

/** Global defines and functions
  *
  * The ALSA library implementation uses these macros and functions. Most
  * applications probably do not need them.
  */
@link("asound")
@extern
object GlobalDefinesAndFunctions {

  /** Internal structure for an async notification client handler.
    *
    * The ALSA library uses a pointer to this structure as a handle to an async
    * notification object. Applications don't access its contents directly.
    *
    * typedef struct _snd_async_handler snd_async_handler_t
    */
  opaque type snd_async_handler_t = CStruct0

  /** Async notification callback.
    *
    * See the snd_async_add_handler function for details.
    *
    * typedef void(* snd_async_callback_t) (snd_async_handler_t *handler)
    */
  type snd_async_callback_t = CFuncPtr1[
    /** @param handler */
    Ptr[snd_async_handler_t],
    Unit
  ]

  /** Timestamp
    */
  type snd_timestamp_t = scala.scalanative.posix.sys.time.timeval

  /** Hi-res timestamp
    */
  type snd_htimestamp_t = scala.scalanative.posix.time.timespec

  /** Returns the ALSA sound library version in ASCII format.
    *
    * @return
    *   The ASCII description of the used ALSA sound library.
    */
  def snd_asoundlib_version(): Ptr[CChar] = extern

  /** Compose the dynamic path.
    *
    * @param path
    *   Returned path (string)
    * @param path_len
    *   Returned path max size (with trailing zero)
    * @param name
    *   Plugin name (relative)
    * @return
    *   Zero on success, otherwise a negative error code
    */
  def snd_dlpath(path: Ptr[CChar], path_len: CSize, name: Ptr[CChar]): CInt =
    extern

  /** Opens a dynamic library - ALSA wrapper for dlopen.
    *
    * @param name
    *   name of the library, similar to dlopen.
    * @param mode
    *   mode flags, similar to dlopen.
    * @param errbuf
    *   a string buffer for the error message dlerror.
    * @param errbuflen
    *   a length of the string buffer for the error message.
    *
    * @return
    *   Library handle if successful, otherwise NULL.
    *
    * This function can emulate dynamic linking for the static build of the
    * alsa-lib library. In that case, name is set to NULL.
    */
  def snd_dlopen(
      name: Ptr[CChar],
      mode: CInt,
      errbuf: Ptr[CChar],
      errbuflen: CSize
  ): CVoidPtr = extern

  /** Resolves a symbol from a dynamic library - ALSA wrapper for dlsym.
    *
    * @param handle
    *   Library handle, similar to dlsym.
    * @param name
    *   Symbol name.
    * @param version
    *   Version of the symbol.
    *
    * This function can emulate dynamic linking for the static build of the
    * alsa-lib library.
    *
    * This special version of the dlsym function checks also the version of the
    * symbol. A versioned symbol should be defined using the
    * SND_DLSYM_BUILD_VERSION macro.
    */
  def snd_dlsym(
      handle: CVoidPtr,
      name: Ptr[CChar],
      version: Ptr[CChar]
  ): CVoidPtr = extern

  /** Closes a dynamic library - ALSA wrapper for dlclose.
    *
    * @param handle
    *   Library handle, similar to dlclose.
    * @return
    *   Zero if successful, otherwise an error code.
    *
    * This function can emulate dynamic linking for the static build of the
    * alsa-lib library.
    */
  def snd_dlclose(handle: CVoidPtr): CInt = extern

  /** Registers an async handler.
    *
    * @param handler
    *   The function puts the pointer to the new async handler object at the
    *   address specified by handler.
    * @param fd
    *   The file descriptor to be associated with the callback.
    * @param callback
    *   The async callback function.
    * @param private_data
    *   Private data for the async callback function.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function associates the callback function with the given file, and
    * saves this association in a snd_async_handler_t object.
    *
    * Whenever the SIGIO signal is raised for the file fd, the callback function
    * will be called with its parameter pointing to the async handler object
    * returned by this function.
    *
    * The ALSA sigaction handler for the SIGIO signal automatically multiplexes
    * the notifications to the registered async callbacks. However, the
    * application is responsible for instructing the device driver to generate
    * the SIGIO signal.
    *
    * The SIGIO signal may have been replaced with another signal, see
    * snd_async_handler_get_signo.
    *
    * When the async handler isn't needed anymore, you must delete it with
    * snd_async_del_handler.
    */
  def snd_async_add_handler(
      handler: Ptr[Ptr[snd_async_handler_t]],
      fd: CInt,
      callback: snd_async_callback_t,
      private_data: CVoidPtr
  ): CInt = extern

  /** Deletes an async handler.
    *
    * @param handler
    *   Handle of the async handler to delete.
    *
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  def snd_async_del_handler(handler: Ptr[snd_async_handler_t]): CInt = extern

  /** Returns the file descriptor assigned to an async handler.
    *
    * @param handler
    *   Handle to an async handler.
    *
    * @return
    *   The file descriptor if successful, otherwise a negative error code.
    */
  def snd_async_handler_get_fd(handler: Ptr[snd_async_handler_t]): CInt = extern

  /** Returns the signal number assigned to an async handler.
    *
    * @param handler
    *   Handle to an async handler.
    *
    * @return
    *   The signal number if successful, otherwise a negative error code.
    *
    * The signal number for async handlers usually is SIGIO, but wizards can
    * redefine it to a realtime signal when compiling the ALSA library.
    */
  def snd_async_handler_get_signo(handler: Ptr[snd_async_handler_t]): CInt =
    extern

  /** Returns the private data assigned to an async handler.
    *
    * @param handler
    *   Handle to an async handler.
    *
    * @return
    *   The private_data value registered with the async handler.
    */
  def snd_async_handler_get_callback_private(
      handler: Ptr[snd_async_handler_t]
  ): CVoidPtr = extern

  /** Create a shm area record.
    *
    * @param shmid
    *   IPC SHM ID
    * @param ptr
    *   the shared area pointer
    *
    * @return
    *   The allocated shm area record, NULL if fail
    *
    * Allocates a shared area record with the given SHM ID and pointer. The
    * record has a reference counter, which is initialized to 1 by this
    * function.
    */
  def snd_shm_area_create(shmid: CInt, ptr: CVoidPtr): Ptr[snd_shm_area] =
    extern

  /** Increase the reference counter of shm area record.
    *
    * @param area
    *   shm area record
    *
    * @return
    *   the shm area record (identical with the argument)
    *
    * Increases the reference counter of the given shared area record.
    */
  def snd_shm_area_share(area: Ptr[snd_shm_area]): Ptr[snd_shm_area] = extern

  /** Release the shared area record.
    *
    * @param area
    *   the shared are record
    *
    * @return
    *   0 if successful, or a negative error code
    *
    * Decreases the reference counter of the given shared area record, and
    * releases the resources automaticall if it reaches to 0.
    */
  def snd_shm_area_destroy(area: Ptr[snd_shm_area]): CInt = extern

  /** Get the full file name.
    *
    * @param file
    *   The file name string to parse
    * @param result
    *   The pointer to store the resultant file name
    *
    * @return
    *   0 if successful, or a negative error code
    *
    * Parses the given file name with POSIX-Shell-like expansion and stores the
    * first matchine one. The returned string is strdup'ed.
    */
  def snd_user_file(file: Ptr[CChar], result: Ptr[Ptr[CChar]]): CInt = extern

}
