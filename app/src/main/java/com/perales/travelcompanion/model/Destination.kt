package com.perales.travelcompanion.model

data class Destination(
    val pais: String,
    val ciudad: String,
    val costoPromedio: Double,
    val imageUrl: String
)

val destinosMuestra = listOf(
    Destination(
        pais = "Perú",
        ciudad = "Cusco",
        costoPromedio = 850.0,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Machu_Picchu%2C_Peru%2C_Sucre_Nicolas_2011-08-01_%28cleaned%29.jpg/640px-Machu_Picchu%2C_Peru%2C_Sucre_Nicolas_2011-08-01_%28cleaned%29.jpg"
    ),
    Destination(
        pais = "Francia",
        ciudad = "París",
        costoPromedio = 3200.0,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Camponotus_flavomarginatus_ant.jpg/640px-Camponotus_flavomarginatus_ant.jpg"
    ),
    Destination(
        pais = "Japón",
        ciudad = "Tokio",
        costoPromedio = 4500.0,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Above_Gotham.jpg/640px-Above_Gotham.jpg"
    ),
    Destination(
        pais = "Colombia",
        ciudad = "Cartagena",
        costoPromedio = 1200.0,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Cartagena_de_Indias%2C_Colombia.jpg/640px-Cartagena_de_Indias%2C_Colombia.jpg"
    ),
    Destination(
        pais = "Italia",
        ciudad = "Roma",
        costoPromedio = 2800.0,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Colosseo_2020.jpg/640px-Colosseo_2020.jpg"
    ),
    Destination(
        pais = "Argentina",
        ciudad = "Buenos Aires",
        costoPromedio = 900.0,
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/24701-nature-natural-beauty.jpg/640px-24701-nature-natural-beauty.jpg"
    )
)
