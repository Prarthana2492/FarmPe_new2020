package com.SevenNine.Essentials.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.SevenNine.Essentials.G_Vision_Controller;
import com.SevenNine.Essentials.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class CameraActivity extends AppCompatActivity  implements SurfaceHolder.Callback  {

    private static final int REQUEST_PERMISSIONS = 100;
    public Bitmap selectedImage;
    private Camera.Size mPreviewSize;

    public static   Camera camera;
    SurfaceView surfaceView;
    public static SurfaceHolder surfaceHolder;
    int  camNum;
    int camBackId = Camera.CameraInfo.CAMERA_FACING_BACK;
    int camFrontId = Camera.CameraInfo.CAMERA_FACING_FRONT;
    G_Vision_Controller g_vision_controller;
    Camera.PictureCallback jpegCallback;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    LinearLayout linear_layout;
    int currentCameraId=camBackId;
    ImageView imageView,selfie;
    public static   String street_addrss;
    CircleImageView gallery;
    public static final int PICK_IMAGE = 1234;
    Fragment selectedFragment;
    public static Bitmap selectedImage2;
    ConstraintLayout constraintLayout;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("aaaaaaaaaaaaaaaaaaaaa"+requestCode+"gfjhygj"+resultCode);
        if (requestCode == 100&& resultCode == RESULT_OK && data != null) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream =getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                if (!(selectedImage==null)){

                    Intent intent = new Intent(getApplicationContext(), UploadCamera_Activity.class);
                    intent.putExtra("name",getPath(imageUri));
                    System.out.println("ccccccccccccccccccccccccccc"+imageUri);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(), "You haven't picked Image",Toast.LENGTH_LONG).show();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();

                   Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_camera);
        g_vision_controller=G_Vision_Controller.getInstance();


        //  final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        // final Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        Intent intent = getIntent();
        street_addrss= intent.getStringExtra("street_adress");
        System.out.println("kkkkkkkkkkkkkkkk"+street_addrss);

        imageView=findViewById(R.id.image);
      //  selfie=findViewById(R.id.selfie);
      //  gallery = findViewById(R.id.gallery);
        constraintLayout=findViewById(R.id.const_lyt);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera.takePicture(null, null, jpegCallback);



               /* try {
                    camera = android.hardware.Camera.open();
                }catch (RuntimeException ex){}
// Here is your problem. Catching RuntimeException will make camera object null,
// so method 'getParameters();' won't work :)
                android.hardware.Camera.Parameters parameters;
                parameters = camera.getParameters();
              //  camera.setFaceDetectionListener(faceDetectionListener);
                camera.startFaceDetection();
                parameters.setPreviewFrameRate(20);
                List<Camera.Size> customSizes = parameters.getSupportedPreviewSizes();
                Camera.Size customSize = customSizes.get(0); //Added size
                parameters.setPreviewSize(customSize.width, customSize.height);
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
                camera.setParameters(parameters);
                camera.setDisplayOrientation(90);


                try {

                    camera.setPreviewDisplay(surfaceHolder);
                    camera.startPreview();


                }catch (Exception e){



                }
*/
            }

        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // to go to gallery
                startActivityForResult(i, 100);
            }
        });



        selfie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //

                camera.stopPreview();

//NB: if you don't release the current camera before switching, you app will crash
                camera.release();

//swap the id of the camera to be used
//swap the id of the camera to be used
                if(currentCameraId == Camera.CameraInfo.CAMERA_FACING_BACK){
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_FRONT;
                }
                else {
                    currentCameraId = Camera.CameraInfo.CAMERA_FACING_BACK;
                }
                camera = Camera.open(currentCameraId);

                camera.setDisplayOrientation(90);
                Camera.Parameters param;
                param = camera.getParameters();

                try {
                    // The Surface has been created, now tell the camera where to draw
                    // the preview.
                    // modify parameter
                    // param.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
                    param.setPreviewSize(352, 288);
                    param.setPictureSize(1200, 900);
                    camera.setParameters(param);



                    camera.setPreviewDisplay(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();


            /*    Camera.CameraInfo currentCamInfo = new Camera.CameraInfo();
                camNum = Camera.getNumberOfCameras();
                surfaceHolder.addCallback(CameraActivity.this);
                surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                //if camera is running
                System.out.println("hhhhhhhhhhhhhh"+camNum);

                    if (camNum > 1) {
                        surfaceHolder.addCallback(CameraActivity.this);
                        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

                        jpegCallback = new Camera.PictureCallback() {
                            public void onPictureTaken(byte[] data, Camera camera) {

                                File destination = new File(Environment.getExternalStorageDirectory(),
                                        System.currentTimeMillis() + ".jpg");

                                FileOutputStream outStream = null;
                                try {
                                    outStream = new FileOutputStream(destination);
                                    outStream.write(data);
                                    outStream.close();
                                    Log.d("Log", "onPictureTaken - wrote bytes: " + destination);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                }*/

             /*   Bundle bundle = new Bundle();
                bundle.putString("name", destination.getPath() );

                Fragment fragment = new ListYourFarmsFive() ;
                fragment.setArguments(bundle);

                FragmentManager fm =getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                System.out.println("abc1111hhhhhhhhh"+destination.getPath());

                refreshCamera();*/
            }
            /*  };
             *//*    camera.stopPreview();
                        //camera.takePicture(null, null, PictureCallback);
                        camera.release();*//*
                        if (currentCamInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                            //surfaceHolder.addCallback(CameraActivity.this);

                        } else {
                            //switch camera to front camera
                            camera = Camera.open(camFrontId);
                        }
*/

            //  }
            // }

        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
            }
        }

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();




        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        //  gv_folder = (GridView)findViewById(R.id.gv_folder);





        jpegCallback = new Camera.PictureCallback() {
            public void onPictureTaken(byte[] data, Camera camera) {

                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");

                FileOutputStream outStream = null;
                try {
                    outStream = new FileOutputStream(destination);
                    outStream.write(data);
                    outStream.close();
                    Log.d("Log", "onPictureTaken - wrote bytes: " + destination);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                }
               /* selectedImage = BitmapFactory.decodeFile(String.valueOf(destination));
                // final InputStream imageStream =getContentResolver().openInputStream();
                // selectedImage = BitmapFactory.decodeStream(imageStream);


                ByteArrayOutputStream byteArrayOutputStreamObject ;

                byteArrayOutputStreamObject = new ByteArrayOutputStream();

                // Converting bitmap image to jpeg format, so by default image will upload in jpeg format.
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

                g_vision_controller.callCloudVision(selectedImage, CameraActivity.this,"farm");*/


                //refreshCamera();



                Intent intent = new Intent(CameraActivity.this, UploadCamera_Activity.class);
                intent.putExtra("name", destination.getPath() );
                System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqq"+destination.getPath());

                startActivity(intent);

              /*  Fragment fragment = new Gallery_Fragment() ;
                fragment.setArguments(bundle);

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frame_layout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
                System.out.println("abc1111hhhhhhhhh"+destination.getPath());*/

                refreshCamera();
            }
        };



        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
                System.out.println("abc1111hhhhhhhhh"+"granted");

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        }else {
            Log.e("Else","Else");
            // fn_imagespath();
        }

        if (!isDeviceSupportCamera()) {
            //Toast.makeText(this).getApplicationContext(),
            //  "Sorry! Your device doesn't support camera",
            //   Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        System.out.println("gggggggggggggggggggggggggggggggggg");

        try {
            // open the camera
            camera = Camera.open(camBackId);
            camera.setDisplayOrientation(90);
            Camera.Parameters p = camera.getParameters();
            p.set("jpeg-quality", 100);
            p.set("orientation", "landscape");
            p.setRotation(90);
            p.setPictureFormat(PixelFormat.JPEG);
            // p.setPreviewSize(h, w);
            camera.setParameters(p);

        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();

        try {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            // modify parameter
            // param.setPreviewSize(mPreviewSize.width, mPreviewSize.height);
            param.setPreviewSize(352, 288);
            param.setPictureSize(1200, 900);
            camera.setParameters(param);
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }



    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        refreshCamera();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // stop preview and release camera
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            System.out.println("abcccccamerA");

            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();

            System.out.println("abcccc");
        } catch (Exception e) {
            System.out.println("abcccc11122");

            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }




/*
    private ArrayList<String> fn_imagespath() {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        final int int_position = 0;

        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        cursor = getApplicationContext().getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        System.out.println("camera" + column_index_data);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            System.out.println("camera111111111");

            System.out.println("camera22" + column_index_data);

            listOfAllImages.add(absolutePathOfImage);
            Image image = new Image(absolutePathOfImage.toString());

            //movieList.add(image);
            System.out.println("camera111111111" + absolutePathOfImage);
            if (boolean_folder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(al_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                al_images.get(int_position).setAl_imagepath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images obj_model = new Model_images();
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                obj_model.setAl_imagepath(al_path);

                al_images.add(obj_model);


            }
        }

        obj_adapter = new Adapter_PhotosFolder(getActivity().getApplicationContext(),al_images);
        //  gv_folder.setAdapter(obj_adapter);

        System.out.println("lllllllllllll"+movieList.size());


        mAdapter = new ImageAdapter(getActivity(),movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        return listOfAllImages;

    }
*/

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
