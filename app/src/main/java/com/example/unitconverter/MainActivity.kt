package com.example.unitconverter

import android.annotation.SuppressLint
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter()
                }
            }
        }
    }
}
@SuppressLint("RememberReturnType")
@Composable
fun UnitConverter(){
    var inputvalue = remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }

    val inputunit = remember { mutableStateOf("Meters") }
    var outputunit by remember { mutableStateOf("Meters") }

    val iexp = remember { mutableStateOf(false) }
    val oexp = remember { mutableStateOf(false) }
    val factor = remember { mutableStateOf(1.0) }
    val ofactor = remember { mutableStateOf(1.0) }
    fun converter(){
        var inputValueDouble = inputvalue.value.toDoubleOrNull()?:0.0
        val result = (inputValueDouble * factor.value *100.0/ofactor.value).roundToInt()/100.0
        outputvalue= result.toString()


    }
    





    Column(modifier =Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        )
    {

        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputvalue.value, onValueChange ={
            inputvalue.value = it


        } , label = { Text(text = "enter a value")})

        
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iexp.value = true }) {
                    Text(text = inputunit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription  = "" )

                }
                DropdownMenu(expanded = iexp.value, onDismissRequest = { iexp.value=false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        iexp.value=false
                        inputunit.value="Centimeters"
                        factor.value=0.01
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Meters")}, onClick = {
                        iexp.value=false
                        inputunit.value="Meters"
                        factor.value=1.0
                        converter()


                    })
                    



                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        iexp.value=false
                        inputunit.value="Feet"
                        factor.value=0.3048
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Milimeters")}, onClick = {
                        iexp.value=false
                        inputunit.value="Milimeters"
                        factor.value=0.001
                        converter()
                    })
                }



            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oexp.value=true }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription  = "" )

                }
                DropdownMenu(expanded = oexp.value, onDismissRequest = { oexp.value=false }) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        oexp.value=false
                        outputunit="Centimeters"
                        ofactor.value=0.01
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Meters")}, onClick = {
                        oexp.value=false
                        outputunit="Meters"
                        ofactor.value=1.0
                        converter()
                    })




                    DropdownMenuItem(text = { Text("Feet")}, onClick = {
                        oexp.value=false
                        outputunit="Feets"
                        ofactor.value=0.3048
                        converter()
                    })
                    DropdownMenuItem(text = { Text("Milimeters")}, onClick = {
                        oexp.value=false
                        outputunit="Milimeters"
                        ofactor.value=0.001
                        converter()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Result: $outputvalue  $outputunit",
            style = MaterialTheme.typography.headlineMedium

        )
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()

}