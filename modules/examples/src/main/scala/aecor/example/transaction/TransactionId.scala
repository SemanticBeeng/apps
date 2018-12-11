package aecor.example.transaction

import aecor.encoding.{ KeyDecoder, KeyEncoder }

final case class TransactionId(value: String) extends AnyVal

object TransactionId {
  implicit val keyEncoder: KeyEncoder[TransactionId] = KeyEncoder.anyVal[TransactionId]
  implicit val keyDecoder: KeyDecoder[TransactionId] = KeyDecoder.anyVal[TransactionId]
}

final case class From[A](value: A) extends AnyVal
final case class To[A](value: A) extends AnyVal

