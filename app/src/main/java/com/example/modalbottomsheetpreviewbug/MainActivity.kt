package com.example.modalbottomsheetpreviewbug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreen("Android")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyScreen(name: String) {
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )

    MyScreenContent(name, scope, modalBottomSheetState)
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyScreenContent(
    name: String,
    scope: CoroutineScope,
    sheetState: ModalBottomSheetState,
) {
    MaterialTheme {
        ModalBottomSheetLayout(
            sheetContent = {
                Column {
                    Text(text = "The sheet content!")
                }
            },
            sheetState = sheetState
        ) {
            Column {
                Text(text = "Hello $name!")
                Button(onClick = {
                    scope.launch {
                        sheetState.show()
                    }
                }) {
                    Text("Open sheet")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MyScreenContent(
            "Android",
            rememberCoroutineScope(),
            rememberModalBottomSheetState(
                initialValue = ModalBottomSheetValue.Expanded,
            )
        )
    }
}