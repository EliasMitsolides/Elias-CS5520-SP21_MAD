
package edu.neu.madcourse.numad21s_eliasmitsolides;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class LinkCard implements LinkClickListener {

    //private final int imageSource;
    private final String url;
    private final String linkText;


    public LinkCard(String url, String linkText){
        this.url = url;
        this.linkText = linkText;
    }

    @Override
    public void onItemClick(int position) {
        //use intent with this url to open up a web browser??
//        Uri web = Uri.parse(getItemURL());
//        Intent webIntent = new Intent(Intent.ACTION_VIEW, web);

        //startActivity(webIntent);
    }

    public String getItemURL() {
        return url;
    }

    public String getItemName() {
        return linkText;
    }
}
