import com.example.weatherapp.domain.model.weather.DayUi
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object Home : Screens()

    @Serializable
    data class ForecastScreen(val days: List<DayUi>) : Screens()

}