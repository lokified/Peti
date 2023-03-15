package com.loki.peti.ui.createProfile

import android.Manifest
import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.dsc.form_builder.TextFieldState
import com.loki.peti.R
import com.loki.peti.ui.common.BasicInput
import com.loki.peti.ui.common.ButtonSection
import com.loki.peti.ui.common.TopBar
import com.loki.peti.ui.theme.Teal_100
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileSetup2Screen(
    openScreen: (String) -> Unit,
    viewModel: ProfileSetUpViewModel = hiltViewModel()
) {

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val activity = remember { context as? Activity }

    //image values
    var imageUri by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val formState = remember { viewModel.petProfileState }

    //form values
    val petName = formState.getState<TextFieldState>("petName")
    val birthday = formState.getState<TextFieldState>("birthdate")
    val gender = formState.getState<TextFieldState>("gender")


    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
    ) { success ->

        if (success) {

            scope.launch {
                bottomSheetState.hide()
            }
            val uri = Uri.parse(imageUri)
            viewModel.imageBitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    val chooseLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->

        if(uri != null) {

            scope.launch {
                bottomSheetState.hide()
            }
            viewModel.imageBitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }
    }

    BottomSheet(
        bottomSheetState = bottomSheetState,
        onTakePhotoClick = {

            if (checkPermission(activity!!)) {

                val timestamp = System.currentTimeMillis()
                val contentValues = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, "peti_${timestamp}.jpg")
                    put(MediaStore.Images.Media.MIME_TYPE, "peti_${timestamp}/jpeg")
                }
                val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                imageUri = uri.toString()
                photoLauncher.launch(uri)
            }
        },
        onChooseClick = {

            if(checkPermission(activity!!)) {
                chooseLauncher.launch("image/*")
            }
        }
    ) {

        TopBar(
            title = "Profile Setup",
            bottomBar = {
                ButtonSection(
                    text = "Next",
                    modifier = Modifier.padding(16.dp)
                ) {

                    keyboardController?.hide()
                    if(formState.validate()) {
                        viewModel.savePetProfile(
                            petName = petName.value,
                            birthday = birthday.value,
                            gender = gender.value
                        ) { route ->
                            openScreen(route)
                        }
                    }
                }
            }
        ) {

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = "Pet Profile",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .size(height = 160.dp, width = 160.dp)
                                .clip(CircleShape)
                                .clickable {
                                    scope.launch {
                                        bottomSheetState.show()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            
                            if (viewModel.imageBitmap.value == null) {
                                Surface(
                                    color = Color.Black.copy(alpha = 0.5f),
                                    modifier = Modifier.fillMaxSize()
                                ) {

                                    Image(
                                        painter = painterResource(id = R.drawable.ic_account_circle),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(
                                                shape = RoundedCornerShape(20.dp),
                                                color = Color.Transparent
                                            ),
                                        contentScale = ContentScale.Crop
                                    )

                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Choose Photo",
                                            color = Color.White,
                                            fontSize = 18.sp,
                                            modifier = Modifier.align(Alignment.Center),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            } else {

                                Image(
                                    bitmap = viewModel.imageBitmap.value!!.asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            shape = RoundedCornerShape(20.dp),
                                            color = Color.Transparent
                                        ),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Column {
                        TextFieldRow(
                            placeholder = "Enter Pet name",
                            value = petName.value,
                            onValueChange = { petName.change(it) },
                            errorMessage = petName.errorMessage,
                            isError = petName.hasError,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        val mCalendar = Calendar.getInstance()
                        val year = mCalendar.get(Calendar.YEAR)
                        val month = mCalendar.get(Calendar.MONTH)
                        val day = mCalendar.get(Calendar.DAY_OF_MONTH)

                        mCalendar.time = Date()

                        val picker = DatePickerDialog(
                            context,
                            {_: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
                                birthday.change("$mDay/${mMonth + 1}/$mYear")
                            }, year, month, day
                        )

                        TextFieldRow(
                            placeholder = "Enter Birth date",
                            value = birthday.value,
                            onValueChange = { birthday.change(it) },
                            errorMessage = birthday.errorMessage,
                            isError = birthday.hasError,
                            isIconVisible = true,
                            trailingIcon = Icons.Default.DateRange,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            keyboardController?.hide()
                            picker.show()
                        }

                        TextFieldRow(
                            placeholder = "Enter gender",
                            value = gender.value,
                            onValueChange = { gender.change(it) },
                            errorMessage = gender.errorMessage,
                            isError = gender.hasError,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldRow(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String = "",
    isError: Boolean = false,
    isIconVisible: Boolean = false,
    trailingIcon: ImageVector = Icons.Default.Animation,
    onIconClicked: () -> Unit = {}
) {

    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(color = Teal_100, shape = RoundedCornerShape(5.dp))
        ) {


            BasicInput(
                placeholder = placeholder,
                value = value,
                onValueChange = onValueChange,
                trailingIcon = trailingIcon,
                onIconClicked = onIconClicked,
                errorMessage = errorMessage,
                isError = isError,
                isIconVisible = isIconVisible,
            )

            if (isIconVisible) {
                Box(
                    modifier = Modifier.matchParentSize()
                        .alpha(0f)
                        .clickable { onIconClicked() }
                )
            }
        }

        if (isError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                fontSize = 12.sp
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    bottomSheetState: ModalBottomSheetState,
    onTakePhotoClick: () -> Unit,
    onChooseClick: () -> Unit,
    content: @Composable () -> Unit
) {

    ModalBottomSheetLayout(
        sheetContent = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                BottomSheetRow(
                    headerText = "Take Photo",
                    icon = Icons.Default.PhotoCamera,
                    onRowClick = onTakePhotoClick
                )
                BottomSheetRow(
                    headerText = "Choose From Gallery",
                    icon = Icons.Default.PhotoAlbum,
                    onRowClick = onChooseClick
                )
            }
        },
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
        sheetBackgroundColor = MaterialTheme.colors.background,
        scrimColor = Color.Black.copy(alpha = .5f)
    ) {
        content()
    }
}

@Composable
fun BottomSheetRow(
    headerText: String,
    icon: ImageVector,
    onRowClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            imageVector = icon,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = headerText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable {
                    onRowClick()
                }
        )
    }
}


private fun checkPermission(activity: Activity): Boolean {

    return if (activity.checkSelfPermission( WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        true
    }
    else if(activity.checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) {
        true
    }
    else {
        ActivityCompat.requestPermissions(
            activity, arrayOf(
                WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                CAMERA
            ), 1
        ); false
    }
}