package com.example.individualproject2

import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen")
        {
            SplashScreen(navController)
        }
        composable("first_screen") {
            FirstScreen(navController)
        }
        composable("second_screen") {
            SecondScreen(navController)
        }
        composable("third_screen") {
            ThirdScreen(navController)
        }
        composable("fourth_screen") {
            FourthScreen(navController)
        }
        composable("fifth_screen") {
            FifthScreen(navController)
        }
        composable("sixth_screen") {
            SixthScreen(navController)
        }
        composable("seventh_screen") {
            SeventhScreen(navController)
        }
        composable("last_screen") {
            LastScreen(navController)
        }
    }
}

var totalAmount = 0
var numberOfCorrectAnswers = 0

//SplashScreen
@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f,1f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(durationMillis = 1000, 0,easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
            ))
        delay(3000)
        navController.navigate("first_screen")
    }

    //Centers the text and image
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.dollar_sign), contentDescription ="")
        Text("Who wants to be a millionaire")
    }
}
// only commenting one Screen as they are all the same
@Composable
fun FirstScreen(navController: NavController) {
    val context = LocalContext.current
    val radioOptions = listOf("50", "61", "31", "21")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

        // added lazyColum so that on landscape mode it can be scrollable which aligns on the center of the screen
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(Modifier.padding(10.dp)) }
            //shows the top headliner
            item {
                Text(
                    "Homework - who wants to be a Millionaire",
                    Modifier
                        .background(Color.Black)
                        .fillMaxWidth()
                        .padding(20.dp),
                    color = Color.White
                )
            }

            item { Spacer(Modifier.padding(30.dp)) }
            // shows the points who have earned so far
            item {
                Text(
                    "You have Earned: $totalAmount",
                    fontSize = 30.sp,
                    color = Color.Blue
                )
            }

            item { Spacer(Modifier.padding(20.dp)) }
            // Shows the question to the user
            item {
                Text(
                    "How many states does the US have?",
                    fontSize = 20.sp
                )
            }

            item { Spacer(Modifier.padding(10.dp)) }
            // Shows all the radio options from the list
            items(radioOptions) { text ->
                Row(
                    Modifier
                        .background(if (text == selectedOption) Color.Green else Color.Transparent)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
             // Button takes you to the next question but also if you answered correctly then it updates the score and correct answers
            item {
                Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp),
                    onClick = {
                        if (selectedOption == "50") {
                            totalAmount += 100
                            numberOfCorrectAnswers += 1
                            navController.navigate("second_screen")
                            // shows the toast to say that you answered correctly
                            Toast.makeText(
                                context,
                                "Correct! You earned $totalAmount",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            navController.navigate("second_screen")
                            // shows the toast to say that you answered wrong
                            Toast.makeText(
                                context,
                                "Incorrect answer. You have earned $totalAmount",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("Confirm")
                }
            }
        }
}

@Composable
fun SecondScreen(navController: NavController) {
    val context = LocalContext.current
    val radioOptions = listOf("Venus", "Earth", "Mars","Jupyter")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    LazyColumn(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item{Spacer(Modifier.padding(10.dp))}
        item {
            Text(
                "Homework - who wants to be a Millionaire", Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(20.dp),
                color = Color.White
            )
        }
        item{Spacer(Modifier.padding(30.dp))}
        item{
            Text(
                "You have Earned: $$totalAmount",
                fontSize = 30.sp, color = Color.Blue
            )
        }
        item{Spacer(Modifier.padding(20.dp))}
        item {
            Text(
                "What is the only planet in our solar system to rotate clockwise on its axis?",
                fontSize = 20.sp
            )
        }
        item {Spacer(modifier = Modifier.padding(10.dp))}
        items(radioOptions) { text ->
            Row(
                Modifier
                    .background(if (text == selectedOption) Color.Green else Color.Transparent)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        item {
            Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp), onClick = {
                if (selectedOption == "Venus") {
                    totalAmount += 100
                    numberOfCorrectAnswers += 1
                    navController.navigate("third_screen")
                    //shows that you selected the right answer and the amount you have earned
                    Toast.makeText(
                        context,
                        "Correct! You earned $totalAmount",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    navController.navigate("third_screen")
                    //Shows that you have answered the wrong answer and the amount you have
                    Toast.makeText(
                        context,
                        "Incorrect answer. You have earned $totalAmount",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
            ) {
                Text("Confirm")
            }
        }
    }
}

@Composable
fun ThirdScreen(navController: NavController) {
    val context = LocalContext.current
    val radioOptions = listOf("206", "186", "204","190")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    LazyColumn(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
       item {   Spacer(Modifier.padding(10.dp))}
        item{Text(
            "Homework - who wants to be a Millionaire", Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White
        )}
        item{Spacer(Modifier.padding(30.dp))}
        item{Text("You have Earned: $$totalAmount",
            fontSize = 30.sp,color = Color.Blue)}
        item{Spacer(Modifier.padding(20.dp))}
        item{Text("How many bones are there in the adult human body??",
            fontSize = 20.sp)}
       item{Spacer(modifier = Modifier.padding(10.dp))}
        items(radioOptions) { text ->
            Row(
                Modifier
                    .background(if (text == selectedOption) Color.Green else Color.Transparent)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        item {
            Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp), onClick = {
                if (selectedOption == "206") {
                    totalAmount += 100
                    numberOfCorrectAnswers += 1
                    navController.navigate("fourth_screen")
                    //shows that you selected the right answer and the amount you have earned
                    Toast.makeText(context, "Correct! You earned $totalAmount", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    navController.navigate("fourth_screen")
                    //Shows that you have answered the wrong answer and the amount you have
                    Toast.makeText(
                        context,
                        "Incorrect answer. You have earned $totalAmount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text("Confirm")
            }
        }
    }
}
@Composable
fun FourthScreen(navController: NavController) {
    val context = LocalContext.current
    val radioOptions = listOf("Cinnamon", "Cardamom", "Nutmeg","Black pepper")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    LazyColumn (verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item{Spacer(Modifier.padding(10.dp))}
            item{Text(
            "Homework - who wants to be a Millionaire", Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White
        )}
        item{ Spacer(Modifier.padding(30.dp))}
        item{Text("You have Earned: $$totalAmount",
            fontSize = 30.sp,color = Color.Blue)}
        item{Spacer(Modifier.padding(20.dp))}
        item{Text("Which spice is known as \"queen of spices\"?",
            fontSize = 20.sp)}
        item{Spacer(modifier = Modifier.padding(10.dp))}
        items(radioOptions){ text ->
            Row(
                Modifier
                    .background(if (text == selectedOption) Color.Green else Color.Transparent)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        item {
            Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp), onClick = {
                if (selectedOption == "Cardamom") {
                    totalAmount += 100
                    numberOfCorrectAnswers += 1
                    navController.navigate("fifth_screen")
                    //shows that you selected the right answer and the amount you have earned
                    Toast.makeText(context, "Correct! You earned $totalAmount", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    navController.navigate("fifth_screen")
                    //Shows that you have answered the wrong answer and the amount you have
                    Toast.makeText(
                        context,
                        "Incorrect answer. You have earned $totalAmount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text("Confirm")
            }
        }
    }
}
@Composable
fun FifthScreen(navController: NavController) {
    val context = LocalContext.current
    val radioOptions = listOf("Mayella", "Scout", "Jem"," Calpurnia")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
   LazyColumn(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
       item{Spacer(Modifier.padding(10.dp))}
       item{Text(
            "Homework - who wants to be a Millionaire", Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White
        )}
       item{Spacer(Modifier.padding(30.dp))}
       item{Text("You have Earned: $$totalAmount",
            fontSize = 30.sp,color = Color.Blue)}
       item{ Spacer(Modifier.padding(20.dp))}
       item{Text("In \"To Kill a Mockingbird,\" what is the name of Atticus Finch's daughter?",
            fontSize = 20.sp)}
       item{Spacer(modifier = Modifier.padding(10.dp))}
       items(radioOptions){ text ->
            Row(
                Modifier
                    .background(if (text == selectedOption) Color.Green else Color.Transparent)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

       item {
           Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp), onClick = {
               if (selectedOption == "Jem") {
                   totalAmount += 100
                   numberOfCorrectAnswers += 1
                   navController.navigate("sixth_screen")
                   //shows that you selected the right answer and the amount you have earned
                   Toast.makeText(context, "Correct! You earned $totalAmount", Toast.LENGTH_SHORT)
                       .show()
               } else {
                   navController.navigate("sixth_screen")
                   //Shows that you have answered the wrong answer and the amount you have
                   Toast.makeText(
                       context,
                       "Incorrect answer. You have earned $totalAmount",
                       Toast.LENGTH_SHORT
                   ).show()
               }
           }) {
               Text("Confirm")
           }
       }
    }
}
@Composable
fun SixthScreen(navController: NavController) {
    val context = LocalContext.current
    val radioOptions = listOf("Carrying oxygen", "Fighting infections", "Clotting blood","Regulating body temperature")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    LazyColumn(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item{Spacer(Modifier.padding(10.dp))}
        item{Text(
            "Homework - who wants to be a Millionaire", Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White
        )}
        item{Spacer(Modifier.padding(30.dp))}
        item{Text("You have Earned: $$totalAmount",
            fontSize = 30.sp,color = Color.Blue)}
        item{Spacer(Modifier.padding(20.dp))}
        item{Text("What is the main function of red blood cells?",
            fontSize = 20.sp)}
        item{Spacer(modifier = Modifier.padding(10.dp))}
        items(radioOptions){ text ->
            Row(
                Modifier
                    .background(if (text == selectedOption) Color.Green else Color.Transparent)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        item {
            Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp), onClick = {
                if (selectedOption == "Carrying oxygen") {
                    totalAmount += 100
                    numberOfCorrectAnswers += 1
                    navController.navigate("seventh_screen")
                    //shows that you selected the right answer and the amount you have earned
                    Toast.makeText(context, "Correct! You earned $totalAmount", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    navController.navigate("seventh_screen")
                    //Shows that you have answered the wrong answer and the amount you have
                    Toast.makeText(
                        context,
                        "Incorrect answer. You have earned $totalAmount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text("Confirm")
            }
        }
    }
}
@Composable
fun SeventhScreen(navController: NavController) {
    val radioOptions = listOf("Tibia", "Femur", "Humerus","Radius")
    val context = LocalContext.current
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        item{Spacer(Modifier.padding(10.dp))}
        item{Text(
            "Homework - who wants to be a Millionaire", Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White
        )}
        item{Spacer(Modifier.padding(30.dp))}
        item{Text(
            "You have Earned: $$totalAmount",
            fontSize = 30.sp, color = Color.Blue
        )}
        item{Spacer(Modifier.padding(20.dp))}
        item{Text(
            "Which human bone is the longest and strongest?",
            fontSize = 20.sp
        )}
        item{Spacer(modifier = Modifier.padding(10.dp))}
        items(radioOptions){ text ->
            Row(
                Modifier
                    .background(if (text == selectedOption) Color.Green else Color.Transparent)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        item {
            Button(modifier = Modifier.paddingFromBaseline(240.dp, 0.dp).padding(bottom = 16.dp), onClick = {
                if (selectedOption == "Femur") {
                    totalAmount += 100
                    numberOfCorrectAnswers += 1
                    navController.navigate("last_screen")
                    //shows that you selected the right answer and the amount you have earned
                    Toast.makeText(context, "Correct! You earned $totalAmount", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    navController.navigate("last_screen")
                    //Shows that you have answered the wrong answer and the amount you have
                    Toast.makeText(
                        context,
                        "Incorrect answer. You have earned $totalAmount",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text("Confirm")
            }
        }
    }
}

@Composable
fun LastScreen(navController: NavController){
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally){
        //all the texts
        item{Text("Game over",
            modifier = Modifier.padding(20.dp),fontSize = 40.sp)}
        item{Text("Here are your Statistics",
            modifier = Modifier.padding(20.dp),fontSize = 30.sp)}
        item{Text("Amount Correct", fontSize = 20.sp)}
        //shows the number of questions you got right
        item{Text("$numberOfCorrectAnswers/7",
            modifier = Modifier.padding(20.dp), fontSize = 20.sp)}
        item{Text("Total Earnings", fontSize = 20.sp)}
        //shows the total earnings
        item{Text("$totalAmount", fontSize = 30.sp)}
        //resets teh number of correct and total amount to zero and takes you to the first question
        item {
            Button(onClick = {
                navController.navigate("first_screen")
                numberOfCorrectAnswers = 0
                totalAmount = 0
            }) {
                Text("Play Again")
            }
        }
    }
}