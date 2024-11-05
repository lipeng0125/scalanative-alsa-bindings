package gweb.alsa.ControlInterface

import scala.scalanative.unsafe.*
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_t
import gweb.alsa.pollfd
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_elem_id_t
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_elem_info_t
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_elem_value_t
import gweb.alsa.ControlInterface.ControlInterface.snd_ctl_elem_iface_t

@link("asound")
@extern
object HighLevelControlInterface {

  /** HCTL element handle
    */
  opaque type snd_hctl_elem_t = CStruct0

  /** HCTL handle
    */
  opaque type snd_hctl_t = CStruct0

  /** Compare function for sorting HCTL elements.
    */
  type snd_hctl_compare_t =
    CFuncPtr2[
      /** @param e1
        *   First element
        */
      Ptr[snd_hctl_elem_t],
      /** @param e2
        *   Second element
        */
      Ptr[snd_hctl_elem_t],
      /** @return
        *   -1 if e1 < e2, 0 if e1 == e2, 1 if e1 > e2
        */
      CInt
    ]

  /** HCTL callback function.
    */
  type snd_hctl_callback_t =
    CFuncPtr3[
      /** @param hctl
        *   HCTL handle
        */
      Ptr[snd_hctl_t],
      /** @param mask
        *   event mask
        */
      CUnsignedInt,
      /** @param elem
        *   related HCTL element (if any)
        */
      Ptr[snd_hctl_elem_t],
      /** @return
        *   0 on success otherwise a negative error code
        */
      CInt
    ]

  /** HCTL element callback function.
    */
  type snd_hctl_elem_callback_t =
    CFuncPtr2[
      /** @param elem
        *   HCTL element
        */
      Ptr[snd_hctl_elem_t],
      /** @param mask
        *   event mask
        */
      CUnsignedInt,
      /** @retrun
        *   0 on success otherwise a negative error code
        */
      CInt
    ]

  /** A "don't care" fast compare functions that may be used with
    * snd_hctl_set_compare.
    *
    * @param c1
    *   First HCTL element
    * @param c2
    *   Second HCTL element
    * @return
    *   -1 if c1 < c2, 0 if c1 == c2, 1 if c1 > c2
    */
  @name("snd_hctl_compare_fast")
  def snd_hctl_compare_fast(
      c1: Ptr[snd_hctl_elem_t],
      c2: Ptr[snd_hctl_elem_t]
  ): CInt = extern

  /** Opens an HCTL.
    *
    * @param hctlp
    *   Returned HCTL handle
    * @param name
    *   ASCII identifier of the underlying CTL handle
    * @param mode
    *   Open mode (see SND_CTL_NONBLOCK, SND_CTL_ASYNC)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hctl_open")
  def snd_hctl_open(
      hctl: Ptr[Ptr[snd_hctl_t]],
      name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** Opens an HCTL.
    *
    * @param hctlp
    *   Returned HCTL handle
    * @param ctl
    *   underlying CTL handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hctl_open_ctl")
  def snd_hctl_open_ctl(
      hctlp: Ptr[Ptr[snd_hctl_t]],
      ctl: Ptr[snd_ctl_t]
  ): CInt = extern

  /** close HCTL handle
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified HCTL handle and frees all associated resources.
    */
  @name("snd_hctl_close")
  def snd_hctl_close(hctl: Ptr[snd_hctl_t]): CInt = extern

  /** set nonblock mode
    *
    * @param hctl
    *   HCTL handle
    * @param nonblock
    *   0 = block, 1 = nonblock mode
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hctl_nonblock")
  def snd_hctl_nonblock(hctl: Ptr[snd_hctl_t], nonblock: CInt): CInt = extern

  /** get count of poll descriptors for HCTL handle
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   count of poll descriptors
    */
  @name("snd_hctl_poll_descriptors_count")
  def snd_hctl_poll_descriptors_count(hctl: Ptr[snd_hctl_t]): CInt = extern

  /** get poll descriptors
    *
    * @param hctl
    *   HCTL handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    */
  @name("snd_hctl_poll_descriptors")
  def snd_hctl_poll_descriptors(
      hctl: Ptr[snd_hctl_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param hctl
    *   HCTL handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_hctl_poll_descriptors_revents")
  def snd_hctl_poll_descriptors_revents(
      ctl: Ptr[snd_hctl_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** Get number of loaded elements for an HCTL.
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   elements count
    */
  @name("snd_hctl_get_count")
  def snd_hctl_get_count(hctl: Ptr[snd_hctl_t]): CUnsignedInt = extern

  /** Change HCTL compare function and reorder elements.
    *
    * @param hctl
    *   HCTL handle
    * @param compare
    *   Element compare function
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hctl_set_compare")
  def snd_hctl_set_compare(
      hctl: Ptr[snd_hctl_t],
      hsort: snd_hctl_compare_t
  ): CInt = extern

  /** get first element for an HCTL
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   pointer to first element
    */
  @name("snd_hctl_first_elem")
  def snd_hctl_first_elem(hctl: Ptr[snd_hctl_t]): Ptr[snd_hctl_elem_t] = extern

  /** get last element for an HCTL
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   pointer to last element
    */
  @name("snd_hctl_last_elem")
  def snd_hctl_last_elem(hctl: Ptr[snd_hctl_t]): Ptr[snd_hctl_elem_t] = extern

  /** Search an HCTL element.
    *
    * @param hctl
    *   HCTL handle
    * @param id
    *   Element identifier
    * @return
    *   pointer to found HCTL element or NULL if it does not exists
    */
  @name("snd_hctl_find_elem")
  def snd_hctl_find_elem(
      hctl: Ptr[snd_hctl_t],
      id: Ptr[snd_ctl_elem_id_t]
  ): Ptr[snd_hctl_elem_t] = extern

  /** Set callback function for an HCTL.
    *
    * @param hctl
    *   HCTL handle
    * @param callback
    *   callback function
    */
  @name("snd_hctl_set_callback")
  def snd_hctl_set_callback(
      hctl: Ptr[snd_hctl_t],
      callback: snd_hctl_callback_t
  ): Unit = extern

  /** Set callback private value for an HCTL.
    *
    * @param hctl
    *   HCTL handle
    * @param callback_private
    *   callback private value
    */
  @name("snd_hctl_set_callback_private")
  def snd_hctl_set_callback_private(
      hctl: Ptr[snd_hctl_t],
      data: CVoidPtr
  ): Unit = extern

  /** Get callback private value for an HCTL.
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   callback private value
    */
  @name("snd_hctl_get_callback_private")
  def snd_hctl_get_callback_private(hctl: Ptr[snd_hctl_t]): CVoidPtr = extern

  /** Load an HCTL with all elements and sort them.
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hctl_load")
  def snd_hctl_load(hctl: Ptr[snd_hctl_t]): CInt = extern

  /** free HCTL loaded elements
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_hctl_free")
  def snd_hctl_free(hctl: Ptr[snd_hctl_t]): CInt = extern

  /** Handle pending HCTL events invoking callbacks.
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_hctl_handle_events")
  def snd_hctl_handle_events(hctl: Ptr[snd_hctl_t]): CInt = extern

  /** get identifier of HCTL handle
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   ascii identifier of HCTL handle
    *
    * Returns the ASCII identifier of given HCTL handle. It's the same
    * identifier specified in snd_hctl_open().
    */
  @name("snd_hctl_name")
  def snd_hctl_name(hctl: Ptr[snd_hctl_t]): Ptr[CChar] = extern

  /** Wait for a HCTL to become ready (i.e. at least one event pending)
    *
    * @param hctl
    *   HCTL handle
    * @param timeout
    *   maximum time in milliseconds to wait
    * @return
    *   a positive value on success otherwise a negative error code
    */
  @name("snd_hctl_wait")
  def snd_hctl_wait(hctl: Ptr[snd_hctl_t], timeout: CInt): CInt = extern

  /** Get a ctl handle associated to the given hctl handle.
    *
    * @param hctl
    *   HCTL handle
    * @return
    *   a ctl handle otherwise NULL
    */
  @name("snd_hctl_ctl")
  def snd_hctl_ctl(hctl: Ptr[snd_hctl_t]): Ptr[snd_ctl_t] = extern

  /** get next HCTL element
    *
    * @param elem
    *   HCTL element
    * @return
    *   pointer to next element
    */
  @name("snd_hctl_elem_next")
  def snd_hctl_elem_next(elem: Ptr[snd_hctl_elem_t]): Ptr[snd_hctl_elem_t] =
    extern

  /** get previous HCTL element
    *
    * @param elem
    *   HCTL element
    * @return
    *   pointer to previous element
    */
  @name("snd_hctl_elem_prev")
  def snd_hctl_elem_prev(elem: Ptr[snd_hctl_elem_t]): Ptr[snd_hctl_elem_t] =
    extern

  /** Get information for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @param info
    *   HCTL element information
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_hctl_elem_info")
  def snd_hctl_elem_info(
      elem: Ptr[snd_hctl_elem_t],
      info: Ptr[snd_ctl_elem_info_t]
  ): CInt = extern

  /** Get value for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @param value
    *   HCTL element value
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_hctl_elem_read")
  def snd_hctl_elem_read(
      elem: Ptr[snd_hctl_elem_t],
      value: Ptr[snd_ctl_elem_value_t]
  ): CInt = extern

  /** Set value for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @param value
    *   HCTL element value
    */
  @name("snd_hctl_elem_write")
  def snd_hctl_elem_write(
      elem: Ptr[snd_hctl_elem_t],
      value: Ptr[snd_ctl_elem_value_t]
  ): CInt = extern

  /** Get TLV value for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @param tlv
    *   TLV array for value
    * @param tlv_size
    *   size of TLV array in bytes
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_hctl_elem_tlv_read")
  def snd_hctl_elem_tlv_read(
      elem: Ptr[snd_hctl_elem_t],
      tlv: Ptr[CUnsignedInt],
      tlv_size: CUnsignedInt
  ): CInt = extern

  /** Set TLV value for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @param tlv
    *   TLV array for value
    */
  @name("snd_hctl_elem_tlv_write")
  def snd_hctl_elem_tlv_write(
      elem: Ptr[snd_hctl_elem_t],
      tlv: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Set TLV value for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @param tlv
    *   TLV array for value
    */
  @name("snd_hctl_elem_tlv_command")
  def snd_hctl_elem_tlv_command(
      elem: Ptr[snd_hctl_elem_t],
      tlv: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Get HCTL handle for an HCTL element.
    *
    * @param elem
    *   HCTL element
    * @return
    *   HCTL handle
    */
  @name("snd_hctl_elem_get_hctl")
  def snd_hctl_elem_get_hctl(elem: Ptr[snd_hctl_elem_t]): Ptr[snd_hctl_t] =
    extern

  /** Get CTL element identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @param ptr
    *   Pointer to returned CTL element identifier
    */
  @name("snd_hctl_elem_get_id")
  def snd_hctl_elem_get_id(
      obj: Ptr[snd_hctl_elem_t],
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** Get element numeric identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @return
    *   element numeric identifier
    */
  @name("snd_hctl_elem_get_numid")
  def snd_hctl_elem_get_numid(obj: Ptr[snd_hctl_elem_t]): CUnsignedInt = extern

  /** Get interface part of CTL element identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @return
    *   interface part of element identifier
    */
  @name("snd_hctl_elem_get_interface")
  def snd_hctl_elem_get_interface(
      obj: Ptr[snd_hctl_elem_t]
  ): snd_ctl_elem_iface_t = extern

  /** Get device part of CTL element identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @return
    *   device part of element identifier
    */
  @name("snd_hctl_elem_get_device")
  def snd_hctl_elem_get_device(obj: Ptr[snd_hctl_elem_t]): CUnsignedInt = extern

  /** Get subdevice part of CTL element identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @return
    *   subdevice part of element identifier
    */
  @name("snd_hctl_elem_get_subdevice")
  def snd_hctl_elem_get_subdevice(obj: Ptr[snd_hctl_elem_t]): CUnsignedInt =
    extern

  /** Get name part of CTL element identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @return
    *   name part of element identifier
    */
  @name("snd_hctl_elem_get_name")
  def snd_hctl_elem_get_name(obj: Ptr[snd_hctl_elem_t]): Ptr[CChar] = extern

  /** Get index part of CTL element identifier of a CTL element id/value.
    *
    * @param obj
    *   CTL element id/value
    * @return
    *   index part of element identifier
    */
  @name("snd_hctl_elem_get_index")
  def snd_hctl_elem_get_index(obj: Ptr[snd_hctl_elem_t]): CUnsignedInt = extern

  /** Set callback function for an HCTL element.
    *
    * @param obj
    *   HCTL element
    * @param val
    *   callback function
    */
  @name("snd_hctl_elem_set_callback")
  def snd_hctl_elem_set_callback(
      obj: Ptr[snd_hctl_elem_t],
      `val`: snd_hctl_elem_callback_t
  ): Unit = extern

  /** Get callback private value for an HCTL element.
    *
    * @param obj
    *   HCTL element
    * @return
    *   callback private value
    */
  @name("snd_hctl_elem_get_callback_private")
  def snd_hctl_elem_get_callback_private(obj: Ptr[snd_hctl_elem_t]): CVoidPtr =
    extern

  /** Set callback private value for an HCTL element.
    *
    * @param obj
    *   HCTL element
    * @param val
    *   callback private value
    */
  @name("snd_hctl_elem_set_callback_private")
  def snd_hctl_elem_set_callback_private(
      obj: Ptr[snd_hctl_elem_t],
      `val`: CVoidPtr
  ): Unit = extern

}
