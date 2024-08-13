import com.example.weatherapp.domain.model.weather.Forecast
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object Home : Screens()

    @Serializable
    data class ForecastScreen(val days: Forecast) : Screens()

}