package bowling.domain

case class InvalidMatchException(message: String) extends IllegalArgumentException(message)
