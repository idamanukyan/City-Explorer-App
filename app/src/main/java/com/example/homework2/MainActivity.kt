package com.example.homework2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.homework2.ui.theme.Homework2Theme
import dev.chrisbanes.accompanist.coil.CoilImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Homework2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "welcome_screen") {
                        composable("welcome_screen") {
                            WelcomeScreen(navController)
                        }
                        composable("second_screen/{cityName}") { backStackEntry ->
                            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
                            val cityInfo = citiesInfo.find { it.cityName == cityName }
                            cityInfo?.let {
                                SecondScreen(cityInfo, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Willkommen zur Städteerkundungs-App",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Entdecken Sie Städte auf der ganzen Welt:",
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(8.dp)
        )

        citiesInfo.forEach { city ->
            Button(
                onClick = { navController.navigate("second_screen/${city.cityName}") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Städte erkunden in ${city.cityName}")
            }
        }
    }
}

@Composable
fun SecondScreen(cityInfo: CityInfo, navController: NavHostController) {
    BackHandler(onBack = { navController.popBackStack() })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Städte erkunden",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = "Explore the city of ${cityInfo.cityName}:",
            style = TextStyle(fontSize = 18.sp),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = getCityDescription(cityInfo.cityName),
            style = TextStyle(fontSize = 16.sp),
            modifier = Modifier.padding(8.dp)
        )

        CoilImage(
            data = cityInfo.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

fun getCityDescription(cityName: String): String {
    return when (cityName) {
        "Yerevan" -> "Yerevan is the capital and largest city of Armenia. It is known for its rich history, stunning architecture, and vibrant culture."
        "Washington" -> "Washington, D.C. is the capital of the United States. It is home to numerous iconic landmarks, museums, and government institutions."
        "Madrid" -> "Madrid is the capital of Spain and is famous for its art, culture, and lively street life."
        "Berlin" -> "Berlin is the capital of Germany and is known for its history, art scene, and diverse culture."
        "Paris" -> "Paris, the capital of France, is renowned for its romantic ambiance, historic landmarks, and world-class cuisine."
        else -> "No information available for $cityName."
    }
}

data class CityInfo(val cityName: String, val imageUrl: String)

val citiesInfo = listOf(
    CityInfo(
        "Yerevan",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Mount_Ararat_and_the_Yerevan_skyline_in_spring_from_the_Cascade.jpg/278px-Mount_Ararat_and_the_Yerevan_skyline_in_spring_from_the_Cascade.jpg"
    ),
    CityInfo(
        "Washington",
        "https://cdn.britannica.com/22/94422-050-29AC90D6/Seattle-Space-Needle.jpg"
    ),
    CityInfo(
        "Madrid",
        "https://www.spain.info/.content/imagenes/cabeceras-grandes/madrid/calle-gran-via-madrid-s333961043.jpg"
    ),
    CityInfo(
        "Berlin",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/Museumsinsel_Berlin_Juli_2021_1_%28cropped%29.jpg/1200px-Museumsinsel_Berlin_Juli_2021_1_%28cropped%29.jpg"
    ),
    CityInfo(
        "Paris",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/La_Tour_Eiffel_vue_de_la_Tour_Saint-Jacques%2C_Paris_ao%C3%BBt_2014_%282%29.jpg/1200px-La_Tour_Eiffel_vue_de_la_Tour_Saint-Jacques%2C_Paris_ao%C3%BBt_2014_%282%29.jpg"
    )
)
