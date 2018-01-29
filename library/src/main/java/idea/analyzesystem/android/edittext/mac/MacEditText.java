package idea.analyzesystem.android.edittext.mac;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.EditText;

import idea.analyzesystem.android.edittext.AbsEditText;

/**
 * Created by idea on 2016/7/15.
 */
public class MacEditText extends AbsEditText {
    public MacEditText(Context context) {
        this(context,null,0);
    }

    public MacEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MacEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMaxLength() {
        return 2;
    }

    @Override
    public char[] getInputFilterAcceptedChars() {
        return new char[]{'A', 'B', 'C', 'D', 'E', 'F', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    @Override
    public boolean checkInputValue() {
        return getText().toString().trim().length()==2?true:false;
    }

    @Override
    public int getEditTextInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }
}
