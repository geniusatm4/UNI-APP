package com.zx.upm.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.zx.upm.R;
import com.zx.upm.adapter.MessageVOAdapter;
import com.zx.upm.face.service.MessageService;
import com.zx.upm.vo.MessageSearchResult;
import com.zx.upm.vo.MessageVO;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener ,AdapterView.OnItemClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private MessageVOAdapter adapter;
    private List<MessageVO> list = new ArrayList<>();
    private ListView listview;
    private MessageService messageService;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        messageService = new MessageService();

        View view = inflater.inflate(R.layout.fragment_message, null);
        listview = (ListView) view.findViewById(R.id.listview);

        initView(view);
        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        MessageVO vo = (MessageVO) view.getTag();

        Toast.makeText(getContext(), vo.getContent(), Toast.LENGTH_LONG).show();
    }

    private void initView(View view) {
        swipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);

        MessageSearchResult result = messageService.getMessages();
        // adapter = new ArrayAdapter<MessageVO>(getActivity(), android.R.layout.fragment_item, list);
        adapter = new MessageVOAdapter(getActivity(), list);

        //  adapter = new MessageVOAdapter(list, this);
        list.addAll(result.getMessages());

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

        //改变加载显示的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED);
        //设置背景颜色
        //swipeRefreshLayout.setBackgroundColor(Color.YELLOW);
        //设置初始时的大小
        //swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        //设置监听
        swipeRefreshLayout.setOnRefreshListener(this);
        //设置向下拉多少出现刷新
        swipeRefreshLayout.setDistanceToTriggerSync(100);
        //设置刷新出现的位置
        swipeRefreshLayout.setProgressViewEndTarget(false, 200);






    }

    @Override
    public void onRefresh() {
        //设置每次刷新时需要更新的数据
        list.clear();

        MessageSearchResult result = messageService.getMessages();
        list.addAll(result.getMessages());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //然刷新控件停留两秒后消失
                    Thread.sleep(1000);
                    handler.post(new Runnable() {//在主线程执行
                        @Override
                        public void run() {
                            //更新数据
                            adapter.notifyDataSetChanged();
                            //停止刷新
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
