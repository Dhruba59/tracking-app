package com.example.bus_tracking;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrCodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_scanner);
    }

    public void scan(View view){
        zXingScannerView =new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(getApplicationContext(),result.getText(),Toast.LENGTH_SHORT).show();
        zXingScannerView.removeAllViews();
        Intent intent = new Intent(qrCodeScanner.this,driverClass.class);
        intent.putExtra("firebaseReference",result.getText());
        startActivity(intent);

    }
}

























//public class qrCodeScanner extends AppCompatActivity {

//    SurfaceView cameraPreview;
//    TextView txtResult;
//    BarcodeDetector barcodeDetector;
//    CameraSource cameraSource;
//    final int RequestCameraPermissionID = 1001;
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case RequestCameraPermissionID: {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                        return;
//                    }
//                    try {
//                        cameraSource.start(cameraPreview.getHolder());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            break;
//        }
 //   }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_qr_code_scanner);

//        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
//        txtResult = (TextView) findViewById(R.id.txtResult);
//
//        barcodeDetector = new BarcodeDetector.Builder(this)
//                .setBarcodeFormats(Barcode.QR_CODE)
//                .build();
//        cameraSource = new CameraSource
//                .Builder(this, barcodeDetector)
//                .setRequestedPreviewSize(640, 480)
//                .build();
//        //Add Event
//        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder surfaceHolder) {
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    //Request permission
//                    ActivityCompat.requestPermissions(qrCodeScanner.this,
//                            new String[]{Manifest.permission.CAMERA},RequestCameraPermissionID);
//                    return;
//                }
//                try {
//                    cameraSource.start(cameraPreview.getHolder());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//                cameraSource.stop();
//
//            }
//        });
//
//        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
//                if(qrcodes.size() != 0)
//                {
//                    txtResult.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //Create vibrate
//                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//                            vibrator.vibrate(1000);
//                            // txtResult.setText(qrcodes.valueAt(0).displayValue);
//
//
////                            Intent intent = new Intent(qrCodeScanner.this, driverClass.class);
////                            intent.putExtra("firebaseReference",qrcodes.toString());
////                            startActivity(intent);
//                            //there will be a intent which will pass the text as a firebase reference
//                            // where the lalong of driverclass will be stored
//                        }
//                    });
//                }
//            }
//        });
 //   }
//}
