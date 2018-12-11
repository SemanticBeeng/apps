package aecor.example

import aecor.runtime.Eventsourced.Entities

package object transaction {
  type Transactions[F[_]] = Entities.Rejectable[TransactionId, transaction.Algebra, F, transaction.Rejection]
}
