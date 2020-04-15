package com.kty.first;

public class BoardData {

    //item_list.xml 에있는 세개의 컴포넌트들에 대한 객체생성

    private int ImageView_profile;
    private  String TextView_tv_name;
    private  String TextView_content_name;

    public int getImageView_profile() {
        return ImageView_profile;
    }

    public void setImageView_profile(int imageView_profile) {
        ImageView_profile = imageView_profile;
    }

    public String getTextView_tv_name() {
        return TextView_tv_name;
    }

    public void setTextView_tv_name(String textView_tv_name) {
        TextView_tv_name = textView_tv_name;
    }

    public String getTextView_content_name() {
        return TextView_content_name;
    }

    public void setTextView_content_name(String textView_content_name) {
        TextView_content_name = textView_content_name;
    }

    public BoardData(int imageView_profile, String textView_tv_name, String textView_content_name) {
        ImageView_profile = imageView_profile;
        TextView_tv_name = textView_tv_name;
        TextView_content_name = textView_content_name;
    }
}
