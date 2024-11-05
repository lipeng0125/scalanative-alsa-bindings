package gweb.alsa.PCMInterface

import scala.scalanative.unsafe.*
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_scope_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_t
import gweb.alsa.PCMInterface.PCMInterface.snd_pcm_uframes_t

/** See the PCM (digital audio) interface page for more details.
  */
@link("asound")
@extern
object ScopePluginExtension {

  /** SND_PCM_TYPE_METER scope functions
    */
  type snd_pcm_scope_ops_t =
    CStruct7[
      /** Enable and prepare it using current params.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        CInt
      ], // enable
      /** Disable.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        Unit
      ], // disable
      /** PCM has been started.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        Unit
      ], // start
      /** PCM has been stopped.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        Unit
      ], // stop
      /** New frames are present.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        Unit
      ], // update
      /** Reset status.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        Unit
      ], // reset
      /** PCM is closing.
        */
      CFuncPtr1[
        /** @param scope
          *   scope handle
          */
        Ptr[snd_pcm_scope_t],
        Unit
      ] //  close
    ]

  /** Get meter buffer size from a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @return
    *   meter buffer size in frames
    */
  @name("snd_pcm_meter_get_bufsize")
  def snd_pcm_meter_get_bufsize(pcm: Ptr[snd_pcm_t]): snd_pcm_uframes_t = extern

  /** Get meter channels from a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @return
    *   meter channels count
    */
  @name("snd_pcm_meter_get_channels")
  def snd_pcm_meter_get_channels(pcm: Ptr[snd_pcm_t]): CUnsignedInt = extern

  /** Get meter rate from a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @return
    *   approximate rate
    */
  @name("snd_pcm_meter_get_rate")
  def snd_pcm_meter_get_rate(pcm: Ptr[snd_pcm_t]): CUnsignedInt = extern

  /** Get meter "now" frame pointer from a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @return
    *   "now" frame pointer in frames (0 ... boundary - 1) see
    *   snd_pcm_meter_get_boundary
    */
  @name("snd_pcm_meter_get_now")
  def snd_pcm_meter_get_now(pcm: Ptr[snd_pcm_t]): snd_pcm_uframes_t = extern

  /** Get boundary for frame pointers from a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @return
    *   boundary in frames
    */
  @name("snd_pcm_meter_get_boundary")
  def snd_pcm_meter_get_boundary(pcm: Ptr[snd_pcm_t]): snd_pcm_uframes_t =
    extern

  /** Add a scope to a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @param scope
    *   Scope handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_pcm_meter_add_scope")
  def snd_pcm_meter_add_scope(
      pcm: Ptr[snd_pcm_t],
      scope: Ptr[snd_pcm_scope_t]
  ): CInt = extern

  /** Search an installed scope inside a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   PCM handle
    * @param name
    *   scope name
    * @return
    *   pointer to found scope or NULL if none is found
    */
  @name("snd_pcm_meter_search_scope")
  def snd_pcm_meter_search_scope(
      pcm: Ptr[snd_pcm_t],
      name: Ptr[CChar]
  ): Ptr[snd_pcm_scope_t] = extern

  /** allocate an invalid snd_pcm_scope_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_pcm_scope_malloc")
  def snd_pcm_scope_malloc(ptr: Ptr[Ptr[snd_pcm_scope_t]]): CInt = extern

  /** Set callbacks for a SND_PCM_TYPE_METER PCM scope.
    *
    * @param scope
    *   PCM meter scope
    * @param val
    *   callbacks
    */
  @name("snd_pcm_scope_set_ops")
  def snd_pcm_scope_set_ops(
      scope: Ptr[snd_pcm_scope_t],
      `val`: Ptr[snd_pcm_scope_ops_t]
  ): Unit = extern

  /** Set name of a SND_PCM_TYPE_METER PCM scope.
    *
    * @param scope
    *   PCM meter scope
    * @param val
    *   scope name
    */
  @name("snd_pcm_scope_set_name")
  def snd_pcm_scope_set_name(
      scope: Ptr[snd_pcm_scope_t],
      `val`: Ptr[CChar]
  ): Unit = extern

  /** Get name of a SND_PCM_TYPE_METER PCM scope.
    *
    * @param scope
    *   PCM meter scope
    * @return
    *   scope name
    */
  @name("snd_pcm_scope_get_name")
  def snd_pcm_scope_get_name(scope: Ptr[snd_pcm_scope_t]): Ptr[CChar] = extern

  /** Get callbacks private value for a SND_PCM_TYPE_METER PCM scope.
    *
    * @param scope
    *   PCM meter scope
    * @return
    *   Private data value
    */
  @name("snd_pcm_scope_get_callback_private")
  def snd_pcm_scope_get_callback_private(
      scope: Ptr[snd_pcm_scope_t]
  ): CVoidPtr = extern

  /** Get callbacks private value for a SND_PCM_TYPE_METER PCM scope.
    *
    * @param scope
    *   PCM meter scope
    * @param val
    *   Private data value
    */
  @name("snd_pcm_scope_set_callback_private")
  def snd_pcm_scope_set_callback_private(
      scope: Ptr[snd_pcm_scope_t],
      `val`: CVoidPtr
  ): Unit = extern

  /** Add a s16 pseudo scope to a SND_PCM_TYPE_METER PCM.
    *
    * @param pcm
    *   The pcm handle
    * @param name
    *   Scope name
    * @param scopep
    *   Pointer to newly created and added scope
    * @return
    *   0 on success otherwise a negative error code
    *
    * s16 pseudo scope convert SND_PCM_TYPE_METER PCM frames in CPU endian 16
    * bit frames for use with other scopes. Don't forget to insert it before and
    * to not insert it more time (see snd_pcm_meter_search_scope)
    */
  @name("snd_pcm_scope_s16_open")
  def snd_pcm_scope_s16_open(
      pcm: Ptr[snd_pcm_t],
      name: Ptr[CChar],
      scopep: Ptr[Ptr[snd_pcm_scope_t]]
  ): CInt = extern

  /** Get s16 pseudo scope frames buffer for a channel.
    *
    * @param scope
    *   s16 pseudo scope handle
    * @param channel
    *   Channel
    * @return
    *   Pointer to channel buffer
    */
  @name("snd_pcm_scope_s16_get_channel_buffer")
  def snd_pcm_scope_s16_get_channel_buffer(
      scope: Ptr[snd_pcm_scope_t],
      channel: CUnsignedInt
  ): Ptr[CShort] = extern

}
