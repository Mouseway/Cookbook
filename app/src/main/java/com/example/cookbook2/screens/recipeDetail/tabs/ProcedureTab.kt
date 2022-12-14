package com.example.cookbook2.screens.recipeDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook2.domain.Recipe

@Composable
fun ProcedureTab(recipe: Recipe){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
        ProcedureTitle()
    recipe.steps.forEachIndexed { index, step ->
            Step(index + 1, step)
        }
    }
}

@Composable
fun ProcedureTitle(){
    Text(text = "Postup přípravy", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    Divider(Modifier.padding(5.dp))
}

@Composable
fun Step(index: Int, text: String){
    Row(Modifier.padding(top = 30.dp)){
      StepIndex(index = index)
      Text(
          text = text,
          fontSize = 16.sp,
          color = MaterialTheme.colorScheme.onBackground,
          modifier = Modifier.padding(start = 10.dp))
    }
}

@Composable
fun StepIndex(index: Int){
    Box(
        Modifier
            .padding(top = 15.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondary)){
        Text(text = index.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.align(Alignment.Center))
    }
}