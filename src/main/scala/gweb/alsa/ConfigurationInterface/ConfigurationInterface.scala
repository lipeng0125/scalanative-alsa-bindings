package gweb.alsa.ConfigurationInterface

import scala.scalanative.unsafe.*

import gweb.alsa.InputInterface.snd_input_t
import gweb.alsa.OutputInterface.snd_output_t
import scala.scalanative.annotation.alwaysinline
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_t
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_iterator_first
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_iterator_next
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_iterator_end
import gweb.alsa.ConfigurationInterface.ConfigurationInterface.snd_config_iterator_t
import gweb.alsa.Iota

/** Helper macro to iterate over the children of a compound node.
  *
  * @param node
  *   Handle to the compound configuration node to iterate over.
  *
  * This macro allows deleting or removing the current node.
  *
  * @param callback
  *   [in,out] pos Iterator variable for the current node.
  *
  * [in,out] next Temporary iterator variable for the next node.
  */
@alwaysinline
@inline
inline def snd_config_for_each(
    node: Ptr[snd_config_t],
    callback: (pos: snd_config_iterator_t, next: snd_config_iterator_t) => Unit
): Unit = {
  var pos: snd_config_iterator_t = snd_config_iterator_first(node)
  var next: snd_config_iterator_t = snd_config_iterator_next(pos)
  while (pos != snd_config_iterator_end(node)) {
    pos = next
    next = snd_config_iterator_next(pos)
    callback(pos, next)
  }
}

/** The configuration functions and types allow you to read, enumerate, modify
  * and write the contents of ALSA configuration files.
  */
@link("asoud")
@extern
object ConfigurationInterface {

  /** Device-name list element (definition)
    *
    * Device-name list element
    */
  type snd_devname = CStruct3[
    /** Device name string
      */
    Ptr[CChar], // name
    /** Comments
      */
    Ptr[CChar], // comment
    /** Next pointer
      *
      * todo 解决引用循环， 应为Ptr[snd_devname_t]
      */
    CVoidPtr // next
  ]

  type snd_devname_t = snd_devname

  /** dlsym version for the config evaluate callback.
    */
  inline val SND_CONFIG_DLSYM_VERSION_EVALUATE = "_dlsym_config_evaluate_001"

  /** dlsym version for the config hook callback.
    */
  inline val SND_CONFIG_DLSYM_VERSION_HOOK = "_dlsym_config_hook_001"

  /** Internal structure for a configuration node object.
    *
    * The ALSA library uses a pointer to this structure as a handle to a
    * configuration node. Applications don't access its contents directly.
    */
  opaque type snd_config_t = CStruct0

  /** Type for a configuration compound iterator.
    *
    * The ALSA library uses this pointer type as a handle to a configuration
    * compound iterator. Applications don't directly access the contents of the
    * structure pointed to by this type.
    */
  type snd_config_iterator_t = Ptr[CStruct0]

  /** Internal structure for a configuration private update object.
    *
    * The ALSA library uses this structure to save private update information.
    */
  opaque type snd_config_update_t = CStruct0

  /** custom expansion callback
    *
    * Use a function of this type to define a custom expansion
    */
  type snd_config_expand_fcn_t =
    CFuncPtr3[
      /** @param dst
        *   [out] The function puts the handle to the new configuration node at
        *   the address specified by dst.
        */
      Ptr[Ptr[snd_config_t]],
      /** @param s [in]	string the string to be expanded */
      Ptr[CChar],
      /** @param private_data [in] Handle to the private_data node. */
      CVoidPtr,
      /** @return
        *   A non-negative value if successful, otherwise a negative error code.
        */
      CInt
    ]

  /** Configuration node type.
    */
  class snd_config_type_t private (val value: CInt) extends AnyVal

  object snd_config_type_t extends Iota {

    /** Integer number.
      */
    val SND_CONFIG_TYPE_INTEGER = iota

    /** 64-bit integer number.
      */
    val SND_CONFIG_TYPE_INTEGER64 = iota

    /** Real number.
      */
    val SND_CONFIG_TYPE_REAL = iota

    /** Character string.
      */
    val SND_CONFIG_TYPE_STRING = iota

    /** Pointer (runtime only, cannot be saved).
      */
    val SND_CONFIG_TYPE_POINTER = iota

    /** Compound node.
      */
    val SND_CONFIG_TYPE_COMPOUND = iota(1024)
  }

  /** Returns the default top-level config directory.
    * @return
    *   The top-level config directory path string
    *
    * This function returns the string of the top-level config directory path.
    * If the path is specified via the environment variable ALSA_CONFIG_DIR and
    * the value is a valid path, it returns this value. If unspecified, it
    * returns the default value, "/usr/share/alsa".
    */
  @name("snd_config_topdir")
  def snd_config_topdir(): Ptr[CChar] = extern

  /** Creates a top level configuration node.
    * @param config
    *   [out] Handle to the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * The returned node is an empty compound node without a parent and without
    * an id.
    */
  @name("snd_config_top")
  def snd_config_top(config: Ptr[Ptr[snd_config_t]]): CInt = extern

  /** Loads a configuration tree.
    * @param config
    *   Handle to a top level configuration node.
    * @param in
    *   Input handle to read the configuration from.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * The definitions loaded from the input are added to config, which must be a
    * compound node.
    */
  @name("snd_config_load")
  def snd_config_load(config: Ptr[snd_config_t], in: Ptr[snd_input_t]): CInt =
    extern

  /** Loads a configuration tree from a string.
    * @param config
    *   [out] The function puts the handle to the configuration node loaded from
    *   the file(s) at the address specified by config.
    * @param s
    *   [in] String with the ASCII configuration
    * @param size
    *   [in] String size, if zero, a C string is expected (with termination)
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * The definitions loaded from the string are put to config, which is created
    * as a new top node.
    */
  @name("snd_config_load_string")
  def snd_config_load_string(
      config: Ptr[Ptr[snd_config_t]],
      s: Ptr[CChar],
      size: CSize
  ): CInt = extern

  /** Loads a configuration tree and overrides existing configuration nodes.
    * @param config
    *   Handle to a top level configuration node.
    * @param in
    *   Input handle to read the configuration from.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function loads definitions from in into config like snd_config_load,
    * but the default mode for input nodes is 'override' (!) instead of
    * 'merge+create' (+).
    */
  @name("snd_config_load_override")
  def snd_config_load_override(
      config: Ptr[snd_config_t],
      in: Ptr[snd_input_t]
  ): CInt = extern

  /** Dumps the contents of a configuration node or tree.
    * @param config
    *   Handle to the (root) configuration node.
    * @param out
    *   Output handle.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function writes a textual representation of config's value to the
    * output out.
    */
  @name("snd_config_save")
  def snd_config_save(config: Ptr[snd_config_t], out: Ptr[snd_output_t]): CInt =
    extern

  /** Updates snd_config by rereading the global configuration files (if
    * needed).
    * @return
    *   0 if snd_config was up to date, 1 if snd_config was updated, otherwise a
    *   negative error code.
    */
  @name("snd_config_update")
  def snd_config_update(): CInt = extern

  /** Updates a configuration tree by rereading the configuration files (if
    * needed).
    * @param _top
    *   [in,out] Address of the handle to the top-level node.
    * @param _update
    *   [in,out] Address of a pointer to private update information.
    * @param cfgs
    *   [in] A list of configuration file names, delimited with ':'. If cfgs is
    *   NULL, the default global configuration file is used.
    * @return
    *   0 if _top was up to date, 1 if the configuration files have been reread,
    *   otherwise a negative error code.
    *
    * The variables pointed to by _top and _update can be initialized to NULL
    * before the first call to this function. The private update information
    * holds information about all used configuration files that allows this
    * function to detects changes to them; this data can be freed with
    * snd_config_update_free.
    *
    * The global configuration files are specified in the environment variable
    * ALSA_CONFIG_PATH.
    */
  @name("snd_config_update_r")
  def snd_config_update_r(
      top: Ptr[Ptr[snd_config_t]],
      update: Ptr[Ptr[snd_config_update_t]],
      path: Ptr[CChar]
  ): CInt = extern

  /** Frees a private update structure.
    * @param update
    *   [in] The private update structure to free.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_update_free")
  def snd_config_update_free(update: Ptr[snd_config_update_t]): CInt = extern

  /** Frees the global configuration tree in snd_config.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This functions releases all resources of the global configuration tree,
    * and sets snd_config to NULL.
    */
  @name("snd_config_update_free_global")
  def snd_config_update_free_global(): CInt = extern

  /** Updates snd_config and takes its reference.
    * @return
    *   0 if snd_config was up to date, 1 if snd_config was updated, otherwise a
    *   negative error code.
    *
    * Unlike snd_config_update, this function increases a reference counter so
    * that the obtained tree won't be deleted until unreferenced by
    * snd_config_unref.
    *
    * This function is supposed to be thread-safe.
    */
  @name("snd_config_update_ref")
  def snd_config_update_ref(top: Ptr[Ptr[snd_config_t]]): CInt = extern

  /** Increases a reference counter of the given config tree.
    *
    * This function is supposed to be thread-safe.
    */
  @name("snd_config_ref")
  def snd_config_ref(top: Ptr[snd_config_t]): Unit = extern

  /** Decreases a reference counter of the given config tree, and eventually
    * deletes the tree if all references are gone. This is the counterpart of
    * snd_config_unref.
    *
    * Also, the config taken via snd_config_update_ref must be unreferenced by
    * this function, too.
    *
    * This function is supposed to be thread-safe.
    */
  @name("snd_config_unref")
  def snd_config_unref(top: Ptr[snd_config_t]): Unit = extern

  /** Searches for a node in a configuration tree.
    * @param config
    *   [in] Handle to the root of the configuration (sub)tree to search.
    * @param key
    *   [in] Search key: one or more node ids, separated with dots.
    * @param result
    *   [out] When result != NULL, the function puts the handle to the node
    *   found at the address specified by result.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function searches for a child node of config that is identified by
    * key, which contains either the id of a direct child node of config, or a
    * series of ids, separated with dots, where each id specifies a node that is
    * contained in the previous compound node.
    *
    * In the following example, the comment after each node shows the search key
    * to find that node, assuming that config is a handle to the compound node
    * with id config:
    */
  @name("snd_config_search")
  def snd_config_search(
      config: Ptr[snd_config_t],
      key: Ptr[CChar],
      result: Ptr[Ptr[snd_config_t]]
  ): CInt = extern

  /** Searches for a node in a configuration tree.
    * @param config
    *   [in] Handle to the root of the configuration (sub)tree to search.
    * @param result
    *   [out] When result != NULL, the function puts the handle to the node
    *   found at the address specified by result.
    * @param args
    *   [in] One or more concatenated dot-separated search keys, terminated with
    *   NULL.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This functions searches for a child node of config like snd_config_search,
    * but the search key is the concatenation of all passed search key strings.
    * For example, the call
    *
    * is equivalent to the call
    */
  @name("snd_config_searchv")
  def snd_config_searchv(
      config: Ptr[snd_config_t],
      result: Ptr[Ptr[snd_config_t]],
      args: Any*
  ): CInt = extern

  /** Searches for a definition in a configuration tree, using aliases and
    * expanding hooks and arguments.
    * @param config
    *   [in] Handle to the configuration (sub)tree to search.
    * @param base
    *   [in] Implicit key base, or NULL for none.
    * @param name
    *   [in] Key suffix, optionally with arguments.
    * @param result
    *   [out] The function puts the handle to the expanded found node at the
    *   address specified by result.
    * @return
    *   A non-negative value if successful, otherwise a negative error code.
    *
    * This functions searches for a child node of config, allowing aliases and
    * expanding hooks, like snd_config_search_alias_hooks.
    *
    * If name contains a colon (:), the rest of the string after the colon
    * contains arguments that are expanded as with snd_config_expand.
    *
    * In any case, result is a new node that must be freed by the caller.
    */
  @name("snd_config_search_definition")
  def snd_config_search_definition(
      config: Ptr[snd_config_t],
      base: Ptr[CChar],
      key: Ptr[CChar],
      result: Ptr[Ptr[snd_config_t]]
  ): CInt = extern

  /** Expands a configuration node, applying arguments and functions.
    * @param config
    *   [in] Handle to the configuration node.
    * @param root
    *   [in] Handle to the root configuration node.
    * @param fcn
    *   [in] Custom function to obtain the referred variable name
    * @param private_data
    *   [in] Private data node for the custom function
    * @param result
    *   [out] The function puts the handle to the result configuration node at
    *   the address specified by result.
    * @return
    *   A non-negative value if successful, otherwise a negative error code.
    *
    * If config has arguments (defined by a child with id @args), this function
    * replaces any string node beginning with $ with the respective argument
    * value, or the default argument value, or nothing. Furthermore, any
    * functions are evaluated (see snd_config_evaluate). The resulting copy of
    * config is returned in result.
    *
    * The new tree is not evaluated (snd_config_evaluate).
    */
  @name("snd_config_expand_custom")
  def snd_config_expand_custom(
      config: Ptr[snd_config_t],
      root: Ptr[snd_config_t],
      fcn: snd_config_expand_fcn_t,
      private_data: CVoidPtr,
      result: Ptr[Ptr[snd_config_t]]
  ): CInt = extern

  /** Expands a configuration node, applying arguments and functions.
    * @param config
    *   [in] Handle to the configuration node.
    * @param root
    *   [in] Handle to the root configuration node.
    * @param args
    *   [in] Arguments string, can be NULL.
    * @param private_data
    *   [in] Handle to the private data node for functions.
    * @param result
    *   [out] The function puts the handle to the result configuration node at
    *   the address specified by result.
    * @return
    *   A non-negative value if successful, otherwise a negative error code.
    *
    * If config has arguments (defined by a child with id @args), this function
    * replaces any string node beginning with $ with the respective argument
    * value, or the default argument value, or nothing. Furthermore, any
    * functions are evaluated (see snd_config_evaluate). The resulting copy of
    * config is returned in result.
    */
  @name("snd_config_expand")
  def snd_config_expand(
      config: Ptr[snd_config_t],
      root: Ptr[snd_config_t],
      args: Ptr[CChar],
      private_data: Ptr[snd_config_t],
      result: Ptr[Ptr[snd_config_t]]
  ): CInt = extern

  /** Evaluates a configuration node at runtime.
    * @param config
    *   [in,out] Handle to the source configuration node.
    * @param root
    *   [in] Handle to the root of the source configuration.
    * @param private_data
    *   [in] Handle to the private data node for runtime evaluation.
    * @param result
    *   Must be NULL.
    * @return
    *   A non-negative value if successful, otherwise a negative error code.
    *
    * This function evaluates any functions (@func) in config and replaces those
    * nodes with the respective function results.
    */
  @name("snd_config_evaluate")
  def snd_config_evaluate(
      config: Ptr[snd_config_t],
      root: Ptr[snd_config_t],
      private_data: Ptr[snd_config_t],
      result: Ptr[Ptr[snd_config_t]]
  ): CInt = extern

  /** Evaluate an math expression in the string.
    * @param dst
    *   [out] The function puts the handle to the new configuration node at the
    *   address specified by dst.
    * @param s
    *   [in] A string to evaluate
    * @param fcn
    *   [in] A function to get the variable contents
    * @param private_data
    *   [in] A private value for the variable contents function
    * @return
    *   0 if successful, otherwise a negative error code.
    */
  @name("snd_config_evaluate_string")
  def snd_config_evaluate_string(
      dst: Ptr[Ptr[snd_config_t]],
      s: Ptr[CChar],
      fcn: snd_config_expand_fcn_t,
      private_data: CVoidPtr
  ): CInt = extern

  /** Adds a child to a compound configuration node.
    * @param parent
    *   Handle to a compound configuration node.
    * @param child
    *   Handle to the configuration node to be added.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function makes the node child a child of the node parent.
    *
    * The parent node then owns the child node, i.e., the child node gets
    * deleted together with its parent.
    *
    * child must have an id.
    */
  @name("snd_config_add")
  def snd_config_add(
      config: Ptr[snd_config_t],
      child: Ptr[snd_config_t]
  ): CInt = extern

  /** Adds a child before another child configuration node.
    * @param before
    *   Handle to the start configuration node.
    * @param child
    *   Handle to the configuration node to be added.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function makes the node child a child of the parent of the node
    * before.
    *
    * The parent node then owns the child node, i.e., the child node gets
    * deleted together with its parent.
    *
    * child must have an id.
    */
  @name("snd_config_add_before")
  def snd_config_add_before(
      before: Ptr[snd_config_t],
      child: Ptr[snd_config_t]
  ): CInt = extern

  /** Adds a child after another child configuration node.
    * @param after
    *   Handle to the start configuration node.
    * @param child
    *   Handle to the configuration node to be added.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function makes the node child a child of the parent of the node
    * after.
    *
    * The parent node then owns the child node, i.e., the child node gets
    * deleted together with its parent.
    *
    * child must have an id.
    */
  @name("snd_config_add_after")
  def snd_config_add_after(
      after: Ptr[snd_config_t],
      child: Ptr[snd_config_t]
  ): CInt = extern

  /** Removes a configuration node from its tree.
    * @param config
    *   Handle to the configuration node to be removed.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function makes config a top-level node, i.e., if config has a parent,
    * then config is removed from the list of the parent's children.
    *
    * This functions does not free the removed node.
    */
  @name("snd_config_remove")
  def snd_config_remove(config: Ptr[snd_config_t]): CInt = extern

  /** Frees a configuration node.
    * @param config
    *   Handle to the configuration node to be deleted.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function frees a configuration node and all its resources.
    *
    * If the node is a child node, it is removed from the tree before being
    * deleted.
    *
    * If the node is a compound node, its descendants (the whole subtree) are
    * deleted recursively.
    *
    * The function is supposed to be called only for locally copied config
    * trees. For the global tree, take the reference via snd_config_update_ref
    * and free it via snd_config_unref.
    */
  @name("snd_config_delete")
  def snd_config_delete(config: Ptr[snd_config_t]): CInt = extern

  /** Deletes the children of a node.
    * @param config
    *   Handle to the compound configuration node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function removes and frees all children of a configuration node.
    *
    * Any compound nodes among the children of config are deleted recursively.
    *
    * After a successful call to this function, config is an empty compound
    * node.
    */
  @name("snd_config_delete_compound_members")
  def snd_config_delete_compound_members(config: Ptr[snd_config_t]): CInt =
    extern

  /** Creates a copy of a configuration node.
    * @param dst
    *   [out] The function puts the handle to the new configuration node at the
    *   address specified by dst.
    * @param src
    *   [in] Handle to the source configuration node.
    * @return
    *   A non-negative value if successful, otherwise a negative error code.
    *
    * This function creates a deep copy, i.e., if src is a compound node, all
    * children are copied recursively.
    */
  @name("snd_config_copy")
  def snd_config_copy(
      dst: Ptr[Ptr[snd_config_t]],
      src: Ptr[snd_config_t]
  ): CInt = extern

  /** Substitutes one configuration node to another.
    * @param dst
    *   Handle to the destination node.
    * @param src
    *   Handle to the source node. Must not be the same as dst.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * If both nodes are compounds, the source compound node members will be
    * moved to the destination compound node. The original destination compound
    * node members will be deleted (overwritten).
    *
    * If the destination node is a compound and the source node is an ordinary
    * type, the compound members are deleted (including their contents).
    *
    * Otherwise, the source node's value replaces the destination node's value.
    *
    * In any case, a successful call to this function frees the source node.
    */
  @name("snd_config_substitute")
  def snd_config_substitute(
      dst: Ptr[snd_config_t],
      src: Ptr[snd_config_t]
  ): CInt = extern

  /** In-place merge of two config handles.
    * @param dst
    *   [out] Config handle for the merged contents
    * @param src
    *   [in] Config handle to merge into dst (may be NULL)
    * @param override
    *   [in] Override flag
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function merges all fields from the source compound to the
    * destination compound. When the override flag is set, the related subtree
    * in dst is replaced from src.
    *
    * When override is not set, the child compounds are traversed and merged.
    *
    * The configuration elements other than compounds are always substituted
    * (overwritten) from the src config handle.
    *
    * The src handle is deleted.
    *
    * Note: On error, config handles may be modified.
    */
  @name("snd_config_merge")
  def snd_config_merge(
      dst: Ptr[snd_config_t],
      src: Ptr[snd_config_t],
      `override`: CInt
  ): CInt = extern

  /** Creates a configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param type
    *   [in] The type of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This functions creates a new node of the specified type. The new node has
    * id id, which may be NULL.
    *
    * The value of the new node is zero (for numbers), or NULL (for strings and
    * pointers), or empty (for compound nodes).
    */
  @name("snd_config_make")
  def snd_config_make(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      `type`: snd_config_type_t
  ): CInt = extern

  /** Creates an integer configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_INTEGER and with
    * value 0.
    */
  @name("snd_config_make_integer")
  def snd_config_make_integer(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar]
  ): CInt = extern

  /** Creates a 64-bit-integer configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_INTEGER64 and
    * with value 0.
    */
  @name("snd_config_make_integer64")
  def snd_config_make_integer64(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar]
  ): CInt = extern

  /** Creates a real number configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_REAL and with
    * value 0.0.
    */
  @name("snd_config_make_real")
  def snd_config_make_real(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar]
  ): CInt = extern

  /** Creates a string configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_STRING and with
    * value NULL.
    */
  @name("snd_config_make_string")
  def snd_config_make_string(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar]
  ): CInt = extern

  /** Creates a pointer configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_POINTER and with
    * value NULL.
    */
  @name("snd_config_make_pointer")
  def snd_config_make_pointer(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar]
  ): CInt = extern

  /** Creates an empty compound configuration node.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param join
    *   [in] Join flag.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new empty node of type SND_CONFIG_TYPE_COMPOUND.
    *
    * join determines how the compound node's id is printed when the
    * configuration is saved to a text file. For example, if the join flag of
    * compound node a is zero, the output will look as follows:
    *
    * If, however, the join flag of a is nonzero, its id will be joined with its
    * children's ids, like this:
    *
    * An empty compound node with its join flag set would result in no output,
    * i.e., after saving and reloading the configuration file, that compound
    * node would be lost.
    */
  @name("snd_config_make_compound")
  def snd_config_make_compound(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      join: CInt
  ): CInt = extern

  /** Creates an empty compound configuration node in the path.
    * @param config
    *   [out] The function puts the handle to the new or existing compound node
    *   at the address specified by config.
    * @param root
    *   [in] The id of the new node.
    * @param key
    *   [in] The id of the new node.
    * @param join
    *   [in] Join flag.
    * @param override
    *   [in] Override flag.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new empty node of type SND_CONFIG_TYPE_COMPOUND if
    * the path does not exist. Otherwise, the node from the current
    * configuration tree is returned without any modification. The join argument
    * is ignored in this case.
    *
    * join determines how the compound node's id is printed when the
    * configuration is saved to a text file. For example, if the join flag of
    * compound node a is zero, the output will look as follows:
    *
    * If, however, the join flag of a is nonzero, its id will be joined with its
    * children's ids, like this:
    *
    * An empty compound node with its join flag set would result in no output,
    * i.e., after saving and reloading the configuration file, that compound
    * node would be lost.
    */
  @name("snd_config_make_path")
  def snd_config_make_path(
      config: Ptr[Ptr[snd_config_t]],
      root: Ptr[snd_config_t],
      key: Ptr[CChar],
      join: CInt,
      `override`: CInt
  ): CInt = extern

  /** Creates an integer configuration node with the given initial value.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param value
    *   [in] The initial value of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_INTEGER and with
    * value value.
    */
  @name("snd_config_imake_integer")
  def snd_config_imake_integer(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      value: CLong
  ): CInt = extern

  /** Creates a 64-bit-integer configuration node with the given initial value.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param value
    *   [in] The initial value of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_INTEGER64 and
    * with value value.
    */
  @name("snd_config_imake_integer64")
  def snd_config_imake_integer64(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      value: CLongLong
  ): CInt = extern

  /** Creates a real number configuration node with the given initial value.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param value
    *   [in] The initial value of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_REAL and with
    * value value.
    */
  @name("snd_config_imake_real")
  def snd_config_imake_real(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      value: CDouble
  ): CInt = extern

  /** Creates a string configuration node with the given initial value.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param value
    *   [in] The initial value of the new node. May be NULL.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_STRING and with a
    * copy of the string value.
    */
  @name("snd_config_imake_string")
  def snd_config_imake_string(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      ascii: Ptr[CChar]
  ): CInt = extern

  /** Creates a string configuration node with the given initial value.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param value
    *   [in] The initial value of the new node. May be NULL.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_STRING. The node
    * contains with a copy of the string value, replacing any character other
    * than alphanumeric, space, or '-' with the character '_'.
    */
  @name("snd_config_imake_safe_string")
  def snd_config_imake_safe_string(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      ascii: Ptr[CChar]
  ): CInt = extern

  /** Creates a pointer configuration node with the given initial value.
    * @param config
    *   [out] The function puts the handle to the new node at the address
    *   specified by config.
    * @param id
    *   [in] The id of the new node.
    * @param value
    *   [in] The initial value of the new node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function creates a new node of type SND_CONFIG_TYPE_POINTER and with
    * value value.
    */
  @name("snd_config_imake_pointer")
  def snd_config_imake_pointer(
      config: Ptr[Ptr[snd_config_t]],
      key: Ptr[CChar],
      ptr: CVoidPtr
  ): CInt = extern

  /** Returns the type of a configuration node.
    * @param config
    *   Handle to the configuration node.
    * @return
    *   The node's type.
    */
  @name("snd_config_get_type")
  def snd_config_get_type(config: Ptr[snd_config_t]): snd_config_type_t = extern

  /** Returns if the compound is an array (and count of items).
    * @param config
    *   Handle to the configuration node.
    * @return
    *   A count of items in array, zero when the compound is not an array,
    *   otherwise a negative error code.
    */
  @name("snd_config_is_array")
  def snd_config_is_array(config: Ptr[snd_config_t]): CInt = extern

  /** Returns if the compound has no fields (is empty).
    * @param config
    *   Handle to the configuration node.
    * @return
    *   A positive value when true, zero when false, otherwise a negative error
    *   code.
    */
  @name("snd_config_is_empty")
  def snd_config_is_empty(config: Ptr[snd_config_t]): CInt = extern

  /** Sets the id of a configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param id
    *   The new node id, must not be NULL.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function stores a copy of id in the node.
    */
  @name("snd_config_set_id")
  def snd_config_set_id(config: Ptr[snd_config_t], id: Ptr[CChar]): CInt =
    extern

  /** Changes the value of an integer configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param value
    *   The new value for the node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_set_integer")
  def snd_config_set_integer(config: Ptr[snd_config_t], value: CLong): CInt =
    extern

  /** Changes the value of a 64-bit-integer configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param value
    *   The new value for the node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_set_integer64")
  def snd_config_set_integer64(
      config: Ptr[snd_config_t],
      value: CLongLong
  ): CInt = extern

  /** Changes the value of a real-number configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param value
    *   The new value for the node.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_set_real")
  def snd_config_set_real(config: Ptr[snd_config_t], value: CDouble): CInt =
    extern

  /** Changes the value of a string configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param value
    *   The new value for the node. May be NULL.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function deletes the old string in the node and stores a copy of
    * value string in the node.
    */
  @name("snd_config_set_string")
  def snd_config_set_string(
      config: Ptr[snd_config_t],
      value: Ptr[CChar]
  ): CInt = extern

  /** Changes the value of a configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param ascii
    *   The new value for the node, as an ASCII string.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function changes the node's value to a new value that is parsed from
    * the string ascii. ascii must not be NULL, not even for a string node.
    *
    * The node's type does not change, i.e., the string must contain a valid
    * value with the same type as the node's type. For a string node, the node's
    * new value is a copy of ascii.
    */
  @name("snd_config_set_ascii")
  def snd_config_set_ascii(config: Ptr[snd_config_t], ascii: Ptr[CChar]): CInt =
    extern

  /** Changes the value of a pointer configuration node.
    * @param config
    *   Handle to the configuration node.
    * @param value
    *   The new value for the node. May be NULL.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function does not free the old pointer in the node.
    */
  @name("snd_config_set_pointer")
  def snd_config_set_pointer(config: Ptr[snd_config_t], ptr: CVoidPtr): CInt =
    extern

  /** Returns the id of a configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param id
    *   [out] The function puts the pointer to the id string at the address
    *   specified by id.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * The returned string is owned by the configuration node; the application
    * must not modify or delete it, and the string becomes invalid when the
    * node's id changes or when the node is freed.
    *
    * If the node does not have an id, *id is set to NULL.
    */
  @name("snd_config_get_id")
  def snd_config_get_id(
      config: Ptr[snd_config_t],
      value: Ptr[Ptr[CChar]]
  ): CInt = extern

  /** Returns the value of an integer configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ptr
    *   [out] The node's value.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_get_integer")
  def snd_config_get_integer(
      config: Ptr[snd_config_t],
      value: Ptr[CLong]
  ): CInt = extern

  /** Returns the value of a 64-bit-integer configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ptr
    *   [out] The node's value.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_get_integer64")
  def snd_config_get_integer64(
      config: Ptr[snd_config_t],
      value: Ptr[CLongLong]
  ): CInt = extern

  /** Returns the value of a real-number configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ptr
    *   [out] The node's value.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_get_real")
  def snd_config_get_real(
      config: Ptr[snd_config_t],
      value: Ptr[CDouble]
  ): CInt = extern

  /** Returns the value of a real or integer configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ptr
    *   [out] The node's value.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * If the node's type is integer or integer64, the value is converted to the
    * double type on the fly.
    */
  @name("snd_config_get_ireal")
  def snd_config_get_ireal(
      config: Ptr[snd_config_t],
      value: Ptr[CDouble]
  ): CInt = extern

  /** Returns the value of a string configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ptr
    *   [out] The function puts the node's value at the address specified by
    *   ptr.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * The returned string is owned by the configuration node; the application
    * must not modify or delete it, and the string becomes invalid when the
    * node's value changes or when the node is freed.
    *
    * The string may be NULL.
    */
  @name("snd_config_get_string")
  def snd_config_get_string(
      config: Ptr[snd_config_t],
      value: Ptr[Ptr[CChar]]
  ): CInt = extern

  /** Returns the value of a configuration node as a string.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ascii
    *   [out] The function puts the pointer to the returned string at the
    *   address specified by ascii.
    * @return
    *   Zero if successful, otherwise a negative error code.
    *
    * This function dynamically allocates the returned string. The application
    * is responsible for deleting it with free() when it is no longer used.
    *
    * For a string node with NULL value, the returned string is NULL.
    *
    * Supported node types are SND_CONFIG_TYPE_INTEGER,
    * SND_CONFIG_TYPE_INTEGER64, SND_CONFIG_TYPE_REAL, and
    * SND_CONFIG_TYPE_STRING.
    */
  @name("snd_config_get_ascii")
  def snd_config_get_ascii(
      config: Ptr[snd_config_t],
      value: Ptr[Ptr[CChar]]
  ): CInt = extern

  /** Returns the value of a pointer configuration node.
    * @param config
    *   [in] Handle to the configuration node.
    * @param ptr
    *   [out] The function puts the node's value at the address specified by
    *   ptr.
    * @return
    *   Zero if successful, otherwise a negative error code.
    */
  @name("snd_config_get_pointer")
  def snd_config_get_pointer(
      config: Ptr[snd_config_t],
      value: Ptr[CVoidPtr]
  ): CInt = extern

  /** Compares the id of a configuration node to a given string.
    * @param config
    *   Handle to the configuration node.
    * @param id
    *   ASCII id.
    * @return
    *   The same value as the result of the strcmp function, i.e., less than
    *   zero if config's id is lexicographically less than id, zero if config's
    *   id is equal to id, greater than zero otherwise.
    */
  @name("snd_config_test_id")
  def snd_config_test_id(config: Ptr[snd_config_t], id: Ptr[CChar]): CInt =
    extern

  /** Returns an iterator pointing to a node's first child.
    * @param config
    *   [in] Handle to a configuration node.
    * @return
    *   An iterator pointing to config's first child.
    *
    * config must be a compound node.
    *
    * The returned iterator is valid if it is not equal to the return value of
    * snd_config_iterator_end on config.
    *
    * Use snd_config_iterator_entry to get the handle of the node pointed to.
    */
  @name("snd_config_iterator_first")
  def snd_config_iterator_first(
      node: Ptr[snd_config_t]
  ): snd_config_iterator_t = extern

  /** Returns an iterator pointing to the next sibling.
    * @param iterator
    *   [in] An iterator pointing to a child configuration node.
    * @return
    *   An iterator pointing to the next sibling of iterator.
    *
    * The returned iterator is valid if it is not equal to the return value of
    * snd_config_iterator_end on the node's parent.
    *
    * Use snd_config_iterator_entry to get the handle of the node pointed to.
    */
  @name("snd_config_iterator_next")
  def snd_config_iterator_next(
      iterator: snd_config_iterator_t
  ): snd_config_iterator_t = extern

  /** Returns an iterator that ends a node's children list.
    * @param config
    *   [in] Handle to a configuration node.
    * @return
    *   An iterator that indicates the end of config's children list.
    *
    * config must be a compound node.
    *
    * The return value can be understood as pointing past the last child of
    * config.
    */
  @name("snd_config_iterator_end")
  def snd_config_iterator_end(node: Ptr[snd_config_t]): snd_config_iterator_t =
    extern

  /** Returns the configuration node handle pointed to by an iterator.
    * @param iterator
    *   [in] A configuration node iterator.
    * @return
    *   The configuration node handle pointed to by iterator.
    */
  @name("snd_config_iterator_entry")
  def snd_config_iterator_entry(
      iterator: snd_config_iterator_t
  ): Ptr[snd_config_t] = extern

  /** Gets the boolean value from the given ASCII string.
    * @param ascii
    *   The string to be parsed.
    * @return
    *   0 or 1 if successful, otherwise a negative error code.
    */
  @name("snd_config_get_bool_ascii")
  def snd_config_get_bool_ascii(ascii: Ptr[CChar]): CInt = extern

  /** Gets the boolean value from a configuration node.
    * @param conf
    *   Handle to the configuration node to be parsed.
    * @return
    *   0 or 1 if successful, otherwise a negative error code.
    */
  @name("snd_config_get_bool")
  def snd_config_get_bool(conf: Ptr[snd_config_t]): CInt = extern

  /** Gets the card number from a configuration node.
    * @param conf
    *   Handle to the configuration node to be parsed.
    * @return
    *   The card number if successful, otherwise a negative error code.
    */
  @name("snd_config_get_card")
  def snd_config_get_card(conf: Ptr[snd_config_t]): CInt = extern

  /** Gets the control interface index from the given ASCII string.
    * @param ascii
    *   The string to be parsed.
    * @return
    *   The control interface index if successful, otherwise a negative error
    *   code.
    */
  @name("snd_config_get_ctl_iface_ascii")
  def snd_config_get_ctl_iface_ascii(ascii: Ptr[CChar]): CInt = extern

  /** Gets the control interface index from a configuration node.
    * @param conf
    *   Handle to the configuration node to be parsed.
    * @return
    *   The control interface index if successful, otherwise a negative error
    *   code.
    */
  @name("snd_config_get_ctl_iface")
  def snd_config_get_ctl_iface(conf: Ptr[snd_config_t]): CInt = extern

  /** This function is unimplemented.
    */
  @name("snd_names_list")
  def snd_names_list(iface: Ptr[CChar], list: Ptr[Ptr[snd_devname_t]]): CInt =
    extern

  /** This function is unimplemented.
    */
  @name("snd_names_list_free")
  def snd_names_list_free(list: Ptr[snd_devname_t]): Unit = extern

  /** Configuration top-level node (the global configuration).
    *
    * This variable contains a handle to the top-level configuration node, as
    * loaded from global configuration file.
    *
    * This variable is initialized or updated by snd_config_update. Functions
    * like snd_pcm_open (that use a device name from the global configuration)
    * automatically call snd_config_update. Before the first call to
    * snd_config_update, this variable is NULL.
    *
    * The global configuration files are specified in the environment variable
    * ALSA_CONFIG_PATH. If this is not set, the default value is
    * "/usr/share/alsa/alsa.conf".
    */
  @name("snd_config")
  var snd_config: Ptr[snd_config_t] = extern

}
