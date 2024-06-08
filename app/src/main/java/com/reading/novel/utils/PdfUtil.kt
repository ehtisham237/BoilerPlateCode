package com.reading.novel.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.Base64
import androidx.core.content.FileProvider
import com.reading.novel.BuildConfig
import com.reading.novel.NovelApp.Companion.applicationContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class PdfUtil {

    companion object {
        const val TAG = "Base64ToPdfUtil"
        const val FOLDER_NAME = "bills"
        const val FILE_NAME_WITH_EXT = "bill.pdf"

        fun convertBase64ToPdf(base64String: String) {
            try {
                var file = getFile(
                    folderName = FOLDER_NAME,
                    fileNameWithExt = FILE_NAME_WITH_EXT
                )

                if (file == null) {
                    file = createFile(
                        folderName = FOLDER_NAME,
                        fileNameWithExt = FILE_NAME_WITH_EXT
                    )
                }

                val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
                val outputStream = FileOutputStream(file)
                outputStream.write(decodedBytes)
                outputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getPdfFile(): File? {
            return getFile(
                folderName = FOLDER_NAME,
                fileNameWithExt = FILE_NAME_WITH_EXT
            )
        }

        fun openPdfFile(context: Activity) {
            try {
                val file = getFile(
                    folderName = FOLDER_NAME,
                    fileNameWithExt = FILE_NAME_WITH_EXT
                )

                val pdfFileUri = FileProvider.getUriForFile(
                    context,
                    context.applicationContext.packageName + ".provider",
                    file!!
                )

//                val uri = FileProvider.getUriForFile(
//                    context,
//                    BuildConfig.APPLICATION_ID + ".provider",
//                    file
//                )


                //Create PDF Intent
                val pdfIntent = Intent(Intent.ACTION_VIEW)
                pdfIntent.setDataAndType(pdfFileUri, "application/pdf")
                pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                //Create Viewer Intent
                val viewerIntent = Intent.createChooser(pdfIntent, "Open PDF")
                try {
                    context.startActivity(viewerIntent)
                } catch (e: ActivityNotFoundException) {
                    // Instruct the user to install a PDF reader here, or something
                    e.printStackTrace()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun createFile(folderName: String, fileNameWithExt: String): File {
            val directory: File = applicationContext().getDir(folderName, Context.MODE_PRIVATE)
            if (!directory.exists() && !directory.mkdirs()) {
                LogUtil.e("CreateFile Error creating directory $directory")
            }
            return File(directory, fileNameWithExt)
        }

        fun getFile(folderName: String, fileNameWithExt: String): File? {
            val file = createFile(folderName, fileNameWithExt)
            if (file.exists()) {
                return file
            }
            return null
        }


        fun openPDFContent(context: Context, inputStream: InputStream, fileName: String) {
            //saving in cache directory
            val filePath = context.externalCacheDir?.absolutePath ?: context.cacheDir.absolutePath
            val fileNameExtension =
                fileName.ifEmpty { "bill_ss" + ".pdf" }
            val file = inputStream.saveToFile(filePath, fileNameExtension)

            LogUtil.e("File Path: ${file.absolutePath}")
            LogUtil.e("File Name: ${file.name}")

            val uri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider", file
            )
            LogUtil.e("URI: $uri")
            val intent = Intent(Intent.ACTION_VIEW).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                setDataAndType(uri, "application/pdf")
            }
            context.startActivity(Intent.createChooser(intent, "Select app"))
        }

        /*
         fun openPDFContent(context: Context, inputStream: InputStream, fileName: String) {
            //saving in cache directory
            try {
                val f = File(Environment.getExternalStorageDirectory(), "ENB")
                if (!f.exists()) {
                    f.mkdirs()
                    LogUtil.e("Directory Created")
                }
                val fileNameExtension =
                    fileName.ifEmpty { "bill_ss" + ".pdf" }
                val file = inputStream.saveToFile(f.absolutePath, fileNameExtension)

                LogUtil.e("File Path: ${file.absolutePath}")
                LogUtil.e("File Name: ${file.name}")

                val uri = FileProvider.getUriForFile(
                    context,
                    BuildConfig.APPLICATION_ID + ".provider", file
                )
                LogUtil.e("URI: $uri")
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    setDataAndType(uri, "application/pdf")
                }
                context.startActivity(Intent.createChooser(intent, "Select app"))
            } catch (e: NoSuchFileException) {
                e.printStackTrace()
            }

        }
        */
    }
}
