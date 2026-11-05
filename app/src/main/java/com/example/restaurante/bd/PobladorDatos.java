package com.example.restaurante.bd;

import com.example.restaurante.bd.dao.PlatilloDao;
import com.example.restaurante.bd.dao.RestauranteDao;
import com.example.restaurante.bd.entidad.PlatilloEntidad;
import com.example.restaurante.bd.entidad.RestauranteEntidad;

import java.util.ArrayList;
import java.util.List;

public final class PobladorDatos {

    private PobladorDatos() {}

    public static void poblar(BaseDeDatos bd) {
        RestauranteDao restauranteDao = bd.restauranteDao();
        PlatilloDao platilloDao = bd.platilloDao();

        List<RestauranteEntidad> restaurantes = new ArrayList<>();
        restaurantes.add(new RestauranteEntidad(
                "El Cardenal",
                "Restaurante emblemático con más de 50 años de tradición en el Centro Histórico, especializado en desayunos y cocina tradicional mexicana.",
                "ic_placeholder_restaurant"));
        restaurantes.add(new RestauranteEntidad(
                "Café de Tacuba",
                "Restaurante histórico fundado en 1912, famoso por su cocina tradicional mexicana y su hermosa arquitectura colonial en la calle de Tacuba.",
                "ic_placeholder_restaurant"));
        restaurantes.add(new RestauranteEntidad(
                "Los Girasoles",
                "Restaurante contemporáneo frente al Zócalo con vista privilegiada, especializado en cocina mexicana de autor con ingredientes de temporada.",
                "ic_placeholder_restaurant"));
        restaurantes.add(new RestauranteEntidad(
                "Hostería de Santo Domingo",
                "El restaurante más antiguo de la Ciudad de México, operando desde 1860, con platillos clásicos de la cocina novohispana y ambiente tradicional.",
                "ic_placeholder_restaurant"));
        restauranteDao.insertarTodos(restaurantes);

        List<PlatilloEntidad> platillos = new ArrayList<>();

        // Comida
        platillos.add(new PlatilloEntidad(1, "Chilaquiles verdes", 145.00,
                "Totopos bañados en salsa verde con crema, queso fresco y cebolla. Acompañados de frijoles refritos.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(1, "Enchiladas suizas", 165.00,
                "Tortillas rellenas de pollo cubiertas con salsa de tomate verde y gratinadas con queso. Servidas con crema.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(1, "Mole poblano", 195.00,
                "Pieza de pollo bañada en mole poblano tradicional preparado con más de 20 ingredientes. Acompañado de arroz rojo.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(1, "Huevos rancheros", 135.00,
                "Huevos estrellados sobre tortilla de maíz con salsa ranchera, frijoles refritos y chilaquiles.",
                "comida", "ic_placeholder_food"));

        // Bebidas
        platillos.add(new PlatilloEntidad(1, "Chocolate caliente", 65.00,
                "Chocolate de metate preparado con agua o leche al estilo tradicional oaxaqueño.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(1, "Agua de jamaica", 45.00,
                "Agua fresca de flor de jamaica endulzada naturalmente, servida con hielo.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(1, "Café americano", 55.00,
                "Café de grano seleccionado de Chiapas, preparado en cafetera profesional.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(1, "Jugo de naranja", 50.00,
                "Jugo de naranja natural recién exprimido, servido en vaso grande.",
                "bebida", "ic_placeholder_drink"));

        // Complementos
        platillos.add(new PlatilloEntidad(1, "Guacamole", 85.00,
                "Guacamole preparado al momento con aguacate, jitomate, cebolla, cilantro y chile serrano.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(1, "Frijoles refritos", 45.00,
                "Frijoles negros refritos con cebolla y epazote, acompañados de totopos.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(1, "Arroz rojo", 40.00,
                "Arroz rojo mexicano preparado con jitomate, cebolla y ajo. Cocido al estilo casero.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(1, "Tortillas a mano", 35.00,
                "Tortillas de maíz hechas a mano al momento en comal de barro.",
                "complemento", "ic_placeholder_side"));

        // === Café de Tacuba (id = 2) ===
        // Comida
        platillos.add(new PlatilloEntidad(2, "Tamales oaxaqueños", 95.00,
                "Tamales envueltos en hoja de plátano rellenos de mole negro con pollo deshebrado.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(2, "Pozole rojo", 155.00,
                "Caldo de maíz cacahuazintle con carne de cerdo en salsa de chile guajillo. Con lechuga, rábano y tostadas.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(2, "Chiles en nogada", 210.00,
                "Chile poblano relleno de picadillo con frutas, cubierto con nogada de nuez de castilla y granada.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(2, "Sopa azteca", 115.00,
                "Sopa de tortilla con chile pasilla, aguacate, crema, queso panela y tiras de tortilla crujientes.",
                "comida", "ic_placeholder_food"));

        // Bebidas
        platillos.add(new PlatilloEntidad(2, "Atole de vainilla", 55.00,
                "Atole caliente de masa de maíz con vainilla natural de Papantla.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(2, "Horchata", 45.00,
                "Agua fresca de arroz con canela y vainilla, servida bien fría con hielo.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(2, "Limonada natural", 40.00,
                "Limonada preparada con limón real, agua mineral o natural, al gusto.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(2, "Café de olla", 50.00,
                "Café preparado en olla de barro con piloncillo, canela y clavo.",
                "bebida", "ic_placeholder_drink"));

        // Complementos
        platillos.add(new PlatilloEntidad(2, "Quesadillas de flor de calabaza", 75.00,
                "Quesadillas de masa de maíz azul rellenas de flor de calabaza con quesillo de Oaxaca.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(2, "Esquites", 55.00,
                "Granos de elote cocidos con epazote, servidos con mayonesa, queso, chile y limón.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(2, "Elote preparado", 45.00,
                "Elote entero cocido untado con mayonesa, queso cotija, chile piquín y limón.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(2, "Nopales asados", 50.00,
                "Pencas de nopal asadas al comal con cebolla, queso panela y salsa verde.",
                "complemento", "ic_placeholder_side"));

        // === Los Girasoles (id = 3) ===
        // Comida
        platillos.add(new PlatilloEntidad(3, "Filete en salsa pasilla", 285.00,
                "Filete de res sellado cubierto con salsa de chile pasilla y guarnición de verduras de temporada.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(3, "Pechuga en mole negro", 245.00,
                "Pechuga de pollo bañada en mole negro oaxaqueño, acompañada de arroz y plátano macho.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(3, "Tlacoyos de huitlacoche", 165.00,
                "Tlacoyos de masa azul rellenos de huitlacoche, servidos con nopales, queso y salsa.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(3, "Tacos de cochinita", 175.00,
                "Tacos de cochinita pibil marinada en achiote, servidos con cebolla morada curtida y habanero.",
                "comida", "ic_placeholder_food"));

        // Bebidas
        platillos.add(new PlatilloEntidad(3, "Mezcal artesanal", 120.00,
                "Mezcal joven artesanal de Oaxaca, servido con sal de gusano y naranja.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(3, "Margarita", 145.00,
                "Margarita clásica con tequila reposado, jugo de limón natural y triple sec. Con sal en el borde.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(3, "Agua de chía", 55.00,
                "Agua fresca de chía con limón, preparada con semillas de chía hidratadas.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(3, "Cerveza artesanal", 85.00,
                "Cerveza artesanal mexicana de temporada, servida en tarro helado.",
                "bebida", "ic_placeholder_drink"));

        // Complementos
        platillos.add(new PlatilloEntidad(3, "Ensalada de nopales", 75.00,
                "Nopales cocidos con jitomate, cebolla, cilantro y queso panela desmoronado.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(3, "Sopa de tortilla", 95.00,
                "Sopa de jitomate con tiras de tortilla crujientes, aguacate, crema y chile pasilla.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(3, "Guacamole con chapulines", 110.00,
                "Guacamole artesanal con chapulines tostados de Oaxaca y totopos de maíz.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(3, "Crema de elote", 85.00,
                "Crema de elote tierno preparada con granos frescos, epazote y un toque de chile poblano.",
                "complemento", "ic_placeholder_side"));

        // === Hostería de Santo Domingo (id = 4) ===
        // Comida
        platillos.add(new PlatilloEntidad(4, "Chiles en nogada", 230.00,
                "Chile poblano relleno de picadillo con frutas de temporada, bañado en nogada y decorado con granada y perejil.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(4, "Mole de olla", 175.00,
                "Caldo tradicional con carne de res, xoconostle, ejotes, calabaza, elote y chile ancho.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(4, "Enchiladas de mole", 160.00,
                "Tortillas rellenas de pollo bañadas en mole poblano con ajonjolí, crema y queso fresco.",
                "comida", "ic_placeholder_food"));
        platillos.add(new PlatilloEntidad(4, "Pambazos", 125.00,
                "Pan bañado en salsa de guajillo relleno de papa con chorizo, lechuga, crema y queso.",
                "comida", "ic_placeholder_food"));

        // Bebidas
        platillos.add(new PlatilloEntidad(4, "Tequila reposado", 110.00,
                "Caballito de tequila reposado de agave azul, acompañado de sangrita y limón.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(4, "Pulque curado", 65.00,
                "Pulque natural curado de frutas de temporada, preparado artesanalmente.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(4, "Agua de tamarindo", 45.00,
                "Agua fresca de tamarindo natural con un toque de chile piquín y limón.",
                "bebida", "ic_placeholder_drink"));
        platillos.add(new PlatilloEntidad(4, "Tepache", 50.00,
                "Bebida fermentada de piña con piloncillo y canela, servida bien fría.",
                "bebida", "ic_placeholder_drink"));

        // Complementos
        platillos.add(new PlatilloEntidad(4, "Sopes de frijol", 70.00,
                "Sopes de masa gruesa con frijoles refritos, lechuga, crema, queso y salsa verde.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(4, "Tostadas de tinga", 85.00,
                "Tostadas crujientes con tinga de pollo en chipotle, lechuga, crema y queso fresco.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(4, "Chalupas", 75.00,
                "Tortillas fritas con salsa verde o roja, cebolla, carne deshebrada y queso.",
                "complemento", "ic_placeholder_side"));
        platillos.add(new PlatilloEntidad(4, "Molletes", 65.00,
                "Bolillo abierto con frijoles refritos y queso gratinado, acompañados de pico de gallo.",
                "complemento", "ic_placeholder_side"));

        platilloDao.insertarTodos(platillos);
    }
}
