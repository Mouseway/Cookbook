package com.example.cookbook2.utils

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cookbook2.R

@Composable
fun MyAppBar(title: String = "",
             backButton: Boolean = false,
             onBackBtnClick: () -> Unit = {},
             searchButton: Boolean = false,
             onSearchClick: () -> Unit = {},
             favoriteButton: Boolean = false,
             onFavoriteClick: () -> Unit = {},
             favoriteState: FavoriteState = FavoriteState.NOT_FAVORITE
){
    TopAppBar(backgroundColor = MaterialTheme.colorScheme.primary) {
        Box(
            Modifier.fillMaxSize()
        ){
            Row(Modifier.align(Alignment.CenterStart)) {
                if (backButton) BackButton { onBackBtnClick() }
                if (title != "") BarTitle(title = title)
            }
            Row(Modifier.align(Alignment.CenterEnd)) {
                if(searchButton) SearchButton{onSearchClick()}
                if(favoriteButton) FavoriteButton(favoriteState){onFavoriteClick()}
            }
        }
    }
}

@Composable
fun FavoriteButton(favoriteState: FavoriteState, onClick: () -> Unit){

    val icon = when(favoriteState){
        FavoriteState.FAVORITE -> R.drawable.heart_filled
        else -> R.drawable.heart
    }
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Favorite",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(15.dp)
        )
    }
}


@Composable
fun BackButton(onBackBtnClick: () -> Unit){

    IconButton(onClick = {onBackBtnClick()}) {
        Icon(painter = painterResource(id = R.drawable.arrow_small_left),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(5.dp)
        )
    }

}

@Composable
fun BarTitle(title: String){
    Box(
        Modifier
            .fillMaxHeight()){
        Box(
            Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.CenterStart)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun SearchButton(onClick: () -> Unit){
    IconButton(
        onClick = { onClick() }
    ){
        Icon(painter = painterResource(id = R.drawable.search),
            contentDescription = "Search",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(15.dp)
        )
    }
}

@Composable
fun SearchingTopBar(text: String, onTextChange: (String) -> Unit, onCloseClick: () -> Unit, ){
    TopAppBar(backgroundColor = MaterialTheme.colorScheme.primary) {
        Box(
            Modifier.fillMaxSize()
        ){
            TextField(
                modifier = Modifier
                   .fillMaxWidth(),
                value = text,
                onValueChange = {txt -> onTextChange(txt)},
                singleLine = true,
                placeholder = {Text(
                   text = "Palačinka...",
                   color = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium)
                )},
                leadingIcon = {
                   Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium ),
                        modifier = Modifier
                            .padding(15.dp)
                   )
                 },
                keyboardOptions = KeyboardOptions(
                   imeAction = ImeAction.Search
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    textColor = MaterialTheme.colorScheme.onPrimary
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        if(text == ""){
                            onCloseClick()
                        }else{
                            onTextChange("")
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "close",
                            tint = MaterialTheme.colorScheme.onPrimary.copy(alpha = ContentAlpha.medium),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                },

           )
        }
    }
}
