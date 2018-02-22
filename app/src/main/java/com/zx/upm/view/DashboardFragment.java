package com.zx.upm.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.zx.upm.R;
import com.zx.upm.face.view.FaceMainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    public static DashboardFragment newInstance(String info) {
        Bundle args = new Bundle();
        DashboardFragment fragment = new DashboardFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, null);
        ImageButton faceButton = (ImageButton) view.findViewById(R.id.faceButton);
        faceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), FaceMainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
      //      case PHOTO_REQUEST_TAKEPHOTO:
//            {
//                if(resultCode == RESULT_OK)
//                {
//                    //将拍摄的照片显示出来
//                    try
//                    {
//                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        picture.setImageBitmap(bitmap);
//                    }
//                    catch (FileNotFoundException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            break;
        }
    }
}
