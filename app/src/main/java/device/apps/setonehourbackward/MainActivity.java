package device.apps.setonehourbackward;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "DelayOneHour";
    private AlarmManager am;
    private SharedPrefRepository pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        pref = SharedPrefRepository.getInstance(getApplicationContext());
        Log.d(TAG, "Have you ever delayed time before? : " + pref.isApply());
        if (!pref.isApply()) setOneHourBackward();
    }

    private void setOneHourBackward() {
        try {
            Calendar now = Calendar.getInstance();
            System.out.println("now : " + now.getTime());
            now.add(Calendar.HOUR, -1);
            Log.d(TAG, "after modify now : " + now.getTime());
            am.setTime(now.getTimeInMillis());
            pref.set(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}