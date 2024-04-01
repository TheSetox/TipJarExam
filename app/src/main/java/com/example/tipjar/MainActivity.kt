package com.example.tipjar

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.tipjar.view.App
import com.example.tipjar.viewmodel.PaymentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>

    private lateinit var viewModel: PaymentViewModel

    private var paymentAmount: String = ""

    private var paymentTotalTip: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerPictureActivityResult()
        setContent {
            App(
                onViewModel = { viewModel = it },
                onLaunchCamera = { amount, totalTip ->
                    onLaunchCamera(amount, totalTip)
                },
            )
        }
    }

    private fun registerPictureActivityResult() {
        takePictureLauncher =
            registerForActivityResult(
                ActivityResultContracts.TakePicture(),
            ) { isSaved ->
                if (isSaved) viewModel.savePaymentWithImage(paymentAmount, paymentTotalTip)
            }
    }

    private fun onLaunchCamera(
        amount: String,
        totalTip: Float,
    ) {
        paymentAmount = amount
        paymentTotalTip = totalTip
        when (arePermissionsGranted()) {
            true -> launchCamera()
            false -> launchPermission()
        }
    }

    private fun launchCamera() = takePictureLauncher.launch(viewModel.createImageIntentUri())

    private fun launchPermission() = permissionsLauncher.launch(permissions)

    private fun arePermissionsGranted(): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private val permissionsLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
        ) { permissions ->
            val allGranted = permissions.all { it.value }
            if (allGranted) {
                launchCamera()
            } else {
                Toast.makeText(
                    this,
                    "Permission required to capture and save image.",
                    Toast.LENGTH_SHORT,
                ).show()
                startActivity(
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        data = Uri.fromParts("package", packageName, null)
                    },
                )
            }
        }

    companion object {
        private val permissions =
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )
    }
}
