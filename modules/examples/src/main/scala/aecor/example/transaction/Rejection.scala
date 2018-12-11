package aecor.example.transaction

import aecor.macros.boopickle.BoopickleCodec._
import scodec.Codec

sealed abstract class Rejection extends Product with Serializable

object Rejection extends RejectionInstances {
  case object TransactionNotFound extends Rejection
  case object IllegalTransition extends Rejection
}

trait RejectionInstances { self: Rejection.type =>
  import boopickle.Default._
  import boopickle.CompositePickler

  implicit val rejectionPickler: CompositePickler[Rejection] = compositePickler[Rejection]
    .addConcreteType[TransactionNotFound.type]
    .addConcreteType[IllegalTransition.type]

  implicit val rejectionCodec: Codec[Rejection] =
    codec[Rejection]
}
