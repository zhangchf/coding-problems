import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

enum class Model {
    Model1, Model2, Model3
}

data class Car(
    val model: Model? = null
)

@JsonAdapter(Color.ColorSerializer::class)
enum class Color(val value: Int) {
    Red(0), Green(1), Blue(2);

    companion object {
        fun findColorByValue(value: Int?): Color? {
            return values().find { it.value == value }
        }
    }

    class ColorSerializer : JsonSerializer<Color>, JsonDeserializer<Color> {
        override fun serialize(src: Color?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
            return context!!.serialize(src?.value)
        }

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Color? {
            return findColorByValue(json?.asInt)
        }
    }
}

data class ColorData(
    val color: Color? = null
)

enum class Status {
    @SerializedName("0")
    Success,
    @SerializedName("1")
    Failed
}

data class StatusData(
    val status: Status? = null
)

fun main() {
    val gson = GsonBuilder()
        .serializeNulls()
        .create()

    for (model in Model.values()) {
        println(gson.toJson(model))
        val car = gson.toJson(Car(model))
        println(car)
        println(gson.fromJson(car, Car::class.java))
    }
    println(gson.toJson(Car()))
    println(Car())

    println()

    for (color in Color.values()) {
        println(gson.toJson(color))
        val colorData = gson.toJson(ColorData(color))
        println(colorData)
        println(gson.fromJson(colorData, ColorData::class.java))
    }
    println(gson.toJson(ColorData()))
    println(ColorData())

    println()

    for (status in Status.values()) {
        println(gson.toJson(status))
        val statusData = gson.toJson(StatusData(status))
        println(statusData)
        println(gson.fromJson(statusData, StatusData::class.java))
    }
    println(gson.toJson(StatusData()))
    println(StatusData())

    println()

    val a: String? = null
    println("indexOf null: " + listOf("", "").indexOf(a))
}