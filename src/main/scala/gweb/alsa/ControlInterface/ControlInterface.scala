package gweb.alsa.ControlInterface

import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t

import scala.scalanative.unsafe.*
import gweb.alsa.pollfd
import scala.scalanative.posix.sys.types.pid_t

import scala.scalanative.unsafe.Nat.Digit2
import scala.scalanative.unsafe.Nat._4
import scala.scalanative.unsafe.Nat._2
import scala.scalanative.unsafe.Nat._1
import scala.scalanative.unsafe.Nat._7
import scala.scalanative.unsafe.Nat.Digit3
import gweb.alsa.GlobalDefinesAndFunctions.snd_async_handler_t
import scala.scalanative.posix.sys.types
import gweb.alsa.GlobalDefinesAndFunctions.snd_async_callback_t
import gweb.alsa.Iota

/** The control interface. See Control interface page for more details.
  */
@link("asound")
@extern
object ControlInterface {

  /** IEC958 structure
    */
  type snd_aes_iec958_t = CStruct4[
    /** AES/IEC958 channel status bits
      */
    CArray[CUnsignedChar, Digit2[_2, _4]], // status
    /** AES/IEC958 subcode bits
      */
    CArray[CUnsignedChar, Digit3[_1, _4, _7]], // subcode
    /** nothing
      */
    CUnsignedChar, // pad
    /** AES/IEC958 subframe bits
      */
    CArray[CUnsignedChar, Nat._4] // dig_subframe
  ]

  /** dlsym version for interface entry callback
    */
  inline val SND_CONTROL_DLSYM_VERSION = "_dlsym_control_001"

  /** Element has been removed (Warning: test this first and if set don't test
    * the other masks)
    */
  inline val SND_CTL_EVENT_MASK_REMOVE = true

  /** Element value has been changed
    */
  inline val SND_CTL_EVENT_MASK_VALUE = true

  /** Element info has been changed
    */
  inline val SND_CTL_EVENT_MASK_INFO = true

  /** Element has been added
    */
  inline val SND_CTL_EVENT_MASK_ADD = true

  /** Element's TLV value has been changed
    */
  inline val SND_CTL_EVENT_MASK_TLV = true

  /** CTL name helper
    */
  inline val SND_CTL_NAME_NONE = ""

  /** CTL name helper
    */
  inline val SND_CTL_NAME_PLAYBACK = "Playback "

  /** CTL name helper
    */
  inline val SND_CTL_NAME_CAPTURE = "Capture "

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_NONE = ""

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_SWITCH = "Switch"

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_VOLUME = "Volume"

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_DEFAULT = "Default"

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_MASK = "Mask"

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_CON_MASK = "Con Mask"

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_PRO_MASK = "Pro Mask"

  /** CTL name helper
    */
  inline val SND_CTL_NAME_IEC958_PCM_STREAM = "PCM Stream"

  // todo inline val SND_CTL_NAME_IEC958(expl, direction, what)
  // "IEC958 " expl SND_CTL_NAME_## direction SND_CTL_NAME_IEC958_## what

  /** Mask for the major Power State identifier
    */
  inline val SND_CTL_POWER_MASK = 0xff00

  /** ACPI/PCI Power State D0
    */
  inline val SND_CTL_POWER_D0 = 0x0000

  /** ACPI/PCI Power State D1
    */
  inline val SND_CTL_POWER_D1 = 0x0100

  /** ACPI/PCI Power State D2
    */
  inline val SND_CTL_POWER_D2 = 0x0200

  /** ACPI/PCI Power State D3
    */
  inline val SND_CTL_POWER_D3 = 0x0300

  /** ACPI/PCI Power State D3hot
    */
  inline val SND_CTL_POWER_D3hot = (SND_CTL_POWER_D3 | 0x0000)

  /** ACPI/PCI Power State D3cold
    */
  inline val SND_CTL_POWER_D3cold = (SND_CTL_POWER_D3 | 0x0001)

  /** TLV type - Container
    */
  inline val SND_CTL_TLVT_CONTAINER = 0x0000

  /** TLV type - basic dB scale
    */
  inline val SND_CTL_TLVT_DB_SCALE = 0x0001

  /** TLV type - linear volume
    */
  inline val SND_CTL_TLVT_DB_LINEAR = 0x0002

  /** TLV type - dB range container
    */
  inline val SND_CTL_TLVT_DB_RANGE = 0x0003

  /** TLV type - dB scale specified by min/max values
    */
  inline val SND_CTL_TLVT_DB_MINMAX = 0x0004

  /** TLV type - dB scale specified by min/max values (with mute)
    */
  inline val SND_CTL_TLVT_DB_MINMAX_MUTE = 0x0005

  /** Mute state
    */
  inline val SND_CTL_TLV_DB_GAIN_MUTE = -9999999

  /** TLV type - fixed channel map positions
    */
  inline val SND_CTL_TLVT_CHMAP_FIXED = 0x00101

  /** TLV type - freely swappable channel map positions
    */
  inline val SND_CTL_TLVT_CHMAP_VAR = 0x00102

  /** TLV type - pair-wise swappable channel map positions
    */
  inline val SND_CTL_TLVT_CHMAP_PAIRED = 0x00103

  /** Non blocking mode (flag for open mode)
    */
  inline val SND_CTL_NONBLOCK = true

  /** Async notification (flag for open mode)
    */
  inline val SND_CTL_ASYNC = true

  /** Read only (flag for open mode)
    */
  inline val SND_CTL_READONLY = true

  /** Return EINTR instead blocking (flag for open mode)
    */
  inline val SND_CTL_EINTR = true

  /** Don't destroy the ctl handle when close
    */
  inline val SND_SCTL_NOFREE = 0x0001

  /** CTL card info container.
    *
    * This type contains meta information about a sound card, such as the index,
    * name, longname, etc.
    *
    * Memory management
    *
    * Before using a snd_ctl_card_info_t object, it must be allocated using
    * snd_ctl_card_info_alloca() or snd_ctl_card_info_malloc(). When using the
    * latter, it must be freed again using snd_ctl_card_info_free().
    *
    * A card info object can be zeroed out using snd_ctl_card_info_clear().
    *
    * A card info object can be copied to another one using
    * snd_ctl_card_info_copy().
    *
    * Obtaining the Information
    *
    * To obtain the card information, it must first be opened using
    * snd_ctl_open(), and a snd_ctl_card_info_t container must be allocated.
    * Then, the information can be read using snd_ctl_card_info_get_card().
    *
    * Thereafter, the card properties can be read using the
    * snd_ctl_card_info_get_*() functions.
    */
  opaque type snd_ctl_card_info_t = CStruct0

  /** CTL element identifier container
    */
  opaque type snd_ctl_elem_id_t = CStruct0

  /** CTL element list container
    *
    * This is a list of CTL elements. The list contains management information
    * (e.g. how many elements the sound card has) as well as the element
    * identifiers. All functions which operate on the list are named
    * snd_ctl_elem_list_*().
    *
    * Memory management
    *
    * There are two memory areas to deal with: The list container itself and the
    * memory for the element identifiers.
    *
    * To manage the area for the list container, the following functions are
    * used:
    *
    * snd_ctl_elem_list_malloc() / snd_ctl_elem_list_free() to allocate and free
    * memory on the heap, or snd_ctl_elem_list_alloca() to allocate the memory
    * on the stack. This memory is auto-released when the stack is unwound. To
    * manage the space for the element identifiers, the
    * snd_ctl_elem_list_alloc_space() and snd_ctl_elem_list_free_space() are
    * used. Allocating the right amount of space can be achieved by first
    * obtaining the number of elements and then calling
    * snd_ctl_elem_list_alloc_space():
    *
    * <pre> snd_ctl_elem_list_t* list; int count;
    *
    * // Initialise list snd_ctl_elem_list_malloc(&list);
    *
    * // Get number of elements snd_ctl_elem_list(ctl, list); count =
    * snd_ctl_elem_list_get_count(list);
    *
    * // Allocate space for identifiers snd_ctl_elem_list_alloc_space(list,
    * count);
    *
    * // Get identifiers snd_ctl_elem_list(ctl, list); // yes, this is same as
    * above :)
    *
    * // Do something useful with the list...
    *
    * // Cleanup snd_ctl_elem_list_free_space(list);
    * snd_ctl_elem_list_free(list); </pre>
    *
    * The Elements
    *
    * The elements in the list are accessed using an index. This index is the
    * location in the list; Don't confuse it with the 'index' of the element
    * identifier. For example:
    *
    * <pre> snd_ctl_elem_list_t list; unsigned int element_index;
    *
    * // Allocate space, fill list ...
    *
    * element_index = snd_ctl_elem_list_get_index(&list, 2); </pre>
    *
    * This will access the 3rd element in the list (index=2) and get the
    * elements index from the driver (which might be 13, for example).
    */
  opaque type snd_ctl_elem_list_t = CStruct0

  /** CTL element info container
    */
  opaque type snd_ctl_elem_info_t = CStruct0

  /** CTL element value container.
    *
    * Contains the value(s) (i.e. members) of a single element. All values of a
    * given element are of the same type.
    *
    * Memory management
    *
    * To access a value, a snd_ctl_elem_value_t must be allocated using
    * snd_ctl_elem_value_alloca() or snd_ctl_elem_value_malloc(). When using the
    * latter, it must be freed again using snd_ctl_elem_value_free().
    *
    * A value object can be zeroed out using snd_ctl_elem_value_clear().
    *
    * A value object can be copied to another one using
    * snd_ctl_elem_value_copy().
    *
    * Identifier
    *
    * Then, the ID must be filled. It is sufficient to fill only the numid, if
    * known. Otherwise, interface type, device, subdevice, name, index must all
    * be given. The following functions can be used to fill the ID:
    *
    * <ul>
    *
    * <li>snd_ctl_elem_value_set_id(): Set the ID. Requires an snd_ctl_elem_id_t
    * object.</li>
    *
    * <li>snd_ctl_elem_value_set_numid(): Set the numid.</li>
    *
    * </ul>
    *
    * Or use all of the following:
    *
    * <ul>
    *
    * <li>snd_ctl_elem_value_set_interface()</li>
    *
    * <li>snd_ctl_elem_value_set_device()</li>
    *
    * <li>snd_ctl_elem_value_set_subdevice()</li>
    *
    * <li>snd_ctl_elem_value_set_name()</li>
    *
    * <li>snd_ctl_elem_value_set_index()</li>
    *
    * </ul>
    *
    * When communicating with the driver (snd_ctl_elem_read(),
    * snd_ctl_elem_write()), and the numid was given, the interface, device, ...
    * are filled (even if you set the before). When the numid is unset (i.e. it
    * is 0), it is filled.
    *
    * Communicating with the driver After the value container was created and
    * filled with the ID of the desired element, the value(s) can be fetched
    * from the driver (and thus from the hardware) or written to the driver.
    *
    * To fetch a value, use snd_ctl_elem_read(). Thereafter, use the
    * snd_ctl_elem_value_get_*() functions to obtain the actual value.
    *
    * To write a new value, first use a snd_ctl_elem_value_set_*() to set it,
    * then call snd_ctl_elem_write() to write it to the driver.
    */
  opaque type snd_ctl_elem_value_t = CStruct0

  /** CTL event container
    */
  opaque type snd_ctl_event_t = CStruct0

  /** CTL handle
    */
  opaque type snd_ctl_t = CStruct0

  /** SCTL type
    */
  opaque type snd_sctl_t = CStruct0

  /** CTL element type
    */
  class snd_ctl_elem_type_t private (val value: CInt) extends AnyVal

  object snd_ctl_elem_type_t extends Iota {

    /** Invalid type */
    val SND_CTL_ELEM_TYPE_NONE = iota(0)

    /** Boolean contents */
    val SND_CTL_ELEM_TYPE_BOOLEAN = iota

    /** Integer contents */
    val SND_CTL_ELEM_TYPE_INTEGER = iota

    /** Enumerated contents */
    val SND_CTL_ELEM_TYPE_ENUMERATED = iota

    /** Bytes contents */
    val SND_CTL_ELEM_TYPE_BYTES = iota

    /** IEC958 (S/PDIF) setting content */
    val SND_CTL_ELEM_TYPE_IEC958 = iota

    /** 64-bit integer contents */
    val SND_CTL_ELEM_TYPE_INTEGER64 = iota

    val SND_CTL_ELEM_TYPE_LAST = iota(SND_CTL_ELEM_TYPE_INTEGER64)
  }

  /** CTL related interface
    */
  class snd_ctl_elem_iface_t private (val value: CInt) extends AnyVal

  object snd_ctl_elem_iface_t extends Iota {

    /** Card level */
    val SND_CTL_ELEM_IFACE_CARD = iota(0)

    /** Hardware dependent device */
    val SND_CTL_ELEM_IFACE_HWDEP = iota

    /** Mixer */
    val SND_CTL_ELEM_IFACE_MIXER = iota

    /** PCM */
    val SND_CTL_ELEM_IFACE_PCM = iota

    /** RawMidi */
    val SND_CTL_ELEM_IFACE_RAWMIDI = iota

    /** Timer */
    val SND_CTL_ELEM_IFACE_TIMER = iota

    /** Sequencer */
    val SND_CTL_ELEM_IFACE_SEQUENCER = iota

    val SND_CTL_ELEM_IFACE_LAST = iota(SND_CTL_ELEM_IFACE_SEQUENCER)
  }

  /** Event class
    */
  class snd_ctl_event_type_t private (val value: CInt) extends AnyVal

  object snd_ctl_event_type_t extends Iota {

    /** Elements related event
      */
    val SND_CTL_EVENT_ELEM = iota(0)

    val SND_CTL_EVENT_LAST = iota(SND_CTL_EVENT_ELEM)
  }

  /** CTL type
    */
  class snd_ctl_type_t private (val value: CInt) extends AnyVal

  object snd_ctl_type_t extends Iota {

    /** Kernel level CTL
      */
    val SND_CTL_TYPE_HW = iota

    /** Shared memory client CTL
      */
    val SND_CTL_TYPE_SHM = iota

    /** INET client CTL (not yet implemented)
      */
    val SND_CTL_TYPE_INET = iota

    /** External control plugin
      */
    val SND_CTL_TYPE_EXT = iota

    /** Control functionality remapping
      */
    val SND_CTL_TYPE_REMAP = iota
  }

  /** Try to load the driver for a card.
    *
    * @param card
    *   Card index.
    * @return
    *   1 if driver is present, zero if driver is not present.
    */
  @name("snd_card_load")
  def snd_card_load(card: CInt): CInt = extern

  /** This function takes the index of a physical sound card and sets it to the
    * index of the next card. If index is -1, it is set to the index of the
    * first card. After the last card, the index is set to -1.
    *
    * For example, if you have 2 sound cards (with index 0 and 1), the index
    * will be modified as follows:
    *
    * This does not work for virtual sound cards.
    */
  @name("snd_card_next")
  def snd_card_next(card: Ptr[CInt]): CInt = extern

  /** This works only for physical sound cards, not for virtual cards.
    *
    * The accepted formats for "string" are:
    */
  @name("snd_card_get_index")
  def snd_card_get_index(name: Ptr[CChar]): CInt = extern

  /** Obtain the card name.
    *
    * @param card
    *   The index of the card.
    * @param name
    *   Result - card name corresponding to card index.
    * @return
    *   zero if success, otherwise a negative error code
    *
    * The value returned in name is allocated with strdup and should be freed
    * when no longer used.
    */
  @name("snd_card_get_name")
  def snd_card_get_name(card: CInt, name: Ptr[Ptr[CChar]]): CInt = extern

  /** Obtain the card long name.
    *
    * @param card
    *   Index of the card.
    * @param name
    *   Result - card long name corresponding to card index.
    * @return
    *   Zero if success, otherwise a negative error code.
    *
    * The value returned in name is allocated with strdup and should be freed
    * when no longer used.
    */
  @name("snd_card_get_longname")
  def snd_card_get_longname(card: CInt, name: Ptr[Ptr[CChar]]): CInt = extern

  /** Opens a sound card.
    *
    * @param ctlp
    *   Returned CTL handle.
    * @param name
    *   A string identifying the card (See Identifying and Opening Control
    *   Interfaces).
    * @param mode
    *   Open mode (see SND_CTL_NONBLOCK, SND_CTL_ASYNC).
    * @return
    *   0 on success otherwise a negative error code.
    */
  @name("snd_ctl_open")
  def snd_ctl_open(
      ctl: Ptr[Ptr[snd_ctl_t]],
      name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** Opens a CTL using local configuration.
    *
    * @param ctlp
    *   Returned CTL handle
    * @param name
    *   ASCII identifier of the CTL handle
    * @param mode
    *   Open mode (see SND_CTL_NONBLOCK, SND_CTL_ASYNC)
    * @param lconf
    *   Local configuration
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_open_lconf")
  def snd_ctl_open_lconf(
      ctl: Ptr[Ptr[snd_ctl_t]],
      name: Ptr[CChar],
      mode: CInt,
      lconf: Ptr[snd_config_t]
  ): CInt = extern

  /** Opens a fallback CTL.
    *
    * @param ctlp
    *   Returned CTL handle
    * @param root
    *   Configuration root
    * @param name
    *   ASCII identifier of the CTL handle used as fallback
    * @param orig_name
    *   The original ASCII name
    * @param mode
    *   Open mode (see SND_CTL_NONBLOCK, SND_CTL_ASYNC)
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_open_fallback")
  def snd_ctl_open_fallback(
      ctl: Ptr[Ptr[snd_ctl_t]],
      root: Ptr[snd_config_t],
      name: Ptr[CChar],
      orig_name: Ptr[CChar],
      mode: CInt
  ): CInt = extern

  /** close CTL handle
    *
    * @param ctl
    *   CTL handle
    * @return
    *   0 on success otherwise a negative error code
    *
    * Closes the specified CTL handle and frees all associated resources.
    */
  @name("snd_ctl_close")
  def snd_ctl_close(ctl: Ptr[snd_ctl_t]): CInt = extern

  /** set nonblock mode
    *
    * @param ctl
    *   CTL handle
    * @param nonblock
    *   0 = block, 1 = nonblock mode, 2 = abort
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_nonblock")
  def snd_ctl_nonblock(ctl: Ptr[snd_ctl_t], nonblock: CInt): CInt = extern

  /** Add an async handler for a CTL.
    *
    * @param handler
    *   Returned handler handle
    * @param ctl
    *   CTL handle
    * @param callback
    *   Callback function
    * @param private_data
    *   Callback private data
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_async_add_ctl_handler")
  def snd_async_add_ctl_handler(
      handler: Ptr[Ptr[snd_async_handler_t]],
      ctl: Ptr[snd_ctl_t],
      callback: snd_async_callback_t,
      private_data: CVoidPtr
  ): CInt = extern

  /** Return CTL handle related to an async handler.
    *
    * @param handler
    *   Async handler handle
    * @return
    *   CTL handle
    */
  @name("snd_async_handler_get_ctl")
  def snd_async_handler_get_ctl(
      handler: Ptr[snd_async_handler_t]
  ): Ptr[snd_ctl_t] = extern

  /** get count of poll descriptors for CTL handle
    *
    * @param ctl
    *   CTL handle
    * @return
    *   count of poll descriptors
    */
  @name("snd_ctl_poll_descriptors_count")
  def snd_ctl_poll_descriptors_count(ctl: Ptr[snd_ctl_t]): CInt = extern

  /** get poll descriptors
    *
    * @param ctl
    *   CTL handle
    * @param pfds
    *   array of poll descriptors
    * @param space
    *   space in the poll descriptor array
    * @return
    *   count of filled descriptors
    */
  @name("snd_ctl_poll_descriptors")
  def snd_ctl_poll_descriptors(
      ctl: Ptr[snd_ctl_t],
      pfds: Ptr[pollfd],
      space: CUnsignedInt
  ): CInt = extern

  /** get returned events from poll descriptors
    *
    * @param ctl
    *   CTL handle
    * @param pfds
    *   array of poll descriptors
    * @param nfds
    *   count of poll descriptors
    * @param revents
    *   returned events
    * @return
    *   zero if success, otherwise a negative error code
    */
  @name("snd_ctl_poll_descriptors_revents")
  def snd_ctl_poll_descriptors_revents(
      ctl: Ptr[snd_ctl_t],
      pfds: Ptr[pollfd],
      nfds: CUnsignedInt,
      revents: Ptr[CUnsignedShort]
  ): CInt = extern

  /** Ask to be informed about events (poll, snd_async_add_ctl_handler,
    * snd_ctl_read)
    *
    * @param ctl
    *   CTL handle
    * @param subscribe
    *   0 = unsubscribe, 1 = subscribe, -1 = check subscribe or not
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_subscribe_events")
  def snd_ctl_subscribe_events(ctl: Ptr[snd_ctl_t], subscribe: CInt): CInt =
    extern

  /** Obtain information about the sound card previously opened using
    * snd_ctl_open(). The object "info" must be allocated prior to calling this
    * function. See snd_ctl_card_info_t for details.
    */
  @name("snd_ctl_card_info")
  def snd_ctl_card_info(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_card_info_t]
  ): CInt = extern

  /** Before calling this function, memoru must be allocated using
    * snd_ctl_elem_list_malloc().
    *
    * This function obtains data from the sound card driver and puts it into the
    * list.
    *
    * If there was space allocated for the element identifiers (using
    * snd_ctl_elem_list_alloc_space()), information will be filled in. If too
    * little space was allocated, only a part of the elements will be queried.
    * If there was too much space allocated, some of it remains unused. Use
    * snd_ctl_elem_list_get_count() and snd_ctl_elem_list_get_used() to obtain
    * information about space usage. See snd_ctl_elem_list_t to learn more.
    */
  @name("snd_ctl_elem_list")
  def snd_ctl_elem_list(
      ctl: Ptr[snd_ctl_t],
      list: Ptr[snd_ctl_elem_list_t]
  ): CInt = extern

  /** Get CTL element information.
    *
    * @param ctl
    *   CTL handle
    * @param info
    *   CTL element id/information pointer
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_elem_info")
  def snd_ctl_elem_info(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_elem_info_t]
  ): CInt = extern

  /** Read information from sound card. You must set the ID of the element
    * before calling this function.
    *
    * See snd_ctl_elem_value_t for details.
    */
  @name("snd_ctl_elem_read")
  def snd_ctl_elem_read(
      ctl: Ptr[snd_ctl_t],
      data: Ptr[snd_ctl_elem_value_t]
  ): CInt = extern

  /** Write new value(s) to the sound card. You must set the ID and the value of
    * the element before calling this function.
    *
    * See snd_ctl_elem_value_t for details.
    */
  @name("snd_ctl_elem_write")
  def snd_ctl_elem_write(
      ctl: Ptr[snd_ctl_t],
      data: Ptr[snd_ctl_elem_value_t]
  ): CInt = extern

  /** Lock CTL element.
    *
    * @param ctl
    *   CTL handle
    * @param id
    *   CTL element id pointer
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_elem_lock")
  def snd_ctl_elem_lock(ctl: Ptr[snd_ctl_t], id: Ptr[snd_ctl_elem_id_t]): CInt =
    extern

  /** Unlock CTL element.
    *
    * @param ctl
    *   CTL handle
    * @param id
    *   CTL element id pointer
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_elem_unlock")
  def snd_ctl_elem_unlock(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t]
  ): CInt = extern

  /** Read structured data from an element set to given buffer.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param id
    *   ID of an element.
    * @param tlv
    *   An array with members of unsigned int type.
    * @param tlv_size
    *   The length of the array.
    * @return
    *   0 on success otherwise a negative error code
    *
    * The format of an array of tlv argument is: tlv[0]: Type. One of
    * SND_CTL_TLVT_XXX. tlv[1]: Length. The length of value in units of byte.
    * tlv[2..]: Value. Depending on the type.
    *
    * Details are described in <sound/tlv.h>.
    */
  @name("snd_ctl_elem_tlv_read")
  def snd_ctl_elem_tlv_read(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      tlv: Ptr[CUnsignedInt],
      tlv_size: CUnsignedInt
  ): CInt = extern

  /** Write structured data from given buffer to an element set.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param id
    *   ID of an element.
    * @param tlv
    *   An array with members of unsigned int type. The second member must
    *   represent total bytes of the rest of array.
    *
    * The format of an array of tlv argument is: tlv[0]: Type. One of
    * SND_CTL_TLVT_XXX. tlv[1]: Length. The length of value in units of byte.
    * tlv[2..]: Value. Depending on the type.
    *
    * Details are described in <sound/tlv.h>.
    */
  @name("snd_ctl_elem_tlv_write")
  def snd_ctl_elem_tlv_write(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      tlv: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Process structured data from given buffer for an element set.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param id
    *   ID of an element.
    * @param tlv
    *   An array with members of unsigned int type. The second member must
    *   represent total bytes of the rest of array.
    *
    * The format of an array of tlv argument is: tlv[0]: Type. One of
    * SND_CTL_TLVT_XXX. tlv[1]: Length. The length of value in units of byte.
    * tlv[2..]: Value. Depending on the type.
    *
    * Details are described in <sound/tlv.h>.
    */
  @name("snd_ctl_elem_tlv_command")
  def snd_ctl_elem_tlv_command(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      tlv: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Set Power State to given SND_CTL_POWER_* value and do the power
    * management.
    *
    * @param ctl
    *   CTL handle
    * @param state
    *   Desired Power State
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_set_power_state")
  def snd_ctl_set_power_state(ctl: Ptr[snd_ctl_t], state: CUnsignedInt): CInt =
    extern

  /** Get actual Power State.
    *
    * @param ctl
    *   CTL handle
    * @param state
    *   Destination value
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_get_power_state")
  def snd_ctl_get_power_state(
      ctl: Ptr[snd_ctl_t],
      state: Ptr[CUnsignedInt]
  ): CInt = extern

  /** Read an event.
    *
    * @param ctl
    *   CTL handle
    * @param event
    *   Event pointer
    * @return
    *   number of events read otherwise a negative error code on failure
    */
  @name("snd_ctl_read")
  def snd_ctl_read(ctl: Ptr[snd_ctl_t], event: Ptr[snd_ctl_event_t]): CInt =
    extern

  /** Wait for a CTL to become ready (i.e. at least one event pending)
    *
    * @param ctl
    *   CTL handle
    * @param timeout
    *   maximum time in milliseconds to wait
    * @return
    *   0 otherwise a negative error code on failure
    */
  @name("snd_ctl_wait")
  def snd_ctl_wait(ctl: Ptr[snd_ctl_t], timeout: CInt): CInt = extern

  /** get identifier of CTL handle
    *
    * @param ctl
    *   CTL handle
    * @return
    *   ascii identifier of CTL handle
    *
    * Returns the ASCII identifier of given CTL handle. It's the same identifier
    * specified in snd_ctl_open().
    */
  @name("snd_ctl_name")
  def snd_ctl_name(ctl: Ptr[snd_ctl_t]): Ptr[CChar] = extern

  /** get type of CTL handle
    *
    * @param ctl
    *   CTL handle
    * @return
    *   type of CTL handle
    *
    * Returns the type snd_ctl_type_t of given CTL handle.
    */
  @name("snd_ctl_type")
  def snd_ctl_type(ctl: Ptr[snd_ctl_t]): snd_ctl_type_t = extern

  /** get name of a CTL element type
    *
    * @param type
    *   CTL element type
    * @return
    *   ascii name of CTL element type
    */
  @name("snd_ctl_elem_type_name")
  def snd_ctl_elem_type_name(`type`: snd_ctl_elem_type_t): Ptr[CChar] = extern

  /** get name of a CTL element related interface
    *
    * @param iface
    *   CTL element related interface
    * @return
    *   ascii name of CTL element related interface
    */
  @name("snd_ctl_elem_iface_name")
  def snd_ctl_elem_iface_name(iface: snd_ctl_elem_iface_t): Ptr[CChar] = extern

  /** get name of a CTL event type
    *
    * @param type
    *   CTL event type
    * @return
    *   ascii name of CTL event type
    */
  @name("snd_ctl_event_type_name")
  def snd_ctl_event_type_name(`type`: snd_ctl_event_type_t): Ptr[CChar] = extern

  /** Get event mask for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   event mask for element related event
    */
  @name("snd_ctl_event_elem_get_mask")
  def snd_ctl_event_elem_get_mask(obj: Ptr[snd_ctl_event_t]): CUnsignedInt =
    extern

  /** Get element numeric identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   element numeric identifier
    */
  @name("snd_ctl_event_elem_get_numid")
  def snd_ctl_event_elem_get_numid(obj: Ptr[snd_ctl_event_t]): CUnsignedInt =
    extern

  /** Get CTL element identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @param ptr
    *   Pointer to returned CTL element identifier
    */
  @name("snd_ctl_event_elem_get_id")
  def snd_ctl_event_elem_get_id(
      obj: Ptr[snd_ctl_event_t],
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** Get interface part of CTL element identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   interface part of element identifier
    */
  @name("snd_ctl_event_elem_get_interface")
  def snd_ctl_event_elem_get_interface(
      obj: Ptr[snd_ctl_event_t]
  ): snd_ctl_elem_iface_t = extern

  /** Get device part of CTL element identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   device part of element identifier
    */
  @name("snd_ctl_event_elem_get_device")
  def snd_ctl_event_elem_get_device(obj: Ptr[snd_ctl_event_t]): CUnsignedInt =
    extern

  /** Get subdevice part of CTL element identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   subdevice part of element identifier
    */
  @name("snd_ctl_event_elem_get_subdevice")
  def snd_ctl_event_elem_get_subdevice(
      obj: Ptr[snd_ctl_event_t]
  ): CUnsignedInt = extern

  /** Get name part of CTL element identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   name part of element identifier
    */
  @name("snd_ctl_event_elem_get_name")
  def snd_ctl_event_elem_get_name(obj: Ptr[snd_ctl_event_t]): Ptr[CChar] =
    extern

  /** Get index part of CTL element identifier for an element related event.
    *
    * @param obj
    *   CTL event
    * @return
    *   index part of element identifier
    */
  @name("snd_ctl_event_elem_get_index")
  def snd_ctl_event_elem_get_index(obj: Ptr[snd_ctl_event_t]): CUnsignedInt =
    extern

  /** The space can be released with snd_ctl_elem_list_free_space().
    */
  @name("snd_ctl_elem_list_alloc_space")
  def snd_ctl_elem_list_alloc_space(
      obj: Ptr[snd_ctl_elem_list_t],
      entries: CUnsignedInt
  ): CInt = extern

  /** Releases space previously allocated using snd_ctl_elem_list_alloc_space().
    */
  @name("snd_ctl_elem_list_free_space")
  def snd_ctl_elem_list_free_space(obj: Ptr[snd_ctl_elem_list_t]): Unit = extern

  /** return ASCII CTL element identifier name
    *
    * @param id
    *   CTL identifier
    * @return
    *   ascii identifier of CTL element
    *
    * The string is allocated using strdup().
    */
  @name("snd_ctl_ascii_elem_id_get")
  def snd_ctl_ascii_elem_id_get(id: Ptr[snd_ctl_elem_id_t]): Ptr[CChar] = extern

  /** parse ASCII string as CTL element identifier
    *
    * @param dst
    *   destination CTL identifier
    * @param str
    *   source ASCII string
    * @return
    *   zero on success, otherwise a negative error code
    */
  @name("snd_ctl_ascii_elem_id_parse")
  def snd_ctl_ascii_elem_id_parse(
      dst: Ptr[snd_ctl_elem_id_t],
      str: Ptr[CChar]
  ): CInt = extern

  /** parse ASCII string as CTL element value
    *
    * @param handle
    *   CTL handle
    * @param dst
    *   destination CTL element value
    * @param info
    *   CTL element info structure
    * @param value
    *   source ASCII string
    * @return
    *   zero on success, otherwise a negative error code
    *
    * Note: For toggle command, the dst must contain previous (current) state
    * (do the snd_ctl_elem_read call to obtain it).
    */
  @name("snd_ctl_ascii_value_parse")
  def snd_ctl_ascii_value_parse(
      handle: Ptr[snd_ctl_t],
      dst: Ptr[snd_ctl_elem_value_t],
      info: Ptr[snd_ctl_elem_info_t],
      value: Ptr[CChar]
  ): CInt = extern

  /** get size of snd_ctl_elem_id_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_ctl_elem_id_sizeof")
  def snd_ctl_elem_id_sizeof(): CSize = extern

  /** allocate an invalid snd_ctl_elem_id_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_ctl_elem_id_malloc")
  def snd_ctl_elem_id_malloc(ptr: Ptr[Ptr[snd_ctl_elem_id_t]]): CInt = extern

  /** frees a previously allocated snd_ctl_elem_id_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_ctl_elem_id_free")
  def snd_ctl_elem_id_free(obj: Ptr[snd_ctl_elem_id_t]): Unit = extern

  /** clear given snd_ctl_elem_id_t object
    *
    * @param obj
    *   pointer to object to clear
    */
  @name("snd_ctl_elem_id_clear")
  def snd_ctl_elem_id_clear(obj: Ptr[snd_ctl_elem_id_t]): Unit = extern

  /** copy one snd_ctl_elem_id_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_ctl_elem_id_copy")
  def snd_ctl_elem_id_copy(
      dst: Ptr[snd_ctl_elem_id_t],
      src: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** compare one snd_ctl_elem_id_t to another using numid
    *
    * @param id1
    *   pointer to first id
    * @param id2
    *   pointer to second id
    *
    * This comparison ignores the set of fields part.
    *
    * The return value can be used for sorting like qsort(). It gives persistent
    * results.
    */
  @name("snd_ctl_elem_id_compare_numid")
  def snd_ctl_elem_id_compare_numid(
      id1: Ptr[snd_ctl_elem_id_t],
      id2: Ptr[snd_ctl_elem_id_t]
  ): CInt = extern

  /** compare one snd_ctl_elem_id_t to another
    *
    * @param id1
    *   pointer to first id
    * @param id2
    *   pointer to second id
    *
    * This comparison ignores the numid part. The numid comparison can be easily
    * implemented using snd_ctl_elem_id_get_numid() calls.
    *
    * The identifier set fields are compared in this order: interface, device,
    * subdevice, name, index.
    *
    * The return value can be used for sorting like qsort(). It gives persistent
    * results.
    */
  @name("snd_ctl_elem_id_compare_set")
  def snd_ctl_elem_id_compare_set(
      id1: Ptr[snd_ctl_elem_id_t],
      id2: Ptr[snd_ctl_elem_id_t]
  ): CInt = extern

  /** Get numeric identifier from a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @return
    *   CTL element numeric identifier
    */
  @name("snd_ctl_elem_id_get_numid")
  def snd_ctl_elem_id_get_numid(obj: Ptr[snd_ctl_elem_id_t]): CUnsignedInt =
    extern

  /** Get interface part of a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @return
    *   CTL element related interface
    */
  @name("snd_ctl_elem_id_get_interface")
  def snd_ctl_elem_id_get_interface(
      obj: Ptr[snd_ctl_elem_id_t]
  ): snd_ctl_elem_iface_t = extern

  /** Get device part of a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @return
    *   CTL element related device
    */
  @name("snd_ctl_elem_id_get_device")
  def snd_ctl_elem_id_get_device(obj: Ptr[snd_ctl_elem_id_t]): CUnsignedInt =
    extern

  /** Get subdevice part of a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @return
    *   CTL element related subdevice
    */
  @name("snd_ctl_elem_id_get_subdevice")
  def snd_ctl_elem_id_get_subdevice(obj: Ptr[snd_ctl_elem_id_t]): CUnsignedInt =
    extern

  /** Get name part of a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @return
    *   CTL element name
    */
  @name("snd_ctl_elem_id_get_name")
  def snd_ctl_elem_id_get_name(obj: Ptr[snd_ctl_elem_id_t]): Ptr[CChar] = extern

  /** Get index part of a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @return
    *   CTL element index
    */
  @name("snd_ctl_elem_id_get_index")
  def snd_ctl_elem_id_get_index(obj: Ptr[snd_ctl_elem_id_t]): CUnsignedInt =
    extern

  /** Set numeric identifier for a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @param val
    *   CTL element numeric identifier
    */
  @name("snd_ctl_elem_id_set_numid")
  def snd_ctl_elem_id_set_numid(
      obj: Ptr[snd_ctl_elem_id_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set interface part for a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @param val
    *   CTL element related interface
    */
  @name("snd_ctl_elem_id_set_interface")
  def snd_ctl_elem_id_set_interface(
      obj: Ptr[snd_ctl_elem_id_t],
      `val`: snd_ctl_elem_iface_t
  ): Unit = extern

  /** Set device part for a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @param val
    *   CTL element related device
    */
  @name("snd_ctl_elem_id_set_device")
  def snd_ctl_elem_id_set_device(
      obj: Ptr[snd_ctl_elem_id_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set subdevice part for a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @param val
    *   CTL element related subdevice
    */
  @name("snd_ctl_elem_id_set_subdevice")
  def snd_ctl_elem_id_set_subdevice(
      obj: Ptr[snd_ctl_elem_id_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set name part for a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @param val
    *   CTL element name
    */
  @name("snd_ctl_elem_id_set_name")
  def snd_ctl_elem_id_set_name(
      obj: Ptr[snd_ctl_elem_id_t],
      `val`: Ptr[CChar]
  ): Unit = extern

  /** Set index part for a CTL element identifier.
    *
    * @param obj
    *   CTL element identifier
    * @param val
    *   CTL element index
    */
  @name("snd_ctl_elem_id_set_index")
  def snd_ctl_elem_id_set_index(
      obj: Ptr[snd_ctl_elem_id_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** get size of snd_ctl_card_info_t.
    *
    * @return
    *   Size in bytes.
    */
  @name("snd_ctl_card_info_sizeof")
  def snd_ctl_card_info_sizeof(): CSize = extern

  /** Allocate space for a card info object on the heap. The allocated memory
    * must be freed using snd_ctl_card_info_free().
    *
    * See snd_ctl_card_info_t for details.
    */
  @name("snd_ctl_card_info_malloc")
  def snd_ctl_card_info_malloc(ptr: Ptr[Ptr[snd_ctl_card_info_t]]): CInt =
    extern

  /** Free an snd_ctl_card_info_t previously allocated using
    * snd_ctl_card_info_malloc().
    *
    * @param obj
    *   Pointer to the snd_ctl_card_info_t.
    */
  @name("snd_ctl_card_info_free")
  def snd_ctl_card_info_free(obj: Ptr[snd_ctl_card_info_t]): Unit = extern

  /** See snd_ctl_elem_value_t for details.
    */
  @name("snd_ctl_card_info_clear")
  def snd_ctl_card_info_clear(obj: Ptr[snd_ctl_card_info_t]): Unit = extern

  /** Bitwise copy of a snd_ctl_card_info_t object.
    *
    * @param dst
    *   Pointer to destination.
    * @param src
    *   Pointer to source.
    */
  @name("snd_ctl_card_info_copy")
  def snd_ctl_card_info_copy(
      dst: Ptr[snd_ctl_card_info_t],
      src: Ptr[snd_ctl_card_info_t]
  ): Unit = extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_card")
  def snd_ctl_card_info_get_card(obj: Ptr[snd_ctl_card_info_t]): CInt = extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_id")
  def snd_ctl_card_info_get_id(obj: Ptr[snd_ctl_card_info_t]): Ptr[CChar] =
    extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_driver")
  def snd_ctl_card_info_get_driver(obj: Ptr[snd_ctl_card_info_t]): Ptr[CChar] =
    extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_name")
  def snd_ctl_card_info_get_name(obj: Ptr[snd_ctl_card_info_t]): Ptr[CChar] =
    extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_longname")
  def snd_ctl_card_info_get_longname(
      obj: Ptr[snd_ctl_card_info_t]
  ): Ptr[CChar] = extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_mixername")
  def snd_ctl_card_info_get_mixername(
      obj: Ptr[snd_ctl_card_info_t]
  ): Ptr[CChar] = extern

  /** See snd_ctl_card_info_t for more details.
    */
  @name("snd_ctl_card_info_get_components")
  def snd_ctl_card_info_get_components(
      obj: Ptr[snd_ctl_card_info_t]
  ): Ptr[CChar] = extern

  /** get size of snd_ctl_event_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_ctl_event_sizeof")
  def snd_ctl_event_sizeof(): CSize = extern

  /** allocate an invalid snd_ctl_event_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_ctl_event_malloc")
  def snd_ctl_event_malloc(ptr: Ptr[Ptr[snd_ctl_event_t]]): CInt = extern

  /** frees a previously allocated snd_ctl_event_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_ctl_event_free")
  def snd_ctl_event_free(obj: Ptr[snd_ctl_event_t]): Unit = extern

  /** clear given snd_ctl_event_t object
    *
    * @param obj
    *   pointer to object to clear
    */
  @name("snd_ctl_event_clear")
  def snd_ctl_event_clear(obj: Ptr[snd_ctl_event_t]): Unit = extern

  /** copy one snd_ctl_event_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_ctl_event_copy")
  def snd_ctl_event_copy(
      dst: Ptr[snd_ctl_event_t],
      src: Ptr[snd_ctl_event_t]
  ): Unit = extern

  /** Get type of a CTL event.
    *
    * @param obj
    *   CTL event
    * @return
    *   CTL event type
    */
  @name("snd_ctl_event_get_type")
  def snd_ctl_event_get_type(obj: Ptr[snd_ctl_event_t]): snd_ctl_event_type_t =
    extern

  /** get size of snd_ctl_elem_list_t.
    *
    * @return
    *   size in bytes
    */
  @name("snd_ctl_elem_list_sizeof")
  def snd_ctl_elem_list_sizeof(): CSize = extern

  /** The memory can be released using snd_ctl_elem_list_free().
    */
  @name("snd_ctl_elem_list_malloc")
  def snd_ctl_elem_list_malloc(ptr: Ptr[Ptr[snd_ctl_elem_list_t]]): CInt =
    extern

  /** Release memory previously allocated using snd_ctl_elem_list_malloc().
    *
    * If you used snd_ctl_elem_list_alloc_space() on the list, you must use
    * snd_ctl_elem_list_free_space() before calling this function.
    */
  @name("snd_ctl_elem_list_free")
  def snd_ctl_elem_list_free(obj: Ptr[snd_ctl_elem_list_t]): Unit = extern

  /** This will make the stored identifiers inaccessible without freeing their
    * space.
    */
  @name("snd_ctl_elem_list_clear")
  def snd_ctl_elem_list_clear(obj: Ptr[snd_ctl_elem_list_t]): Unit = extern

  /** This performs a shallow copy. That means the both lists will share the
    * same space for the elements. The elements will not be copied.
    */
  @name("snd_ctl_elem_list_copy")
  def snd_ctl_elem_list_copy(
      dst: Ptr[snd_ctl_elem_list_t],
      src: Ptr[snd_ctl_elem_list_t]
  ): Unit = extern

  /** Set index of first wanted CTL element identifier in a CTL element
    * identifiers list.
    *
    * @param obj
    *   CTL element identifiers list
    * @param val
    *   index of CTL element to put at position 0 of list
    */
  @name("snd_ctl_elem_list_set_offset")
  def snd_ctl_elem_list_set_offset(
      obj: Ptr[snd_ctl_elem_list_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** This function returns how many entries are actually filled with useful
    * information.
    *
    * See also snd_ctl_elem_list_get_count().
    */
  @name("snd_ctl_elem_list_get_used")
  def snd_ctl_elem_list_get_used(obj: Ptr[snd_ctl_elem_list_t]): CUnsignedInt =
    extern

  /** This function returns how many entries were allocated using
    * snd_ctl_elem_list_alloc_space(). This information is present after
    * snd_ctl_elem_list() was called.
    *
    * See also snd_ctl_elem_list_get_used().
    */
  @name("snd_ctl_elem_list_get_count")
  def snd_ctl_elem_list_get_count(obj: Ptr[snd_ctl_elem_list_t]): CUnsignedInt =
    extern

  /** Get CTL element identifier for an entry of a CTL element identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @param ptr
    *   Pointer to returned CTL element identifier
    */
  @name("snd_ctl_elem_list_get_id")
  def snd_ctl_elem_list_get_id(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt,
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** Get CTL element numeric identifier for an entry of a CTL element
    * identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @return
    *   CTL element numeric identifier
    */
  @name("snd_ctl_elem_list_get_numid")
  def snd_ctl_elem_list_get_numid(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt
  ): CUnsignedInt = extern

  /** Get interface part of CTL element identifier for an entry of a CTL element
    * identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @return
    *   CTL element related interface
    */
  @name("snd_ctl_elem_list_get_interface")
  def snd_ctl_elem_list_get_interface(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt
  ): snd_ctl_elem_iface_t = extern

  /** Get the device part of CTL element identifier for an entry of a CTL
    * element identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @return
    *   CTL element related device
    */
  @name("snd_ctl_elem_list_get_device")
  def snd_ctl_elem_list_get_device(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt
  ): CUnsignedInt = extern

  /** Get subdevice part of CTL element identifier for an entry of a CTL element
    * identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @return
    *   CTL element related subdevice
    */
  @name("snd_ctl_elem_list_get_subdevice")
  def snd_ctl_elem_list_get_subdevice(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt
  ): CUnsignedInt = extern

  /** Get name part of CTL element identifier for an entry of a CTL element
    * identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @return
    *   CTL element name
    */
  @name("snd_ctl_elem_list_get_name")
  def snd_ctl_elem_list_get_name(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt
  ): Ptr[CChar] = extern

  /** Get index part of CTL element identifier for an entry of a CTL element
    * identifiers list.
    *
    * @param obj
    *   CTL element identifier list
    * @param idx
    *   Index of entry
    * @return
    *   CTL element index
    */
  @name("snd_ctl_elem_list_get_index")
  def snd_ctl_elem_list_get_index(
      obj: Ptr[snd_ctl_elem_list_t],
      idx: CUnsignedInt
  ): CUnsignedInt = extern

  /** get size of snd_ctl_elem_info_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_ctl_elem_info_sizeof")
  def snd_ctl_elem_info_sizeof(): CSize = extern

  /** allocate an invalid snd_ctl_elem_info_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_ctl_elem_info_malloc")
  def snd_ctl_elem_info_malloc(ptr: Ptr[Ptr[snd_ctl_elem_info_t]]): CInt =
    extern

  /** frees a previously allocated snd_ctl_elem_info_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_ctl_elem_info_free")
  def snd_ctl_elem_info_free(obj: Ptr[snd_ctl_elem_info_t]): Unit = extern

  /** clear given snd_ctl_elem_info_t object
    *
    * @param obj
    *   pointer to object to clear
    */
  @name("snd_ctl_elem_info_clear")
  def snd_ctl_elem_info_clear(obj: Ptr[snd_ctl_elem_info_t]): Unit = extern

  /** copy one snd_ctl_elem_info_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_ctl_elem_info_copy")
  def snd_ctl_elem_info_copy(
      dst: Ptr[snd_ctl_elem_info_t],
      src: Ptr[snd_ctl_elem_info_t]
  ): Unit = extern

  /** Get type from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   CTL element content type
    */
  @name("snd_ctl_elem_info_get_type")
  def snd_ctl_elem_info_get_type(
      obj: Ptr[snd_ctl_elem_info_t]
  ): snd_ctl_elem_type_t = extern

  /** Get info about readability from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element is not readable, 1 if element is readable
    */
  @name("snd_ctl_elem_info_is_readable")
  def snd_ctl_elem_info_is_readable(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get info about writability from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element is not writable, 1 if element is not writable
    */
  @name("snd_ctl_elem_info_is_writable")
  def snd_ctl_elem_info_is_writable(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get info about notification feasibility from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if all element value changes are notified to subscribed applications,
    *   1 otherwise
    */
  @name("snd_ctl_elem_info_is_volatile")
  def snd_ctl_elem_info_is_volatile(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get info about status from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element value is not active, 1 if is active
    */
  @name("snd_ctl_elem_info_is_inactive")
  def snd_ctl_elem_info_is_inactive(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get info whether an element is locked.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element value is currently changeable, 1 if it's locked by another
    *   application
    */
  @name("snd_ctl_elem_info_is_locked")
  def snd_ctl_elem_info_is_locked(obj: Ptr[snd_ctl_elem_info_t]): CInt = extern

  /** Get info about TLV readability from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element's TLV is not readable, 1 if element's TLV is readable
    */
  @name("snd_ctl_elem_info_is_tlv_readable")
  def snd_ctl_elem_info_is_tlv_readable(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get info about TLV writeability from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element's TLV is not writable, 1 if element's TLV is writable
    */
  @name("snd_ctl_elem_info_is_tlv_writable")
  def snd_ctl_elem_info_is_tlv_writable(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get info about TLV command possibility from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element's TLV command is not possible, 1 if element's TLV command
    *   is supported
    */
  @name("snd_ctl_elem_info_is_tlv_commandable")
  def snd_ctl_elem_info_is_tlv_commandable(
      obj: Ptr[snd_ctl_elem_info_t]
  ): CInt = extern

  /** Get info if I own an element.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element value is currently changeable, 1 if it's locked by another
    *   application
    */
  @name("snd_ctl_elem_info_is_owner")
  def snd_ctl_elem_info_is_owner(obj: Ptr[snd_ctl_elem_info_t]): CInt = extern

  /** Get info if it's a user element.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   0 if element value is a system element, 1 if it's a user-created element
    */
  @name("snd_ctl_elem_info_is_user")
  def snd_ctl_elem_info_is_user(obj: Ptr[snd_ctl_elem_info_t]): CInt = extern

  /** Get owner of a locked element.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   value entries count
    */
  @name("snd_ctl_elem_info_get_owner")
  def snd_ctl_elem_info_get_owner(obj: Ptr[snd_ctl_elem_info_t]): pid_t = extern

  /** Get number of value entries from a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   value entries count
    */
  @name("snd_ctl_elem_info_get_count")
  def snd_ctl_elem_info_get_count(obj: Ptr[snd_ctl_elem_info_t]): CUnsignedInt =
    extern

  /** Get minimum value from a SND_CTL_ELEM_TYPE_INTEGER CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   Minimum value
    */
  @name("snd_ctl_elem_info_get_min")
  def snd_ctl_elem_info_get_min(obj: Ptr[snd_ctl_elem_info_t]): CLong = extern

  /** Get maximum value from a SND_CTL_ELEM_TYPE_INTEGER CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   Maximum value
    */
  @name("snd_ctl_elem_info_get_max")
  def snd_ctl_elem_info_get_max(obj: Ptr[snd_ctl_elem_info_t]): CLong = extern

  /** Get value step from a SND_CTL_ELEM_TYPE_INTEGER CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   Step
    */
  @name("snd_ctl_elem_info_get_step")
  def snd_ctl_elem_info_get_step(obj: Ptr[snd_ctl_elem_info_t]): CLong = extern

  /** Get minimum value from a SND_CTL_ELEM_TYPE_INTEGER64 CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   Minimum value
    */
  @name("snd_ctl_elem_info_get_min64")
  def snd_ctl_elem_info_get_min64(obj: Ptr[snd_ctl_elem_info_t]): CLongLong =
    extern

  /** Get maximum value from a SND_CTL_ELEM_TYPE_INTEGER64 CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   Maximum value
    */
  @name("snd_ctl_elem_info_get_max64")
  def snd_ctl_elem_info_get_max64(obj: Ptr[snd_ctl_elem_info_t]): CLongLong =
    extern

  /** Get value step from a SND_CTL_ELEM_TYPE_INTEGER64 CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   Step
    */
  @name("snd_ctl_elem_info_get_step64")
  def snd_ctl_elem_info_get_step64(obj: Ptr[snd_ctl_elem_info_t]): CLongLong =
    extern

  /** Get number of items available from a SND_CTL_ELEM_TYPE_ENUMERATED CTL
    * element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   items count
    */
  @name("snd_ctl_elem_info_get_items")
  def snd_ctl_elem_info_get_items(obj: Ptr[snd_ctl_elem_info_t]): CUnsignedInt =
    extern

  /** Select item in a SND_CTL_ELEM_TYPE_ENUMERATED CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   item number
    */
  @name("snd_ctl_elem_info_set_item")
  def snd_ctl_elem_info_set_item(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Get name for selected item in a SND_CTL_ELEM_TYPE_ENUMERATED CTL element
    * id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   name of chosen item
    */
  @name("snd_ctl_elem_info_get_item_name")
  def snd_ctl_elem_info_get_item_name(
      obj: Ptr[snd_ctl_elem_info_t]
  ): Ptr[CChar] = extern

  /** Get count of dimensions for given element.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   zero value if no dimensions are defined, otherwise positive value with
    *   count of dimensions
    */
  @name("snd_ctl_elem_info_get_dimensions")
  def snd_ctl_elem_info_get_dimensions(obj: Ptr[snd_ctl_elem_info_t]): CInt =
    extern

  /** Get specified of dimension width for given element.
    *
    * @param obj
    *   CTL element id/info
    * @param idx
    *   The dimension index
    * @return
    *   zero value if no dimension width is defined, otherwise positive value
    *   with with of specified dimension
    */
  @name("snd_ctl_elem_info_get_dimension")
  def snd_ctl_elem_info_get_dimension(
      obj: Ptr[snd_ctl_elem_info_t],
      idx: CUnsignedInt
  ): CInt = extern

  /** Set width to a specified dimension level of given element information.
    *
    * @param info
    *   Information of an element.
    * @param dimension
    *   Dimension width for each level by member unit.
    * @return
    *   Zero on success, otherwise a negative error code.
    */
  @name("snd_ctl_elem_info_set_dimension")
  def snd_ctl_elem_info_set_dimension(
      info: Ptr[snd_ctl_elem_info_t],
      dimension: CArray[CInt, Nat._4]
  ): CInt = extern

  /** Get CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param ptr
    *   Pointer to returned CTL element identifier
    */
  @name("snd_ctl_elem_info_get_id")
  def snd_ctl_elem_info_get_id(
      obj: Ptr[snd_ctl_elem_info_t],
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** Get element numeric identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   element numeric identifier
    */
  @name("snd_ctl_elem_info_get_numid")
  def snd_ctl_elem_info_get_numid(obj: Ptr[snd_ctl_elem_info_t]): CUnsignedInt =
    extern

  /** Get interface part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   interface part of element identifier
    */
  @name("snd_ctl_elem_info_get_interface")
  def snd_ctl_elem_info_get_interface(
      obj: Ptr[snd_ctl_elem_info_t]
  ): snd_ctl_elem_iface_t = extern

  /** Get device part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   device part of element identifier
    */
  @name("snd_ctl_elem_info_get_device")
  def snd_ctl_elem_info_get_device(
      obj: Ptr[snd_ctl_elem_info_t]
  ): CUnsignedInt = extern

  /** Get subdevice part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   subdevice part of element identifier
    */
  @name("snd_ctl_elem_info_get_subdevice")
  def snd_ctl_elem_info_get_subdevice(
      obj: Ptr[snd_ctl_elem_info_t]
  ): CUnsignedInt = extern

  /** Get name part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   name part of element identifier
    */
  @name("snd_ctl_elem_info_get_name")
  def snd_ctl_elem_info_get_name(obj: Ptr[snd_ctl_elem_info_t]): Ptr[CChar] =
    extern

  /** Get index part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @return
    *   index part of element identifier
    */
  @name("snd_ctl_elem_info_get_index")
  def snd_ctl_elem_info_get_index(obj: Ptr[snd_ctl_elem_info_t]): CUnsignedInt =
    extern

  /** Set CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param ptr
    *   CTL element identifier
    */
  @name("snd_ctl_elem_info_set_id")
  def snd_ctl_elem_info_set_id(
      obj: Ptr[snd_ctl_elem_info_t],
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** Set element numeric identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   element numeric identifier
    */
  @name("snd_ctl_elem_info_set_numid")
  def snd_ctl_elem_info_set_numid(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set interface part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   interface part of element identifier
    */
  @name("snd_ctl_elem_info_set_interface")
  def snd_ctl_elem_info_set_interface(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: snd_ctl_elem_iface_t
  ): Unit = extern

  /** Set device part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   device part of element identifier
    */
  @name("snd_ctl_elem_info_set_device")
  def snd_ctl_elem_info_set_device(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set subdevice part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   subdevice part of element identifier
    */
  @name("snd_ctl_elem_info_set_subdevice")
  def snd_ctl_elem_info_set_subdevice(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set name part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   name part of element identifier
    */
  @name("snd_ctl_elem_info_set_name")
  def snd_ctl_elem_info_set_name(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: Ptr[CChar]
  ): Unit = extern

  /** Set index part of CTL element identifier of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   index part of element identifier
    */
  @name("snd_ctl_elem_info_set_index")
  def snd_ctl_elem_info_set_index(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Set readability/writeability parameter of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param rval
    *   readability part of element identifier
    * @param wval
    *   writeability part of element identifier
    */
  @name("snd_ctl_elem_info_set_read_write")
  def snd_ctl_elem_info_set_read_write(
      obj: Ptr[snd_ctl_elem_info_t],
      rval: CInt,
      wval: CInt
  ): Unit = extern

  /** Set TLV readability/writeability parameter of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param rval
    *   TLV readability part of element identifier
    * @param wval
    *   TLV writeability part of element identifier
    */
  @name("snd_ctl_elem_info_set_tlv_read_write")
  def snd_ctl_elem_info_set_tlv_read_write(
      obj: Ptr[snd_ctl_elem_info_t],
      rval: CInt,
      wval: CInt
  ): Unit = extern

  /** Set inactive parameter of a CTL element id/info.
    *
    * @param obj
    *   CTL element id/info
    * @param val
    *   inactive part of element identifier
    */
  @name("snd_ctl_elem_info_set_inactive")
  def snd_ctl_elem_info_set_inactive(
      obj: Ptr[snd_ctl_elem_info_t],
      `val`: CInt
  ): Unit = extern

  /** Create and add some user-defined control elements of integer type.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param info
    *   Common information for a new element set, with ID of the first new
    *   element.
    * @param element_count
    *   The number of elements added by this operation.
    * @param member_count
    *   The number of members which a element has to represent its states.
    * @param min
    *   Minimum value for each member of the elements.
    * @param max
    *   Maximum value for each member of the elements.
    * @param step
    *   The step of value for each member in the elements.
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function creates some user elements with integer type. These elements
    * are not controlled by device drivers in kernel. They can be operated by
    * the same way as usual elements added by the device drivers.
    *
    * The name field of id must be set with unique value to identify new control
    * elements. After returning, all fields of id are filled. A element can be
    * identified by the combination of name and index, or by numid.
    *
    * All of members in the new elements are locked. The value of each member is
    * initialized with the minimum value.
    */
  @name("snd_ctl_add_integer_elem_set")
  def snd_ctl_add_integer_elem_set(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_elem_info_t],
      element_count: CUnsignedInt,
      member_count: CUnsignedInt,
      min: CLong,
      max: CLong,
      step: CLong
  ): CInt = extern

  /** Create and add some user-defined control elements of integer64 type.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param info
    *   Common information for a new element set, with ID of the first new
    *   element.
    * @param element_count
    *   The number of elements added by this operation.
    * @param member_count
    *   The number of members which a element has to represent its states.
    * @param min
    *   Minimum value for each member of the elements.
    * @param max
    *   Maximum value for each member of the elements.
    * @param step
    *   The step of value for each member in the elements.
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function creates some user elements with integer64 type. These
    * elements are not controlled by device drivers in kernel. They can be
    * operated by the same way as usual elements added by the device drivers.
    *
    * The name field of id must be set with unique value to identify new control
    * elements. After returning, all fields of id are filled. A element can be
    * identified by the combination of name and index, or by numid.
    *
    * All of members in the new elements are locked. The value of each member is
    * initialized with the minimum value.
    */
  @name("snd_ctl_add_integer64_elem_set")
  def snd_ctl_add_integer64_elem_set(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_elem_info_t],
      element_count: CUnsignedInt,
      member_count: CUnsignedInt,
      min: CLongLong,
      max: CLongLong,
      step: CLongLong
  ): CInt = extern

  /** Create and add some user-defined control elements of boolean type.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param info
    *   Common information for a new element set, with ID of the first new
    *   element.
    * @param element_count
    *   The number of elements added by this operation.
    * @param member_count
    *   The number of members which a element has to represent its states.
    *
    * This function creates some user elements with boolean type. These elements
    * are not controlled by device drivers in kernel. They can be operated by
    * the same way as usual elements added by the device drivers.
    *
    * The name field of id must be set with unique value to identify new control
    * elements. After returning, all fields of id are filled. A element can be
    * identified by the combination of name and index, or by numid.
    *
    * All of members in the new elements are locked. The value of each member is
    * initialized with false.
    */
  @name("snd_ctl_add_boolean_elem_set")
  def snd_ctl_add_boolean_elem_set(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_elem_info_t],
      element_count: CUnsignedInt,
      member_count: CUnsignedInt
  ): CInt = extern

  /** Create and add some user-defined control elements of enumerated type.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param info
    *   Common information for a new element set, with ID of the first new
    *   element.
    * @param element_count
    *   The number of elements added by this operation.
    * @param member_count
    *   The number of members which a element has to represent its states.
    * @param items
    *   Range of possible values (0 ... items - 1).
    * @param labels
    *   An array containing items strings.
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function creates some user elements with enumerated type. These
    * elements are not controlled by device drivers in kernel. They can be
    * operated by the same way as usual elements added by the device drivers.
    *
    * The name field of id must be set with unique value to identify new control
    * elements. After returning, all fields of id are filled. A element can be
    * identified by the combination of name and index, or by numid.
    *
    * All of members in the new elements are locked. The value of each member is
    * initialized with the first entry of labels.
    */
  @name("snd_ctl_add_enumerated_elem_set")
  def snd_ctl_add_enumerated_elem_set(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_elem_info_t],
      element_count: CUnsignedInt,
      member_count: CUnsignedInt,
      items: CUnsignedInt,
      labels: Ptr[Ptr[CChar]]
  ): CInt = extern

  /** Create and add some user-defined control elements of bytes type.
    *
    * @param ctl
    *   A handle of backend module for control interface.
    * @param info
    *   Common information for a new element set, with ID of the first new
    *   element.
    * @param element_count
    *   The number of elements added by this operation.
    * @param member_count
    *   The number of members which a element has to represent its states.
    * @return
    *   Zero on success, otherwise a negative error code.
    *
    * This function creates some user elements with bytes type. These elements
    * are not controlled by device drivers in kernel. They can be operated by
    * the same way as usual elements added by the device drivers.
    *
    * The name field of id must be set with unique value to identify new control
    * elements. After returning, all fields of id are filled. A element can be
    * identified by the combination of name and index, or by numid.
    *
    * All of members in the new elements are locked. The value of each member is
    * initialized with the minimum value.
    */
  @name("snd_ctl_add_bytes_elem_set")
  def snd_ctl_add_bytes_elem_set(
      ctl: Ptr[snd_ctl_t],
      info: Ptr[snd_ctl_elem_info_t],
      element_count: CUnsignedInt,
      member_count: CUnsignedInt
  ): CInt = extern

  /** This is a wrapper function to snd_ctl_add_integer_elem_set() for a control
    * element. This doesn't fill the id data with full information, thus it's
    * recommended to use snd_ctl_add_integer_elem_set(), instead.
    */
  @name("snd_ctl_elem_add_integer")
  def snd_ctl_elem_add_integer(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      count: CUnsignedInt,
      imin: CLong,
      imax: CLong,
      istep: CLong
  ): CInt = extern

  /** This is a wrapper function to snd_ctl_add_integer64_elem_set() for a
    * single control element. This doesn't fill the id data with full
    * information, thus it's recommended to use
    * snd_ctl_add_integer64_elem_set(), instead.
    */
  @name("snd_ctl_elem_add_integer64")
  def snd_ctl_elem_add_integer64(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      count: CUnsignedInt,
      imin: CLongLong,
      imax: CLongLong,
      istep: CLongLong
  ): CInt = extern

  /** This is a wrapper function to snd_ctl_add_boolean_elem_set() for a single
    * control element. This doesn't fill the id data with full information, thus
    * it's recommended to use snd_ctl_add_boolean_elem_set(), instead.
    */
  @name("snd_ctl_elem_add_boolean")
  def snd_ctl_elem_add_boolean(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      count: CUnsignedInt
  ): CInt = extern

  /** This is a wrapper function to snd_ctl_add_enumerated_elem_set() for a
    * single control element. This doesn't fill the id data with full
    * information, thus it's recommended to use
    * snd_ctl_add_enumerated_elem_set(), instead.
    *
    * This function is added in version 1.0.25.
    */
  @name("snd_ctl_elem_add_enumerated")
  def snd_ctl_elem_add_enumerated(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      count: CUnsignedInt,
      items: CUnsignedInt,
      names: Ptr[Ptr[CChar]]
  ): CInt = extern

  /** Create and add a user-defined control element of IEC958 type.
    *
    * @param ctl
    *   [in] A handle of backend module for control interface.
    * @param id
    *   [in,out] ID of the new control element.
    *
    * This function creates an user element with IEC958 type. This element is
    * not controlled by device drivers in kernel. It can be operated by the same
    * way as usual elements added by the device drivers.
    *
    * The name field of id must be set with unique value to identify a new
    * control element. After returning, all fields of id are filled. A element
    * can be identified by the combination of name and index, or by numid.
    *
    * A member in the new element is locked and filled with zero.
    */
  @name("snd_ctl_elem_add_iec958")
  def snd_ctl_elem_add_iec958(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t]
  ): CInt = extern

  /** Remove an user CTL element.
    *
    * @param ctl
    *   CTL handle
    * @param id
    *   CTL element identification
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_ctl_elem_remove")
  def snd_ctl_elem_remove(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t]
  ): CInt = extern

  /** Get size of data structure for an element.
    *
    * @return
    *   Size in bytes.
    */
  @name("snd_ctl_elem_value_sizeof")
  def snd_ctl_elem_value_sizeof(): CSize = extern

  /** Allocate space for a value object on the heap. The allocated memory must
    * be freed using snd_ctl_elem_value_free().
    *
    * See snd_ctl_elem_value_t for details.
    */
  @name("snd_ctl_elem_value_malloc")
  def snd_ctl_elem_value_malloc(ptr: Ptr[Ptr[snd_ctl_elem_value_t]]): CInt =
    extern

  /** Free an snd_ctl_elem_value_t previously allocated using
    * snd_ctl_elem_value_malloc().
    *
    * @param obj
    *   Pointer to the snd_ctl_elem_value_t.
    */
  @name("snd_ctl_elem_value_free")
  def snd_ctl_elem_value_free(obj: Ptr[snd_ctl_elem_value_t]): Unit = extern

  /** See snd_ctl_elem_value_t for details.
    */
  @name("snd_ctl_elem_value_clear")
  def snd_ctl_elem_value_clear(obj: Ptr[snd_ctl_elem_value_t]): Unit = extern

  /** Bitwise copy of a snd_ctl_elem_value_t value.
    *
    * @param dst
    *   Pointer to destination.
    * @param src
    *   Pointer to source.
    */
  @name("snd_ctl_elem_value_copy")
  def snd_ctl_elem_value_copy(
      dst: Ptr[snd_ctl_elem_value_t],
      src: Ptr[snd_ctl_elem_value_t]
  ): Unit = extern

  /** Compare two snd_ctl_elem_value_t values, bytewise.
    *
    * @param left
    *   First value.
    * @param right
    *   Second value.
    * @return
    *   0 on match, less than or greater than otherwise, see memcmp(3).
    */
  @name("snd_ctl_elem_value_compare")
  def snd_ctl_elem_value_compare(
      left: Ptr[snd_ctl_elem_value_t],
      right: Ptr[snd_ctl_elem_value_t]
  ): CInt = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_id")
  def snd_ctl_elem_value_get_id(
      obj: Ptr[snd_ctl_elem_value_t],
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_numid")
  def snd_ctl_elem_value_get_numid(
      obj: Ptr[snd_ctl_elem_value_t]
  ): CUnsignedInt = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_interface")
  def snd_ctl_elem_value_get_interface(
      obj: Ptr[snd_ctl_elem_value_t]
  ): snd_ctl_elem_iface_t = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_device")
  def snd_ctl_elem_value_get_device(
      obj: Ptr[snd_ctl_elem_value_t]
  ): CUnsignedInt = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_subdevice")
  def snd_ctl_elem_value_get_subdevice(
      obj: Ptr[snd_ctl_elem_value_t]
  ): CUnsignedInt = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_name")
  def snd_ctl_elem_value_get_name(obj: Ptr[snd_ctl_elem_value_t]): Ptr[CChar] =
    extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_get_index")
  def snd_ctl_elem_value_get_index(
      obj: Ptr[snd_ctl_elem_value_t]
  ): CUnsignedInt = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_id")
  def snd_ctl_elem_value_set_id(
      obj: Ptr[snd_ctl_elem_value_t],
      ptr: Ptr[snd_ctl_elem_id_t]
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_numid")
  def snd_ctl_elem_value_set_numid(
      obj: Ptr[snd_ctl_elem_value_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_interface")
  def snd_ctl_elem_value_set_interface(
      obj: Ptr[snd_ctl_elem_value_t],
      `val`: snd_ctl_elem_iface_t
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_device")
  def snd_ctl_elem_value_set_device(
      obj: Ptr[snd_ctl_elem_value_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_subdevice")
  def snd_ctl_elem_value_set_subdevice(
      obj: Ptr[snd_ctl_elem_value_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_name")
  def snd_ctl_elem_value_set_name(
      obj: Ptr[snd_ctl_elem_value_t],
      `val`: Ptr[CChar]
  ): Unit = extern

  /** See snd_ctl_elem_value_t for more details.
    */
  @name("snd_ctl_elem_value_set_index")
  def snd_ctl_elem_value_set_index(
      obj: Ptr[snd_ctl_elem_value_t],
      `val`: CUnsignedInt
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_BOOLEAN.
    * It returns the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_get_boolean")
  def snd_ctl_elem_value_get_boolean(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt
  ): CInt = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_INTEGER.
    * It returns the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_get_integer")
  def snd_ctl_elem_value_get_integer(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt
  ): CLong = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_INTEGER64.
    * It returns the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_get_integer64")
  def snd_ctl_elem_value_get_integer64(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt
  ): CLongLong = extern

  /** Use this function if the element is of type
    * SNDRV_CTL_ELEM_TYPE_ENUMERATED. It returns the index of the active item.
    * See snd_ctl_elem_value_t and Control interface for more details.
    */
  @name("snd_ctl_elem_value_get_enumerated")
  def snd_ctl_elem_value_get_enumerated(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt
  ): CUnsignedInt = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_BYTE. It
    * returns the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_get_byte")
  def snd_ctl_elem_value_get_byte(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt
  ): CUnsignedChar = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_BOOLEAN.
    * It sets the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_set_boolean")
  def snd_ctl_elem_value_set_boolean(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt,
      `val`: CLong
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_INTEGER.
    * It sets the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_set_integer")
  def snd_ctl_elem_value_set_integer(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt,
      `val`: CLong
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_INTEGER64.
    * It sets the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_set_integer64")
  def snd_ctl_elem_value_set_integer64(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt,
      `val`: CLongLong
  ): Unit = extern

  /** Use this function if the element is of type
    * SNDRV_CTL_ELEM_TYPE_ENUMERATED. It activates the specified item. See
    * snd_ctl_elem_value_t and Control interface for more details.
    */
  @name("snd_ctl_elem_value_set_enumerated")
  def snd_ctl_elem_value_set_enumerated(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt,
      `val`: CUnsignedInt
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_BYTE. It
    * sets the value of one member. See snd_ctl_elem_value_t and Control
    * interface for more details.
    */
  @name("snd_ctl_elem_value_set_byte")
  def snd_ctl_elem_value_set_byte(
      obj: Ptr[snd_ctl_elem_value_t],
      idx: CUnsignedInt,
      `val`: CUnsignedChar
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_BYTES. It
    * replaces the data stored in the element. Note that "bytes" elements don't
    * have members. They have only one single block of data.
    *
    * See snd_ctl_elem_value_t and Control interface for more details.
    */
  @name("snd_ctl_elem_set_bytes")
  def snd_ctl_elem_set_bytes(
      obj: Ptr[snd_ctl_elem_value_t],
      data: CVoidPtr,
      size: CSize
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_BYTES. It
    * returns the data stored in the element. Note that "bytes" elements don't
    * have members. They have only one single block of data.
    *
    * See snd_ctl_elem_value_t and Control interface for more details.
    */
  @name("snd_ctl_elem_value_get_bytes")
  def snd_ctl_elem_value_get_bytes(obj: Ptr[snd_ctl_elem_value_t]): CVoidPtr =
    extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_IEC958.
    * Note that "IEC958" elements don't have members. They have only one single
    * IEC958 information block.
    *
    * See snd_ctl_elem_value_t and Control interface for more details.
    */
  @name("snd_ctl_elem_value_get_iec958")
  def snd_ctl_elem_value_get_iec958(
      obj: Ptr[snd_ctl_elem_value_t],
      ptr: Ptr[snd_aes_iec958_t]
  ): Unit = extern

  /** Use this function if the element is of type SNDRV_CTL_ELEM_TYPE_IEC958.
    * Note that "IEC958" elements don't have members. They have only one single
    * IEC958 information block.
    *
    * See snd_ctl_elem_value_t and Control interface for more details.
    */
  @name("snd_ctl_elem_value_set_iec958")
  def snd_ctl_elem_value_set_iec958(
      obj: Ptr[snd_ctl_elem_value_t],
      ptr: Ptr[snd_aes_iec958_t]
  ): Unit = extern

  /** Parse TLV stream and retrieve dB information.
    *
    * @param tlv
    *   the TLV source
    * @param tlv_size
    *   the byte size of TLV source
    * @param db_tlvp
    *   the pointer stored the dB TLV information
    * @return
    *   the byte size of dB TLV information if found in the given TLV source, or
    *   a negative error code.
    *
    * This function parses the given TLV source and stores the TLV start point
    * if the TLV information regarding dB conversion is found. The stored TLV
    * pointer can be passed to the convesion functions snd_tlv_convert_to_dB(),
    * snd_tlv_convert_from_dB() and snd_tlv_get_dB_range().
    */
  @name("snd_tlv_parse_dB_info")
  def snd_tlv_parse_dB_info(
      tlv: Ptr[CUnsignedInt],
      tlv_size: CUnsignedInt,
      db_tlvp: Ptr[Ptr[CUnsignedInt]]
  ): CInt = extern

  /** Get the dB min/max values.
    *
    * @param tlv
    *   the TLV source returned by snd_tlv_parse_dB_info()
    * @param rangemin
    *   the minimum value of the raw volume
    * @param rangemax
    *   the maximum value of the raw volume
    * @param min
    *   the pointer to store the minimum dB value (in 0.01dB unit)
    * @param max
    *   the pointer to store the maximum dB value (in 0.01dB unit)
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_tlv_get_dB_range")
  def snd_tlv_get_dB_range(
      tlv: Ptr[CUnsignedInt],
      rangemin: CLong,
      rangemax: CLong,
      min: Ptr[CLong],
      max: Ptr[Long]
  ): CInt = extern

  /** Convert the given raw volume value to a dB gain.
    *
    * @param tlv
    *   the TLV source returned by snd_tlv_parse_dB_info()
    * @param rangemin
    *   the minimum value of the raw volume
    * @param rangemax
    *   the maximum value of the raw volume
    * @param volume
    *   the raw volume value to convert
    * @param db_gain
    *   the dB gain (in 0.01dB unit)
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_tlv_convert_to_dB")
  def snd_tlv_convert_to_dB(
      tlv: Ptr[CUnsignedInt],
      rangemin: CLong,
      rangemax: CLong,
      volume: CLong,
      db_gain: Ptr[CLong]
  ): CInt = extern

  /** Convert from dB gain to the corresponding raw value.
    *
    * @param tlv
    *   the TLV source returned by snd_tlv_parse_dB_info()
    * @param rangemin
    *   the minimum value of the raw volume
    * @param rangemax
    *   the maximum value of the raw volume
    * @param db_gain
    *   the dB gain to convert (in 0.01dB unit)
    * @param value
    *   the pointer to store the converted raw volume value
    * @param xdir
    *   the direction for round-up. The value is round up when this is positive.
    *   A negative value means round down. Zero means round-up to nearest.
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_tlv_convert_from_dB")
  def snd_tlv_convert_from_dB(
      tlv: Ptr[CUnsignedInt],
      rangemin: CLong,
      rangemax: CLong,
      db_gain: CLong,
      value: Ptr[CLong],
      xdir: CInt
  ): CInt = extern

  /** Get the dB min/max values on the given control element.
    *
    * @param ctl
    *   the control handler
    * @param id
    *   the element id
    * @param min
    *   the pointer to store the minimum dB value (in 0.01dB unit)
    * @param max
    *   the pointer to store the maximum dB value (in 0.01dB unit)
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_ctl_get_dB_range")
  def snd_ctl_get_dB_range(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      min: Ptr[CLong],
      max: Ptr[CLong]
  ): CInt = extern

  /** Convert the volume value to dB on the given control element.
    *
    * @param ctl
    *   the control handler
    * @param id
    *   the element id
    * @param volume
    *   the raw volume value to convert
    * @param db_gain
    *   the dB gain (in 0.01dB unit)
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_ctl_convert_to_dB")
  def snd_ctl_convert_to_dB(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      volume: CLong,
      db_gain: Ptr[CLong]
  ): CInt = extern

  /** Convert from dB gain to the raw volume value on the given control element.
    *
    * @param ctl
    *   the control handler
    * @param id
    *   the element id
    * @param db_gain
    *   the dB gain to convert (in 0.01dB unit)
    * @param value
    *   the pointer to store the converted raw volume value
    * @param xdir
    *   the direction for round-up. The value is round up when this is positive.
    * @return
    *   0 if successful, or a negative error code
    */
  @name("snd_ctl_convert_from_dB")
  def snd_ctl_convert_from_dB(
      ctl: Ptr[snd_ctl_t],
      id: Ptr[snd_ctl_elem_id_t],
      db_gain: CLong,
      value: Ptr[CLong],
      xdir: CInt
  ): CInt = extern

}
