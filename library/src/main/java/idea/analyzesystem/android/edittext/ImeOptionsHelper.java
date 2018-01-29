package idea.analyzesystem.android.edittext;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by idea on 2016/8/3.
 */
public class ImeOptionsHelper {

    public static void onNextAction(ArrayList<EditText> editTexts) {

        for (int i = 0; i < editTexts.size()-1; i++) {
            OnEditorActionListenerImpl onEditorActionListener = new OnEditorActionListenerImpl(editTexts.get(i), editTexts.get(i + 1));
            editTexts.get(i).setOnEditorActionListener(onEditorActionListener);
        }
    }

    static class OnEditorActionListenerImpl implements TextView.OnEditorActionListener {

        private EditText requestEditText;
        private EditText clearEditText;

        OnEditorActionListenerImpl(EditText clearEditText, EditText requestEditText) {
            this.clearEditText = clearEditText;
            this.requestEditText = requestEditText;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_NEXT|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                clearEditText.clearFocus();
                requestEditText.requestFocus();
                return true;
            }
            return false;
        }
    }
}
