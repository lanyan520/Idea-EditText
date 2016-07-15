package idea.analyzesystem.android.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import idea.analyzesystem.android.edittext.mac.MacView;

public class MainActivity extends AppCompatActivity {

    private MacView macView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
