package paddo.schrittzaehler;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.util.ArrayList;


public class zaehler extends Activity {

    private TextView tvX;       //X-Achse
    private TextView tvY;       //Y-Achse
    private TextView tvZ;       //Z-Achse

    private TextView textSensitive;

    private TextView tvSteps;   //Zeigt Schritte an

    private Button btnStats;    //geht zur Statistik

    public static int numSteps;
    private boolean isStep;
    private float trigger;
    //ArrayList<SaveDate> datumsichern = new ArrayList<SaveDate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_zaehler);
        tvX = (TextView) findViewById(R.id.tvX);
        tvY = (TextView) findViewById(R.id.tvY);
        tvZ = (TextView) findViewById(R.id.tvZ);
        tvSteps = (TextView) findViewById(R.id.textSteps);
        textSensitive = (TextView) findViewById(R.id.textSensitive);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnStats = (Button) findViewById(R.id.btnStats);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);


        numSteps = 0;
        trigger = 10.0f;

        seekBar.setProgress((int) trigger); //Der Schritt wird zwar erst bei gforce>=10 getriggert, der progress der SeekBar steht aber immer auf 0
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        textSensitive.setText(String.valueOf(trigger));


        enableAccelerometerListening();
    }


    private void enableAccelerometerListening() {
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener()
    {
        public void onSensorChanged(SensorEvent event)
        {
            float gforce = (event.values[0] * event.values[0] + event.values[1] * event.values[1] + event.values[2] * event.values[2]) / (SensorManager.GRAVITY_EARTH + SensorManager.GRAVITY_EARTH);

            if(gforce >trigger){
                numSteps++;
                isStep=true;
            }

            if(gforce <=trigger && isStep){
                isStep=false;
            }

            /*if(gforce>test){
                test=gforce;
            }*/

            tvX.setText(String.valueOf(event.values[0]));
            tvY.setText(String.valueOf(event.values[1]));
            tvZ.setText(String.valueOf(event.values[2]));
            tvSteps.setText(String.valueOf(numSteps));
        }


        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    public void resetSteps(View v){
        numSteps=0;
        tvSteps.setText(String.valueOf(numSteps));
    }

    public void switchScreen (View v){
        startActivity(new Intent(getApplicationContext(), Statistik.class));
    }

    /*public void sichereDatum (View v){
        int gespeichert = datumsichern.size();
        SaveDate test = null;
        if (datumsichern.isEmpty() || gespeichert != datumsichern.size()) {
            test = new SaveDate();
        }
        datumsichern.add(gespeichert, test);
    }*/ //Was genau ich da gemacht hab, ist aus reiner Verzweiflung entstanden und bringt nichts

    private OnSeekBarChangeListener seekBarChangeListener =
            new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    trigger = (float)seekBar.getProgress()/100;
                    textSensitive.setText(String.valueOf(trigger));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_zaehler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
