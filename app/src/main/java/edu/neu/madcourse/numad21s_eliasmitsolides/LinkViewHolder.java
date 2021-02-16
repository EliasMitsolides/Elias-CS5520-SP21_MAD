package edu.neu.madcourse.numad21s_eliasmitsolides;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkViewHolder extends RecyclerView.ViewHolder {
   public TextView itemUri;
   public TextView itemName;

   public LinkViewHolder(View linkView, final LinkClickListener listener){
      super(linkView);
      itemName = linkView.findViewById(R.id.item_name);
      itemUri = linkView.findViewById(R.id.item_url);

      //since LinkCard extends  LinkClickListener, a click here will trigger the thing
      linkView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (listener != null) {

               String url_name = itemUri.getText().toString();
               boolean isgood = URLUtil.isValidUrl(url_name);
               if (isgood) {
                  Uri web = Uri.parse(url_name);
                  Intent webIntent = new Intent(Intent.ACTION_VIEW, web);

                  //check valid input
                  //abc.xyz -> google.com
                  //ftp:/
                  //google.com www.google.com http://www.google.com https://...
                  v.getContext().startActivity(webIntent);
               }
               else if (isgood == false){

                  String url_name_changed = "https://" + url_name;
                  boolean isgoodnow = URLUtil.isValidUrl(url_name_changed);
                  if (isgoodnow) {
                     Uri web = Uri.parse(url_name_changed);
                     Intent webIntent = new Intent(Intent.ACTION_VIEW, web);

                     //check valid input
                     //abc.xyz -> google.com
                     //ftp:/
                     //google.com www.google.com http://www.google.com https://...
                     v.getContext().startActivity(webIntent);
                  }
               }
            }
         }
      });


   }
   public void urlNoGood(View view)
   {
      Snackbar.make(view, "URL not valid, sorry about that.", Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();

   }
}
