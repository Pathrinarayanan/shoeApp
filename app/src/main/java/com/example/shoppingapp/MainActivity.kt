package com.example.shoppingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import com.example.curvedbottombar.FloatedNavigationBar
import com.example.curvedbottombar.model.NavItem
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.ui.theme.ShoppingAppTheme

class MainActivity : ComponentActivity() {
    private  lateinit var viewmodel: MainViewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MainViewmodel::class)
        enableEdgeToEdge()
        viewmodel.fetchData()
        setContent {
            ShoppingAppTheme {
                MainScreen(viewmodel)
            }
        }
    }
}


@Composable
fun CustomTopBar(viewmodel: MainViewmodel){
    Column(
        modifier= Modifier
            .padding(top = 20.dp)
            .padding(16.dp)
    ){
        Row {
            Icon(
                Icons.Filled.Menu,
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
            Text("My Shoe App", Modifier, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Filled.Search,
                contentDescription = null
            )
        }
        Text("Popular Products", Modifier.padding(top =20.dp), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Row(
            modifier =Modifier,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            viewmodel.data?.value?.brands?.forEach {
                Box(
                    Modifier
                        .padding(top = 16.dp)
                        .size(height = 30.dp, width = 50.dp)
                        .border(1.dp, Color(0xffcae5ff), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        rememberAsyncImagePainter(it),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
        }
    }
}


@Composable
fun MainScreen(viewmodel: MainViewmodel) {
    var selectedIndex by remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize().background(Color.White),
        topBar = {
            CustomTopBar(viewmodel)
        },
        bottomBar = {
            val bottomItems = listOf(
                NavItem(
                    Icons.Filled.Home,
                    "Home"
                ),
                NavItem(
                    Icons.Filled.Favorite,
                    "Favorites"
                ),
                NavItem(
                    Icons.Filled.ShoppingCart,
                    "Cart"
                ),
                NavItem(
                    Icons.Filled.Person,
                    "Profile"
                ),
            )

            FloatedNavigationBar(
                modifier =Modifier,
                selectedIndex = selectedIndex,
                backgroundColor = Color(0xffcae5ff),
                items = bottomItems,
                onItemSelected = {
                    selectedIndex = it
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            Modifier.padding(innerPadding).padding(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            viewmodel.data?.value?.products?.forEach {
                item {
                    Product(it)
                }
            }
        }
    }
}


@Composable
fun Product(product: Product?) {
    Column (
        modifier  = Modifier.height(250.dp).width(185.dp)
            .background(Color(0xfff1f1f1), RoundedCornerShape(20.dp))
            .padding(start = 16.dp, end =16.dp, bottom = 16.dp)
    ) {
        val gradient = Brush.verticalGradient(
            listOf(
                Color(0xff3b9eff),
                Color(0xff2e2e2e)
            )
        )
        Row{
            Box(
                modifier = Modifier.size(height = 35.dp, width = 30.dp)
                    .padding(start = 4.dp)
                    .background(gradient ),
                contentAlignment = Alignment.Center
            ){
                Text(product?.rating.toString(), Modifier, fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.heart_icon),
                contentDescription = null,
                modifier= Modifier.padding(top = 10.dp).size(20.dp)
            )
        }
        Box(
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
        Image(
             rememberAsyncImagePainter(product?.img),
            contentDescription = "shoe",
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(20.dp))
        )
    }
        Text(
            product?.title?:"",
            Modifier.padding(top = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            product?.subtitle?:"",
            Modifier.padding(top = 2.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray
        )
        Row(
            Modifier.padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "$ ${product?.price}",
                Modifier.padding(top = 2.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff357cfe)
            )
            Spacer(modifier =Modifier.weight(1f))
            Box(
                modifier = Modifier.size(height = 25.dp, width = 40.dp)
                    .background(Color(0xffcae5ff), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ){
                Text("Add", Modifier, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}