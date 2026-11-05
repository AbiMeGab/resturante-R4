# Reto 4

## Lenguaje y versiones

- **Lenguaje**: Java 25
- **Android Gradle Plugin (AGP)**: 9.5.0
- **Gradle**: 9.6.0
- **compileSdk**: 37 (release)
- **minSdk**: 26 (Android 8.0)
- **targetSdk**: 37

## Dependencias principales

| Librería | Versión | Uso |
|----------|---------|-----|
| Room | 2.6.1 | Base de datos SQLite local |
| Lifecycle (ViewModel + LiveData) | 2.8.7 | Arquitectura MVVM |
| ViewPager2 | 1.1.0 | Pestañas deslizables |
| Material Components | 1.10.0 | UI Material Design |
| Fragment | 1.8.5 | Fragmentos en pestañas |
| RecyclerView | 1.4.0 | Listas eficientes |
| ConstraintLayout | 2.2.1 | Layouts flexibles |

## Cómo iniciar la aplicación

1. Abrir la carpeta `app/` como proyecto en **Android Studio**.
2. Esperar a que Gradle sincronice las dependencias (puede tardar la primera vez).
3. Conectar un dispositivo o iniciar un emulador con API 26+.
4. Ejecutar la app desde el botón **Run**.

## Configuración del build

- **Archivo de versiones**: `app/gradle/libs.versions.toml` — aquí se definen todas las versiones de dependencias.
- **Build del proyecto**: `app/build.gradle.kts` — plugins a nivel proyecto.
- **Build del módulo**: `app/app/build.gradle.kts` — plugins, compileSdk, dependencias, y `viewBinding = true`.

## Configuración de la base de datos (Room / SQLite)

La base de datos se configura y se puebla automáticamente al primer inicio de la app.

### Dónde se configura

- **`bd/BaseDeDatos.java`** — Clase principal de Room. Define el singleton, las entidades registradas y el callback que dispara el poblado inicial.

### Dónde se definen las tablas

- **`bd/entidad/RestauranteEntidad.java`** — Tabla `restaurantes` (id, nombre, descripcion, nombre_recurso_imagen).
- **`bd/entidad/PlatilloEntidad.java`** — Tabla `platillos` (id, id_restaurante, nombre, precio, descripcion, categoria, nombre_recurso_imagen). Tiene foreign key hacia `restaurantes`.

### Dónde se hacen las consultas

- **`bd/dao/RestauranteDao.java`** — Queries para obtener, buscar e insertar restaurantes.
- **`bd/dao/PlatilloDao.java`** — Queries para obtener, buscar e insertar platillos por categoría.

### Dónde se pueblan los datos

- **`bd/PobladorDatos.java`** — Contiene el método `poblar()` que inserta los 4 restaurantes y sus 48 platillos (12 por restaurante: 4 comidas, 4 bebidas, 4 complementos). Se ejecuta automáticamente una sola vez cuando la base de datos se crea por primera vez, a través del callback en `BaseDeDatos.java`.

## Estructura del proyecto

```
com.example.restaurante/
├── AplicacionRestaurante.java            Entry point, provee la BD
├── bd/                                   Base de datos (independiente)
│   ├── BaseDeDatos.java                  Singleton Room, callback de creación
│   ├── PobladorDatos.java                Datos iniciales (4 restaurantes, 48 platillos)
│   ├── entidad/
│   │   ├── RestauranteEntidad.java       Tabla restaurantes
│   │   └── PlatilloEntidad.java          Tabla platillos
│   └── dao/
│       ├── RestauranteDao.java           Consultas de restaurantes
│       └── PlatilloDao.java              Consultas de platillos
├── datos/
│   └── repositorio/
│       ├── RestauranteRepositorio.java   Acceso a datos de restaurantes
│       └── PlatilloRepositorio.java      Acceso a datos de platillos
└── ui/
    ├── ConstantesNavegacion.java         Extras y constantes de navegación
    ├── restaurantes/
    │   ├── ActividadPrincipal.java        Lista de restaurantes (pantalla inicial)
    │   ├── RestauranteAdaptador.java      Adaptador RecyclerView
    │   └── RestauranteVistaModelo.java    ViewModel con búsqueda
    ├── detalle/
    │   ├── ActividadDetalleRestaurante.java    Pestañas: Comida/Bebidas/Complementos
    │   ├── DetalleRestauranteVistaModelo.java
    │   ├── PestanaMenuFragmento.java      Fragment reutilizable (1 por pestaña)
    │   ├── PaginadorMenuAdaptador.java    ViewPager2 adapter
    │   └── PlatilloAdaptador.java         Adaptador de items del menú
    └── detalleplatillo/
        ├── ActividadDetallePlatillo.java   Imagen, precio, nombre, descripción
        └── DetallePlatilloVistaModelo.java
```

## Recursos (res/)

- `values/strings.xml` — Todos los textos en español
- `values/colors.xml` — Paleta de colore
- `values/dimens.xml` — Espaciados y tamaños consistentes
- `values/themes.xml` — Tema Material Components NoActionBar
- `menu/menu_search.xml` — SearchView en toolbar
- `menu/menu_context_restaurant.xml` — Menú contextual (long press)
- `drawable/` — Íconos vectoriales placeholder para restaurantes, comida, bebidas y complementos
