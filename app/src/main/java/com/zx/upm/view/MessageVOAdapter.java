package com.zx.upm.view;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.zx.upm.R;
import com.zx.upm.face.vo.MessageVO;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MessageVO} and makes a call to the
 */
public class MessageVOAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<MessageVO> mData;

    public MessageVOAdapter(Context context, List<MessageVO> mData) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MessageVO msg=  mData.get(position);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.fragment_item, parent, false);
            TextView contentTextView = (TextView) convertView.findViewById(R.id.content);
            contentTextView.setText(msg.getContent());
            convertView.setTag(msg);
        }
        return convertView;
    }

}
