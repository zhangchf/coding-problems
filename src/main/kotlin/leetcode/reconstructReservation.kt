package leetcode

import java.lang.IllegalArgumentException

data class Reservation(val source: String, val destination: String)

interface ItineraryReconstructor {
    /**
     * Reconstructs the journey timeline.
     * Time Complexity: O(N) where N is the number of reservations.
     * Space Complexity: O(N) to store the lookup graphs.
     */
    fun reconstruct(reservations: List<Reservation>): List<String>
}

class ItineraryReconstructorImpl : ItineraryReconstructor {

    override fun reconstruct(reservations: List<Reservation>): List<String> {
        //buildGraph
        val graph = mutableMapOf<String, MutableList<String>>()

        //findPath


    }
}
