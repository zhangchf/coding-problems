class BusBooking(n: Int) {

    private val bookings = mutableMapOf<String, Int>()
    private var _totalTime = 0

    val adjSeatMap = mapOf(
        'A' to 'B',
        'D' to 'C'
    )

    fun toggle(seat: String) {
        if (seat in bookings) {
            _totalTime -= bookings.remove(seat)!!
            return
        }
        val col = seat[0]
        val row = seat.substring(1)

        val adjSeat = "${adjSeatMap[col]}${row}"

        val cost = if (adjSeat in bookings) 3 else 1
        bookings[seat] = cost
        _totalTime += cost
    }

    fun getTotalTime(): Int {
        return _totalTime
    }

    fun getMinTime(): Int {
        return bookings.size
    }

}

/**
 * Your BusBooking object will be instantiated and called as such:
 * var obj = BusBooking(n)
 * obj.toggle(seat)
 * var param_2 = obj.getTotalTime()
 * var param_3 = obj.getMinTime()
 */