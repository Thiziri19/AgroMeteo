package com.example.agrometeo.ui.login

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material3.Checkbox
import androidx.compose.ui.draw.blur
import com.example.agrometeo.SharedPref.SharedPreferencesManager

@Composable
fun Login( onLogin:()->Unit){

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val error = remember { mutableStateOf(false) }
    val rememberChecked = remember { mutableStateOf(false) }
    val context = LocalContext.current
    lateinit var  sharedPreferncesManager : SharedPreferencesManager

    sharedPreferncesManager = SharedPreferencesManager(context);
    //verify if the user is logged then navigate to the right screen
    if(sharedPreferncesManager.isLoggedIn() && sharedPreferncesManager.isRememberMe()){
        onLogin()
        return
    }

    Column(
        modifier = Modifier.fillMaxSize().safeDrawingPadding().imePadding()
            .verticalScroll(rememberScrollState()).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Surface(
            modifier = Modifier.size(72.dp),
            shape = MaterialTheme.shapes.large,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(120.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.WbSunny,
                    contentDescription = "Sun",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.TopEnd)
                )

                Icon(
                    imageVector = Icons.Default.Eco,
                    contentDescription = "Plnt",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier
                        .size(55.dp)
                        .align(Alignment.BottomStart)
                )
            }
        }
        Text("AgroMeteo", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(top = 20.dp))
        Text(
            "Une nouvelle plante à ajouter à votre jardin? Allez, c’est parti!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp,start=15.dp, end = 15.dp),
        )
            Column(modifier = Modifier.padding(22.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Bienvenue", style = MaterialTheme.typography.titleLarge)
                Text("Connectez-vous pour retrouver vos cultures!.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it; error.value = false },
                    label = { Text("E-mail") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                )
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it; error.value = false },
                    label = { Text("Mot de passe") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = MaterialTheme.shapes.medium,
                )
                Row(modifier = Modifier.padding(top =10.dp, bottom = 22.dp).fillMaxSize(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text("Se souvenir de moi?", style = MaterialTheme.typography.bodyMedium)
                    Checkbox( checked = rememberChecked.value, onCheckedChange ={rememberChecked.value=it})
                }
                Button(

                    onClick = {
                        if (email.value.isBlank() || password.value.isBlank()) {
                            error.value = true
                        } else {
                            error.value = false
                            sharedPreferncesManager.saveRemmeberMe(rememberChecked.value)
                            if (rememberChecked.value) {
                                sharedPreferncesManager.saveUserEmail(email.value)
                                sharedPreferncesManager.saveLoggedIn(true)
                            } else {
                                sharedPreferncesManager.saveLoggedIn(false)
                            }
                            onLogin()
                        }
                    },

                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2E7D32),
                        contentColor = Color.White
                    )
                ) { Text("Se connecter") }
                if(error.value) {
                    Toast.makeText(context,"veuillez renseigner vos identifiants", Toast.LENGTH_SHORT).show();
                }
            }
    }
}