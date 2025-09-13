🌍 City Explorer App

An Android app built with Kotlin + Jetpack Compose that allows users to explore different cities around the world, view descriptions, see images, and fetch real-time weather data using a public weather API.
It also demonstrates navigation, location permissions, and API integration in a modern Android app.

🎯 Overview

This project is a learning exercise to explore:

Jetpack Compose UI with navigation between screens

REST API integration with WeatherAPI
 via Ktor Client

Location permission handling (runtime permissions)

Dynamic content rendering (city descriptions, weather, images)

✨ Features

🏙️ Welcome Screen

Displays a list of cities.

Buttons to navigate to city detail pages.

Requests location permission on launch.

🌤️ Weather Integration

Fetches live weather data for the selected city.

Shows current temperature in °C.

📷 City Info

Displays description of the selected city.

Shows an image (via CoilImage from Accompanist).

🔙 Back Navigation

Uses BackHandler to return to the previous screen.

🔒 Location Permission

Requests fine location access with runtime permission handling.

📂 Project Structure

MainActivity → App entry point, sets up navigation.

WelcomeScreen → City selection + location permission request.

SecondScreen → Displays selected city info + weather.

WeatherApiService → Uses Ktor Client to fetch weather data.

CityInfo → Data class holding city name and image URL.

LocationPermissionRequester → Handles runtime location permission.

LocationPermissionViewModel → Tracks permission request state.

🛠️ Tech Stack

Language: Kotlin

UI: Jetpack Compose + Material 3

Navigation: Jetpack Navigation Compose

API: Ktor Client (WeatherAPI integration)

Images: Coil (Accompanist)

Permissions: AndroidX Activity + ViewModel

🚀 Getting Started

Clone the repository:

git clone https://github.com/<your-username>/CityExplorerApp.git


Open in Android Studio.

Add your WeatherAPI key in WeatherApiService:

class WeatherApiService(private val apiKey: String) { ... }


Build and run the app on an emulator or device.

📊 Example Cities

Yerevan → “Known for rich history and vibrant culture.”

Washington D.C. → “Capital of the USA, home to iconic landmarks.”

Madrid → “Famous for art, culture, and lively street life.”

Berlin → “Known for history, art, and diverse culture.”

Paris → “Romantic ambiance, cuisine, and historic landmarks.”

📸 Example UI
Welcome Screen

List of cities with navigation buttons.

City Detail Screen

City description, image, and current weather.

📜 License

This project is licensed under the MIT License – see LICENSE
 for details.
