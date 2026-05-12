package com.example.budilnik
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
const val teal700code = 0xFF018786
val teal700 = Color(teal700code)
@Composable
fun ColorB(f: Color): ButtonColors { return ButtonDefaults.buttonColors(containerColor = f) }
@ExperimentalMaterial3Api
@Composable
fun ColorT(f: Color): TopAppBarColors { return TopAppBarDefaults.topAppBarColors(containerColor = f) }
