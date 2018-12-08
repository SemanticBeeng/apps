package aecor.util

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

import aecor.runtime.akkapersistence.serialization.{
  DecodingFailure,
  PersistentDecoder,
  PersistentEncoder,
  PersistentRepr
}
import io.circe.{ Decoder, Encoder, jawn }

object PersistentEncoderCirce {

  def encoder[A](implicit encoder: Encoder[A]): PersistentEncoder[A] =
    PersistentEncoder.instance(
      e => PersistentRepr("", encoder(e).noSpaces.getBytes(StandardCharsets.UTF_8))
    )

  def decoder[A](implicit decoder: Decoder[A]): PersistentDecoder[A] =
    PersistentDecoder.instance(
      repr =>
        jawn
          .parseByteBuffer(ByteBuffer.wrap(repr.payload))
          .right
          .flatMap(decoder.decodeJson)
          .left
          .map(DecodingFailure.fromThrowable)
    )
}
