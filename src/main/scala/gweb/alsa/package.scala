package gweb

import scala.scalanative.unsafe.*

package object alsa {
  opaque type snd_shm_area = CStruct0 // hidden

  type pollfd = scala.scalanative.posix.poll.struct_pollfd // todo 名称不对
  opaque type snd_ctl_ext_tlv_rw_t = CStruct0
  inline val libname = "asoud"

  trait Iota {
    private var initial: CInt = 0

    @inline
    inline def iota: CInt = {
      val value = initial
      initial += 1
      value
    }

    @inline
    inline def iota(value: CInt): CInt = {
      initial = value
      initial += 1
      value
    }
  }

}
