package device.apps.setonehourbackward;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

public class SharedPrefRepository {

    private final String PREF_NAME = "SetOneHourBackward_PREFERENCES";
    private final String PROFILE_KEY = "SetOneHourBackward";
    private SharedPreferences sharedPreferences;
    private static SharedPrefRepository instance;

    private SharedPrefRepository(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPrefRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (SharedPrefRepository.class) {
                if (instance == null) {
                    instance = new SharedPrefRepository(context);
                }
            }
        }
        return instance;
    }

    @NonNull
    public boolean isApply() {
        return sharedPreferences.getBoolean(PROFILE_KEY, false);
    }

    public void set(boolean isApply) {
        sharedPreferences
                .edit()
                .putBoolean(PROFILE_KEY, isApply)
                .apply();
    }

    public void delete() {
        sharedPreferences
                .edit()
                .remove(PROFILE_KEY)
                .apply();
    }
}
