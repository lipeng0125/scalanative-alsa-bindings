package gweb.alsa.MIDISequencer

import scala.scalanative.unsafe.*

import gweb.alsa.MIDISequencer.MIDISequencer.snd_seq_t
import gweb.alsa.MIDISequencer.SequencerEventDefinitions.snd_seq_addr_t
import gweb.alsa.Iota

/** Sequencer Port Subscription
  */
@link("asound")
@extern
object SequencerPortSubscription {

  /** port subscription container
    */
  type snd_seq_port_subscribe_t = CStruct0

  /** subscription query container
    */
  type snd_seq_query_subscribe_t = CStruct0

  /** type of query subscription
    */
  class snd_seq_query_subs_type_t private (val value: CInt) extends AnyVal

  object snd_seq_query_subs_type_t extends Iota {

    /** query read subscriptions */
    val SND_SEQ_QUERY_SUBS_READ = iota

    /** query write subscriptions */
    val SND_SEQ_QUERY_SUBS_WRITE = iota

  }

  /** get size of snd_seq_port_subscribe_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_port_subscribe_sizeof")
  def snd_seq_port_subscribe_sizeof(): CSize = extern

  /** allocate an empty snd_seq_port_subscribe_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_port_subscribe_malloc")
  def snd_seq_port_subscribe_malloc(
      ptr: Ptr[Ptr[snd_seq_port_subscribe_t]]
  ): CInt = extern

  /** frees a previously allocated snd_seq_port_subscribe_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_port_subscribe_free")
  def snd_seq_port_subscribe_free(ptr: Ptr[snd_seq_port_subscribe_t]): Unit =
    extern

  /** copy one snd_seq_port_subscribe_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_port_subscribe_copy")
  def snd_seq_port_subscribe_copy(
      dst: Ptr[snd_seq_port_subscribe_t],
      src: Ptr[snd_seq_port_subscribe_t]
  ): Unit = extern

  /** Get sender address of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    */
  @name("snd_seq_port_subscribe_get_sender")
  def snd_seq_port_subscribe_get_sender(
      info: Ptr[snd_seq_port_subscribe_t]
  ): Ptr[snd_seq_addr_t] = extern

  /** Get destination address of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    */
  @name("snd_seq_port_subscribe_get_dest")
  def snd_seq_port_subscribe_get_dest(
      info: Ptr[snd_seq_port_subscribe_t]
  ): Ptr[snd_seq_addr_t] = extern

  /** Get the queue id of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @return
    *   queue id
    */
  @name("snd_seq_port_subscribe_get_queue")
  def snd_seq_port_subscribe_get_queue(
      info: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** Get the exclusive mode of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @return
    *   1 if exclusive mode
    */
  @name("snd_seq_port_subscribe_get_exclusive")
  def snd_seq_port_subscribe_get_exclusive(
      info: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** Get the time-update mode of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @return
    *   1 if update timestamp
    */
  @name("snd_seq_port_subscribe_get_time_update")
  def snd_seq_port_subscribe_get_time_update(
      info: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** Get the real-time update mode of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @return
    *   1 if real-time update mode
    */
  @name("snd_seq_port_subscribe_get_time_real")
  def snd_seq_port_subscribe_get_time_real(
      info: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** Set sender address of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @param addr
    *   sender address
    */
  @name("snd_seq_port_subscribe_set_sender")
  def snd_seq_port_subscribe_set_sender(
      info: Ptr[snd_seq_port_subscribe_t],
      addr: Ptr[snd_seq_addr_t]
  ): Unit = extern

  /** Set destination address of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @param addr
    *   destination address
    */
  @name("snd_seq_port_subscribe_set_dest")
  def snd_seq_port_subscribe_set_dest(
      info: Ptr[snd_seq_port_subscribe_t],
      addr: Ptr[snd_seq_addr_t]
  ): Unit = extern

  /** Set the queue id of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @param q
    *   queue id
    */
  @name("snd_seq_port_subscribe_set_queue")
  def snd_seq_port_subscribe_set_queue(
      info: Ptr[snd_seq_port_subscribe_t],
      q: CInt
  ): Unit = extern

  /** Set the exclusive mode of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @param val
    *   non-zero to enable
    */
  @name("snd_seq_port_subscribe_set_exclusive")
  def snd_seq_port_subscribe_set_exclusive(
      info: Ptr[snd_seq_port_subscribe_t],
      `val`: CInt
  ): Unit = extern

  /** Set the time-update mode of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @param val
    *   non-zero to enable
    */
  @name("snd_seq_port_subscribe_set_time_update")
  def snd_seq_port_subscribe_set_time_update(
      info: Ptr[snd_seq_port_subscribe_t],
      `val`: CInt
  ): Unit = extern

  /** Set the real-time mode of a port_subscribe container.
    *
    * @param info
    *   port_subscribe container
    * @param val
    *   non-zero to enable
    */
  @name("snd_seq_port_subscribe_set_time_real")
  def snd_seq_port_subscribe_set_time_real(
      info: Ptr[snd_seq_port_subscribe_t],
      `val`: CInt
  ): Unit = extern

  /** obtain subscription information
    *
    * @param seq
    *   sequencer handle
    * @param sub
    *   pointer to return the subscription information
    * @return
    *   0 on success otherwise a negative error code
    */
  @name("snd_seq_get_port_subscription")
  def snd_seq_get_port_subscription(
      handle: Ptr[snd_seq_t],
      sub: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** subscribe a port connection
    *
    * @param seq
    *   sequencer handle
    * @param sub
    *   subscription information
    * @return
    *   0 on success otherwise a negative error code
    *
    * Subscribes a connection between two ports. The subscription information is
    * stored in sub argument.
    */
  @name("snd_seq_subscribe_port")
  def snd_seq_subscribe_port(
      handle: Ptr[snd_seq_t],
      sub: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** unsubscribe a connection between ports
    *
    * @param seq
    *   sequencer handle
    * @param sub
    *   subscription information to disconnect
    * @return
    *   0 on success otherwise a negative error code
    *
    * Unsubscribes a connection between two ports, described in sender and dest
    * fields in sub argument.
    */
  @name("snd_seq_unsubscribe_port")
  def snd_seq_unsubscribe_port(
      handle: Ptr[snd_seq_t],
      sub: Ptr[snd_seq_port_subscribe_t]
  ): CInt = extern

  /** get size of snd_seq_query_subscribe_t
    *
    * @return
    *   size in bytes
    */
  @name("snd_seq_query_subscribe_sizeof")
  def snd_seq_query_subscribe_sizeof(): CSize = extern

  /** allocate an empty snd_seq_query_subscribe_t using standard malloc
    *
    * @param ptr
    *   returned pointer
    * @return
    *   0 on success otherwise negative error code
    */
  @name("snd_seq_query_subscribe_malloc")
  def snd_seq_query_subscribe_malloc(
      ptr: Ptr[Ptr[snd_seq_query_subscribe_t]]
  ): CInt = extern

  /** frees a previously allocated snd_seq_query_subscribe_t
    *
    * @param obj
    *   pointer to object to free
    */
  @name("snd_seq_query_subscribe_free")
  def snd_seq_query_subscribe_free(ptr: Ptr[snd_seq_query_subscribe_t]): Unit =
    extern

  /** copy one snd_seq_query_subscribe_t to another
    *
    * @param dst
    *   pointer to destination
    * @param src
    *   pointer to source
    */
  @name("snd_seq_query_subscribe_copy")
  def snd_seq_query_subscribe_copy(
      dst: Ptr[snd_seq_query_subscribe_t],
      src: Ptr[snd_seq_query_subscribe_t]
  ): Unit = extern

  /** Get the client id of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   client id
    */
  @name("snd_seq_query_subscribe_get_client")
  def snd_seq_query_subscribe_get_client(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the port id of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   port id
    */
  @name("snd_seq_query_subscribe_get_port")
  def snd_seq_query_subscribe_get_port(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the client/port address of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   client/port address pointer
    */
  @name("snd_seq_query_subscribe_get_root")
  def snd_seq_query_subscribe_get_root(
      info: Ptr[snd_seq_query_subscribe_t]
  ): Ptr[snd_seq_addr_t] = extern

  /** Get the query type of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   query type
    */
  @name("snd_seq_query_subscribe_get_type")
  def snd_seq_query_subscribe_get_type(
      info: Ptr[snd_seq_query_subscribe_t]
  ): snd_seq_query_subs_type_t = extern

  /** Get the index of subscriber of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   subscriber's index
    */
  @name("snd_seq_query_subscribe_get_index")
  def snd_seq_query_subscribe_get_index(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the number of subscriptions of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   number of subscriptions
    */
  @name("snd_seq_query_subscribe_get_num_subs")
  def snd_seq_query_subscribe_get_num_subs(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the address of subscriber of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   subscriber's address pointer
    */
  @name("snd_seq_query_subscribe_get_addr")
  def snd_seq_query_subscribe_get_addr(
      info: Ptr[snd_seq_query_subscribe_t]
  ): Ptr[snd_seq_addr_t] = extern

  /** Get the queue id of subscriber of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   subscriber's queue id
    */
  @name("snd_seq_query_subscribe_get_queue")
  def snd_seq_query_subscribe_get_queue(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the exclusive mode of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   1 if exclusive mode
    */
  @name("snd_seq_query_subscribe_get_exclusive")
  def snd_seq_query_subscribe_get_exclusive(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the time-update mode of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   1 if update timestamp
    */
  @name("snd_seq_query_subscribe_get_time_update")
  def snd_seq_query_subscribe_get_time_update(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Get the real-time update mode of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @return
    *   1 if real-time update mode
    */
  @name("snd_seq_query_subscribe_get_time_real")
  def snd_seq_query_subscribe_get_time_real(
      info: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

  /** Set the client id of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @param client
    *   client id
    */
  @name("snd_seq_query_subscribe_set_client")
  def snd_seq_query_subscribe_set_client(
      info: Ptr[snd_seq_query_subscribe_t],
      client: CInt
  ): Unit = extern

  /** Set the port id of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @param port
    *   port id
    */
  @name("snd_seq_query_subscribe_set_port")
  def snd_seq_query_subscribe_set_port(
      info: Ptr[snd_seq_query_subscribe_t],
      port: CInt
  ): Unit = extern

  /** Set the client/port address of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @param addr
    *   client/port address pointer
    */
  @name("snd_seq_query_subscribe_set_root")
  def snd_seq_query_subscribe_set_root(
      info: Ptr[snd_seq_query_subscribe_t],
      addr: Ptr[snd_seq_addr_t]
  ): Unit = extern

  /** Set the query type of a query_subscribe container.
    *
    * @param info
    *   query_subscribe container
    * @param type
    *   query type
    */
  @name("snd_seq_query_subscribe_set_type")
  def snd_seq_query_subscribe_set_type(
      info: Ptr[snd_seq_query_subscribe_t],
      `type`: snd_seq_query_subs_type_t
  ): Unit = extern

  /** Set the subscriber's index to be queried.
    *
    * @param info
    *   query_subscribe container
    * @param index
    *   index to be queried
    */
  @name("snd_seq_query_subscribe_set_index")
  def snd_seq_query_subscribe_set_index(
      info: Ptr[snd_seq_query_subscribe_t],
      _index: CInt
  ): Unit = extern

  /** query port subscriber list
    *
    * @param seq
    *   sequencer handle
    * @param subs
    *   subscription to query
    * @return
    *   0 on success otherwise a negative error code
    *
    * Queries the subscribers accessing to a port. The query information is
    * specified in subs argument.
    *
    * At least, the client id, the port id, the index number and the query type
    * must be set to perform a proper query. As the query type,
    * SND_SEQ_QUERY_SUBS_READ or SND_SEQ_QUERY_SUBS_WRITE can be specified to
    * check whether the readers or the writers to the port. To query the first
    * subscription, set 0 to the index number. To list up all the subscriptions,
    * call this function with the index numbers from 0 until this returns a
    * negative value.
    */
  @name("snd_seq_query_port_subscribers")
  def snd_seq_query_port_subscribers(
      seq: Ptr[snd_seq_t],
      subs: Ptr[snd_seq_query_subscribe_t]
  ): CInt = extern

}
