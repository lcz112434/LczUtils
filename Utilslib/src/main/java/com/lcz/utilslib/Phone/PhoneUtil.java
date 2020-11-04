package com.lcz.utilslib.Phone;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by lcz on 2016/9/28.
 * 获取所有联系人
 */

public class PhoneUtil {
    // 号码
    public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
    // 联系人姓名
    public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
    //上下文对象
    private Context context;
    //联系人提供者的uri
    private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

    public PhoneUtil(Context context) {
        this.context = context;
    }

    //获取所有联系人
    public List<PhoneDto> getPhone() {
        List<PhoneDto> phoneDtos = new ArrayList<>();
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(phoneUri, new String[]{NUM, NAME}, null, null, null);
        while (cursor.moveToNext()) {
            PhoneDto phoneDto = new PhoneDto(cursor.getString(cursor.getColumnIndex(NAME)).replaceAll(" ",""), cursor.getString(cursor.getColumnIndex(NUM)).replaceAll(" ",""));


            phoneDtos.add(phoneDto);
        }
        return phoneDtos;
    }

}
