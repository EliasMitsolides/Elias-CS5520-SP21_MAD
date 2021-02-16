package edu.neu.madcourse.numad21s_eliasmitsolides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivityUrlList extends AppCompatActivity {
    private ArrayList<LinkCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private LinkViewAdapter linkViewAdapter;
    private RecyclerView.LayoutManager LinkLayoutManager;
    private FloatingActionButton addLinkButton;

    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_url_list);

        init(savedInstanceState);

        addLinkButton = findViewById(R.id.addLinkButton);
        addLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int pos = 0;
                enterHyperLinkPopup(pos);

            }
        });




    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++){
            //url
            outState.putString(KEY_OF_INSTANCE + i + "0", itemList.get(i).getItemURL());
            outState.putString(KEY_OF_INSTANCE + i + "1", itemList.get(i).getItemName());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {


        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String url = savedInstanceState.getString(KEY_OF_INSTANCE + i + "0");
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");



                    LinkCard itemCard = new LinkCard(url, itemName);

                    itemList.add(itemCard);
                }
            }
        }
        // The first time to open this Activity
        //TODO: ASK if this would even let them have openable links.
        else {
            LinkCard item1 = new LinkCard("google.com", "Google");
            LinkCard item2 = new LinkCard("yahoo.com", "Yahoo");
            LinkCard item3 = new LinkCard("reddit.com", "Reddit");
            itemList.add(item1);
            itemList.add(item2);
            itemList.add(item3);
        }

    }

    private void createRecyclerView() {


        LinkLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.LinkRecyclerView);
        recyclerView.setHasFixedSize(true);

        linkViewAdapter = new LinkViewAdapter(itemList);
        LinkClickListener linkClickListener = new LinkClickListener() {
            @Override
            public void onItemClick(int position) {
                itemList.get(position).onItemClick(position);
                linkViewAdapter.notifyItemChanged(position);
                //TODO:ASK, should I have this here?
                //launch(recyclerView);

            }

        };
        linkViewAdapter.setOnLinkClickListener(linkClickListener);

        recyclerView.setAdapter(linkViewAdapter);
        recyclerView.setLayoutManager(LinkLayoutManager);


    }

    private void enterHyperLinkPopup(int position) {

        AlertDialog.Builder linkTypeBuilder = new AlertDialog.Builder(MainActivityUrlList.this);

        // Get the layout inflater
        linkTypeBuilder.setTitle("Enter URI now");
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the xml layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //linkTypeBuilder.setView(inflater.inflate(R.layout.type_link_popup, null));

        final View DialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.type_link_popup,null);
        linkTypeBuilder.setView(DialogView);

        final EditText url = (EditText) DialogView.findViewById(R.id.url_edit_text);
        final EditText title = (EditText) DialogView.findViewById(R.id.url_name_text);


        // Add action buttons
        linkTypeBuilder.setPositiveButton(R.string.addlink, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int id) {

                Log.e("HHHHH",id+"");

                String url_name = url.getText().toString();
                String title_name = title.getText().toString();

                addItem(position,url_name,title_name);
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        linkTypeBuilder.create();

        linkTypeBuilder.show();

    }

    private void addItem(int position, String uri, String linkName){
        itemList.add(position, new LinkCard(uri, linkName));
        Toast.makeText(MainActivityUrlList.this, "URI Added To List", Toast.LENGTH_SHORT).show();

        linkViewAdapter.notifyItemInserted(position);
    }

    public void urlNoGood(View view)
    {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}