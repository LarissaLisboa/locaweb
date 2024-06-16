package br.com.fiap.locaweb.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.locaweb.Email
import br.com.fiap.locaweb.EmailViewModel
import br.com.fiap.locaweb.R

@Composable
fun OpenedEmail(modifier: Modifier = Modifier, email: Email, navController: NavController, emailViewModel: EmailViewModel = viewModel()) {
    val lightRoseColor = Color(0.8689f, 0.6169f, 0.6139f)

    Column(
        modifier = modifier
            .padding(10.dp)
    ) {

        Button(
            onClick = { navController.popBackStack() }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, contentColor = Color.Black
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Voltar"
            )
        }
        Text(
            text = email.subject,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(lightRoseColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = email.sender.take(2),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = email.sender,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { emailViewModel.toggleFavorite(email) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                Image(
                    painter = painterResource(id = if (email.isStarred) R.drawable.star else R.drawable.ic_star_outline),
                    contentDescription = if (email.isStarred) "Favoritado" else "Favoritar",
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            Image(
                painter = painterResource(id = R.drawable.dots),
                contentDescription = "Dots",
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if(email.hasEvent) {
            EventBox("titulo", "01/01/2000", link = "link", onRsvpClick = {url -> println("aqui")})
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = email.content,
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 18.dp),
        )
    }
}