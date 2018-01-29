package idea.analyzesystem.android.edittext.ip;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import idea.analyzesystem.android.edittext.AbsEditText;

/**
 * Created by idea on 2016/7/15.
 */
public class IPEditText extends AbsEditText {

    public IPEditText(Context context) {
        this(context,null,0);
    }

    public IPEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IPEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMaxLength() {
        return 3;
    }

    @Override
    public char[] getInputFilterAcceptedChars() {
        return new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    @Override
    public boolean checkInputValue() {
        String textValue = getText().toString().trim();
        if(textValue.isEmpty()){
            return false;
        }else{
            int number = Integer.valueOf(textValue);
            return number>=0&&number<=255;
        }
    }

    @Override
    public int getEditTextInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }
}