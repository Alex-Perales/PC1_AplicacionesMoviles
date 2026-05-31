# Travel Companion

Aplicación móvil Android desarrollada con **Jetpack Compose** como proyecto de la asignatura de Aplicaciones Móviles.

**Autor:** Perales — 22200107

---

## Descripción

Travel Companion es una app de utilidades para viajeros que ofrece cuatro herramientas principales: calculadora de equipaje, planificador de presupuesto, catálogo de destinos y gestión de permisos de ubicación.

---

## Pantallas

| Pantalla | Descripción |
|---|---|
| Menú Principal | Pantalla de inicio con acceso a las cuatro funciones |
| Calculadora de Equipaje | Verifica si el peso del equipaje cumple los límites de vuelos nacionales (23 kg) e internacionales (32 kg) |
| Planificador de Presupuesto | Calcula el presupuesto total según días, gasto diario y tipo de alojamiento (Económico, Estándar, Premium) |
| Catálogo de Destinos | Lista de 6 destinos con imagen, país, ciudad y costo promedio en soles |
| Permiso de Ubicación | Solicita y gestiona los permisos de ubicación del dispositivo |

---

## Tecnologías y dependencias

- **Kotlin 2.0.0**
- **Jetpack Compose** (BOM 2024.08.00) — UI declarativa
- **Material Design 3** — Componentes y tema visual
- **Navigation Compose 2.7.7** — Navegación entre pantallas
- **Coil 2.6.0** — Carga de imágenes remotas
- **Min SDK:** 24 (Android 7.0) | **Target SDK:** 35

---

## Estructura del proyecto

```
app/src/main/java/com/perales/travelcompanion/
├── MainActivity.kt
├── model/
│   └── Destination.kt
├── navigation/
│   └── AppNavGraph.kt
├── screens/
│   ├── MainMenuScreen.kt
│   ├── LuggageCalculatorScreen.kt
│   ├── BudgetPlannerScreen.kt
│   ├── DestinationCatalogScreen.kt
│   └── LocationPermissionScreen.kt
└── ui/theme/
    └── Theme.kt
```

---

## Permisos requeridos

- `ACCESS_FINE_LOCATION`
- `ACCESS_COARSE_LOCATION`
- `INTERNET`

---

## Cómo ejecutar

1. Clona el repositorio.
2. Abre el proyecto en **Android Studio**.
3. Sincroniza Gradle (**Sync Project with Gradle Files**).
4. Ejecuta en un emulador o dispositivo físico con Android 7.0+.
