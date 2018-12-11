package aecor.example.transaction

import aecor.macros.boopickle.BoopickleCodec._
import scodec.Codec

sealed abstract class Rejection extends Product with Serializable

object Rejection extends RejectionInstances {
  case class TransactionRejected(reason: String) extends Rejection
  case object TransactionNotFound extends Rejection
}

trait RejectionInstances { self: Rejection.type =>
  import boopickle.Default._
  import boopickle.CompositePickler

  implicit val rejectionPickler: CompositePickler[Rejection] = compositePickler[Rejection]
    .addConcreteType[TransactionNotFound.type]

  implicit val rejectionCodec: Codec[Rejection] =
    codec[Rejection]
}
