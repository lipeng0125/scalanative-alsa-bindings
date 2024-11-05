package gweb.alsa.MixerInterface

import scala.scalanative.unsafe.*
import gweb.alsa.ControlInterface.HighLevelControlInterface.snd_hctl_elem_t

import gweb.alsa.ControlInterface.HighLevelControlInterface.snd_hctl_t
import gweb.alsa.pollfd
import gweb.alsa.Iota

/** The mixer interface.
  */
@link("asound")
@extern
object MixerInterface {

  /** Mixer handle
    */
  type snd_mixer_t = CStruct0

  /** Mixer elements class handle
    */
  type snd_mixer_class_t = CStruct0

  /** Mixer element handle
    */
  type snd_mixer_elem_t = CStruct0

  /** Mixer callback function.
    */
  type snd_mixer_callback_t =
    CFuncPtr3[
      /** @param ctl
        *   Mixer handle
        */
      Ptr[snd_mixer_t],
      /** @param mask
        *   event mask
        */
      CUnsignedInt,
      /** @param elem
        *   related mixer element (if any)
        */
      Ptr[snd_mixer_elem_t],
      /** @return
        *   0 on success otherwise a negative error code
        */
      CInt
    ]

  /** Mixer element callback function.
    */
  type snd_mixer_elem_callback_t =
    CFuncPtr2[
      /** @param elem
        *   Mixer element
        */
      Ptr[snd_mixer_elem_t],
      /** @param mask
        *   event mask
        */
      CUnsignedInt,
      /** @return
        *   0 on success otherwise a negative error code
        */
      CInt
    ]

  /** Compare function for sorting mixer elements.
    */
  type snd_mixer_compare_t =
    CFuncPtr2[
      /** @param e1
        *   First element
        */
      Ptr[snd_mixer_elem_t],
      /** @param e2
        *   Second element
        */
      Ptr[snd_mixer_elem_t],
      /** @return
        *   -1 if e1 < e2, 0 if e1 == e2, 1 if e1 > e2
        */
      CInt
    ]

  /** Event callback for the mixer class.
    */
  type snd_mixer_event_t = CFuncPtr4[
    /** @param class_
      *   Mixer class
      */
    Ptr[snd_mixer_class_t],
    /** @param mask
      *   Event mask (SND_CTL_EVENT_*)
      */
    CUnsignedInt,
    /** @param helem
      *   HCTL element which invoked the event
      */
    Ptr[
      snd_hctl_elem_t
    ],
    /** @param melem
      *   Mixer element associated to HCTL element
      */
    Ptr[snd_mixer_elem_t],
    /** @return
      *   zero if success, otherwise a negative error value
      */
    CInt
  ]

  /** Mixer element type
    */
  class snd_mixer_elem_type_t private (val value: CInt) extends AnyVal

  object snd_mixer_elem_type_t extends Iota {

    /** */
    val SND_MIXER_ELEM_SIMPLE = iota

    /** */
    val SND_MIXER_ELEM_LAST = iota(SND_MIXER_ELEM_SIMPLE)

  }

  /** Opens an empty mixer.
    *
    * @param mixerp
    *   Returned mixer handle
    * @param mode
    *   Open mode
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_open")
  def snd_mixer_open(mixer: Ptr[Ptr[snd_mixer_t]], mode: CInt): CInt = extern

  /** Close a mixer and free all related resources.
    *
    * @param mixer
    *   Mixer handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_close")
  def snd_mixer_close(mixer: Ptr[snd_mixer_t]): CInt = extern

  /** get first element for a mixer
    *
    * @param mixer
    *   Mixer handle
    * @return
    *   pointer to first element
    */
  @name("snd_mixer_first_elem")
  def snd_mixer_first_elem(mixer: Ptr[snd_mixer_t]): Ptr[snd_mixer_elem_t] =
    extern

  /** get last element for a mixer
    *
    * @param mixer
    *   Mixer handle
    * @return
    *   pointer to last element
    */
  @name("snd_mixer_last_elem")
  def snd_mixer_last_elem(mixer: Ptr[snd_mixer_t]): Ptr[snd_mixer_elem_t] =
    extern

  /** Handle pending mixer events invoking callbacks.
    *
    * @param mixer
    *   Mixer handle
    * @return
    *   Number of events that occured on success, otherwise a negative error
    *   code on failure
    */
  @name("snd_mixer_handle_events")
  def snd_mixer_handle_events(mixer: Ptr[snd_mixer_t]): CInt = extern

  /** Attach an HCTL specified with the CTL device name to an opened mixer.
    *
    * @param mixer
    *   Mixer handle
    * @param name
    *   HCTL name (see snd_hctl_open)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_attach")
  def snd_mixer_attach(mixer: Ptr[snd_mixer_t], name: Ptr[CChar]): CInt = extern

  /** Attach an HCTL to an opened mixer.
    *
    * @param mixer
    *   Mixer handle
    * @param hctl
    *   the HCTL to be attached
    * @return
    *   0 on success otherwise a negative error code
    *
    * Upon error, this function closes the given hctl handle automatically.
    */
  @name("snd_mixer_attach_hctl")
  def snd_mixer_attach_hctl(
      mixer: Ptr[snd_mixer_t],
      hctl: Ptr[snd_hctl_t]
  ): CInt = extern

  /** Detach a previously attached HCTL to an opened mixer freeing all related
    * resources.
    *
    * @param mixer
    *   Mixer handle
    * @param name
    *   HCTL previously attached
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_detach")
  def snd_mixer_detach(mixer: Ptr[snd_mixer_t], name: Ptr[CChar]): CInt = extern

  /** Detach a previously attached HCTL to an opened mixer freeing all related
    * resources.
    *
    * @param mixer
    *   Mixer handle
    * @param hctl
    *   HCTL previously attached
    * @return
    *   0 on success otherwise a negative error code
    *
    * Note: The hctl handle is not closed!
    */
  @name("snd_mixer_detach_hctl")
  def snd_mixer_detach_hctl(
      mixer: Ptr[snd_mixer_t],
      hctl: Ptr[snd_hctl_t]
  ): CInt = extern

  /** Obtain a HCTL pointer associated to given name.
    *
    * @param mixer
    *   Mixer handle
    * @param name
    *   HCTL previously attached
    * @param hctl
    *   HCTL pointer
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_get_hctl")
  def snd_mixer_get_hctl(
      mixer: Ptr[snd_mixer_t],
      name: Ptr[CChar],
      hctl: Ptr[Ptr[snd_hctl_t]]
  ): CInt = extern

  /** get count of poll descriptors for mixer handle
    *
    * @param mixer
    *   Mixer handle
    * @return
    *   count of poll descriptors
    */
  @name("snd_mixer_poll_descriptors_count")
  def snd_mixer_poll_descriptors_count(mixer: Ptr[snd_mixer_t]): CInt = extern

  /** get poll descriptors
    *
    * @param mixer
    *   Mixer handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    */
  @name("snd_mixer_poll_descriptors")
  def snd_mixer_poll_descriptors(
      mixer: Ptr[snd_mixer_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param mixer
    *   Mixer handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_mixer_poll_descriptors_revents")
  def snd_mixer_poll_descriptors_revents(
      mixer: Ptr[snd_mixer_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** Load a mixer elements.
    *
    * @param mixer
    *   Mixer handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_load")
  def snd_mixer_load(mixer: Ptr[snd_mixer_t]): CInt = extern

  /** Unload all mixer elements and free all related resources.
    *
    * @param mixer
    *   Mixer handle
    */
  @name("snd_mixer_free")
  def snd_mixer_free(mixer: Ptr[snd_mixer_t]): Unit = extern

  /** Wait for a mixer to become ready (i.e. at least one event pending)
    *
    * @param mixer
    *   Mixer handle
    * @param timeout
    *   maximum time in milliseconds to wait
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_mixer_wait")
  def snd_mixer_wait(mixer: Ptr[snd_mixer_t], timeout: CInt): CInt = extern

  /** Change mixer compare function and reorder elements.
    *
    * @param mixer
    *   Mixer handle
    * @param compare
    *   Element compare function
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_mixer_set_compare")
  def snd_mixer_set_compare(
      mixer: Ptr[snd_mixer_t],
      msort: snd_mixer_compare_t
  ): CInt = extern

  /** Set callback function for a mixer.
    *
    * @param obj
    *   mixer handle
    * @param val
    *   callback function
    */
  @name("snd_mixer_set_callback")
  def snd_mixer_set_callback(
      obj: Ptr[snd_mixer_t],
      `val`: snd_mixer_callback_t
  ): Unit = extern

  /** Get callback private value for a mixer.
    *
    * @param mixer
    *   mixer handle
    * @return
    *   callback private value
    */
  @name("snd_mixer_get_callback_private")
  def snd_mixer_get_callback_private(obj: Ptr[snd_mixer_t]): CVoidPtr = extern

  /** Set callback private value for a mixer.
    *
    * @param mixer
    *   mixer handle
    * @param val
    *   callback private value
    */
  @name("snd_mixer_set_callback_private")
  def snd_mixer_set_callback_private(
      obj: Ptr[snd_mixer_t],
      `val`: CVoidPtr
  ): Unit = extern

  /** Get elements count for a mixer.
    *
    * @param mixer
    *   mixer handle
    * @return
    *   elements count
    */
  @name("snd_mixer_get_count")
  def snd_mixer_get_count(obj: Ptr[snd_mixer_t]): CUnsignedInt = extern

  /** Unregister mixer element class and remove all its elements.
    *
    * @param class
    *   Mixer element class
    * @return
    *   0 on success otherwise a negative error code
    *
    * Note that the class structure is also deallocated!
    */
  @name("snd_mixer_class_unregister")
  def snd_mixer_class_unregister(clss: Ptr[snd_mixer_class_t]): CInt = extern

  /** get next mixer element
    *
    * @param elem
    *   mixer element
    * @return
    *   pointer to next element
    */
  @name("snd_mixer_elem_next")
  def snd_mixer_elem_next(elem: Ptr[snd_mixer_elem_t]): Ptr[snd_mixer_elem_t] =
    extern

  /** get previous mixer element
    *
    * @param elem
    *   mixer element
    * @return
    *   pointer to previous element
    */
  @name("snd_mixer_elem_prev")
  def snd_mixer_elem_prev(elem: Ptr[snd_mixer_elem_t]): Ptr[snd_mixer_elem_t] =
    extern

  /** Set callback function for a mixer element.
    *
    * @param mixer
    *   mixer element
    * @param val
    *   callback function
    */
  @name("snd_mixer_elem_set_callback")
  def snd_mixer_elem_set_callback(
      obj: Ptr[snd_mixer_elem_t],
      `val`: snd_mixer_elem_callback_t
  ): Unit = extern

  /** Get callback private value for a mixer element.
    *
    * @param mixer
    *   mixer element
    * @return
    *   callback private value
    */
  @name("snd_mixer_elem_get_callback_private")
  def snd_mixer_elem_get_callback_private(
      obj: Ptr[snd_mixer_elem_t]
  ): CVoidPtr = extern

  /** Set callback private value for a mixer element.
    *
    * @param mixer
    *   mixer element
    * @param val
    *   callback private value
    */
  @name("snd_mixer_elem_set_callback_private")
  def snd_mixer_elem_set_callback_private(
      obj: Ptr[snd_mixer_elem_t],
      `val`: CVoidPtr
  ): Unit = extern

  /** Get type for a mixer element.
    *
    * @param mixer
    *   mixer element
    * @return
    *   mixer element type
    */
  @name("snd_mixer_elem_get_type")
  def snd_mixer_elem_get_type(
      obj: Ptr[snd_mixer_elem_t]
  ): snd_mixer_elem_type_t = extern

  /** Register mixer element class.
    *
    * @param class
    *   Mixer element class
    * @param mixer
    *   Mixer handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_class_register")
  def snd_mixer_class_register(
      class_ : Ptr[snd_mixer_class_t],
      mixer: Ptr[snd_mixer_t]
  ): CInt = extern

  /** Allocate a new mixer element.
    *
    * @param elem
    *   Returned mixer element
    * @param type
    *   Mixer element type
    * @param compare_weight
    *   Mixer element compare weight
    * @param private_data
    *   Private data
    * @param private_free
    *   Private data free callback
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_new")
  def snd_mixer_elem_new(
      elem: Ptr[Ptr[snd_mixer_elem_t]],
      `type`: snd_mixer_elem_type_t,
      compare_weight: CInt,
      private_data: CVoidPtr,
      private_free: CFuncPtr1[Ptr[snd_mixer_elem_t], Unit]
  ): CInt = extern

  /** Add an element for a registered mixer element class.
    *
    * @param elem
    *   Mixer element
    * @param class
    *   Mixer element class
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_add")
  def snd_mixer_elem_add(
      elem: Ptr[snd_mixer_elem_t],
      class_ : Ptr[snd_mixer_class_t]
  ): CInt = extern

  /** Remove a mixer element.
    *
    * @param elem
    *   Mixer element
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_remove")
  def snd_mixer_elem_remove(elem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** Free a mixer element.
    *
    * @param elem
    *   Mixer element
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_free")
  def snd_mixer_elem_free(elem: Ptr[snd_mixer_elem_t]): Unit = extern

  /** Mixer element informations are changed.
    *
    * @param elem
    *   Mixer element
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_info")
  def snd_mixer_elem_info(elem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** Mixer element values is changed.
    *
    * @param elem
    *   Mixer element
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_value")
  def snd_mixer_elem_value(elem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** Attach an HCTL element to a mixer element.
    *
    * @param melem
    *   Mixer element
    * @param helem
    *   HCTL element
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    *
    * The implementation of mixer class typically calls it at
    * SND_CTL_EVENT_MASK_ADD event. Once attaching, the implementation should
    * make sure to detach it by call of snd_mixer_elem_detach() at
    * SND_CTL_EVENT_MASK_REMOVE event. Unless detaching, mixer API internal hits
    * assertion due to unsatisfied postcondition after the event.
    */
  @name("snd_mixer_elem_attach")
  def snd_mixer_elem_attach(
      melem: Ptr[snd_mixer_elem_t],
      helem: Ptr[snd_hctl_elem_t]
  ): CInt = extern

  /** Detach an HCTL element from a mixer element.
    *
    * @param melem
    *   Mixer element
    * @param helem
    *   HCTL element
    * @return
    *   0 on success otherwise a negative error code
    *
    * For use by mixer element class specific code.
    *
    * The implementation of mixer class typically calls it at
    * SND_CTL_EVENT_MASK_REMOVE event for attached mixer element at
    * SND_CTL_EVENT_MASK_ADD. Unless detaching, mixer API internal hits
    * assertion due to unsatisfied postcondition after the event.
    */
  @name("snd_mixer_elem_detach")
  def snd_mixer_elem_detach(
      melem: Ptr[snd_mixer_elem_t],
      helem: Ptr[snd_hctl_elem_t]
  ): CInt = extern

  /** Return true if a mixer element does not contain any HCTL elements.
    *
    * @param melem
    *   Mixer element
    * @return
    *   0 if not empty, 1 if empty
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_empty")
  def snd_mixer_elem_empty(melem: Ptr[snd_mixer_elem_t]): CInt = extern

  /** Get private data associated to give mixer element.
    *
    * @param elem
    *   Mixer element
    * @return
    *   private data
    *
    * For use by mixer element class specific code.
    */
  @name("snd_mixer_elem_get_private")
  def snd_mixer_elem_get_private(melem: Ptr[snd_mixer_elem_t]): CVoidPtr =
    extern

  /** get size of snd_mixer_class_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_mixer_class_sizeof")
  def snd_mixer_class_sizeof(): CSize = extern

  /** allocate an invalid snd_mixer_class_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_mixer_class_malloc")
  def snd_mixer_class_malloc(ptr: Ptr[Ptr[snd_mixer_class_t]]): CInt = extern

  /** frees a previously allocated snd_mixer_class_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_mixer_class_free")
  def snd_mixer_class_free(obj: Ptr[snd_mixer_class_t]): Unit = extern

  /** copy one snd_mixer_class_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_mixer_class_copy")
  def snd_mixer_class_copy(
      dst: Ptr[snd_mixer_class_t],
      src: Ptr[snd_mixer_class_t]
  ): Unit = extern

  /** Get a mixer associated to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @return
    *   mixer pointer
    */
  @name("snd_mixer_class_get_mixer")
  def snd_mixer_class_get_mixer(
      class_ : Ptr[snd_mixer_class_t]
  ): Ptr[snd_mixer_t] = extern

  /** Get mixer event callback associated to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @return
    *   event callback pointer
    */
  @name("snd_mixer_class_get_event")
  def snd_mixer_class_get_event(
      class_ : Ptr[snd_mixer_class_t]
  ): snd_mixer_event_t = extern

  /** Get mixer private data associated to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @return
    *   event callback pointer
    */
  @name("snd_mixer_class_get_private")
  def snd_mixer_class_get_private(class_ : Ptr[snd_mixer_class_t]): CVoidPtr =
    extern

  /** Get mixer compare callback associated to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @return
    *   event callback pointer
    */
  @name("snd_mixer_class_get_compare")
  def snd_mixer_class_get_compare(
      class_ : Ptr[snd_mixer_class_t]
  ): snd_mixer_compare_t = extern

  /** Set mixer event callback to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @param event
    *   Event callback
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_mixer_class_set_event")
  def snd_mixer_class_set_event(
      class_ : Ptr[snd_mixer_class_t],
      event: snd_mixer_event_t
  ): CInt = extern

  /** Set mixer private data to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @param private_data
    *   class private data
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_mixer_class_set_private")
  def snd_mixer_class_set_private(
      class_ : Ptr[snd_mixer_class_t],
      private_data: CVoidPtr
  ): CInt = extern

  /** Set mixer private data free callback to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @param private_free
    *   Mixer class private data free callback
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_mixer_class_set_private_free")
  def snd_mixer_class_set_private_free(
      class_ : Ptr[snd_mixer_class_t],
      private_free: CFuncPtr1[Ptr[snd_mixer_class_t], Unit]
  ): CInt = extern

  /** Set mixer compare callback to given mixer class.
    *
    * @param obj
    *   Mixer simple class identifier
    * @param compare
    *   the compare callback to be used
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_mixer_class_set_compare")
  def snd_mixer_class_set_compare(
      class_ : Ptr[snd_mixer_class_t],
      compare: snd_mixer_compare_t
  ): CInt = extern

}
