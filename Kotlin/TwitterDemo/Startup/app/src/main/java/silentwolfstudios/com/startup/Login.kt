package silentwolfstudios.com.startup

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

import kotlinx.android.synthetic.main.activity_main.*



class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ivImagePerson.setOnClickListener(View.OnClickListener {
            //TODO SELECT IMAGE FROM PHONE
            checkPermission()
        })
    }


    var READIMAGE:Int=730 //random values for ID
    fun checkPermission(){
        if (Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),READIMAGE)
                return
            }
        }
        loadImage()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            READIMAGE -> {
                 if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

                 } else{
                     Toast.makeText(applicationContext,"Cannot access your images",Toast.LENGTH_LONG).show()

                 }
            }
            else-> { super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    val PICK_IMAGE_CODE=123
    fun loadImage(){
        //TODO Load Image
        var intent=Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMAGE_CODE ) //Activity expects result, OnAcitivtyResults will be called after activity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_IMAGE_CODE && data!=null && resultCode== RESULT_OK){
            val selectedImage=data.data
            val filePathColum= arrayOf(MediaStore.Images.Media.DATA)
            val cursor= contentResolver.query(selectedImage,filePathColum,null,null,null)
            cursor.moveToFirst()
            val columIndex=cursor.getColumnIndex(filePathColum[0])
            val picturePATH=cursor.getString(columIndex);
            cursor.close()
            ivImagePerson.setImageBitmap(BitmapFactory.decodeFile(picturePATH))



        }
    } //Request Permission and Select Image from Phone



    fun buLogin (view: View){

    }
}
