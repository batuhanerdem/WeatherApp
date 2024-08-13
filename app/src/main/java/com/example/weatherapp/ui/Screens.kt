import com.example.weatherapp.domain.model.weather.Forecastday
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object Home : Screens()

//    @Serializable
//    data class Forecast(val days: Forecastday) : Screens()

}