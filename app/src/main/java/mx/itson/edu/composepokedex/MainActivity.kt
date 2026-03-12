package mx.itson.edu.composepokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.itson.edu.composepokedex.domain.Pokemon
import mx.itson.edu.composepokedex.ui.theme.ComposepokedexTheme

class MainActivity : ComponentActivity() {
    val pokemon = Pokemon(
        name = "Pikachu",
        number = 25,
        type = "Eléctrico",
        description = "Pikachu es un Pokémon de tipo Eléctrico conocido por ser un pequeño roedor amarillo, icónico por sus mejillas rojas que almacenan electricidad, cola en forma de rayo y orejas con puntas negras",
        height = 0.4f,
        weight = 6f,
        fav = true,
        ability = "Estática",
        R.drawable.pikachu
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposepokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        pokemon = pokemon,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun PokemonHeader(name: String, number: Int, fav: Boolean) {
        Row(
            Modifier.fillMaxWidth().padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(name,)
                Text(text = "#${number}", modifier = Modifier.align(Alignment.End))
            }
            Box {
                Image(
                    painter = painterResource(R.drawable.pokeball),
                    contentDescription = "pokeball image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(130.dp).offset(30.dp, 20.dp)
                )
                Image(
                    painter = painterResource(if (fav) R.drawable.star_filled else R.drawable.star_outline),
                    contentDescription = if (fav) "yellow star filled" else "yellow star outlined",
                    modifier = Modifier.align(Alignment.TopEnd)
                )
            }
        }
    }

    @Composable
    fun PokemonCard(
        name: String,
        weight: Float,
        height: Float,
        description: String,
        ability: String,
        image: Int
    ) {
        Card {
            Image(painter = painterResource(image), contentDescription = name)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PokemonHeaderPreview() {
        ComposepokedexTheme() {
            PokemonHeader(name = "Pikachu", number = 25, fav = true)
        }
    }

    @Composable
    fun Greeting(pokemon: Pokemon, modifier: Modifier = Modifier) {
        Column {
            PokemonHeader(pokemon.name, pokemon.number, pokemon.fav)
            PokemonCard(
                pokemon.name,
                pokemon.weight,
                pokemon.height,
                pokemon.description,
                pokemon.ability,
                pokemon.image
            )
        }
    }
}