package com.example.step04gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GalleryAdapter extends BaseAdapter {
    //필요한 필드 정의하기
    private LayoutInflater inflater; // 레이아웃 전개자 객체를 담을 필드
    private Context context; // 앱 컨텍스트를 담을 필드
    private int layoutRes; // 셀의 레이아웃 리소스 id를 담을 필드
    private List<GalleryDto> list; // 모델

    public GalleryAdapter(Context context, int layoutRes, List<GalleryDto> list){
        this.context = context;
        this.layoutRes = layoutRes;
        this.list = list;
        //레이아웃 전개자 객체의 참조값을 얻어내서 필드에 저장
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    // i번째 모델의 아이디(PK) 가 있으면 리턴
    @Override
    public long getItemId(int i) {
        return list.get(i).getNum();
    }

    // i번째 cell의 View를 만들거나 수정해서 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            //레이아웃 전개자 객체를 이용해서 cell view를 만든다.
            convertView=inflater.inflate(layoutRes, viewGroup, false);
        }
        // position 에 해당하는 GalleryDto 를 얻어내서
        GalleryDto dto=list.get(position);
        ImageView imageView = convertView.findViewById(R.id.imageView);
        TextView textWriter = convertView.findViewById(R.id.writer);
        TextView textCaption = convertView.findViewById(R.id.caption);
        TextView textRegdate = convertView.findViewById(R.id.regdate);

        //TextView에는 작성자, 제목, 등록일을 출력하고
        textWriter.setText(dto.getWriter());
        textCaption.setText(dto.getCaption());
        textRegdate.setText(dto.getRegdate());

        //ImageView에는 Glide를 이용해서 이미지를 출력하기
        Glide.with(context)
                .load(dto.getImagePath())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);

        // gallery 정보가 출력된 View 객체 리턴해주기
        return convertView;
    }
}
