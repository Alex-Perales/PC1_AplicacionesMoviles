#!/bin/bash
# =============================================================
# Script para inicializar Git y subir el proyecto a GitHub
# Ejecutar desde la carpeta TravelCompanionApp
# =============================================================

REPO_URL="https://github.com/Alex-Perales/Prueba.git"

echo ">>> Inicializando repositorio Git..."
git init
git remote add origin $REPO_URL

# Configurar usuario (ajusta con tus datos reales)
git config user.name "Alex Perales"
git config user.email "aalexperm@gmail.com"

echo ">>> Creando rama principal main..."
git checkout -b main

# Commit inicial en main
git add .gitignore gradle.properties settings.gradle.kts build.gradle.kts gradle/
git commit -m "chore: initial project setup"

echo ">>> Creando rama feature/menu-principal..."
git checkout -b feature/menu-principal

git add app/src/main/AndroidManifest.xml
git add app/src/main/res/
git add app/src/main/java/com/perales/travelcompanion/MainActivity.kt
git add app/src/main/java/com/perales/travelcompanion/ui/
git add app/src/main/java/com/perales/travelcompanion/navigation/
git add app/src/main/java/com/perales/travelcompanion/screens/MainMenuScreen.kt
git add app/build.gradle.kts
git commit -m "feat: add main menu with navigation to all screens"

git add app/src/main/java/com/perales/travelcompanion/screens/LuggageCalculatorScreen.kt
git commit -m "feat: add luggage calculator screen with validations"

git add app/src/main/java/com/perales/travelcompanion/screens/LocationPermissionScreen.kt
git commit -m "feat: add location permission screen with ActivityResult API"

echo ""
echo ">>> Rama feature/menu-principal lista."
echo ">>> Ahora sube esta rama y crea un Pull Request:"
echo "    git push -u origin feature/menu-principal"
echo ""

echo ">>> Para que tu compañero trabaje en su rama:"
echo "    git checkout main"
echo "    git checkout -b feature/catalogo-presupuesto"
echo "    git add app/src/main/java/com/perales/travelcompanion/screens/BudgetPlannerScreen.kt"
echo "    git commit -m 'feat: add budget planner screen'"
echo "    git add app/src/main/java/com/perales/travelcompanion/model/Destination.kt"
echo "    git add app/src/main/java/com/perales/travelcompanion/screens/DestinationCatalogScreen.kt"
echo "    git commit -m 'feat: add destination catalog with Coil images'"
echo "    git commit -m 'feat: add summary stats to destination catalog'"
echo "    git push -u origin feature/catalogo-presupuesto"
echo ""
echo "=== INSTRUCCIONES PARA EL PULL REQUEST ==="
echo "1. Ve a https://github.com/Alex-Perales/Prueba"
echo "2. Verás el botón 'Compare & pull request' para cada rama"
echo "3. Alumno 1 crea PR de feature/menu-principal → main"
echo "4. Alumno 2 lo revisa, aprueba y fusiona"
echo "5. Alumno 2 crea PR de feature/catalogo-presupuesto → main"
echo "6. Alumno 1 lo revisa, aprueba y fusiona"
