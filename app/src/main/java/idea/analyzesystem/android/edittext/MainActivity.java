package idea.analyzesystem.android.edittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import idea.analyzesystem.android.edittext.ip.IPView;
import idea.analyzesystem.android.edittext.mac.MacView;
import idea.analyzesystem.android.edittext.port.PortView;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.ipView)
    IPView ipView;

    @Bind(R.id.macView)
    MacView macView;

    @Bind(R.id.portView)
    PortView portView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ImeOptionsHelper.onNextAction(getImeOptionWithEditText());
    }

    public ArrayList<EditText> getImeOptionWithEditText() {
        ArrayList<EditText> result = new ArrayList<>();
        result.addAll(ipView.getChildEditTextViews());
        result.addAll(macView.getChildEditTextViews());
        result.add(portView);
        return result;
    }
}
